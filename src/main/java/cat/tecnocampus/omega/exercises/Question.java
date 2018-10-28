package cat.tecnocampus.omega.exercises;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Question {
    @NotNull
    String question_ID;
    @NotNull
    @Size(max = 1024,message = "The text is too long")
    String text;
    List<Solution> solutions;

    public Question(String question_ID,String text) {
        this.question_ID = question_ID;
        this.text = text;
        solutions=new ArrayList<Solution>();
    }

    public boolean solve(){
        List<Solution> wrong=new ArrayList<Solution>();
        for (Solution solution:solutions) {
            if(!solution.solve())
                wrong.add(solution);
        }
        if(wrong.isEmpty())
            return true;
        return false;
    }
}
