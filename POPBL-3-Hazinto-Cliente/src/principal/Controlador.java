package principal;

/**
*
* @author Eder Gomez de segura <eder.gomezp@alumni.mondragon.edu>
* @author Xabier Gandiaga <xabier.gandiaga@alumni.mondragon.edu>
* @author Alex Gainza <alex.gainza@alumni.mondragon.edu>
* @author Iñigo Ayestaran <inigo.ayestaran@alumni.mondragon.edu>
* @author Nikolay Zabaleta <nikolay.zabaleta@alumni.mondragon.edu>
*/
public class Controlador {
    
    VariablesComunes vc;
    Conexion conexion;
    ConexionBasys conexionBesys;
    HiloVoz hiloVoz;
    Graficos graficos;
    
    final String NOMBRECLIENTE = "Cliente1";
    
    public Controlador(VariablesComunes vc){
        this.vc = vc;
        conexion = new Conexion(this, this.vc,NOMBRECLIENTE);
        //conexionBesys = new ConexionBasys(this, this.vc);
        conexion.start();
        hiloVoz = new HiloVoz(this, this.vc);
        graficos = new  Graficos(this, this.vc);
        
    }
}
