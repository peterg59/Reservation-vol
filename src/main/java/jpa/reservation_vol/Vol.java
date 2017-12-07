package jpa.reservation_vol;

import java.time.*;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@SequenceGenerator(name = "seq_vol", sequenceName = "seq_vol", initialValue = 1, allocationSize = 1)
public class Vol {
	@Id
	@GeneratedValue(generator = "seq_vol")
	private Integer id;
	
	@Column(unique = true, length = 4, nullable = false)
	private String numero;
	
	@NotNull
	private Integer nbrePlace;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private Type type;
	
	@NotNull
	private String villeDepart;
	
	@NotNull
	private String villeArrivee;
	
	@NotNull
	private LocalDate dateDepart;
	
	@OneToMany(mappedBy = "vol")
	private List<Reservation> reservations = new ArrayList<>();
	
	public Vol(String n, int nPlace, Type t, String vDepart, String vArrivee, LocalDate d) {
		numero = n;
		nbrePlace = nPlace;
		type = t;
		villeDepart = vDepart;
		villeArrivee = vArrivee;
		dateDepart = d;
	}
	
	public Vol() {
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getNbrePlace() {
		return nbrePlace;
	}

	public void setNbrePlace(int nbrePlace) {
		this.nbrePlace = nbrePlace;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public LocalDate getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(LocalDate dateDepart) {
		this.dateDepart = dateDepart;
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	@Override
	public String toString() {
		return getNumero() + " | " + getType() + " | " + getReservations().size() +"/"+ getNbrePlace() +" | " + getVilleDepart() + " | " + getVilleArrivee() + " | " + getDateDepart();
	}
}
