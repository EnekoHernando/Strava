package server;

import java.rmi.Naming;

import g.remote.IRemoteFacadeGoogle;
import g.remote.RemoteFacadeGoogle;


public class MP {
	//Activate Security Manager. It is needed for RMI.
	public static void main(String[] args) {
			if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}

			//args[0] = RMIRegistry IP
			//args[1] = RMIRegistry Port
			//args[2] = Service Name
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
			
			//Bind remote facade instance to a service name using RMIRegistry
			try {
				IRemoteFacadeGoogle remoteFacade = new RemoteFacadeGoogle();			
				Naming.rebind(name, remoteFacade);
				System.out.println(" * Google '" + name + "' started!!");
			} catch (Exception ex) {
				System.err.println(" # Google Server Exception: " + ex.getMessage());
				ex.printStackTrace();
			}
	}
}
