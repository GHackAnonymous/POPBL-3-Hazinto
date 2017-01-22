package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;


public class Graficos {

	JFrame frame;
	JPanel panel, panelIzquierdaTotal, panelBotones;
	JLabel consumo, paginacion;
	String consumoT;
	JPanel panelDerecha, panelIzquierda, panelTop;
	Objeto obj1, obj2, obj3, obj4, obj5;
	ImageIcon imagen;
	JButton iz, dh;
	List<Objeto> listaElectrodomesticos, lista1;
	int posicion = 4;
	int posicion1 = 5;
	int minimo, incremento;
	Controlador controlador;
    VariablesComunes vc;

	public Graficos(Controlador controlador, VariablesComunes vc) {
		this.controlador = controlador;
		this.vc = vc;
		listaElectrodomesticos = new ArrayList<>();
		lista1 = new ArrayList<>();
		frame = new JFrame("Hazinto Beta 1.01");
		imagen = new ImageIcon("img/hazinto.jpg");
		frame.setBounds(200, 200, 1000, 600);
		frame.setResizable(false);
		frame.getContentPane().add(crearAll());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		minimo = 1;
		incremento = 5;
	}
	public Component crearAll() {
		panel = new JPanel(new BorderLayout());
		panel.add(crearPanelIzda(),BorderLayout.WEST);
		panel.add(crearPanelDcha(),BorderLayout.CENTER);
		panel.add(crearToolBar(),BorderLayout.NORTH);
		//System.out.println(listaElectrodomesticos.get(1).getHeight() + "/" + listaElectrodomesticos.get(1).getWidth());
		return panel;
	}

	private List<Objeto> cargarConfigDeFichero() {
		//dependiendo del fichero de configuracion
		String linea ;
		try (BufferedReader in = new BufferedReader (new FileReader("files/Conf/Config.txt"))) { //buffered reader trabajar con las lineas
			while ((linea = in.readLine())!=null){ //leer de linea  en linea y al llegar al final devuelve null
				String [] palabras = linea.toLowerCase().split("[$]");//coger string y dividirlo como tu se lo digas
				obj1 = new Objeto(palabras[0], false,  Double.valueOf(palabras[1]), palabras[2],this.vc);

				listaElectrodomesticos.add(obj1);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < 4; i++){
			lista1.add(listaElectrodomesticos.get(i));
		}
		
		return listaElectrodomesticos;
	}
	private JToolBar crearToolBar() {
		JToolBar toolBar = new JToolBar(); // la barra de herramientas
		toolBar.setBorder(BorderFactory.createRaisedBevelBorder());
		JButton boton;
		boton =(JButton) toolBar.add(new JButton (new ImageIcon("img/window_new.png")));
		boton.setText("Anadir dispositivo");
		boton.setActionCommand("Open");
		boton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				MiDialog dialogo = new MiDialog (frame,"Anadir dispositivo",true);
				String te;
				if(dialogo.boton1.isSelected()){
					te = dialogo.nombre+"$"+"0"+"$"+dialogo.foto;
					File TextFile = new File("files/Conf/Config.txt");
					FileWriter TextOut;
					try {
						TextOut = new FileWriter(TextFile, true);
						TextOut.write(te);
						TextOut.close();
					} catch (IOException e1) {
						// TODO Bloque catch generado autom¨¢ticamente
						e1.printStackTrace();
					}
					
					}
				//listaElectrodomesticos.clear();
				//cargarConfigDeFichero();
				//actualizar();
				}
				
		});
		
		
		return toolBar;
	}
	public List pasarListaDr(){
		lista1.clear();
		int kont = 0;
		iz.setEnabled(true);
		for(int i = 0; i < 4; i++){
			
			if(posicion + i < listaElectrodomesticos.size()){
				lista1.add(listaElectrodomesticos.get(posicion+i));
				kont++;
			}else{
				break;
			}
		}
		posicion += kont;
		posicion1 += kont;
		if(posicion >= listaElectrodomesticos.size()){
			dh.setEnabled(false);
			iz.setEnabled(true);
		}else{
			dh.setEnabled(true);
		}
		return lista1;	
	}
	public List pasarListaIz(){
		
		int j;
		int y = lista1.size();
		lista1.clear();
		int kont = 0;
		dh.setEnabled(true);
		if(y % 4 > 0){
			for(int i = 0; i < 4; i++){
					
					j = posicion - (4 + (y - i));
					lista1.add(listaElectrodomesticos.get(j));
					kont++;
			}
			posicion -= y;
		}else{
			for(int x = 0; x < 4; x++){
				j = posicion - (8 - x);
				lista1.add(listaElectrodomesticos.get(j));
				kont++;
			}
			posicion -= kont;
		}
		
		
		posicion1 -= kont;
		if(posicion <= 4){
			iz.setEnabled(false);
			dh.setEnabled(true);
		}else{
			iz.setEnabled(true);
		}
		return lista1;	
	}
	public Component actualizar(){
		panelIzquierda.removeAll();
		for(int i = 0; i < lista1.size(); i++){
			panelIzquierda.add(lista1.get(i));
			
		}
		paginacion.setText(posicion +"/" +listaElectrodomesticos.size());
		paginacion.repaint();
		panelIzquierda.validate();
		panelIzquierda.repaint();
		panelIzquierdaTotal.repaint();
		return panelIzquierda;
		
	}
	private Component crearPanelDcha() {
		cargarConfigDeFichero();
		int cantidadElectrodomesticos = listaElectrodomesticos.size();
		
		panelIzquierdaTotal = new JPanel(new BorderLayout());
		panelIzquierda = new JPanel(new GridLayout(2,2,5,5));
		System.out.println(lista1.size());
		for(int i = 0; i < 4; i++){
			
			panelIzquierda.add(lista1.get(i));
		}
			/*iz = new JButton("<");
			dh = new JButton(">");
			panelBotones = new JPanel();
			for(int i = 0; i < 4; i++){
				
				
			}
			
			AdaptadorPaneles adapter = new AdaptadorPaneles();
			iz.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					
				}
				
			});
			panelBotones.add(iz);
			panelBotones.add(dh);
			panelIzquierdaTotal.add(panelBotones, BorderLayout.SOUTH);
			JScrollPane panel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			lista = new JList<>();
			modelo.cargarConfigDeFichero();
			lista.setModel(modelo);
			lista.setCellRenderer(adapter);
			panel.setViewportView(lista);
			panelIzquierdaTotal.add(panel, BorderLayout.CENTER);*/
	
		
		paginacion = new JLabel();
		paginacion.setText(posicion +"/" +cantidadElectrodomesticos);
		iz = new JButton("<");
		iz.setSize(100, 30);
		iz.setBackground(Color.BLUE.gray);
		iz.setEnabled(false);
		dh = new JButton(">");
		dh.setBackground(Color.gray);
		panelBotones = new JPanel();
		panelIzquierdaTotal = new JPanel(new BorderLayout());
		/*panelIzquierda = new JPanel(new GridLayout(2,2,5,5));
		obj1 = new Objeto("Luz", false, 22.4, "img/luzahorro.png");
		panelIzquierda.add(obj1);
		obj2 = new Objeto("Persiana", true, 22.4, "img/luzoff.jpg");
		panelIzquierda.add(obj2);
		obj3 = new Objeto("Luz", false, 22.4, "img/luza.jpg");
		panelIzquierda.add(obj3);
		obj4 = new Objeto("Persiana", false, 22.4, "img/luzoff.jpg");
		panelIzquierda.add(obj4);*/
		
		panelIzquierdaTotal.add(panelIzquierda, BorderLayout.CENTER);
		iz.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pasarListaIz();
				actualizar();
				System.out.println(lista1.size());
				System.out.println(posicion);
			}
			
		});
		dh.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pasarListaDr();
				actualizar();
				System.out.println(lista1.size());
				System.out.println(posicion);
			}
			
		});
		panelBotones.add(iz);
		panelBotones.add(dh);
		panelBotones.add(paginacion);
		panelIzquierdaTotal.add(panelBotones, BorderLayout.SOUTH);
		
		return panelIzquierdaTotal;
	}

	private Component crearPanelIzda() {
		JLabel imagen;
		imagen = new JLabel();
		imagen.setIcon(this.imagen);
		panelDerecha = new JPanel(new BorderLayout());
		consumo = new JLabel();
		panelTop = new JPanel();
		panelTop.add(imagen);
		consumo.setText(recogerConsumo());
		panelDerecha.add(panelTop, BorderLayout.CENTER);
		//panelDerecha.add(consumo, BorderLayout.NORTH);
		return panelDerecha;
	}
	private String recogerConsumo() {
		double con = 0;
		con = 44.5;
		
		consumoT = Double.toString(con);
		return consumoT;
	}
}
