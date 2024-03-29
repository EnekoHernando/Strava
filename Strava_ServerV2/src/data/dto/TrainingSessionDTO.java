package data.dto;

import java.io.Serializable;
import java.util.Date;

public class TrainingSessionDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;
	private SportDTO sport;
	private int distance;
	private Date startDate;
	private Date finishDate;
	private int duration;
	private ChallengeDTO challenge;
	
	public ChallengeDTO getChallenges() {
		return challenge;
	}
	public void setChallenges(ChallengeDTO challenges) {
		this.challenge = challenges;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public SportDTO getSport() {
		return sport;
	}
	public void setSport(SportDTO sport) {
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
	
	@Override
	public String toString() {
		return "Title: "+this.title+" Start Date: "+this.startDate+" FinishDate: " + this.finishDate + ", Challenges" + this.challenge;
	}
	
}
