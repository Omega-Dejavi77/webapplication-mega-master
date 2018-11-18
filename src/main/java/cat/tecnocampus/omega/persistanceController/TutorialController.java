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

    public Tutorial insertTutorial(String description, String title, String category){
        Tutorial tutorial = new Tutorial(description,title,category);
        insert(tutorial);
        return tutorial;
    }

    @Transactional
    public int insert(Tutorial tutorial){
        return  tutorialDAO.insertDAOTutorial(tutorial);
    }

    public List<Tutorial> findAll(){
        return tutorialDAO.findAll();
    }
    public Tutorial findById(String id){
        return tutorialDAO.findById(id);
    }
    public  List<Tutorial> findByCategory(String category){ return tutorialDAO.findByCategory(category);}

}
