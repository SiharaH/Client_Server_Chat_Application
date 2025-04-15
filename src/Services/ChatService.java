package Services;

import Models.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatService extends Remote {
    void sendMessage(String message, String user) throws RemoteException;


    void subscribeUser(String user) throws RemoteException;
    void unsubscribeUser(String user) throws RemoteException;
    void startChat(String chatId) throws RemoteException;

    void endChat(String chatId) throws RemoteException;
}
