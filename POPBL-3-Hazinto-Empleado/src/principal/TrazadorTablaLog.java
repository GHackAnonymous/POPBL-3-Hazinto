package principal;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TrazadorTablaLog extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object valor,
			boolean isSelected, boolean hasFocus, int fila, int columna) {
		
		super.getTableCellRendererComponent(table, valor, isSelected, hasFocus, fila, columna);
		switch (columna){
		case 0: this.setHorizontalAlignment(LEFT);break;
		case 1: this.setHorizontalAlignment(CENTER);break;
		case 2: this.setHorizontalAlignment(CENTER);break;
		}
		return this;
	}

}
