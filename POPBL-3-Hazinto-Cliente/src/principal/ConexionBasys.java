package principal;

import com.pi4j.io.gpio.*;

/**
*
* @author Eder Gomez de segura <eder.gomezp@alumni.mondragon.edu>
* @author Xabier Gandiaga <xabier.gandiaga@alumni.mondragon.edu>
* @author Alex Gainza <alex.gainza@alumni.mondragon.edu>
* @author Iñigo Ayestaran <inigo.ayestaran@alumni.mondragon.edu>
* @author Nikolay Zabaleta <nikolay.zabaleta@alumni.mondragon.edu>
*/
public class ConexionBasys extends Thread{
    Controlador c;
    VariablesComunes vc;
    GpioController gpio = GpioFactory.getInstance();
    
            
    public ConexionBasys(Controlador c, VariablesComunes vc){
        this.c = c;
        this.vc = vc;
    }
    
    @Override
    public void run(){
    	
        // aqui es donde relizamos la  conexion con la basys 
    	
    	
    	switch(this.vc.getComando()){
        case "Bajar Persiana Cuarto":
            // aqui se reliazan llamada a hilo concexion basys
        	
        	GpioPinDigitalOutput pinBPersiana = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00,
        									"bajarPersiana", PinState.HIGH);
        	
        	pinBPersiana.setShutdownOptions(true, PinState.LOW);
        	
        	try {
				Thread.sleep(500);
			} catch (InterruptedException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
        	
        	pinBPersiana.low();
            
            break;
        case "Subir Persiana Cuarto":
        	GpioPinDigitalOutput pinSPersiana = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,
					"subirPersiana", PinState.HIGH);

        	pinSPersiana.setShutdownOptions(true, PinState.LOW);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			pinSPersiana.low();
            break; 
        case "Encender Luz Comedor":
        	GpioPinDigitalOutput pinELuz = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,
					"encenderLuz", PinState.HIGH);

        	pinELuz.setShutdownOptions(true, PinState.LOW);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			pinELuz.low();
            break; 
        case "Apagar Luz Comedor":
        	GpioPinDigitalOutput pinALuz = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03,
					"apagarLuz", PinState.HIGH);

        	pinALuz.setShutdownOptions(true, PinState.LOW);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pinALuz.low();
            break;
        case "Encender Aire Comedor":
        	GpioPinDigitalOutput pinEAire = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,
					"encenderAire", PinState.HIGH);

        	pinEAire.setShutdownOptions(true, PinState.LOW);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pinEAire.low();
            break; 
        case "Apagar Aire Comedor":
        	GpioPinDigitalOutput pinAAire = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05,
					"apagarAire", PinState.HIGH);

        	pinAAire.setShutdownOptions(true, PinState.LOW);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pinAAire.low();
            break; 
        case "Encender Aire Cuarto":
        	GpioPinDigitalOutput pinEAlarma = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06,
					"encendeAlarma", PinState.HIGH);

        	pinEAlarma.setShutdownOptions(true, PinState.LOW);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pinEAlarma.low();
            break; 
        case "Apagar Aire Cuarto":
        	GpioPinDigitalOutput pinAAlarma = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07,
					"apagarAlarma", PinState.HIGH);

        	pinAAlarma.setShutdownOptions(true, PinState.LOW);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pinAAlarma.low();
            break; 
     }
    	
    }
}
