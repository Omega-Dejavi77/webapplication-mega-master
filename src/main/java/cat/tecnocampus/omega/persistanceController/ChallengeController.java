package cat.tecnocampus.omega.persistanceController;

import cat.tecnocampus.omega.persistance.ChallengeDAO;
import cat.tecnocampus.omega.post.Challenge;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ChallengeController {

    private final ChallengeDAO challengeDAO;

    public ChallengeController(ChallengeDAO challengeDAO){
        this.challengeDAO=challengeDAO;
    }

    public Challenge insertChallenge(String description,String title){
        Challenge challenge= new Challenge(description,title);
        insert(challenge);
        return challenge;

    }

    @Transactional
    public int insert(Challenge challenge){
        return  challengeDAO.insertChallenge(challenge);
    }

    public List<Challenge> findAll(){

        return challengeDAO.findAll();
    }
}
