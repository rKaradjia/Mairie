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

public class Sport extends JPanel {

	
	private ControleurBoutonSelectionActivite ctrlBouton;
	private ModeleActivites modeleTabActivites ;
	private JTable tabSport;
	private String sport="sport";
	
	public Sport(){
		super() ;
		
		modeleTabActivites = new ModeleActivites(sport) ;
		this.creerInterfaceVisiteurs() ;
	}
	
	private void creerInterfaceVisiteurs(){
		
		System.out.println("Sport::creerInterfaceVisiteurs()");
		
		Box boxPrincipale = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTable = Box.createHorizontalBox() ;
		
		boxEtiquette.add( new JLabel( "Sport" ) ) ;
			
		this.tabSport=new JTable(modeleTabActivites);
		this.tabSport.setRowHeight(30);//hauteur de chaques lignes
		JScrollPane spJeux= new JScrollPane(this.tabSport);
		spJeux.setPreferredSize(new Dimension(1090,420));// y integre un srool d'une dimension de 1090*420
		//tabPraticienNoto.getColumn("").setCellRenderer(new RendusBoutonVoirVisiteur());
		tabSport.getColumn("Modifier").setCellRenderer(new RenduBoutonSelectionActivite());
		tabSport.getColumn("Modifier").setCellEditor(new EditeurBoutonSelectionActivite(sport));
		
		boxTable.add(spJeux);
		boxPrincipale.add(boxTable);

		boxPrincipale.add( boxEtiquette ) ;
		boxPrincipale.add( boxTable ) ;
		
		this.add( boxPrincipale ) ;
		
	}
	
	public ModeleActivites getModeleListeJeux() {
		
		this.ctrlBouton.setInitActivite(sport);
		return modeleTabActivites;
	}

	public JTable getTabSport() {
		return tabSport;
	}
	
	
	
	
}


