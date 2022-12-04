package dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import data.domain.User;

public class UserDAO extends DataAccessObjectBase implements IDataAccessObject<User> {

	private static UserDAO instance;
	private UserDAO() {}
	public static UserDAO getInstance() {
		if(instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	@Override
	public void save(User object) {
		super.saveObject(object);
		
	}
	public void updateUser(User user) {
		PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx = pm.currentTransaction();
	    
	    try {
	    	tx.begin();
	    	pm.makePersistent(user);
	    	tx.commit();
	     } catch (Exception ex) {
		   	System.out.println("   $ Error retreiving an extent: " + ex);
	     } finally {
		   	if (tx != null && tx.isActive()) {
		   		tx.rollback();
		   	}
				
	   		pm.close();
	     }
	}
	@Override
	public void delete(User object) {
		super.deleteObject(object);
	}

	@Override
	public List<User> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		List<User> users = new ArrayList<>();
		
		try {
			tx.begin();
			
			Extent<User> extent = pm.getExtent(User.class, true);

			for (User category : extent) {
				users.add(category);
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

		return users;
	}

	@Override
	public User find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		User result = null; 

		try {
			tx.begin();
						
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE email == '"+param+"'");
			System.out.println("QUERY:::::::::::D " + query);
			query.setUnique(true);
			result = (User) query.execute();
			System.out.println("RESULTADO DEL QUERY:::::::::::::: " + result);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying an User: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return result;
	}

}
