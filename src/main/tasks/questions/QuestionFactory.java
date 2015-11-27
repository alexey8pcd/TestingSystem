package main.tasks.questions;

import java.io.DataInputStream;
import java.io.IOException;
import main.tasks.AnswerType;
import main.tasks.Variant;
import static main.tasks.questions.Question.ANSWER;
import static main.tasks.questions.Question.NAME;
import static main.tasks.questions.Question.TYPE;
import org.jdom2.Element;

/**
 * @author Alexey
 */
public class QuestionFactory {

    public static Question loadQuestion(DataInputStream reader) throws IOException {
        int classType = reader.readInt();
        Question question = QuestionFactory.createQuestion(null, classType);
        question.load(reader);
        return question;
    }

    public static Question11 createQuestion11(String title) {
        return new Question11("", title);
    }

    public static QuestionN1 createQuestionN1(String title) {
        return new QuestionN1(title);
    }

    public static QuestionNK createQuestionNK(String title) {
        return new QuestionNK(title);
    }

    /**
     * Создает вопрос нужного класса, основываясь на информации в
     * xml-представлении.
     *
     * @param element
     * @return Question11, QuetionN1, QuestionNK
     */
    public static Question createFromXMLElement(Element element) throws Exception {
        String questionTitle = element.getAttributeValue(NAME);
        Question question = null;
        int type = Integer.valueOf(element.getAttributeValue(TYPE));
        switch (type) {
            case Question11.CLASS_TYPE:
                question = new Question11(element.getAttributeValue(ANSWER),
                        questionTitle);
                break;
            case QuestionN1.CLASS_TYPE:
                QuestionN1 questionN1 = new QuestionN1(questionTitle);
                for (int i = 0, n = element.getChildren().size(); i < n; ++i) {
                    Element variant = element.getChildren().get(i);
                    questionN1.addVariant(variant.getAttributeValue(NAME));
                    if (variant.getAttributeValue(TYPE).
                            equalsIgnoreCase(AnswerType.RIGHT.name())) {
                        questionN1.setNumberOfRightVariant(i);
                    }
                }
                question = questionN1;
                break;
            case QuestionNK.CLASS_TYPE:
                QuestionNK questionNK = new QuestionNK(questionTitle);
                for (Element variant : element.getChildren()) {
                    questionNK.addVariant(new Variant(
                            variant.getAttributeValue(NAME),
                            AnswerType.valueOf(variant.getAttributeValue(TYPE))));
                }
                question = questionNK;
        }
        return question;
    }

    public static Question createQuestion(String title, int classType) {
        switch (classType) {
            case Question11.CLASS_TYPE:
                return new Question11("", title);
            case QuestionNK.CLASS_TYPE:
                return new QuestionNK(title);
            case QuestionN1.CLASS_TYPE:
                return new QuestionN1(title);
        }
        return null;
    }
}
