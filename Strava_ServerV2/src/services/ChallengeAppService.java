package services;

import java.util.Date;

import data.domain.Challenge;
import data.domain.Sport;
import data.domain.User;
import data.dto.ChallengeDTO;
import data.dto.UserDTO;

public class ChallengeAppService {
	//Instance for the Singleton Pattern
		private static ChallengeAppService instance;
		
		private  ChallengeAppService() {}
		
		public static ChallengeAppService getInstance() {
			if (instance == null) {
				instance = new ChallengeAppService();
			}
			
			return instance;
		}

		public Challenge createChallenge(User user, String name, Date startDate, Date endDate, float targetDistance,
			int targetTime, Sport sport) {
			Challenge ch = new Challenge();
			ch.setCreator(user);
			ch.setName(name);
			ch.setStartDate(startDate);
			ch.setEndDate(endDate);
			ch.setTargetDistance(targetDistance);
			ch.setTargetTime(targetTime);
			ch.setSport(sport);
			System.out.println("ChallengeService new challenge: " + ch);
			return ch;
		}
		public boolean equals(ChallengeDTO chC, Challenge ch){
			return chC.getName().equals(ch.getName()) && chC.getSport().equals(ch.getSport()); 
		}
}
