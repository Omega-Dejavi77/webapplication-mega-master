package cat.tecnocampus.omega.persistance;

import cat.tecnocampus.omega.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;

    private final String INSERT_USER = "INSERT INTO Users (username, password, first_name, last_name, email, birthday, experience_points, level, enable) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String FIND_ALL = "SELECT * FROM Users";
    private final String FIND_BY_USERNAME = "SELECT * FROM Users WHERE username = ?";
    private final String DELETE_USER = "update users set enable=false where username=?";

    private final RowMapper<User> mapper = (resultSet, i) -> {
        return new User.UserBuilder()
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .firstName(resultSet.getString("first_name"))
                .lastName(resultSet.getString("last_name"))
                .email(resultSet.getString("email"))
                .birthday(resultSet.getDate("birthday"))
                .experiencePoints(resultSet.getInt("experience_points"))
                .level(resultSet.getInt("level"))
                .enable(resultSet.getBoolean("enable"))
                .build();
    };

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        //ClassToText.addInsert("User", INSERT_USER);
    }

    public int insertDAOUser(String username, String password, String firstName, String lastName, String email, Date birthday,int experience_points,int level,int enable ) {
        return jdbcTemplate.update(INSERT_USER, username, password, firstName, lastName, email, birthday, experience_points, level, 1);
    }

    public List<User> findAll() {
        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(User.class));
    }

    public User findByUsername(String username) {
        return jdbcTemplate.queryForObject(FIND_BY_USERNAME, new Object[]{username}, mapper);
    }

    public int deleteUser(User user) {
        return jdbcTemplate.update(DELETE_USER, user.getUsername());
    }
}
