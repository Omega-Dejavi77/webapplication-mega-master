package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.post.Discussion;
import cat.tecnocampus.omega.persistanceController.ForumController;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ForumWebController {

    private ForumController forumController;

    public ForumWebController (ForumController forumController){
        this.forumController=forumController;
    }

    @GetMapping("createDiscussion")
    public String createDiscussion (Model model){
        model.addAttribute(new Discussion());
        return "post/newDiscussion";
    }

    @PostMapping("createDiscussion")
    public String createTutorial(@Valid Discussion discussion, Errors errors, Model model, RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "post/newDiscussion";
        }

        model.addAttribute("title", discussion.getTitle());

        forumController.insertDiscussion(discussion);

        redirectAttributes.addAttribute("id",discussion.getPostID());
        return "redirect:/showForum/{id}";
    }

    @GetMapping("showForum")
    public String showForum(Model model){
        model.addAttribute("discussions",forumController.getDiscussions());
        return "showForum";
    }

    @GetMapping("showDiscussion/{id}")
    public String showDiscussion(Model model, @PathVariable String id){
        model.addAttribute("discussion",forumController.getDiscussion(id));
        return "";
    }

}
