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
    protected void setQuestions(String questions) {
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
                if (after.length > 1)
                    questionText = after[1];
                Question question = new Question(questionText);
                question.validation();
                Solution solution = new Solution(after[0], true);
                solution.validation();
                addQuestion(question);
                question.addSolution(solution);
                counter++;
            }
        }
        for (Question q:getQuestions()) {
            System.out.println(q.getText());
            for (Solution s:q.getSolutions()) {
                System.out.println("\t"+s.getText());
            }
        }
    }
}
