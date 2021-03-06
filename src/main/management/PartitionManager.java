package main.management;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import main.tasks.Partition;
import main.tasks.Task;
import static main.tasks.factories.ObjectFactory.*;
import org.jdom2.Element;

/**
 * Управляет загрузкой и сохранением набора разделов
 *
 * @author Alexey
 */
public class PartitionManager {

    public static final String PARTITION_FILE_PATH = "./partitions.dat";    

    public static void export(List<Partition> partitions, Object[] path,
            Element toExport) throws Exception {
        String className = toExport.getName();
        if (Task.class.getSimpleName().equalsIgnoreCase(className)) {
            Task task = loadTask(toExport);
            for (Object pathElement : path) {
                if (pathElement instanceof Partition) {
                    Partition target = (Partition) pathElement;
                    target.addTask(task);
                    break;
                }
            }
        } else if (Partition.class.getSimpleName().equalsIgnoreCase(className)) {
            Partition partition = loadPartition(toExport);
            partitions.add(partition);
        }
    }

    

    /**
     * Загружает из файла набор разделов с заданиями
     *
     *
     * @return загруженный список разделов с заданиями
     */
    public static List<Partition> loadAllPartitions() {
        List<Partition> partitions = new ArrayList<>();
        try (DataInputStream reader = new DataInputStream(new FileInputStream(
                new File(PARTITION_FILE_PATH)))) {
            int amountOfPartitions = reader.readInt();
            for (int i = 0; i < amountOfPartitions; i++) {
                partitions.add(loadPartition(reader));
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex1) {
            int result = JOptionPane.showConfirmDialog(null,
                    "<html>Не удалось кооректно прочитать данные о заданиях. "
                    + "<br>Удалить старую информацию?",
                    "Ошибка чтения файла", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                File file = new File(PARTITION_FILE_PATH);
                if (file.exists()) {
                    file.delete();
                }
            }
            partitions = new ArrayList<>();
        }
        return partitions;
    }

    public static void saveAllPartitions(List<Partition> partitions) {
        try (DataOutputStream writer = new DataOutputStream(
                new FileOutputStream(new File(PARTITION_FILE_PATH)))) {
            writer.writeInt(partitions.size());
            if (!partitions.isEmpty()) {
                for (Partition partition : partitions) {
                    partition.save(writer);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

}
