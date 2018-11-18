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
    private final String FIND_BY_ID = "select * from Posts where post_id = ? AND son_type = ?";
    private final String INSERT_TUTORIAL = "INSERT INTO Posts (post_id, title, description, creationDay, likes, enable, son_type, category) VALUES (?, ?, ?, ?, ?,?,?,?)";
    private final String FIND_BY_CATEGORY= "select * from Posts where category=? AND son_type=?";


    public TutorialDAO(JdbcTemplate jdbcTemplate, ExerciseDAO exerciseDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.exerciseDAO = exerciseDAO;
    }

    private Tutorial tutorialMapper(ResultSet resultSet) throws SQLException {
        Tutorial tutorial = new Tutorial(resultSet.getString("post_id"), resultSet.getString("description"), resultSet.getString("title"), resultSet.getString("category"));
        return tutorial;
    }

    public List<Tutorial> findByCategory(String category){
        return jdbcTemplate.query(FIND_BY_CATEGORY,new Object[]{category,"Tutorial"}, mapperEager);
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

    public Tutorial findById(String id) {
        return jdbcTemplate.queryForObject(FIND_BY_ID, new Object[]{id,"Tutorial"}, mapperEager);
    }

    public int insertDAOTutorial(Tutorial tutorial) {
        return jdbcTemplate.update(INSERT_TUTORIAL, tutorial.getPostID(),tutorial.getTitle(),tutorial.getDescription(),tutorial.getCreationDay(), tutorial.getLikes(),tutorial.isEnable(),"Tutorial");
    }

}
