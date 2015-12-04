package main.represent;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import static main.Parameters.SCREEN_SIZE;
import main.management.User;
import main.management.PartitionManager;
import static main.management.PartitionManager.export;
import main.tasks.Partition;
import main.tasks.Task;
import main.tasks.XMLSeriazable;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Алексей
 */
public class MainForm extends javax.swing.JFrame {

    private User currentUser;
    private List<Partition> partitions;
    private List<Task> tasks;

    private static MainForm instance = null;

    private final TreeModel treeModel = new DefaultTreeModel(new TreeNode() {

        @Override
        public TreeNode getChildAt(int childIndex) {
            return partitions.get(childIndex);
        }

        @Override
        public int getChildCount() {
            return partitions.size();
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
            return "Все разделы";
        }

    });

    public static MainForm getInstance(User currentUser) {
        if (instance == null) {
            instance = new MainForm(currentUser);
        }
        return instance;
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    private void startChoosenTask() {
        TreePath elements = treeViewPartitions.getSelectionPath();
        if (elements != null) {
            Object[] path = elements.getPath();
            if (path.length == 3) {
                Task task = ((Task) path[2]);
                LearningTaskForm learningTaskForm = new LearningTaskForm(this, true);
                learningTaskForm.setTask(task);
                learningTaskForm.setUser(currentUser);
                learningTaskForm.init();
                learningTaskForm.setVisible(true);
            }
        }
    }

    /**
     * Конструктор
     *
     * @param currentUser
     */
    private MainForm(User currentUser) {
        initComponents();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        this.currentUser = currentUser;
        if (!currentUser.isTeacher()) {
            buttonCreateNewPartition.setVisible(false);
            buttonCreateTask.setVisible(false);
            buttonDeleteChoosenPartition.setVisible(false);
            buttonDeleteChoosenTask.setVisible(false);
        }
        partitions = PartitionManager.loadAllPartitions();
        treeViewPartitions.setModel(treeModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonCreateNewPartition = new javax.swing.JButton();
        buttonCreateTask = new javax.swing.JButton();
        bShowResults = new javax.swing.JButton();
        buttonDeleteChoosenTask = new javax.swing.JButton();
        buttonDeleteChoosenPartition = new javax.swing.JButton();
        bExportToXML = new javax.swing.JButton();
        bImportFromXml = new javax.swing.JButton();
        buttonPerformTask = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeViewPartitions = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Управление учебным материалом");

        buttonCreateNewPartition.setText("<html><center>Создать новый раздел");
        buttonCreateNewPartition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateNewPartitionActionPerformed(evt);
            }
        });

        buttonCreateTask.setText("<html><center>Создать тест в разделе");
        buttonCreateTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateTaskActionPerformed(evt);
            }
        });

        bShowResults.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bShowResults.setText("<html><center>Просмотр результатов");
        bShowResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bShowResultsActionPerformed(evt);
            }
        });

        buttonDeleteChoosenTask.setText("<html><center>Удалить выбранный тест");
        buttonDeleteChoosenTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteChoosenTaskActionPerformed(evt);
            }
        });

        buttonDeleteChoosenPartition.setText("<html><center>Удалить раздел");
        buttonDeleteChoosenPartition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteChoosenPartitionActionPerformed(evt);
            }
        });

        bExportToXML.setText("<html><center>Экспорт в XML");
        bExportToXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExportToXMLActionPerformed(evt);
            }
        });

        bImportFromXml.setText("<html><center>Импорт из XML");
        bImportFromXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bImportFromXmlActionPerformed(evt);
            }
        });

        buttonPerformTask.setText("<html><center>Пройти выбранный тест");
        buttonPerformTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPerformTaskActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        treeViewPartitions.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeViewPartitions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                treeViewPartitionsMousePressed(evt);
            }
        });
        treeViewPartitions.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                treeViewPartitionsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                treeViewPartitionsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(treeViewPartitions);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(buttonCreateNewPartition, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDeleteChoosenPartition, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bExportToXML, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bImportFromXml, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(buttonCreateTask, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDeleteChoosenTask, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bShowResults, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonPerformTask, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCreateNewPartition, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bExportToXML, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bImportFromXml, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonDeleteChoosenPartition, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonCreateTask, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonDeleteChoosenTask, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bShowResults, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonPerformTask, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPerformTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPerformTaskActionPerformed
        startChoosenTask();
    }//GEN-LAST:event_buttonPerformTaskActionPerformed

    private void buttonCreateNewPartitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateNewPartitionActionPerformed
        String name = JOptionPane.showInputDialog(null,
                "Введите название раздела", "Создать раздел",
                JOptionPane.INFORMATION_MESSAGE);
        if (name != null && !name.isEmpty() && !name.startsWith(" ")) {
            Partition partition = new Partition(name);
            partitions.add(partition);
            PartitionManager.saveAllPartitions(partitions);
            treeViewPartitions.updateUI();
        }

    }//GEN-LAST:event_buttonCreateNewPartitionActionPerformed

    private void buttonCreateTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateTaskActionPerformed
        TreePath elements = treeViewPartitions.getSelectionPath();
        if (elements != null) {
            Object[] path = elements.getPath();
            if (path.length >= 2) {
                tasks = ((Partition) path[1]).getAllTasks();
                if (tasks == null) {
                    tasks = new ArrayList<>();
                }
                CreateTaskForm createTaskForm = new CreateTaskForm(this, true);
                createTaskForm.setVisible(true);
                Task obtainedTask = createTaskForm.getTask();
                if (obtainedTask != null) {
                    tasks.add(obtainedTask);
                    PartitionManager.saveAllPartitions(partitions);
                }
                treeViewPartitions.updateUI();
            }
        }
    }//GEN-LAST:event_buttonCreateTaskActionPerformed

    private void buttonDeleteChoosenPartitionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteChoosenPartitionActionPerformed
        TreePath elements = treeViewPartitions.getSelectionPath();
        if (elements != null) {
            Object[] path = elements.getPath();
            if (path.length >= 2) {
                Partition partition = (Partition) path[1];
                if (!partition.getAllTasks().isEmpty()) {
                    int result = JOptionPane.showConfirmDialog(null, "<html>Данный раздел не пуст и"
                            + " содержит тесты. Вы действительно хотите удалить "
                            + "раздел и все тесты в нем?",
                            "Подтверждение удаления", JOptionPane.YES_NO_OPTION);
                    if (result != JOptionPane.YES_OPTION) {
                        return;
                    }
                }
                if (partitions.remove(partition)) {
                    treeViewPartitions.updateUI();
                    PartitionManager.saveAllPartitions(partitions);
                }
            }
        }
    }//GEN-LAST:event_buttonDeleteChoosenPartitionActionPerformed

    private void buttonDeleteChoosenTaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteChoosenTaskActionPerformed
        TreePath elements = treeViewPartitions.getSelectionPath();
        if (elements != null) {
            Object[] path = elements.getPath();
            if (path.length == 3) {
                Task task = ((Task) path[2]);
                int result = JOptionPane.showConfirmDialog(null,
                        "<html>Удалить выбранный тест?",
                        "Подтверждение удаления", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (((Partition) path[1]).getAllTasks().remove(task)) {
                        treeViewPartitions.updateUI();
                        PartitionManager.saveAllPartitions(partitions);
                    }
                }
            }
        }
    }//GEN-LAST:event_buttonDeleteChoosenTaskActionPerformed

    private void bShowResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bShowResultsActionPerformed
        TestPassStatsForm form = new TestPassStatsForm(this, true);
        form.setVisible(true);
    }//GEN-LAST:event_bShowResultsActionPerformed

    private void bExportToXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExportToXMLActionPerformed
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.showSaveDialog(this);
        File file = fileChooser.getSelectedFile();
        if (file == null) {
            JOptionPane.showMessageDialog(null, "Не выбрано имя файла",
                    "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try (FileWriter fileWriter = new FileWriter(new File(file + ".xml"))) {
            TreePath elements = treeViewPartitions.getSelectionPath();
            if (elements != null) {
                Object[] path = elements.getPath();
                XMLSeriazable element = (XMLSeriazable) path[path.length - 1];
                Document document = new Document(element.getXMLElement());
                XMLOutputter xmlo = new XMLOutputter();
                xmlo.setFormat(Format.getPrettyFormat());
                xmlo.output(document, fileWriter);
                JOptionPane.showMessageDialog(null, "Готово");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_bExportToXMLActionPerformed

    private void bImportFromXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bImportFromXmlActionPerformed
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".xml");
            }

            @Override
            public String getDescription() {
                return "XML file (*.xml)";
            }
        });
        fileChooser.showOpenDialog(this);
        File file = fileChooser.getSelectedFile();
        try {
            TreePath elements = treeViewPartitions.getSelectionPath();
            if (elements != null) {
                SAXBuilder parser = new SAXBuilder();
                FileReader fileReader = new FileReader(file);
                Document doc = parser.build(fileReader);
                Element element = doc.getRootElement();
                Object[] path = elements.getPath();
                export(partitions, path, element);
                treeViewPartitions.updateUI();
                PartitionManager.saveAllPartitions(partitions);
                JOptionPane.showMessageDialog(null, "Импорт завершен успешно");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ошибка импорта документа. "
                    + "Возможно документ был изменен или имеет неправильный формат.",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bImportFromXmlActionPerformed

    private void treeViewPartitionsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeViewPartitionsMousePressed
        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() >= 2) {
            startChoosenTask();
        }
    }//GEN-LAST:event_treeViewPartitionsMousePressed

    private boolean controlPressed;
    private boolean copied;
    private Task selectedTask;
    private Partition selectedPartition;

    private void treeViewPartitionsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_treeViewPartitionsKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_CONTROL) {
            controlPressed = false;
        }
    }//GEN-LAST:event_treeViewPartitionsKeyReleased

    private void treeViewPartitionsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_treeViewPartitionsKeyPressed
        replaceTask(evt);
    }//GEN-LAST:event_treeViewPartitionsKeyPressed

    private void replaceTask(KeyEvent evt) {
        int keyCode = evt.getKeyCode();
        if (keyCode == KeyEvent.VK_CONTROL) {
            controlPressed = true;
        }
        if ((keyCode == KeyEvent.VK_X
                || keyCode == KeyEvent.VK_C) && controlPressed) {
            copied = keyCode == KeyEvent.VK_C;
            TreePath selectionPath = treeViewPartitions.getSelectionPath();
            if (selectionPath != null) {
                Object[] path = selectionPath.getPath();
                if (path != null) {
                    for (Object element : path) {
                        if (element instanceof Partition) {
                            selectedPartition = (Partition) element;
                            continue;
                        }
                        if (element instanceof Task) {
                            selectedTask = (Task) element;;
                            break;
                        }
                    }
                }
            }
        }
        if (keyCode == KeyEvent.VK_V && controlPressed
                && selectedTask != null) {
            TreePath selectionPath = treeViewPartitions.getSelectionPath();
            if (selectionPath != null) {
                Object[] path = selectionPath.getPath();
                if (path != null) {
                    for (Object element : path) {
                        if (element instanceof Partition) {
                            Partition partition = (Partition) element;
                            if (partition == selectedPartition) {
                                if (copied) {
                                    Task task = new Task(selectedTask);
                                    task.setName(task.getName() + System.currentTimeMillis());
                                    partition.addTask(task);
                                }
                            } else {
                                partition.addTask(selectedTask);
                                if (!copied) {
                                    selectedPartition.getAllTasks().remove(selectedTask);
                                }
                            }
                            treeViewPartitions.setBackground(Color.WHITE);
                            selectedTask = null;
                            selectedPartition = null;
                            treeViewPartitions.updateUI();
                            PartitionManager.saveAllPartitions(partitions);
                            break;

                        }
                    }
                }
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bExportToXML;
    private javax.swing.JButton bImportFromXml;
    private javax.swing.JButton bShowResults;
    private javax.swing.JButton buttonCreateNewPartition;
    private javax.swing.JButton buttonCreateTask;
    private javax.swing.JButton buttonDeleteChoosenPartition;
    private javax.swing.JButton buttonDeleteChoosenTask;
    private javax.swing.JButton buttonPerformTask;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree treeViewPartitions;
    // End of variables declaration//GEN-END:variables
}
