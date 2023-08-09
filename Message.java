import java.io.Serializable;

public class Message implements Serializable {
    private int senderNodeId;

    public Message(int senderNodeId) {
        this.senderNodeId = senderNodeId;
    }

    public int getSenderNodeId() {
        return senderNodeId;
    }
}
