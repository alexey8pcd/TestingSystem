package main.represent;

import java.awt.event.KeyEvent;
import static main.Parameters.*;
import main.management.User;
import main.management.UserManager;

/**
 *
 * @author Алексей
 */
public class LoginForm extends javax.swing.JDialog {

    private User user;

    public LoginForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        labelWarning.setText("");
    }

    public User getUser() {
        return user;
    }

    private void input() {
        boolean inputIsCorrect = true;
        String enteredUserName = textFieldLogin.getText();
        char[] enteredPassword = textFieldPassword.getPassword();
        if (enteredUserName.isEmpty() || enteredUserName.startsWith(" ")) {
            labelWarning.setText("<html><font color=red>Имя пользователя<br>"
                    + " не может быть пустым");
            inputIsCorrect = false;
        }
        if (inputIsCorrect && enteredPassword.length < PASSWORD_LENGTH) {
            labelWarning.setText("<html><font color=red>Длина пароля не<br>"
                    + " может быть меньше " + PASSWORD_LENGTH + " символов");
            inputIsCorrect = false;
        }
        if (inputIsCorrect) {
            user = UserManager.loadUser(enteredUserName, String.valueOf(enteredPassword));
            if (user == User.ILLEGAL_USER) {
                labelWarning.setText("<html><font color=red>Такого пользователя не создано");
            } else {
                dispose();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLogin = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        textFieldLogin = new javax.swing.JTextField();
        textFieldPassword = new javax.swing.JPasswordField();
        buttonExit = new javax.swing.JButton();
        buttonEnter = new javax.swing.JButton();
        labelWarning = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Подтверждение входа");

        labelLogin.setText("<html><h3>Введите имя пользователя:");

        labelPassword.setText("<html><h3>Введите пароль:");

        textFieldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldPasswordKeyPressed(evt);
            }
        });

        buttonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/reject.png"))); // NOI18N
        buttonExit.setText("Выйти");
        buttonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExitActionPerformed(evt);
            }
        });

        buttonEnter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/accept.png"))); // NOI18N
        buttonEnter.setText("Войти");
        buttonEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnterActionPerformed(evt);
            }
        });

        labelWarning.setText("Предупреждение");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldLogin)
                            .addComponent(textFieldPassword)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(labelWarning, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonEnter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonExit)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonExit)
                        .addComponent(buttonEnter))
                    .addComponent(labelWarning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEnterActionPerformed
        input();
    }//GEN-LAST:event_buttonEnterActionPerformed

    private void buttonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExitActionPerformed
        dispose();
    }//GEN-LAST:event_buttonExitActionPerformed

    private void textFieldPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            input();
        }
    }//GEN-LAST:event_textFieldPasswordKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEnter;
    private javax.swing.JButton buttonExit;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelWarning;
    private javax.swing.JTextField textFieldLogin;
    private javax.swing.JPasswordField textFieldPassword;
    // End of variables declaration//GEN-END:variables
}
