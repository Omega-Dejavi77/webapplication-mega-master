package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.post.Tutorial;
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

    public TutorialWebController(TutorialController tutorialController) {
        this.tutorialController = tutorialController;
    }

    @GetMapping("tutorials")
    public String listTutorials(Model model) {
        model.addAttribute("tutorialList", tutorialController.findAll());
        return "post/showTutorials";
    }

    @GetMapping("tutorial/{id}")
    public String showTutorial(Model model, @PathVariable String id) {
        model.addAttribute("tutorial", tutorialController.findById(id));
        return "post/showTutorial";
    }
    @PostMapping("tutorial/{id}")
    public String showTutorial(@Valid Tutorial tutorial, Errors errors, Model model, RedirectAttributes redirectAttributes) {

        model.addAttribute("title", tutorial.getTitle());

        tutorialController.insert(tutorial);

        redirectAttributes.addAttribute("id",tutorial.getPostID());
        return "redirect:/createExercise/{id}";
    }
    @GetMapping("createTutorial")
    public String createTutorial(Model model) {
        model.addAttribute(new Tutorial());
        return "post/newTutorial";
    }

    @PostMapping("createTutorial")
    public String createTutorial(@Valid Tutorial tutorial, Errors errors, Model model, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "post/newTutorial";
        }

        model.addAttribute("title", tutorial.getTitle());

        tutorialController.insert(tutorial);

        redirectAttributes.addAttribute("id",tutorial.getPostID());
        return "redirect:/createExercise/{id}";
    }
}