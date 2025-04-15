package Models;

public class ChatMessage {

    private String sender;
    private String message;
    private long timestamp;

    // Constructor
    public ChatMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters
    public String getSender() { return sender; }
    public String getMessage() { return message; }
    public long getTimestamp() { return timestamp; }

    // To display the message in a readable format
    @Override
    public String toString() {
        return "[" + timestamp + "] " + sender + ": " + message;
    }
}
