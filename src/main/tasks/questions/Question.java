package main.tasks.questions;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import main.tasks.AnswerType;
import main.tasks.Variant;
import main.tasks.XMLSeriazable;
import main.tasks.input.UserInput;
import org.jdom2.Element;

/**
 * Author Alexey
 */
public abstract class Question implements XMLSeriazable {

    protected String title;    

    public Question(String title) {
        this.title = title;
    }

    public void load(DataInputStream reader) throws IOException {
        title = reader.readUTF();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public Element getXMLElement() {
        Element element = new Element("question");
        element.setAttribute(NAME, title);
        element.setAttribute(TYPE, String.valueOf(getClassType()));
        return element;
    }

    public abstract int getType();

    public abstract void save(DataOutputStream writer) throws IOException;

    public abstract boolean check(UserInput userInput);   

    public abstract int getClassType();
}
