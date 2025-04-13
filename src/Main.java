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
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Username: ");
                        String username = scanner.nextLine();
                        System.out.print("Password: ");
                        String password = scanner.nextLine();
                        System.out.print("Nickname: ");
                        String nickname = scanner.nextLine();
                        System.out.print("Register as Admin? (y/n): ");
                        boolean isAdmin = scanner.nextLine().equalsIgnoreCase("y");

                        userService.registerUser(email, username, password, nickname, isAdmin);
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
                                while (true) {
                                    System.out.println("\n--- Admin Dashboard ---");
                                    System.out.println("1. View Users");
                                    System.out.println("2. Remove User");
                                    System.out.println("0. Logout");
                                    System.out.print("Enter choice: ");
                                    String adminChoice = scanner.nextLine();

                                    if (adminChoice.equals("1")) {
                                        userService.printAllUsers();
                                    } else if (adminChoice.equals("2")) {
                                        System.out.print("Enter email to remove: ");
                                        String removeEmail = scanner.nextLine();
                                        userService.removeUser(removeEmail);
                                    } else if (adminChoice.equals("0")) {
                                        break;
                                    } else {
                                        System.out.println("Invalid choice.");
                                    }
                                }
                            } else {
                                System.out.println("You are logged in as a User.");
                            }
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