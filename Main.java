import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) {
        try {
            // Start the RMI registry
            LocateRegistry.createRegistry(1099);

            NodeInterface node = new Node();
            String registryURL = "rmi://localhost/Node"; // Change to your IP or hostname if needed

            Naming.rebind(registryURL, node);

            System.out.println("Node is registered.");

            // Run the algorithms on the node
            node.runSynchronousBellmanFord();
            node.runAsynchronousBellmanFord();

            // Retrieve and display the results
            int[] syncDistances = node.getSynchronousDistances();
            int[] asyncDistances = node.getAsynchronousDistances();

            System.out.println("Synchronous Shortest Paths: " + java.util.Arrays.toString(syncDistances));
            System.out.println("Asynchronous Shortest Paths: " + java.util.Arrays.toString(asyncDistances));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
