package principal;

public class Log {
	private String comando = "";
	private String fecha = null;
	private String hora = null;
	private final int NUMCAMPOS = 3;
	
	public Log(String comando, String hora, String fecha) {
		this.comando = comando;
		this.fecha = fecha;
		this.hora = hora;
	}
	public String getComando() {
		return comando;
	}
	public void setComando(String comando) {
		this.comando = comando;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String [] toArray() {
		String [] datos = new String [NUMCAMPOS];
		datos[0] = comando;
		datos[1] = hora;
		datos[2] = fecha;
		
		return datos;
	}
	
	public Class<?> getFieldClass(int indice){
		switch (indice){
		default: return String.class; 
		}
		
	}

	public Object getFieldAt(int columna) {
		switch (columna){
		case 0: return comando;
		case 1: return hora;
		case 2: return fecha;
		default: return null; 
		}
	}
}
