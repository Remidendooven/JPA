package com.bankonet.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;



@Entity

@NamedQuery(name="nomsEmployés", query = "select e from Employe as e where upper(e.departement.nom) = :nomdept")

public class Employe extends Personne {

	private BigDecimal salaire;
	
	@OneToMany (cascade={CascadeType.PERSIST})
	private Set<Participation> paticipationSet = new HashSet<Participation>();
	
	@ManyToOne (optional=true)
	private Employe superieur;
	
	@ManyToOne
	private Departement departement;

	@ManyToMany (mappedBy="employesList")
	private List<Projet> projetsList = new ArrayList<Projet>();
	
	public Employe() {
		super();
	}
	
	public Employe(String nom) {
		super();
		this.setNom(nom);
	}

	public Employe(String nom, Departement departement, Employe superieur) {
		super();
		this.setNom(nom);
		this.superieur = superieur;
		this.departement = departement;
		departement.addEmploye(this);
	}

	
	public void ajouterProjet(Projet projet){
		projet.getEmployesList().add(this);
		this.getProjetsList().add(projet);
	}
	
	public void supprimerProjet (Projet projet){
		projet.getEmployesList().remove(this);
		this.projetsList.remove(projet);
	}
	
	
	public void ajouterParticipation (Projet projet, String fonction){
		Participation participation = new Participation();
		participation.setEmploye(this);
		participation.setFonction(fonction);
		participation.setProjet(projet);
		projet.getParticipationSet().add(participation);
		this.getPaticipationSet().add(participation);
	}
	
	public void supprimerParticipation(Projet projet, String fonction){
		Iterator<Participation> iterator = this.getPaticipationSet().iterator();
		while (iterator.hasNext())
		{
			Participation participation = iterator.next();
				if (participation.getProjet() == projet && participation.getFonction().equals(fonction))
				{
					this.getPaticipationSet().remove(participation);
					projet.getParticipationSet().remove(participation);
					break;
				}
		}
		
	}
	
	
	public List<Projet> getProjetsList() {
		return projetsList;
	}

	public void setProjetsList(List<Projet> projetsList) {
		this.projetsList = projetsList;
	}

	public BigDecimal getSalaire() {
		return salaire;
	}

	public void setSalaire(BigDecimal salaire) {
		this.salaire = salaire;
	}

	public Employe getSuperieur() {
		return superieur;
	}

	public void setSuperieur(Employe employe) {
		this.superieur = employe;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	
	public Set<Participation> getPaticipationSet() {
		return paticipationSet;
	}

	
	public void setPaticipationSet(Set<Participation> paticipationSet) {
		this.paticipationSet = paticipationSet;
	}

	
}
