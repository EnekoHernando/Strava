package es.deusto.ingenieria.sd.auctions.server.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Article;

//This class implements Singleton and DAO patterns
public class ArticleDAO extends DataAccessObjectBase implements IDataAccessObject<Article> {

	private static ArticleDAO instance;	
	
	private ArticleDAO() { }
	
	public static ArticleDAO getInstance() {
		if (instance == null) {
			instance = new ArticleDAO();
		}		
		
		return instance;
	}
	
	@Override
	public void save(Article object) {
		super.saveObject(object);
	}

	@Override
	public void delete(Article object) {
		super.deleteObject(object);
	}

	@Override
	public List<Article> getAll() {				
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<Article> articles = new ArrayList<>();
		
		try {
			tx.begin();
			
			Extent<Article> extent = pm.getExtent(Article.class, true);

			for (Article category : extent) {
				articles.add(category);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Articles: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return articles;
	}

	@Override
	public Article find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Article result = null; 

		try {
			tx.begin();
						
			Query<?> query = pm.newQuery("SELECT FROM " + Article.class.getName() + " WHERE number == " + param);
			query.setUnique(true);
			result = (Article) query.execute();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying an Article: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return result;
	}
}