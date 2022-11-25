package data.domain;

import java.util.Date;

public class Challenge {
	private String name;
	private Date startDate;
	private Date endDate;
	private float targetDistance;
	private int targetTime;
	private Sport sport;
	private User creator;
	
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
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	@Override
	public String toString() {
		return "Challenge: "+ this.name + ", Sport" + this.sport;
	}
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.name.equals(((Challenge)obj).name) && this.creator.equals(((Challenge)obj).creator);
		}
		return false;
	}
}
