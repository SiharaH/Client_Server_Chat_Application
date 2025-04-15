package Models;


import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class User {
    protected String email;
    protected String username;
    protected String password;
    protected String nickname;
    private boolean isAdmin;
    private Set<String> subscribedChats;

    public User(String email, String username, String password, String nickname, boolean isAdmin) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.isAdmin = isAdmin;
        this.subscribedChats = new HashSet<>();
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void updateProfile(Scanner scanner) {
        System.out.print("New Username: ");
        setUsername(scanner.nextLine());
        System.out.print("New Password: ");
        setPassword(scanner.nextLine());
        System.out.print("New Nick Name: ");
        setNickname(scanner.nextLine());
//        System.out.print("New Profile Picture (filename): ");
//        setProfilePicture(scanner.nextLine());

        System.out.println("Profile updated successfully!");
    }

    // Subscribe to a chat
    public void subscribeToChat(String chatId) {
        subscribedChats.add(chatId);
    }

    // Unsubscribe from a chat
    public void unsubscribeFromChat(String chatId) {
        subscribedChats.remove(chatId);
    }

    public Set<String> getSubscribedChats() {
        return subscribedChats;
    }

}
