package com.bankonet.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Departement {

	@Id @GeneratedValue
  	private int id;

	@Column (length = 25)
	private String nom;

  private String lieu;
  
 @OneToMany(mappedBy="departement")
  private Collection<Employe> employes = new ArrayList<Employe>();

   public Departement(){
	  super();
  }
  
  public Departement(String nom, String lieu) {
	this();
	this.setNom(nom);
	this.setLieu(lieu);
  }

public int getId() {
    return id;
  }

	public void setId(int id){
		this.id = id;
	}

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getLieu() {
    return lieu;
  }

  public void setLieu(String lieu) {
    this.lieu = lieu;
  }

  public Collection<Employe> getEmployes() {
    return employes;
  }

  
  public void addEmploye(Employe employe) {
    // Si l'employ� est d�j� dans un d�partement, il faut l'enlever de
    // ce d�partement dans la liste des employ�s de ce d�partement
    Departement ancienDept;
    if ((ancienDept = employe.getDepartement()) != null) {
      ancienDept.employes.remove(employe);
    }
    employes.add(employe);
    employe.setDepartement(this);
  }

}
