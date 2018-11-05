package cat.tecnocampus.omega.domain.exercises;

import cat.tecnocampus.omega.persistanceController.ExercisesDAOController;

import java.util.ArrayList;
import java.util.List;

public class QuestionsCreator {

    public static void fillTheGapCreator(String text, ExercisesDAOController exercisesDAOController, String exerciseID) {
        String[] beforeHashtag = text.split("#R");
        if (beforeHashtag.length > 1) {
            int counter = 0;
            String questionText = "";
            for (String s : beforeHashtag) {
                if (counter == 0) {
                    questionText = s;
                    counter++;
                    continue;
                }
                String[] afterHastag = s.split("#/R");
                if (afterHastag.length > 1)
                    questionText = afterHastag[1];
                Question question = new Question(questionText);
                question.validation();
                Solution solution = new Solution(afterHastag[0], true);
                solution.validation();
                exercisesDAOController.insertQuestion(question, exerciseID);
                exercisesDAOController.insertSolution(solution, question.getQuestion_ID());
                counter++;
            }
        }
    }

    public static void testCreator(String text, ExercisesDAOController exercisesDAOController, String exerciseID) {
        text.replace("/n", "");
        String[] questions = text.split("#Q");
        if (questions.length > 1) {
            int counter = 0;
            for (String s : questions) {
                if (counter == 0) {
                    counter++;
                    continue;
                }
                String[] questionPlusSolutions = s.split("#/Q");
                Question question = new Question(counter + ". " + questionPlusSolutions[0]);
                question.validation();
                exercisesDAOController.insertQuestion(question, exerciseID);
                String[] solutions = questionPlusSolutions[1].split("#R");
                if (solutions.length > 1) {
                    List<Solution> listSolution = new ArrayList<Solution>();
                    int counterSolutions = 0;
                    for (String c : solutions) {
                        if (counterSolutions == 0) {
                            counterSolutions++;
                            continue;
                        }
                        String[] solution = c.split("#/R");
                        Solution solutionobj;
                        if (solution[0].contains("#C")) {
                            String last = "";
                            for (String end : solution[0].split("#C"))
                                last += end;
                            solutionobj = new Solution(intToABC(counterSolutions) + ") " + last, true);
                        } else
                            solutionobj = new Solution(intToABC(counterSolutions) + ") " + solution[0], false);
                        solutionobj.validation();
                        listSolution.add(solutionobj);
                        counterSolutions++;
                    }
                    solutionListValidation(listSolution, counter);
                    for (Solution solution : listSolution) {
                        exercisesDAOController.insertSolution(solution, question.getQuestion_ID());
                    }
                }
                counter++;
            }
        }
    }

    private static String intToABC(int i) {
        return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
    }

    private static void solutionListValidation(List<Solution> solutions, int i) {
        int counter = 0;
        for (Solution solution : solutions)
            if (solution.getCorrect())
                counter++;

        if (counter == 0)
            throw new IllegalArgumentException("SOMETHING WENT WRONG WHEN CREATING A SOLUTION:\n\t\tThere isn't any solution for the question number " + i);
    }
}
