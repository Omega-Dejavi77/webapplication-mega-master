package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.exercises.*;
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
import java.util.Map;


@Controller
public class ExerciseWebController {
    private final ExercisesDAOController exercisesDAO;

    public ExerciseWebController(ExercisesDAOController exercisesDAO) {
        this.exercisesDAO = exercisesDAO;
    }

    @GetMapping("createExercise/{id}")
    public String createExercise() {
        return "exercise/newExercise";
    }

    @PostMapping("createExercise/{id}")
    public String createExercise(String exercise, @PathVariable String id, RedirectAttributes redirectAttributes) {
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
        return "exercise/newTestExercise";
    }

    @PostMapping("createTestExercise/{id}")
    public String createTestExercise(@Valid TestExercise testExercise, Errors errors, @PathVariable String id,String testText,String end, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "exercise/newTestExercise";
        }
        exercisesDAO.insertExercise(testExercise, id, "Test");
        QuestionsCreator.testCreator(testText,exercisesDAO,testExercise.getExercise_ID());
        if(end.equals("Finish"))
        return "redirect:/posts";
        else {
            redirectAttributes.addAttribute("id",id);
            return "redirect:/createExercise/{id}";
        }
    }
    @GetMapping("createFillTheGapExercise/{id}")
    public String createFillTheGapExercise(Model model) {
        model.addAttribute("fillTheGapExercise", new FillTheGapExercise());
        return "exercise/newFillTheGapExercise";
    }

    @PostMapping("createFillTheGapExercise/{id}")
    public String createFillTheGapExercise(@Valid FillTheGapExercise fillTheGapExercise, Errors errors, @PathVariable String id,String fillText,String end, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            redirectAttributes.addAttribute("id", id);
            return "exercise/newFillTheGapExercise";
        }
        exercisesDAO.insertExercise(fillTheGapExercise,id,"Fill");
        QuestionsCreator.fillTheGapCreator(fillText,exercisesDAO,fillTheGapExercise.getExercise_ID());

        if(end.equals("Finish"))
            return "redirect:/posts";
        else {
            redirectAttributes.addAttribute("id",id);
            return "redirect:/createExercise/{id}";
        }
    }

    @GetMapping("doTest/{id}")
    public String doTest(Model model,@PathVariable String id) {
        model.addAttribute("solutions", new HashMap<String,String>());
        model.addAttribute("exercise", exercisesDAO.getExercise(id));
        return "exercise/doTestExercise";
    }
    @PostMapping("doTest/{id}")
    public String doTest(Map<String,String> solutions,Exercise exercise, @PathVariable String id, RedirectAttributes redirectAttributes) {
//        if (errors.hasErrors()) {
//            redirectAttributes.addAttribute("id", id);
//            return "exercise/newFillTheGapExercise";
//        }
        //exercisesDAO.insertExercise(fillTheGapExercise, id, "Fill");

        return "redirect:/Test";
    }
}
