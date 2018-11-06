package cat.tecnocampus.omega.persistance;

import cat.tecnocampus.omega.domain.exercises.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExerciseDAO {
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_EXERCISE = "INSERT INTO Exercises VALUES (?, ?, ?, ?,?,?,?);";
    private final String INSERT_QUESTION = "INSERT INTO Questions VALUES (?, ?, ?, ?);";
    private final String INSERT_SOLUTION = "INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES (?, ?, ?, ?,?);";

    private final String DELETE_EXERCISE = "";
    private final String DELETE_QUESTION = "";
    private final String DELETE_SOLUTION = "";

    private final String SELECT_EXERCISE_BY_POST = "SELECT * FROM Exercises WHERE post_id = ?";
    private final String SELECT_QUESTION_BY_EXERCISE = "SELECT * FROM Questions WHERE exercise_id = ?";
    private final String SELECT_SOLUTION_BY_QUESTION = "SELECT * FROM Solutions WHERE question_id = ?";

    private Exercise exerciseMapper(ResultSet resultSet) throws SQLException {
        Exercise exercise;
        if (resultSet.getString("son_type").equals("Test")) {
            exercise = new TestExercise(resultSet.getString("exercise_id"), resultSet.getString("description"), resultSet.getInt("difficulty"));
            exercise.setExperience_points(resultSet.getInt("experience_points"));
            exercise.setType(resultSet.getString("son_type"));
        } else {
            exercise = new FillTheGapExercise(resultSet.getString("exercise_id"), resultSet.getString("description"), resultSet.getInt("difficulty"));
            exercise.setExperience_points(resultSet.getInt("experience_points"));
            exercise.setType(resultSet.getString("son_type"));
        }
        return exercise;
    }

    private RowMapper<Question> questionMapper = (resultSet, i) -> {
        Question question = new Question(resultSet.getString("question_id"), resultSet.getString("texts"));
        return question;
    };

    private RowMapper<Solution> solutionMapper = (resultSet, i) -> {
        Solution solution = new Solution(resultSet.getString("solution_id"), resultSet.getString("texts"), resultSet.getBoolean("correct"));
        return solution;
    };

    private RowMapper<Exercise> mapperEager = (resultSet, i) -> {
        Exercise exercise = exerciseMapper(resultSet);

        List<Question> questions = findQuestionByExercise(exercise.getExercise_ID());
        for (Question question : questions) {
            List<Solution> solutions = findSolutionByQuestion(question.getQuestion_ID());
            question.addSolution(solutions);
        }
        exercise.addQuestion(questions);
        return exercise;
    };

    public ExerciseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
//        ClassToText.addInsert("Exercise",INSERT_EXERCISE);
//        ClassToText.addInsert("Question",INSERT_QUESTION);
//        ClassToText.addInsert("Solution",INSERT_SOLUTION);
    }

    public int insertDAOExercise(String exercise_ID,String description,boolean enable,int difficulty,int experience_points, String id, String type) {
        return jdbcTemplate.update(INSERT_EXERCISE, exercise_ID, description, enable, difficulty, experience_points, type, id);
    }

    public int insertDAOQuestion(String question_ID,String text,boolean enable, String id) {
        return jdbcTemplate.update(INSERT_QUESTION, question_ID, text, enable, id);
    }

    public int insertDAOSolution(String solution_ID,String text,boolean correct,boolean enable, String id) {
        return jdbcTemplate.update(INSERT_SOLUTION, solution_ID, text, correct, enable, id);
    }

    public List<Exercise> findExercisesByPost(String id) {
        return jdbcTemplate.query(SELECT_EXERCISE_BY_POST, new Object[]{id}, mapperEager);
    }

    public List<Question> findQuestionByExercise(String id) {
        return jdbcTemplate.query(SELECT_QUESTION_BY_EXERCISE, new Object[]{id}, questionMapper);
    }

    public List<Solution> findSolutionByQuestion(String id) {
        return jdbcTemplate.query(SELECT_SOLUTION_BY_QUESTION, new Object[]{id}, solutionMapper);
    }

}
