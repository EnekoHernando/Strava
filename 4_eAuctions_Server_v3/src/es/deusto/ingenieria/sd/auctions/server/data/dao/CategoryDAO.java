package es.deusto.ingenieria.sd.auctions.server.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.auctions.server.data.domain.Category;

//This class implements Singleton and DAO patterns
public class CategoryDAO extends DataAccessObjectBase implements IDataAccessObject<Category> {

	private static CategoryDAO instance;	
	
	private CategoryDAO() { }
	
	public static CategoryDAO getInstance() {
		if (instance == null) {
			instance = new CategoryDAO();
		}		
		
		return instance;
	}
	
	@Override
	public void save(Category object) {
		super.saveObject(object);
	}

	@Override
	public void delete(Category object) {
		super.deleteObject(object);
	}

	@Override
	public List<Category> getAll() {				
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		Transaction tx = pm.currentTransaction();

		List<Category> categories = new ArrayList<>();
		
		try {
			tx.begin();
			
			Extent<Category> extent = pm.getExtent(Category.class, true);

			for (Category category : extent) {
				categories.add(category);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Categories: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return categories;		
	}

	@Override
	public Category find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		Transaction tx = pm.currentTransaction();
		
		Category result = null; 

		try {
			tx.begin();
						
			Query<?> query = pm.newQuery("SELECT FROM " + Category.class.getName() + " WHERE name == '" + param + "'");
			query.setUnique(true);
			result = (Category) query.execute();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a Category: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return result;
	}
}