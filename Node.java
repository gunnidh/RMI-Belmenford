import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Node extends Remote {
    void receiveMessage(Message message) throws RemoteException;
    int[] getDistances() throws RemoteException;
}
