package main.represent;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import main.Parameters;
import static main.Parameters.SCREEN_SIZE;
import main.data.TestResult;
import main.management.User;
import main.tasks.AnswerType;
import main.tasks.Task;
import main.tasks.Variant;
import main.tasks.input.InputFactory;
import main.tasks.input.UserInput;
import main.tasks.questions.Question;
import main.tasks.questions.Question11;
import main.tasks.questions.QuestionN1;
import main.tasks.questions.QuestionNK;

/**
 *
 * @author Алексей
 */
public class LearningTaskForm extends javax.swing.JDialog {

    public static final String PATH = "./log.txt";
    private Task task;
    private User currentUser;
    private Iterator<Question> iteratorQuestion;
    private int currentQuestionNumber;
    private int questionsAmount;
    private boolean running;
    private int timeToEnd;
    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private List<JRadioButton> listOfRadioButtons;
    private List<JCheckBox> listOfCheckBoxes;
    private JTextField textFieldForInputAnswer;
    private ButtonGroup radioButtonGroup;
    private final Timer timer;
    private int amountOfRightVariants;
    private Question previous;
    private double result;
    private PrintWriter printWriter;
    private Variant[] answers;

    public LearningTaskForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        this.timer = new Timer(1000, (ActionEvent e) -> {
            if (running) {
                timeToEnd -= 1;
                if (timeToEnd < 0) {
                    JOptionPane.showMessageDialog(null, "Время истекло");
                    stopTest();
                } else {
                    showTime();
                }

            }
        });
        printWriter = null;
    }

    public double getAbsoluteResult() {
        return result;
    }

    public Variant[] getAnswers() {
        return answers;
    }

    public void writeToLog(String message) {
        STRING_BUILDER.setLength(0);
        STRING_BUILDER.append("Пользователь ");
        STRING_BUILDER.append(currentUser.getLogin());
        STRING_BUILDER.append(" выполнил действие: ");
        STRING_BUILDER.append(message);
        STRING_BUILDER.append(" Время: ");
        STRING_BUILDER.append(Calendar.getInstance().getTime().toString());
        if (printWriter != null) {
            printWriter.println(STRING_BUILDER.toString());
        }
        System.out.println(STRING_BUILDER.toString());
    }

    public void setUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setTask(Task task) {
        this.task = task;
        answers = new Variant[task.getSize()];
        this.task.shuffle();
    }

    public void init() {
        iteratorQuestion = task.iterator();
        buttonNextQuestion.setText("Начать тест");
        currentQuestionNumber = 0;
        timeToEnd = task.getTimeLimit() * 60;
        setVisibleComponents(false);
        running = false;
        panelForVariants.setLayout(new GridLayout(5, 1));
        questionsAmount = task.getQuestionsAmount();
        radioButtonGroup = new ButtonGroup();
        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File(PATH))));
        } catch (IOException ex) {
        }
    }

    private void stopTest() {
        if (printWriter != null) {
            printWriter.close();
        }
        running = false;
        timer.stop();
        writeToLog("завершил тест");
        result = (double) amountOfRightVariants / questionsAmount;
        try {
            new TestResult(currentUser.getLogin(), Calendar.getInstance().getTime(),
                    (int) (result * 100), task).append();
            ResultForm resultForm = new ResultForm(null, true);
            resultForm.setData(result, answers);
            resultForm.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.toString(),
                    "Ошибка сохранения файла", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }

    private void setVisibleComponents(boolean visible) {
        labelQuestionHeader.setVisible(visible);
        labelQuestionNumber.setVisible(visible);
        labelRemainingTime.setVisible(visible);
        panelForVariants.setVisible(visible);
    }

    private void showTime() {
        //оставшееся время
        STRING_BUILDER.setLength(0);
        STRING_BUILDER.append("Оставшееся время: ");
        STRING_BUILDER.append(timeToEnd / 60);
        STRING_BUILDER.append(":");
        STRING_BUILDER.append(timeToEnd % 60);
        labelRemainingTime.setText(STRING_BUILDER.toString());
    }

    private void makeFrame(Question currentQuestion) {

        //номер вопроса
        STRING_BUILDER.setLength(0);
        STRING_BUILDER.append("<html>");
        STRING_BUILDER.append("Вопрос ");
        STRING_BUILDER.append(currentQuestionNumber + 1);
        STRING_BUILDER.append("/");
        STRING_BUILDER.append(questionsAmount);
        labelQuestionNumber.setText(STRING_BUILDER.toString());

        //заголовок вопроса
        STRING_BUILDER.setLength(0);
        STRING_BUILDER.append("<html><h4>");
        STRING_BUILDER.append(currentQuestion.getTitle());
        labelQuestionHeader.setText(STRING_BUILDER.toString());
        //оставшееся время
        showTime();
        if (currentQuestionNumber == questionsAmount - 1) {
            buttonNextQuestion.setText("Завершить");
        } else {
            buttonNextQuestion.setText("Следующий вопрос");
        }
        removeComponents();
        createComponents(currentQuestion);
        panelForVariants.updateUI();
    }

    private void createComponents(Question currentQuestion) {
        switch (currentQuestion.getType()) {
            case Question11.CLASS_TYPE:
                textFieldForInputAnswer = new JTextField();
                textFieldForInputAnswer.setToolTipText("Введите текст ответа");
                textFieldForInputAnswer.setSize(Parameters.COMPONENT_SIZE);
                this.panelForVariants.add(textFieldForInputAnswer);
                break;
            case QuestionN1.CLASS_TYPE:
                listOfRadioButtons = new ArrayList<>();
                QuestionN1 questionN1 = (QuestionN1) currentQuestion;
                for (String string : questionN1.getAllVariants()) {
                    JRadioButton radioButton = new JRadioButton(string);
                    radioButton.setBackground(panelForVariants.getBackground());
                    radioButton.setSize(Parameters.COMPONENT_SIZE);
                    this.listOfRadioButtons.add(radioButton);
                    this.panelForVariants.add(radioButton);
                    this.radioButtonGroup.add(radioButton);
                }
                if (!listOfRadioButtons.isEmpty()) {
                    listOfRadioButtons.get(0).setSelected(true);
                }
                break;
            case QuestionNK.CLASS_TYPE:
                listOfCheckBoxes = new ArrayList<>();
                QuestionNK questionNK = (QuestionNK) currentQuestion;
                for (Variant variant : questionNK.getAllVariants()) {
                    JCheckBox checkBox = new JCheckBox(variant.getText());
                    checkBox.setBackground(panelForVariants.getBackground());
                    checkBox.setSize(Parameters.COMPONENT_SIZE);
                    this.listOfCheckBoxes.add(checkBox);
                    this.panelForVariants.add(checkBox);
                }
        }
    }

    private void removeComponents() {

        if (textFieldForInputAnswer != null) {
            panelForVariants.remove(textFieldForInputAnswer);
            textFieldForInputAnswer = null;
        }
        if (listOfCheckBoxes != null) {
            for (JCheckBox checkBox : listOfCheckBoxes) {
                panelForVariants.remove(checkBox);
            }
            listOfCheckBoxes = null;
        }
        if (listOfRadioButtons != null) {
            for (JRadioButton radioButton : listOfRadioButtons) {
                panelForVariants.remove(radioButton);
                radioButtonGroup.remove(radioButton);
            }
            listOfRadioButtons = null;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelForVariants = new javax.swing.JPanel();
        labelQuestionHeader = new javax.swing.JLabel();
        buttonNextQuestion = new javax.swing.JButton();
        labelQuestionNumber = new javax.swing.JLabel();
        labelRemainingTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Пройти тест");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelForVariants.setBackground(new java.awt.Color(0, 204, 204));

        javax.swing.GroupLayout panelForVariantsLayout = new javax.swing.GroupLayout(panelForVariants);
        panelForVariants.setLayout(panelForVariantsLayout);
        panelForVariantsLayout.setHorizontalGroup(
            panelForVariantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
        );
        panelForVariantsLayout.setVerticalGroup(
            panelForVariantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );

        labelQuestionHeader.setText("Заголовок вопроса");

        buttonNextQuestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/next.png"))); // NOI18N
        buttonNextQuestion.setText("Следующий вопрос");
        buttonNextQuestion.setToolTipText("");
        buttonNextQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNextQuestionActionPerformed(evt);
            }
        });

        labelQuestionNumber.setText("Номер вопроса");

        labelRemainingTime.setText("Оставшееся время");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelQuestionHeader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelForVariants, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelQuestionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelRemainingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                        .addComponent(buttonNextQuestion)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelQuestionHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelForVariants, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonNextQuestion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelQuestionNumber)
                        .addComponent(labelRemainingTime)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonNextQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNextQuestionActionPerformed
        if (!running) {
            running = true;
            timer.start();
            amountOfRightVariants = 0;
            setVisibleComponents(true);
            writeToLog("начал тест");
            previous = iteratorQuestion.next();
            makeFrame(previous);
        } else {
            UserInput userInput = null;
            switch (previous.getType()) {
                case Question11.CLASS_TYPE:
                    userInput = InputFactory.createInput11(textFieldForInputAnswer.getText());
                    break;
                case QuestionN1.CLASS_TYPE:
                    userInput = InputFactory.createInputN1(listOfRadioButtons);
                    break;
                case QuestionNK.CLASS_TYPE:
                    userInput = InputFactory.createInputNK(listOfCheckBoxes);
                    break;
            }
            if (previous.check(userInput)) {
                answers[currentQuestionNumber]
                        = new Variant(previous.getTitle(), AnswerType.RIGHT);
                amountOfRightVariants++;
            } else {
                answers[currentQuestionNumber]
                        = new Variant(previous.getTitle(), AnswerType.WRONG);
            }
            int numberToOut = currentQuestionNumber + 1;
            writeToLog("ответил на вопрос № " + numberToOut);
            currentQuestionNumber++;

            if (iteratorQuestion.hasNext()) {
                previous = iteratorQuestion.next();
                makeFrame(previous);
            } else {
                stopTest();
            }
        }

    }//GEN-LAST:event_buttonNextQuestionActionPerformed

    private void confirmExit() {
        if (running) {
            if (JOptionPane.showConfirmDialog(null, "Вы ответили не на все вопросы. "
                    + "Вы действительно хотите выйти?", "Подтверждение выхода",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                    == JOptionPane.YES_OPTION) {
                stopTest();
            }
        } else {
            if (JOptionPane.showConfirmDialog(null, "Отменить тест?",
                    "Подтверждение выхода",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                    == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
    }


    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        confirmExit();
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonNextQuestion;
    private javax.swing.JLabel labelQuestionHeader;
    private javax.swing.JLabel labelQuestionNumber;
    private javax.swing.JLabel labelRemainingTime;
    private javax.swing.JPanel panelForVariants;
    // End of variables declaration//GEN-END:variables

}
