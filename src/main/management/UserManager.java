package main.management;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import main.management.User.Privileges;
import main.represent.EditUserForm;

/**
 * Author Alexey Сохраняет в файл информацию о пользователе Загружает из файла
 * информацию о пользователе
 */
public class UserManager {

    public static final String PATH = "./users.dat";

    public static List<User> loadAllUsers() {
        List<User> users = new ArrayList<>();
        try (DataInputStream reader = new DataInputStream(
                new FileInputStream(new File(PATH)))) {
            while (reader.available() > 0) {
                users.add(User.createUser(reader));
            }
            return users;
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }

    public static User loadUser(String enteredUserName, String enteredPassword) {
        User user;
        try {
            DataInputStream inputStream = new DataInputStream(
                    new FileInputStream(new File(PATH)));
            while (inputStream.available() > 0) {
                user = User.createUser(inputStream);
                if (enteredUserName.equalsIgnoreCase(user.getLogin())
                        && enteredPassword.equalsIgnoreCase(user.getPassword())) {
                    inputStream.close();
                    return user;
                }
            }
        } catch (FileNotFoundException fileNotFoundEx) {
            //файла с пользователями не существует, создаем новый файл
            int result = JOptionPane.showConfirmDialog(null,
                    "<html>Не найдено данных о пользователе."
                    + "<br> Создать пользователя с таким именем и паролем<br>"
                    + "(Он будет администратором)?",
                    "Предложение", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                try (DataOutputStream writer = new DataOutputStream(
                        new FileOutputStream(new File(PATH)))) {
                    user = new User(enteredUserName, enteredPassword, Privileges.ADMIN);
                    user.save(writer);
                    return user;
                } catch (Exception ex1) {
                    JOptionPane.showMessageDialog(null,
                            "<html>Ошибка при сохранении данных пользователя, "
                            + "возможные причины:<br>"
                            + "1) Недостаточно места на диске<br>"
                            + "2) Нет прав на запись на данный диск<br>"
                            + "3) Ошибка чтения/записи данных", "Ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "<html>Ошибка чтения данных из"
                    + "файла users.dat<br>Возможно файл был некорректно сохранен,"
                    + "или поврежден.");
        }
        return User.ILLEGAL_USER;
    }

    public static String encrypt(String string) {
        String prefix = string.substring(0, 2);
        String suffix = string.substring(2) + prefix;
        char[] chars = suffix.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            int charValue = Short.MAX_VALUE * 2 - chars[i];
            if (charValue % 2 == 0) {
                ++charValue;
            } else {
                --charValue;
            }
            chars[i] = (char) charValue;
        }
        return new String(chars);
    }

    public static String decrypt(String string) {
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            int charValue = chars[i];
            if (charValue % 2 == 0) {
                ++charValue;
            } else {
                --charValue;
            }
            chars[i] = (char) (Short.MAX_VALUE * 2 - charValue);
        }
        String s = new String(chars);
        String prefix = s.substring(0, s.length() - 2);
        String suffix = s.substring(s.length() - 2);
        return suffix + prefix;
    }

    public static void saveAllUsers(List<User> users) {
        try (DataOutputStream writer = new DataOutputStream(
                new FileOutputStream(new File(PATH)))) {
            for (User user : users) {
                user.save(writer);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
    }

    /**
     * Проверяет на существование пользователя
     *
     * @param user - проверяемый
     * @param users - среди кого проверяется
     * @return false, если не существует, true - если существует
     */
    public static boolean isCreated(User user, List<User> users) {
        if (users == null || user == null) {
            return false;
        } else {
            boolean founded = false;
            for (User user1 : users) {
                if (user1.isSimilar(user)) {
                    founded = true;
                    break;
                }
            }
            return founded;
        }
    }

    public static User createUser(User userForEdit) {
        List<User> users = loadAllUsers();
        User user = null;
        boolean accepted;
        do {
            EditUserForm editForm = new EditUserForm(null, true);
            editForm.setTitle("Редактировать пользователя");
            boolean edit;
            if (userForEdit != null) {
                editForm.setUser(userForEdit);
                edit = true;
            }else{
                edit = false;
            }
            editForm.setVisible(true);
            user = editForm.getUser();
            accepted = editForm.accepted();
            if (!edit && isCreated(user, users)) {
                user = null;
                JOptionPane.showMessageDialog(null,
                        "Такой пользователь уже существует", "Ошибка", 
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (user == null && accepted);
        return user;
    }

}
