package main;

import main.management.User;
import main.represent.LoginForm;
import main.represent.MainForm;
import main.represent.UserManageForm;

public class Program {

    private static User currentUser;

    public static void main(String[] args) {
//        UIManager.put("Yes", "Да");
//        UIManager.put("No", "Нет");
//        UIManager.put("Cancel", "Отменить");
        do {
            LoginForm loginForm = new LoginForm(null, true);
            loginForm.setVisible(true);
            currentUser = loginForm.getUser();
            if (currentUser != User.ILLEGAL_USER) {
                if (currentUser.isAdmin()) {
                    UserManageForm manageForm;
                    manageForm = new UserManageForm(null, true);
                    manageForm.setVisible(true);
                    if (!manageForm.isChangeUser()) {
                        currentUser = null;
                    }
                } else {
                    MainForm mainForm = MainForm.getInstance(currentUser);
                    mainForm.setVisible(true);
                    currentUser = null;
                }
            }
        } while (currentUser != null && currentUser != User.ILLEGAL_USER);

    }

}
