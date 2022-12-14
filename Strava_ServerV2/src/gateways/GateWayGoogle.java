package gateways;

import java.rmi.Naming;

import g.remote.IRemoteFacadeGoogle;

public class GateWayGoogle implements IGateWay{
	private static GateWayGoogle instance;
	private IRemoteFacadeGoogle service;
	
	
	public GateWayGoogle(String URL) {
		try {
			this.service = (IRemoteFacadeGoogle) Naming.lookup(URL);
		} catch (Exception ex) {
			System.err.println("# Error locating remote facade: " + ex);
		}		
	}
	public static GateWayGoogle getInstance(String URL) {
		if(instance == null) {
			instance = new GateWayGoogle(URL);
		}
		return instance;
	}
	@Override
	public boolean register(String email, String password) {
		try {
			return this.service.register(email, password);
		} catch (Exception e) {
			System.out.println("# Error registerin with google: " + e);
		}
		return false;
	}
	@Override
	public boolean logIn(String email, String password) {
		try {
			boolean ans =this.service.login(email, password);
			return ans;
		} catch (Exception e) {
			System.out.println("# Error login in with google: " + e);
		}
		return false;
	}
}
