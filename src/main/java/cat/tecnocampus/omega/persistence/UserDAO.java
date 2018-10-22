package cat.tecnocampus.omega.persistence;

import cat.tecnocampus.omega.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;

    private final String INSERT = "INSERT INTO Users(nickname, password, email) VALUES(?, ?, ?, ?)";
    private final String FIND_ALL = "SELECT * FROM Users";

    private final RowMapper<User> mapper = (resultSet, i) -> {
        return new User.UserBuilder()
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .email(resultSet.getString("email"))
                .build();
    };

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(User user) {
        return jdbcTemplate.update(INSERT, user.getNickname(), user.getPassword(), user.getEmail());
    }

    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(User.class));
    }
}
