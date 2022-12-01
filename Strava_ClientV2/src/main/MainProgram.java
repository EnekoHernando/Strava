package main;

import controllers.LoginController;
import gui.LogIn_Window;
import remote.ServiceLocator;
public class MainProgram {
	public static void main(String[] args) {
		ServiceLocator serviceLocator = new ServiceLocator();
		
		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Service Name
		serviceLocator.setService(args[0], args[1], args[2]);
		
		LoginController loginController = new LoginController(serviceLocator);
		//Date date = new Date(System.currentTimeMillis()-24*3600000L);
		//loginController.register("Google","eneko@gmail.com", "1234",date.getTime(), 100, 173, 120, 70);
		//loginController.register("Google", "asieruli@gmail.com", "abcd",date.getTime(), 100, 173, 120, 70);
		new LogIn_Window(loginController);
	}
}
