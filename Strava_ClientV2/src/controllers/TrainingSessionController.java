package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import remote.ServiceLocator;
import data.dto.UserDTO;
import data.dto.ChallengeDTO;
import data.dto.SportDTO;
import data.dto.TrainingSessionDTO;

public class TrainingSessionController {
	private ServiceLocator servicelocator;
	private UserDTO user;
	
	public TrainingSessionController(ServiceLocator sl) {
		this.servicelocator=sl;
	}
	public void setUser(UserDTO user) {
		this.user=user;
	}
	public UserDTO getUser() {
		return this.user;
	}
	public List<TrainingSessionDTO> getSessions(UserDTO user){
		try {
			return this.servicelocator.getService().getSessions(this.user);
		} catch (Exception e) {
			System.out.println("# Error recovering sessions " + e);
		}
		return new ArrayList<>(Arrays.asList(new TrainingSessionDTO()));
	}
	public void createTrainingSession(UserDTO user, String title, SportDTO sport, int distance,
			Date startDate, Date finishdate, int duration, ChallengeDTO challenge) {
		try {
			this.servicelocator.getService().createTrainingSession(user,title,sport,distance,startDate,finishdate,duration, challenge);
		} catch (Exception e) {
			
		}
	}
}
