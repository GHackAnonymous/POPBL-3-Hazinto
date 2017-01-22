package principal;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;



public class AdaptadorPaneles implements ListCellRenderer<Objeto> {
	
	public AdaptadorPaneles(){
		
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Objeto> list, Objeto value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JPanel panel = new JPanel(new GridLayout(2,2,5,5));
		
			panel.add(value);
			panel.setOpaque(true);
	
		System.out.println();
		return panel;
	}
	
	
}
