package cat.tecnocampus.omega.post;

import java.util.Date;

public class Tutorial extends APost {

    //Exercice<List> exercices;

    public Tutorial(String description, String title) {
        super(description,title);
    }

    public Tutorial() {
        super(null,null);
    }


    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    public String getPostID() {
        return postID;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
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

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public void setEnable(boolean enable) {
        this.enable=enable;
    }
}
