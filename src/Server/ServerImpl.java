package Server;

import UI.ClientInterface;
import UI.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
    // Thread-safe map of connected clients
    private final Map<String, ClientInterface> clients = new ConcurrentHashMap<>();

    public ServerImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void registerClient(ClientInterface client, String nickname) throws RemoteException {
        clients.put(nickname, client);
        broadcastMessage(nickname + " has joined the chat.", "Server");
    }

    @Override
    public synchronized void unregisterClient(ClientInterface client) throws RemoteException {
        String nicknameToRemove = null;
        for (Map.Entry<String, ClientInterface> entry : clients.entrySet()) {
            if (entry.getValue().equals(client)) {
                nicknameToRemove = entry.getKey();
                break;
            }
        }

        if (nicknameToRemove != null) {
            clients.remove(nicknameToRemove);
            broadcastMessage(nicknameToRemove + " has left the chat.", "Server");
        }
    }

    @Override
    public synchronized void broadcastMessage(String message, String senderNickname) throws RemoteException {
        String formattedMessage = "[" + senderNickname + "]: " + message;
        for (ClientInterface client : clients.values()) {
            new Thread(() -> {
                try {
                    client.receiveMessage(formattedMessage);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}