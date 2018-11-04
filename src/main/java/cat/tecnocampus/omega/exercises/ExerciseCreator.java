package cat.tecnocampus.omega.exercises;

import cat.tecnocampus.omega.persistanceController.ExercisesDAOController;

public class ExerciseCreator {

    public static void fillTheGapCreator(String text, ExercisesDAOController exercisesDAOController, String exerciseID) {
        String[] beforeHashtag = text.split("#R");
        if (beforeHashtag.length > 2) {
            int counter = 0;
            String questionText = "";
            for (String s : beforeHashtag) {
                if (counter == 0) {
                    questionText = s;
                    counter++;
                    continue;
                }
                String[] afterHastag = s.split("#/R");
                Question question = new Question(questionText);
                exercisesDAOController.insertQuestion(question, exerciseID);
                exercisesDAOController.insertSolution(new Solution(afterHastag[0], true), question.getQuestion_ID());
                questionText = afterHastag[1];
                counter++;
            }
        }
    }

    public static void testCreator(String text, ExercisesDAOController exercisesDAOController, String exerciseID) {
        text.replace("/n", "");
        String[] questions = text.split("#Q");
        if (questions.length > 2) {
            int counter = 0;
            for (String s : questions) {
                if (counter == 0) {
                    counter++;
                    continue;
                }
                String[] questionPlusSolutions = s.split("#/Q");
                Question question = new Question((counter + 1) + ". " + questionPlusSolutions[0]);
                exercisesDAOController.insertQuestion(question, exerciseID);
                String[] solutions = questionPlusSolutions[1].split("#R");
                if (solutions.length > 2) {
                    int counterSolutions = 0;
                    for (String c : solutions) {
                        if (counterSolutions == 0) {
                            counterSolutions++;
                            continue;
                        }
                        String[] solution = c.split("#/R");
                        if (solution[0].contains("#C")) {
                            String last="";
                            for (String end:solution[0].split("#C"))
                                last+=end;
                            exercisesDAOController.insertSolution(new Solution(intToABC(counterSolutions) + ") " +last, true), question.getQuestion_ID());
                        }
                        else
                            exercisesDAOController.insertSolution(new Solution(intToABC(counterSolutions) + ") " + solution[0], false), question.getQuestion_ID());
                        counterSolutions++;
                    }
                }
                counter++;
            }
        }
    }

    private static String intToABC(int i) {
        return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
    }
}
