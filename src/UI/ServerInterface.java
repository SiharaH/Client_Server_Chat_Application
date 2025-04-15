package UI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {
    void registerClient(ClientInterface client, String nickname) throws RemoteException;
    void unregisterClient(ClientInterface client) throws RemoteException;
    void broadcastMessage(String message, String senderNickname) throws RemoteException;
}
