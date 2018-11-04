package cat.tecnocampus.omega.exercises;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class Solution {
    @NotNull
    private String solution_ID;
    @NotNull
    @Size(max = 1024,message = "The text is too long")
    private String text;
    private int order;
    @NotNull
    private boolean enable;

    public Solution() {
    }

    @NotNull
    private boolean correct;

    public Solution(@NotNull String solution_ID, @NotNull @Size(max = 1024, message = "The text is too long") String text, int order, @NotNull boolean correct) {
        this.solution_ID = solution_ID;
        this.text = text;
        this.order = order;
        this.correct = correct;
        this.enable = true;
    }
    public Solution(@NotNull String solution_ID, @NotNull @Size(max = 1024, message = "The text is too long") String text, @NotNull boolean correct) {
        this.solution_ID = solution_ID;
        this.text = text;
        this.correct = correct;
        this.enable = true;
    }

    public Solution(String text, int order, boolean correct) {
        this.solution_ID= UUID.randomUUID().toString();
        this.text = text;
        this.order = order;
        this.correct = correct;
        this.enable = true;
    }

    public Solution(String text, boolean correct) {
        this.solution_ID= UUID.randomUUID().toString();
        this.text = text;
        this.correct = correct;
        this.enable = true;
    }

    public String getSolution_ID() {
        return solution_ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    

    public boolean getCorrect(){
        return correct;
    }
}
