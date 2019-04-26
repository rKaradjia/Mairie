package fr.mairie.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import fr.mairie.entites.Activites;
import fr.mairie.modeles.ModeleMairie;
import fr.mairie.technique.ConnexionException;
//import fr.mairie.technique.ConnexionException;
import fr.mairie.vues.Mairie;
import fr.mairie.vues.ModifActivite;

public class ControleurBoutonSelectionActivite implements ActionListener{

	private ModeleMairie modele ;//Requete SQL
	
	private int row ;
	private int column ;
	private JTable table ;
	private String activite;
	
	private Mairie vuepr;
	
	//La méthode ci-dessus est appelé également par ModeleActivite
	//Ici on en a besoin pour se souvenir de l'identifiant dans le 
	//tableau
	
	ArrayList<Activites> evenements;

	


	/*public ControleurBoutonSelectionActivite(String activite1) {
		// TODO Auto-generated constructor stub
		this.activite=activite1;
	}*/

	

	public ControleurBoutonSelectionActivite(String string) {
		this.activite=string;
		evenements = new ArrayList<Activites>(ModeleMairie.getUneActivites(activite));
		// TODO Auto-generated constructor stub
	}



	public int getColumn() {
		return column;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}


	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	
	public void setRow(int row) {
		this.row = row;
	}
	
	

	public int getRow() {
		return row;
	}

	

	

	
	
	
/////////////////Données d'une activite////////////////	
	public String getNomActList(){
		
		String nomAct = evenements.get(getRow()).getNom();
		System.out.println("ControleurBoutonSelectionActivite::getNomActList() " + nomAct );
		return nomAct;
	}
	
	
	public Date getDateActList(){
	
        Date date = evenements.get(getRow()).getDate();		
		System.out.println("ControleurBoutonSelectionActivite::getDateActList() " + date );
		return date;
	}
	
	
	public String getLieuActList(){
	
		
		String lieu = evenements.get(getRow()).getLieu();
		System.out.println("ControleurBoutonSelectionActivite::getLieuActList() " + lieu );
		return lieu;
	}
	
	public String getResponsableActList(){
			
			String responsable = evenements.get(getRow()).getResponsable();
			System.out.println("ControleurBoutonSelectionActivite::getResponsableActList() " + responsable );
			return responsable;
		}
	
	public Integer getNbparticipantsActList(){
		
			Integer nbParticipants = evenements.get(getRow()).getNbParticipants();
			System.out.println("ControleurBoutonSelectionActivite::getNbParticipantsActList() " + nbParticipants );
			return nbParticipants;
	}

	
////////////////Activite///////////////////
	public String getActivite() {
		return activite;
	}


	public void setInitActivite(String activite) {
		this.activite = activite;
	}
	
	
///////////////////////////////////////////	
	
	
	


	public void getModifAct(Mairie vuepr, Integer resultat, String dateString, String lieu, String responsable, Integer nbPart, String string, String nomact){
		/* Remplacer par la vue qui affichera les champs et les données 
		 * correspondantes que l'utilisateur peut modifier */
		ModifActivite modifAct = new ModifActivite(vuepr,resultat,dateString,lieu,responsable,nbPart,activite,nomact);
		modifAct.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("ControleurBoutonVisiteurs::actionPerformed()");
	    System.out.println("Recuperation de la ligne dans le tableau Visiteur "+this.getRow());//ok 9
	    String nomAct = this.getNomActList();
	    Date dateAct = this.getDateActList();
	    String lieuAct = this.getLieuActList();
	    String responsableAct = this.getResponsableActList();
	    Integer nbPartAct = this.getNbparticipantsActList();
	    System.out.println("actionPerformed() Verification des données ==>  Nom --> "+ nomAct
	    		+ " Date --> " + dateAct + " Lieu --> " + lieuAct + " Responsable --> " + responsableAct + " Nombre --> " + nbPartAct 
	    		+ " Type --> " + this.getActivite());
	     Integer idAct;
	     
	     String pattern = "yyyy-MM-dd";

	 
	     DateFormat df = new SimpleDateFormat(pattern);
	     // Date today = Calendar.getInstance().getTime();        
	     String dateString = df.format(dateAct);
	     System.out.println("Verification de la date " + dateString);
	  
	     //stockage du résultat en dehors du try catch
	     Integer resultat = null; 
	  
	     Boolean droit = false;
	          try {
				droit = this.modele.verifierDroitsActivite(ModeleMairie.getLg(), "droitModifActivites");
			} catch (ConnexionException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	     if(droit==true) {
	    	
			
			try {
				 System.out.println("actionPerformed() SQL Request");
				resultat = this.modele.getIdAct(dateString,lieuAct,responsableAct,nbPartAct,this.getActivite(),nomAct);
			}
			 catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("actionPerformed SQL Result --> " + resultat);
	
		
	    this.getModifAct(vuepr,resultat,dateString, lieuAct, responsableAct, nbPartAct, this.getActivite(),
				nomAct);
						
	     }else {
	    	 JOptionPane fails=new JOptionPane();
				fails.showMessageDialog(fails,"Vous n'avez pas les autorisations","Echec "
						+ "--> vous n'avez pas les droits pour cette action"
						+ "admin@admin.fr .",0);
	     }
	 
	}

}
