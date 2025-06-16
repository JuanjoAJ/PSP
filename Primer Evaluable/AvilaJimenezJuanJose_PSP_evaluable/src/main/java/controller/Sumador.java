package controller;

import model.Impar;
import model.Par;

public class Sumador {

    private final Thread[] hiloPar;
    private final Thread[] hiloImpar;


    public Sumador() {
        hiloPar = new Thread[]{new Thread(new Par()), new Thread(new Par())};
        hiloImpar = new Thread[]{new Thread(new Impar()), new Thread(new Impar())};
    }

    public void run() {
        for (Thread item : hiloPar) {
            item.start();
        }
        for (Thread item : hiloImpar) {
            item.start();
        }

        try {
            for (Thread item : hiloPar) {
                item.join();
            }
            for (Thread item : hiloImpar) {
                item.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Fallo de interrupción en la ejecución de hilos");
        }

        System.out.println("El acumulado de los pares es : " + Par.getAcumulativoPar());
        System.out.println("El acumulado de los impares es : " + Impar.getAcumulativoImpar());

    }
}
