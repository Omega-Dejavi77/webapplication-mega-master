package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.post.*;
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
@RequestMapping("insertTutorial")
public class TutorialPOSTWebController {
    private TutorialController tutorialController;

    public TutorialPOSTWebController(TutorialController tutorialController) {
        this.tutorialController = tutorialController;
    }

    @GetMapping
    public String createTutorial(Model model) {
        model.addAttribute(new Tutorial());
        return "insertTutorial";
    }

    @PostMapping
    public String createTutorial(@Valid Tutorial tutorial, Errors errors, Model model, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "insertTutorial";
        }

        model.addAttribute("title", tutorial.getTitle());

        tutorialController.insert(tutorial);

        //redirectAttributes.addAttribute("postID", tutorial.getPostID());
        return "redirect:/tutorials";

        //return "redirect:/createExercise/[postID]";
    }



}
