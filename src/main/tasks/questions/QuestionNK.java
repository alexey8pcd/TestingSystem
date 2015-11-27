package main.tasks.questions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.tasks.AnswerType;
import main.tasks.Variant;
import main.tasks.input.InputNK;
import main.tasks.input.UserInput;
import org.jdom2.Element;

/**
 * @author Alexey
 */
public class QuestionNK extends Question {

    public static final int CLASS_TYPE = 2;
    private List<Variant> variants;

    public QuestionNK(String title) {
        super(title);
        variants = new ArrayList<>();
    }

    public List<Variant> getAllVariants() {
        return variants;
    }

    public void addVariant(Variant variant) {
        variants.add(variant);
    }

    @Override
    public void save(DataOutputStream writer) throws IOException {
        writer.writeInt(CLASS_TYPE);
        writer.writeUTF(title);
        writer.writeInt(variants.size());
        for (Variant variant : variants) {
            variant.save(writer);
        }
    }

    @Override
    public void load(DataInputStream reader) throws IOException {
        super.load(reader);
        int amountOfVariants = reader.readInt();
        variants = new ArrayList<>();
        for (int i = 0; i < amountOfVariants; i++) {
            variants.add(new Variant(reader));
        }
    }

    @Override
    public int getType() {
        return CLASS_TYPE;
    }

    @Override
    public boolean check(UserInput userInput) {
        InputNK inputNK = (InputNK) userInput;
        List<AnswerType> listOfAnswers = inputNK.getUserInput();
        for (int i = 0; i < listOfAnswers.size(); i++) {
            if (listOfAnswers.get(i) != variants.get(i).getAnswerType()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Element getXMLElement() {
        Element element = super.getXMLElement();
        for (Variant variant : variants) {
            Element vrn = new Element(ANSWER);
            vrn.setAttribute(NAME, variant.getText());
            vrn.setAttribute(TYPE, variant.getAnswerType().name());
            element.addContent(vrn);
        }
        return element;
    }

    @Override
    public int getClassType() {
        return CLASS_TYPE;
    }

}
