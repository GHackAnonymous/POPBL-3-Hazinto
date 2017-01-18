package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class Graficos {

	JFrame frame;
	JPanel panel, panelIzquierdaTotal, panelBotones;
	JLabel consumo;
	String consumoT;
	JPanel panelDerecha, panelIzquierda, panelTop;
	Objeto obj1, obj2, obj3, obj4, obj5;
	ImageIcon imagen;
	JButton iz, dh;
	List<Objeto> listaElectrodomesticos, lista1;
	int posicion = 4;
	int minimo, incremento;
	VariablesComunes vc;
	Controlador controlador;

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
		panel.add(crearPanelIzda(), BorderLayout.WEST);
		panel.add(crearPanelDcha(), BorderLayout.CENTER);
		return panel;
	}

	private List<Objeto> cargarConfigDeFichero() {
		// dependiendo del fichero de configuracion
		String linea;
		try (BufferedReader in = new BufferedReader(new FileReader("Conf/Config.txt"))) { // buffered
																							// reader
																							// trabajar
																							// con
																							// las
																							// lineas

			while ((linea = in.readLine()) != null) { // leer de linea en linea
														// y al llegar al final
														// devuelve null
				String[] palabras = linea.toLowerCase().split("[$]");// coger
																		// string
																		// y
																		// dividirlo
																		// como
																		// tu se
																		// lo
																		// digas
				obj1 = new Objeto(palabras[0], false, Double.valueOf(palabras[1]), palabras[2]);

				listaElectrodomesticos.add(obj1);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listaElectrodomesticos;
	}

	private Component crearPanelDcha() {

		int cantidadElectrodomesticos = listaElectrodomesticos.size();

		panelIzquierdaTotal = new JPanel(new BorderLayout());
		panelIzquierda = new JPanel(new GridLayout(2, 2, 5, 5));

		iz = new JButton("<");
		dh = new JButton(">");
		panelBotones = new JPanel();
		panelIzquierdaTotal = new JPanel(new BorderLayout());
		panelIzquierda = new JPanel(new GridLayout(2, 2, 5, 5));
		obj1 = new Objeto("Luz", false, 22.4, "img/luzahorro.png");
		panelIzquierda.add(obj1);
		obj2 = new Objeto("Persiana", true, 22.4, "img/luzoff.jpg");
		panelIzquierda.add(obj2);
		obj3 = new Objeto("Luz", false, 22.4, "img/luza.jpg");
		panelIzquierda.add(obj3);
		obj4 = new Objeto("Persiana", false, 22.4, "img/luzoff.jpg");
		panelIzquierda.add(obj4);

		panelIzquierdaTotal.add(panelIzquierda, BorderLayout.CENTER);

		panelBotones.add(iz);
		panelBotones.add(dh);
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
		// panelDerecha.add(consumo, BorderLayout.NORTH);
		return panelDerecha;
	}
	private String recogerConsumo() {
		double con = 0;
		con = 44.5;
		
		consumoT = Double.toString(con);
		return consumoT;
	}
}
