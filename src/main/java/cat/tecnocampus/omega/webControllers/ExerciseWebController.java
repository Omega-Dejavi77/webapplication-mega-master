package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.exercises.*;
import cat.tecnocampus.omega.persistanceController.ExercisesDAOController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class ExerciseWebController {
    private final ExercisesDAOController exercisesDAO;

    public ExerciseWebController(ExercisesDAOController exercisesDAO) {
        this.exercisesDAO = exercisesDAO;
    }

    @GetMapping("createExercise/{id}")
    public String createExercise() {
        return "newExercise";
    }

    @PostMapping("createExercise/{id}")
    public String createExercise(String exercise, @PathVariable int id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("id", id);
        if (exercise.equals("Test"))
            return "redirect:/createTestExercise/{id}";
        else
            return "redirect:/createFillTheGapExercise/{id}";
    }

    @GetMapping("createTestExercise/{id}")
    public String createTestExercise(Model model) {
        model.addAttribute(new TestExercise());
        //model.addAttribute("testQuestions", new HashMap<Question,ArrayList<Solution>>());
        model.addAttribute("testQuestion", new Question());
        model.addAttribute("testSolution", new Solution());
        return "newTestExercise";
    }

    @PostMapping("createTestExercise/{id}")
    public String createTestExercise(@Valid TestExercise testExercise, Errors errors,@PathVariable int id, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "newTestExercise";
        }
        System.out.println(testExercise.getExercise_ID());
        System.out.println(testExercise.getDescription());
        System.out.println(testExercise.getDifficulty());
        exercisesDAO.insertExercise(testExercise,id,"Test");
        redirectAttributes.addAttribute("id",testExercise.getExercise_ID());
        return "redirect:/finish";
    }

    @GetMapping("createFillTheGapExercise/{id}")
    public String createFillTheGapExercise(Model model) {
        model.addAttribute("fillTheGapExercise", new FillTheGapExercise());
        return "newFillTheGapExercise";
    }

    @PostMapping("createFillTheGapExercise/{id}")
    public String createFillTheGapExercise(@Valid FillTheGapExercise fillTheGapExercise, Errors errors, @PathVariable int id, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "newFillTheGapExercise";
        }
        System.out.println(fillTheGapExercise.getExercise_ID());
        System.out.println(fillTheGapExercise.getDescription());
        System.out.println(fillTheGapExercise.getDifficulty());
        exercisesDAO.insertExercise(fillTheGapExercise,id,"Fill");
        redirectAttributes.addAttribute("id",fillTheGapExercise.getExercise_ID());
        return "redirect:/finish";
    }
}
