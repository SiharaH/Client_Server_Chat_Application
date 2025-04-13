package Models;

import java.io.File;

public class Admin extends User {

    private static Admin instance = null;

    private Admin(String email, String username, String password, String nickname) {
        super(email, username, password, nickname);
    }

    public static Admin getInstance(String email, String username, String password, String nickname) {
        if (instance == null) {
            instance = new Admin(email, username, password, nickname);
        }
        return instance;
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
