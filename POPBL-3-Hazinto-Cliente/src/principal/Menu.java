package principal;

import java.util.Scanner;

public class Menu {
	
	 VariablesComunes vc;
	
	public Menu(VariablesComunes vc){
		
		this.vc = vc;
		
		Scanner sc = new Scanner(System.in);
		int opcionElegida;

		do {
			System.out.println("1.- Abrir ventanas");
			System.out.println("2.- Encender Luz");
			System.out.print("Opcion ->");

			opcionElegida = sc.nextInt();

			switch (opcionElegida) {
			case 1:
				 System.out.println("Abriendo ventanas...");
                 
                 this.vc.setComando("Abriendo ventanas...");
				break;
			case 2:
				 System.out.println("Encendiendo luces...");
                 
                 this.vc.setComando("Encendiendo luces...");
				break;
			}

		} while (opcionElegida != 3);
	}
}
