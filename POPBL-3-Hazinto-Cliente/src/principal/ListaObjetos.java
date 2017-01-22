package principal;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;

public class ListaObjetos extends DefaultListModel<Objeto>{
	final String nombreFichero = "files/Conf/Config.txt";
	
	int maxObjetos;
	VariablesComunes vc;

	public ListaObjetos(VariablesComunes vc){
		this.maxObjetos = this.leerNumPeliculas();
		this.vc = vc;
	}
	public int leerNumPeliculas() {
		int contador = 0;
		BufferedReader in = null;
		String linea = null;
		int indice = 1;
		this.removeAllElements();
		try {
			in = new BufferedReader(new FileReader(nombreFichero));
			
			while (((linea = in.readLine())!=null)){
				
				contador++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if (in!=null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return contador;
	}
	
	void cargarConfigDeFichero(){
		//dependiendo del fichero de configuracion
		String linea ;
		int indice = 0;
		this.removeAllElements();
		try (BufferedReader in = new BufferedReader (new FileReader(nombreFichero))) { //buffered reader trabajar con las lineas
			
			while (/*((linea = in.readLine())!=null) ||*/ (indice <=3)){
				linea = in.readLine();
					String [] palabras = linea.toLowerCase().split("[$]");//coger string y dividirlo como tu se lo digas
					Objeto obj1 = new Objeto(palabras[0], false,  Double.valueOf(palabras[1]), palabras[2], this.vc);

					this.addElement(obj1);
				
				indice++;
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	public int getMaxObjetos() {
		return this.maxObjetos;
	}
}
