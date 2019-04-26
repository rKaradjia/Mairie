package fr.mairie.vues;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.mairie.editeurs.EditeurBoutonSelectionActivite;
import fr.mairie.modeles.ModeleActivites;
import fr.mairie.rendus.RenduBoutonSelectionActivite;
import fr.mairie.modeles.ModeleActivites;
import fr.mairie.controleurs.ControleurBoutonSelectionActivite;

public class Jeux extends JPanel {
    //Ce controleur sert à la recherche et à la recupération des informations
	//concernant une activite que ce soit un jeu, un film, de la musique 
	//ou une activite sportive.
	//Afin que l'on puisse recuperer le tableau correspondant 
	//à un type d'activite, il faut que l'on passe en avance le type 
	//d'activite pour ce controleur. Ce sera utilise pour des opérations tel que
	//getRow() ou encore dans l'opération vers le model
	//Note : une variable stocke le nom de l'activite dans ce controleur
	//et est reinitialiser à appel par une vue par la vue dont il d'agit
	//Ex : jeux --> films :  var=jeux --> reinit --> var=films
	private ControleurBoutonSelectionActivite ctrlBouton;
	
	
	private ModeleActivites modeleTabActivites ;
	private JTable tabJeux;
	private String jeux="jeux";
	
	public Jeux(){
		super() ;
		System.out.println("Jeux::Constructor");
		//this.ctrlBouton.setInitActivite("");
		//this.ctrlBouton.setInitActivite(jeux);
		modeleTabActivites = new ModeleActivites(jeux) ;
		this.creerInterfaceVisiteurs() ;
	}
	
	private void creerInterfaceVisiteurs(){
		
		System.out.println("Jeux::creerInterfaceVisiteurs()");
		
		Box boxPrincipale = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTable = Box.createHorizontalBox() ;
		
		boxEtiquette.add( new JLabel( "Jeux" ) ) ;
			
		this.tabJeux=new JTable(modeleTabActivites);
		this.tabJeux.setRowHeight(30);//hauteur de chaques lignes
		JScrollPane spJeux= new JScrollPane(this.tabJeux);
		spJeux.setPreferredSize(new Dimension(1090,420));// y integre un srool d'une dimension de 1090*420
		//tabPraticienNoto.getColumn("").setCellRenderer(new RendusBoutonVoirVisiteur());
		System.out.println("creerInterfaceVisiteurs()::BEFORE RenduBoutonSelectionActivite");
		tabJeux.getColumn("Modifier").setCellRenderer(new RenduBoutonSelectionActivite());
		
		System.out.println("creerInterfaceVisiteurs()::BEFORE EditeurBoutonSelectionActivite");
		tabJeux.getColumn("Modifier").setCellEditor(new EditeurBoutonSelectionActivite(jeux));
		
		
		boxTable.add(spJeux);
		boxPrincipale.add(boxTable);

		boxPrincipale.add( boxEtiquette ) ;
		boxPrincipale.add( boxTable ) ;
		
		this.add( boxPrincipale ) ;		
	}
//////////A l'origine seuls les getters etaient présent ci-après/////////////////	
	public ModeleActivites getModeleListeJeux() {
		System.out.println("Jeux::getModeleListeJeux()");
	//	this.ctrlBouton.setInitActivite(jeux);
		return modeleTabActivites;
	}
	
	

	public void setModeleTabActivites(ModeleActivites NewmodeleTabActivites) {
		this.modeleTabActivites = NewmodeleTabActivites;
		
	}

	public JTable getTabJeux() {
		return tabJeux;
	}

	public void setTabJeux(JTable tabJeux) {
		this.tabJeux = tabJeux;
	}
	
		
	
}

