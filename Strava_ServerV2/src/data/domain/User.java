package data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable="true")
public class User {	
	private long token=0l;
	private String email = "";
	private String password="";
	private Date birthdate=null;
	private float weight=0.0f;
	private int height=0;
	private int maxHeartRate=0;
	private int heartRateAtRest=0;
	
	public User() {}
	
	public User(String email, String password, Date birthdate, float weight, int height, int maxHeartRate,
			int heartRateAtRest) {
		super();
		this.email = email+ "@gmail.com";
		this.password = password;
		this.birthdate = birthdate;
		this.weight = weight;
		this.height = height;
		this.maxHeartRate = maxHeartRate;
		this.heartRateAtRest = heartRateAtRest;
	}

	/**
	 * The map registers the challenge accepted and the completition of those, 
	 * AKA, how much of the objective is done (in km).
	 */
	@Persistent(defaultFetchGroup="true")
	@Join
	private Map<Challenge, Float> challengeA = new TreeMap<Challenge, Float>();
	
	@Persistent(defaultFetchGroup="true")
	@Join
	private List<TrainingSession> traininSL = new ArrayList<TrainingSession>();
		

	public Map<Challenge, Float> getChallengeA() {
		return challengeA;
	}
	
	public void setChallengeA(HashMap<Challenge, Float> challengeA) {
		this.challengeA = challengeA;
	}
	
	/*public List<Challenge> getChallengeAL() {
		return challengeAL;
	}

	public void setChallengeAL(List<Challenge> challengeAL) {
		this.challengeAL = challengeAL;
	}

	public List<Challenge> getChallengeCL() {
		return challengeCL;
	}

	public void setChallengeCL(List<Challenge> challengeCL) {
		this.challengeCL = challengeCL;
	}*/

	public List<TrainingSession> getTraininSL() {
		return traininSL;
	}

	public void setTraininSL(List<TrainingSession> traininSL) {
		this.traininSL = traininSL;
	}
	public long getToken() {
		return token;
	}

	public void setToken(long token) {
		this.token = token;
	}
	
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMaxHeartRate() {
		return maxHeartRate;
	}

	public void setMaxHeartRate(int maxHeartRate) {
		this.maxHeartRate = maxHeartRate;
	}

	public int getHeartRateAtRest() {
		return heartRateAtRest;
	}

	public void setHeartRateAtRest(int heartRateAtRest) {
		this.heartRateAtRest = heartRateAtRest;
	}

	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return "User [token=" + token + ", email=" + email + ", password=" + password + ", birthdate=" + birthdate
				+ ", weight=" + weight + ", height=" + height + ", maxHeartRate=" + maxHeartRate + ", heartRateAtRest="
				+ heartRateAtRest+ ", MAP:" + challengeA+ ", "+ traininSL;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.email.equals(((User)obj).email) && this.password.equals(((User)obj).password);
		}
		return false;
	}

	
}