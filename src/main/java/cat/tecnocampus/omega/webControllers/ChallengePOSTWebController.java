package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.persistanceController.ChallengeController;
import cat.tecnocampus.omega.post.Challenge;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("insertChallenge")
public class ChallengePOSTWebController {

    private ChallengeController challengeController;

    public ChallengePOSTWebController (ChallengeController challengeController){

        this.challengeController=challengeController;
    }
    @GetMapping
    public String createChallenge(Model model){

        model.addAttribute(new Challenge());
        return "insertChallenge";
    }

    @PostMapping
    public String createChellenge(@Valid Challenge challenge, Errors errors, Model model){

        if (errors.hasErrors()) {
            return "insertChallenges";
        }

        model.addAttribute("title", challenge.getTitle());
        challengeController.insert(challenge);
        return "redirect:/challenges";
    }


}
