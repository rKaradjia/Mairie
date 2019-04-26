package fr.mairie.vues;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.mairie.modeles.ModeleMenus;
import fr.mairie.modeles.ModeleSanteAssoc;

public class Associations extends JPanel {
	private ModeleSanteAssoc modeleTabSanteA ;
	private JTable tabAssoc;
	
	
	public Associations(){
		super() ;
		System.out.println("Associations::Constructor");
		//this.ctrlBouton.setInitActivite("");
		//this.ctrlBouton.setInitActivite(jeux);
		modeleTabSanteA = new ModeleSanteAssoc("Associations") ;
		this.creerInterfaceVisiteurs() ;
	}
	
	private void creerInterfaceVisiteurs(){
		
		System.out.println("Associations::creerInterfaceVisiteurs()");
		
		Box boxPrincipale = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTable = Box.createHorizontalBox() ;
		
		boxEtiquette.add( new JLabel( "Associations" ) ) ;
			
		this.tabAssoc=new JTable(modeleTabSanteA);
		tabAssoc.getColumnModel().getColumn(0).setMaxWidth(200);
		tabAssoc.getColumnModel().getColumn(0).setPreferredWidth(200);
		this.tabAssoc.setRowHeight(30);//hauteur de chaques lignes
		JScrollPane spJeux= new JScrollPane(this.tabAssoc);
		spJeux.setPreferredSize(new Dimension(1090,420));// y integre un srool d'une dimension de 1090*420
		//tabPraticienNoto.getColumn("").setCellRenderer(new RendusBoutonVoirVisiteur());
		
		
		boxTable.add(spJeux);
		boxPrincipale.add(boxTable);

		boxPrincipale.add( boxEtiquette ) ;
		boxPrincipale.add( boxTable ) ;
		
		this.add( boxPrincipale ) ;		
	}
//////////A l'origine seuls les getters etaient présent ci-après/////////////////	
	public ModeleSanteAssoc getModeleListeMedecineG() {
		System.out.println("Jeux::getModeleListeJeux()");
	//	this.ctrlBouton.setInitActivite(jeux);
		return modeleTabSanteA;
	}
	
	

	public JTable getTabAssoc() {
		return tabAssoc;
	}

	
	
		
}
