package main.data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static main.Common.SIMPLE_DATE_FORMAT;
import main.tasks.Task;

/**
 * @author Alexey
 */
public final class TestResult {

    private static final String path = "result.dat";
    private final String userName;//кто сдавал тест
    private final Date date;//когда сдавал

    private final int scored;//набранный балл(из 100)
    private final String taskName;//название теста
    private final long taskKey;//ключ теста
    private static final StringBuilder builder = new StringBuilder();
    

    public String getUserName() {
        return userName;
    }

    public Date getDate() {
        return (Date) date.clone();
    }

    public int getScored() {
        return scored;
    }

    public String getTaskName() {
        return taskName;
    }

    public long getTaskKey() {
        return taskKey;
    }

    public TestResult(String userName, Date date, int scored, Task task) {
        this.userName = userName;
        this.date = date;
        this.scored = scored;
        this.taskName = task.getName();
        this.taskKey = task.getKey();
    }

    public TestResult(String userName, Date date, int scored,
            String taskName, long taskKey) {
        this.userName = userName;
        this.date = date;
        this.scored = scored;
        this.taskName = taskName;
        this.taskKey = taskKey;
    }

    @Override
    public String toString() {
        builder.setLength(0);
        builder.append("Пользователь ").append(userName);
        builder.append(" выполнил тест '").append(taskName);
        builder.append("' ").append(SIMPLE_DATE_FORMAT.format(date));
        builder.append(" набрал(а) ").append(scored);
        builder.append(" баллов из 100");
        return builder.toString();
    }

    private void save(DataOutputStream stream) throws IOException {
        stream.writeLong(taskKey);
        stream.writeUTF(userName);
        stream.writeUTF(taskName);
        stream.writeUTF(SIMPLE_DATE_FORMAT.format(date));
        stream.writeInt(scored);
        stream.flush();
    }

    public void append() throws IOException {
        List<TestResult> results = loadAll();
        try (DataOutputStream dataOutputStream = new DataOutputStream(
                new FileOutputStream(new File(path)))) {
            for (TestResult result : results) {
                result.save(dataOutputStream);
            }
            save(dataOutputStream);
        }
    }

    public static List<TestResult> loadAll() {
        try (DataInputStream dataInputStream
                = new DataInputStream(new FileInputStream(new File(path)))) {
            List<TestResult> results = new ArrayList<>();
            while (dataInputStream.available() > 0) {
                long taskKey = dataInputStream.readLong();
                String name = dataInputStream.readUTF();
                String taskName = dataInputStream.readUTF();
                Date date = SIMPLE_DATE_FORMAT.parse(dataInputStream.readUTF());
                int scoredIn = dataInputStream.readInt();
                results.add(new TestResult(name, date, scoredIn,
                        taskName, taskKey));
            }
            return results;

        } catch (FileNotFoundException ex) {

        } catch (ParseException | IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
        return Collections.EMPTY_LIST;
    }

}
