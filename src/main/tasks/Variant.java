package main.tasks;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author Alexey
 */
public class Variant {

    private final AnswerType answerType;
    private final String text;

    public Variant(String text, AnswerType answerType) {
        this.answerType = answerType;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public AnswerType getAnswerType() {
        return answerType;
    }

    public void save(DataOutputStream writer) throws IOException {
        writer.writeUTF(text);
        writer.writeUTF(answerType.toString());
    }

    public Variant(DataInputStream reader) throws IOException {
        text = reader.readUTF();
        answerType = AnswerType.valueOf(reader.readUTF());
    }

    @Override
    public String toString() {
        String prefix = "";
        switch (answerType) {
            case RIGHT:
                prefix = "<html><font color=\"green\">(верно)";
                break;
            case WRONG:
                prefix = "<html><font color=\"red\">(неверно)";
        }
        return prefix + text;
    }

}
