public class Utils {
    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void sendAckMessages(int senderNodeId, boolean[] receivedAck) {
        for (int i = 0; i < receivedAck.length; i++) {
            if (i != senderNodeId) {
                receivedAck[i] = false;
            }
        }
    }

    public static void waitForAcknowledge(boolean[] receivedAck) {
        boolean allAcknowledged = false;
        while (!allAcknowledged) {
            allAcknowledged = true;
            for (boolean ack : receivedAck) {
                if (!ack) {
                    allAcknowledged = false;
                    break;
                }
            }
        }
    }
}
