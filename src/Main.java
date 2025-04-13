import Models.User;
import Services.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            UserService userService = new UserService();
            Scanner scanner = new Scanner(System.in);

            System.out.println("=== Welcome to the Chat Application ===");

            while (true) {
                System.out.println("\n1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Select option: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Are you registering as Admin? (Y/N): ");
                        String isAdmin = scanner.nextLine().trim().toUpperCase();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Username: ");
                        String username = scanner.nextLine();
                        System.out.print("Password: ");
                        String password = scanner.nextLine();
                        System.out.print("Nickname: ");
                        String nickname = scanner.nextLine();

                        if (isAdmin.equals("Y")) {
                            userService.registerAdmin(email, username, password, nickname);
                        } else {
                            userService.registerUser(email, username, password, nickname);
                        }
                        break;

                    case "2":
                        System.out.print("Email: ");
                        String loginEmail = scanner.nextLine();
                        System.out.print("Password: ");
                        String loginPassword = scanner.nextLine();

                        User loggedUser = userService.login(loginEmail, loginPassword);
                        if (loggedUser != null) {
                            System.out.println("Login successful. Welcome " + loggedUser.getNickname() + "!");
                            if (loggedUser.isAdmin()) {
                                System.out.println("You are logged in as Admin.");

                            } else {
                                System.out.println("You are logged in as User.");
                            }
                        } else {
                            System.out.println("Login failed. Invalid email or password.");
                        }
                        break;

                    case "3":
                        System.out.println("Exiting application.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
    }
}