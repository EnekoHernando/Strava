package server;

import java.rmi.Naming;
import java.util.Date;

import dao.ChallengeDAO;
import dao.UserDAO;
import data.domain.Challenge;
import data.domain.Sport;
import data.domain.TrainingSession;
import data.domain.User;
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
		Challenge g1 = new Challenge("Here goes the name", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()+86400L), 200, 200, Sport.CYCLING);
		ChallengeDAO.getInstance().save(g1);
		User u1 = new User(0, "eneko",org.apache.commons.codec.digest.DigestUtils.sha1Hex("1234"), new Date(System.currentTimeMillis()), 100, 175,120,60);
		u1.getChallengeAL().add(g1);
		u1.getTraininSL().add(new TrainingSession(u1, "Run until u can breath", Sport.RUNNING, 10, 
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()+86400L), 10));
		UserDAO.getInstance().save(u1);
	}
}
