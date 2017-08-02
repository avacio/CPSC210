package ca.ubc.cpsc210.quizbuilder.model.question;

/**
 * A multiplication question.
 */
public class MultiplicationQuestion extends Question {

    private String correctAnswer;

    // REQUIRES: maxMark must be >= 0
    // EFFECTS: constructs multiplication question with given maximum mark, and two factors
    public MultiplicationQuestion (double maxMark, int factor1, int factor2) {
        super(maxMark, Integer.toString(factor1) + " * " + Integer.toString(factor2) + " = ???");
       this.correctAnswer = Integer.toString(factor1*factor2);
    }

    @Override
    public boolean isCorrect(String answer) {
        try {return answer.equals(correctAnswer);}
        catch(NumberFormatException e) {return false;}
    }
}
