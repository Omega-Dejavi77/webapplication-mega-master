package cat.tecnocampus.omega.persistanceController;

import cat.tecnocampus.omega.domain.User;
import cat.tecnocampus.omega.domain.exercises.*;
import cat.tecnocampus.omega.persistance.ExerciseDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service("ExercisesDAOController")
public class ExercisesDAOController {
    private final ExerciseDAO exerciseDAO;
    private final UserController userController;
    private Map<Float,String> marks;
    private String[] solutions;

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

    public Exercise getExercise(String id) {
        return exerciseDAO.findExercisesByID(id);
    }

    public Exercise getExerciseByType(String id, String type) {
        return exerciseDAO.findExercisesByIDAndType(id, type);
    }

    public Submission getSubmission(String id, String username) {
        List<Submission> list = exerciseDAO.findAllSubmissions(id, username);
        return list.get(list.size() - 1);
    }

    public void solve(String id, String[] solutions, String username, String type) {
        Exercise exercise = exerciseDAO.findExercisesByIDAndType(id, type);
        this.solutions=solutions;
        float mark = exercise.solve(solutions);
        Submission submission = new Submission(mark, userController.getUser(username), exercise);
        exerciseDAO.insertSubmission(submission);
    }
    public String getMark(float mark){
        if(marks ==null){
            initializeMarks();
        }
        for (float f:marks.keySet()) {
            if(mark<=f)
                return marks.get(f);
        }
        return "";
    }
    private void initializeMarks(){
        marks=new TreeMap<Float,String>();
        marks.put(10f,"A+");
        marks.put(9f,"A");
        marks.put(8f,"B+");
        marks.put(7.5f,"B");
        marks.put(7f,"B-");
        marks.put(6f,"C+");
        marks.put(5f,"C");
        marks.put(4f,"C-");
        marks.put(3f,"D+");
        marks.put(2f,"D");
        marks.put(1f,"D-");
        marks.put(0f,"F");
    }
}
