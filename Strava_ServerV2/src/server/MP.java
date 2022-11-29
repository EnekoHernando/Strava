package server;

import java.rmi.Naming;
import remote.IRemoteFacade;
import remote.RemoteFacade;

public class MP {
	public static void main(String[] args) {	
		//Activate Security Manager. It is needed for RMI.
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		//Bind remote facade instance to a sirvice name using RMIRegistry
		try {
			System.out.println(args[0] + " "+ args[1] + " "+args[3]+" "+Integer.parseInt(args[4]));
			IRemoteFacade remoteFacade = new RemoteFacade(args);			
			Naming.rebind(name, remoteFacade);
			System.out.println(" * Strava '" + name + "' started!!");
		} catch (Exception ex) {
			System.err.println(" # Strava Server Exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
