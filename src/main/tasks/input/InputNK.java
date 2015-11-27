package main.tasks.input;

import java.util.ArrayList;
import java.util.List;
import main.tasks.AnswerType;
import main.tasks.questions.QuestionNK;

/**
 * @author Alexey
 */
public class InputNK implements UserInput {

    private final List<AnswerType> userVariants;

    public InputNK() {
        userVariants = new ArrayList<>();
    }
    
    @Override
    public List<AnswerType> getUserInput(){
        return userVariants;
    }
    
    public void addVariant(AnswerType answerType){
        userVariants.add(answerType);
    }

    @Override
    public int getImplementationType() {
        return QuestionNK.CLASS_TYPE;
    }

}
