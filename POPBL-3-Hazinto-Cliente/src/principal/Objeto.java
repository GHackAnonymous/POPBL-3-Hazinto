package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class Objeto extends JPanel{
	String nombre;
	boolean estado;
	double consumo;
	ImageIcon imagen;
	JLabel estado1;
	JPanel panelPrincipal, panelCambio1, panelBoton, panelCambio2;
	JButton boton;
	
	public Objeto(String nombre, boolean estado, double consumo, String imagen){
		super(new BorderLayout());
		this.nombre = nombre;
		this.estado = estado;
		this.consumo = consumo;
		this.imagen = new ImageIcon(imagen);
		panelCambio1 = new JPanel(new BorderLayout(0,6));
		panelCambio2 = new JPanel(new BorderLayout(0,6));
		panelPrincipal = new JPanel(new BorderLayout(0,6));
		this.add(crearPanel(), BorderLayout.CENTER);
	}
	public JPanel crearPanelBoton(){
		panelBoton = new JPanel(new BorderLayout());
		boton = new JButton("Infor");
		boton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(boton.getText().equals("Infor")){
					boton.setText("Back");
					changeContent(1);
					panelCambio1.setEnabled(false);
					panelCambio2.setEnabled(true);
				}else{
					boton.setText("Infor");
					changeContent(2);
					panelCambio1.setEnabled(true);
					panelCambio2.setEnabled(false);
				}
				
				
			}
			
		});
		panelBoton.add(boton, BorderLayout.SOUTH);
		return panelBoton;
		
	}
	public JPanel crearPanel(){
		JLabel nombre, consumo, imagen;
		nombre = new JLabel();
		nombre.setText(this.nombre);
		Font fuente = new Font("Monospaced", Font.BOLD, 20);
        nombre.setFont(fuente);
		consumo = new JLabel("Consumo: "+String.valueOf(this.consumo));
		consumo.setFont(fuente);
		estado1 = new JLabel(this.estado ?"ON" : "OFF");
		imagen = new JLabel();
		imagen.setIcon(this.imagen);
		this.panelCambio1.add(imagen, BorderLayout.CENTER);
		this.panelCambio2.add(nombre, BorderLayout.NORTH);
		this.panelCambio2.add(consumo, BorderLayout.SOUTH);
		this.panelCambio2.add(estado1, BorderLayout.CENTER);
		this.panelCambio1.setBorder(BorderFactory.createLineBorder(Color.black));
		this.panelCambio2.setBorder(BorderFactory.createLineBorder(Color.black));
		this.panelPrincipal.add(crearPanelBoton(), BorderLayout.SOUTH);
		this.panelPrincipal.add(panelCambio1, BorderLayout.CENTER);

		if(this.estado){
			this.panelCambio1.setBackground(Color.gray.brighter().brighter().brighter());
		}else{
			this.panelCambio1.setBackground(Color.gray.brighter());
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
			this.estado = false;
			this.panelCambio1.setBackground(Color.gray.brighter());
			this.estado1.setText("OFF");
		}else{
			this.estado = true;
			this.panelCambio1.setBackground(Color.gray.brighter().brighter().brighter());
			this.estado1.setText("ON");
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