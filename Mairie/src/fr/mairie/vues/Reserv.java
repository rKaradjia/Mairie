package fr.mairie.vues;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.mairie.editeurs.EditeurBoutonSelectionActivite;
import fr.mairie.modeles.ModeleActivites;
import fr.mairie.modeles.ModeleReservation;
import fr.mairie.rendus.RenduBoutonSelectionActivite;

public class Reserv extends JPanel{
	private ModeleReservation modeleTabReservation ;
	private JTable tabReserv;
	
	public Reserv(){
		super() ;
		System.out.println("Reserv::Constructor");
		//this.ctrlBouton.setInitActivite("");
		//this.ctrlBouton.setInitActivite(jeux);
		modeleTabReservation = new ModeleReservation() ;
		this.creerInterfaceVisiteurs() ;
	}
	
	private void creerInterfaceVisiteurs(){
		
		System.out.println("Reserv::creerInterfaceVisiteurs()");
		
		Box boxPrincipale = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTable = Box.createHorizontalBox() ;
		
		boxEtiquette.add( new JLabel( "Reservation" ) ) ;
			
		this.tabReserv=new JTable(modeleTabReservation);
		this.tabReserv.setRowHeight(30);//hauteur de chaques lignes
		JScrollPane spJeux= new JScrollPane(this.tabReserv);
		spJeux.setPreferredSize(new Dimension(1090,420));// y integre un srool d'une dimension de 1090*420
		//tabPraticienNoto.getColumn("").setCellRenderer(new RendusBoutonVoirVisiteur());
		//System.out.println("creerInterfaceVisiteurs()::BEFORE RenduBoutonSelectionActivite");
		/*tabJeux.getColumn("Modifier").setCellRenderer(new RenduBoutonSelectionActivite());		
		System.out.println("creerInterfaceVisiteurs()::BEFORE EditeurBoutonSelectionActivite");
		tabJeux.getColumn("Modifier").setCellEditor(new EditeurBoutonSelectionActivite(reserv));*/
		
		
		boxTable.add(spJeux);
		boxPrincipale.add(boxTable);

		boxPrincipale.add( boxEtiquette ) ;
		boxPrincipale.add( boxTable ) ;
		
		this.add( boxPrincipale ) ;		
	}

	public ModeleReservation getModeleListeReserv() {
		System.out.println("Reservation::getModeleListeReserv()");
	
		return modeleTabReservation;
	}


	public JTable getTabReserv() {
		return tabReserv;
	}

	
		
	
}


