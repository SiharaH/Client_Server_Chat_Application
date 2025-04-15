package Services;

import Models.ChatMessage;
import Models.User;
import Models.UserClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {

    // Store registered users
//    private final Map<String, User> registeredUsers = new HashMap<>();
    private final Map<String, UserClient> onlineClients = new HashMap<>();

    // Active chat ID and participants
    private String currentChatId = null;
    private final List<User> chatParticipants = new ArrayList<>();

    public ChatServiceImpl() throws RemoteException {
        super();
    }

//    @Override
//    public synchronized void registerUser(User user) throws RemoteException {
//        if (registeredUsers.containsKey(user.getEmail())) {
//            System.out.println("User already registered: " + user.getEmail());
//        } else {
//            registeredUsers.put(user.getEmail(), user);
//            System.out.println("User registered: " + user.getEmail());
//        }
//    }

//    @Override
//    public synchronized User login(String email, String password) throws RemoteException {
//        User user = registeredUsers.get(email);
//        if (user != null && user.getPassword().equals(password)) {
//            System.out.println("User logged in: " + email);
//            return user;
//        }
//        System.out.println("Login failed for: " + email);
//        return null;
//    }

    @Override
    public synchronized void startChat(String chatId) throws RemoteException {
        if (currentChatId != null) {
            System.out.println("A chat is already active.");
            return;
        }
        currentChatId = chatId;
        chatParticipants.clear();
        System.out.println("Chat started: " + chatId);
    }

    @Override
    public synchronized void sendMessage(String chatId, String message) throws RemoteException {
        if (!chatId.equals(currentChatId)) {
            System.out.println("Invalid chat ID.");
            return;
        }

        for (User user : chatParticipants) {
            UserClient client = onlineClients.get(user.getEmail());
            if (client != null) {
                client.receiveMessage(new ChatMessage("Server", message));
            }
        }
    }

    @Override
    public void subscribeUser(String user) throws RemoteException {

    }

    @Override
    public void unsubscribeUser(String user) throws RemoteException {

    }

//    @Override
//    public synchronized void subscribeUser(String chatId, User user) throws RemoteException {
//        if (!chatId.equals(currentChatId)) {
//            System.out.println("No chat session available.");
//            return;
//        }
//
//        if (!chatParticipants.contains(user)) {
//            chatParticipants.add(user);
//            System.out.println(user.getNickname() + " joined the chat.");
//        }
//    }
//
//    @Override
//    public synchronized void unsubscribeUser(String chatId, User user) throws RemoteException {
//        if (chatParticipants.contains(user)) {
//            chatParticipants.remove(user);
//            System.out.println(user.getNickname() + " left the chat.");
//        }
//
//        if (chatParticipants.isEmpty()) {
//            endChat(chatId);
//        }
//    }

    @Override
    public synchronized void endChat(String chatId) throws RemoteException {
        if (chatId.equals(currentChatId)) {
            currentChatId = null;
            chatParticipants.clear();
            System.out.println("Chat ended: " + chatId);
        }
    }

    // Called by client to register its RMI object
    public synchronized void registerClient(String email, UserClient clientStub) throws RemoteException {
        onlineClients.put(email, clientStub);
        System.out.println("Client registered for callbacks: " + email);
    }
}
