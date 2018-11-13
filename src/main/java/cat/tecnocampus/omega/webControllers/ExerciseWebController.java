package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.exercises.*;
import cat.tecnocampus.omega.persistanceController.ExercisesDAOController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;


@Controller
public class ExerciseWebController {
    private final ExercisesDAOController exercisesDAO;

    public ExerciseWebController(ExercisesDAOController exercisesDAO) {
        this.exercisesDAO = exercisesDAO;
    }

    @GetMapping("createExercise/{id}")
    public String createExercise() {
        return "exercise/newExercise";
    }

    @PostMapping("createExercise/{id}")
    public String createExercise(String exercise, @PathVariable String id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", id);
        if (exercise.equals("Test"))
            return "redirect:/createTestExercise/{id}";
        else
            return "redirect:/createFillTheGapExercise/{id}";
    }

    @GetMapping("createTestExercise/{id}")
    public String createTestExercise(Model model) {
        model.addAttribute(new TestExercise());
        model.addAttribute("testQuestion", new Question());
        model.addAttribute("testSolution", new Solution());
        return "exercise/newTestExercise";
    }

    @PostMapping("createTestExercise/{id}")
    public String createTestExercise(@Valid TestExercise testExercise, Errors errors, @PathVariable String id, String testText, String end, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "exercise/newTestExercise";
        }
        exercisesDAO.insertExercise(testExercise, id, "Test");
        QuestionsCreator.testCreator(testText, exercisesDAO, testExercise.getExercise_ID());
        if (end.equals("Finish"))
            return "redirect:/posts";
        else {
            redirectAttributes.addAttribute("id", id);
            return "redirect:/createExercise/{id}";
        }
    }

    @GetMapping("createFillTheGapExercise/{id}")
    public String createFillTheGapExercise(Model model) {
        model.addAttribute("fillTheGapExercise", new FillTheGapExercise());
        return "exercise/newFillTheGapExercise";
    }

    @PostMapping("createFillTheGapExercise/{id}")
    public String createFillTheGapExercise(@Valid FillTheGapExercise fillTheGapExercise, Errors errors, @PathVariable String id, String fillText, String end, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "exercise/newFillTheGapExercise";
        }
        exercisesDAO.insertExercise(fillTheGapExercise, id, "Fill");
        QuestionsCreator.fillTheGapCreator(fillText, exercisesDAO, fillTheGapExercise.getExercise_ID());

        if (end.equals("Finish"))
            return "redirect:/posts";
        else {
            redirectAttributes.addAttribute("id", id);
            return "redirect:/createExercise/{id}";
        }
    }

    @GetMapping("doTest/{post}/{exercise}")
    public String doTest(Model model, @PathVariable String exercise) {
        model.addAttribute("exercise", exercisesDAO.getExerciseByType(exercise, "Test"));
        return "exercise/doTestExercise";
    }

    @PostMapping("doTest/{post}/{exercise}")
    public String doTest(HttpServletRequest request, @PathVariable String post, @PathVariable String exercise, RedirectAttributes redirectAttributes) {
        Map<String, String[]> mp = request.getParameterMap();
        String[] solution = new String[mp.size()];
        int i = 0;
        for (String s : mp.keySet()) {
            solution[i] = mp.get(s)[0];
            i++;
        }
        exercisesDAO.solve(exercise, solution, "admin", "Test");
        redirectAttributes.addAttribute("post", post);
        redirectAttributes.addAttribute("exercise", exercise);
        redirectAttributes.addAttribute("type", "doTest");
        return "redirect:/showMark/{post}/{exercise}/{type}";
    }

    @GetMapping("doFill1/{post}/{exercise}")
    public String doFill1(Model model, @PathVariable String exercise) {
        model.addAttribute("exercise", exercisesDAO.getExerciseByType(exercise, "Fill"));
        return "exercise/doFillTheGapExercise1";
    }

    @PostMapping("doFill1/{post}/{exercise}")
    public String doFill1(@RequestParam(value = "solution") String[] solution, @PathVariable String post, @PathVariable String exercise, RedirectAttributes redirectAttributes, Principal principal) {
        exercisesDAO.solve(exercise, solution, "admin", "Fill");
        redirectAttributes.addAttribute("post", post);
        redirectAttributes.addAttribute("exercise", exercise);
        redirectAttributes.addAttribute("type", "doFill1");
        return "redirect:/showMark/{post}/{exercise}/{type}";
    }

    @GetMapping("doFill2/{post}/{exercise}")
    public String doFill2(Model model, @PathVariable String exercise) {
        model.addAttribute("exercise", exercisesDAO.getExerciseByType(exercise, "Fill"));
        return "exercise/doFillTheGapExercise2";
    }

    @PostMapping("doFill2/{post}/{exercise}")
    public String doFill2(@RequestParam(value = "solution") String[] solution, @PathVariable String post, @PathVariable String exercise, RedirectAttributes redirectAttributes, Principal principal) {
        exercisesDAO.solve(exercise, solution, "admin", "Fill");
        redirectAttributes.addAttribute("post", post);
        redirectAttributes.addAttribute("exercise", exercise);
        redirectAttributes.addAttribute("type", "doFill2");
        return "redirect:/showMark/{post}/{exercise}/{type}";
    }

    @GetMapping("showMark/{post}/{exercise}/{type}")
    public String showMark(Model model, @PathVariable String exercise) {
        Submission submission = exercisesDAO.getSubmission(exercise, "admin");
        model.addAttribute("submission", submission);
        model.addAttribute("mark", exercisesDAO.getMark(submission.getMark()));
        return "exercise/showMark";
    }

    @PostMapping("showMark/{post}/{exercise}/{type}")
    public String showMark(String chosen, @PathVariable String post,@PathVariable String exercise,@PathVariable String type, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", post);
        if (chosen.equals("Return"))
            return "redirect:/tutorial/{id}";
        redirectAttributes.addAttribute("post", post);
        redirectAttributes.addAttribute("exercise", exercise);
        if (chosen.equals("See Result")) {

        }
        return "redirect:/"+type+"/{post}/{exercise}";
    }
}
