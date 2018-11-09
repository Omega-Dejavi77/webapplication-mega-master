package cat.tecnocampus.omega.domain.post;

import cat.tecnocampus.omega.domain.*;
import java.util.Date;
import java.util.UUID;

public class Comment {

    private Discussion discussion;
    private User user;
    private String commentID;
    private String comment;
    private Date creationDay;
    private int likes;
    private boolean enable;
    private boolean bestComment;


    public Comment (String ID, String comment, User user, Discussion discussion){
        commentID=ID;
        this.comment=comment;
        this.user=user;
        this.discussion=discussion;
        creationDay=new Date();
        likes=0;
        enable=true;
        bestComment=false;
    }

    public Comment (String comment, User user, Discussion discussion){
        commentID=UUID.randomUUID().toString();
        this.comment=comment;
        this.user=user;
        this.discussion=discussion;
        creationDay=new Date();
        likes=0;
        enable=true;
        bestComment=false;
    }

    public void editComment (String newComment){
        comment=newComment;
    }

    public void addLike(){
        likes++;
    }

    void setBestComment(boolean stat){
        bestComment=stat;
        discussion.setHasBestComment(stat,this);
    }

    public Discussion getDiscussion() {
        return discussion;
    }

    public User getUser() {
        return user;
    }

    public String getCommentID() {
        return commentID;
    }

    public String getComment() {
        return comment;
    }

    public Date getCreationDay() {
        return creationDay;
    }

    public int getLikes() {
        return likes;
    }

    public boolean isEnable() {
        return enable;
    }

    public boolean isBestComment() {
        return bestComment;
    }
}
