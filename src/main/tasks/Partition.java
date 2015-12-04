package main.tasks;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.tree.TreeNode;
import main.tasks.questions.Question;
import org.jdom2.Element;

/**
 * Author Alexey
 */
public class Partition implements XMLSeriazable, TreeNode {

    private String name;
    private final List<Task> taskKit;

    public Partition(String name) {
        taskKit = new ArrayList<>();
        this.name = name;
    }

    public List<Task> getAllTasks() {
        return taskKit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addTask(Task task) {
        for (Task t : taskKit) {
            if (t.getName().equals(task.getName())) {
                return false;
            }
        }
        this.taskKit.add(task);
        return true;
    }

    public Task getTask(int index) {
        if (index < taskKit.size()) {
            return taskKit.get(index);
        } else {
            return null;
        }
    }

    public void save(DataOutputStream writer) throws IOException {
        writer.writeUTF(name);
        writer.writeInt(taskKit.size());
        if (!taskKit.isEmpty()) {
            for (Task task : taskKit) {
                task.save(writer);
            }
        }
    }

    @Override
    public Element getXMLElement() {
        Element partition = new Element("partition");
        partition.setAttribute(NAME, name);
        for (Task task : taskKit) {
            partition.addContent(task.getXMLElement());
        }
        return partition;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        return taskKit.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return taskKit.size();
    }

    @Override
    public TreeNode getParent() {
        return null;
    }

    @Override
    public int getIndex(TreeNode node) {
        return -1;
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return false;
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
