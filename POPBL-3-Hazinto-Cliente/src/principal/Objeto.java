package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Objeto extends JPanel implements Observer{
	String nombre;
	boolean estado;
	double consumo;
	ImageIcon imagen;
	JLabel estado1;
	JPanel botones;
	JPanel panelPrincipal, panelCambio1, panelBoton, panelCambio2;
	JButton boton;
	String [] palabras;
	VariablesComunes vc;
	JButton botonUp, botonDown;

	public Objeto(String nombre, boolean estado, double consumo, String imagen, VariablesComunes vc){
		super(new BorderLayout());
		this.vc = vc;
		this.vc.addObserver(this);
		this.nombre = nombre;
		this.estado = estado;
		this.consumo = consumo;
		this.imagen = new ImageIcon(imagen);
		botones = new JPanel(new BorderLayout());
		panelCambio2 = new JPanel(new BorderLayout(0,6));
		panelPrincipal = new JPanel(new BorderLayout(0,6));
		palabras = nombre.toLowerCase().split("[ ]");
		if(palabras[0].equals("persiana")){
			this.add(crearPanelPersiana(), BorderLayout.CENTER);
		}else{
			this.add(crearPanelNormal(), BorderLayout.CENTER);
		}
	}
	public JPanel crearPanelBoton(){
		panelBoton = new JPanel(new BorderLayout());
		boton = new JButton(nombre);
		boton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(boton.getText().equals(nombre)){
					boton.setText("Back");
					changeContent(1);
					panelCambio1.setEnabled(false);
					panelCambio2.setEnabled(true);
				}else{
					boton.setText(nombre);
					changeContent(2);
					panelCambio1.setEnabled(true);
					panelCambio2.setEnabled(false);
				}
				
				
			}
			
		});
		panelBoton.add(boton, BorderLayout.SOUTH);
		return panelBoton;
		
	}
	public JPanel crearPanelPersiana(){
		JLabel nombre, consumo, imagen;
		panelCambio1 = new JPanel(new BorderLayout());
		
		botonUp = new JButton("Up");
		botonDown = new JButton("Down");
		nombre = new JLabel();
		nombre.setText(this.nombre);
		Font fuente = new Font("Monospaced", Font.BOLD, 20);
        nombre.setFont(fuente);
		consumo = new JLabel("Consumo: "+String.valueOf(this.consumo));
		consumo.setFont(fuente);
		estado1 = new JLabel(this.estado ? "UP" : "DOWN");
		imagen = new JLabel();
		imagen.setIcon(this.imagen);
		botonUp.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cambiarEstado();
				botonUp.setEnabled(false);
				botonDown.setEnabled(true);
				
			}
			
		});
		botonDown.setEnabled(false);
		botonDown.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cambiarEstado();
				botonUp.setEnabled(true);
				botonDown.setEnabled(false);
				
			}
			
		});
		botones.add(botonUp, BorderLayout.NORTH);
		botones.add(botonDown, BorderLayout.SOUTH);
		this.panelCambio1.add(imagen, BorderLayout.CENTER);
		this.panelCambio1.add(botones, BorderLayout.WEST);
		this.panelCambio2.add(nombre, BorderLayout.NORTH);
		this.panelCambio2.add(consumo, BorderLayout.SOUTH);
		this.panelCambio2.add(estado1, BorderLayout.CENTER);
		/*this.panelCambio1.setBorder(BorderFactory.createLineBorder(Color.black));
		this.panelCambio2.setBorder(BorderFactory.createLineBorder(Color.black));*/
		this.panelPrincipal.add(crearPanelBoton(), BorderLayout.SOUTH);
		this.panelPrincipal.add(panelCambio1, BorderLayout.CENTER);

		if(this.estado){
			botones.setBackground(Color.gray.brighter().brighter().brighter());
			this.panelCambio1.setBackground(Color.gray.brighter().brighter().brighter());
		}else{
			botones.setBackground(Color.gray);
			this.panelCambio1.setBackground(Color.gray);
		}
		
		
		return panelPrincipal;
	}
	public JPanel crearPanelNormal(){
		JLabel nombre, consumo, imagen;
		panelCambio1 = new JPanel();
		nombre = new JLabel();
		nombre.setText(this.nombre);
		Font fuente = new Font("Monospaced", Font.BOLD, 20);
        nombre.setFont(fuente);
		consumo = new JLabel("Consumo: "+String.valueOf(this.consumo));
		consumo.setFont(fuente);
		if(palabras[0].equals("persiana")){
			estado1 = new JLabel(this.estado ? "Up" : "Down");
		}else{
			estado1 = new JLabel(this.estado ? "ON" : "OFF");
		}
		
		imagen = new JLabel();
		imagen.setIcon(this.imagen);
		this.panelCambio1.add(imagen);
		this.panelCambio2.add(nombre, BorderLayout.NORTH);
		this.panelCambio2.add(consumo, BorderLayout.SOUTH);
		this.panelCambio2.add(estado1, BorderLayout.CENTER);
		/*this.panelCambio1.setBorder(BorderFactory.createLineBorder(Color.black));
		this.panelCambio2.setBorder(BorderFactory.createLineBorder(Color.black));*/
		this.panelPrincipal.add(crearPanelBoton(), BorderLayout.SOUTH);
		this.panelPrincipal.add(panelCambio1, BorderLayout.CENTER);

		if(this.estado){
			
			this.panelCambio1.setBackground(Color.gray.brighter().brighter().brighter());
		}else{
			this.panelCambio1.setBackground(Color.gray);
		}
		
		this.panelCambio1.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
					
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Ap¨¦ndice de m¨¦todo generado autom¨¢ticamente
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Ap¨¦ndice de m¨¦todo generado autom¨¢ticamente
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
				cambiarEstado();
				
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Ap¨¦ndice de m¨¦todo generado autom¨¢ticamente
				
			}
			
		});
		return panelPrincipal;
	}

	protected void cambiarEstado() {
		if(this.estado){
			if(palabras[0].equals("persiana")){
				this.vc.setComando("Bajar "+this.nombre);
			}else{
				this.vc.setComando("Apagar "+this.nombre);
			}
			
		}else{
			if(palabras[0].equals("persiana")){
				this.vc.setComando("Subir "+this.nombre);
			}else{
				this.vc.setComando("Encender "+this.nombre);
			}
		}
		
	}
	public void changeContent(int i){
		if(i ==1){
			this.panelPrincipal.remove(this.panelCambio1);
			this.panelPrincipal.add(this.panelCambio2, BorderLayout.CENTER);
		}else if(i == 2){
			this.panelPrincipal.remove(this.panelCambio2);
			this.panelPrincipal.add(this.panelCambio1, BorderLayout.CENTER);
			
		}
		
	}
	
	@Override
	public void update(Observable asc, Object objetoModificado) {
		if(!this.vc.getComando().equalsIgnoreCase("")){
			String[] porPalabras = this.vc.getComando().toLowerCase().split("[ ]");
			String nombrePorComando = porPalabras[1]+" "+porPalabras[2];
			if(nombrePorComando.equalsIgnoreCase(this.nombre)){
				String[] palabras2 = this.vc.getComando().toLowerCase().split("[ ]");
				if(this.estado){
					this.estado = false;
					botones.setBackground(Color.gray);
					this.panelCambio1.setBackground(Color.gray);
					if(palabras2[1].equals("persiana")){
						this.estado1.setText("Down");
						botonUp.setEnabled(true);
						botonDown.setEnabled(false);
						this.vc.setComando("");
					}else{
						this.estado1.setText("OFF");
						this.vc.setComando("");
					}
					
				}else{
					this.estado = true;
					botones.setBackground(Color.gray.brighter().brighter().brighter());
					this.panelCambio1.setBackground(Color.gray.brighter().brighter().brighter());
					if(palabras2[1].equals("persiana")){
						this.estado1.setText("UP");
						botonUp.setEnabled(false);
						botonDown.setEnabled(true);
						this.vc.setComando("");
					}else{
						this.estado1.setText("On");
						this.vc.setComando("");
					}
				}
			}
		}
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public double getCondsumo() {
		return consumo;
	}

	public void setCondsumo(double condsumo) {
		this.consumo = condsumo;
	}

	public String getNombre() {
		return nombre;
	}

	public boolean isEstado() {
		return estado;
	}

	public Image getImage() {
		return imagen.getImage();
	}
}

