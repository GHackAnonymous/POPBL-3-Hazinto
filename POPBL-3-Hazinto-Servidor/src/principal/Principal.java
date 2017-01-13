package principal;

import com.sun.corba.se.spi.activation.Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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

public class Principal {

	/*
	 * IMPORTANTE SIEMPRE QUE ESTE EJECUTANDO EJERCICIOS SOCKET EN EL ORDENADOR
	 * TIENES QUE TENER EL ANTIVIRUS "AVAST" DECONECTADO Y EL FIREWALL
	 * DESACTIVADO
	 */

	public static void main(String[] args) {
		ServerSocket server = null;
		Socket cliente = null;

		try {
			server = new ServerSocket(5500);

			while (true) {
				System.out.println("ESPERANDO CLIENTE");
				Socket c = (Socket) server.accept();

				Hilo hilo = new Hilo(c);
				hilo.start();
			}

		} catch (IOException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
