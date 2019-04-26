package fr.mairie.modeles;

import java.sql.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import fr.mairie.entites.Activites;

public class ModeleActivites extends AbstractTableModel{
	
	
	
	private List<Activites> untypeAct;
	
	private final String[] colonnes = {"nom","date","lieu","responsable","Nbre de participants","Modifier"};

	public ModeleActivites(String act) {         //transmission de la vue a affiche
		// TODO Auto-generated constructor stub   //transmission de l actvite a afficher
		System.out.println("ModeleActivites()String::Constructor");
		untypeAct = ModeleMairie.getUneActivites(act) ;
	}

	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colonnes.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return untypeAct.size();
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
		case 0: return String.class;
				
		case 1:return Date.class;
		              
		case 2:return String.class;

		case 3:return String.class;
		
		case 4:return Integer.class;
		
		case 5:return String.class;
			
		}
		return null;	
	}
	
	
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		switch (col) {
		
		case 0: return untypeAct.get(row).getNom();
		case 1: return untypeAct.get(row).getDate();
		case 2: return untypeAct.get(row).getLieu();
		case 3: return untypeAct.get(row).getResponsable();
		case 4:	return untypeAct.get(row).getNbParticipants();
		case 5:{return "Modifier";}
		}
		
		
		return null;
	}
	
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
			if(columnIndex == 5){
			
				return true ;
			}
			else{
			
				return false;
			}
		
	}
	
	
	public void actualiser(){
		//fireTableDataChanged notifie à la JTable 
		//que les données contenues dans le TableModel ont changé.
		//this.fireTableDataChanged();
		this.fireTableStructureChanged();
	}


	public List<Activites> getUntypeAct() {
		return untypeAct;
	}


	public void setUntypeAct(List<Activites> untypeAct) {
		this.untypeAct = untypeAct;
	}

}
