package controllers;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import data.dto.ChallengeDTO;
import data.dto.SportDTO;
import data.dto.UserDTO;
import remote.ServiceLocator;

public class ChallengeController {
	//Reference to the Service Locator
		private ServiceLocator serviceLocator;
		//This attibute stores the token when login success
		private UserDTO user; //-1 = login has not been done or fails	
		public ChallengeController(ServiceLocator serviceLocator) {
			this.serviceLocator = serviceLocator;
		}
		public void logout(UserDTO user) {
			this.user=user;
			try {
				this.serviceLocator.getService().logout(this.user);
				this.user = null;
			} catch (RemoteException e) {
				System.out.println("# Error during logout: " + e);
			}
		}
		public List<ChallengeDTO> getCompletedChallenges(UserDTO user){
			try {
				return this.serviceLocator.getService().getCompletedChallenges(this.user);
			} catch (RemoteException e) {
				System.out.println("# Error recovering completed challenges: " + e);
			}
			return new ArrayList<>(Arrays.asList(new ChallengeDTO()));
		}
		public List<ChallengeDTO> getAcceptedChallenges(UserDTO user){
			try {
				return this.serviceLocator.getService().getAcceptedChallenges(this.user);
			} catch (Exception e) {
				System.out.println("# Error recovering accepted challenges: " + e);
			}
			return new ArrayList<>(Arrays.asList(new ChallengeDTO()));
		}
		public List<ChallengeDTO> recoverAllChallenges(){
			try {
				return this.serviceLocator.getService().recoverAllChallenges();
			} catch (Exception e) {
				System.out.println("# Error recovering all challenges: " + e);
			}
			return new ArrayList<>(Arrays.asList(new ChallengeDTO()));
		}
		public void createChallenge(String name, Date startDate, Date endDate,
				float targetDistance, int targetTime, SportDTO sport) {
			try {
				this.serviceLocator.getService().createChallenge(this.user, name, startDate, endDate,
						targetDistance, targetTime, sport);
			} catch (Exception e) {
				System.out.println("# Error creating a challenge: " + e);
			}			
		}
		public void completeChallenge(int challenge) {
			try {
				this.serviceLocator.getService().completeChallenge(this.user, challenge);
			} catch (Exception e) {
				System.out.println("# Error Completing the challenge");
			}
		}
		public void acceptChallenge(int challenge) {
			try {
				this.serviceLocator.getService().acceptChallenge(this.user, challenge);
			} catch (Exception e) {
				System.out.println("# Error accept the challenge");
			}
		}
		public long getToken() {
			return user.getToken();
		}
		public void setUser(UserDTO user) {
			this.user = user;
		}
		public UserDTO getUser() {
			return this.user;
		}
		public ServiceLocator getService() {
			return this.serviceLocator;
		}
}