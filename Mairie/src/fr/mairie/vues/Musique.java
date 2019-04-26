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

public class Musique extends JPanel {

	private ControleurBoutonSelectionActivite ctrlBouton;
	
	private ModeleActivites modeleTabActivites ;
	private JTable tabMusique;
	private String musique="musique";
	
	public Musique(){
		super() ;
	
		modeleTabActivites = new ModeleActivites(musique) ;
		this.creerInterfaceVisiteurs() ;
	}
	
	private void creerInterfaceVisiteurs(){
		
		System.out.println("Musique::creerInterfaceVisiteurs()");
		
		Box boxPrincipale = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTable = Box.createHorizontalBox() ;
		
		boxEtiquette.add( new JLabel( "Musique" ) ) ;
			
		this.tabMusique=new JTable(modeleTabActivites);
		this.tabMusique.setRowHeight(30);//hauteur de chaques lignes
		JScrollPane spJeux= new JScrollPane(this.tabMusique);
		spJeux.setPreferredSize(new Dimension(1090,420));// y integre un srool d'une dimension de 1090*420
		//tabPraticienNoto.getColumn("").setCellRenderer(new RendusBoutonVoirVisiteur());
		tabMusique.getColumn("Modifier").setCellRenderer(new RenduBoutonSelectionActivite());
		tabMusique.getColumn("Modifier").setCellEditor(new EditeurBoutonSelectionActivite(musique));
		
		boxTable.add(spJeux);
		boxPrincipale.add(boxTable);

		boxPrincipale.add( boxEtiquette ) ;
		boxPrincipale.add( boxTable ) ;
		
		this.add( boxPrincipale ) ;
		
	}
	
	public ModeleActivites getModeleListeJeux() {
		this.ctrlBouton.setInitActivite(musique);
		return modeleTabActivites;
	}

	public JTable getTabMusique() {
		return tabMusique;
	}
	
	
	
	
}

