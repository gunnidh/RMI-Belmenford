import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Node extends UnicastRemoteObject implements NodeInterface {
    private int numNodes = 4;
    private Edge[] edges = {
        new Edge(0, 1, 1),
        new Edge(0, 2, 5),
        new Edge(1, 2, 2),
        new Edge(1, 3, 4),
        new Edge(2, 3, 1),
        new Edge(3, 0, -3)
    };
    private int[] distances;

    public Node() throws RemoteException {
        distances = new int[numNodes];
        java.util.Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;
    }

    public void runSynchronousBellmanFord() throws RemoteException {
        for (int i = 0; i < numNodes - 1; i++) {
            for (Edge edge : edges) {
                if (distances[edge.getSource()] != Integer.MAX_VALUE
                        && distances[edge.getSource()] + edge.getWeight() < distances[edge.getDestination()]) {
                    distances[edge.getDestination()] = distances[edge.getSource()] + edge.getWeight();
                }
            }
        }
    }

    public void runAsynchronousBellmanFord() throws RemoteException {
        for (Edge edge : edges) {
            if (distances[edge.getSource()] != Integer.MAX_VALUE
                    && distances[edge.getSource()] + edge.getWeight() < distances[edge.getDestination()]) {
                distances[edge.getDestination()] = distances[edge.getSource()] + edge.getWeight();
            }
        }
    }

    public int[] getSynchronousDistances() throws RemoteException {
        return distances;
    }

    public int[] getAsynchronousDistances() throws RemoteException {
        return distances;
    }

    public void sendMessage(int destination, int value) throws RemoteException {
        distances[destination] = value;
    }

    public static void main(String[] args) {
        try {
            // Start the RMI registry
            LocateRegistry.createRegistry(1099);

            NodeInterface node = new Node();
            String registryURL = "rmi://localhost/Node"; // Change to your IP or hostname if needed

            Naming.rebind(registryURL, node);

            System.out.println("Node is registered.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
