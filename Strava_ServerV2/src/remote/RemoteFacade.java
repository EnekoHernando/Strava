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
import gateways.MailSender;
import services.ChallengeAppService;
import services.LoginRegisterAppService;
import services.TrainingAppSessionService;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade {	
	private static final long serialVersionUID = 1L;
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
		System.out.println( "GETTING THE SESSIONS---->" + this.serverState.get(user.getToken()).getTraininSL()); //FIXME
		return TrainingSessionAssembler.getInstance().categoryToDTO(this.serverState.get(user.getToken()).getTraininSL());
	}

	@Override
	public String getMapChallenge(UserDTO user, int selectedRow) {
		List<Challenge> chL = new ArrayList<Challenge>();
		chL.addAll(this.serverState.get(user.getToken()).getChallengeA().keySet());
		return this.serverState.get(user.getToken()).getChallengeA().get(chL.get(selectedRow)) + "";
	}
	@Override
	public void modifyMapChallenge(UserDTO user, int selectedRow, float value) {
		List<Challenge> chL = new ArrayList<Challenge>();
		chL.addAll(this.serverState.get(user.getToken()).getChallengeA().keySet());
		if(chL.get(selectedRow).getTargetDistance() < value);
		this.serverState.get(user.getToken()).getChallengeA().put(chL.get(selectedRow), value);
	}
	/**
	 * Creates training sesions
	 */
	@Override
	public void createTrainingSession(UserDTO user, String title, SportDTO sport, int dintance, Date startDate,
			Date finishdate, int duration, ChallengeDTO challenge) throws RemoteException {
		Challenge challengeSelected = null;
		for(Challenge c: this.serverState.get(user.getToken()).getChallengeA().keySet()) {
			if(ChallengeAssembler.getInstance().equalsDTO(c, challenge)) {
				challengeSelected = c;
				break;
			}
		}
		if(challengeSelected != null) {
			TrainingSession ts = TrainingAppSessionService.getInstance().createTrainingSession(this.serverState.get(user.getToken()), title, SportAssembler.getInstance().dtoToSport(sport), dintance, startDate, finishdate, duration, challengeSelected);
			System.out.println("CREADA LA TRAINNING SESSION");//FIXME
			Float f = this.serverState.get(user.getToken()).getChallengeA().get(ts.getChallenge())+ts.getDistance();
			System.out.println("CALCULADA LA NUEVA DISTANCIA");//FIXME
			this.serverState.get(user.getToken()).getChallengeA().replace(ts.getChallenge(), f);
			System.out.println("MAPA ACTUALIZADO");//FIXME
			if(!this.serverState.get(user.getToken()).getTraininSL().contains(ts)) {
				System.out.println("DENTRO DEL IF"); //FIXME
				this.serverState.get(user.getToken()).getTraininSL().add(ts);
				
				System.out.println("User sessions::::::::::" + this.serverState.get(user.getToken()).getTraininSL()); //FIXME
				
				UserDAO.getInstance().updateUser(this.serverState.get(user.getToken()));
				
			}
		}
		else throw new RemoteException("The selected challenge does not exist.");
	}
	/**
	 * Takes community Challenge and accept them
	 */
	@Override
	public void acceptChallenge(UserDTO user, int challenge) throws RemoteException {
		if(!this.serverState.get(user.getToken()).getChallengeA().containsKey(ChallengeDAO.getInstance().getAll().get(challenge))) {
			this.serverState.get(user.getToken()).getChallengeA().put(ChallengeDAO.getInstance().getAll().get(challenge), 0f);
			UserDAO.getInstance().updateUser(this.serverState.get(user.getToken()));
		}else throw new RemoteException("Challenge already Accepted");
	}

	@Override
	public Map<ChallengeDTO, Float> getAcceptedChallenges(UserDTO user) throws RemoteException {
		return ChallengeAssembler.getInstance().mapToDTO(this.serverState.get(user.getToken()).getChallengeA());
	}

	@Override
	public List<ChallengeDTO> recoverAllChallenges() throws RemoteException {
		return ChallengeAssembler.getInstance().categoryToDTO(ChallengeDAO.getInstance().getAll());
	}
	
	@Override
	public void createChallenge(UserDTO user, String name, Date startDate, Date endDate, float targetDistance, int targetTime, SportDTO sport) throws RemoteException {
		Challenge created = ChallengeAppService.getInstance().createChallenge(name, startDate, endDate, targetDistance, targetTime,SportAssembler.getInstance().dtoToSport(sport));
		ChallengeDAO.getInstance().save(created);
		new MailSender(args[0], name).sendMessage(created.toString());
		System.out.println("Mail with the challenge information sended to: " + user.getEmail());
	}
	
	@Override
	public synchronized void logout(UserDTO userdto) throws RemoteException {
		System.out.println(" * RemoteFacade logout: " + userdto.getEmail());
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
		}
		throw new RemoteException("Login fails!");
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