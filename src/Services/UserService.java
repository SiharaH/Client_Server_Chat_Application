package Services;

import Models.User;
import Models.Admin;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users = new HashMap<>();
    private User adminInstance;

    public boolean registerUser(String email, String username, String password, String nickname, boolean isAdmin) {
        if (users.containsKey(email)) {
            System.out.println("User already exists with this email.");
            return false;
        }

        if (isAdmin) {
            if (adminInstance != null) {
                System.out.println("Admin already exists.");
                return false;
            }
            adminInstance = new User(email, username, password, nickname, true);
            users.put(email, adminInstance);
            System.out.println("Admin registered successfully.");
            return true;
        }
        User user = new User(email, username, password, nickname, false);
        users.put(email, user);
        System.out.println("User registered successfully.");
        return true;
    }

    public User login(String email, String password) {
        if (!users.containsKey(email)) {
            System.out.println("No user found");
            return null;
        }

        User user = users.get(email);
        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password.");
            return null;
        }
        return user;
    }

    public void removeUser(String email) {
        if (!users.containsKey(email)) {
            System.out.println("User not found.");
            return;
        }

        User user = users.get(email);
        if (user.isAdmin()) {
            System.out.println("Cannot remove the admin.");
            return;
        }

        users.remove(email);
        System.out.println("User removed successfully.");
    }

    public void printAllUsers() {
        System.out.println("\n--- Registered Users ---");
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        for (User user : users.values()) {
            String role = user.isAdmin() ? "Admin" : "User";
            System.out.println("- " + user.getUsername() + user.getNickname() + " | " + user.getEmail() + " | " + role);
        }
    }
}
