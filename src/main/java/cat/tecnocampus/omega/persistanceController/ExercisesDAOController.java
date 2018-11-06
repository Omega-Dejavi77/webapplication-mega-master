package cat.tecnocampus.omega.persistanceController;

import cat.tecnocampus.omega.domain.exercises.Exercise;
import cat.tecnocampus.omega.domain.exercises.Question;
import cat.tecnocampus.omega.domain.exercises.Solution;
import cat.tecnocampus.omega.persistance.ExerciseDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ExercisesDAOController")
public class ExercisesDAOController {
    private final ExerciseDAO exerciseDAO;

    public ExercisesDAOController(ExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }

    @Transactional
    public int insertExercise(Exercise exercise, String id, String type) {
        return exerciseDAO.insertDAOExercise(exercise.getExercise_ID(), exercise.getDescription(), exercise.isEnable(), exercise.getDifficulty(), exercise.getExperience_points(), id, type);
    }

    @Transactional
    public int insertQuestion(Question question, String id) {
        return exerciseDAO.insertDAOQuestion(question.getQuestion_ID(), question.getText(), question.isEnable(), id);
    }

    @Transactional
    public int insertSolution(Solution solution, String id) {
        return exerciseDAO.insertDAOSolution(solution.getSolution_ID(), solution.getText(), solution.getCorrect(), solution.isEnable(), id);
    }

    public List<Exercise> getExercise(String id) {
        return exerciseDAO.findExercisesByPost(id);
    }

}
