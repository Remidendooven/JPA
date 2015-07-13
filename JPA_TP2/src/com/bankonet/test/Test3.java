package com.bankonet.test;

import java.math.BigDecimal;
import java.util.List;

import com.bankonet.model.Employe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Test3 {

	
	
	public static void main(String[] args) {
					
		EntityManagerFactory emf = null;
	    EntityManager em = null;
				 
		emf = Persistence.createEntityManagerFactory("Employes");
	    em = emf.createEntityManager();
		
	    
	   	String req1 = "Select e from Employe as e where upper(e.departement.nom) = :nomdept";
		Query quer1 = em.createQuery(req1);
		quer1.setParameter("nomdept", "DIRECTION");
		
		List<Employe> result1 = (List<Employe>)quer1.getResultList();
		
		
		
		String req2 = "Select e.nom, e.salaire from Employe as e where upper(e.departement.nom) = :nomdept";
		Query quer2 = em.createQuery(req2);
		quer2.setParameter("nomdept", "DIRECTION");
		
		List<Object[]> result2 = (List<Object[]>)quer2.getResultList();
			
		
		
		Query quer3 = em.createNamedQuery("nomsEmploy�s");
		quer3.setParameter("nomdept", "DIRECTION");
		List<Employe> result3 = (List<Employe>)quer3.getResultList();
	
		
		String req4 = "Select e from Employe as e where upper(e.departement.nom) = :nomdept";
		Query quer4 = em.createQuery(req4);
		quer4.setParameter("nomdept", "DIRECTION");
		List<Employe> result4 = (List<Employe>)quer4.getResultList();

		EntityTransaction tx = em.getTransaction();
	    tx.begin();
		
	    for (Employe employe : result4){
			employe.setSalaire((employe.getSalaire().multiply(new BigDecimal(1.05))));
			em.persist(employe);
		}
		
	    
	    String req5 = "Update Employe employe set employe.salaire = 2200";
	    Query quer5 = em.createQuery(req5);
	    int updateCount = quer5.executeUpdate();
	    
	    tx.commit();
	  
	    System.out.println("nombre de salaire modifi�s : "+updateCount);
	    
	    System.out.println("salaire");
	    
		em.close();
   		emf.close();
		
	}
}
