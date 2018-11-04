package cat.tecnocampus.omega.persistance;

import cat.tecnocampus.omega.exercises.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseDAO {
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_TEST_EXERCISE = "INSERT INTO Exercises VALUES (?, ?, ?, ?,?,?,?);";
    private final String INSERT_FILL_THE_GAP_EXERCISE = "INSERT INTO Exercises VALUES (?, ?, ?, ?,?,?,?);";
    private final String INSERT_QUESTION = "INSERT INTO Questions VALUES (?, ?, ?);";
    private final String INSERT_SOLUTION = "INSERT INTO Solutions VALUES (?, ?, ?, ?,?,?);";

    private final String DELETE_EXERCISE = "";
    private final String DELETE_QUESTION = "";
    private final String DELETE_SOLUTION = "";

    private final String SELECT_EXERCISE_BY_TUTORIAL = "SELECT * FFROM Exercises WHERE type = ? AND post_ID = ?";
    private final String SELECT_QUESTION_BY_EXERCISE = "";
    private final String SELECT_SOLUTION_BY_QUESTION = "";

    public int insertExercise(Exercise exercise, String id, String type) {
        String insert;
        if (type.equals("Test")) insert = INSERT_TEST_EXERCISE;
        else insert = INSERT_FILL_THE_GAP_EXERCISE;
        return jdbcTemplate.update(insert, exercise.getExercise_ID(), exercise.getDescription(), exercise.isEnable(), exercise.getDifficulty(), exercise.getExperience_points(), type, id);
    }
    public int insertQuestion(Question question, String id) {
        return jdbcTemplate.update(INSERT_QUESTION, question.getQuestion_ID(), question.getText() , id);
    }
    public int insertSolution(Solution solution, String id) {
        return jdbcTemplate.update(INSERT_SOLUTION, solution.getSolution_ID(),solution.getOrder(),solution.getText(),solution.getCorrect(),solution.isEnable() , id);
    }

}
