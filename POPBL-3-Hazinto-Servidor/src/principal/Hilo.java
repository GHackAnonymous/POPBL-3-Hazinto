package Principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
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
    
    
    public Hilo(Socket socket) throws IOException{
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
            do{
                System.out.println("ESPERANDO COMANDO");
              //  System.out.println(is.readUTF());
                comando = (String) is.readObject();
                System.out.println(comando);
                System.out.println("COMANDO RECIBIDO");
            }while(!comando.equalsIgnoreCase("Salir"));
            this.stop();
        
        } catch(SocketException e){
            System.out.println("CONEXION PERDIDA");
            this.stop();
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
