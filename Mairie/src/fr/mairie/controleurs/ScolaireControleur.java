package fr.mairie.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import fr.mairie.modeles.ModeleMairie;
import fr.mairie.technique.ConnexionException;
import fr.mairie.vues.Mairie;

public class ScolaireControleur implements ActionListener{

	// Vue associée au contrôleur
		private Mairie vue ;					
		private ModeleMairie modele;//Controle d'acces à la fonctionalité
		
		
		private Boolean autorisation = null;
		
		public ScolaireControleur(Mairie vue){
			super() ;
			System.out.println("ScolaireControleur::Constructeur");
			// Mémorise sa vue associée
			this.vue = vue ;
			
			
			
				this.enregistrerEcouteur();
			System.out.println("Constructeur:: autorisation --> " + autorisation);
			
			
		}


		private void enregistrerEcouteur() {
			
			// TODO Auto-generated method stub
			this.vue.getItemRestoReserv().addActionListener(this) ;
			this.vue.getItemRestoMenus().addActionListener(this) ;
			
		}
		
		
		public void actionPerformed(ActionEvent e) {
			//Avant de permettre l'acces, on verifit que l'utilisateur 
			//fait parti du service scolaire
				try {
					autorisation = modele.getVerifierDroitsScolaire();
				} catch (ConnexionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			
			if(autorisation==false) {
				JOptionPane fails=new JOptionPane();
				fails.showMessageDialog(fails,"Vous n'avez pas les autorisations","Echec "
						+ "--> vous n'avez pas les droits pour cette action"
						+ "admin@admin.fr .",0);
			}else {
			
			
			Object sourceEvenement = e.getSource() ;

			if( sourceEvenement == this.vue.getItemRestoReserv() ){
				this.afficherReservation();
			}
			
			if( sourceEvenement == this.vue.getItemRestoMenus() ){
				this.afficherMenus();
			}
			}
		}


		private void afficherMenus() {
			// TODO Auto-generated method stub
			
			this.vue.changerVue("vueMenus");
			
		}


		private void afficherReservation() {
			// TODO Auto-generated method stub
			
			this.vue.changerVue("vueReservations");
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

}
