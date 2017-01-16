package principal;

import java.util.List;

public class Controlador {
	
	private TratamientoTXT tratarTXT = new TratamientoTXT();
	
	public List<Log> leerTXT(String path){
		return tratarTXT.leer(path);
	}

}
