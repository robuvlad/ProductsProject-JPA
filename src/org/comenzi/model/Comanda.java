package org.comenzi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.AUTO;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Basic;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
public class Comanda {
	// Atribute private
	@Id
	private Integer id;
	
	@Temporal(DATE)
	private Date dataComanda;

	@OneToMany(mappedBy = "comanda", cascade = ALL, orphanRemoval = true)
	private List<ArticolComanda> articole = new ArrayList<ArticolComanda>();
	
	@ManyToOne
	private Client client;
	
	// Constructori
	public Comanda(Integer id, Date dataComanda) {
		this.id = id;
		this.dataComanda = dataComanda;
	}	
	public Comanda(Integer id, Date dataComanda, List<ArticolComanda> articole,
			Client client) {
		this.id = id;
		this.dataComanda = dataComanda;
		this.articole = articole;
		this.client = client;
	}
	public Comanda() {
	}
	
	// Getteri si setteri
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDataComanda() {
		return dataComanda;
	}
	public void setDataComanda(Date dataComanda) {
		this.dataComanda = dataComanda;
	}
	public List<ArticolComanda> getArticole() {
		return articole;
	}
	public void setArticole(List<ArticolComanda> articole) {
		this.articole = articole;
	}
	public Double getValoareComanda(){
		if (articole.isEmpty())
			return null;
		
		Double valoare = 0.0;
		for (ArticolComanda articol: articole){
			valoare = valoare + articol.getValoareArticol();
		}
		
		return valoare;
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
		Comanda other = (Comanda) obj;
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
	
	public void adauga(Produs produs, Double cantitate){
		ArticolComanda articol = new ArticolComanda();
		articol.setComanda(this);
		articol.setProdus(produs);
		articol.setCantitate(cantitate);
		this.articole.add(articol);
	}
	
	public Boolean verificaProdus(Produs produs){
		return null;
	}
	
	public ArticolComanda getArticolComanda(Produs produs){
		return null;
	}
	
	public Double getValoareComandataProdus(Produs produs){
		return null;
	}
	
	@Override
	public String toString() {
		return "Comanda id:" + id + ", dataComanda:" + dataComanda
				+ ", " + client + ", valoare comanda:"
				+ getValoareComanda();
	}	
	
	
}
