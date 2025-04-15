package Models;

import java.util.HashMap;
import java.util.Map;

public class Admin extends User {

    private static Admin instance = null;

    private Admin(String email, String username, String password, String nickname) {
        super(email, username, password, nickname, true);
    }

    public static Admin getInstance(String email, String username, String password, String nickname) {
        if (instance == null) {
            instance = new Admin(email, username, password, nickname);
        }else {
            System.out.println("Admin already exists. You can't create another one.");
        }
        return instance;
    }
/*
    public void viewAllUsers(HashMap<String, User> users) {
        System.out.println("\n--- Registered Users ---");
        for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
            System.out.println("Email: " + user.getEmail());
            System.out.println("Username: " + user.getUsername());
            System.out.println("NickName: " + user.getNickname());
            System.out.println("------------------------");
        }
    }

    // Remove a user by email
    public void removeUser(String email, HashMap<String, User> users) {
        if (users.containsKey(email)) {
            users.remove(email);
            System.out.println("User with email '" + email + "' has been removed.");
        } else {
            System.out.println("User not found.");
        }
    }
*/
}
