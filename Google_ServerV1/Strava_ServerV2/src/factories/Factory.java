package factories;

import gateways.GateWayFacebook;
import gateways.GateWayGoogle;
import gateways.IGateWay;

public class Factory {
	private static Factory service;
	public static Factory getInstance() {
		if(service == null) {
			service= new Factory();
		}
		return service;
	}
	public IGateWay createGateWay(String type, String ip, String gport, String gname, int fport) {
		switch (type) {
			case "Google":
				return GateWayGoogle.getInstance(ip, gport, gname);			
			case "Facebook":
				return GateWayFacebook.getInstance(ip, fport);
			default:
				return null;
		}
	}
}
