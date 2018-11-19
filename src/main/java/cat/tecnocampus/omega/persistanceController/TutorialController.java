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

    @Transactional
    public int addTutorial(Tutorial tutorial){
        return  tutorialDAO.insertTutorial(tutorial,"Java");
    }

    public List<Tutorial> findAll(){
        return tutorialDAO.findAll();
    }
    public Tutorial findById(String id){
        return tutorialDAO.findById(id);
    }
    public  List<Tutorial> findByCategory(String category){ return tutorialDAO.findByCategory(category);}

}
