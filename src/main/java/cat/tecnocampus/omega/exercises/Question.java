package cat.tecnocampus.omega.exercises;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question {
    private String question_ID;
    @NotNull
    @Size(max = 1024,message = "The text is too long")
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
    public void addSolution(List<Solution> solution){
        solutions.addAll(solution);
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
    public void validation(){
        if(this.text.length()>1024)
            throw new IllegalArgumentException("SOMETHING WENT WRONG WHEN CREATING A QUESTION:\n\t\tThe text is too long, should be less than 1024 characters \n Text:"+this.text);
    }
}
