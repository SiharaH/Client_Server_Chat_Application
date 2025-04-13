package Services;

import Models.User;
import Models.Admin;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> users = new ArrayList<>();

    public boolean registerUser(String email, String username, String password, String nickname) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Email already registered.");
                return false;
            }
        }
        User newUser = new User(email, username, password, nickname);
        users.add(newUser);
        System.out.println("User registered successfully.");
        return true;
    }

    public boolean registerAdmin(String email, String username, String password, String nickname) {
        for (User user : users) {
            if (user instanceof Admin) {
                System.out.println("Admin already exists.");
                return false;
            }
        }
        Admin admin = Admin.getInstance(email, username, password, nickname);
        users.add(admin);
        System.out.println("Admin registered successfully.");
        return true;
    }

    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
