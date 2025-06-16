import controller.Carrera;
import model.Galgo;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Carrera carrera=new Carrera();
        boolean salir=false;

        do{
            System.out.println("""
                Bienvenido a la carrera de galgos, por favor, elija una de estas opciones:
                1. Registrar un competidor
                2. Elegir el tiempo de la carrera
                3. Dar comienzo a la carrera
                4. Listar galgos
                5. Salir""");
            int opcion= scanner.nextInt();
            switch (opcion){
                case 1:
                    scanner=new Scanner(System.in);
                    System.out.println("Indica el nombre del galgo");
                    String nombre= scanner.nextLine();
                    System.out.println("Indica la raza");
                    String raza= scanner.nextLine();
                    System.out.println("Indica el dorsal");
                    int dorsal= scanner.nextInt();
                    carrera.addGalgo(new Galgo(nombre, raza, dorsal, carrera.getTiempo()));
                    break;
                case 2:
                    System.out.println("¿Cuanto tiempo quieres que corran los galgos?");
                    carrera.setTiempo(scanner.nextInt());
                    System.out.println("Se ha establecido correctamente el tiempo de carrera");
                    System.out.println();
                    break;
                case 3:
                    carrera.carrera();
                    System.out.println("Gracias por competir con nosotros");
                    salir=true;
                    break;
                case 4:
                    carrera.listarGalgos();
                    break;
                case 5:
                    System.out.println("Gracias por competir con nosotros");
                    salir=true;
                    break;
                default:
                    System.out.println("Opción no contemplada");
                    break;

            }

        }while (!salir);


    }
}
