package cat.tecnocampus.omega.persistanceController;

import cat.tecnocampus.omega.persistance.ChallengeDAO;
import cat.tecnocampus.omega.domain.post.Challenge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ChallengeController")
public class ChallengeController {

    private final ChallengeDAO challengeDAO;

    public ChallengeController(ChallengeDAO challengeDAO) {
        this.challengeDAO = challengeDAO;
    }

    public Challenge insertChallenge(String description, String title) {
        Challenge challenge = new Challenge(description, title);
        insert(challenge);
        return challenge;

    }

    @Transactional
    public int insert(Challenge challenge) {
        return challengeDAO.insertDAOChallenge(challenge);
    }

    public List<Challenge> findAll() {

        return challengeDAO.findAll();
    }
}
