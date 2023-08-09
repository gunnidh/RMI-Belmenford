import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NodeInterface extends Remote {
    void runSynchronousBellmanFord() throws RemoteException;
    void runAsynchronousBellmanFord() throws RemoteException;
    int[] getSynchronousDistances() throws RemoteException;
    int[] getAsynchronousDistances() throws RemoteException;
}
