package jpa.reservation_vol;

import javax.persistence.EntityManager;


public class ReservationDAO {
	
	public static void createReservation(Reservation reservation) {
		EntityManager em = DatabaseHelper.createEntityManager();
		DatabaseHelper.beginTx(em);
		em.persist(reservation);
		DatabaseHelper.commitTxAndClose(em);
	}
}
