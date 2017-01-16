package principal;

public class Log {
	private String comando = "";
	private String fecha = null;
	private String hora = null;
	private final int NUMCAMPOS = 3;
	
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
}
