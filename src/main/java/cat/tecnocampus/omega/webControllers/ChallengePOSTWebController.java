package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.persistanceController.ChallengeController;
import cat.tecnocampus.omega.domain.post.Challenge;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("createChallenge")
public class ChallengePOSTWebController {

    private ChallengeController challengeController;

    public ChallengePOSTWebController (ChallengeController challengeController){

        this.challengeController=challengeController;
    }
    @GetMapping
    public String createChallenge(Model model){

        model.addAttribute(new Challenge());
        return "post/newChallenge";
    }

    @PostMapping
    public String createChellenge(@Valid Challenge challenge, Errors errors, Model model, RedirectAttributes redirectAttributes){

        if (errors.hasErrors()) {
            return "post/newChallenge";
        }

        model.addAttribute("title", challenge.getTitle());
        challengeController.insert(challenge);
        redirectAttributes.addAttribute("id",challenge.getPostID());
        return "redirect:/createExercise/{id}";
    }


}
