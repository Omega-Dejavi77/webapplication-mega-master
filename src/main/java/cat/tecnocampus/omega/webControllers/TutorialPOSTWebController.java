package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.post.Tutorial;
import cat.tecnocampus.omega.persistanceController.TutorialController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("createTutorial")
public class TutorialPOSTWebController {
    private TutorialController tutorialController;

    public TutorialPOSTWebController(TutorialController tutorialController) {
        this.tutorialController = tutorialController;
    }

    @GetMapping
    public String createTutorial(Model model) {
        model.addAttribute(new Tutorial());
        return "post/newTutorial";
    }

    @PostMapping
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
