package cat.tecnocampus.omega.domain.exercises;

public class FillTheGapExercise extends Exercise {
    public FillTheGapExercise(String exercise_ID, String description, int difficulty) {
        super(exercise_ID, description, difficulty);
        this.type = "Fill";
    }

    public FillTheGapExercise() {
        super();
        this.type = "Fill";
    }

    public float solve(String[] solutions) {
        float numSol = 0;
        float numCorr = 0;
        for (int counter = 0; counter < solutions.length; counter++) {
            numCorr += super.questions.get(counter).solve(solutions[counter]);
            numSol++;
        }
        return (numCorr / numSol) * 10;
    }
}
