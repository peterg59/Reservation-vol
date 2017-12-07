package jpa.reservation_vol;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		String str;
		afficheMenu();
		do {
			Scanner sc = new Scanner(System.in);
			str = sc.nextLine();
			switch (str) {
			case "1":
				afficheMenuVol();
				break;
			case "2":
				afficheMenuReservation();
				break;
			case "3":
				break;
			}
		} while (str != "3");
	}

	public static void afficheMenu() {
		System.out.println("(1) Gestion des vols");
		System.out.println("(2) Gestion des réservations");
		System.out.println("(3) Quitter");
		System.out.println("Entrez votre choix :");
	}

	public static void afficheMenuVol() {
		System.out.println("(1) Creation d'un vol");
		System.out.println("(2) Liste des vols");
		System.out.println("(3) Recherche d'un avion par numero");
		System.out.println("(4) Rechercher un avion par ville de départ et d’arrivée");
		System.out.println("(5) Quitter");
		System.out.println("Entrez votre choix :");
		selectMenuVol();
	}

	public static void afficheMenuReservation() {
		System.out.println("(1) Créer une réservation");
		System.out.println("(2) Voir les réservations d’un vol");
		System.out.println("(3) Annuler une réservation");
		System.out.println("(4) Voir toutes les réservations d’une personne");
		System.out.println("Entrez votre choix :");
		selectMenuRes();
	}

	public static void selectMenuVol() {
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		Type type;
		int num;
		EntityManager em;
		TypedQuery<Vol> query;
		List<Vol> list;
		String str2;
		
		switch (str) {
		case "1":
			Vol vol = new Vol();
			
			System.out.println("Entrez le numero de vol :");
			str = sc.nextLine();
			vol.setNumero(str);
			
			System.out.println("Entrez le type d'avion :");
			type = Type.valueOf(sc.nextLine());
			vol.setType(type);
			
			System.out.println("Entrez le nombre de place disponible :");
			num = Integer.parseInt(sc.nextLine());
			vol.setNbrePlace(num);

			System.out.println("Entrez la ville de depart :");
			str = sc.nextLine();
			vol.setVilleDepart(str);
			
			System.out.println("Entrez la ville d'arrivee :");
			str = sc.nextLine();
			vol.setVilleArrivee(str);
			
			System.out.println("Entrez la date :");
			str = sc.nextLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate localDate = LocalDate.parse(str, formatter);
			vol.setDateDepart(localDate);
			
			VolDAO.createVol(vol);
			break;
		
		case "2":
			em = DatabaseHelper.createEntityManager();
			 query = em.createQuery("from Vol", Vol.class);
			list = query.getResultList();
			for (Vol v : list) {
				System.out.println(v);
			}
			
			em.close();
			break;
		
		case "3":
			System.out.println("Entrez le numero de vol :");
			str = sc.nextLine();
			em = DatabaseHelper.createEntityManager();
			query = em.createQuery("from Vol v where v.numero =:numero", Vol.class);
			query.setParameter("numero", str);
			list = query.getResultList();
			for (Vol v : list) {
				System.out.println(v);
			}
			em.close();
			break;
		case "4":
			System.out.println("Entrez la ville de depart :");
			str = sc.nextLine();
			
			System.out.println("Entrez la ville d'arrivee:");
			str2 = sc.nextLine();
			
			em = DatabaseHelper.createEntityManager();
			query = em.createQuery("from Vol v where v.villeDepart =:villeDepart and v.villeArrivee =:villeArrivee", Vol.class);
			query.setParameter("villeDepart", str);
			query.setParameter("villeArrivee", str2);
			list = query.getResultList();
			for (Vol v : list) {
				System.out.println(v);
			}
			em.close();
			break;
		case "5":
			afficheMenu();
			break;
		}
	}
	
	public static void selectMenuRes() {
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		Type type;
		int num;
		EntityManager em;
		TypedQuery<Vol> query;
		TypedQuery<Reservation> queryReservation;
		Vol vol;
		List<Vol> list;
		List<Reservation> listReservation;
		
		switch (str) {
		case "1":
			Reservation res = new Reservation();
			
			System.out.println("Entrez votre nom :");
			str = sc.nextLine();
			res.setLastName(str);
			
			System.out.println("Entrez votre prenom :");
			str = sc.nextLine();
			res.setName(str);
			
			System.out.println("Entrez votre age :");
			num = Integer.parseInt(sc.nextLine());
			res.setAge(num);
			
			System.out.println("Entrez votre vol :");
			str = sc.nextLine();
			em = DatabaseHelper.createEntityManager();
			query = em.createQuery("from Vol v where v.numero =:numero", Vol.class);
			query.setParameter("numero", str);
			vol = query.getSingleResult();
			res.setVol(vol);
			
			res.setNumeroReservation();
			ReservationDAO.createReservation(res);
			em.close();
			break;
		
		case "2":
			em = DatabaseHelper.createEntityManager();
			queryReservation = em.createQuery("from Reservation order by lastName, name", Reservation.class);
			listReservation = queryReservation.getResultList();
			for (Reservation r : listReservation) {
				System.out.println(r);
			}
			
			em.close();
			break;
		case "3":
			break;
		case "4":
			break;
		case "5":
			afficheMenu();
			break;
		}
	}
}
