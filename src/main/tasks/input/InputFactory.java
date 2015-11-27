package main.tasks.input;

import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import main.tasks.AnswerType;

/**
 * @author Alexey
 */
public class InputFactory {

    public static UserInput createInput11(String text) {
        return new Input11(text);
    }

    public static UserInput createInputN1(List<JRadioButton> radioButtons) {
        for (int i = 0; i < radioButtons.size(); i++) {
            JRadioButton radioButton = radioButtons.get(i);
            if (radioButton.isSelected()) {
                return new InputN1(i);
            }
        }
        return null;
    }

    public static UserInput createInputNK(List<JCheckBox> checkBoxes) {
        InputNK inputNK = new InputNK();
        for (JCheckBox checkBox : checkBoxes) {
            inputNK.addVariant(checkBox.isSelected() == true
                    ? AnswerType.RIGHT : AnswerType.WRONG);
        }
        return inputNK;
    }
}
