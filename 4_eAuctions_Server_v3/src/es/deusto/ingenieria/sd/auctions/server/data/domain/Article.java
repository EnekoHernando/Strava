package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable="true")
public class Article {
	@PrimaryKey
	private int number;
	private String title;	
	private float initialPrice;
	private Date auctionEnd;
	
	@Persistent(defaultFetchGroup="true")
	//By default non-primitive data is not retrieved from the DB immediately. 
	//This implies that the value of this attribute would be "null". 
	//The annotation forces the attribute to be retrieved as soon as the object 
	//is retrieved from the DB.
	private Category category;
	
	@Persistent(defaultFetchGroup="true")
	private User owner;
	
	@Join
	//This annotation maps the 1-N relationship as an intermediate table.
	@Persistent(mappedBy="article", dependentElement="true", defaultFetchGroup="true")
	//"mappedBy" indicates the name of the attribute defining the relationship at the other end
	//"dependentElement" indicates that the objects in the list are automatically deleted from 
	//the DB when this object is deleted.
	private List<Bid> bids = new ArrayList<>();
		
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public float getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(float price) {
		this.initialPrice = price;
	}
	
	public float getActualPrice() {
		if (this.bids.isEmpty()) {
			return this.initialPrice;
		} else {
			return this.getHighestBid().getAmount();
		}
	}

	public Date getAuctionEnd() {
		return auctionEnd;
	}

	public void setAuctionEnd(Date auctionEnd) {
		this.auctionEnd = auctionEnd;
	}

	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public List<Bid> getBids() {
		return bids;
	}
	
	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
	
	public void addBid(Bid bid) {
		if (bid != null && !this.bids.contains(bid)) {
			this.bids.add(bid);
		}
	}
	
	public Bid getHighestBid() {
		if (!this.bids.isEmpty()) {
			ArrayList<Bid> bidsArray = new ArrayList<Bid>(this.bids);			
			Collections.sort(bidsArray);		
			return bidsArray.get(0);
		} else {
			return null;
		}
	}
	
	public String toString() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-YY");
		NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.getDefault()); 

		StringBuffer result = new StringBuffer();
		
		result.append(this.number);
		result.append(" / ");
		result.append(this.title);
		result.append(" / Initial/actual price: ");
		result.append(numberFormatter.format(this.initialPrice));
		result.append("/");
		result.append(numberFormatter.format(this.getActualPrice()));
		result.append(" / Auction end: ");
		result.append(dateFormatter.format(this.auctionEnd));
		result.append(" (");
		result.append(this.bids.size());
		result.append(" bids)");
		
		return result.toString();		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.number == ((Article)obj).number;
		}
		
		return false;
	}
}