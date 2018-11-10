package cat.tecnocampus.omega.persistanceController;

import cat.tecnocampus.omega.domain.User;
import cat.tecnocampus.omega.domain.exercises.*;
import cat.tecnocampus.omega.persistance.ExerciseDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ExercisesDAOController")
public class ExercisesDAOController {
    private final ExerciseDAO exerciseDAO;
    private final UserController userController;

    public ExercisesDAOController(ExerciseDAO exerciseDAO, UserController userController) {
        this.exerciseDAO = exerciseDAO;
        this.userController = userController;
    }

    @Transactional
    public int insertExercise(Exercise exercise, String id, String type) {
        return exerciseDAO.insertExercise(exercise, id, type);
    }

    @Transactional
    public int insertQuestion(Question question, String id) {
        return exerciseDAO.insertQuestion(question, id);
    }

    @Transactional
    public int insertSolution(Solution solution, String id) {
        return exerciseDAO.insertSolution(solution, id);
    }

    public List<Exercise> getExercises(String id) {
        return exerciseDAO.findExercisesByPost(id);
    }

    public Exercise getExercise(String id, String type) {
        return exerciseDAO.findExercisesByIDAndType(id, type);
    }

    public void solve(String id, String[] solutions, String username) {
        FillTheGapExercise fillTheGapExercise = (FillTheGapExercise) exerciseDAO.findExercisesByIDAndType(id, "Fill");
        float mark = fillTheGapExercise.solve(solutions);
        Submission submission=new Submission(mark, userController.getUser(username), fillTheGapExercise);
        //exerciseDAO.insertSubmission(submission);
    }
}
