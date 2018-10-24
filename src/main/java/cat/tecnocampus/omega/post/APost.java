package cat.tecnocampus.omega.post;

import java.util.Date;

public abstract class APost {

    protected int postID;
    protected String description;
    protected Date creationDay;
    protected int likes = 0;
    protected boolean enable=false;
    //protected Category category;


    protected APost (String description){

    }

    public int getPostID() {
        return postID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
