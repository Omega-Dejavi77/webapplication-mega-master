package cat.tecnocampus.omega.exercises;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Exercise {
    @NotNull
    protected String exercise_ID;

    @NotNull (message = "The Description can't be null")
    @Size(max = 1024,message = "Description too long")
    protected String description;
    @NotNull
    protected boolean enable;
    @NotNull(message = "Difficulty can't be null")
    protected int difficulty;
    protected int experience_points;

    private List<Question> questions;
    public Exercise(String exercise_ID,String description,int difficulty) {
        this.exercise_ID=exercise_ID;
        setUp(description,difficulty);
    }
    public Exercise(String description,int difficulty) {
        this.exercise_ID= UUID.randomUUID().toString();
        setUp(description,difficulty);
    }
    public Exercise(){

    }
    private void setUp(String description,int difficulty){
        this.description = description;
        this.difficulty=difficulty;
        enable=true;
        questions= new ArrayList<Question>();
    }

    public String getExercise_ID() {
        return exercise_ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getExperience_points() {
        return experience_points;
    }

    public void setExperience_points(int experience_points) {
        this.experience_points = experience_points;
    }
    public List<Question> getQuestions() {
        return questions;
    }
    public void addQuestion(Question question){
        questions.add(question);
    }
    public boolean solve() {
        List<Question> wrong=new ArrayList<Question>();
        for (Question question:questions) {
            if(!question.solve())
                wrong.add(question);
        }
        if(wrong.isEmpty())
            return true;
        return false;
    }
}
