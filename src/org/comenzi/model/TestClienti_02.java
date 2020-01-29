package org.comenzi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

public class TestClienti_02 {
	public static void main(String[] args){
			EntityManagerFactory emf = Persistence.
				createEntityManagerFactory("ProduseJPA");
			EntityManager em = emf.createEntityManager();
			List<Comanda> lstComenzi;
			
			// Clean-up comenzi
			em.getTransaction().begin();
			em.createQuery("DELETE FROM ArticolComanda c").executeUpdate();
			em.createQuery("DELETE FROM Comanda c").executeUpdate();
			em.getTransaction().commit();
			
			// Populare clienti
			List<Client> lstClientiPersistenti = em.
					createQuery("SELECT c FROM Client c").getResultList();
			
			if (!lstClientiPersistenti.isEmpty()){
				em.getTransaction().begin();
				for (Client c: lstClientiPersistenti)
					em.remove(c);
				em.getTransaction().commit();					
			}
			// Create
			em.getTransaction().begin();
			em.persist(new Client(101, "Alfa SRL"));
			em.persist(new Client(102, "Beta SRL"));
			em.persist(new Client(103, "Gama SRL"));
			em.persist(new Client(104, "Delta SRL"));
			em.getTransaction().commit();
			// Read after create				
			lstClientiPersistenti = em.
					createQuery("SELECT c FROM Client c").getResultList();
			
			System.out.println("Lista clienti persistenti/salvati in baza de date");
			for (Client c: lstClientiPersistenti)
				System.out.println("Id: " + c.getId() + ", nume: " + c.getNume());
					
			// Populare produse
			List<Produs> lstProdusePersistente = em.
					createQuery("SELECT p FROM Produs p").getResultList();
			if (!lstProdusePersistente.isEmpty()){
				em.getTransaction().begin();
				for (Produs p: lstProdusePersistente)
					em.remove(p);
				em.getTransaction().commit();				
			}
			// Create
			em.getTransaction().begin();
			em.persist(new Produs(1, "Produs A", "kg", 10.0));
			em.persist(new Produs(2, "Produs B", "bc", 15.0));
			em.persist(new Produs(3, "Produs C", "kg", 20.0));
			em.persist(new Produs(4, "Produs D", "bc", 25.0));
			em.getTransaction().commit();
			// Read after create				
			lstProdusePersistente = em.
					createQuery("SELECT p FROM Produs p").getResultList();
			
			
			System.out.println("Lista produselor persistente/salvate in baza de date");
			for (Produs p: lstProdusePersistente)
				System.out.println("Cod: " + p.getCod() + ", nume: " + p.getDenumire());					
			
			// Populare comenzi	
			Comanda c1 = new Comanda(1, new Date());
			c1.adauga(lstProdusePersistente.get(0), 20.0);
			c1.adauga(lstProdusePersistente.get(1), 15.0);
			// Create
			em.getTransaction().begin();
			em.persist(c1);
			em.getTransaction().commit();
			//em.getTransaction().rollback();
			// Read after create				
			lstComenzi = em.
					createQuery("SELECT c FROM Comanda c").getResultList();
			
			
			System.out.println("Lista comenzilor persistente/salvate in baza de date");
			for (Comanda c: lstComenzi)
				System.out.println("Id: " + c.getId() + 
						", valoare: " + c.getValoareComanda());						
	}
		
}
