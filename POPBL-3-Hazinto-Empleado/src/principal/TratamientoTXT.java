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
	public List<Log> leer(String path){
		BufferedReader in  = null;
		List<Log> logs = new ArrayList<>();
		try {
			in = new BufferedReader(new FileReader(path));
			String s;
			String[] linea = null;
			while ((s = in.readLine())!=null){
				Log log = new Log();
				linea = s.split("[$]");
				log.setComando(linea[0]);
				log.setHora(linea[1]);
				log.setFecha(linea[2]);
				logs.add(log);
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

	public void guardar(List<String> lista,String path) {
		try (PrintWriter out = new PrintWriter(new FileWriter (path))){
			
			for (int i = 0;i< lista.size(); i++){
				out.println( lista.get(i));
			}
			
		} catch (IOException e) {e.printStackTrace();
		}
	}

}
