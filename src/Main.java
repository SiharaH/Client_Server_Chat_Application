import Models.Admin;
import Models.User;
import Services.UserService;
import Services.ChatManager;

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
                                ChatManager chatManager = ChatManager.getInstance();

                                while (true) {
                                    System.out.println("\n--- Admin Dashboard ---");
                                    System.out.println("1. View Users");
                                    System.out.println("2. Remove User");
                                    System.out.println("3. Update Profile");
                                    System.out.println("4. Create Chat");
                                    System.out.println("5. Subscribe Users to Chat");
                                    System.out.println("6. Unsubscribe User from Chat");
                                    System.out.println("0. Logout");
                                    System.out.print("Enter choice: ");
                                    String adminChoice = scanner.nextLine();

                                    switch (adminChoice) {
                                        case "1":
                                            userService.printAllUsers();
                                            break;
                                        case "2":
                                            System.out.print("Enter email to remove: ");
                                            String removeEmail = scanner.nextLine();
                                            userService.removeUser(removeEmail);
                                            break;
                                        case "3":
                                            loggedUser.updateProfile(scanner);
                                            break;
                                        case "4":
                                            System.out.print("Enter Chat ID to start: ");
                                            String chatId = scanner.nextLine();
                                            chatManager.startNewChat(chatId);
                                            break;
                                        case "5":
                                            chatManager.endCurrentChat();
                                            break;
                                        case "0":
                                            System.out.println("Admin logged out.");
                                            return;
                                        default:
                                            System.out.println("Invalid choice.");
                                            break;
                                    }
                                }

                            }
                            } else {
                            ChatManager chatManager = ChatManager.getInstance();
                            chatManager.subscribeUser(loggedUser);

                            while (true) {
                                System.out.println("\n=== User Dashboard ===");
                                System.out.println("1. Update Profile");
                                System.out.println("2. Send Message");
                                System.out.println("3. Leave Chat & Logout");
                                System.out.print("Enter choice: ");
                                String userChoice = scanner.nextLine();

                                switch (userChoice) {
                                    case "1":
                                        loggedUser.updateProfile(scanner);
                                        break;
                                    case "2":
                                        if (chatManager.isChatActive()) {
                                            System.out.print("Enter message: ");
                                            String message = scanner.nextLine();
                                            chatManager.sendMessage(loggedUser, message);
                                        } else {
                                            System.out.println("No active chat session.");
                                        }
                                        break;
                                    case "3":
                                        chatManager.unsubscribeUser(loggedUser);
                                        System.out.println("User logged out.");
                                        break;
                                    default:
                                        System.out.println("Invalid choice.");
                                        break;
                                }
                            }

                        }
                        break;

                    case "3":
                        System.out.println("Exiting application.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
    }
}