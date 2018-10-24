package cat.tecnocampus.omega.post;

import java.util.Date;
import java.util.UUID;

public abstract class APost {

    protected String postID;
    protected String description;
    protected String title;
    protected Date creationDay;
    protected int likes;
    protected boolean enable;
    //protected Category category;


    protected APost (String description,String title){
        postID=UUID.randomUUID().toString();
        this.description=description;
        this.title=title;
        creationDay=new Date();
        likes = 0;
        enable=false;
    }

    public String getPostID() {
        return postID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDay() {
        return creationDay;
    }

    public int getLikes() {
        return likes;
    }

    public void addLike() {
        likes++;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
