package data.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//This class implements DTO pattern
public class UserDTO implements Serializable {	
	//This attribute is needed to implement the "Serializable" interface.
	private static final long serialVersionUID = 1L;
	private String email;
	private Date birthdate;
	private float weight;
	private float height;
	private int maxHeartRate;
	private int heartRateAtRest;
	private long token;
	private Map<ChallengeDTO, Float> challengeA = new LinkedHashMap<ChallengeDTO, Float>();
	private List<TrainingSessionDTO> traininSL = new ArrayList<TrainingSessionDTO>();

	public List<TrainingSessionDTO> getTraininSL() {
		return traininSL;
	}

	public Map<ChallengeDTO, Float> getChallengeA() {
		return challengeA;
	}

	public void setChallengeA(Map<ChallengeDTO, Float> challengeA) {
		this.challengeA = challengeA;
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

	@Override
	public String toString() {
		return "UserDTO [email=" + email + ", birthdate=" + birthdate + ", weight=" + weight + ", height=" + height
				+ ", maxHeartRate=" + maxHeartRate + ", heartRateAtRest=" + heartRateAtRest + ", token=" + token
				+ ", challengeA=" + challengeA + ", traininSL=" + traininSL + "]";
	}
	
}