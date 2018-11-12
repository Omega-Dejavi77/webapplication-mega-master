package cat.tecnocampus.omega.domain.post;

import java.util.ArrayList;
import java.util.List;
import cat.tecnocampus.omega.domain.*;

public class Discussion extends APost {

    private boolean hasBestComment;
    private User user;

    private List<Comment> comments= new ArrayList<Comment>();

    public Discussion(String postID, String description, String title,User user) {
        super(postID, description, title);
        hasBestComment=false;
        this.user=user;
    }

    public Discussion(String body, String title,User user) {
        super(body, title);
        hasBestComment=false;
        this.user=user;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    void setHasBestComment(boolean stat,Comment comment){
        hasBestComment=stat;
    }

    public User getUser(){
        return user;
    }
    public boolean hasBest(){
        return hasBestComment;
    }
}
