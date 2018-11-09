package cat.tecnocampus.omega.persistance;

import org.springframework.jdbc.core.JdbcTemplate;

public class ForumDAO {

    private JdbcTemplate jdbcTemplate;

    private final String INSERT_DISCUSSION = "INSERT INTO Posts (post_id, title, description, creationDay, likes, enable, son_type) VALUES (?, ?, ?, ?, ?,?,?)";
    private final String INSERT_COMMENT = "INSERT INTO Posts (post_id, title, description, creationDay, likes, enable, son_type) VALUES (?, ?, ?, ?, ?,?,?)";

}
