package cat.tecnocampus.omega.domain.exercises;

public class TestExercise extends Exercise {

    public TestExercise(String exercise_ID,String description, int difficulty) {
        super(exercise_ID,description, difficulty);
        this.type = "Test";
    }
    public TestExercise(){
        this.type = "Test";
    }
}
