package org.comenzi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import static javax.persistence.GenerationType.AUTO;


@Entity 
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Client { 
	// Atribute private
	
	@Id // @GeneratedValue(strategy = AUTO)
	private Integer id;
	
	private String nume;
	
	// Constructori
	public Client(Integer id, String nume) {
		this.id = id;
		this.nume = nume;
	}
	public Client() {
	}
	
	// Getteri si setteri
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	// Criteriu de egalitate
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	// Operatii specifice logicii modelului afacerii	
	
	@Override
	public String toString() {
		return "Client id:" + id + ", nume:" + nume;
	}	
}
