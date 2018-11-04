package cat.tecnocampus.omega.exercises;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question {
    private String question_ID;
    @NotNull
    @Size(min=5,max = 1024,message = "The text is too long")
    private String text;

    public Question() {
    }

    private boolean enable;
    List<Solution> solutions;

    public Question(String question_ID,String text) {
        this.question_ID = question_ID;
        this.text = text;
        solutions=new ArrayList<Solution>();
    }
    public Question(String text) {
        this.question_ID= UUID.randomUUID().toString();
        this.text = text;
        solutions=new ArrayList<Solution>();
    }

    public String getQuestion_ID() {
        return question_ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }
    public void addSolution(Solution solution){
        if(solution.getCorrect())
        for (Solution solution1:solutions) {
            if(solution1.getCorrect())
                throw new IllegalArgumentException("ERROR: There are a Solution that is also correct, there's only can be one true.");
        }
        solutions.add(solution);
    }

    public boolean solve(){
        List<Solution> wrong=new ArrayList<Solution>();
        for (Solution solution:solutions) {
            if(!solution.getCorrect())
                wrong.add(solution);
        }
        if(wrong.isEmpty())
            return true;
        return false;
    }
}
