package ca.ubc.cpsc210.quizbuilder.test;

import ca.ubc.cpsc210.quizbuilder.model.question.MultiplicationQuestion;
import ca.ubc.cpsc210.quizbuilder.model.question.Question;
import ca.ubc.cpsc210.quizbuilder.model.questionslist.QuestionsList;
import ca.ubc.cpsc210.quizbuilder.model.quiz.EachAnswerMustBeRightQuiz;
import ca.ubc.cpsc210.quizbuilder.model.quiz.Quiz;
import org.junit.Before;
import org.junit.Test;

public class MultiplicationQuestionTest extends QuizBuilderTests {

    private Question q1, q2;
    private QuestionsList qList;
    private Quiz testQuiz;

    @Before
    public void runBefore() {
        // make a new multiplication question, question list
        // and quiz for testing
        q1 = new MultiplicationQuestion(10, 2, 5);
        q2 = new MultiplicationQuestion(6, 2, 8);
        qList = new QuestionsList();
        qList.addQuestion(q1);
        qList.addQuestion(q2);
        testQuiz = new EachAnswerMustBeRightQuiz(qList);

    }

    @Test
    public void testIsCorrect() {
        //assertTrue()
    }
}