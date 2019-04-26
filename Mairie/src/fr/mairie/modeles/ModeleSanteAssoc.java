package fr.mairie.modeles;

import java.sql.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import fr.mairie.entites.Activites;
import fr.mairie.entites.SanteAssoc;

public class ModeleSanteAssoc extends AbstractTableModel{
	
	
	private List<SanteAssoc> untypeSanteA;
	
	private final String[] colonnes = {"Identite du responsable","Adresse","Commentaire"};

	public ModeleSanteAssoc(String type) {         //transmission de la vue a affiche
		// TODO Auto-generated constructor stub   //transmission de l actvite a afficher
		untypeSanteA = ModeleMairie.getTypeSanteAssoc(type) ;
	}

	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colonnes.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return untypeSanteA.size();
	}
	
	@Override
	public String getColumnName(int column) {
		// VOTRE CODE ICI - Question 5
		return colonnes[column];
	}

	
	@Override
	public Class<?> getColumnClass(int col) {
		// VOTRE CODE ICI - Question 5
		switch (col){
		case 0: return String.class;
				
		case 1:return String.class;
		
		case 2:return String.class;
		              
		
			
		}
		return null;	
	}
	
	
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		switch (col) {
		
		case 0: return untypeSanteA.get(row).getIdentiteResp();
		case 1: return untypeSanteA.get(row).getAdresse();
		case 2: return untypeSanteA.get(row).getCommentaire();
		
		}
		
		
		return null;
	}
	
	
	public void actualiser(){
		//fireTableDataChanged notifie à la JTable 
		//que les données contenues dans le TableModel ont changé.
		this.fireTableDataChanged();
	}

}
