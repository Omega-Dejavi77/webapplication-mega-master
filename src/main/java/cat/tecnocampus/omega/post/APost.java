package cat.tecnocampus.omega.post;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.UUID;

public abstract class APost {

    protected String postID;
    @NotNull(message = "Description cannot be null")
    @Size(max = 10000, message = "Description must be less than 10000 characters long")
    protected String description;
    @NotNull(message = "Title cannot be null")
    @Size(min = 4, max = 150, message = "Title must be between 4 an 150 characters long")
    protected String title;
    protected Date creationDay;
    protected int likes;
    protected boolean enable;


    protected APost (String postID, String description, String title){
        this.postID=postID;
        this.description=description;
        this.title=title;
        creationDay=new Date();
        likes = 0;
        enable=true;
    }
    protected APost (String description,String title){
        postID=UUID.randomUUID().toString();
        this.description=description;
        this.title=title;
        creationDay=new Date();
        likes = 0;
        enable=true;
    }

    protected String getPostID() {
        return postID;
    }

    protected String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }
    protected String getTitle() {
        return title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    protected Date getCreationDay() {
        return creationDay;
    }

    protected int getLikes() {
        return likes;
    }

    protected void addLike() {
        likes++;
    }

    protected boolean isEnable() {
        return enable;
    }

    protected void setEnable(boolean enable) {
        this.enable = enable;
    }
}
