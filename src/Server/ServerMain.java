package Server;

import Services.ChatServiceImpl;
import Services.ChatService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerMain {
    public static void main(String[] args) {
        try {
            // Start RMI registry on port 1099
            LocateRegistry.createRegistry(1099);
            System.out.println("RMI Registry started on port 1099");

            // Create service implementation instance
            ChatService chatService = new ChatServiceImpl();

            // Bind the service to the RMI registry
            Naming.rebind("rmi://localhost/ChatService", chatService);
            System.out.println("ChatService is bound and ready.");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
