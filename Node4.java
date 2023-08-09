import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Node4 extends UnicastRemoteObject implements Node {
    private int nodeId;
    private int numNodes;
    private int[] distances;
    private Edge[] edges;
    private boolean[] receivedAck;

    public Node4(int nodeId, int numNodes, Edge[] edges) throws RemoteException {
        this.nodeId = nodeId;
        this.numNodes = numNodes;
        this.edges = edges;
        distances = new int[numNodes];
        receivedAck = new boolean[numNodes];
        for (int i = 0; i < numNodes; i++) {
            distances[i] = (i == nodeId) ? 0 : Integer.MAX_VALUE;
            receivedAck[i] = false;
        }
    }

    public static void main(String[] args) {
        try {
            int numNodes = 4;
            Edge[] edges = {
                new Edge(0, 1, 1),
                new Edge(0, 2, 5),
                new Edge(1, 2, 2),
                new Edge(1, 3, 4),
                new Edge(2, 3, 1),
                new Edge(3, 0, -3)
            };

            Node4 node = new Node4(0, numNodes, edges);
            Naming.rebind("rmi://localhost/Node4", node);

            System.out.println("Node 1 initialized.");
            node.runSynchronousBellmanFord();
            System.out.println("Node 1 Synchronous Shortest Paths: " + Utils.arrayToString(node.getDistances()));

            node.runAsynchronousBellmanFord();
            System.out.println("Node 1 Asynchronous Shortest Paths: " + Utils.arrayToString(node.getDistances()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveMessage(Message message) throws RemoteException {
        int senderNodeId = message.getSenderNodeId();
        receivedAck[senderNodeId] = true;
    }

    @Override
    public int[] getDistances() throws RemoteException {
        return distances;
    }

    private void runSynchronousBellmanFord() {
        for (int i = 0; i < numNodes - 1; i++) {
            for (Edge edge : edges) {
                if (distances[edge.getSource()] != Integer.MAX_VALUE
                        && distances[edge.getSource()] + edge.getWeight() < distances[edge.getDestination()]) {
                    distances[edge.getDestination()] = distances[edge.getSource()] + edge.getWeight();
                }
            }
            Utils.sendAckMessages(nodeId, receivedAck);
            Utils.waitForAcknowledge(receivedAck);
        }
    }

    private void runAsynchronousBellmanFord() {
        for (Edge edge : edges) {
            if (distances[edge.getSource()] != Integer.MAX_VALUE
                    && distances[edge.getSource()] + edge.getWeight() < distances[edge.getDestination()]) {
                distances[edge.getDestination()] = distances[edge.getSource()] + edge.getWeight();
            }
        }
        Utils.sendAckMessages(nodeId, receivedAck);
    }
}
