package principal;

import java.util.Scanner;

/**
*
* @author Eder Gomez de segura <eder.gomezp@alumni.mondragon.edu>
* @author Xabier Gandiaga <xabier.gandiaga@alumni.mondragon.edu>
* @author Alex Gainza <alex.gainza@alumni.mondragon.edu>
* @author Iñigo Ayestaran <inigo.ayestaran@alumni.mondragon.edu>
* @author Nikolay Zabaleta <nikolay.zabaleta@alumni.mondragon.edu>
*/
public class Principal {

    /**
     * IMPORTANTE SIEMPRE QUE ESTE EJECUTANDO EJERCICIOS SOCKET 
     * EN EL ORDENADOR TIENES QUE TENER EL ANTIVIRUS "AVAST" DECONECTADO
     * Y EL FIREWALL DESACTIVADO
     */
    public static void main(String[] args) {
        
        VariablesComunes vc = new VariablesComunes();

        Controlador c = new Controlador(vc);
        /*Conexion c = new Conexion(vc);
        c.start();
        Scanner sc = new Scanner(System.in);
        int opcionElegida;*/
                 
        /*do{
            System.out.println("1.- Abrir ventanas");
            System.out.println("2.- Encender Luz");
            System.out.println("3.- Salir");
            System.out.print("Opcion ->");

            opcionElegida =  sc.nextInt();

            switch(opcionElegida){
                case 1:
                    vc.setComando("Abriendo ventanas ...");
                    break;
                case 2:
                    vc.setComando("Encendiendo Luces ...");
                    break;
                case 3:
                    vc.setComando("Salir");
                    c.stop();
                    c = null;
                    break;
            }
            
            
        }while(opcionElegida != 3);*/
        
    }
}
