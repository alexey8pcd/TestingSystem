package main.tasks.questions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import main.tasks.AnswerType;
import main.tasks.Variant;
import main.tasks.input.Input11;
import main.tasks.input.UserInput;
import org.jdom2.Element;

/**
 * @author Alexey
 */
public class Question11 extends Question {

    private Variant rightVariant;
    public static final int CLASS_TYPE = 3;

    public Question11(Variant variant, String title) {
        super(title);
        this.rightVariant = variant;
    }
    
    public Question11(String nameOfRightVariant, String title){
        super(title);
        this.rightVariant = new Variant(nameOfRightVariant, AnswerType.RIGHT);
    }

    public void addRightVariant(Variant variant) {
        if (this.rightVariant == null) {
            this.rightVariant = variant;
        }
    }

    public void removeVariant() {
        this.rightVariant = null;
    }

    @Override
    public void save(DataOutputStream writer) throws IOException {
        writer.writeInt(CLASS_TYPE);
        writer.writeUTF(title);
        rightVariant.save(writer);
    }

    @Override
    public void load(DataInputStream reader) throws IOException {
        super.load(reader);
        rightVariant = new Variant(reader);
    }

    @Override
    public int getType() {
        return CLASS_TYPE;
    }

    @Override
    public boolean check(UserInput userInput) {
        Input11 input11 = (Input11) userInput;
        return rightVariant.getText().compareToIgnoreCase(
                (String) input11.getUserInput()) == 0;
    }

    @Override
    public Element getXMLElement() {
        Element element = super.getXMLElement();
        return element.setAttribute(ANSWER, rightVariant.getText());
    }

    @Override
    public int getClassType() {
        return CLASS_TYPE;
    }

}
