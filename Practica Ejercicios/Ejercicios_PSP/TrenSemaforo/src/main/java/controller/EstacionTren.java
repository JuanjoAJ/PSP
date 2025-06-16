package controller;

import model.Tren;

import java.util.ArrayList;
import java.util.Random;

public class EstacionTren {
    private ArrayList<Tren> trenesPasajeros;
    private ArrayList<Tren> trenesCarga;

    public EstacionTren() {
        trenesCarga=rellenarTren(false);
        trenesPasajeros=rellenarTren(true);
    }

    public void run() throws InterruptedException {
        for (Tren item:trenesCarga) {
            item.start();
        }
        for (Tren item:trenesPasajeros) {
            item.start();
        }

        for (Tren item:trenesCarga) {
            item.join();
        }
        for (Tren item:trenesPasajeros) {
            item.join();
        }

        System.out.printf("""
                El total de trenes ha sido de %d de los cuales:
                %d han sido de pasajeros.
                y %d de trenes de carga""", Tren.getContador(), Tren.getTrenPasajeros(), Tren.getTrenCarga());

    }


    private ArrayList<Tren> rellenarTren(boolean pasajeros){
        ArrayList<Tren> trenes=new ArrayList<>();
        Random r=new Random();

        for (int i = 0; i < r.nextInt(1, 20); i++) {
            Tren tren=new Tren(pasajeros);
            trenes.add(tren);
        }

        return trenes;

    }


}
