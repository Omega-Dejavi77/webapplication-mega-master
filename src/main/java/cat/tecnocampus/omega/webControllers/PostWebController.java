package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.persistanceController.ChallengeController;
import cat.tecnocampus.omega.persistanceController.TutorialController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostWebController {
    private ChallengeController challengeController;
    private TutorialController tutorialController;

    public PostWebController(ChallengeController challengeController, TutorialController tutorialController) {
        this.challengeController = challengeController;
        this.tutorialController = tutorialController;
    }

    @GetMapping("posts")
    public String listTutorials(Model model) {
        model.addAttribute("tutorialList", tutorialController.findAll());
        model.addAttribute("challengeList", challengeController.findAll());
        return "post/showPosts";
    }
}
