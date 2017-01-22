package principal;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class Vista  implements ActionListener{

	JFrame ventana;
	JMenuBar barra;
	JMenu menuSalir;
	JMenu menuAbrir;
	JScrollPane panelScroll;
	JTextArea jtextarea;
	
	
	public Vista(String nombreCliente){

		ventana = new JFrame ("Server Log Live "+nombreCliente);
		
		ventana.setJMenuBar(crearBarraMenu());
		ventana.getContentPane().add(crearPanelCentral());
		
		ventana.setSize(300,350);
		ventana.setLocation(100, 100);
		
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private JMenuBar crearBarraMenu() {
		barra = new JMenuBar();
		
		barra.add (crearMenuSalir());
		
		return barra;
		
	}

	private JMenu crearMenuSalir() {
		JMenuItem op;
		menuSalir = new JMenu ("Salir");
		
		op=menuSalir.add("Salir");
		op.setIcon(new ImageIcon("img/exit.png"));
		op.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e){
				ventana.dispose();
			}
		});
		return menuSalir;
	}

	private Component crearPanelCentral() {
		jtextarea = new JTextArea();
		JScrollPane panel = new JScrollPane(jtextarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		jtextarea.setEditable(false);
		jtextarea.setVisible(true);

		return panel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Salir")){
			System.exit(0);
		}
	}
	public String getLogs(){
		return jtextarea.getText();
	}
	public void setLog(String log){
		jtextarea.setText(jtextarea.getText()+"\n"+log);
	}
}