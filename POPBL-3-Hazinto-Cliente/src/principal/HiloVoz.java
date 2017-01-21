package principal;

/**
*
* @author Eder Gomez de segura <eder.gomezp@alumni.mondragon.edu>
* @author Xabier Gandiaga <xabier.gandiaga@alumni.mondragon.edu>
* @author Alex Gainza <alex.gainza@alumni.mondragon.edu>
* @author Iñigo Ayestaran <inigo.ayestaran@alumni.mondragon.edu>
* @author Nikolay Zabaleta <nikolay.zabaleta@alumni.mondragon.edu>
*/
public class HiloVoz extends Thread{
    Controlador c;
    VariablesComunes vc;
            
    public HiloVoz(Controlador c, VariablesComunes vc){
        this.c = c;
        this.vc = vc;
        Voz voz = new Voz(this, vc);
       // Menu menu = new Menu(this.vc);
    }
}
