package Client;

import UI.ClientInterface;
import UI.ServerInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ClientApp extends UnicastRemoteObject implements ClientInterface {
    private final String nickname;
    private final ServerInterface server;

    public ClientApp(String nickname, ServerInterface server) throws RemoteException {
        super();
        this.nickname = nickname;
        this.server = server;
        server.registerClient(this, nickname);
    }

    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println(message);
    }

    public void startChat() throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Start chatting, " + nickname + " ===");
        System.out.println("Type 'exit' to leave the chat.");

        while (true) {
            String msg = scanner.nextLine();
            if (msg.equalsIgnoreCase("exit")) {
                server.unregisterClient(this);
                System.out.println("You left the chat.");
                break;
            }
            server.broadcastMessage(msg, nickname);
        }

        scanner.close();
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your nickname: ");
            String nickname = scanner.nextLine();

            ServerInterface server = (ServerInterface) Naming.lookup("rmi://localhost/ChatServer");

            ClientApp client = new ClientApp(nickname, server);
            client.startChat();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
