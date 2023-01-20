package data.dto;

import java.io.Serializable;
import java.util.Date;

public class ChallengeDTO implements Serializable, Comparable<ChallengeDTO>{
	private static final long serialVersionUID = 1L;
	private String name = "null";
	private Date startDate = new Date();
	private Date endDate = new Date();
	private float targetDistance = 0.0f;
	private int targetTime = 0;
	private SportDTO sport;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public float getTargetDistance() {
		return targetDistance;
	}
	public void setTargetDistance(float targetDistance) {
		this.targetDistance = targetDistance;
	}
	public int getTargetTime() {
		return targetTime;
	}
	public void setTargetTime(int targetTime) {
		this.targetTime = targetTime;
	}
	public SportDTO getSport() {
		return sport;
	}
	public void setSport(SportDTO sport) {
		this.sport = sport;
	}
	@Override
	public String toString() {
		return this.name;
	}
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.name.equals(((ChallengeDTO)obj).name) && this.sport.equals(((ChallengeDTO)obj).sport) ;
		}
		return false;
	}
	@Override
	public int compareTo(ChallengeDTO o) {
		
		return o.name.compareTo(name);
	}
}
