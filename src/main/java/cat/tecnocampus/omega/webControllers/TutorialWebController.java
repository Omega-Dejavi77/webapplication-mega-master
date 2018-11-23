package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.exercises.Exercise;
import cat.tecnocampus.omega.domain.post.Tutorial;
import cat.tecnocampus.omega.persistanceController.ExerciseController;
import cat.tecnocampus.omega.persistanceController.TutorialController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TutorialWebController {

    private TutorialController tutorialController;
    private ExerciseController exerciseController;

    public TutorialWebController(TutorialController tutorialController, ExerciseController exerciseController) {
        this.tutorialController = tutorialController;
        this.exerciseController = exerciseController;
    }

    @GetMapping("tutorial/create")
    public String createTutorial(Model model) {
        model.addAttribute(new Tutorial());
        return "post/newTutorial";
    }

    @PostMapping("tutorial/create")
    public String createTutorial(@Valid Tutorial tutorial, Errors errors, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "post/newTutorial";
        }
        tutorialController.addTutorial(tutorial);

        redirectAttributes.addAttribute("id", tutorial.getPostID());
        return "redirect:/exercise/create/{id}";
    }

    @GetMapping("tutorial/all/{category}")
    public String listTutorials(@PathVariable String category, Model model) {
        model.addAttribute("tutorialList", tutorialController.findByCategory(category));
        return "post/showTutorials";
    }

    @GetMapping("tutorial/all")
    public String listTutorials(Model model) {
        model.addAttribute("tutorialList", tutorialController.findAll());
        return "post/showTutorials";
    }

    @PostMapping("tutorial/all")
    public String listTutorials(String chosen, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", chosen);
        return "redirect:/tutorial/{id}";
    }

    @GetMapping("tutorial/{id}")
    public String showTutorial(Model model, @PathVariable String id) {
        model.addAttribute("tutorial", tutorialController.findById(id));
        return "post/showTutorial";
    }

    @PostMapping("tutorial/{id}")
    public String showTutorial(String chosen, @PathVariable String id, RedirectAttributes redirectAttributes) {
        if (chosen.equals("Return"))
            return "redirect:/tutorials";

        Exercise exercise = exerciseController.getExercise(chosen);
        redirectAttributes.addAttribute("post", id);
        redirectAttributes.addAttribute("exercise", exercise.getExercise_ID());
        redirectAttributes.addAttribute("type", "do");
        if (exercise.getType().equals("Test"))
            return "redirect:/exercise/quiz/{type}/{post}/{exercise}";
        return "redirect:/exercise/fillTheBlank/{type}/{post}/{exercise}";
    }
}
