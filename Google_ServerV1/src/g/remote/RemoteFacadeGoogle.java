package g.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;


public class RemoteFacadeGoogle extends UnicastRemoteObject implements IRemoteFacadeGoogle {
	
	private Map<String, String> serverState = new HashMap<>(); //Map with the users.	
	
	public RemoteFacadeGoogle() throws RemoteException {
	}
	private static final long serialVersionUID = 1L;
	//Data structure for manage Server State
	
	
	@Override
	public boolean login(String email, String password) throws RemoteException {
		System.out.println("Strava asked log in");
		if(serverState.containsKey(email)) {
			if(serverState.get(email).equals(org.apache.commons.codec.digest.DigestUtils.sha1Hex(password))) {
				System.out.println("True loggin");
				return true;
			}else throw new RemoteException("The Password does not match");
		}else throw new RemoteException("There is no user with that email.");
	}
	
	@Override
	public boolean register(String email, String password) throws RemoteException {
			System.out.println("Registering with google: "+email);
			serverState.put(email, org.apache.commons.codec.digest.DigestUtils.sha1Hex(password));
			return login(email, password);
	}
	
}