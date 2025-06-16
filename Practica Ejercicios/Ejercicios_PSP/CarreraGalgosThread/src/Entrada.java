import controller.Carrera;

import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("¿Cuanto tiempo quieres que corran los galgos?");
        int tiempo= scanner.nextInt();

        Carrera carrera=new Carrera(tiempo);
        carrera.carrera();

    }
}
