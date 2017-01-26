package principal;

import java.util.Observable;
import java.util.Observer;

import com.pi4j.io.gpio.*;

/**
*
* @author Eder Gomez de segura <eder.gomezp@alumni.mondragon.edu>
* @author Xabier Gandiaga <xabier.gandiaga@alumni.mondragon.edu>
* @author Alex Gainza <alex.gainza@alumni.mondragon.edu>
* @author Inigo Ayestaran <inigo.ayestaran@alumni.mondragon.edu>
* @author Nikolay Zabaleta <nikolay.zabaleta@alumni.mondragon.edu>
*/
public class ConexionBasys extends Thread implements Observer{
    Controlador c;
    VariablesComunes vc;
    GpioController gpio = GpioFactory.getInstance();
    GpioPinDigitalOutput pinBPersiana = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00,
			"bajarPersiana", PinState.LOW);
    GpioPinDigitalOutput pinSPersiana = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,
			"subirPersiana", PinState.LOW);
    GpioPinDigitalOutput pinELuz = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03,
			"encenderLuz", PinState.LOW);
    GpioPinDigitalOutput pinALuz = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,
			"apagarLuz", PinState.LOW);
    GpioPinDigitalOutput pinEAireComedor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05,
			"encenderAire", PinState.LOW);
    GpioPinDigitalOutput pinAAireComedor = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,
			"apagarAire", PinState.LOW);
    GpioPinDigitalOutput pinEAireCuarto = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07,
			"encendeAlarma", PinState.LOW);
    GpioPinDigitalOutput pinAAireCuarto = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06,
			"apagarAlarma", PinState.LOW);
    
    public void pinesACero(){
    	pinBPersiana.low();
    	pinSPersiana.low();
    	
    	pinELuz.low();
    	pinALuz.low();
    	pinEAireComedor.low();
    	pinAAireComedor.low();
    	pinEAireCuarto.low();
    	pinAAireCuarto.low();
    }
    
            
    public ConexionBasys(Controlador c, VariablesComunes vc){
        this.c = c;
        this.vc = vc;
        this.vc.addObserver(this);
        this.pinesACero();
        
        
    }
    
    @Override
    public void run(){}
    
    @Override
	public void update(Observable asc, Object objetoModificado) {
    	String variable = this.vc.getComando();
    	switch(variable){
        case "Bajar persiana cuarto":
            // aqui se reliazan llamada a hilo concexion basys

        	//pinBPersiana.setShutdownOptions(true, PinState.LOW);

        	pinBPersiana.high();
        	pinSPersiana.low();
        	pinELuz.low();
        	pinALuz.low();
        	pinEAireComedor.low();
        	pinAAireComedor.low();
        	pinEAireCuarto.low();
        	pinAAireCuarto.low();
        	
        	/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e3) {
				e3.printStackTrace();
			}*/
        	
        	this.pinesACero();
        	
        	/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e3) {
				e3.printStackTrace();
			}*/
            
            break;
        case "Subir persiana cuarto":

        	//pinSPersiana.setShutdownOptions(true, PinState.LOW);
			
        	//pinSPersiana.high();
        	//pinSPersiana.low();
        	
        	pinBPersiana.low();
        	pinSPersiana.high();
        	
        	pinELuz.low();
        	pinALuz.low();
        	pinEAireComedor.low();
        	pinAAireComedor.low();
        	pinEAireCuarto.low();
        	pinAAireCuarto.low();
        	
        	
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}*/
			//pinSPersiana.high();
			
			this.pinesACero();
			
		/*	try {
				Thread.sleep(5000);
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}*/
			
            break; 
        case "Encender luz comedor":

        	//pinELuz.setShutdownOptions(true, PinState.LOW);
			
        	
        	pinBPersiana.low();
        	pinSPersiana.low();
        	
        	pinELuz.high();
        	pinALuz.low();
        	pinEAireComedor.low();
        	pinAAireComedor.low();
        	pinEAireCuarto.low();
        	pinAAireCuarto.low();
        	
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}*/
		
        	this.pinesACero();
		
            break; 
        case "Apagar luz comedor":
        	

        	//pinALuz.setShutdownOptions(true, PinState.LOW);
			
        	pinBPersiana.low();
        	pinSPersiana.low();
        	
        	pinELuz.low();
        	pinALuz.high();
        	pinEAireComedor.low();
        	pinAAireComedor.low();
        	pinEAireCuarto.low();
        	pinAAireCuarto.low();
        	
        	
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			
			this.pinesACero();
        	
            break;
        case "Encender aire comedor":
        	

        	//pinEAire.setShutdownOptions(true, PinState.LOW);
			
        	pinBPersiana.low();
        	pinSPersiana.low();
        	
        	pinELuz.low();
        	pinALuz.low();
        	pinEAireComedor.high();
        	pinAAireComedor.low();
        	pinEAireCuarto.low();
        	pinAAireCuarto.low();
        	
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			
			this.pinesACero();
			
            break; 
        case "Apagar aire comedor":
        	

        	//pinAAire.setShutdownOptions(true, PinState.LOW);
			
        	pinBPersiana.low();
        	pinSPersiana.low();
        	
        	pinELuz.low();
        	pinALuz.low();
        	pinEAireComedor.low();
        	pinAAireComedor.high();
        	pinEAireCuarto.low();
        	pinAAireCuarto.low();
        	
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			
			this.pinesACero();
            break; 
        case "Encender aire cuarto":
        	

        	//pinEAlarma.setShutdownOptions(true, PinState.LOW);
			
        	
        	pinBPersiana.low();
        	pinSPersiana.low();
        	
        	pinELuz.low();
        	pinALuz.low();
        	pinEAireComedor.low();
        	pinAAireComedor.low();
        	pinEAireCuarto.high();
        	pinAAireCuarto.low();
        	
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			
			this.pinesACero();
        	
            break; 
        case "Apagar aire cuarto":
        	

        	//pinAAireCuarto.setShutdownOptions(true, PinState.LOW);
			
        	pinBPersiana.low();
        	pinSPersiana.low();
        	
        	pinELuz.low();
        	pinALuz.low();
        	pinEAireComedor.low();
        	pinAAireComedor.low();
        	pinEAireCuarto.low();
        	pinAAireCuarto.high();
        	
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
        	
        	this.pinesACero();
        	
			//pinAAireCuarto.low();
            break;
		}
    	 gpio.shutdown();
    }
}
