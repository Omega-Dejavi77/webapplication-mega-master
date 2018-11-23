package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.exercises.*;
import cat.tecnocampus.omega.persistanceController.ExerciseController;
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
    private final ExerciseController exerciseController;

    public ExerciseWebController(ExerciseController exerciseController) {
        this.exerciseController = exerciseController;
    }

    @GetMapping("exercise/create/{id}")
    public String createExercise() {
        return "exercise/newExercise";
    }

    @PostMapping("exercise/create/{id}")
    public String createExercise(String exercise, @PathVariable String id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", id);
        if (exercise.equals("Test"))
            return "exercise/quiz/create/{id}";
        else
            return "exercise/fillTheBlank/create/{id}";
    }

    @GetMapping("exercise/quiz/create/{id}")
    public String createTestExercise(Model model) {
        model.addAttribute(new TestExercise());
        model.addAttribute("testQuestion", new Question());
        model.addAttribute("testSolution", new Solution());
        return "exercise/newTestExercise";
    }

    @PostMapping("exercise/quiz/create/{id}")
    public String createTestExercise(@Valid TestExercise testExercise, Errors errors, @PathVariable String id, String end, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "exercise/newTestExercise";
        }
        exerciseController.addExercise(testExercise, id, "Test");
        redirectAttributes.addAttribute("id", id);
        if (end.equals("Finish"))
            return "redirect:/tutorial/{id}";
        else {
            return "redirect:/exercise/create/{id}";
        }
    }

    @GetMapping("exercise/fillTheBlank/create/{id}")
    public String createFillTheGapExercise(Model model) {
        model.addAttribute("fillTheGapExercise", new FillTheGapExercise());
        return "exercise/newFillTheGapExercise";
    }

    @PostMapping("exercise/fillTheBlank/create/{id}")
    public String createFillTheGapExercise(@Valid FillTheGapExercise fillTheGapExercise, Errors errors, @PathVariable String id, String end, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "exercise/newFillTheGapExercise";
        }
        exerciseController.addExercise(fillTheGapExercise, id, "Fill");
        redirectAttributes.addAttribute("id", id);
        if (end.equals("Finish"))
            return "redirect:/tutorial/{id}";
        else {
            return "redirect:/exercise/create/{id}";
        }
    }

    @GetMapping("exercise/quiz/{type}/{post}/{exercise}")
    public String doTest(Model model, @PathVariable String type, @PathVariable String exercise) {
        model.addAttribute("exercise", exerciseController.getExerciseByType(exercise, "Test"));
        return "exercise/doTestExercise" + exerciseController.type(type);
    }

    @PostMapping("exercise/quiz/{type}/{post}/{exercise}")
    public String doTest(HttpServletRequest request, @PathVariable String type, @PathVariable String post, @PathVariable String exercise, RedirectAttributes redirectAttributes, Principal principal) {
        if (type.equals("do")) {
            Map<String, String[]> mp = request.getParameterMap();
            String[] solution = new String[mp.size()];
            int i = 0;
            for (String s : mp.keySet()) {
                if (!s.equals("id")) {
                    solution[i] = mp.get(s)[0];
                    i++;
                }
            }
            exerciseController.solve(exercise, solution, principal.getName(), "Test");
        }
        redirectAttributes.addAttribute("post", post);
        redirectAttributes.addAttribute("exercise", exercise);
        return "redirect:/exercise/mark/quiz/{post}/{exercise}";
    }

    @GetMapping("exercise/fillTheBlank/{type}/{post}/{exercise}")
    public String doFill(Model model, @PathVariable String type, @PathVariable String exercise) {
        Exercise fillTheGapExercise = exerciseController.getExerciseByType(exercise, "Fill");
        model.addAttribute("exercise", fillTheGapExercise);
        String html;
        if (fillTheGapExercise.isDrag())
            html = "doFillTheGapExercise2";
        else
            html = "doFillTheGapExercise1";
        if (type.equals("result"))
            html = html.substring(0, html.length() - 1);
        return "exercise/" + html + exerciseController.type(type);
    }

    @PostMapping("exercise/fillTheBlank/{type}/{post}/{exercise}")
    public String doFill(@RequestParam(value = "solution") String[] solution, @PathVariable String
            type, @PathVariable String post, @PathVariable String exercise, RedirectAttributes
                                 redirectAttributes, Principal principal) {
        if (type.equals("do")) {
            exerciseController.solve(exercise, solution, principal.getName(), "Fill");
        }
        redirectAttributes.addAttribute("post", post);
        redirectAttributes.addAttribute("exercise", exercise);
        return "redirect:/exercise/mark/fillTheBlank/{post}/{exercise}";
    }

    @GetMapping("exercise/doCompiler")
    public String doCompiler(Model model) {
        return "exercise/doCompilerExercise";
    }

    @GetMapping("exercise/mark/quiz/{post}/{exercise}")
    public String showMarkQuiz(Model model, @PathVariable String exercise, Principal principal) {
        Submission submission = exerciseController.getSubmission(exercise, principal.getName());
        model.addAttribute("submission", submission);
        model.addAttribute("mark", exerciseController.getMark(submission.getMark()));
        model.addAttribute("pass", exerciseController.pass(submission.getPass()));
        return "exercise/showMark";
    }

    @PostMapping("exercise/mark/quiz/{post}/{exercise}")
    public String showMarkQuiz(String chosen, @PathVariable String post, @PathVariable String
            exercise, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", post);
        if (chosen.equals("Return")) {
            return "redirect:/tutorial/{id}";
        }
        redirectAttributes.addAttribute("post", post);
        redirectAttributes.addAttribute("exercise", exercise);
        if (chosen.equals("See Results"))
            redirectAttributes.addAttribute("type", "result");
        else
            redirectAttributes.addAttribute("type", "do");
        return "redirect:/exercise/quiz/{type}/{post}/{exercise}";
    }

    @GetMapping("exercise/mark/fillTheBlank/{post}/{exercise}")
    public String showMarkFillTheBlank(Model model, @PathVariable String exercise, Principal principal) {
        Submission submission = exerciseController.getSubmission(exercise, principal.getName());
        model.addAttribute("submission", submission);
        model.addAttribute("mark", exerciseController.getMark(submission.getMark()));
        model.addAttribute("pass", exerciseController.pass(submission.getPass()));
        return "exercise/showMark";
    }

    @PostMapping("exercise/mark/fillTheBlank/{post}/{exercise}")
    public String showMarkFillTheBlank(String chosen, @PathVariable String post, @PathVariable String
            exercise, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", post);
        if (chosen.equals("Return")) {
            return "redirect:/tutorial/{id}";
        }
        redirectAttributes.addAttribute("post", post);
        redirectAttributes.addAttribute("exercise", exercise);
        if (chosen.equals("See Results"))
            redirectAttributes.addAttribute("type", "result");
        else
            redirectAttributes.addAttribute("type", "do");
        return "redirect:/exercise/fillTheBlank/{type}/{post}/{exercise}";
    }
}
