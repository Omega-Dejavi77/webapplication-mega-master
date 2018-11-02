package cat.tecnocampus.omega.persistance;

import cat.tecnocampus.omega.post.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TutorialDAO {

    private JdbcTemplate jdbcTemplate;
    private final String FIND_ALL = "select * from Posts";
    private final String INSERT = "insert into Posts (post_ID, Name, description, creationDay, like, enable, sonType) values(?, ?, ?, ?, ?,?,'Tutorial')";


    public TutorialDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tutorial> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Tutorial.class));
    }

    public int insertTutorial(Tutorial tutorial) {
        return jdbcTemplate.update(INSERT,tutorial.getPostID(),tutorial.getTitle(),tutorial.getDescription(),tutorial.getCreationDay(),tutorial.getLikes(),tutorial.isEnable());
    }

}
