package cat.tecnocampus.omega.controller;

import cat.tecnocampus.omega.domain.User;
import cat.tecnocampus.omega.persistence.UserDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("UserController")
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User createUser(String name, String password, String firstName, String lastName, String email, Date birthday,
                           int experiencePoints, int level, boolean enable) {
        User user = new User.UserBuilder()
                .username(name)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .birthday(birthday)
                .experiencePoints(experiencePoints)
                .level(level)
                .enable(enable)
                .build();
        insert(user);
        return user;
    }

    @Transactional
    public int insert(User user) {
        return userDAO.insert(user);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User getUser(String username) {
        return userDAO.findByUsername(username);
    }

    public int deleteUser(User user) {
        return userDAO.deleteUser(user);
    }
}
