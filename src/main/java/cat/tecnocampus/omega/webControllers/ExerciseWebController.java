package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.exercises.Exercise;
import cat.tecnocampus.omega.exercises.FillTheGapExercise;
import cat.tecnocampus.omega.exercises.TestExercise;
import cat.tecnocampus.omega.persistanceController.ExercisesDAOController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class ExerciseWebController {
        private final ExercisesDAOController exercisesDAO;

        public ExerciseWebController(ExercisesDAOController exercisesDAO) {
            this.exercisesDAO = exercisesDAO;
        }
    @GetMapping("createExercise")
    public String createExercise() {
        return "newExercise";
    }
    @PostMapping("createExercise")
    public String createExercise(Model model) {
        if(model)//Test
        return "redirect:/createTestExercise";
        else
            return "redirect:/createFillTheGapExercise";
    }
    @GetMapping("createTestExercise")
    public String createTestExercise(Model model) {
        model.addAttribute(new TestExercise() {});
        return "newTestExercise";
    }
    @PostMapping("createTestExercise")
    public String createTestExercise(@Valid Exercise exercise, RedirectAttributes redirectAttributes) {

        //exercisesDAO.insertExercise(exercise);

        redirectAttributes.addAttribute("id", exercise.getExercise_ID());

        return "redirect:/createQuestion";
    }
        @GetMapping("createFillTheGapExercise")
        public String createFillTheGapExercise(Model model) {
            model.addAttribute(new FillTheGapExercise() {});
            return "newFillTheGapExercise";
        }
        @PostMapping("createFillTheGapExercise")
        public String createFillTheGapExercise(@Valid Exercise exercise, RedirectAttributes redirectAttributes) {

            //exercisesDAO.insertExercise(exercise);

        redirectAttributes.addAttribute("id", exercise.getExercise_ID());

        return "redirect:/createQuestion";
        }
        @GetMapping("createQuestion")
        public String showAllClassrooms(Model model) {
            //model.addAttribute("classrooms", proxyClassroomDAO.findAllExercises());
            return "showAllClassrooms";
        }
    }
