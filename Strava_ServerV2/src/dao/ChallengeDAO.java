package dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import data.domain.Challenge;

public class ChallengeDAO extends DataAccessObjectBase implements IDataAccessObject<Challenge>{
	
	private static ChallengeDAO instance;
	
	private ChallengeDAO() {}
	public static ChallengeDAO getInstance() {
		if(instance == null) {
			instance = new ChallengeDAO();
		}
		return instance;
	}
	
	
	@Override
	public void save(Challenge object) {
		super.saveObject(object);
	}

	@Override
	public void delete(Challenge object) {
		super.deleteObject(object);
	}

	@Override
	public List<Challenge> getAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		List<Challenge> challenges = new ArrayList<>();
		
		try {
			tx.begin();
			Extent<Challenge> extent = pm.getExtent(Challenge.class, true);
			for (Challenge challenge : extent) {
				challenges.add(challenge);
			}
			tx.commit();
		} catch (Exception e) {
			System.out.println(" # ERROR GETTING THE CHALLENGES.");
		} finally {
			if (tx != null && tx.isActive()) {
			tx.rollback();
		}

		pm.close();
	}

	return challenges;
	}

	@Override
	public Challenge find(String param) {
		return null;
	}

}
