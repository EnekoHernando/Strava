package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import data.domain.User;
import data.dto.ChallengeDTO;
import data.dto.SportDTO;
import data.dto.TrainingSessionDTO;
import data.dto.UserDTO;


//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IRemoteFacade extends Remote {	
	public UserDTO login(String type, String email, String password) throws RemoteException;
	public UserDTO register(String type, String email, String password,Date birth, float weight, int height, int maxHeartRate, int heartRateAtRest) throws RemoteException;
	public void logout(UserDTO user) throws RemoteException;
	public List<ChallengeDTO> getCompletedChallenges(UserDTO user) throws RemoteException;
	public List<ChallengeDTO> getAcceptedChallenges(UserDTO user) throws RemoteException;
	public List<ChallengeDTO> recoverAllChallenges() throws RemoteException;
	public void createChallenge(UserDTO user, String name, Date startDate, Date endDate, float targetDistance, int targetTime, SportDTO sport) throws RemoteException;
	public void completeChallenge(UserDTO user, int challenge) throws RemoteException;
	public void acceptChallenge(UserDTO user, int challenge) throws RemoteException;
	public List<TrainingSessionDTO> getSessions(UserDTO user) throws RemoteException;
	public void createTrainingSession(UserDTO user, String title, SportDTO sport, int dintance, Date startDate, Date finishdate, int duration) throws RemoteException;
}