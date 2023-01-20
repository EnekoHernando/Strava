package services;

import java.util.Date;

import data.domain.Challenge;
import data.domain.Sport;
import data.domain.TrainingSession;
import data.domain.User;

public class TrainingAppSessionService {
	private static TrainingAppSessionService instance;
	private TrainingAppSessionService() {}
	public static TrainingAppSessionService getInstance() {
		if(instance == null) {
			instance = new TrainingAppSessionService();
		}
		return instance;
	}
	public TrainingSession createTrainingSession(User user, String title, Sport sport, int dintance,
			Date startDate, Date finishdate, int duration, Challenge c) {
		TrainingSession ts = new TrainingSession();
		ts.setOwner(user);
		ts.setTitle(title);
		ts.setSport(sport);
		ts.setDistance(dintance);
		ts.setDuration(duration);
		ts.setStartDate(startDate);
		ts.setFinishDate(finishdate);
		ts.setChallenge(c);
		return ts; 
	}
}
