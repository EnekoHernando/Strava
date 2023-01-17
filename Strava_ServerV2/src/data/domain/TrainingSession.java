package data.domain;

import java.util.Date;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class TrainingSession {
	@Persistent(defaultFetchGroup="true", mappedBy="traininSL", dependentElement = "true")
	private User owner;
	
	private String title;
	@Extension(vendorName="datanucleus", key="enum-value-getter", value="getValue")	
	private Sport sport;
	private int distance;
	private Date startDate;
	private Date finishDate;
	private int duration;
	
	@Persistent(defaultFetchGroup="true")
	private Challenge challenge;
	
	public TrainingSession() {}
	
	public TrainingSession(User owner, String title, Sport sport, int distance, Date startDate, Date finishDate,
			int duration) {
		super();
		this.owner = owner;
		this.title = title;
		this.sport = sport;
		this.distance = distance;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.duration = duration;
	}

	public Challenge getChallenges() {
		return challenge;
	}

	public void setChallenges(Challenge challenges) {
		this.challenge = challenges;
	}

	public String getTitle() {
		return title;
	}

	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Sport getSport() {
		return sport;
	}
	public void setSport(Sport sport) {
		this.sport = sport;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "Title: "+this.title+" Start Date: "+this.startDate+" FinishDate: " + this.finishDate+" Challenges: " + this.challenge;
	}
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.title.equals(((TrainingSession)obj).title) && this.sport.equals(((TrainingSession)obj).sport) && this.owner.equals(((TrainingSession)obj).owner);
		}
		return false;
	}
}
