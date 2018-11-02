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

    private final String INSERT = "insert into users (nickname, password, email) values(?, ?, ?)";
    private final String FIND_ALL = "SELECT * FROM users";
    private final String FIND_BY_NICKNAME = "SELECT * FROM users WHERE nickname = ?";
    private final String DELETE_USER = "DELETE users WHERE nickname = ?";

    private final RowMapper<User> mapper = (resultSet, i) -> {
        return new User.UserBuilder()
                .nickName(resultSet.getString("nickname"))
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

    public User findByNickName(String nickname) {
        return jdbcTemplate.queryForObject(FIND_BY_NICKNAME, new Object[]{nickname}, mapper);
    }

    public int deleteUser(User user) {
        return jdbcTemplate.update(DELETE_USER, user.getNickname());
    }
}
