package main.represent;

import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import static main.Parameters.SCREEN_SIZE;
import main.tasks.questions.Question;
import main.tasks.Task;

/**
 *
 * @author Алексей
 */
public class CreateTaskForm extends javax.swing.JDialog {

    private Task task;

    private class TaskListModel extends AbstractListModel<String> {

        @Override
        public int getSize() {
            if (task != null) {
                return task.getQuestionsAmount();
            }
            return 0;
        }

        @Override
        public String getElementAt(int index) {
            return task.getQuestion(index).getTitle();
        }

    }
    private final TaskListModel model = new TaskListModel();

    public CreateTaskForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2, 
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        spinnerTimeForTask.setValue(10);
        listQuestions.setModel(model);
        task = new Task(null, null);
    }

    public Task getTask() {
        return task;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelForQuestionsList = new javax.swing.JPanel();
        scrollPaneForQuestionsList = new javax.swing.JScrollPane();
        listQuestions = new javax.swing.JList();
        buttonAddQuestion = new javax.swing.JButton();
        buttonDeleteQuestion = new javax.swing.JButton();
        panelForExtraInformation = new javax.swing.JPanel();
        labelTaskName = new javax.swing.JLabel();
        textFieldTaskName = new javax.swing.JTextField();
        labelTimeForTask = new javax.swing.JLabel();
        spinnerTimeForTask = new javax.swing.JSpinner();
        buttonCancel = new javax.swing.JButton();
        buttonCreate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Создать тест");

        panelForQuestionsList.setBackground(new java.awt.Color(0, 204, 204));
        panelForQuestionsList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Вопросы", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        scrollPaneForQuestionsList.setViewportView(listQuestions);

        buttonAddQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/addQuestion.png"))); // NOI18N
        buttonAddQuestion.setToolTipText("Добавить вопрос");
        buttonAddQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddQuestionActionPerformed(evt);
            }
        });

        buttonDeleteQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/deleteQuestion.png"))); // NOI18N
        buttonDeleteQuestion.setToolTipText("Удалить вопрос");
        buttonDeleteQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteQuestionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelForQuestionsListLayout = new javax.swing.GroupLayout(panelForQuestionsList);
        panelForQuestionsList.setLayout(panelForQuestionsListLayout);
        panelForQuestionsListLayout.setHorizontalGroup(
            panelForQuestionsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelForQuestionsListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelForQuestionsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelForQuestionsListLayout.createSequentialGroup()
                        .addComponent(buttonAddQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDeleteQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPaneForQuestionsList, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelForQuestionsListLayout.setVerticalGroup(
            panelForQuestionsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelForQuestionsListLayout.createSequentialGroup()
                .addGroup(panelForQuestionsListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonAddQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDeleteQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneForQuestionsList, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelForExtraInformation.setBackground(new java.awt.Color(0, 153, 204));
        panelForExtraInformation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Дополнительно", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        labelTaskName.setText("<html><h3>Название теста:");

        labelTimeForTask.setText("<html><h3>Время на прохождение(минуты):");

        spinnerTimeForTask.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerTimeForTaskStateChanged(evt);
            }
        });

        buttonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/reject.png"))); // NOI18N
        buttonCancel.setText("Отменить");
        buttonCancel.setToolTipText("");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        buttonCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/accept.png"))); // NOI18N
        buttonCreate.setText("Создать");
        buttonCreate.setToolTipText("");
        buttonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelForExtraInformationLayout = new javax.swing.GroupLayout(panelForExtraInformation);
        panelForExtraInformation.setLayout(panelForExtraInformationLayout);
        panelForExtraInformationLayout.setHorizontalGroup(
            panelForExtraInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelForExtraInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelForExtraInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelForExtraInformationLayout.createSequentialGroup()
                        .addComponent(labelTaskName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldTaskName))
                    .addGroup(panelForExtraInformationLayout.createSequentialGroup()
                        .addComponent(labelTimeForTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(spinnerTimeForTask, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelForExtraInformationLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelForExtraInformationLayout.setVerticalGroup(
            panelForExtraInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelForExtraInformationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelForExtraInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTaskName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldTaskName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelForExtraInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(labelTimeForTask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerTimeForTask))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 205, Short.MAX_VALUE)
                .addGroup(panelForExtraInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelForQuestionsList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelForExtraInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelForExtraInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelForQuestionsList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateActionPerformed
        if (!textFieldTaskName.getText().isEmpty()) {
            if (task.getQuestionsAmount() == 0) {
                JOptionPane.showMessageDialog(null,"Тест не содержит ни одного вопроса");
            } else {
                task.setName(textFieldTaskName.getText());
                task.setTimeLimit((int) spinnerTimeForTask.getValue());
                dispose();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Название теста не должно быть пустым");
        }
    }//GEN-LAST:event_buttonCreateActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        task = null;
        dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonAddQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddQuestionActionPerformed
        if (task != null) {
            CreateQuestionForm createQuestionForm = new CreateQuestionForm(null, true);
            createQuestionForm.setVisible(true);
            Question obtainedQuestion = createQuestionForm.getQuestion();
            if (obtainedQuestion != null) {
                task.addQuestion(obtainedQuestion);
                listQuestions.updateUI();
            }
        }
    }//GEN-LAST:event_buttonAddQuestionActionPerformed

    private void buttonDeleteQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteQuestionActionPerformed
        if (listQuestions.getSelectedIndex() != -1) {
            task.removeQuestionAt(listQuestions.getSelectedIndex());
            listQuestions.updateUI();
        }
    }//GEN-LAST:event_buttonDeleteQuestionActionPerformed

    private void spinnerTimeForTaskStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerTimeForTaskStateChanged
        int value = (int) spinnerTimeForTask.getValue();
        if (value < 1) {
            value = 1;
        } else if (value > 180) {
            value = 180;
        }
        spinnerTimeForTask.setValue(value);
    }//GEN-LAST:event_spinnerTimeForTaskStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddQuestion;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonCreate;
    private javax.swing.JButton buttonDeleteQuestion;
    private javax.swing.JLabel labelTaskName;
    private javax.swing.JLabel labelTimeForTask;
    private javax.swing.JList listQuestions;
    private javax.swing.JPanel panelForExtraInformation;
    private javax.swing.JPanel panelForQuestionsList;
    private javax.swing.JScrollPane scrollPaneForQuestionsList;
    private javax.swing.JSpinner spinnerTimeForTask;
    private javax.swing.JTextField textFieldTaskName;
    // End of variables declaration//GEN-END:variables

}
