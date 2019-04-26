package fr.mairie.rendus;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class RenduBoutonSelectionActivite extends JButton implements TableCellRenderer {
	
	
	public RenduBoutonSelectionActivite(){
		System.out.println("RenduBoutonSelectionActivite::Constructeur");
		setForeground(Color.BLACK);
		setOpaque(true);
		setContentAreaFilled(false); 
		setHorizontalAlignment(SwingConstants.CENTER);
		setHorizontalTextPosition(SwingConstants.CENTER);
		
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		
		if(value != null){
			
			this.setText(value.toString());
			
		}else{
			
			this.setText("Modifier");
		}
		
		return this;
	}

}
