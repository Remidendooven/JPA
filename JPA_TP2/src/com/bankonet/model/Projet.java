package com.bankonet.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Projet {
	
	@Id @GeneratedValue
	private int id;
	
	private String nom;
	
	@OneToMany (cascade={CascadeType.ALL})
	private Set<Participation> participationSet = new HashSet<Participation>();	
	
	@ManyToMany
	@JoinTable (name="PROJ_EMPLOY", joinColumns=@JoinColumn(name="project_id",referencedColumnName="ID"),
									inverseJoinColumns=@JoinColumn(name="employeId",referencedColumnName="id")
	
	)
	
	private Set<Employe> employesList = new HashSet<Employe>();
	
	
	
	public Projet() {
		super();
	}

	public Projet(String nom) {
		super();
		this.setNom(nom);
	}

	
	public Set<Employe> getEmployesList() {
		return employesList;
	}
	
	public void setEmployesList (Set<Employe> employeList){
		this.employesList = employeList;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
	    return id;
	  }
	
	public String getNom() {
	    return nom;
	  }
	
	public void setNom(String nom) {
	    this.nom = nom;
	  }

	
	public Set<Participation> getParticipationSet() {
		return participationSet;
	}

	
	public void setParticipationSet(Set<Participation> participationSet) {
		this.participationSet = participationSet;
	}
	
	
}
