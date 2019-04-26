package fr.mairie.controleurs;

import fr.mairie.vues.Authentification;
import fr.mairie.vues.Mairie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import fr.mairie.modeles.ModeleMairie;


public class AuthentificationControleur implements ActionListener{
	
	// Déclarer et initialiser l'attribut modele
	private ModeleMairie modele ;			
	
	private Mairie vueMairie;
	
	private Authentification vueAuthentification ;
	
	//les actionPerformed dans ce controleur concerne les boutons interne 
	//à la JDialog c'est a dire dans notre cas à fr.mairie.vues.Authentification
	
	/** Constructeur
	 * @param vue Vue associée
	 */
	public AuthentificationControleur(Authentification vueAuthentification){
		
		super() ;
		System.out.println("ControleurAuthentification::ControleurAuthentification()") ;
		
		this.vueAuthentification = vueAuthentification ;
		this.enregistrerEcouteur() ;
	}
	
	/** Enregistrer le contrôleur en tant qu'écouteur
	 * 
	 */
	private void enregistrerEcouteur(){
		System.out.println("ControleurAuthentification::enregistrerEcouteur()") ;
		
		// VOTRE CODE : 
		// Enregistrer l'écouteur du bouton "Se connecter"
		this.vueAuthentification.getbAnnuler().addActionListener(this);
		// Enregistrer l'écoureur du bouton "Annuler"
		this.vueAuthentification.getbConnecter().addActionListener(this);
	}
	
	// VOTRE CODE :
	// Méthode de traitement des événements
	
	public void actionPerformed(ActionEvent e){
		System.out.println ("AuthentificationControleur::actionPerformed (ActionEvent e)");
		Object sourceEvenement = e.getSource();
		
		if(sourceEvenement==this.vueAuthentification.getbAnnuler()){
			
			this.vueAuthentification.initialiser();
			
		}
		
		if(sourceEvenement==this.vueAuthentification.getbConnecter()){
				JTextField Login = this.vueAuthentification.getTfLogin();
				JTextField Mdp = this.vueAuthentification.getPfMdp();
			
				String TfLogin = Login.getText();
				String Pfmdp = Mdp.getText();
				System.out.println(Pfmdp);
				System.out.println(TfLogin);
				
				
				try {
					Boolean co = this.modele.seConnecter(TfLogin, Pfmdp,vueAuthentification);
					System.out.println("Resultat du login");
					if(co==true){
						System.out.println("yes co ok");
						Mairie vueParente = (Mairie) this.vueAuthentification.getParent();  //VueFoody -> Mairie
					
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
		
		this.vueAuthentification.initialiser();
		
		
	}
	

	
	
	
}