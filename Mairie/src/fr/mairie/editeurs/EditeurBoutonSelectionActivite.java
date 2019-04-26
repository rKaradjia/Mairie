package fr.mairie.editeurs;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.TableCellEditor;

import fr.mairie.controleurs.ControleurBoutonSelectionActivite;

public class EditeurBoutonSelectionActivite extends DefaultCellEditor{

	String activite;
	protected JButton button = new JButton();
	private ControleurBoutonSelectionActivite actCtrl;
	
	public EditeurBoutonSelectionActivite() {
		
		super(new JCheckBox());
		System.out.println("EditeurBoutonSelectionActivite::Constructeur");
		System.out.println("ATTENTION : Ce controleur n'est pas censée être appelé");
		this.button.addActionListener(this.actCtrl);
	}

	public EditeurBoutonSelectionActivite(String act) {
		// TODO Auto-generated constructor stub
		super(new JCheckBox());
		System.out.println("EditeurBoutonSelectionActivite::Constructeur   " + act);
		this.activite=act;
		actCtrl = new ControleurBoutonSelectionActivite(activite) ;
		this.button.addActionListener(this.actCtrl);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		System.out.println("EditeurBoutonSelectionActivite::getTableCellEditorComponent()");
		super.getTableCellEditorComponent(table, value, isSelected, row, column);
		
		this.actCtrl.setRow(row);
		
		if(value == null){
			this.button.setText("");
		}
		
		else{
			this.button.setText(value.toString());
		}
		
		return this.button;
	}
	
		
	

	

	

}
