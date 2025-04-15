package Client;

import UI.ClientInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {
    private String nickname;

    public ClientImpl(String nickname) throws RemoteException {
        this.nickname = nickname;
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    public String getNickname() {
        return nickname;
    }
}
