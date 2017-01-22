package principal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class MiDialog extends JDialog{
	JTextField text;
	String valor, nombre, foto;
	JTextField textNombre;
	JRadioButton box1, box2, box3;
	JLabel lMensaje;
	JButton boton1, boton2;
	String msgError = "debes introducir un valor o cancelar";
	
	public MiDialog (JFrame ventana,String titulo, boolean modo) {
		super(ventana,titulo,modo);
		
		this.setSize(400,200);
		this.setLocation (100,100);
		box1 = new JRadioButton("Persiana");
		box2 = new JRadioButton("Luz");
		box3 = new JRadioButton("Aire");
		this.setContentPane(crearPanelCentral());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		  //son equivalentes
		/*
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("Cerrando... ");
				String texto =  text.getText();
				if ((texto.length()!=0 && !texto.equals(MiDialog.this.valor))){
					//MiDialog.this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
					MiDialog.this.valor = texto;
					MiDialog.this.dispose();
				}else{
					lMensaje.setText(msgError);
					
				}
			
			}
		});
		*/
		this.setVisible(true);
		
	}
	private Container crearPanelTexto1(){
		JPanel panelText = new JPanel(new GridLayout(2,1,5,5));
		
		textNombre = new JTextField();
		textNombre.setBorder(BorderFactory.createTitledBorder("Introduce el nombre"));
		ButtonGroup group = new ButtonGroup();
		group.add(box1);
		group.add(box2);
		group.add(box3);
		JPanel pan = new JPanel();
		pan.add(box1);
		pan.add(box2);
		pan.add(box3);
		panelText.add(textNombre);
		panelText.add(pan);
		return panelText;
		
	}

	private Container crearPanelDialogo() {
		JPanel panel = new JPanel (new BorderLayout(0,20));
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panel.add(crearPanelCentral(), BorderLayout.CENTER);
		panel.add(crearPanelMensajes(),BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelMensajes() {
		JPanel panel = new JPanel (new GridLayout(1,1));
		lMensaje = new JLabel("  ");
		lMensaje.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.add(lMensaje);
		return panel;
	}

	private Container crearPanelCentral() {
		JPanel panel = new JPanel (new BorderLayout(0,20));
		panel.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));
		panel.add(crearPanelTexto1(), BorderLayout.CENTER);
		panel.add(crearPanelBotones(),BorderLayout.SOUTH);
		return panel;
	}

	private Component crearPanelBotones() {
		JPanel panel = new JPanel(new GridLayout(1,2,20,0));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
		boton1 = new JButton ("Anadir");
		
	
		boton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				String texto =  textNombre.getText();
				String imagen;
				if(box1.isSelected()){
					imagen = "img/Persiana";
				}else if(box2.isSelected()){
					imagen = "img/BombillaTrans";
				}else{
					imagen = "img/aire";
				}
				pasarValores(texto, imagen);
				MiDialog.this.dispose();
			}
		});
	
		boton2 = new JButton ("Cancel");
		boton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				MiDialog.this.dispose();
			}
		});
		panel.add(boton1);
		panel.add(boton2);
		return panel;
	}
	
	public void pasarValores(String nombre, String imagen){
		this.nombre = nombre;
		this.foto = imagen;
	}

	
	private Component crearPanelTexto() {
		JPanel panel = new JPanel (new GridLayout(1,1));
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.cyan),BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		text = new JTextField (valor);
		panel.add (text);
		return panel;
	}

	String getText(){
		return valor;
	}

}
