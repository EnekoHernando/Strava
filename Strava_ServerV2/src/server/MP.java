package server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import dao.ChallengeDAO;
import dao.UserDAO;
import data.domain.Challenge;
import data.domain.Sport;
import data.domain.TrainingSession;
import data.domain.User;
import remote.IRemoteFacade;
import remote.RemoteFacade;
import services.LoginRegisterAppService;

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
		Challenge c2 = new Challenge("DAY D V2", sdf2.parse("6/6/2022"), sdf2.parse("7/6/2022"), 20, 15, Sport.RUNNING_CYCLING);
		Challenge c3 = new Challenge("Run when u r horny", sdf2.parse("1/01/2023"), sdf2.parse("31/12/2023"), 180, 30, Sport.RUNNING);
		User u1 = new User("eneko",org.apache.commons.codec.digest.DigestUtils.sha1Hex("1234"), new Date(System.currentTimeMillis()), 100, 175,120,60);
		u1.getChallengeA().put(c1,0f);
		TrainingSession ts1 = new TrainingSession(u1, "Run until u can't breath", Sport.RUNNING, 10, 
				new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()+86400L), 10);
		ts1.setChallenge(c1);
		u1.getTraininSL().add(ts1);
		UserDAO.getInstance().save(u1);
		User u2 = new User("winston.churchill", org.apache.commons.codec.digest.DigestUtils.sha1Hex("GodSaveTheQueen1960"), sdf2.parse("30/11/1974"), 130, 165, 150, 70);
		u2.getChallengeA().put(c2,0f);
		TrainingSession ts2 = new TrainingSession(u2, "Win WW3", Sport.RUNNING_CYCLING, 2000, sdf2.parse("1/9/2022"), sdf2.parse("2/9/2022"), 2190);
		ts2.setChallenge(c2);
		u2.getTraininSL().add(ts2);
		UserDAO.getInstance().save(u2);
		User u3 = new User();
		try {
			u3 = LoginRegisterAppService.getInstance().register("Asieruli@gmail.com", "reisa123", sdf2.parse("29/07/2002"), 60, 177, 120, 90, "Google", args);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		Challenge c4 = new Challenge("Training for the marathon", sdf2.parse("20/1/2023"), sdf2.parse("28/02/2023"), 1000, 1000, Sport.RUNNING);
		TrainingSession ts6 = new TrainingSession(u3, "Morning ruting 25/01", Sport.RUNNING, 20, sdf2.parse("25/01/2023"), sdf2.parse("25/01/2023"), 60);
		TrainingSession ts7 = new TrainingSession(u3, "After lunch ruting 25/01", Sport.RUNNING, 20, sdf2.parse("25/01/2023"), sdf2.parse("25/01/2023"), 60);
		TrainingSession ts8 = new TrainingSession(u3, "Morning ruting 26/01", Sport.RUNNING, 20, sdf2.parse("26/01/2023"), sdf2.parse("26/01/2023"), 60);
		u3.getTraininSL().addAll(Arrays.asList(new TrainingSession[] {ts6,ts7, ts8}));
		c4.getTrss().addAll(Arrays.asList(new TrainingSession[] {ts6,ts7,ts8}));
		u3.getChallengeA().put(c4, 0f);
		
		TrainingSession ts3 = new TrainingSession(u3, "When i woke up", Sport.RUNNING,60 , sdf2.parse("1/01/2023"), sdf2.parse("1/01/2023"), 120);
		TrainingSession ts4 = new TrainingSession(u3, "After lunch", Sport.RUNNING, 60, sdf2.parse("1/01/2023"), sdf2.parse("1/01/2023"), 120);
		TrainingSession ts5 = new TrainingSession(u3, "Before midnight", Sport.RUNNING, 60, sdf2.parse("1/01/2023"), sdf2.parse("1/01/2023"), 120);
		u3.getTraininSL().addAll(Arrays.asList(new TrainingSession[] {ts3,ts4,ts5}));
		c3.getTrss().addAll(Arrays.asList(new TrainingSession[] {ts3,ts4,ts5}));
		u3.getChallengeA().put(c3, 0f);
		
		UserDAO.getInstance().updateUser(u3);
		
		ChallengeDAO.getInstance().save(c1);
		ChallengeDAO.getInstance().save(c2);
		ChallengeDAO.getInstance().save(c3);
		ChallengeDAO.getInstance().save(c4);
	}
}
