package fr.mairie.vues;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.mairie.controleurs.ControleurBoutonSelectionActivite;
import fr.mairie.editeurs.EditeurBoutonSelectionActivite;
import fr.mairie.modeles.ModeleActivites;
import fr.mairie.rendus.RenduBoutonSelectionActivite;
import fr.mairie.modeles.ModeleActivites;

public class Films extends JPanel {

	private ControleurBoutonSelectionActivite ctrlBouton;
	
	private ModeleActivites modeleTabActivites ;
	private JTable tabFilms;
	private String films="films";
	
	public Films(){
		super() ;
		//this.ctrlBouton.setInitActivite("");
		
		modeleTabActivites = new ModeleActivites(films) ;
		this.creerInterfaceVisiteurs() ;
	}
	
	private void creerInterfaceVisiteurs(){
		
		System.out.println("Films::creerInterfaceVisiteurs()");
		
		Box boxPrincipale = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTable = Box.createHorizontalBox() ;
		
		boxEtiquette.add( new JLabel( "Cinéma" ) ) ;
			
		this.tabFilms=new JTable(modeleTabActivites);
		this.tabFilms.setRowHeight(30);//hauteur de chaques lignes
		JScrollPane spJeux= new JScrollPane(this.tabFilms);
		spJeux.setPreferredSize(new Dimension(1090,420));// y integre un srool d'une dimension de 1090*420
		//tabPraticienNoto.getColumn("").setCellRenderer(new RendusBoutonVoirVisiteur());
		tabFilms.getColumn("Modifier").setCellRenderer(new RenduBoutonSelectionActivite());
		tabFilms.getColumn("Modifier").setCellEditor(new EditeurBoutonSelectionActivite(films));
		
		boxTable.add(spJeux);
		boxPrincipale.add(boxTable);

		boxPrincipale.add( boxEtiquette ) ;
		boxPrincipale.add( boxTable ) ;
		
		this.add( boxPrincipale ) ;
		
	}
	
	public ModeleActivites getModeleListeJeux() {
		this.ctrlBouton.setInitActivite(films);
		return modeleTabActivites;
	}

	public JTable getTabFilms() {
		return tabFilms;
	}
	
	
	
	
}

