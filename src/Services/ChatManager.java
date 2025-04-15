package Services;

import Models.Chat;
import Models.User;

public class ChatManager {
    private static ChatManager instance;
    private Chat currentChat;

    private ChatManager() {}

    public static ChatManager getInstance() {
        if (instance == null) {
            instance = new ChatManager();
        }
        return instance;
    }

    public boolean startNewChat(String chatId) {
        if (currentChat != null && currentChat.isActive()) {
            System.out.println("There is already an active chat.");
            return false;
        }
        currentChat = new Chat(chatId);
        currentChat.startChat();
        return true;
    }

    public void endCurrentChat() {
        if (currentChat != null && currentChat.isActive()) {
            currentChat.endChat();
        } else {
            System.out.println("No active chat to end.");
        }
    }

    public boolean subscribeUser(User user) {
        if (currentChat != null && currentChat.isActive()) {
            currentChat.subscribeUser(user);
            return true;
        } else {
            System.out.println("No active chat to join.");
            return false;
        }
    }

    public void unsubscribeUser(User user) {
        if (currentChat != null && currentChat.isActive()) {
            currentChat.unsubscribeUser(user);
        } else {
            System.out.println("No active chat to leave.");
        }
    }

    public boolean sendMessage(User user, String message) {
        if (currentChat != null && currentChat.isActive()) {
            String formatted = user.getNickname() + ": " + message;
            currentChat.addMessage(formatted);
            System.out.println(formatted);
            return true;
        } else {
            System.out.println("No active chat.");
            return false;
        }
    }

    public boolean isChatActive() {
        return currentChat != null && currentChat.isActive();
    }
}
