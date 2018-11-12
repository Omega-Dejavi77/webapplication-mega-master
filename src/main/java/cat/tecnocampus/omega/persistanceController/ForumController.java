package cat.tecnocampus.omega.persistanceController;

import cat.tecnocampus.omega.persistance.ForumDAO;

public class ForumController {

    private final ForumDAO forumDAO;

    public ForumController(ForumDAO forumDAO){
        this.forumDAO=forumDAO;
    }


}
