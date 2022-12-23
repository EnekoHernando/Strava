package services;

import java.rmi.RemoteException;
import java.util.Date;

import dao.UserDAO;
import data.domain.User;
import factories.Factory;

public class LoginRegisterAppService{
	
	//Instance for the Singleton Pattern
	private static LoginRegisterAppService instance;
	private LoginRegisterAppService() { }
	public static LoginRegisterAppService getInstance() {
		if (instance == null) {
			instance = new LoginRegisterAppService();
		}
		return instance;
	}
	public User login(String email, String password, String type, String [] args) {
		User u = UserDAO.getInstance().find(email);
		System.out.println("LOGIN APP SERVICE RESULT::::::: "+u);
		if(type.equals("Normal") && u!=null && u.getPassword().equals(org.apache.commons.codec.digest.DigestUtils.sha1Hex(password))) {
			System.out.println("PASSWORD:::::::  " + u.getPassword());
			return u;
		}
		if(Factory.getInstance().createGateWay(type, args).logIn(email, password)) {
			return u;
		}
		return null;
	}
	
	public User register(String email,String password, Date birth, float weight, int height, int maxHeartRate, int heartRateAtRest, String type, String [] args) throws RemoteException {
		User user = new User();
		if(type.equals("Normal") || Factory.getInstance().createGateWay(type, args).register(email, password)) {
			try {
				if(UserDAO.getInstance().find(email)==null) {
					user.setEmail(email);
					if(type.equals("Normal")) user.setPassword(org.apache.commons.codec.digest.DigestUtils.sha1Hex(password));
					else user.setPassword("");
					user.setBirthdate(birth);
					user.setWeight(weight);
					user.setHeight(height);
					user.setMaxHeartRate(maxHeartRate);
					user.setHeartRateAtRest(heartRateAtRest);
					UserDAO.getInstance().save(user);
				}else 
					throw new RemoteException("USER ALREADY EXISTING");
			} catch (Exception e) {
				
			}
		}
		return user;
	}
}