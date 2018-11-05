package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.persistanceController.UserController;
import cat.tecnocampus.omega.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserWebController {

    private UserController userController;

    public UserWebController(UserController userController) {
        this.userController = userController;
    }

    @GetMapping("allUsers")
    public String allUsers(Model model) {
        model.addAttribute("users", userController.findAll());
        return "InitialPage";
    }

    @GetMapping("createUser")
    public String getCreateUser(Model model) {
        model.addAttribute("createUser", new User());
        return "RegisterUser";
    }

    @PostMapping("createUser")
    public String postCreateUser(@Valid User user, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("error", errors);
            System.out.println(errors.getFieldError());
            return "Errors";
        }

        model.addAttribute("username", user.getUsername());
        userController.insert(user);
        redirectAttributes.addAttribute("username", user.getUsername());
        return "redirect:/users/{username}";
    }



    @GetMapping("users/{username}")
    public String showUser(@PathVariable String username, Model model, Principal principal) {
        /*if(principal.getName().equals("De la Serna")) {*/
            model.addAttribute("user", userController.getUser(username));
            return "showUser";
        //}

        //return null;
    }

}
