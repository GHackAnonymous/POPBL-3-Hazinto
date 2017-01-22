package principal;

import javax.speech.*;
import javax.speech.recognition.*;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
*
* @author Eder Gomez de segura <eder.gomezp@alumni.mondragon.edu>
* @author Xabier Gandiaga <xabier.gandiaga@alumni.mondragon.edu>
* @author Alex Gainza <alex.gainza@alumni.mondragon.edu>
* @author Iñigo Ayestaran <inigo.ayestaran@alumni.mondragon.edu>
* @author Nikolay Zabaleta <nikolay.zabaleta@alumni.mondragon.edu>
*/
public class Voz extends ResultAdapter {

    HiloVoz hiloVoz;
    Recognizer recognizer;
    String gst;
    boolean activo = false;
    VariablesComunes vc;
 
    @Override
    public void resultAccepted(ResultEvent re){
       try{

           Result res = (Result)(re.getSource());

            ResultToken tokensArray[] = res.getBestTokens();
            
            List<ResultToken> tokens =  Arrays.asList(tokensArray);

          for (int i=0; i < tokens.size(); i++){
            gst = tokens.get(i).getSpokenText();
            if(tokens.size() == 1){
                if(gst.equalsIgnoreCase("Jacinto")){
                	System.out.println(gst);
                    activo = true;
                }/*else if(gst.equalsIgnoreCase("cerrar")){
                   activo = false;
                }*/
            }else if(tokens.size() == 3){
            	System.out.println(gst);
                if(activo == true){
                    switch(""+tokens.get(0).getSpokenText()+" "+tokens.get(1).getSpokenText()+" "+tokens.get(2).getSpokenText()){
                        case "Bajar Persiana Cuarto":
                            System.out.println("Bajando Persiana Cuarto");
                            
                            this.vc.setComando("Bajando Persiana Cuarto");

                            activo = false;
                            break;
                        case "Subir Persiana Cuarto":
                            System.out.println("Subiendo Persiana cuarto");
                            
                            this.vc.setComando("Subiendo Persiana Cuarto");

                            activo = false;
                            break;
                        case "Encender Luz Comedor":
                        	System.out.println(gst);
                            System.out.println("Encendiendo Luz Comedor");
                            
                            this.vc.setComando("Encendiendo Luz Comedor");

                            activo = false;
                            break;
                        case "Apagar Luz Comedor":
                        	System.out.println(gst);
                            System.out.println("Apagando Luz Comedor");
                            
                            this.vc.setComando("Apagando Luz Comedor");

                            activo = false;
                            break;
                        case "Encender Aire Comedor":
                        	System.out.println(gst);
                            System.out.println("Encendiendo Aire Comedor");
                            
                            this.vc.setComando("Encendiendo Aire Comedor");

                            activo = false;
                            break; 
                        case "Apagar Aire Comedor":
                        	System.out.println(gst);
                            System.out.println("Apagando Aire Comedor");
                            
                            this.vc.setComando("Apagando Aire Comedor");

                            activo = false;
                            break; 
                        case "Encender Aire Cuarto":
                        	System.out.println(gst);
                            System.out.println("Encendiendo Aire Cuarto");
                            
                            this.vc.setComando("Encendiendo Aire Cuarto");

                            activo = false;
                            break; 
                        case "Apagar Aire Cuarto":
                        	System.out.println(gst);
                            System.out.println("Apagando Aire Cuarto");
                            
                            this.vc.setComando("Apagando Aire Cuarto");

                            activo = false;
                            break; 
                     }
                }
            }else if(tokens.size() == 1){
                if(activo == true){
                	if(gst.equals("Salir")){
                        recognizer.deallocate();
                        System.out.println("Saliendo....");
                        System.exit(0);
                    }else{
                       recognizer.suspend();
                       recognizer.resume();
                    }
                }
            }
          }
          	recognizer.suspend();
          	recognizer.resume();
       }catch(Exception ex){
           System.out.println("Ha ocurrido algo inesperado " + ex);
       }
    }
 
    public Voz(HiloVoz hiloVoz, VariablesComunes vc){
        this.hiloVoz = hiloVoz;
        this.vc = vc;
       try{
           recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ROOT));
           recognizer.allocate();

           FileReader grammar1 =new FileReader("files/gramatica/gramticaFile.txt");

           RuleGrammar rg = recognizer.loadJSGF(grammar1);
           rg.setEnabled(true);

           recognizer.addResultListener(this);

           //System.out.println("Empieze Dictado");
           recognizer.commitChanges();

           recognizer.requestFocus();
           recognizer.resume();
       }catch (Exception e){
           e.printStackTrace();
       }
   }

}
