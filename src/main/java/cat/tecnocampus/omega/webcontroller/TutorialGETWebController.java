package cat.tecnocampus.omega.webcontroller;

import cat.tecnocampus.omega.controller.TutorialController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TutorialGETWebController {

    private TutorialController tutorialController;

    public TutorialGETWebController(TutorialController tutorialController) {
        this.tutorialController = tutorialController;
    }

    @GetMapping("tutorials")
    public String listTutorials(Model model) {
        model.addAttribute("tutorialList", tutorialController.findAll());
        return "tutorials";
    }
}
