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

    @GetMapping("createExercise/{id}/{type}")
    public String createExercise() {
        return "exercise/newExercise";
    }

    @PostMapping("createExercise/{id}/{type}")
    public String createExercise(String exercise, @PathVariable String id,@PathVariable String type, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", id);
        redirectAttributes.addAttribute("type",type);
        if (exercise.equals("Test"))
            return "redirect:/createTestExercise/{id}/{type}";
        else
            return "redirect:/createFillTheGapExercise/{id}/{type}";
    }

    @GetMapping("createTestExercise/{id}/{type}")
    public String createTestExercise(Model model) {
        model.addAttribute(new TestExercise());
        model.addAttribute("testQuestion", new Question());
        model.addAttribute("testSolution", new Solution());
        return "exercise/newTestExercise";
    }

    @PostMapping("createTestExercise/{id}/{type}")
    public String createTestExercise(@Valid TestExercise testExercise, Errors errors, @PathVariable String id,@PathVariable String type, String testText, String end, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "exercise/newTestExercise";
        }
        exercisesDAO.insertExercise(testExercise, id, "Test");
        QuestionsCreator.testCreator(testText, exercisesDAO, testExercise.getExercise_ID());
        redirectAttributes.addAttribute("id", id);
        if (end.equals("Finish"))
            if(type.equals("Tut"))
            return "redirect:/tutorial/{id}";
            else
                return "redirect:/tutorial/{id}";
        else {
            redirectAttributes.addAttribute("id", id);
            redirectAttributes.addAttribute("type",type);
            return "redirect:/createExercise/{id}";
        }
    }

    @GetMapping("createFillTheGapExercise/{id}/{type}")
    public String createFillTheGapExercise(Model model) {
        model.addAttribute("fillTheGapExercise", new FillTheGapExercise());
        return "exercise/newFillTheGapExercise";
    }

    @PostMapping("createFillTheGapExercise/{id}/{type}")
    public String createFillTheGapExercise(@Valid FillTheGapExercise fillTheGapExercise, Errors errors, @PathVariable String id,@PathVariable String type, String fillText, String end, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "exercise/newFillTheGapExercise";
        }
        exercisesDAO.insertExercise(fillTheGapExercise, id, "Fill");
        QuestionsCreator.fillTheGapCreator(fillText, exercisesDAO, fillTheGapExercise.getExercise_ID());

        if (end.equals("Finish"))
            if(type.equals("Tut"))
                return "redirect:/tutorial/{id}";
            else
                return "redirect:/tutorial/{id}";
        else {
            redirectAttributes.addAttribute("id", id);
            redirectAttributes.addAttribute("type",type);
            return "redirect:/createExercise/{id}/{type}";
        }
    }

    @GetMapping("doTest/{type}/{post}/{exercise}")
    public String doTest(Model model, @PathVariable String type, @PathVariable String exercise) {
        model.addAttribute("exercise", exercisesDAO.getExerciseByType(exercise, "Test"));
        return "exercise/doTestExercise" + exercisesDAO.type(type);
    }

    @PostMapping("doTest/{type}/{post}/{exercise}")
    public String doTest(HttpServletRequest request, @PathVariable String type, @PathVariable String post, @PathVariable String exercise, RedirectAttributes redirectAttributes) {
        if (type.equals("do")) {
            Map<String, String[]> mp = request.getParameterMap();
            String[] solution = new String[mp.size()];
            int i = 0;
            for (String s : mp.keySet()) {
                if (!s.equals("id")) {
                    //System.out.println(s);
                    solution[i] = mp.get(s)[0];
                    //System.out.println("\t" + solution[i]);
                    i++;
                }
            }
            exercisesDAO.solve(exercise, solution, "admin", "Test");
        }
        redirectAttributes.addAttribute("post", post);
        redirectAttributes.addAttribute("exercise", exercise);
        redirectAttributes.addAttribute("type", "doTest");
        redirectAttributes.addAttribute("drag", "0");
        return "redirect:/showMark/{post}/{exercise}/{type}/{drag}";
    }

    @GetMapping("doFill/{type}/{post}/{exercise}/{drag}")
    public String doFill(Model model, @PathVariable String type, @PathVariable String drag, @PathVariable String exercise) {
        model.addAttribute("exercise", exercisesDAO.getExerciseByType(exercise, "Fill"));
        String html;
        switch (drag) {
            case "1":
                html = "doFillTheGapExercise1";
                break;
            case "2":
                html = "doFillTheGapExercise2";
                break;
            default:
                html = "";
                break;
        }
        if (type.equals("Result"))
            html = html.substring(0, html.length() - 1);
        return "exercise/" + html + exercisesDAO.type(type);
    }

    @PostMapping("doFill/{type}/{post}/{exercise}/{drag}")
    public String doFill(@RequestParam(value = "solution") String[] solution, @PathVariable String type, @PathVariable String post, @PathVariable String exercise, RedirectAttributes redirectAttributes, Principal principal) {
        if (type.equals("do")) {
            exercisesDAO.solve(exercise, solution, "admin", "Fill");
        }
        redirectAttributes.addAttribute("post", post);
        redirectAttributes.addAttribute("exercise", exercise);
        redirectAttributes.addAttribute("type", "doFill");
        return "redirect:/showMark/{post}/{exercise}/{type}";
    }

    @GetMapping("showMark/{post}/{exercise}/{type}/{drag}")
    public String showMark(Model model, @PathVariable String exercise) {
        Submission submission = exercisesDAO.getSubmission(exercise, "admin");
        model.addAttribute("submission", submission);
        model.addAttribute("mark", exercisesDAO.getMark(submission.getMark()));
        String pass = "YOU ";
        if (submission.getPass())
            pass += "PASS";
        else
            pass += "FAILED";
        model.addAttribute("pass", pass);
        return "exercise/showMark";
    }

    @PostMapping("showMark/{post}/{exercise}/{type}/{drag}")
    public String showMark(String chosen, @PathVariable String post, @PathVariable String exercise, @PathVariable String type, @PathVariable String drag, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", post);
        if (chosen.equals("Return"))
            return "redirect:/tutorial/{id}";
        redirectAttributes.addAttribute("post", post);
        redirectAttributes.addAttribute("exercise", exercise);
        redirectAttributes.addAttribute("type", "do");
        if (type.equals("doFill")) {
            redirectAttributes.addAttribute("drag", drag);
            type += "/{type}/{post}/{exercise}/{drag}";
        } else
            type += "/{type}/{post}/{exercise}";
        if (chosen.equals("See Results"))
            redirectAttributes.addAttribute("type", "Result");
        else
            redirectAttributes.addAttribute("type", "do");
        return "redirect:/" + type;
    }
}
