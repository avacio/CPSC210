package ca.ubc.cpsc210.quizbuilder.model.quiz;

import ca.ubc.cpsc210.quizbuilder.model.exceptions.OutOfTriesException;
import ca.ubc.cpsc210.quizbuilder.model.exceptions.RetryAnswerException;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;

/**
 * Represents a Quiz that decrements the maximum mark from a question by one
 *            if answered wrong each time.
 */
public class DecrementMarksQuiz extends EachAnswerMustBeRightQuiz {
    // REQUIRES: questions cannot be an empty list
    // EFFECTS: constructs quiz with given list of questions
    public DecrementMarksQuiz(QuestionsList questions) {
        super(questions);
    }

    // MODIFIES: this
    // EFFECTS: submit an answer to the current question and return feedback string;
    // throws RetryAnswerException if the user should re-try the question, but max mark is decremented by 1;
    // throws OutOfTriesException if user has used up all attempts to answer the question.
    @Override
    public String submitAnswer(String answer) throws RetryAnswerException, OutOfTriesException {
        boolean correct = super.checkAnswer(answer);
        if (!correct) {
            this.curQuestion.setMaxMark(this.curQuestion.getMaxMark()-1.0);
            if (this.curQuestion.getMaxMark() <= 0) {throw new OutOfTriesException("");}
            throw new RetryAnswerException("Wrong answer, please retry.");
        }
        return "";
    }
}
