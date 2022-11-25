package data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//This class implements DTO pattern
public class UserDTO implements Serializable {	
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private Date birthdate;
	private float weight;
	private float height;
	private int maxHeartRate;
	private int heartRateAtRest;
	private long token;
	private List<ChallengeDTO> challengeAL = new ArrayList<ChallengeDTO>();
	private List<ChallengeDTO> challengeCL = new ArrayList<ChallengeDTO>();
	private List<TrainingSessionDTO> traininSL = new ArrayList<TrainingSessionDTO>();
	
	public List<ChallengeDTO> getChallengeAL() {
		return challengeAL;
	}

	public void setChallengeAL(List<ChallengeDTO> challengeAL) {
		this.challengeAL = challengeAL;
	}

	public List<ChallengeDTO> getChallengeCL() {
		return challengeCL;
	}

	public void setChallengeCL(List<ChallengeDTO> challengeCL) {
		this.challengeCL = challengeCL;
	}

	public List<TrainingSessionDTO> getTraininSL() {
		return traininSL;
	}

	public void setTraininSL(List<TrainingSessionDTO> traininSL) {
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

	public void setHeight(float height) {
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
}