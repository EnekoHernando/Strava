package services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import data.domain.User;
import factories.Factory;

public class LoginRegisterAppService{
	
	//Instance for the Singleton Pattern
	private static LoginRegisterAppService instance;
	private Map<String, User> historicUsers = new HashMap<String, User>();
	private LoginRegisterAppService() { }
	public static LoginRegisterAppService getInstance() {
		if (instance == null) {
			instance = new LoginRegisterAppService();
		}
		return instance;
	}
	public User login(String email, String password, String type, String ip, String gport, String gname, int fport) {
		if(Factory.getInstance().createGateWay(type, ip, gport, gname, fport)!=null && Factory.getInstance().createGateWay(type, ip, gport, gname, fport).logIn(email, password)) {
			return historicUsers.get(email);
		}
		if(this.historicUsers.containsKey(email) && this.historicUsers.get(email).getPassword().equals(org.apache.commons.codec.digest.DigestUtils.sha1Hex(password))) {
			return historicUsers.get(email);
		}
		return null;
	}
	
	public User register(String email,String password, Date birth, float weight, int height, int maxHeartRate, int heartRateAtRest, String type, String ip, String gport, String gname, int fport) {
		User user = new User();
		if(Factory.getInstance().createGateWay(type, ip, gport, gname, fport)!=null && Factory.getInstance().createGateWay(type, ip, gport, gname, fport).register(email, password)) {
				user.setEmail(email);
				if(type.equals("Normal")) user.setPassword(org.apache.commons.codec.digest.DigestUtils.sha1Hex(password));
				else user.setPassword("");
				user.setBirthdate(birth);
				user.setWeight(weight);
				user.setHeight(height);
				user.setMaxHeartRate(maxHeartRate);
				user.setHeartRateAtRest(heartRateAtRest);
				historicUsers.put(email, user);
				return user;
		}	
		user.setEmail(email);
		user.setPassword(org.apache.commons.codec.digest.DigestUtils.sha1Hex(password));
		user.setBirthdate(birth);
		user.setWeight(weight);
		user.setHeight(height);
		user.setMaxHeartRate(maxHeartRate);
		user.setHeartRateAtRest(heartRateAtRest);
		historicUsers.put(email, user);
		return user;
	}
}