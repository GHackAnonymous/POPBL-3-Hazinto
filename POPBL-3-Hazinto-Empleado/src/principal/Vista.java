package principal;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Vista  implements ActionListener{

	Controlador control = null;
	JFrame ventana;
	JMenuBar barra;
	JMenu menuSalir;
	JMenu menuAbrir;
	JScrollPane panelScroll;
	List<Log> listaLogs;
	JTable vTabla;
	ModeloTablaLog tabla;
	TrazadorTablaLog trazador;
	ModeloColumnasTablaLog columnas;
	
	
	public Vista(){
		
		control = new Controlador();
		
		trazador = new TrazadorTablaLog();
		columnas = new ModeloColumnasTablaLog(trazador);
		tabla = new ModeloTablaLog(columnas, control);
		
		ventana = new JFrame ("Visor de Log");
		
		ventana.setJMenuBar(crearBarraMenu());
		//ventana.setExtendedState(ventana.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		ventana.setSize(600,400);
		ventana.setLocation(100, 100);
		
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
					
					listaLogs = tabla.leerTablaFichero(path);
					
					ventana.getContentPane().add(crearPanelCentral());
					
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
		vTabla = new JTable(tabla,columnas);
		JScrollPane panel = new JScrollPane(vTabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		vTabla.setFillsViewportHeight(true);
		vTabla.setDefaultEditor(Object.class, null);

		return panel;
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
