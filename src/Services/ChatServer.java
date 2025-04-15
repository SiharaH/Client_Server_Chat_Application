package Services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ChatServer extends UnicastRemoteObject implements ChatService {

    private Map<String, String> users;  // Use HashMap for user info (key: username, value: status)
    private Map<Integer, String> chatHistory;  // Use HashMap for storing messages with chat ID

    public ChatServer() throws RemoteException {
        super();
        users = new HashMap<>();  // Initializing the HashMap for users
        chatHistory = new HashMap<>();  // Initializing the HashMap for chat messages
    }

    @Override
    public void sendMessage(String message, String user) throws RemoteException {
        String chatMessage = user + ": " + message;
        int chatId = chatHistory.size() + 1; // Simple way to assign a unique chat ID
        chatHistory.put(chatId, chatMessage);  // Store the message with the chatId
        System.out.println(chatMessage);
    }

    @Override
    public void subscribeUser(String user) throws RemoteException {

    }

    @Override
    public void unsubscribeUser(String user) throws RemoteException {

    }


    @Override
    public void startChat(String chatId) throws RemoteException {

    }

    @Override
    public void endChat(String chatId) throws RemoteException {

    }
}
