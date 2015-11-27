package main.tasks.questions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import main.tasks.AnswerType;
import main.tasks.input.UserInput;
import org.jdom2.Element;

/**
 * @author Alexey
 */
public class QuestionN1 extends Question {

    private List<String> listOfVariantTitles;
    private int numberOfRightVariant;
    public static final int CLASS_TYPE = 1;

    public QuestionN1(String title) {
        super(title);
        listOfVariantTitles = new ArrayList<>();
        numberOfRightVariant = 0;
    }

    public void addVariant(String variant) {
        listOfVariantTitles.add(variant);
    }

    public List<String> getAllVariants() {
        return listOfVariantTitles;
    }

    /**
     *
     * @param numberOfRightVariant индекс варианта ответа, который является
     * правильным(>0)
     */
    public void setNumberOfRightVariant(int numberOfRightVariant) {
        if (numberOfRightVariant < listOfVariantTitles.size()) {
            this.numberOfRightVariant = numberOfRightVariant;
        } else {
            this.numberOfRightVariant = 0;
        }
    }

    @Override
    public void save(DataOutputStream writer) throws IOException {
        writer.writeInt(CLASS_TYPE);
        writer.writeUTF(title);
        writer.writeInt(listOfVariantTitles.size());
        for (String string : listOfVariantTitles) {
            writer.writeUTF(string);
        }
        writer.writeInt(numberOfRightVariant);
    }

    @Override
    public void load(DataInputStream reader) throws IOException {
        super.load(reader);
        listOfVariantTitles = new ArrayList<>();
        int amountOfVariants = reader.readInt();
        for (int i = 0; i < amountOfVariants; i++) {
            listOfVariantTitles.add(reader.readUTF());
        }
        numberOfRightVariant = reader.readInt();
    }

    @Override
    public int getType() {
        return CLASS_TYPE;
    }

    @Override
    public boolean check(UserInput userInput) {
        return numberOfRightVariant == (int) userInput.getUserInput();
    }

    @Override
    public Element getXMLElement() {
        Element element = super.getXMLElement();
        for (int i = 0, n = listOfVariantTitles.size(); i < n; ++i) {
            Element variant = new Element(ANSWER);
            variant.setAttribute(NAME, listOfVariantTitles.get(i));
            String val = i == numberOfRightVariant
                    ? AnswerType.RIGHT.name()
                    : AnswerType.WRONG.name();
            variant.setAttribute(TYPE, val);
            element.addContent(variant);
        }
        return element;
    }

    @Override
    public int getClassType() {
        return CLASS_TYPE;
    }
}
