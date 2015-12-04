package main.tasks.factories;

import java.io.DataInputStream;
import java.io.IOException;
import main.tasks.Partition;
import main.tasks.Task;
import static main.tasks.XMLSeriazable.KEY;
import static main.tasks.XMLSeriazable.NAME;
import static main.tasks.XMLSeriazable.TIME_LIMIT;
import main.tasks.questions.QuestionFactory;
import org.jdom2.Element;

/**
 *  Фабрика для создаения классов заданий для разделов, заданий
 * 
 * @author alex
 */
public class ObjectFactory {

    public static Task loadTask(Element element) throws Exception {
        Task task = new Task(element.getAttributeValue(NAME),
                Long.valueOf(element.getAttributeValue(KEY)));
        task.setTimeLimit(Long.valueOf(element.getAttributeValue(TIME_LIMIT)));
        for (Element element1 : element.getChildren()) {
            task.addQuestion(QuestionFactory.createFromXMLElement(element1));
        }
        return task;
    }
    
    public static Partition loadPartition(Element xmlElement) throws Exception {
        Partition partition = new Partition(xmlElement.getAttributeValue(NAME));
        for (Element element : xmlElement.getChildren()) {
            partition.addTask(loadTask(element));
        }
        return partition;
    }
    
    public static Partition loadPartition(DataInputStream reader) throws IOException {
        Partition partition = new Partition(reader.readUTF());
        int amountOfTasks = reader.readInt();
        for (int i = 0; i < amountOfTasks; i++) {
            partition.addTask(Task.loadTask(reader));
        }
        return partition;
    }

}
