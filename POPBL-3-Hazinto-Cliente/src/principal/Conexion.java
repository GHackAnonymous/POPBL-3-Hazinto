package principal;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

/**
*
* @author Eder Gomez de segura <eder.gomezp@alumni.mondragon.edu>
* @author Xabier Gandiaga <xabier.gandiaga@alumni.mondragon.edu>
* @author Alex Gainza <alex.gainza@alumni.mondragon.edu>
* @author Iñigo Ayestaran <inigo.ayestaran@alumni.mondragon.edu>
* @author Nikolay Zabaleta <nikolay.zabaleta@alumni.mondragon.edu>
*/
public class Conexion extends Thread{
    
    private Socket socket;
    private InputStream in;
    private ObjectInputStream is;
    private OutputStream os;
    private ObjectOutputStream out;
    private Controlador c;
    VariablesComunes vc;
    
    public Conexion(Controlador c, VariablesComunes vc){
        this.c = c;
        this.vc = vc;
    }

    public Conexion(VariablesComunes vc) {
        this.vc = vc;
    }
    
    @Override
    public void run(){
        try{
            try {
            	System.out.println("Relaizando conexion");
                 socket = new Socket("172.17.29.121",5500);

                 out = new ObjectOutputStream(socket.getOutputStream());
                 in = new ObjectInputStream(socket.getInputStream());
                 
                 System.out.println("conexion realizada");
                 do{
                    //sleep(10000);
                    if(!this.vc.getComando().equalsIgnoreCase("")){
                        String comand = this.vc.getComando();
                       //out.writeUTF("aaa");
                        out.writeObject(this.vc.getComando());
                        this.vc.setComando("");
                    }
                }while(!this.vc.getComando().equalsIgnoreCase("Salir"));
                 

            } catch (IOException e) {e.printStackTrace();}

           // int turno = (int) in.readObject();

            out.close();
            in.close();
            socket.close();

        } catch (Exception ex){
            try {
                out.close();
                in.close();
                socket.close();
            } catch (IOException e) {e.printStackTrace();}
        }
    }
}
