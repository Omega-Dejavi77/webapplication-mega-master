package cat.tecnocampus.omega.post;

import java.util.Date;

public class Tutorial extends APost {

    //Exercice<List> exercices;

    protected Tutorial(String description, String title) {
        super(description,title);
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
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
