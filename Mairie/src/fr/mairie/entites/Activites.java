package fr.mairie.entites;
//Rappel la classe entite permet de pouvoir 
//communiquer avec la base de données
import java.sql.Date;

public class Activites {
    
	// Quand un appel a la base de données sera affectue 
	// le formalisme ci-dessus sera appele totalement OU
	// partiellement utilisé
	private String nom;
	private String type;
	private Date date;
	private String lieu;
	private String responsable;
	private int nbParticipants;
	
	public Activites() {
		// TODO Auto-generated constructor stub
	}

	
	public Activites(String nom, String type, Date date, String lieu, String responsable, int nbParticipants) {
		super();
		this.nom = nom;
		this.type = type;
		this.date = date;
		this.lieu = lieu;
		this.responsable = responsable;
		this.nbParticipants = nbParticipants;
	}


	


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getLieu() {
		return lieu;
	}


	public void setLieu(String lieu) {
		this.lieu = lieu;
	}


	public String getResponsable() {
		return responsable;
	}


	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}


	public int getNbParticipants() {
		return nbParticipants;
	}


	public void setNbParticipants(int nbParticipants) {
		this.nbParticipants = nbParticipants;
	}

	
	
	
	
}
