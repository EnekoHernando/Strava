package controllers;

import java.rmi.RemoteException;
import java.util.Date;

import data.dto.UserDTO;
import remote.ServiceLocator;


//This class implements Controller pattern.
public class LoginController {	
	
	//Reference to the Service Locator
	private ServiceLocator serviceLocator;
	//This attibute stores the token when login success
	private UserDTO user; //-1 = login has not been done or fails

	public LoginController(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public UserDTO login(String email, String password, String type) {
		try {
			this.user = this.serviceLocator.getService().login(email, password, type);		
			System.out.println("Log In Controller User:"+this.user);
			return this.user;
		} catch (RemoteException e) {
			System.out.println("# Error during login: " + e);
			this.user = null;
			return this.user;
		}
	}
	public UserDTO register(String type, String email,String password, long date, float weight, int height, int maxHeartRate, int heartRateAtRest) {
		try {
			this.user = this.serviceLocator.getService().register(type, email, password, new Date(date), weight,height,maxHeartRate,heartRateAtRest);
			return this.user;
		} catch (Exception e) {
			System.out.println("# Error during Registration: " + e);
			this.user = null;
			return this.user;
		}
	}

	public long getToken() {
		return user.getToken();
	}
	public ServiceLocator getService() {
		return this.serviceLocator;
	}
}