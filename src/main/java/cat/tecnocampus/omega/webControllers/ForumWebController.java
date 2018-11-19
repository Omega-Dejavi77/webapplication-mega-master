package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.post.Discussion;
import cat.tecnocampus.omega.domain.post.Comment;
import cat.tecnocampus.omega.persistanceController.ForumController;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

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
    public String createDiscussion(@Valid Discussion discussion, Errors errors, Model model, RedirectAttributes redirectAttributes, Principal principal) {

        if (errors.hasErrors()) {
            return "post/newDiscussion";
        }
        model.addAttribute("title", discussion.getTitle());
        forumController.addDiscussion(discussion,principal.getName());
        redirectAttributes.addAttribute("id",discussion.getPostID());
        return "redirect:/showDiscussion/{id}";
    }
    @GetMapping("forum")
    public String showForum(Model model){
        model.addAttribute("discussions",forumController.getDiscussions());
        return "post/showForum";
    }
    @PostMapping("forum")
    public String showForum(String chosen,RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("id",chosen);
        return "redirect:/showDiscussion/{id}";
    }
    @GetMapping("discussion/{id}")
    public String showDiscussion(Model model, @PathVariable String id){
        model.addAttribute("discussion",forumController.getDiscussion(id));
        return "post/showDiscussion";
    }
    @PostMapping("discussion/{id}")
    public String createComment(@PathVariable String id,String comment, RedirectAttributes redirectAttributes, Principal principal){
        System.out.println(comment);
        forumController.addComment(new Comment(comment),principal.getName(),id);
        redirectAttributes.addAttribute("id",id);
        return "redirect:/showDiscussion/{id}";
    }
}
