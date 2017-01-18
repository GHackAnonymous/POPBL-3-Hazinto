package principal;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaLog extends AbstractTableModel {
	
	ModeloColumnasTablaLog columnas;
	Controlador control;
	List<Log> listaLog;
	
	public ModeloTablaLog(ModeloColumnasTablaLog columnas, Controlador control){
		super();
		this.control = control;
		this.columnas = columnas;
		
	}
	public List<Log> leerTablaFichero(String path) {
		listaLog = control.leerTXT(path);
		return listaLog;
	}

	@Override
	public int getColumnCount() {
		
		return columnas.getColumnCount();
	}

	@Override
	public int getRowCount() {
		
		return listaLog.size();
	}

	@Override
	public Object getValueAt(int fila, int columna) {
		Log log = listaLog.get(fila);
		return log.getFieldAt(columna);
		
	}
		
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return getValueAt(0,columnIndex).getClass();
	}

}