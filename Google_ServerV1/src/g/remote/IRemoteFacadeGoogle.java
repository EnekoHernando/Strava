package g.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;


//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacadeGoogle extends Remote {	

	public boolean login(String email, String password) throws RemoteException;
	
	public boolean register(String email, String password) throws RemoteException;
	
}