package cat.tecnocampus.omega.domain.exercises;

public class FillTheGapExercise extends Exercise {
    public FillTheGapExercise(String exercise_ID, String description, int difficulty,boolean drag) {
        super(exercise_ID, description, difficulty);
        this.type = "Fill";
        this.drag=drag;
    }

    public FillTheGapExercise() {
        super();
        this.type = "Fill";
    }
}
