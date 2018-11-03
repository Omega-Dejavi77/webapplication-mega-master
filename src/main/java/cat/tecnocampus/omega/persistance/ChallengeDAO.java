package cat.tecnocampus.omega.persistance;

import cat.tecnocampus.omega.post.Challenge;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ChallengeDAO {

    private JdbcTemplate jdbcTemplate;
    private final String FIND_ALL = "select * from posts";
    private final String INSERT = "insert into posts (post_ID, title, description, creationDay, likes, enable, son_TYPE) values(?, ?, ?, ?, ?,?,'Challenge')";

    public ChallengeDAO(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Challenge> findAll() {
        return jdbcTemplate.query(FIND_ALL,new BeanPropertyRowMapper<>(Challenge.class));
    }

    public int insertChallenge(Challenge challenge) {
        return jdbcTemplate.update(INSERT,challenge.getPostID(),challenge.getTitle(),challenge.getDescription(),challenge.getCreationDay(), challenge.getLikes(),challenge.isEnable());
    }

}
