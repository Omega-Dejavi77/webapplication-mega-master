package cat.tecnocampus.omega.webControllers;

import cat.tecnocampus.omega.domain.post.Discussion;
import cat.tecnocampus.omega.domain.post.Comment;
import cat.tecnocampus.omega.persistanceController.ForumController;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.github.rjeschke.txtmark.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

@Controller
public class ForumWebController {

    private ForumController forumController;

    public ForumWebController (ForumController forumController){
        this.forumController=forumController;
    }

    @GetMapping("forum/create/discussion")
    public String createDiscussion (Model model){
        model.addAttribute(new Discussion());
        return "post/newDiscussion";
    }

    @PostMapping("forum/create/discussion")
    public String createDiscussion(@Valid Discussion discussion,String description, Errors errors, Model model, RedirectAttributes redirectAttributes, Principal principal) {
        if (errors.hasErrors()) {
            return "post/newDiscussion";
        }
        model.addAttribute("title", discussion.getTitle());
        discussion.setDescription(Processor.process(description));
        forumController.addDiscussion(discussion,principal.getName());
        redirectAttributes.addAttribute("id",discussion.getPostID());
        return "redirect:/forum/discussion/{id}";
    }

    @GetMapping("forum/all")
    public String showForum(Model model){
        model.addAttribute("discussions",forumController.getDiscussions());
        return "post/showForum";
    }
    @PostMapping("forum/all")
    public String showForum(String chosen,RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("id",chosen);
        return "redirect:/forum/discussion/{id}";
    }
    @GetMapping("forum/discussion/{id}")
    public String showDiscussion(Model model, @PathVariable String id){
        model.addAttribute("discussion",forumController.getDiscussion(id));
        return "post/showDiscussion";
    }
    @PostMapping("forum/discussion/{id}")
    public String showDiscussion(@PathVariable String id, String answer,String reply, RedirectAttributes redirectAttributes, Principal principal){
        if(principal==null)
            return "redirect:/login";
        System.out.println(reply);
        if(reply==null)
            forumController.addComment(new Comment(Processor.process(answer)),principal.getName(),id);
        else
            forumController.addCommentReply(new Comment(Processor.process(answer)),principal.getName(),id,reply);
        redirectAttributes.addAttribute("id",id);
        return "redirect:/forum/discussion/{id}";
    }
}
