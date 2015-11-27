package main.tasks.input;

import main.tasks.questions.QuestionN1;

/**
 * @author Alexey
 */
public class InputN1 implements UserInput {

    private int numberOfRightVariant;

    public InputN1(int numberOfRightVariant) {
        setNumberOfRightVariant(numberOfRightVariant);
    }

    public final void setNumberOfRightVariant(int numberOfRightVariant) {
        if (numberOfRightVariant < 0) {
            numberOfRightVariant = 0;
        }
        this.numberOfRightVariant = numberOfRightVariant;
    }

    @Override
    public int getImplementationType() {
        return QuestionN1.CLASS_TYPE;
    }

    @Override
    public Object getUserInput() {
        return numberOfRightVariant;
    }

}
