package fr.mairie.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import fr.mairie.entites.Activites;
import fr.mairie.vues.AjoutActivite;
import fr.mairie.vues.Authentification;
import fr.mairie.vues.Mairie;

public class ActivitesControleur implements ActionListener {
	
	// Vue associée au contrôleur
	private Mairie vue ;					
	
	
	
	
	
	public ActivitesControleur(Mairie vue){
		super() ;
		// Mémorise sa vue associée
		this.vue = vue ;
		
		// Se met à l'écoute des items de menu
		this.enregistrerEcouteur();
	}
	
	private void enregistrerEcouteur(){

	/*getItemJeux()
      getItemFilms()    les méthodes a enregistrer / écouter
	  getItemMusique()
	  getItemSport() 
	  getItemAjoutActivite() */
		//4 Activites
		this.vue.getItemJeux().addActionListener(this) ;
		this.vue.getItemFilms().addActionListener(this) ;
		this.vue.getItemMusique().addActionListener(this) ;
		this.vue.getItemSport().addActionListener(this) ;
		//+1 methode pour l'ajout
		this.vue.getItemAjoutActivite().addActionListener(this) ;
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		// Obtient le composant graphique source de l'événement ("clic")
		Object sourceEvenement = e.getSource() ;
		
		if( sourceEvenement == this.vue.getItemJeux() ){
			this.afficherJeux();
		}
		
		
		else if( sourceEvenement == this.vue.getItemFilms() ){
			this.afficherFilms();
			}
		
		else if( sourceEvenement == this.vue.getItemMusique() ){
			this.afficherMusique();
			}
		
		else if( sourceEvenement == this.vue.getItemSport() ){
			this.afficherSport();
			}
		
		else if( sourceEvenement == this.vue.getItemAjoutActivite() ){
			this.afficherAjoutActivite();
			}
		
	}
	

	
	
	

	private void afficherAjoutActivite() {
		// TODO Auto-generated method stub
		new AjoutActivite(this.vue) ;
		
		
	}

	private void afficherSport() {
		// TODO Auto-generated method stub
		this.vue.changerVue("vueSport");
		
	}

	private void afficherMusique() {
		// TODO Auto-generated method stub
		this.vue.changerVue("vueMusique");
		
	}

	private void afficherFilms() {
		// TODO Auto-generated method stub
		this.vue.changerVue("vueFilms");
		
	}

	private void afficherJeux() {
		// TODO Auto-generated method stub
		
		this.vue.changerVue("vueJeux");
		
	}

	private void afficherRapport(){
		//this.vue.changerVue("vueVisiteur");
		}
		
	}
