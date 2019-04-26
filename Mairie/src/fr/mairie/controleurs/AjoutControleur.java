package fr.mairie.controleurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import fr.mairie.modeles.ModeleMairie;
import fr.mairie.vues.AjoutActivite;
import fr.mairie.vues.Mairie;

public class AjoutControleur implements ActionListener{
	
	
	
    private ModeleMairie modele ;			
	
	private Mairie vueMairie;
	
	private AjoutActivite vueAjoutActivite;
	//les autres vues d'ajout seront ajoutées ultérieurement

	public AjoutControleur(AjoutActivite ajoutActivite) {
		// TODO Auto-generated constructor stub
		this.vueAjoutActivite = ajoutActivite ;
		this.enregistrerEcouteur() ;
	}
	
	
	
	private void enregistrerEcouteur(){
		System.out.println("ControleurAuthentification::enregistrerEcouteur()") ;
		
		//Ajouter une activite
		//Recup bouton + comboBox
		this.vueAjoutActivite.getAnnuler().addActionListener(this);
		this.vueAjoutActivite.getValider().addActionListener(this);
		this.vueAjoutActivite.getMois().addActionListener(this);
		this.vueAjoutActivite.getAnnees().addActionListener(this);
		this.vueAjoutActivite.getLesacts().addActionListener(this);
		
		//Ajouter ...
		//this.vueSanteAssoc.getAnnuler().addActionListener(this);
		//this.vueSanteAssoc.getValider().addActionListener(this);
		
	}
	
	// VOTRE CODE :
	// Méthode de traitement des événements
	
	public void actionPerformed(ActionEvent e){
		System.out.println ("AuthentificationControleur::actionPerformed (ActionEvent e)");
		Object sourceEvenement = e.getSource();
		
		if(sourceEvenement==this.vueAjoutActivite.getAnnuler()){
			
			this.vueAjoutActivite.initialiser();
			
		}
		
		if(sourceEvenement==this.vueAjoutActivite.getValider()){
			    //Nous n'affichons pas de données par conséquent, 
			    //nous n'avons pas besoin de modele d'affichage et de JTable
			    //Date
			    String jour = this.vueAjoutActivite.getJour().getSelectedItem().toString();
			    String mois =this.vueAjoutActivite.getMois().getSelectedItem().toString();
			    String annees =this.vueAjoutActivite.getAnnees().getSelectedItem().toString();
			    
			    
	            JTextField lieu = this.vueAjoutActivite.getLieu();//ajouter pour tous recupérer
	            String mrmme =this.vueAjoutActivite.getMrmme().getSelectedItem().toString();
				JTextField responsable = this.vueAjoutActivite.getResponsable();
				JTextField nbParticipants = this.vueAjoutActivite.getNbParticipants();
				String act =this.vueAjoutActivite.getLesacts().getSelectedItem().toString();
				JTextField nom = this.vueAjoutActivite.getNomAct();
				
				//Conversion JTextField vers String 
				//pour les données concernées
				String lieuString = lieu.getText();
				String responsableString = responsable.getText();
				String nbParticipantsString=null;
				nbParticipantsString = nbParticipants.getText();
				String nomString = nom.getText();
				
				
				System.out.println("Ajout Controleur ::actionPerformed()----> nbParticipantsString content  " + nbParticipantsString);
			
				/*tranformation de la date en données comprehensible pour la 
				 base de données.*/
				
				//Rappel des mois dans la liste données dans la vue d'ajout
				//String [] month = {"...","Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet"
				//,"Aout","Septembre","Octobre","Novembre","Décembre"};
				String dateAInserer = null;
				//Mysql format date : 2018-04-22
				if(mois=="Janvier") {
					dateAInserer=annees+"-01-"+jour;
				}
				
				else if(mois=="Fevrier") {
					dateAInserer=annees+"-02-"+jour;
				}
				else if(mois=="Mars") {
					dateAInserer=annees+"-03-"+jour;
				}	
				
				else if(mois=="Avril") {
					dateAInserer=annees+"-04-"+jour;
				}	
				
				else if(mois=="Mai") {
					dateAInserer=annees+"-05-"+jour;
				}
				
				else if(mois=="Juin") {
					dateAInserer=annees+"-06-"+jour;
				}	
				
				else if(mois=="Juillet") {
					dateAInserer=annees+"-07-"+jour;
				}
				
				else if(mois=="Aout") {
					dateAInserer=annees+"-08-"+jour;
				}
				
				else if(mois=="Septembre") {
					dateAInserer=annees+"-09-"+jour;
				}
				
				else if(mois=="Octobre") {
					dateAInserer=annees+"-10-"+jour;
				}
				else if(mois=="Novembre") {
					dateAInserer=annees+"-11-"+jour;
				}
				else if(mois=="Decembre") {
					dateAInserer=annees+"-12-"+jour;
				}
				
				
				
				try {
					
					//String date,String typeAct, String lieu, String responsable,String nbParticipants,
					//String nomAct,AjoutActivite vueAjoutActivite
					
					Boolean resultat = this.modele.AjouterActivite(dateAInserer, act,lieuString, mrmme +" "+responsableString,nbParticipantsString, nomString);//vueAuthentification
					System.out.println("Resultat du login");
					if(resultat==true){
						System.out.println("yes co ok");
						Mairie vueParente = (Mairie) this.vueAjoutActivite.getParent();  //VueFoody -> Mairie
					
					    vueParente.setBarreMenusModeConnecte() ;
					    }
					
					}
				 catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
		
	    }
	}

	public void reinitialiser(){
		
		this.vueAjoutActivite.initialiser();
		
		
	}
	

	
	
	
}
	


