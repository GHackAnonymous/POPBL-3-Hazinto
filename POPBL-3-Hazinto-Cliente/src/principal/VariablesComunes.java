package principal;

import java.util.Observable;

/**
*
* @author Eder Gomez de segura <eder.gomezp@alumni.mondragon.edu>
* @author Xabier Gandiaga <xabier.gandiaga@alumni.mondragon.edu>
* @author Alex Gainza <alex.gainza@alumni.mondragon.edu>
* @author I�igo Ayestaran <inigo.ayestaran@alumni.mondragon.edu>
* @author Nikolay Zabaleta <nikolay.zabaleta@alumni.mondragon.edu>
*/
public class VariablesComunes extends Observable {
    private volatile String comando = "";

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        
        synchronized(this) {  
            this.comando = comando; 
            if(!this.comando.equalsIgnoreCase("")){
	            this.setChanged();
				this.notifyObservers(this);
            }
        }
    }
}
