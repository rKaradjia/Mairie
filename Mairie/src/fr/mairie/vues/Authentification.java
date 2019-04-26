package fr.mairie.vues;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import fr.mairie.controleurs.AuthentificationControleur;

public class Authentification extends JDialog   {

	Mairie vueParente;
	
	AuthentificationControleur controleur ;
	
	private JTextField tfLogin = new JTextField() ;
	private JPasswordField pfMdp = new JPasswordField() ;
	private JButton bConnecter = new JButton("Se connecter") ;
	private JButton bAnnuler = new JButton("Annuler") ;
	
	public Authentification(Mairie vueParente){
		// Appel du constructeur de la super-classe
		//	Troisième argument : true pour indiquer que la boîte de dialogue est modale 
		super(vueParente,"Authentification",true) ;
		
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
		this.controleur = new AuthentificationControleur(this) ;
		
		// Affiche la boîte de dialogue
		this.setVisible(true) ;
		
	}
	
	
	
	
	/** Initialiser les champs de saisie
	 * 
	 */
	public void initialiser(){
				
		// VOTRE CODE : 
		// Afficher une chaîne de caractères vide dans les deux
		// champs de saisie (login et MDP)
		this.tfLogin.setText("");
		
		this.pfMdp.setText("");
		
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
		
		boxEtiquettes.add(new JLabel("Login : ")) ;
		boxEtiquettes.add( Box.createVerticalStrut(10) ) ;
		boxEtiquettes.add(new JLabel("MDP : ")) ;
		
		boxSaisies.add( this.tfLogin) ;
		boxSaisies.add( Box.createVerticalStrut(10) ) ;
		boxSaisies.add( this.pfMdp) ;
		
		boxLigne.add( Box.createHorizontalStrut( 10 ) ) ;
		boxLigne.add( new JSeparator() ) ;
		boxLigne.add( Box.createHorizontalStrut( 10 ) ) ;
		
		// VOTRE CODE :
		// Agence les boutons "Se connecter" et "Annuler"
		boxActions.add( Box.createHorizontalStrut( 10 ) ) ;
		boxActions.add( this.bConnecter ) ;
		boxActions.add( Box.createHorizontalStrut( 10 ) ) ;
		boxActions.add( this.bAnnuler ) ;
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
		
		Dimension dimensionBouton = this.bConnecter.getPreferredSize() ;
		
		this.bAnnuler.setPreferredSize(dimensionBouton) ;
		System.out.println(this.bAnnuler.getPreferredSize()) ;
		this.bAnnuler.setMaximumSize(dimensionBouton) ;
		System.out.println(this.bAnnuler.getPreferredSize()) ;
		this.bAnnuler.setMinimumSize(dimensionBouton) ;
		System.out.println(this.bAnnuler.getPreferredSize()) ;
		
	}

	public JTextField getTfLogin() {
		return tfLogin;
	}

	public JPasswordField getPfMdp() {
		return pfMdp;
	}

	public JButton getbConnecter() {
		return bConnecter;
	}

	public JButton getbAnnuler() {
		return bAnnuler;
	}

}