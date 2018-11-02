package cat.tecnocampus.omega.controllers;

import cat.tecnocampus.omega.domain.User;
import cat.tecnocampus.omega.persistence.UserDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UserController")
public class UserController {

    private final UserDAO userDAO;

    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public User createUser(String name, String password, String email) {
        User user = new User.UserBuilder()
                .nickName(name)
                .password(password)
                .email(email)
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

    public User getUser(String nickname) {
        return userDAO.findByNickName(nickname);
    }

    public int deleteUser(User user) {
        return userDAO.deleteUser(user);
    }
}
