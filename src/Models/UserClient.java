package Models;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserClient extends Remote {
    // Notify the client about new messages
    void receiveMessage(ChatMessage message) throws RemoteException;

    // Notify the client when a new user joins the chat
    void userJoined(String username) throws RemoteException;

    // Notify the client when a user leaves the chat
    void userLeft(String username) throws RemoteException;
}
