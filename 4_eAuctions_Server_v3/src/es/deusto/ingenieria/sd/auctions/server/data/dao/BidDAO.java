package es.deusto.ingenieria.sd.auctions.server.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Article;
import es.deusto.ingenieria.sd.auctions.server.data.domain.Bid;

//This class implements Singleton and DAO patterns
public class BidDAO extends DataAccessObjectBase implements IDataAccessObject<Bid> {

	private static BidDAO instance;	
	
	private BidDAO() { }
	
	public static BidDAO getInstance() {
		if (instance == null) {
			instance = new BidDAO();
		}		
		
		return instance;
	}
	
	@Override
	public void save(Bid object) {
		super.saveObject(object);
	}

	@Override
	public void delete(Bid object) {
		super.deleteObject(object);
	}

	@Override
	public List<Bid> getAll() {			
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		List<Bid> bids = new ArrayList<>();

		try {
			tx.begin();
			
			Extent<Bid> extent = pm.getExtent(Bid.class, true);

			for (Bid category : extent) {
				bids.add(category);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Bids: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return bids;
	}

	@Override
	public Bid find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		Article article = null;
		Bid result = null;

		try {
			tx.begin();
						
			Query<?> query = pm.newQuery("SELECT FROM " + Article.class.getName() + " WHERE number == " + param);
			query.setUnique(true);
			article = (Article) query.execute();
			result = article.getHighestBid();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a Bid: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}		

		return result;
	}
}