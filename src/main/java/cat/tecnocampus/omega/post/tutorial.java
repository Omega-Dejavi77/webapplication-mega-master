package cat.tecnocampus.omega.post;

import java.util.Date;

public class tutorial extends APost {


    protected tutorial(String description) {
        super(description);
    }

    @Override
    public String getPostID() {
        return super.getPostID();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public Date getCreationDay() {
        return super.getCreationDay();
    }

    @Override
    public int getLikes() {
        return super.getLikes();
    }

    @Override
    public void addLike() {
        super.addLike();
    }

    @Override
    public boolean isEnable() {
        return super.isEnable();
    }

    @Override
    public void setEnable(boolean enable) {
        super.setEnable(enable);
    }
}
