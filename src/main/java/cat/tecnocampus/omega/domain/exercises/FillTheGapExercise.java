package cat.tecnocampus.omega.domain.exercises;

public class FillTheGapExercise extends Exercise {
    public FillTheGapExercise(String exercise_ID, String description, int difficulty, boolean drag) {
        super(exercise_ID, description, difficulty);
        this.type = "Fill";
        this.drag = drag;
    }

    public FillTheGapExercise() {
        super();
        this.type = "Fill";
    }

    public void setQuestions(String questions) {
        String[] before = questions.split("#R");
        if (before.length > 1) {
            int counter = 0;
            String questionText = "";
            for (String s : before) {
                if (counter == 0) {
                    questionText = s;
                    counter++;
                    continue;
                }
                String[] after = s.split("#/R");
                Question question = new Question(questionText);
                question.validation();
                Solution solution = new Solution(after[0], true);
                solution.validation();
                addQuestion(question);
                question.addSolution(solution);
                if (after.length > 1) {
                    for (int i=1;i<after.length;i++) {
                        question = new Question(after[i]);
                        addQuestion(question);
                    }
                }
                counter++;
            }
        }
    }
}
