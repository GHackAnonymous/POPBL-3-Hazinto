package principal;

/**
*
* @author Eder Gomez de segura <eder.gomezp@alumni.mondragon.edu>
* @author Xabier Gandiaga <xabier.gandiaga@alumni.mondragon.edu>
* @author Alex Gainza <alex.gainza@alumni.mondragon.edu>
* @author Iñigo Ayestaran <inigo.ayestaran@alumni.mondragon.edu>
* @author Nikolay Zabaleta <nikolay.zabaleta@alumni.mondragon.edu>
*/
public class VariablesComunes {
    private volatile String comando = "";

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        
        synchronized(this) {  
            this.comando = comando; 
        }  

       
    }
}
