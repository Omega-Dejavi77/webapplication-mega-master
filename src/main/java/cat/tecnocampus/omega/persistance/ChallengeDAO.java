package cat.tecnocampus.omega.persistance;

import cat.tecnocampus.omega.domain.exercises.Exercise;
import cat.tecnocampus.omega.domain.post.Challenge;
import cat.tecnocampus.omega.domain.post.Tutorial;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ChallengeDAO {

    private JdbcTemplate jdbcTemplate;
    private ExerciseDAO exerciseDAO;
    private final String FIND_ALL = "select * from Posts where son_type=?";;
    private final String INSERT = "insert into posts (post_ID, title, description, creationDay, likes, enable, son_TYPE) values(?, ?, ?, ?, ?,?,?)";

    public ChallengeDAO(JdbcTemplate jdbcTemplate,ExerciseDAO exerciseDAO) {

        this.jdbcTemplate = jdbcTemplate;
        this.exerciseDAO=exerciseDAO;
    }
    private Challenge challengeMapper(ResultSet resultSet) throws SQLException {
        Challenge challenge = new Challenge(resultSet.getString("post_id"), resultSet.getString("description"), resultSet.getString("title"));
        return challenge;
    }

    private RowMapper<Challenge> mapperEager = (resultSet, i) -> {
        Challenge challenge = challengeMapper(resultSet);
        List<Exercise> exercises = exerciseDAO.findExercisesByPost(challenge.getPostID());
        challenge.addExercises(exercises);
        return challenge;
    };

    public List<Challenge> findAll() {
        return jdbcTemplate.query(FIND_ALL,new Object[]{"Challenge"}, mapperEager);
    }

    public int insertDAOChallenge(Challenge challenge) {
        return jdbcTemplate.update(INSERT,challenge.getPostID(),challenge.getTitle(),challenge.getDescription(),challenge.getCreationDay(), challenge.getLikes(),challenge.isEnable(),"Challenge");
    }

}
