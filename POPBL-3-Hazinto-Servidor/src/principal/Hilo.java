package principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author Eder Gomez de segura <eder.gomezp@alumni.mondragon.edu>
* @author Xabier Gandiaga <xabier.gandiaga@alumni.mondragon.edu>
* @author Alex Gainza <alex.gainza@alumni.mondragon.edu>
* @author Iñigo Ayestaran <inigo.ayestaran@alumni.mondragon.edu>
* @author Nikolay Zabaleta <nikolay.zabaleta@alumni.mondragon.edu>
*/

public class Hilo extends Thread{
    
    private Socket socket;
    private InputStream in;
    private ObjectInputStream is;
    private OutputStream os;
    private ObjectOutputStream out;
    private Vista vista;
    private TratamientoTXT tratadorTXT;
    
    
    public Hilo(Socket socket) throws IOException{
    	
    	tratadorTXT = new TratamientoTXT();
        this.socket = socket;
        this.in = this.socket.getInputStream();
        this.is = new ObjectInputStream(in);
        this.os = this.socket.getOutputStream();
        this.out = new ObjectOutputStream(os);
    }
    
    @Override
    public void run(){
        try {
            String comando = "";
            String log = "";
            
            String nombreCliente = (String) is.readObject();
            
            vista = new Vista(nombreCliente);
            
            do{
                System.out.println("ESPERANDO COMANDO");
                vista.setLog("ESPERANDO COMANDO");
              //  System.out.println(is.readUTF());
                comando = (String) is.readObject();
                
                System.out.println(comando);
                
                String fecha = this.saberFecha();
                String hora = this.saberHora();
                
                log = comando+"$"+hora+"$"+fecha;
                
                
                vista.setLog(comando+" / "+hora+" / "+fecha);
                
                tratadorTXT.guardar(log, "files/"+nombreCliente+".txt");
               
                System.out.println("COMANDO RECIBIDO");
                vista.setLog("COMANDO RECIBIDO");
            }while(!comando.equalsIgnoreCase("Salir"));
            this.stop();
        
        } catch(SocketException e){
            System.out.println("CONEXION PERDIDA");
            vista.setLog("CONEXION PERDIDA");
            this.stop();
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String saberFecha(){
    	Date date = new Date();
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    	return ""+dateFormat.format(date);
    }
    public String saberHora(){
    	Date date = new Date();
    	DateFormat hourFormat = new SimpleDateFormat("HH:mm");
    	return ""+hourFormat.format(date);
    }
}
