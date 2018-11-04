package cat.tecnocampus.omega.persistanceController;

import cat.tecnocampus.omega.domain.post.Tutorial;
import cat.tecnocampus.omega.persistance.TutorialDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("TutorialController")
public class TutorialController {

    private final TutorialDAO tutorialDAO;

    public TutorialController(TutorialDAO tutorialDAO){
        this.tutorialDAO = tutorialDAO;
    }

    public Tutorial insertTutorial(String description, String title){
        Tutorial tutorial = new Tutorial(description,title);
        insert(tutorial);
        return tutorial;
    }

    @Transactional
    public int insert(Tutorial tutorial){
        return  tutorialDAO.insertTutorial(tutorial);
    }

    public List<Tutorial> findAll(){
        return tutorialDAO.findAll();
    }

}
