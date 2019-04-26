package fr.mairie.modeles;

import java.sql.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.mairie.entites.Activites;
import fr.mairie.entites.Menus;
import fr.mairie.entites.Reservations;
import fr.mairie.modeles.ModeleMairie;

public class ModeleReservation extends AbstractTableModel{

	private List<Reservations> lesReserv;

	private final String[] colonnes = {"Nom de l'élève ","Parents","Adresse","Solde"};
	
	public ModeleReservation() {
		// TODO Auto-generated constructor stub
		System.out.println("ModeleReservation()::Constructor");
		lesReserv=ModeleMairie.getReservations();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colonnes.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		//return 0;
		return lesReserv.size();         // LISTE
	}

	@Override
	public String getColumnName(int column) {
		//affiche le nom des colonnes
		return colonnes[column];
	}

	
	
	@Override
	public Class<?> getColumnClass(int col) {
	
		switch (col){
		
		case 0: return String.class;
				
		case 1:return String.class;
		
		case 2:return String.class;
						
		case 3:	return String.class;	
		}
		return null;	
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
	//	return null;
		
		switch (col) {
		
		case 0: return lesReserv.get(row).getNomEleve();
		case 1: return lesReserv.get(row).getParents();
		case 2: return lesReserv.get(row).getAdresse();
		case 3: return lesReserv.get(row).getSolde();
		
		}
		return null;
		
	}
	/*
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		if(columnIndex == 5){
		
			return true ;
		}
		else{
		
			return false;
		}
	
}*/
	
	public void actualiser(){
		//fireTableDataChanged notifie à la JTable 
		//que les données contenues dans le TableModel ont changé.
		//this.fireTableDataChanged();
		this.fireTableStructureChanged();
	}


	public List<Reservations> getReservations() {
		//return null;
		return lesReserv;
	}


	public void setReservations(List<Reservations> lesReserv) {
		this.lesReserv = lesReserv;
	}


}
