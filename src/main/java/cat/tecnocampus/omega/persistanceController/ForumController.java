package cat.tecnocampus.omega.persistanceController;

import cat.tecnocampus.omega.persistance.ForumDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cat.tecnocampus.omega.domain.post.Discussion;
import cat.tecnocampus.omega.domain.post.Comment;
import java.util.List;

@Service("ForumController")
public class ForumController {

    private final ForumDAO forumDAO;

    public ForumController(ForumDAO forumDAO){
        this.forumDAO=forumDAO;
    }

    @Transactional
    public int insertDiscussion(Discussion discussion){
        return forumDAO.insertDAODiscussion(discussion);
    }

    @Transactional
    public int insertComment(Comment comment){
        return forumDAO.insertDAOComment(comment);
    }

    public List<Comment> getComment (String id){
        return forumDAO.findCommentByDiscussion(id);
    }

    public Discussion getDiscussion (String id){
        return forumDAO.getDiscussion(id);
    }

    public List<Discussion> getDiscussions (){
        return forumDAO.getAllDiscussions();
    }
}
