package main.management;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Author Alexey
 */
public class User {

    public boolean isTeacher() {
        return privileges == Privileges.TEACHER;
    }

    public boolean isAdmin() {
        return privileges == Privileges.ADMIN;
    }

    public static enum Privileges {

        ADMIN,//создает пользователей, не может создавать задания или проходить их
        TEACHER,//создает задания и может их проходить
        STUDENT//может только проходить задания
    }

    private final Privileges privileges;
    private String login;
    private String password;
    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    public static final User ILLEGAL_USER = new User(null, null, null);

    public User(String login, String password, Privileges privileges) {
        this.login = login;
        this.password = password;
        this.privileges = privileges;
    }

    public static User createUser(DataInputStream inputStream) throws IOException {
        String userName = inputStream.readUTF();
        String password = UserManager.decrypt(inputStream.readUTF());
        Privileges privileges = Privileges.valueOf(inputStream.readUTF());
        return new User(userName, password, privileges);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        STRING_BUILDER.setLength(0);
        STRING_BUILDER.append(login);
        STRING_BUILDER.append("; ");
        STRING_BUILDER.append(privileges.name());
        return STRING_BUILDER.toString();
    }

    public void save(DataOutputStream writer) throws IOException {
        writer.writeUTF(login);
        writer.writeUTF(UserManager.encrypt(password));
        writer.writeUTF(privileges.name());
    }

    public boolean isSimilar(User user) {
        return user.login.equalsIgnoreCase(login);
    }

}
