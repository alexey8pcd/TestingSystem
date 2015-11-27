package main.represent;

import java.awt.Color;
import static main.Parameters.PASSWORD_LENGTH;
import static main.Parameters.SCREEN_SIZE;
import main.management.User;

/**
 *
 * @author Алексей
 */
public class EditUserForm extends javax.swing.JDialog {

    private User user;
    private boolean accepted;

    public EditUserForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        labelWarning.setText(null);
        user = null;
        accepted = false;
    }

    public User getUser() {
        return user;
    }

    public boolean accepted() {
        return accepted;
    }

    public void setUser(User user) {
        textFieldLogin.setText(user.getLogin());
        passwordField.setText(user.getPassword());
        passwordConfirmField.setText(user.getPassword());
        checkBoxCanCreateTasks.setSelected(user.isTeacher());
        if (user.isAdmin()) {
            checkBoxCanCreateTasks.setEnabled(false);
        }
    }

    /**
     * Сравнивает пароль и подтверждение
     *
     * @return true - если пароли совпадают, false - в ином случае
     *
     */
    private boolean comparePasswordAndComfirm() {
        String password = String.valueOf(passwordField.getPassword());
        String confirmPassword = String.valueOf(passwordConfirmField.getPassword());
        if (!password.equalsIgnoreCase(confirmPassword)) {
            passwordConfirmField.setBackground(Color.red);
            return false;
        } else {
            passwordConfirmField.setBackground(passwordField.getBackground());
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLogin = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        textFieldLogin = new javax.swing.JTextField();
        labelConfirmPassword = new javax.swing.JLabel();
        checkBoxCanCreateTasks = new javax.swing.JCheckBox();
        buttonCancel = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        passwordConfirmField = new javax.swing.JPasswordField();
        buttonCreate = new javax.swing.JButton();
        labelWarning = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Редактировать данные о пользователе");

        labelLogin.setText("<html><h3>Имя пользователя:");

        labelPassword.setText("<html><h3>Пароль:");

        labelConfirmPassword.setText("<html><h3>Подтверждение:");

        checkBoxCanCreateTasks.setText("<html><h3>Может создавать задания");

        buttonCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/reject.png"))); // NOI18N
        buttonCancel.setText("Отменить");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordFieldKeyReleased(evt);
            }
        });

        passwordConfirmField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordConfirmFieldKeyReleased(evt);
            }
        });

        buttonCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/accept.png"))); // NOI18N
        buttonCreate.setText("Создать");
        buttonCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateActionPerformed(evt);
            }
        });

        labelWarning.setText("Предупреждение");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldLogin)
                    .addComponent(passwordField)
                    .addComponent(passwordConfirmField)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 47, Short.MAX_VALUE)
                        .addComponent(buttonCreate)
                        .addGap(18, 18, 18)
                        .addComponent(buttonCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkBoxCanCreateTasks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordConfirmField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBoxCanCreateTasks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelWarning, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCreate)
                    .addComponent(buttonCancel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateActionPerformed
        boolean correctInfo = true;
        if (this.textFieldLogin.getText().isEmpty()) {
            labelWarning.setText("<html><font color=red>Имя пользователя "
                    + "не может быть пустым");
            correctInfo = false;
        }
        if (this.passwordField.getPassword().length < PASSWORD_LENGTH) {
            labelWarning.setText("<html><font color=red>Длина пароля не "
                    + "может быть меньше " + PASSWORD_LENGTH + " символов");
            correctInfo = false;
        }
        if (!comparePasswordAndComfirm()) {
            labelWarning.setText("<html><font color=red>Подтверждение "
                    + "пароля и пароль не совпадают");
            correctInfo = false;
        }
        if (correctInfo) {
            user = new User(textFieldLogin.getText(),
                    String.valueOf(passwordField.getPassword()),
                    checkBoxCanCreateTasks.isSelected() == true
                            ? User.Privileges.TEACHER
                            : User.Privileges.STUDENT);
            accepted = true;
            dispose();
        }
    }//GEN-LAST:event_buttonCreateActionPerformed

    private void passwordConfirmFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordConfirmFieldKeyReleased
        comparePasswordAndComfirm();
    }//GEN-LAST:event_passwordConfirmFieldKeyReleased

    private void passwordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyReleased
        comparePasswordAndComfirm();
    }//GEN-LAST:event_passwordFieldKeyReleased

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        accepted = false;
        dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonCreate;
    private javax.swing.JCheckBox checkBoxCanCreateTasks;
    private javax.swing.JLabel labelConfirmPassword;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelWarning;
    private javax.swing.JPasswordField passwordConfirmField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField textFieldLogin;
    // End of variables declaration//GEN-END:variables

}
