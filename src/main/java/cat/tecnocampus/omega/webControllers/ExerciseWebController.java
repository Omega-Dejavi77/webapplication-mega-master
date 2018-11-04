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
    public String createTestExercise(@Valid TestExercise testExercise, Errors errors, @PathVariable String id,String testText, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "newTestExercise";
        }
        ExerciseCreator.testCreator(testText,exercisesDAO,id);
        //exercisesDAO.insertExercise(testExercise, id, "Test");
        //redirectAttributes.addAttribute("id", testExercise.getExercise_ID());
        return "redirect:/finish";
        //return "redirect:/createTestQuestion/{id}";
    }
/*
    @GetMapping("createTestQuestion/{id}")
    public String createTestQuestion(Model model) {
        model.addAttribute("testQuestion", new Question());
        return "newTestQuestion";
    }

    @PostMapping("createTestQuestion/{id}")
    public String createTestQuestion(@Valid Question testQuestion, Errors errors, @PathVariable String id, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "newTestQuestion";
        }
        exercisesDAO.insertQuestion(testQuestion, id);
        redirectAttributes.addAttribute("id", id);
        redirectAttributes.addAttribute("id2", testQuestion.getQuestion_ID());
        return "redirect:/createTestSolution/{id}/{id2}";
    }

    @GetMapping("createTestSolution/{id}/{id2}")
    public String createTestSolution(Model model) {
        model.addAttribute("testSolution", new Solution());
        return "newTestSolution";
    }

    @PostMapping("createTestSolution/{id}/{id2}")
    public String createTestSolution(@Valid Solution testSolution, Errors errors, String finish, @PathVariable String id, @PathVariable String id2, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            redirectAttributes.addAttribute("id2", id2);
            return "newTestSolution";
        }
        exercisesDAO.insertSolution(testSolution, id);
        if (finish.equals("Add More Solutions")) {
            redirectAttributes.addAttribute("id", id);
            redirectAttributes.addAttribute("id2", id2);
            return "redirect:/createTestSolution/{id}/{id2}";
        } else if (finish.equals("Add More Questions")) {
            redirectAttributes.addAttribute("id", id);
            return "redirect:/createTestQuestion/{id}";
        } else return "redirect:/finish";
    }*/
    @GetMapping("createFillTheGapExercise/{id}")
    public String createFillTheGapExercise(Model model) {
        model.addAttribute("fillTheGapExercise", new FillTheGapExercise());
        return "newFillTheGapExercise";
    }

    @PostMapping("createFillTheGapExercise/{id}")
    public String createFillTheGapExercise(@Valid FillTheGapExercise fillTheGapExercise, Errors errors, @PathVariable String id,String fillText, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "newFillTheGapExercise";
        }
        ExerciseCreator.fillTheGapCreator(fillText,exercisesDAO,id);

        //exercisesDAO.insertExercise(fillTheGapExercise,id,"Fill");
        redirectAttributes.addAttribute("id",fillTheGapExercise.getExercise_ID());
        return "redirect:/finish";
    }
}
