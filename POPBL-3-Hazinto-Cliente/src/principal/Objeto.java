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
	JPanel panelPrincipal, panelObjetoFrontal, panelBoton, panelObjetoTrasero;
	JButton boton;
	String [] palabras;
	VariablesComunes vc;
	JButton botonUp, botonDown;
	String comandoAnterior = "";

	public Objeto(String nombre, boolean estado, double consumo, String imagen, 
			VariablesComunes vc){
		super(new BorderLayout());
		this.vc = vc;
		this.vc.addObserver(this);
		this.nombre = nombre;
		this.estado = estado;
		this.consumo = consumo;
		this.imagen = new ImageIcon(imagen);
		botones = new JPanel(new BorderLayout());
		panelObjetoTrasero = new JPanel(new BorderLayout(0,6));
		panelPrincipal = new JPanel(new BorderLayout(0,6));
		palabras = separadorPalabras(nombre);
		this.panelPersiana();
	}
	private String[] separadorPalabras(String frase){
		return frase.toLowerCase().split("[ ]");
	}
	private void panelPersiana(){
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
					panelObjetoFrontal.setEnabled(false);
					panelObjetoTrasero.setEnabled(true);
				}else{
					boton.setText(nombre);
					changeContent(2);
					panelObjetoFrontal.setEnabled(true);
					panelObjetoTrasero.setEnabled(false);
				}
			}
			
		});
		panelBoton.add(boton, BorderLayout.SOUTH);
		return panelBoton;
		
	}
	public void botonUp(){
		botonUp.setEnabled(true);
		botonDown.setEnabled(false);
	} 
	public void botonDown(){
		botonUp.setEnabled(false);
		botonDown.setEnabled(true);
	} 
	public JPanel crearPanelPersiana(){
		JLabel nombre, consumo, imagen;
		panelObjetoFrontal = new JPanel(new BorderLayout());
		
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
				botonDown();
			}
			
		});
		botonDown.setEnabled(false);
		botonDown.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cambiarEstado();
				botonUp();
			}
			
		});
		botones.add(botonUp, BorderLayout.NORTH);
		botones.add(botonDown, BorderLayout.SOUTH);
		this.panelObjetoFrontal.add(imagen, BorderLayout.CENTER);
		this.panelObjetoFrontal.add(botones, BorderLayout.WEST);
		this.panelObjetoTrasero.add(nombre, BorderLayout.NORTH);
		this.panelObjetoTrasero.add(consumo, BorderLayout.SOUTH);
		this.panelObjetoTrasero.add(estado1, BorderLayout.CENTER);
		/*this.panelCambio1.setBorder(BorderFactory.createLineBorder(Color.black));
		this.panelCambio2.setBorder(BorderFactory.createLineBorder(Color.black));*/
		this.panelPrincipal.add(crearPanelBoton(), BorderLayout.SOUTH);
		this.panelPrincipal.add(panelObjetoFrontal, BorderLayout.CENTER);

		if(this.estado){
			botones.setBackground(Color.gray.brighter().brighter().brighter());
			this.panelObjetoFrontal.setBackground(Color.gray.brighter().brighter().brighter());
		}else{
			botones.setBackground(Color.gray);
			this.panelObjetoFrontal.setBackground(Color.gray);
		}
		
		return panelPrincipal;
	}
	public JPanel crearPanelNormal(){
		JLabel nombre, consumo, imagen;
		panelObjetoFrontal = new JPanel();
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
		this.panelObjetoFrontal.add(imagen);
		this.panelObjetoTrasero.add(nombre, BorderLayout.NORTH);
		this.panelObjetoTrasero.add(consumo, BorderLayout.SOUTH);
		this.panelObjetoTrasero.add(estado1, BorderLayout.CENTER);
		/*this.panelCambio1.setBorder(BorderFactory.createLineBorder(Color.black));
		this.panelCambio2.setBorder(BorderFactory.createLineBorder(Color.black));*/
		this.panelPrincipal.add(crearPanelBoton(), BorderLayout.SOUTH);
		this.panelPrincipal.add(panelObjetoFrontal, BorderLayout.CENTER);

		if(this.estado){
			this.panelObjetoFrontal.setBackground(Color.gray.brighter().brighter().brighter());
		}else{
			this.panelObjetoFrontal.setBackground(Color.gray);
		}
		
		this.panelObjetoFrontal.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {
				cambiarEstado();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
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
			this.panelPrincipal.remove(this.panelObjetoFrontal);
			this.panelPrincipal.add(this.panelObjetoTrasero, BorderLayout.CENTER);
		}else if(i == 2){
			this.panelPrincipal.remove(this.panelObjetoTrasero);
			this.panelPrincipal.add(this.panelObjetoFrontal, BorderLayout.CENTER);
			
		}
	}
	
	@Override
	public void update(Observable asc, Object objetoModificado) {
		if(!this.vc.getComando().equalsIgnoreCase("")){
			
			String[] porPalabras = separadorPalabras(this.vc.getComando());
			String nombrePorComando = porPalabras[1]+" "+porPalabras[2];
			
			if(nombrePorComando.equalsIgnoreCase(this.nombre)){
				if(this.estado){
					
					
					String comando = porPalabras[0] +" "+porPalabras[1];
					//if(comando.equalsIgnoreCase("Bajar persiana")){
					if(!comandoAnterior.equalsIgnoreCase("Subir persiana")){
						this.estado = false;
						
						botonUp();
						
						this.estado1.setText("Down");
						
						botones.setBackground(Color.gray);
						this.panelObjetoFrontal.setBackground(Color.gray);
						
						this.vc.setComando("");
					}else if(porPalabras[0].equalsIgnoreCase("Apagar")){
						this.estado = false;
						botones.setBackground(Color.gray);
						this.panelObjetoFrontal.setBackground(Color.gray);
						
						this.estado1.setText("OFF");
						this.vc.setComando("");
					}
					comandoAnterior = porPalabras[0]+" "+porPalabras[1];
				}else{
					
					//botones.setBackground(Color.gray.brighter().brighter().brighter());
					
					//this.panelObjetoFrontal.setBackground(
					//		Color.gray.brighter().brighter().brighter());
					String comando = porPalabras[0] +" "+porPalabras[1];
					if(!comandoAnterior.equalsIgnoreCase("Bajar persiana")){
						this.estado = true;
					//if(comando.equalsIgnoreCase("Subir persiana")){
						
						botonDown();
						this.estado1.setText("UP");
						this.vc.setComando("");
						
						botones.setBackground(Color.gray.brighter().brighter().brighter());
						
						this.panelObjetoFrontal.setBackground(
								Color.gray.brighter().brighter().brighter());
					}else if(porPalabras[0].equalsIgnoreCase("Encender")){
						this.estado = true;
						botones.setBackground(Color.gray.brighter().brighter().brighter());
						
						this.panelObjetoFrontal.setBackground(
								Color.gray.brighter().brighter().brighter());
						this.estado1.setText("On");
						this.vc.setComando("");
					}
				}
				comandoAnterior = porPalabras[0]+" "+porPalabras[1];
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

