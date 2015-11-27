package main.tasks.input;

import main.tasks.questions.Question11;

/**
 * @author Alexey
 */
public class Input11 implements UserInput {

    private String input;

    public Input11(String input) {
        setInputString(input);
    }

    public final void setInputString(String input) {
        this.input = input;
    }

    @Override
    public int getImplementationType() {
        return Question11.CLASS_TYPE;
    }

    @Override
    public Object getUserInput() {
        return input;
    }

}
