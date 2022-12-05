package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ChallengeDAO;
import dao.UserDAO;
import data.domain.Challenge;
import data.domain.TrainingSession;
import data.domain.User;
import data.dto.ChallengeAssembler;
import data.dto.ChallengeDTO;
import data.dto.SportAssembler;
import data.dto.SportDTO;
import data.dto.TrainingSessionAssembler;
import data.dto.TrainingSessionDTO;
import data.dto.UserAssembler;
import data.dto.UserDTO;
import services.ChallengeAppService;
import services.LoginRegisterAppService;
import services.TrainingAppSessionService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;
	//Data structure for manage Server State
	private String[] args;
	private Map<Long, User> serverState = new HashMap<>(); //Map with the users.
	
	public RemoteFacade(String[] args) throws RemoteException {
		this.args = args;
	}
	/**
	 * Returns the sessions of the user that has logged in
	 */
	@Override
	public List<TrainingSessionDTO> getSessions(UserDTO user) throws RemoteException {
		return TrainingSessionAssembler.getInstance().categoryToDTO(this.serverState.get(user.getToken()).getTraininSL());
	}

	/**
	 * Creates training sesions
	 */
	@Override
	public void createTrainingSession(UserDTO user, String title, SportDTO sport, int dintance, Date startDate,
			Date finishdate, int duration) throws RemoteException {
		TrainingSession ts = TrainingAppSessionService.getInstance().createTrainingSession(this.serverState.get(user.getToken()), title, SportAssembler.getInstance().dtoToSport(sport), dintance, startDate, finishdate, duration);
		if(this.serverState.get(user.getToken()).getTraininSL().contains(ts)) {
			this.serverState.get(user.getToken()).getTraininSL().add(ts);
			UserDAO.getInstance().updateUser(this.serverState.get(user.getToken()));
		}
		else throw new RemoteException("Trainning session already exists");
	}
	/**
	 *Makes accepted Challenges completed
	 */
	@Override
	public void completeChallenge(UserDTO user, int challenge) throws RemoteException {
		if(!this.serverState.get(user.getToken()).getChallengeCL().contains(this.serverState.get(user.getToken()).getChallengeAL().get(challenge))) {
			this.serverState.get(user.getToken()).getChallengeCL().add(this.serverState.get(user.getToken()).getChallengeAL().get(challenge));
			UserDAO.getInstance().updateUser(this.serverState.get(user.getToken()));
		}else throw new RemoteException("Challenge alredy Completed!");		
	}

	/**
	 * Takes community Challenge and accept them
	 */
	@Override
	public void acceptChallenge(UserDTO user, int challenge) throws RemoteException {
		if(!this.serverState.get(user.getToken()).getChallengeAL().contains(ChallengeDAO.getInstance().getAll().get(challenge))) {
			this.serverState.get(user.getToken()).getChallengeAL().add(ChallengeDAO.getInstance().getAll().get(challenge));
			UserDAO.getInstance().updateUser(this.serverState.get(user.getToken()));
		}else throw new RemoteException("Challenge already Accepted");
	}
	@Override
	
	public List<ChallengeDTO> getCompletedChallenges(UserDTO user) throws RemoteException {
		return ChallengeAssembler.getInstance().categoryToDTO(this.serverState.get(user.getToken()).getChallengeCL());
		
	}

	@Override
	public List<ChallengeDTO> getAcceptedChallenges(UserDTO user) throws RemoteException {
		return ChallengeAssembler.getInstance().categoryToDTO(this.serverState.get(user.getToken()).getChallengeAL());
	}

	@Override
	public List<ChallengeDTO> recoverAllChallenges() throws RemoteException {
		return ChallengeAssembler.getInstance().categoryToDTO(ChallengeDAO.getInstance().getAll());
	}
	@Override
	public void createChallenge(String name, Date startDate, Date endDate, float targetDistance,
			int targetTime, SportDTO sport) throws RemoteException {
		ChallengeDAO.getInstance().save(ChallengeAppService.getInstance().createChallenge(name, startDate, endDate, targetDistance, targetTime,SportAssembler.getInstance().dtoToSport(sport)));
	}
	
	@Override
	public synchronized void logout(UserDTO userdto) throws RemoteException {
		System.out.println(" * RemoteFacade logout: " + userdto);
		if (this.serverState.containsKey(userdto.getToken())) {
			this.serverState.remove(userdto.getToken());
		} else throw new RemoteException("User is not not logged in!");
	}
	
	@Override
	public synchronized UserDTO login(String email, String password, String type) throws RemoteException {
		System.out.println(" * RemoteFacade login: " + email + " / " + password);
		User user = LoginRegisterAppService.getInstance().login(email, password, type, args);
		if (user != null) {
			user.setToken(System.currentTimeMillis());
			this.serverState.put(user.getToken(), user);
			return UserAssembler.getInstance().userToDTO(user);
		}else throw new RemoteException("Login fails!");
	}

	@Override
	public UserDTO register(String type, String email,String password, Date birth, float weight, int height, int maxHeartRate, int heartRateAtRest)
			throws RemoteException {
		User user = LoginRegisterAppService.getInstance().register(email, password, birth, weight, height, maxHeartRate, heartRateAtRest, type, args);
		if(user != null) {
			Long token = System.currentTimeMillis();
			user.setToken(token);
			UserDTO udt = UserAssembler.getInstance().userToDTO(user);
			this.serverState.put(token, user);
			return udt;
		}else throw new RemoteException("Sign In fails!");
	}
}