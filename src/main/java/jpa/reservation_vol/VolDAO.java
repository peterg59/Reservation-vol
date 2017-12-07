package jpa.reservation_vol;

import javax.persistence.EntityManager;


public class VolDAO {
	public static void createVol(Vol vol) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.persist(vol);
		DatabaseHelper.commitTxAndClose(em);
	}
}
