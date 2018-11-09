package cat.tecnocampus.omega.domain.post;

import java.util.ArrayList;
import java.util.List;

public class Discussion extends APost {


    private List<Comment> comments= new ArrayList<Comment>();

    public Discussion(String postID, String description, String title) {
        super(postID, description, title);
    }

    public Discussion(String body, String title) {
        super(body, title);
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

}
