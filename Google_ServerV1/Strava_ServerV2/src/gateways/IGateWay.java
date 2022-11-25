package gateways;

public interface IGateWay {
	public boolean logIn(String email, String password);
	public boolean register(String email, String password);
}
