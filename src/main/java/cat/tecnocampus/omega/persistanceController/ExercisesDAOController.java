package cat.tecnocampus.omega.persistanceController;

import cat.tecnocampus.omega.exercises.Exercise;
import cat.tecnocampus.omega.exercises.Question;
import cat.tecnocampus.omega.exercises.Solution;
import cat.tecnocampus.omega.persistance.ExerciseDAO;
import org.springframework.stereotype.Service;

@Service("ExercisesDAOController")
public class ExercisesDAOController {
    private final ExerciseDAO exerciseDAO;

    public ExercisesDAOController(ExerciseDAO exerciseDAO) {
        this.exerciseDAO = exerciseDAO;
    }

    public int insertExercise(Exercise exercise,String id,String type){
        return exerciseDAO.insertExercise(exercise,id,type);
    }

    public int insertQuestion(Question question, String id) {
        return exerciseDAO.insertQuestion(question,id);
    }
    public int insertSolution(Solution solution, String id) {
        return exerciseDAO.insertSolution(solution,id);
    }
}
