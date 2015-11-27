package main.represent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static main.Common.SIMPLE_DATE_FORMAT;
import static main.Parameters.SCREEN_SIZE;
import main.criteria.Criteriable;
import main.criteria.LinearCriteria;
import main.data.TestResult;

/**
 *
 * @author Алексей
 */
public class TestPassStatsForm extends javax.swing.JDialog {

    private List<TestResult> results;
    private final Object[] headers = {
        "Название теста", "Кто проходил",
        "Дата прохождения", "Набранный балл", "Оценка"
    };
    private final String splitter = " | ";
    private Criteriable criteria;

    public TestPassStatsForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        criteria = LinearCriteria.getInstance();
        lCriteriaName.setText("Шкала оценки: " + criteria.getName());
        loadData();
    }

    private void loadData() {
        try {
            results = TestResult.loadAll();
            tableResults.setModel(new DefaultTableModel(headers, results.size()));
            for (int i = 0, n = results.size(); i < n; ++i) {
                tableResults.setValueAt(results.get(i).getTaskName(), i, 0);
                tableResults.setValueAt(results.get(i).getUserName(), i, 1);
                tableResults.setValueAt(SIMPLE_DATE_FORMAT.format(
                        results.get(i).getDate()), i, 2);
                tableResults.setValueAt(results.get(i).getScored(), i, 3);
            }
            refreshMark();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void refreshMark() {
        for (int i = 0, n = results.size(); i < n; ++i) {
            tableResults.setValueAt(criteria.calculateMark(
                    results.get(i).getScored()), i, 4);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableResults = new javax.swing.JTable();
        bSaveAs = new javax.swing.JButton();
        bChangeCriteria = new javax.swing.JButton();
        lCriteriaName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Результаты тестов");

        tableResults.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Название теста", "Кто проходил", "Дата прохождения", "Набранные баллы(из 100)"
            }
        ));
        jScrollPane1.setViewportView(tableResults);

        bSaveAs.setText("Сохранить как...");
        bSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveAsActionPerformed(evt);
            }
        });

        bChangeCriteria.setText("Изменить критерий оценки...");
        bChangeCriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bChangeCriteriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lCriteriaName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(bChangeCriteria)
                        .addGap(18, 18, 18)
                        .addComponent(bSaveAs)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lCriteriaName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bSaveAs)
                        .addComponent(bChangeCriteria)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveAsActionPerformed
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.showSaveDialog(this);
        File file = fileChooser.getSelectedFile();
        if (file == null) {
            JOptionPane.showMessageDialog(null, "Не выбрано имя файла",
                    "Ошибка", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try (FileWriter fileWriter = new FileWriter(new File(file + ".csv"))) {
            for (int i = 0; i < headers.length; ++i) {
                fileWriter.append(headers[i].toString());
                if (i < headers.length - 1) {
                    fileWriter.append(splitter);
                }
            }
            fileWriter.append('\n');
            for (int i = 0, n = results.size(); i < n; ++i) {
                TestResult result = results.get(i);
                fileWriter.append(result.getTaskName());
                fileWriter.append(splitter);
                fileWriter.append(result.getUserName());
                fileWriter.append(splitter);
                fileWriter.append(SIMPLE_DATE_FORMAT.format(result.getDate()));
                fileWriter.append(splitter);
                fileWriter.append(String.valueOf(result.getScored()));
                fileWriter.append(splitter);
                fileWriter.append(tableResults.getValueAt(i, 4).toString());
                fileWriter.append('\n');
            }
            fileWriter.flush();
            JOptionPane.showMessageDialog(null, "Сохранение завершено", 
                    "Информация", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }//GEN-LAST:event_bSaveAsActionPerformed

    private void bChangeCriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bChangeCriteriaActionPerformed
        CriteriaForm form = new CriteriaForm(null, true);
        form.setCriteria(criteria);
        form.setVisible(true);
        criteria = form.getCriteria();
        lCriteriaName.setText("Шкала оценки: " + criteria.getName());
        refreshMark();
    }//GEN-LAST:event_bChangeCriteriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bChangeCriteria;
    private javax.swing.JButton bSaveAs;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lCriteriaName;
    private javax.swing.JTable tableResults;
    // End of variables declaration//GEN-END:variables

}
