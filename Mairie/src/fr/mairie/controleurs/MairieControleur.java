package fr.mairie.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import fr.mairie.vues.Authentification;
import fr.mairie.vues.Mairie;

public class MairieControleur implements ActionListener {
	
	// Vue associée au contrôleur
	private Mairie vueMairie ;					
	
	public MairieControleur(Mairie vueMairie){
		super() ;
		// Mémorise sa vue associée
		this.vueMairie = vueMairie ;
		
		// Se met à l'écoute des items de menu
		this.enregistrerEcouteur();
	}
	
	private void enregistrerEcouteur(){
		// Se met à l'écoute de l'item de menu "Se connecter"
		this.vueMairie.getItemSeConnecter().addActionListener(this) ;
		
		// Se met à l'écoute de l'item de menu "Se déconnecter"
		this.vueMairie.getItemSeDeconnecter().addActionListener(this) ;
		
		// Se met à l'écoute de l'item de menu "Quitter"
		this.vueMairie.getItemQuitter().addActionListener(this) ;
		
		// Se met à l'écoute de l'item de menu "A propos..."
		this.vueMairie.getItemApropos().addActionListener(this) ;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		// Obtient le composant graphique source de l'événement ("clic")
		System.out.println("MairieConstroleur::actionPerformed()");
		Object sourceEvenement = e.getSource() ;
		
		if( sourceEvenement == this.vueMairie.getItemSeConnecter() ){
			this.seConnecter();
		}
		// Si la source est l'item de menu "Se déconnecter"
		else if( sourceEvenement == this.vueMairie.getItemSeDeconnecter() ){
			this.seDeconnecter();
		}
		// Si la source est l'item de menu "Quitter"
		else if( sourceEvenement == this.vueMairie.getItemQuitter() ){
			this.quitter();
		}
	
	}
	
	private void seConnecter(){
		System.out.println("L'utilisateur veut se connecter.");
		// Affiche la vue dédiée à la connexion (formulaire de connexion)
		new Authentification(this.vueMairie) ; //ouverture de la JDialog 
		                                       //rappel dans mairie pas d'ajout 
		                                       //de cette vue dans le conteneur
	}
	
	private void seDeconnecter(){
		System.out.println("L'utilisateur veut se déconnecter.");
		// Demande confirmation à l'utilisateur au moyen d'une boîte de dialogue
		int reponse = JOptionPane.showConfirmDialog(this.vueMairie, "Voulez-vous vraiment vous déconnecter ?","Déconnexion",JOptionPane.YES_NO_OPTION) ;
		
		// Si l'utilateur confirme...
		if( reponse == JOptionPane.YES_OPTION ){
				
			// Bascule la barre de menus dans le "Mode Non connecté"
			this.vueMairie.setBarreMenusModeDeconnecte();
			
			JOptionPane.showMessageDialog(null, "Vous êtes maintenant déconnecté.","Déconnexion",JOptionPane.INFORMATION_MESSAGE) ;
		}
	}
	
	private void quitter(){
		System.out.println("L'utilisateur veut quitter.");
		// Demande confirmation à l'utilisateur au moyen d'une boîte de dialogue
		int reponse = JOptionPane.showConfirmDialog(this.vueMairie, "Voulez-vous vraiment quitter ?","Quitter",JOptionPane.YES_NO_OPTION) ;
		
		// Si l'utilateur confirme...
		if( reponse == JOptionPane.YES_OPTION ){
			// Met fin à l'application
			System.exit(0) ;
		}
	}
	
}
