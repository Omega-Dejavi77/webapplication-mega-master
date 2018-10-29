package cat.tecnocampus.omega.persistance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseDAO {
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_TEST_EXERCISE="";
    private final String INSERT_FILL_THE_GAP_EXERCISE="";
    private final String INSERT_QUESTION="";
    private final String INSERT_SOLUTION="";

    private final String DELETE_TEST_EXERCISE="UPDATE Text SET enable = 0 WHERE test_ID = ?";
    private final String DELETE_FILL_THE_GAP_EXERCISE="";
    private final String DELETE_QUESTION="";
    private final String DELETE_SOLUTION="";

    private final String SELECT_TEST_BY_TUTORIAL="";
    private final String SELECT_FILL_THE_GAP_BY_TUTORIAL="";
    private final String SELECT_QUESTION_BY_EXERCISE="";
    private final String SELECT_SOLUTION_BY_QUESTION="";


    //

}
