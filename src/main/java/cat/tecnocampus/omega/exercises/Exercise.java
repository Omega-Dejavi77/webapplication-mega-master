package cat.tecnocampus.omega.exercises;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public abstract class Exercise {
    @NotNull
    protected String exercise_ID;

    @NotNull
    @Size(max = 1024,message = "Description too long")
    protected String description;
    @NotNull
    protected boolean enable;
    @NotNull
    protected int difficulty;
    protected int experience_points;
    public Exercise(String description,int difficulty) {
        this.description = description;
        this.difficulty=difficulty;
        //ID
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
}
