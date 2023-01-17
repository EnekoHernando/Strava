package data.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable = "true")
public class Challenge implements Comparable<Challenge> {
	
	private String name;
	private Date startDate;
	private Date endDate;
	private float targetDistance;
	private int targetTime;
	
	
	@Extension(vendorName="datanucleus", key="enum-value-getter", value="getValue")
	private Sport sport;
	
	@Join
	@Persistent(defaultFetchGroup = "true", mappedBy = "challenge", dependentElement = "true")
	public List<TrainingSession> trss = new ArrayList<>(); 
	
	public Challenge() {}
	
	public Challenge(String name, Date startDate, Date endDate, float targetDistance, int targetTime, Sport sport) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.targetDistance = targetDistance;
		this.targetTime = targetTime;
		this.sport = sport;
	}

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
	@Override
	public String toString() {
		return "Challenge: "+ this.name + ", Sport" + this.sport;
	}
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.name.equals(((Challenge)obj).name);
		}
		return false;
	}

	@Override
	public int compareTo(Challenge o) {
		return o.getName().compareTo(name);	
	}
}
