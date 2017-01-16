package principal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class Vista  implements ActionListener{

	Controlador control = null;
	JFrame ventana;
	JMenuBar barra;
	JMenu menuSalir;
	JMenu menuAbrir;
	JScrollPane panelScroll;
	List<Log> listaLogs;
	JTable vTabla;
	final static String [] NOMBRE_COLUMNAS = {"Comando", "Hora","Fecha"};
	
	
	public Vista(){
		
		control = new Controlador();
		
		ventana = new JFrame ("Visor de Log");
		
		ventana.setJMenuBar(crearBarraMenu());
		ventana.setExtendedState(ventana.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		ventana.setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JMenuBar crearBarraMenu() {
		barra = new JMenuBar();
		
		barra.add (crearMenuAbrir());
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

	private JMenu crearMenuAbrir() {
		
		JMenuItem op;
		menuAbrir = new JMenu ("Abrir");
		
		op=menuAbrir.add("Abrir");
		op.setIcon(new ImageIcon("img/abrir.png"));
		op.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e){
				JFileChooser selectorDirectorio = new JFileChooser();

				int result = selectorDirectorio.showOpenDialog(ventana);
				
				File directorio = selectorDirectorio.getSelectedFile();
				String path = "";
				if (directorio!=null && result == JFileChooser.APPROVE_OPTION){
					path = directorio.getPath();
					
					listaLogs = control.leerTXT(path);
					
					ventana.getContentPane().add(crearPanelCentral());
					
					/*DefaultTableModel modelo=(DefaultTableModel) vTabla.getModel(); 
					
					modelo.addRow(crearArray(listaLogs));
					vTabla.setModel(modelo); 
					*/
					
					vTabla.repaint();
					vTabla.revalidate();
					vTabla.repaint();
					
					ventana.repaint();
					ventana.revalidate();
					ventana.repaint();
					
				}
			}
		});
		return menuAbrir;
		
	}
	private Component crearPanelCentral() {
		//Tabla dentro de un scroll panel
		
		vTabla = new JTable(crearArray(listaLogs),NOMBRE_COLUMNAS);
		//vTabla.setFillsViewportHeight(true);
		JScrollPane panel = new JScrollPane(vTabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		return panel;
	}
	private String [][] crearArray(List<Log> tabla) {
		String [][] datos = new String [tabla.size()][NOMBRE_COLUMNAS.length];
		int i = 0;
		for (Log log: tabla){
			datos[i] = log.toArray();
			i++;
		}
		
		for(int e = 0; e<4;e++){
			for(int g = 0;g<3;g++){
				System.out.print(datos[e][g]);
			}
			System.out.println("");
		}
		
		return datos;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Salir")){
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		try {
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Vista ejercicio = new Vista();
		
	}
}
