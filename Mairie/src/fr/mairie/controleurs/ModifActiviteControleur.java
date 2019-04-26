package fr.mairie.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.mairie.modeles.ModeleActivites;
import fr.mairie.modeles.ModeleMairie;
import fr.mairie.vues.AjoutActivite;
import fr.mairie.vues.Jeux;
import fr.mairie.vues.Mairie;
import fr.mairie.vues.ModifActivite;

public class ModifActiviteControleur implements ActionListener {
	
//fichiers dont on a besoin pour réaliser l'action
    private ModeleMairie modele ;				
	private Mairie vueMairie;	
	private ModifActivite vueModifActivite;
	
	//ceci est un test  
	private ModeleActivites modeleTabActivites;
	private Jeux jeux;
	

	public ModifActiviteControleur(ModifActivite modifActivite) {
		// TODO Auto-generated constructor stub
		this.vueModifActivite = modifActivite ;
		this.enregistrerEcouteur() ;
	}

	private void enregistrerEcouteur() {
		// TODO Auto-generated method stub
		this.vueModifActivite.getAnnuler().addActionListener(this);
		this.vueModifActivite.getValider().addActionListener(this);
		this.vueModifActivite.getMois().addActionListener(this);
		this.vueModifActivite.getAnnees().addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println ("ModifActiviteControleur::actionPerformed (ActionEvent e)");
		Object sourceEvenement = e.getSource();
		
		if(sourceEvenement==this.vueModifActivite.getAnnuler()){
			
			this.vueModifActivite.initialiser();
			
		}
		if(sourceEvenement==this.vueModifActivite.getValider()){
		/*	private JTextField lieu = new JTextField(lieuAct);
			private JTextField responsable = new JTextField(responsableAct);
			private JTextField nbPart = new JTextField(String.valueOf(nbPartAct));
			private JTextField nom = new JTextField(nomAct);
	        private JComboBox jour = new JComboBox(listeJour) ;//Menu Déroulant
	        private JComboBox mois = new JComboBox(month) ;
	        private JComboBox annees = new JComboBox(year) ;
	        private JComboBox mrmme = new JComboBox(pers) ;
	        ***************getActiviteAct()
	        
	        String lieuString = lieu.getText();*/
			
			Integer id= this.vueModifActivite.getIdAct();
			
			String jour = this.vueModifActivite.getJour().getSelectedItem().toString();
		    String mois =this.vueModifActivite.getMois().getSelectedItem().toString();
		    String annees =this.vueModifActivite.getAnnees().getSelectedItem().toString();
	        
		    System.out.println("actionPerfomed :: Date :  Jour --> " + jour + " Mois --> " + mois + " Annees --> "+ annees);
		    String dateAInserer = null;
		    if(jour.equals("...")||mois.equals("...")||annees.equals("...")) {
		    	dateAInserer=this.vueModifActivite.getDateAct();
		    }
		    
		    
		    
		    JTextField lieu = this.vueModifActivite.getLieu();//ajouter pour tous recupérer
            String mrmme =this.vueModifActivite.getMrmme().getSelectedItem().toString();
			JTextField responsable = this.vueModifActivite.getResponsable();
			JTextField nbParticipants = this.vueModifActivite.getNbPart();
			JTextField nom = this.vueModifActivite.getNom();
			
			//Conversion JTextField vers String 
			//pour les données concernées
			String lieuString = lieu.getText();
			String responsableString = responsable.getText();
			String nbParticipantsString=null;
			nbParticipantsString = nbParticipants.getText();
			String nomString = nom.getText();
			String typeAct = vueModifActivite.getActiviteAct();
			
			//String dateAInserer = null;
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
			
			System.out.println("actionPerfomed date a inserer -->"+ dateAInserer);
			
			
			if(nomString.length()>0&&mrmme.equals("...")) {
				JOptionPane fails=new JOptionPane();
				fails.showMessageDialog(fails,"Veuillez renseigner le champ Civilité","Echec "						
						+ "admin@admin.fr .",0);
			}else {
			
				try {
				
					/*	 String mrmme =this.vueModifActivite.getMrmme().getSelectedItem().toString();
				 	String lieuString = lieu.getText();
					String responsableString = responsable.getText();
					String nbParticipantsString=null;
					nbParticipantsString = nbParticipants.getText();
					String nomString = nom.getText();
					String typeAct = vueModifActivite.getActiviteAct()	 */
				 
					Boolean resultat = this.modele.ModifActivite(id,dateAInserer,typeAct, lieuString, mrmme+" "+ responsableString,nbParticipantsString,nomString);
															
					System.out.println("ActionPerformed --> Resultat du login");
					if(resultat==true){
						System.out.println(" ActionPerformed --> Données modifie dans la base ");
		//A VOIR ABS	System.out.println(" Actionperfomed --> Rafraichir la JTable --> " + typeAct);			
						// this.modeleTabActivites.actualiser();
						

						
						JOptionPane inserer=new JOptionPane();
						inserer.showMessageDialog(inserer,"Cliquez pour continuer"," Element Modifie ", 1);
						inserer.setVisible(false);
						//vueAjoutActivite.setVisible(false);
						//			vue2.creerBarreAuthentifie();
						
						
						
//Tentative de mise à jour de la table
					/*	modeleTabActivites.fireTableRowsUpdated(0, modeleTabActivites.getUntypeAct().size() - 1);
						modeleTabActivites.actualiser();*/
						
                     //   this.jeux.getModeleListeJeux();
				//		modeleTabActivites.fireTableDataChanged();
				//		modeleTabActivites.fireTableStructureChanged();
						
						
						
					/*	JTable tab = this.jeux.getTabJeux();
						ModeleActivites NewmodeleTabActivites = new ModeleActivites(typeAct);
						NewmodeleTabActivites.actualiser();
						tab.setModel(NewmodeleTabActivites);*/
						
						
						
						//this.jeux.setTabJeux(tab);
						/*   JTable tab=jeux.getTabJeux();
	                	 tab.setModel(modeleTabActivites);*/
	           
				    	}else {
				    		
				    		JOptionPane fails=new JOptionPane();
							 fails.showMessageDialog(fails,"Erreur","Echec de la Modif : remplissez "
							+ "tous les champs si c'est déjà le cas : Erreur Echec de connection de la BDD, contactez l'administrateur"
							+ "admin@admin.fr .",0);

				
						    fails.setVisible(false);
				    		
				    	}
				
					}
				catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			}
	
    }
			
			
	    
	}
	
	
	
	
	

}
