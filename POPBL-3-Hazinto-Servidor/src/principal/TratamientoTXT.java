package principal;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TratamientoTXT {
	
	private List<String> leer(String path){
		BufferedReader in  = null;
		List<String> logs = new ArrayList<>();
		try {
			in = new BufferedReader(new FileReader(path));
			String s;
			while ((s = in.readLine())!=null){
				
				logs.add(s);
			}
			return logs;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (EOFException e){
			
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (in!= null){
				try { in.close(); } catch (IOException e) {}
			}
		}
		return null;
	}
	
	public void guardar(String log,String path) {
		
		
		//primero leer  el fichero para que no sobrescriba
		List<String> contenidoDelTXT = this.leer(path);
		
		try (PrintWriter out = new PrintWriter(new FileWriter (path))){
			
			for (int i = 0;i< contenidoDelTXT.size(); i++){
				out.println( contenidoDelTXT.get(i));
			}
			
			out.println(log);
			
		} catch (IOException e) {e.printStackTrace();
		}
	}

}
