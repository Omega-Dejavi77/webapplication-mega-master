package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.exercises.Exercise;
import cat.tecnocampus.omega.domain.post.Challenge;
import cat.tecnocampus.omega.domain.post.Tutorial;
import cat.tecnocampus.omega.persistanceController.ChallengeController;
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
public class ChallengeWebController {

    private ChallengeController challengeController;
    private ExerciseController exerciseController;

    public ChallengeWebController(ChallengeController challengeController, ExerciseController exerciseController) {
        this.challengeController = challengeController;
        this.exerciseController = exerciseController;
    }

    @GetMapping("challenges/{category}")
    public String listChallenges(@PathVariable String category, Model model) {
        model.addAttribute("challengesList", challengeController.findByCategory(category));
        return "post/showChallenges";
    }

    @GetMapping("challenges")
    public String listChallenges(Model model) {
        model.addAttribute("challengesList", challengeController.findAll());
        return "post/showChallenges";
    }

    @PostMapping("challenges")
    public String listChallenges(String chosen, RedirectAttributes redirectAttributes) {
        System.out.println(chosen);
        redirectAttributes.addAttribute("id", chosen);
        return "redirect:/challenge/{id}";
    }

    @GetMapping("challenge/{id}")
    public String showChallenge(Model model, @PathVariable String id) {
        model.addAttribute("challenge", challengeController.findById(id));
        return "post/showChallenge";
    }

    @PostMapping("challenge/{id}")
    public String showChallenge(String chosen, @PathVariable String id, RedirectAttributes redirectAttributes) {
        if (chosen.equals("Return"))
            return "redirect:/challenges";

        Exercise exercise = exerciseController.getExercise(chosen);
        redirectAttributes.addAttribute("post", id);
        redirectAttributes.addAttribute("exercise", exercise.getExercise_ID());
        redirectAttributes.addAttribute("type", "do");
        if (exercise.getType().equals("Test"))
            return "redirect:/exercise/doTest/{type}/{post}/{exercise}";
        if (exercise.isDrag())
            redirectAttributes.addAttribute("drag", "2");
        else redirectAttributes.addAttribute("drag", "1");
        return "redirect:/exercise/doFill/{type}/{post}/{exercise}/{drag}";
    }

    @GetMapping("createChallenge")
    public String createChallenge(Model model) {
        model.addAttribute(new Challenge());
        return "post/newTutorial";
    }

    @PostMapping("createChallenge")
    public String createChallenge(@Valid Challenge challenge, Errors errors, Model model, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "post/newChallenge";
        }

        model.addAttribute("title", challenge.getTitle());

        challengeController.addChallenge(challenge);

        redirectAttributes.addAttribute("id", challenge.getPostID());
        redirectAttributes.addAttribute("type", "Cha");
        return "redirect:/createExercise/{id}/{type}";
    }
}
