package es.deusto.ingenieria.sd.auctions.server.data.domain;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(detachable="true")
public class Category {	
	private String name;
	
	@Join
	//This annotation maps the 1-N relationship as an intermediate table.
	@Persistent(mappedBy="category", dependentElement="true", defaultFetchGroup="true")
	//"mappedBy" indicates the name of the attribute defining the relationship at the other end
	//"dependentElement" indicates that the objects in the list are automatically deleted from 
	//the DB when this object is deleted.
	private List<Article> articles = new ArrayList<>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
		
		result.append(this.name);
		result.append(" (");
		result.append(this.articles.size());
		result.append(" articles)");
		
		return result.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.name.equals(((Category)obj).name);
		}
		
		return false;
	}
}