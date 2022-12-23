package server;

import java.rmi.Naming;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private static SimpleDateFormat sdf2 = new SimpleDateFormat( "dd/MM/yyyy" );
	public static void main(String[] args) throws ParseException {	
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
		Challenge c1 = new Challenge("Here goes the name", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()+86400L), 200, 200, Sport.CYCLING);
		Challenge c2 = new Challenge("DAY D V2", sdf2.parse("6/6/2022"), sdf2.parse("7/6/2022"), 20, 15, Sport.BULLET_DOGE);
		Challenge c3 = new Challenge("Run when u r horny", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + (long) (86400*5)), 20, 30, Sport.RUNNING);
		ChallengeDAO.getInstance().save(c1);
		ChallengeDAO.getInstance().save(c2);
		ChallengeDAO.getInstance().save(c3);
		User u1 = new User("eneko",org.apache.commons.codec.digest.DigestUtils.sha1Hex("1234"), new Date(System.currentTimeMillis()), 100, 175,120,60);
		u1.getChallengeAL().add(c1);
		TrainingSession ts1 = new TrainingSession(u1, "Run until u can't breath", Sport.RUNNING, 10, 
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()+86400L), 10);
		ts1.getChallenges().add(c1);
		u1.getTraininSL().add(ts1);
		UserDAO.getInstance().save(u1);
		User u2 = new User("Winston.Churchill", org.apache.commons.codec.digest.DigestUtils.sha1Hex("GodSaveTheQueen1960"), sdf2.parse("30/11/1974"), 130, 165, 150, 70);
		u2.getChallengeAL().add(c2);
		u2.getChallengeCL().add(c3);
		TrainingSession ts2 = new TrainingSession(u2, "Win WW3", Sport.BULLET_DOGE, 2000, sdf2.parse("1/9/2022"), sdf2.parse("2/9/2022"), 2190);
		ts2.getChallenges().add(c2);
		u2.getTraininSL().add(ts2);
		UserDAO.getInstance().save(u2);
	}
}
