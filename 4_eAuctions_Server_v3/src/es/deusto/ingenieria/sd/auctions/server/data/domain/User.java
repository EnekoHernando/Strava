package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable="true")
public class User {	
	private String nickname;
	private String password;
	private String email;
	
	@Join
	//This annotation maps the 1-N relationship as an intermediate table.
	@Persistent(mappedBy="user", dependentElement="true", defaultFetchGroup="true")
	//"mappedBy" indicates the name of the attribute defining the relationship at the other end
	//"dependentElement" indicates that the objects in the list are automatically deleted from 
	//the DB when this object is deleted.
	private List<Bid> bids = new ArrayList<>();
	@Join
	@Persistent(mappedBy="owner", dependentElement="true", defaultFetchGroup="true")
	private List<Article> articles = new ArrayList<>();
		
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	
	public List<Article> getArticles() {
		return articles;
	}
	
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	
	public void addArticle(Article article) {
		if (article != null && !this.articles.contains(article)) {
			this.articles.add(article);
		}
	}
		
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		
		result.append(this.nickname);
		result.append(" - ");
		result.append(this.email);
		result.append(" - (");
		result.append(this.articles.size());
		result.append(" articles) - (");
		result.append(this.bids.size());
		result.append(" bids)");
		
		return result.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.email.equals(((User)obj).email);
		}
		
		return false;
	}
}