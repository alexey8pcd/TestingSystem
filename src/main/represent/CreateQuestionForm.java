package main.represent;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import static main.Parameters.*;
import main.tasks.AnswerType;
import main.tasks.Variant;
import main.tasks.questions.Question;
import main.tasks.questions.Question11;
import main.tasks.questions.QuestionFactory;
import main.tasks.questions.QuestionN1;
import main.tasks.questions.QuestionNK;

/**
 *
 * @author Алексей
 */
public class CreateQuestionForm extends javax.swing.JDialog {

    private final ButtonGroup groupForVariantsRadioButtons;
    private Question question;
    private int rightVariantsCount;
    private List<JCheckBox> listOfCheckBoxes;
    private final List<String> listOfVariantsHeaders;
    private JTextField textFieldForEnterVariant;
    private int typeOfQuestion;
    private int componentsCount;
    private int numberOfRightVariant;

    public CreateQuestionForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        panelForVariants.setLayout(new GridLayout(5, 1));
        groupForVariantsRadioButtons = new ButtonGroup();
        typeOfQuestion = comboBoxQuestionType.getSelectedIndex();
        rightVariantsCount = 0;
        componentsCount = 0;
        listOfVariantsHeaders = new ArrayList<>();
        listOfCheckBoxes = null;
        textFieldForEnterVariant = null;
        question = null;
    }

    public Question getQuestion() {
        return question;
    }

    private void updateButtons() {
        switch (typeOfQuestion) {
            case 0:
                buttonAddRightVariant.setVisible(true);
                buttonAddRightVariant.setText("<html>Добавить<br> правильный<br> вариант");
                buttonAddWrongVariant.setVisible(true);
                break;
            case 1:
                buttonAddWrongVariant.setVisible(false);
                buttonAddRightVariant.setText("<html>Добавить<br> вариант<br> ответа");
                break;
            case 2:
                buttonAddWrongVariant.setVisible(false);
                buttonAddRightVariant.setText("Добавить поле для ответа");
        }
    }

    private boolean addVariant(boolean right) {
        //получение текст варианта ответа
        String variantTitle = null;
        int type = comboBoxQuestionType.getSelectedIndex() + 1;
        if (type == QuestionN1.CLASS_TYPE || type == QuestionNK.CLASS_TYPE) {
            variantTitle = JOptionPane.showInputDialog(null,
                    "Введите текст варианта ответа",
                    "Добавить вариант ответа", JOptionPane.INFORMATION_MESSAGE);
            if (variantTitle == null || variantTitle.isEmpty()
                    || variantTitle.startsWith(" ")) {
                return false;
            }
        }
        //добавляем компонент на форму
        switch (type) {
            case QuestionN1.CLASS_TYPE:
                JPanel borderPanel = new JPanel(new GridLayout(1, 1));
                JRadioButton generatedRadioButton = new JRadioButton(variantTitle);
                if (right) {
                    borderPanel.setBackground(RIGHT_ANSWER_COLOR);
                    borderPanel.setBorder(BorderFactory.createTitledBorder("правильный"));
                    numberOfRightVariant = componentsCount;
                } else {
                    borderPanel.setBackground(WRONG_ANSWER_COLOR);
                    borderPanel.setBorder(BorderFactory.createTitledBorder("неправильный"));
                }
                generatedRadioButton.setBackground(borderPanel.getBackground());
                groupForVariantsRadioButtons.add(generatedRadioButton);
                borderPanel.add(generatedRadioButton);
                this.panelForVariants.add(borderPanel);
                this.listOfVariantsHeaders.add(variantTitle);
                break;
            case QuestionNK.CLASS_TYPE:
                JCheckBox generatedCheckButton = new JCheckBox(variantTitle);
                generatedCheckButton.setBackground(panelForVariants.getBackground());
                this.panelForVariants.add(generatedCheckButton);
                if (listOfCheckBoxes == null) {
                    listOfCheckBoxes = new ArrayList<>();
                }
                listOfCheckBoxes.add(generatedCheckButton);
                break;
            case Question11.CLASS_TYPE:
                JTextField generatedTextField = new JTextField();
                this.panelForVariants.add(generatedTextField);
                textFieldForEnterVariant = generatedTextField;
        }
        this.comboBoxQuestionType.setEnabled(false);
        this.panelForVariants.updateUI();
        this.componentsCount++;
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelQuestionHeader = new javax.swing.JLabel();
        buttonEnter = new javax.swing.JButton();
        buttonExit = new javax.swing.JButton();
        labelQuestionType = new javax.swing.JLabel();
        comboBoxQuestionType = new javax.swing.JComboBox();
        panelForVariants = new javax.swing.JPanel();
        buttonAddRightVariant = new javax.swing.JButton();
        buttonAddWrongVariant = new javax.swing.JButton();
        scrollPaneForQuestionHeader = new javax.swing.JScrollPane();
        textAreaQuestionHeader = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Создать вопрос");

        labelQuestionHeader.setText("<html><h3>Заголовок вопроса:");

        buttonEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/accept.png"))); // NOI18N
        buttonEnter.setText("Завершить");
        buttonEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnterActionPerformed(evt);
            }
        });

        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/reject.png"))); // NOI18N
        buttonExit.setText("Отменить");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        labelQuestionType.setText("<html><h3>Тип вопроса:");

        comboBoxQuestionType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Один правильный вариант", "Несколько правильных вариантов", "Вводимый ответ" }));
        comboBoxQuestionType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxQuestionTypeActionPerformed(evt);
            }
        });

        panelForVariants.setBackground(new java.awt.Color(0, 204, 204));
        panelForVariants.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Варианты ответа:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        javax.swing.GroupLayout panelForVariantsLayout = new javax.swing.GroupLayout(panelForVariants);
        panelForVariants.setLayout(panelForVariantsLayout);
        panelForVariantsLayout.setHorizontalGroup(
            panelForVariantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 683, Short.MAX_VALUE)
        );
        panelForVariantsLayout.setVerticalGroup(
            panelForVariantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );

        buttonAddRightVariant.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        buttonAddRightVariant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/rightAnswer.png"))); // NOI18N
        buttonAddRightVariant.setText("<html>Добавить<br> правильный<br> вариант");
        buttonAddRightVariant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddRightVariantActionPerformed(evt);
            }
        });

        buttonAddWrongVariant.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        buttonAddWrongVariant.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/wrongAnswer.png"))); // NOI18N
        buttonAddWrongVariant.setText("<html>Добавить<br> неправильный<br> вариант");
        buttonAddWrongVariant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddWrongVariantActionPerformed(evt);
            }
        });

        textAreaQuestionHeader.setColumns(20);
        textAreaQuestionHeader.setLineWrap(true);
        textAreaQuestionHeader.setRows(4);
        scrollPaneForQuestionHeader.setViewportView(textAreaQuestionHeader);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelForVariants, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelQuestionHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelQuestionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPaneForQuestionHeader)
                            .addComponent(comboBoxQuestionType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonAddRightVariant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonAddWrongVariant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonEnter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonExit)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonEnter, buttonExit});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelQuestionHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPaneForQuestionHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuestionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxQuestionType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelForVariants, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAddRightVariant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonAddWrongVariant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonEnter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buttonAddRightVariant, buttonAddWrongVariant, buttonEnter, buttonExit});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddRightVariantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddRightVariantActionPerformed
        if (addVariant(true)) {
            rightVariantsCount++;
            if (rightVariantsCount > 0 && typeOfQuestion == 0) {
                buttonAddRightVariant.setEnabled(false);
            }
            if (componentsCount > 0 && typeOfQuestion == 2) {
                buttonAddRightVariant.setEnabled(false);
            }
        }
    }//GEN-LAST:event_buttonAddRightVariantActionPerformed


    private void buttonAddWrongVariantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddWrongVariantActionPerformed
        addVariant(false);
    }//GEN-LAST:event_buttonAddWrongVariantActionPerformed

    private void comboBoxQuestionTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxQuestionTypeActionPerformed
        typeOfQuestion = comboBoxQuestionType.getSelectedIndex();
        updateButtons();
    }//GEN-LAST:event_comboBoxQuestionTypeActionPerformed

    private void buttonEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEnterActionPerformed

        if (textFieldForEnterVariant != null && textFieldForEnterVariant.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Поле ввода для ответа не может быть пустым");
        } else if (rightVariantsCount == 0) {
            JOptionPane.showMessageDialog(null, "Не было добавлено ни одного правильного варианта");
        } else {
            String questionHeader = textAreaQuestionHeader.getText();
            if (!questionHeader.isEmpty()) {
                switch (typeOfQuestion) {
                    case 0:
                        QuestionN1 questionN1 = QuestionFactory.createQuestionN1(questionHeader);
                        for (String string : listOfVariantsHeaders) {
                            questionN1.addVariant(string);
                        }
                        questionN1.setNumberOfRightVariant(numberOfRightVariant);
                        question = questionN1;
                        break;
                    case 1:
                        QuestionNK questionNK = QuestionFactory.createQuestionNK(questionHeader);
                        for (JCheckBox checkBox : listOfCheckBoxes) {
                            questionNK.addVariant(new Variant(checkBox.getText(), checkBox.isSelected() == true
                                    ? AnswerType.RIGHT : AnswerType.WRONG));
                        }
                        question = questionNK;
                        break;
                    case 2:
                        Question11 question11 = QuestionFactory.createQuestion11(questionHeader);
                        question11.addRightVariant(new Variant(textFieldForEnterVariant.getText(), AnswerType.RIGHT));
                        question = question11;
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Название вопроса не должно быть пустым");
            }
        }

    }//GEN-LAST:event_buttonEnterActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        question = null;
        dispose();
    }//GEN-LAST:event_buttonExitActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddRightVariant;
    private javax.swing.JButton buttonAddWrongVariant;
    private javax.swing.JButton buttonEnter;
    private javax.swing.JButton buttonExit;
    private javax.swing.JComboBox comboBoxQuestionType;
    private javax.swing.JLabel labelQuestionHeader;
    private javax.swing.JLabel labelQuestionType;
    private javax.swing.JPanel panelForVariants;
    private javax.swing.JScrollPane scrollPaneForQuestionHeader;
    private javax.swing.JTextArea textAreaQuestionHeader;
    // End of variables declaration//GEN-END:variables
}
