package cat.tecnocampus.omega.webcontroller;

import cat.tecnocampus.omega.persistence.UserDAO;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserWebController {

    private UserDAO userDAO;

    public UserWebController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/AllUsers")
    public String allUsers(Model model) {
        model.addAttribute("users", userDAO.findAll());
        return "allUsers";
    }

    @GetMapping("/createUser")
    public String getCreateUser(Model model) {
        model.addAttribute("createUser", new cat.tecnocampus.omega.domain.User());
        return "RegisterUser";
    }

    @PostMapping("/createUser")
    public String postCreateUser(@Valid cat.tecnocampus.omega.domain.User user, Errors errors, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors())
            return "RegisterUser";

        userDAO.insert(user);
        redirectAttributes.addAttribute("name", user.getNickname());
        return "RegisterUser";
    }

    @GetMapping("/signUser")
    public String signIn(Model model) {
        model.addAttribute("signUser", null);
        return "Main page";
    }

}
