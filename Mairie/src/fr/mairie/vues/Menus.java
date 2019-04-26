package fr.mairie.vues;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.mairie.editeurs.EditeurBoutonSelectionActivite;
import fr.mairie.modeles.ModeleActivites;
import fr.mairie.modeles.ModeleMenus;
import fr.mairie.rendus.RenduBoutonSelectionActivite;

public class Menus extends JPanel{
	
	
	private ModeleMenus modeleTabMenus ;
	private JTable tabMenus;
	
	
	public Menus(){
		super() ;
		System.out.println("Menus::Constructor");
		//this.ctrlBouton.setInitActivite("");
		//this.ctrlBouton.setInitActivite(jeux);
		modeleTabMenus = new ModeleMenus() ;
		this.creerInterfaceVisiteurs() ;
	}
	
	private void creerInterfaceVisiteurs(){
		
		System.out.println("Menus::creerInterfaceVisiteurs()");
		
		Box boxPrincipale = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTable = Box.createHorizontalBox() ;
		
		boxEtiquette.add( new JLabel( "Menus Scolaire" ) ) ;
			
		this.tabMenus=new JTable(modeleTabMenus);
		tabMenus.getColumnModel().getColumn(0).setMaxWidth(200);
		tabMenus.getColumnModel().getColumn(0).setPreferredWidth(200);
		this.tabMenus.setRowHeight(30);//hauteur de chaques lignes
		JScrollPane spJeux= new JScrollPane(this.tabMenus);
		spJeux.setPreferredSize(new Dimension(1090,420));// y integre un srool d'une dimension de 1090*420
		//tabPraticienNoto.getColumn("").setCellRenderer(new RendusBoutonVoirVisiteur());
		
		
		boxTable.add(spJeux);
		boxPrincipale.add(boxTable);

		boxPrincipale.add( boxEtiquette ) ;
		boxPrincipale.add( boxTable ) ;
		
		this.add( boxPrincipale ) ;		
	}
//////////A l'origine seuls les getters etaient présent ci-après/////////////////	
	public ModeleMenus getModeleListeMenus() {
		System.out.println("Jeux::getModeleListeJeux()");
	//	this.ctrlBouton.setInitActivite(jeux);
		return modeleTabMenus;
	}
	
	

	public JTable getTabMenus() {
		return tabMenus;
	}

	
	
		
	
}



