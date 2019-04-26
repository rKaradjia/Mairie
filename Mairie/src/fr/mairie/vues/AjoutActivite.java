package fr.mairie.vues;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import fr.mairie.controleurs.AjoutControleur;
import fr.mairie.controleurs.AuthentificationControleur;
import java.awt.Component;

public class AjoutActivite extends JDialog   {

	Mairie vueParente;
	
	AjoutControleur controleur ;
	
	//JPasswordField inutile ici
	String [] listeJour = {"...","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21"
			,"22","23","24","25","26","27","28","29","30","31"};
	private JComboBox jour = new JComboBox(listeJour) ;//Menu Déroulant
	String [] month = {"...","Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Décembre"};
	private JComboBox mois = new JComboBox(month) ;//Menu Déroulant
	String []year = {"...","2019","2020","2021","2022"};
	private JComboBox annees = new JComboBox(year) ;//Menu Déroulant
	private JTextField lieu = new JTextField() ;
	
	String []pers = {"...","Monsieur","Madame"};
	private JComboBox mrmme = new JComboBox(pers) ;//Menu Déroulant
	private JTextField responsable = new JTextField() ;
	private JTextField nbParticipants = new JTextField() ;
	String [] typeact = {"...","films","jeux","musique","sport"};
	private JComboBox lesacts = new JComboBox(typeact) ;//Menu Déroulant
	private JTextField nomAct = new JTextField() ;
	
	private JButton valider = new JButton("Valider") ;
	private JButton annuler = new JButton("Annuler") ;
	
	public AjoutActivite(Mairie vueParente){
		// Appel du constructeur de la super-classe
		//	Troisième argument : true pour indiquer que la boîte de dialogue est modale 
		super(vueParente,"AjoutActivite",true) ;
		
		// Mémorise la vue parente qui est la fenêtre principale de l'application
		this.vueParente = vueParente ;
		
		// Crée le formulaire de saisie
		this.creerInterfaceUtilisateur() ;
		
		// Redimensionne la boîte de dialogue (dimensions adaptées aux composants qui s'y trouvent)
		this.pack() ;
		
		// Positionne la boîte de dialogue au centre de l'écran
		this.setLocationRelativeTo(null) ;
		
		// Empêche le redimensionnement par l'utilisateur
		this.setResizable(false);
		
		// Crée le controleur associé et lui indique que le vue qui lui
		// est associée est elle-même
		this.controleur = new AjoutControleur(this) ;  
		
		// Affiche la boîte de dialogue
		this.setVisible(true) ;
		
	}
	
	
	
	
	/** Initialiser les champs de saisie
	 * 
	 */
	public void initialiser(){
				
		//Initialiser les JTextField
		this.lieu.setText("");		
		this.responsable.setText("");		
		this.nbParticipants.setText("");		
		this.nomAct.setText("");
		
	}
	
	/** Créer l'interface utilisateur
	 * 
	 */
	private void creerInterfaceUtilisateur(){
		System.out.println("VueAuthentification::creerInterfaceUtilisateur()") ;
		
		Container conteneur = this.getContentPane() ;
		
		Box boxPrincipale = Box.createVerticalBox() ;
		Box boxChamps = Box.createHorizontalBox() ;
		Box boxEtiquettes = Box.createVerticalBox() ;
		Box boxSaisies = Box.createVerticalBox() ;
		Box boxLigne = Box.createHorizontalBox() ;
		Box boxActions = Box.createHorizontalBox() ;
		
		/*Rappel des données
		String [] listeJour = {"...","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21"
		,"22","23","24","25","26","27","28","29","30","31"};
	private JComboBox jour = new JComboBox(listeJour) ;//Menu Déroulant
	String [] month = {"...","Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Décembre"};
	private JComboBox mois = new JComboBox(month) ;//Menu Déroulant
	String []year = {"...","2019","2020","2021","2022"};
	private JComboBox annees = new JComboBox(year) ;//Menu Déroulant
	private JTextField lieu = new JTextField() ;
	
	String []pers = {"...","Monsieur","Madame"};
	private JComboBox mrmme = new JComboBox(pers) ;//Menu Déroulant
	private JTextField responsable = new JTextField() ;
	private JTextField nbParticipants = new JTextField() ;
	String [] typeact = {"...","films","jeux","musique","films"};
	private JComboBox lesacts = new JComboBox(typeact) ;//Menu Déroulant
	private JTextField nomAct = new JTextField() ; */
		
		
		boxEtiquettes.add(new JLabel("Date : "));
		boxEtiquettes.add( Box.createVerticalStrut(55) ) ;
		boxEtiquettes.add(new JLabel("Lieu : ")) ;
		boxEtiquettes.add( Box.createVerticalStrut(25) ) ;
		boxEtiquettes.add(new JLabel("Responsable : ")) ;
		boxEtiquettes.add( Box.createVerticalStrut(40) ) ;
		boxEtiquettes.add(new JLabel("Nombre de participants : ")) ;
		boxEtiquettes.add( Box.createVerticalStrut(30) ) ;
		boxEtiquettes.add(new JLabel("Type d'activite : ")) ;
		boxEtiquettes.add( Box.createVerticalStrut(15) ) ;
		boxEtiquettes.add(new JLabel("nom de l'activite et infos supl. : ")) ;
		
		boxSaisies.add( this.jour) ;
		boxSaisies.add( this.mois) ;
		boxSaisies.add( this.annees) ;
		boxSaisies.add( Box.createVerticalStrut(10) ) ;
		boxSaisies.add( this.lieu) ;
		boxSaisies.add( Box.createVerticalStrut(10) ) ;
		boxSaisies.add( this.mrmme) ;
		boxSaisies.add( this.responsable) ;
		boxSaisies.add( Box.createVerticalStrut(10) ) ;
		boxSaisies.add( this.nbParticipants) ;
		boxSaisies.add( Box.createVerticalStrut(10) ) ;
		boxSaisies.add( this.lesacts) ;
		boxSaisies.add( Box.createVerticalStrut(10) ) ;
		boxSaisies.add( this.nomAct) ;
		
		boxLigne.add( Box.createHorizontalStrut( 30 ) ) ;
		boxLigne.add( new JSeparator() ) ;
		boxLigne.add( Box.createHorizontalStrut( 30 ) ) ;
		
		// VOTRE CODE :
		// Agence les boutons "Se connecter" et "Annuler"
		boxActions.add( Box.createHorizontalStrut( 10 ) ) ;
		boxActions.add( this.valider ) ;
		boxActions.add( Box.createHorizontalStrut( 10 ) ) ;
		boxActions.add( this.annuler ) ;
		boxActions.add( Box.createHorizontalStrut( 10 ) ) ;
		
		
		
		
		boxChamps.add( Box.createHorizontalStrut( 10 ) ) ;
		boxChamps.add( boxEtiquettes ) ;
		boxChamps.add( Box.createHorizontalStrut( 10 ) ) ;
		boxChamps.add( boxSaisies ) ;
		boxChamps.add( Box.createHorizontalStrut( 10 ) ) ;
		
		boxPrincipale.add( Box.createVerticalStrut( 10 ) ) ;
		boxPrincipale.add( boxChamps ) ;
		boxPrincipale.add( Box.createVerticalStrut( 10 ) ) ;
		boxPrincipale.add( boxLigne ) ;
		boxPrincipale.add( Box.createVerticalStrut( 10 ) ) ;
		boxPrincipale.add( boxActions ) ;
		boxPrincipale.add( Box.createVerticalStrut( 10 ) ) ;
		
		conteneur.add(boxPrincipale) ;
		
		
		// Le code qui suit permet d'adapter la taille du bouton
		// "Annuler" par rapport à celle du bouton "Se connecter"
		
		Dimension dimensionBouton = this.valider.getPreferredSize() ;
		
		this.annuler.setPreferredSize(dimensionBouton) ;
		System.out.println(this.annuler.getPreferredSize()) ;
		this.annuler.setMaximumSize(dimensionBouton) ;
		System.out.println(this.annuler.getPreferredSize()) ;
		this.annuler.setMinimumSize(dimensionBouton) ;
		System.out.println(this.annuler.getPreferredSize()) ;
		
	}

/*	public JTextField getTfLogin() {
		return lieu;
	}

	public JTextField getPfMdp() {
		return responsable;
	}
	*/
	
	

	



//Boutons de la boite de dialogue

	public JButton getValider() {
		return valider;
	}


	public JButton getAnnuler() {
		return annuler;
	}
	
	////////////////////

	public JTextField getLieu() {
		return lieu;
	}



	public JTextField getResponsable() {
		return responsable;
	}




	public JTextField getNbParticipants() {
		return nbParticipants;
	}





	public JTextField getNomAct() {
		return nomAct;
	}
//LES COMBO BOX




	public JComboBox getMois() {
		return mois;
	}




	public JComboBox getAnnees() {
		return annees;
	}




	public JComboBox getLesacts() {
		return lesacts;
	}




	public JComboBox getMrmme() {
		return mrmme;
	}




	public JComboBox getJour() {
		return jour;
	}
	
	



}
