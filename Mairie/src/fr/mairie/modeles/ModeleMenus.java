package fr.mairie.modeles;

import java.sql.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.mairie.entites.Activites;
import fr.mairie.entites.Menus;
import fr.mairie.modeles.ModeleMairie;

public class ModeleMenus extends AbstractTableModel{

	private List<Menus> lesMenus;
	
	private final String[] colonnes = {"date","Menu de la Semaine"};
	
	public ModeleMenus()  {
		// TODO Auto-generated constructor stub
		System.out.println("ModeleMenus()::Constructor");
		lesMenus=ModeleMairie.getMenus();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colonnes.length;
	}

	@Override
	public int getRowCount() {
		//return 0;
		// TODO Auto-generated method stub
		return lesMenus.size();       //   LISTE
	}
	
	@Override
	public String getColumnName(int column) {
		//affiche le nom des colonnes
		return colonnes[column];
	}

	
	
	@Override
	public Class<?> getColumnClass(int col) {
		// VOTRE CODE ICI - Question 5
		switch (col){
		case 0: return Date.class;
				
		case 1:return String.class;
			
		}
		return null;	
	}
	

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		
		switch (col) {
		
		case 0: return lesMenus.get(row).getDate();
		case 1: return lesMenus.get(row).getMenuSemaine();
		
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


	public List<Menus> getMenus() {
		return lesMenus;
	}


	public void setMenus(List<Menus> lesMenus) {
		this.lesMenus = lesMenus;
	}

}
