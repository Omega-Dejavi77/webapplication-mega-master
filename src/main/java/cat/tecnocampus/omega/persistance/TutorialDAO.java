package cat.tecnocampus.omega.persistance;

import cat.tecnocampus.omega.domain.exercises.Exercise;
import cat.tecnocampus.omega.domain.post.Tutorial;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class TutorialDAO {

    private JdbcTemplate jdbcTemplate;
    private ExerciseDAO exerciseDAO;

    private final String FIND_ALL = "select * from Posts where son_type=?";
    private final String INSERT_TUTORIAL = "INSERT INTO Posts (post_id, title, description, creationDay, likes, enable, son_type) VALUES (?, ?, ?, ?, ?,?,?)";


    public TutorialDAO(JdbcTemplate jdbcTemplate, ExerciseDAO exerciseDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.exerciseDAO = exerciseDAO;
        //ClassToText.addInsert("Tutorial", INSERT_TUTORIAL);
    }

    private Tutorial tutorialMapper(ResultSet resultSet) throws SQLException {
        Tutorial tutorial = new Tutorial(resultSet.getString("post_id"), resultSet.getString("description"), resultSet.getString("title"));
        return tutorial;
    }

    private RowMapper<Tutorial> mapperEager = (resultSet, i) -> {
        Tutorial tutorial = tutorialMapper(resultSet);
        List<Exercise> exercises = exerciseDAO.findExercisesByPost(tutorial.getPostID());
        tutorial.addExercises(exercises);
        return tutorial;
    };

    public List<Tutorial> findAll() {
        return jdbcTemplate.query(FIND_ALL, new Object[]{"Tutorial"}, mapperEager);
    }

    public int insertDAOTutorial(String postID, String title, String description, Date creationDay, int likes, boolean enable, String type) {
        return jdbcTemplate.update(INSERT_TUTORIAL, postID, title, description, creationDay, likes, enable, type);
    }

}
