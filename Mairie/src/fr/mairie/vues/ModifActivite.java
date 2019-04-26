package fr.mairie.vues;

import java.awt.Container;
import java.awt.Dimension;
import java.sql.Date;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import fr.mairie.controleurs.ModifActiviteControleur;


public class ModifActivite extends JDialog {
	
	//Attention il n'est pas possible de modifier le type
	//d'une activite 
	

	private Mairie mairie ;
	private ModifActiviteControleur ctrl;
	
	//Stockage des données requises
	Integer idAct;
	String dateAct;
	String lieuAct;
	String responsableAct;
	Integer nbPartAct;
	String nomAct;
	String activiteAct;

	private JTextField lieutf = new JTextField(lieuAct);
	private JTextField responsabletf = new JTextField(responsableAct);
	private JTextField nbParttf = new JTextField(String.valueOf(nbPartAct));
	private JTextField nomtf = new JTextField(nomAct);
	
	
	//pour la date
	String [] listeJour = {"...","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21"
			,"22","23","24","25","26","27","28","29","30","31"};
	private JComboBox jour = new JComboBox(listeJour) ;//Menu Déroulant
	String [] month = {"...","Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Décembre"};
	private JComboBox mois = new JComboBox(month) ;//Menu Déroulant
	String []year = {"...","2019","2020","2021","2022"};
	private JComboBox annees = new JComboBox(year) ;//Menu Déroulant
	
	//Données necessitant un menu-déroulant MR/MME  -  NB Participants  --
	String []pers = {"...","Monsieur","Madame"};
	private JComboBox mrmme = new JComboBox(pers) ;//Menu Déroulant
	//private JTextField nbParticipants = new JTextField() ;


	//Boutons
	private JButton valider = new JButton("Modifier") ;
	private JButton annuler = new JButton("Annuler") ;

	public ModifActivite(Mairie vuepr, Integer id, String dateString, String lieu, String responsable, Integer nbPart,
			String activite, String nom) {
		super(vuepr,"Modification",true) ;
		System.out.println("ModifActivite::Constructeur");

		//
		String mme = responsable.substring(0,6);
		String mr = responsable.substring(0,8);
		System.out.println("Constructeur:: mr -->" + mr + " / mme -->" + mme);
				
		
		String FamilyName = null;
		if(mme.equals("Madame")) {
			FamilyName=responsable.substring(7,responsable.length());
		}
		else if(mr.equals("Monsieur")){
			FamilyName=responsable.substring(9,responsable.length());
		}else {
		           FamilyName=responsable;
		}
		
		
		
		System.out.println("Constructeur FamilyName --> "+ FamilyName);
		System.out.println("Constructeur apres decoupage --> " +responsable.substring(7,responsable.length()) + " " 
		                   +responsable.substring(9,responsable.length()));
		
		//System.out.println("Constructeur nom du responsable --> " + this.responsableAct);
		
		this.idAct=id;
		this.dateAct=dateString;
		this.lieuAct=lieu;
		this.nbPartAct=nbPart;
		this.nomAct=nom;
		this.responsableAct=FamilyName;
		this.activiteAct=activite;
		this.mairie=vuepr;

		System.out.println("Constructeur nom du responsable --> " + this.responsableAct);
		
		lieutf = new JTextField(lieu);
		responsabletf = new JTextField(responsableAct);
		nbParttf = new JTextField(String.valueOf(nbPart));
		nomtf = new JTextField(nom);
		
		this.creerInterfaceUtilisateur() ;
		
		
		this.pack() ;
		
		
		this.setLocationRelativeTo(null) ;
		
		
		this.setResizable(false);
		
		
		this.ctrl = new ModifActiviteControleur(this) ;
		
	}

	private void creerInterfaceUtilisateur() {
		// TODO Auto-generated method stub
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
		boxEtiquettes.add( Box.createVerticalStrut(25) ) ;
		boxEtiquettes.add(new JLabel("Date Actuelle : " + dateAct));
		boxEtiquettes.add( Box.createVerticalStrut(30) ) ;
		boxEtiquettes.add(new JLabel("Lieu : ")) ;
		boxEtiquettes.add( Box.createVerticalStrut(25) ) ;
		boxEtiquettes.add(new JLabel("Responsable : ")) ;
		boxEtiquettes.add( Box.createVerticalStrut(40) ) ;
		boxEtiquettes.add(new JLabel("Nombre de participants : ")) ;
		boxEtiquettes.add( Box.createVerticalStrut(30) ) ;
		boxEtiquettes.add(new JLabel("nom de l'activite et infos supl. : ")) ;
		
		boxSaisies.add( this.jour) ;
		boxSaisies.add( this.mois) ;
		boxSaisies.add( this.annees) ;
		boxSaisies.add( Box.createVerticalStrut(10) ) ;
		boxSaisies.add( this.lieutf) ;
		boxSaisies.add( Box.createVerticalStrut(10) ) ;
		boxSaisies.add( this.mrmme) ;
		boxSaisies.add( this.responsabletf) ;
		boxSaisies.add( Box.createVerticalStrut(10) ) ;
		boxSaisies.add( this.nbParttf) ;
		boxSaisies.add( Box.createVerticalStrut(10) ) ;
		boxSaisies.add( this.nomtf) ;
		
		boxLigne.add( Box.createHorizontalStrut( 30 ) ) ;
		boxLigne.add( new JSeparator() ) ;
		boxLigne.add( Box.createHorizontalStrut( 30 ) ) ;
		
		
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
	
	public void initialiser() {
		// TODO Auto-generated method stub
		//Initialiser les JTextField à leurs valeurs d'origine
				this.lieutf.setText(lieuAct);		
				this.responsabletf.setText(responsableAct);		
				this.nbParttf.setText(String.valueOf(nbPartAct));		
				this.nomtf.setText(nomAct);
	}
	
	

	public Integer getIdAct() {
		return idAct;
	}


	public JTextField getLieu() {
		return lieutf;
	}

	

	public String getActiviteAct() {
		return activiteAct;
	}


	public JTextField getResponsable() {
		return responsabletf;
	}

	
	public JTextField getNbPart() {
		return nbParttf;
	}



	public JTextField getNom() {
		return nomtf;
	}



	public JComboBox getJour() {
		return jour;
	}



	public JComboBox getMois() {
		return mois;
	}



	public JComboBox getAnnees() {
		return annees;
	}


	public JComboBox getMrmme() {
		return mrmme;
	}
/////////Si la date n'est pas modifie ou si les champs sont vides///////////////
	

	public String getDateAct() {
		
		return dateAct;
		
	}
	
	
	//////Boutons/////////////////////
	
	public JButton getValider() {
		return valider;
	}

	


	public JButton getAnnuler() {
		return annuler;
	}

	




/*	public JTextField getNbParticipants() {
		return nbParticipants;
	}

	public void setNbParticipants(JTextField nbParticipants) {
		this.nbParticipants = nbParticipants;
	}*/
	
	
	
	

}
