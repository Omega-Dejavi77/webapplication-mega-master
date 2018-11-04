package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.persistanceController.ChallengeController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChallengeGETWebController {
    private ChallengeController challengeController;

    public ChallengeGETWebController(ChallengeController challengeController){
        this.challengeController=challengeController;
    }
    @GetMapping("challenges")
    public String listTutorials(Model model) {
        model.addAttribute("challengeList", challengeController.findAll());
        return "post/showChallenges";
    }
}
