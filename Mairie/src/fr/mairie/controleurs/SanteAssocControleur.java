package fr.mairie.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import fr.mairie.modeles.ModeleMairie;
import fr.mairie.vues.Mairie;

public class SanteAssocControleur implements ActionListener{
	// Vue associée au contrôleur
			private Mairie vue ;					
		
			//Dispositon speciale : Le nouveau arrivant doit se
			//declarer en mairie un login/mdp lui sera
			//remis afin de completer les informations
			//sur une application à part (fictive)
			
			//il n'y a aucune condition d'acces à cette table
	public SanteAssocControleur(Mairie vue) {
		super() ;
		System.out.println("SanteAssocControleur::Constructeur");
		// Mémorise sa vue associée
		this.vue = vue ;
		
		// Se met à l'écoute des items de menu
		this.enregistrerEcouteur();
	}

	

	private void enregistrerEcouteur() {
		// TODO Auto-generated method stub  
		
		System.out.println("enregistrerEcouteur()");
		
		
		this.vue.getItemMedecineGenerale().addActionListener(this) ;
		this.vue.getItemDentiste().addActionListener(this) ;
		this.vue.getItemOrl().addActionListener(this) ;
		this.vue.getItemOrthopediste().addActionListener(this) ;
		this.vue.getItemNeurologue().addActionListener(this) ;
		this.vue.getItemGenecologue().addActionListener(this) ;
		this.vue.getItemPediatre().addActionListener(this) ;
		this.vue.getItemAjoutActivite().addActionListener(this) ;
		this.vue.getItemAssoc().addActionListener(this);
		
	}


	/*getItemMedecineGenerale() 
	getItemDentiste()
	getItemOrl()
	getItemOrthopediste()
	getItemNeurologue() 
	getItemGenecologue() 
	getItemPediatre() 
	getItemAjouterAs()*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("actionPerformed");
		
		Object sourceEvenement = e.getSource() ;
		
		if( sourceEvenement == this.vue.getItemMedecineGenerale()){
			System.out.println("actionPerformed:: sourceEvenement --> MedecineG ");
			this.afficherMG();
		}
		else if( sourceEvenement == this.vue.getItemDentiste() ){
			System.out.println("actionPerformed:: sourceEvenement --> Dentiste ");
			this.afficherDentiste();
		}
		else if( sourceEvenement == this.vue.getItemOrl() ){
			System.out.println("actionPerformed:: sourceEvenement --> Orl ");
			this.afficherOrl();
		}
		else if( sourceEvenement == this.vue.getItemOrthopediste() ){
			System.out.println("actionPerformed:: sourceEvenement --> Orthopediste ");
			this.afficherOrthopediste();
		}
		else if( sourceEvenement == this.vue.getItemNeurologue() ){
			System.out.println("actionPerformed:: sourceEvenement --> Neurologue ");
			this.afficherNeurologue();
		}
		else if( sourceEvenement == this.vue.getItemGenecologue() ){
			System.out.println("actionPerformed:: sourceEvenement --> Genecologue ");
			this.afficherGenecologue();
		}
		else if( sourceEvenement == this.vue.getItemPediatre() ){
			System.out.println("actionPerformed:: sourceEvenement --> Pediatre ");
			this.afficherPediatre();
		}
		else if( sourceEvenement == this.vue.getItemAssoc() ){
			System.out.println("actionPerformed:: sourceEvenement --> Associations ");
			this.afficherAssociation();
		}
		
	}
	




	/*conteneur.add(vuemedecineG,"vuemedecineG");
	conteneur.add(vuedentiste,"vuedentiste");
	conteneur.add(vueorl,"vueorl");
	conteneur.add(vueortho,"vueortho");
	conteneur.add(vueneurologue,"vueneurologue");
	conteneur.add(vuegenecologue,"vuegenecologue");
	conteneur.add(vuepediatre,"vueprediatre");*/

	private void afficherDentiste() {
		// TODO Auto-generated method stub
		this.vue.changerVue("vuedentiste");
	}

	private void afficherPediatre() {
		// TODO Auto-generated method stub
		this.vue.changerVue("vueprediatre");
	}

	private void afficherGenecologue() {
		// TODO Auto-generated method stub
		this.vue.changerVue("vuegenecologue");
	}

	private void afficherNeurologue() {
		// TODO Auto-generated method stub
		this.vue.changerVue("vueneurologue");
	}

	private void afficherOrthopediste() {
		// TODO Auto-generated method stub
		this.vue.changerVue("vueortho");//!!Attention!!//
	}

	private void afficherOrl() {
		// TODO Auto-generated method stub
		this.vue.changerVue("vueorl");
	}

	private void afficherMG() {
		// TODO Auto-generated method stub
		this.vue.changerVue("vuemedecineG");
	}
	
	private void afficherAssociation() {
		// TODO Auto-generated method stub
		this.vue.changerVue("vueassoc");
		
	}

}
