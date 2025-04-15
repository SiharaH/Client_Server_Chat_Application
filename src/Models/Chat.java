package Models;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Chat {
    private String chatId;
    private Set<User> subscribedUsers;
    private List<String> messages;
    private boolean isActive;
    private LocalDateTime startedAt;
    private LocalDateTime stoppedAt;

    public Chat(String chatId) {
        this.chatId = chatId;
        this.subscribedUsers = new HashSet<>();
        this.messages = new ArrayList<>();
        this.isActive = false;
    }

    public void subscribeUser(User user) {
        subscribedUsers.add(user);
        System.out.println("\"" + user.getNickname() + "\" has joined : " + getCurrentTime());
    }

    public void unsubscribeUser(User user) {
        subscribedUsers.remove(user);
        System.out.println("\"" + user.getNickname() + "\" left : " + getCurrentTime());
    }

    public void startChat() {
        this.isActive = true;
        this.startedAt = LocalDateTime.now();
        System.out.println("Chat started at: " + getCurrentTime());
    }

    public void endChat() {
        this.isActive = false;
        this.stoppedAt = LocalDateTime.now();
        System.out.println("Chat stopped at: " + getCurrentTime());
        saveChatToFile();
    }

    public boolean isActive() {
        return isActive;
    }

    public Set<User> getSubscribedUsers() {
        return subscribedUsers;
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public void saveChatToFile() {
        // you can implement this next step to write messages to a file
    }

    private String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
