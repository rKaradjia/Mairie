package fr.mairie.vues;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
/*
import fr.berufood.foody.controleurs.ControleurFoody;
import fr.berufood.foody.controleurs.ControleurPraticien;
import fr.berufood.foody.controleurs.ControleurSelectionDate;
import fr.berufood.foody.controleurs.ControleurVisiteur;
import fr.berufood.foody.vues.*;*/

import javax.swing.JTable;

import fr.mairie.controleurs.ActivitesControleur;
import fr.mairie.controleurs.AjoutControleur;
import fr.mairie.controleurs.MairieControleur;
import fr.mairie.controleurs.SanteAssocControleur;
import fr.mairie.controleurs.ScolaireControleur;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

public class Mairie extends JFrame {

	// Contrôleur associé à la vue ASSOCIE PAR ONGLET
	private MairieControleur mairieCtrl ;
	private ActivitesControleur activitesCtrl;
	private ScolaireControleur scolaireCtrl;
	private SanteAssocControleur santeassocCtrl;
	private AjoutControleur ajoutCtrl;
/*	private ControleurVisiteur controleurVisiteur;
	private ControleurPraticien controleurPraticien;
	*/
	//private ControleurVisiteur controleurVisiteur;
	
	// Les menus
	private JMenu menuFichier = new JMenu("Fichier") ;	
	private JMenu menuAide = new JMenu("Aide") ;
	private JMenu menuActivites = new JMenu("Activites") ;/*MenuRapport devient MenuActivite*/
	private JMenu menuScolaire = new JMenu("Scolaire") ;/*MenuPraticiens devient MenuScolaire*/
	private JMenu menuSanteAssoc = new JMenu("Sante & Associations");
	
	
	
	// Les items de menu dans l'ordre par rapport au JMenu ci-dessus
	private JMenuItem itemSeConnecter = new JMenuItem("Se connecter") ;			
	private JMenuItem itemSeDeconnecter = new JMenuItem("Se déconnecter") ;
	private JMenuItem itemConsulter = new JMenuItem("Consulter") ;
	private JMenuItem itemQuitter = new JMenuItem("Quitter") ;
	
	private JMenuItem itemLire = new JMenuItem("Lire") ;// inactif
	private JMenuItem itemModifier = new JMenuItem("Modifier") ;//inactif
	
	private JMenuItem itemJeux = new JMenuItem("Jeux");
	private JMenuItem itemFilms = new JMenuItem("Films");
	private JMenuItem itemMusique = new JMenuItem("Musique");
	private JMenuItem itemSport = new JMenuItem("Sport");
	private JMenuItem itemAjoutActivite = new JMenuItem("Ajouter Activite");
	
	private JMenuItem itemRestoMenus = new JMenuItem("Menu Resto");
	private JMenuItem itemRestoReserv = new JMenuItem("Reservations Restos");
	
	private JMenuItem itemMedecineGenerale = new JMenuItem("Medecine Generale");
	private JMenuItem itemDentiste = new JMenuItem("Dentiste");
	private JMenuItem itemOrl = new JMenuItem("Orl");
	private JMenuItem itemOrthopediste = new JMenuItem("Orthopediste");
	private JMenuItem itemNeurologue = new JMenuItem("Neurologue");
	private JMenuItem itemGenecologue = new JMenuItem("Genecologue");
	private JMenuItem itemPediatre = new JMenuItem("Pediatre");
	private JMenuItem itemAssoc = new JMenuItem("Associations");
	

	
	
	private JMenuBar barreMenus = new JMenuBar() ;
	
	private CardLayout clVues = new CardLayout(0,0) ;
	
	private Accueil vueAccueil = new Accueil();
	private Jeux vueJeux = new Jeux();
	private Films vueFilms = new Films();
	private Musique vueMusique = new Musique();
	private Sport vueSport = new Sport();
	
	private Menus vueMenus = new Menus();
	private Reserv vueReserv = new Reserv();
	
	//Vues dediés à la santé
	/*getItemMedecineGenerale() 
	getItemDentiste()
	getItemOrl()
	getItemOrthopediste()
	getItemNeurologue() 
	getItemGenecologue() 
	getItemPediatre() 
	getItemAjouterAs()*/
	private MedecineG vuemedecineG=new MedecineG();
	private Dentiste vuedentiste = new Dentiste();
	private Orl vueorl= new Orl();
	private Orthopediste vueortho=new Orthopediste();
	private Neurologue vueneurologue=new Neurologue();
	private Genecologue vuegenecologue=new Genecologue();
	private Pediatre vuepediatre = new Pediatre();
	private Associations vueassoc = new Associations();
	
	
	public Mairie(){
		super() ;
		
		// Donne un titre à la fenêtre
		this.setTitle("Foody") ;
		
		// Définit le largeur et la hauteur de la fenêtre
		this.setSize(1400,900) ;
		
		// Positionne la fenêtre au centre de l'écran
		this.setLocationRelativeTo(null) ;
		
		// Empêche l'utilisateur de fermer la fenêtre à l'aide de la croix
		// qui se trouve en haut à droite
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		// Empêche le redimensionnement par l'utilisateur
		this.setResizable(false);
		
		// Crée la barre de menus
		this.creerBarreMenus();
		
		// Bascule la barre de menus dans le "Mode non connecte"
		this.setBarreMenusModeDeconnecte();
		
		
		
	//CONTENEUR	
		Container conteneur = this.getContentPane();
		conteneur.setLayout(clVues);
		//differentes vue 1 vue = 1 JMenuItem  ==> 13 au total dans notre cas sans compte cas ajout ( +3 ) 
		conteneur.add(vueJeux,"vueJeux");
		conteneur.add(vueFilms,"vueFilms");
		conteneur.add(vueMusique,"vueMusique");     // A configurer et a decomenter
		conteneur.add(vueSport,"vueSport");
		conteneur.add(vueAccueil,"Accueil");
		//Comme les ajouts se font dans une JDialog, 
		//on n'a pas besoin de le rajouter dans le conteneur
		//conteneur.add(vueAjoutActivite,"vueAjoutActivite"); 
		
		//Suite de l'ajout des vues
		conteneur.add(vueMenus,"vueMenus");
		conteneur.add(vueReserv,"vueReservations");
		
		//Vue sante et associations
		/*private MedecineG vuemedecineG=new MedecineG();
	private Dentiste vuedentiste = new Dentiste();
	private Orl vueorl= new Orl();
	private Orthopediste vueortho=new Orthopediste();
	private Neurologue vueneurologue=new Neurologue();
	private Genecologue vuegenecologue=new Genecologue();
	private Pediatre vuepediatre = new Pediatre();*/
		conteneur.add(vuemedecineG,"vuemedecineG");
		conteneur.add(vuedentiste,"vuedentiste");
		conteneur.add(vueorl,"vueorl");
		conteneur.add(vueortho,"vueortho");
		conteneur.add(vueneurologue,"vueneurologue");
		conteneur.add(vuegenecologue,"vuegenecologue");
		conteneur.add(vuepediatre,"vueprediatre");
		conteneur.add(vueassoc,"vueassoc");
		
		this.clVues.show(conteneur,"Accueil") ;                      
		
		// Crée le controleur associé et lui indique que le vue qui lui
		// est associée est elle-même ASSOCIE PAR ONGLET
	/*	private MairieControleur mairieCtrl ;
		private ActivitesControleur activitesCtrl;
		private ScolaireControleur scolaireCtrl;
		private SanteAssocControleur santeassocCtrl;*/
		
		
		this.mairieCtrl = new MairieControleur(this) ;    //declaration associé private MairieControleur mairieCtrl ;
		this.activitesCtrl = new ActivitesControleur(this);
		this.scolaireCtrl = new ScolaireControleur(this);
		this.santeassocCtrl = new SanteAssocControleur(this);
		//nous n'avons pas besoin d'ajouter le controleur AjoutControleur
		//le controleur mairieControleur nous affiche la vue
		//cette derniere va intérargir avec la vueAjoutActivite
		
		/*this.controleurVisiteur = new ControleurVisiteur(this);
		this.controleurPraticien = new ControleurPraticien(this);*/
		
	//	this.controleurVisiteur=new ControleurVisiteur(this);
		
		// Affiche la fenêtre
		this.setVisible(true) ;
	}
	
	private void creerBarreMenus(){
		
		// Crée une barre de menu vide
		JMenuBar barreMenus = new JMenuBar() ;
		
		// Ajoute les items de menu dans leur menu respectif
		this.menuFichier.add(this.itemSeConnecter) ;
		this.menuFichier.add(this.itemSeDeconnecter) ;
		this.menuFichier.add(new JSeparator());
		this.menuFichier.add(this.itemQuitter);
		this.menuFichier.add(this.itemConsulter);
		// le troisieme menu
		this.menuActivites.add(this.itemJeux) ;
		this.menuActivites.add(this.itemFilms) ;
		this.menuActivites.add(this.itemMusique) ;
		this.menuActivites.add(this.itemSport) ;
		this.menuActivites.add(new JSeparator());
		this.menuActivites.add(this.itemAjoutActivite);
		
		this.menuScolaire.add(itemRestoMenus );
		this.menuScolaire.add(itemRestoReserv);
		
		this.menuSanteAssoc.add(itemMedecineGenerale);
		this.menuSanteAssoc.add(itemDentiste);
		this.menuSanteAssoc.add(itemOrl);
		this.menuSanteAssoc.add(itemOrthopediste);
		this.menuSanteAssoc.add(itemNeurologue);
		this.menuSanteAssoc.add(itemGenecologue);
		this.menuSanteAssoc.add(itemPediatre);
		this.menuSanteAssoc.add(itemAssoc);
		
		
		// Ajoute les menus dans la barre de menu
		barreMenus.add(menuFichier) ;
		barreMenus.add(menuAide) ;
		barreMenus.add(menuActivites);
		barreMenus.add(menuScolaire);
		barreMenus.add(menuSanteAssoc);
		
		
		// Ajoute la barre de menus à la fenêtre
		this.setJMenuBar(barreMenus) ;
	}
	
	
	public void setBarreMenusModeConnecte(){
		// Désactive l'item de menu "Se connecter"
		this.itemSeConnecter.setEnabled(false) ;
		
		// Active l'item de menu "Se déconnecter"
		this.itemSeDeconnecter.setEnabled(true) ;
		
		
		// Active les menus "métiers"
				this.menuAide.setEnabled(true) ;
				this.menuActivites.setEnabled(true) ;
				this.menuScolaire.setEnabled(true) ;
				this.menuSanteAssoc.setEnabled(true) ;
				
	}
	
public void setBarreMenusModeDeconnecte(){
		
		// Active l'item de menu "Se connecter"
		this.itemSeConnecter.setEnabled(true) ;
		
		// Désactive l'item de menu "Se déconnecter"
		this.itemSeDeconnecter.setEnabled(false) ;
		
		// Désactive les menus "métiers"
		this.menuAide.setEnabled(false) ;
		this.menuActivites.setEnabled(false) ;
		this.menuScolaire.setEnabled(false) ;
		this.menuSanteAssoc.setEnabled(false) ;
		
	}

	public void changerVue(String nomVue){
	// VOTRE CODE ICI - QUESTION 2
		Container conteneur = this.getContentPane();
	
		this.clVues.show(conteneur,nomVue);
	
	}

	public MairieControleur getControleur() {
		return mairieCtrl;
	}
	
	//les getters qui suivent correspondent à la recuperation
	//du JMenuItem afin de le rendre actif lors d'un clic
	//dans son controleur correspondant
	

	public JMenuItem getItemSeConnecter() {
		return itemSeConnecter;
	}

	public JMenuItem getItemSeDeconnecter() {
		return itemSeDeconnecter;
	}

	public JMenuItem getItemQuitter() {
		return itemQuitter;
	}

	public JMenuItem getItemApropos() {
		return itemLire;
	}
	
	public JMenuItem getItemLire() {
		return itemLire;
	}
	
	public JMenuItem getItemModifier() {
		return itemModifier;
	}
// getters generés :  source->Generate getters and setters
	public JMenuItem getItemConsulter() {
		return itemConsulter;
	}

	
	//Les activites
	public JMenuItem getItemJeux() {
		return itemJeux;
	}

	public JMenuItem getItemFilms() {
		return itemFilms;
	}

	public JMenuItem getItemMusique() {
		return itemMusique;
	}

	public JMenuItem getItemSport() {
		return itemSport;
	}

	public JMenuItem getItemAjoutActivite() {
		return itemAjoutActivite;
	}

	//Scolaire
	
	public JMenuItem getItemRestoMenus() {
		return itemRestoMenus;
	}

	public JMenuItem getItemRestoReserv() {
		return itemRestoReserv;
	}

	//Sante et associations 
	
	public JMenuItem getItemMedecineGenerale() {
		return itemMedecineGenerale;
	}

	public JMenuItem getItemDentiste() {
		return itemDentiste;
	}

	public JMenuItem getItemOrl() {
		return itemOrl;
	}

	public JMenuItem getItemOrthopediste() {
		return itemOrthopediste;
	}

	public JMenuItem getItemNeurologue() {
		return itemNeurologue;
	}

	public JMenuItem getItemGenecologue() {
		return itemGenecologue;
	}

	public JMenuItem getItemPediatre() {
		return itemPediatre;
	}

	public JMenuItem getItemAssoc() {
		return itemAssoc;
	}


}