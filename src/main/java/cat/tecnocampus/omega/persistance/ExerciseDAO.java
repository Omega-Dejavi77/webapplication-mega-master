package cat.tecnocampus.omega.persistance;
import cat.tecnocampus.omega.exercises.Exercise;
import cat.tecnocampus.omega.exercises.FillTheGapExercise;
import cat.tecnocampus.omega.exercises.TestExercise;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseDAO {
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_TEST_EXERCISE="INSERT INTO Exercises VALUES (?, ?, ?, ?,?,?,?);";
    private final String INSERT_FILL_THE_GAP_EXERCISE="INSERT INTO Exercises VALUES (?, ?, ?, ?,?,?,?);";
    private final String INSERT_QUESTION="INSERT INTO Questions VALUES (?, ?, ?, ?);";
    private final String INSERT_SOLUTION="INSERT INTO Solutions VALUES (?, ?, ?, ?,?,?);";

    private final String DELETE_EXERCISE="";
    private final String DELETE_QUESTION="";
    private final String DELETE_SOLUTION="";

    private final String SELECT_EXERCISE_BY_TUTORIAL="SELECT * FFROM Exercises WHERE type = ? AND post_ID = ?";
    private final String SELECT_QUESTION_BY_EXERCISE="";
    private final String SELECT_SOLUTION_BY_QUESTION="";


    private final RowMapper<Exercise> mapper = (resultSet, i) -> {
        if(resultSet.getString("type").equals("Test"))
            return new TestExercise();
        else if(resultSet.getString("type").equals("Fill"))
            return new FillTheGapExercise();
        return null;
    };


}
