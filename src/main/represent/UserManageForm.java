package main.represent;

import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.JOptionPane;
import static main.Parameters.SCREEN_SIZE;
import main.management.User;
import main.management.UserManager;

/**
 *
 * @author Алексей
 */
public class UserManageForm extends javax.swing.JDialog {

    private final List<User> users;
    private boolean changeUser;

    private class UserListModel extends AbstractListModel {

        @Override
        public int getSize() {
            if (users != null) {
                return users.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getElementAt(int index) {
            if (users != null) {
                return users.get(index).toString();
            } else {
                return null;
            }
        }

    }
    private final UserListModel listModel;

    public UserManageForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
                SCREEN_SIZE.height / 2 - getHeight() / 2);
        users = UserManager.loadAllUsers();
        listModel = new UserListModel();
        listUsers.setModel(listModel);
        listUsers.updateUI();
        changeUser = false;
    }

    public boolean isChangeUser() {
        return changeUser;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelForUserInfo = new javax.swing.JPanel();
        scrollPaneForUserList = new javax.swing.JScrollPane();
        listUsers = new javax.swing.JList();
        buttonCreateUser = new javax.swing.JButton();
        buttonDeleteUser = new javax.swing.JButton();
        buttonEditUser = new javax.swing.JButton();
        buttonSettings = new javax.swing.JButton();
        buttonSwitchUsers = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Пользователи");

        panelForUserInfo.setBackground(new java.awt.Color(0, 204, 204));
        panelForUserInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Пользователи", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        listUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPaneForUserList.setViewportView(listUsers);

        buttonCreateUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/userAdd.png"))); // NOI18N
        buttonCreateUser.setToolTipText("Добавить пользователя");
        buttonCreateUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonCreateUser.setDoubleBuffered(true);
        buttonCreateUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCreateUserActionPerformed(evt);
            }
        });

        buttonDeleteUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/userDelete.png"))); // NOI18N
        buttonDeleteUser.setToolTipText("Удалить пользователя");
        buttonDeleteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteUserActionPerformed(evt);
            }
        });

        buttonEditUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/userEdit.png"))); // NOI18N
        buttonEditUser.setToolTipText("Редактировать пользователя");
        buttonEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditUserActionPerformed(evt);
            }
        });

        buttonSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/settings.png"))); // NOI18N
        buttonSettings.setToolTipText("Настройки");
        buttonSettings.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonSettings.setDoubleBuffered(true);
        buttonSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSettingsActionPerformed(evt);
            }
        });

        buttonSwitchUsers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/userSwitch.png"))); // NOI18N
        buttonSwitchUsers.setToolTipText("Сменить пользователя");
        buttonSwitchUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonSwitchUsers.setDoubleBuffered(true);
        buttonSwitchUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSwitchUsersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelForUserInfoLayout = new javax.swing.GroupLayout(panelForUserInfo);
        panelForUserInfo.setLayout(panelForUserInfoLayout);
        panelForUserInfoLayout.setHorizontalGroup(
            panelForUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelForUserInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelForUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPaneForUserList)
                    .addGroup(panelForUserInfoLayout.createSequentialGroup()
                        .addComponent(buttonCreateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSwitchUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelForUserInfoLayout.setVerticalGroup(
            panelForUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelForUserInfoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelForUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCreateUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelForUserInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(buttonSettings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonSwitchUsers, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(scrollPaneForUserList, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelForUserInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelForUserInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCreateUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCreateUserActionPerformed
        User user = UserManager.createUser(null);
        if (user != null) {
            JOptionPane.showMessageDialog(null, "Пользователь создан",
                    "Информация", JOptionPane.INFORMATION_MESSAGE);
            users.add(user);
            UserManager.saveAllUsers(users);
            listUsers.updateUI();
        } else {
            JOptionPane.showMessageDialog(null, "Не удалось создать пользователя",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonCreateUserActionPerformed

    private void buttonDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteUserActionPerformed
        if (listUsers.getSelectedIndex() != -1) {
            User user = users.get(listUsers.getSelectedIndex());
            if (!user.isAdmin()) {
                users.remove(user);
                UserManager.saveAllUsers(users);
                listUsers.updateUI();
            } else {
                JOptionPane.showMessageDialog(null, "Нельзя удалить пользователя"
                        + " с правами администратора", "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_buttonDeleteUserActionPerformed

    private void buttonEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditUserActionPerformed
        int selectedIndex = listUsers.getSelectedIndex();
        if (selectedIndex != -1) {
            User user = UserManager.createUser(users.get(selectedIndex));
            if (user != null) {
                JOptionPane.showMessageDialog(null,
                        "Информация о пользователе изменена",
                        "Информация", JOptionPane.INFORMATION_MESSAGE);
                users.set(selectedIndex, user);
                UserManager.saveAllUsers(users);
                listUsers.updateUI();
            }
        }
    }//GEN-LAST:event_buttonEditUserActionPerformed

    private void buttonSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSettingsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSettingsActionPerformed

    private void buttonSwitchUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSwitchUsersActionPerformed
        changeUser = true;
        dispose();
    }//GEN-LAST:event_buttonSwitchUsersActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCreateUser;
    private javax.swing.JButton buttonDeleteUser;
    private javax.swing.JButton buttonEditUser;
    private javax.swing.JButton buttonSettings;
    private javax.swing.JButton buttonSwitchUsers;
    private javax.swing.JList listUsers;
    private javax.swing.JPanel panelForUserInfo;
    private javax.swing.JScrollPane scrollPaneForUserList;
    // End of variables declaration//GEN-END:variables

}
