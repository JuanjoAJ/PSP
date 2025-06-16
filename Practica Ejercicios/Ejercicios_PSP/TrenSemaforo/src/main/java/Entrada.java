import controller.EstacionTren;
import model.Tren;

import java.util.TreeMap;

public class Entrada {

    public static void main(String[] args) {
        EstacionTren estacionTren=new EstacionTren();

        try {
            estacionTren.run();
        } catch (InterruptedException e) {
            System.out.println("Fallo en la ejecución de los join");
        }
    }

}
