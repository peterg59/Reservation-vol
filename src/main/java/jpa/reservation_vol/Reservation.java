package jpa.reservation_vol;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name = "seq_res", sequenceName = "seq_res", initialValue = 1, allocationSize = 1)
public class Reservation {

	@Id
	@GeneratedValue(generator = "seq_res")
	private Integer id;

	@NotNull
	private String name;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private Integer age;

	@Column(unique = true)
	private String numeroReservation;

	@ManyToOne
	private Vol vol;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getNumeroReservation() {
		return numeroReservation;
	}

	public Integer getId() {
		return id;
	}
	
	public Vol getVol() {
		return vol;
	}
	
	public void setVol(Vol vol) {
		this.vol = vol;
	}

	public void setNumeroReservation() {
		this.numeroReservation = vol.getNumero() + "-" +getId();
	}
}
