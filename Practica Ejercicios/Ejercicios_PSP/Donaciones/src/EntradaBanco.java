

import java.util.Scanner;

import controller.CuentaBanco;

public class EntradaBanco {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CuentaBanco cuentaBanco=new CuentaBanco();
		boolean salir = false;

		do {
			System.out.println("""
					Bienvenido al servicio de donaciones de la universidad,
					usted puede:
					1. Aleatorizar las donaciones
					2. Registrar donaciones
					""");
			int op = scanner.nextInt();
			switch (op) {
			case 1:
				System.out.println("Ha elegido realizar donaciones de forma aleatoria");
				cuentaBanco.donacionesRandom();
				salir=true;
				break;
			case 2:
				System.out.println("¿De que cantidad es la donación?");
				int dineroDonado=scanner.nextInt();
				cuentaBanco.nuevaDonacion(dineroDonado);
				System.out.println("""
						¿Desea realizar más donaciones?
						1. Sí
						2. No
						""");
				int opSalir=scanner.nextInt();
				if(opSalir == 2) salir=true;
				break;

			default:
				System.out.println("Opción no valida");
				break;
			}

		} while (!salir);
		
		cuentaBanco.run();

	}

}
