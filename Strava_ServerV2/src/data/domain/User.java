package data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class User {	
	//Attributes: email, password, birthdate, 
	//and optionally: weight(kg), height(cm), maxHeartRate and heartRateAtRest(number of beats per minute).
	private long token=0l;
	private String email = "";
	private String password="";
	private Date birthdate=null;
	private float weight=0.0f;
	private int height=0;
	private int maxHeartRate=0;
	private int heartRateAtRest=0;
	
	@Persistent(defaultFetchGroup="true", mappedBy="creator", dependentElement = "true")
	@Join
	private List<Challenge> challengeAL = new ArrayList<Challenge>();
	
	@Persistent(defaultFetchGroup="true", mappedBy="creator", dependentElement = "true")
	@Join
	private List<Challenge> challengeCL = new ArrayList<Challenge>();
	@Persistent(defaultFetchGroup="true", mappedBy="owner", dependentElement = "true")
	@Join
	private List<TrainingSession> traininSL = new ArrayList<TrainingSession>();
	
	public List<Challenge> getChallengeAL() {
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
	}

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
		StringBuffer result = new StringBuffer();
		
		result.append(this.email);
		return result.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.email.equals(((User)obj).email) && this.password.equals(((User)obj).password);
		}
		
		return false;
	}

	
}