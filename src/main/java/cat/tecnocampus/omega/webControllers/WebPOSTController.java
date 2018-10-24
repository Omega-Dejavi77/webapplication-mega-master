package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.controllers.ClassroomController;
import cat.tecnocampus.domain.Classroom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("createTutorial")
public class WebPOSTController {
    private ClassroomController classroomController;

    public WebPOSTController(ClassroomController classroomController) {
        this.classroomController = classroomController;
    }

    @GetMapping
    public String createClassroom(Model model) {
        model.addAttribute(new Classroom());
        return "classroomForm";
    }

    @PostMapping
    public String createClassroom(@Valid Classroom classroom, Errors errors, Model model, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "classroomForm";
        }

        model.addAttribute("name", classroom.getName());

        classroomController.insert(classroom);

        redirectAttributes.addAttribute("name", classroom.getName());

        return "redirect:/classrooms/{name}";
    }

}
