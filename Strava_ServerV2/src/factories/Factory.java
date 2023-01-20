package factories;

import gateways.GateWayFacebook;
import gateways.GateWayGoogle;
import gateways.IGateWay;

public class Factory {
	private static Factory service;
	private Factory() {}
	public static Factory getInstance() {
		if(service == null) {
			service= new Factory();
		}
		return service;
	}
	public IGateWay createGateWay(String type, String[] args ) {
		switch (type) {
			case "Google":
				return GateWayGoogle.getInstance("//"+args[0]+":"+args[1]+"/"+args[3]);			
			case "Facebook":
				return GateWayFacebook.getInstance(args[0], args[4]);
			default:
				return null;
		}
	}
}
