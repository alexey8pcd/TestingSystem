package main.tasks;

import main.tasks.questions.Question;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.TreeNode;
import main.tasks.questions.QuestionFactory;
import org.jdom2.Element;

/**
 * Author Alexey
 */
public class Task implements Iterable<Question>, XMLSeriazable, TreeNode {

    private String name;
    private final List<Question> questions;
    private long timeLimit;//миллисекунды
    private int currentQuestionNumber;
    public static final int MILLISECONDS_IN_MINUTE = 60000;
    private final long key;//уникальный код теста
    private Partition parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getKey() {
        return key;
    }

    public Task(String name, Partition parent) {
        this.name = name;
        this.parent = parent;
        this.questions = new LinkedList<>();
        currentQuestionNumber = 0;
        key = System.currentTimeMillis() / 1000;
    }

    public Task(String name, long key) {
        this.name = name;
        this.questions = new LinkedList<>();
        currentQuestionNumber = 0;
        this.key = key;
    }

    public Question getQuestion(int index) {
        if (questions != null && questions.size() > index) {
            return questions.get(index);
        } else {
            return null;
        }

    }

    public int getQuestionsAmount() {
        return questions.size();
    }

    public void setTimeLimit(int minutes) {
        timeLimit = minutes * MILLISECONDS_IN_MINUTE;
    }

    public void setTimeLimit(long milliseconds) {
        timeLimit = milliseconds;
    }

    public int getTimeLimit() {
        return (int) (timeLimit / MILLISECONDS_IN_MINUTE);
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public void removeQuestionAt(int index) {
        if (index < questions.size()) {
            this.questions.remove(index);
        }
    }

    public void save(DataOutputStream writer) throws IOException {
        writer.writeUTF(name);
        writer.writeLong(timeLimit);
        writer.writeInt(questions.size());
        if (!questions.isEmpty()) {
            for (Question question : questions) {
                question.save(writer);
            }
        }
    }

    public static Task loadTask(DataInputStream reader) throws IOException {
        Task task = new Task(reader.readUTF(), null);
        task.setTimeLimit(reader.readLong());
        int amountOfQuestions = reader.readInt();
        for (int i = 0; i < amountOfQuestions; i++) {
            task.addQuestion(QuestionFactory.loadQuestion(reader));
        }
        return task;
    }

    public int getSize() {
        return questions.size();
    }

    @Override
    public Iterator<Question> iterator() {
        currentQuestionNumber = 0;
        return new Iterator<Question>() {

            @Override
            public boolean hasNext() {
                return currentQuestionNumber < questions.size();
            }

            @Override
            public Question next() {
                return questions.get(currentQuestionNumber++);
            }
        };
    }

    /**
     * Переставляет вопросы в задании случайным образом
     */
    public void shuffle() {
        Collections.shuffle(questions);
    }

    public static Task loadTask(Element element) throws Exception{
        Task task = new Task(element.getAttributeValue(NAME),
                Long.valueOf(element.getAttributeValue(KEY)));
        task.setTimeLimit(Long.valueOf(element.getAttributeValue(TIME_LIMIT)));
        for (Element element1 : element.getChildren()) {
            task.addQuestion(QuestionFactory.createFromXMLElement(element1));
        }
        return task;
    }

    @Override
    public Element getXMLElement() {
        Element element = new Element("task");
        element.setAttribute(NAME, name);
        element.setAttribute(KEY, String.valueOf(key));
        element.setAttribute(TIME_LIMIT, String.valueOf(timeLimit));
        for (Question question : questions) {
            element.addContent(question.getXMLElement());
        }
        return element;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return -1;
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Enumeration children() {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
    
    
}
