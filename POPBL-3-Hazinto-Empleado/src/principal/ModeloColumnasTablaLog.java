package principal;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

public class ModeloColumnasTablaLog extends DefaultTableColumnModel{
	
	TrazadorTablaLog trazador;
	
	public ModeloColumnasTablaLog(TrazadorTablaLog trazador){
		super();
		this.trazador = trazador;
		this.addColumn(crearColumna("Comando",0,400));
		this.addColumn(crearColumna("Hora",1,20));
		this.addColumn(crearColumna("Fecha",2,100));
	}

	private TableColumn crearColumna(String texto, int indice, int ancho) {
		TableColumn columna = new TableColumn(indice,ancho);

		columna.setHeaderValue(texto);
		columna.setPreferredWidth(ancho);
		columna.setCellRenderer(trazador);
		
		return columna;
	}

}
