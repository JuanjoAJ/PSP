package controller;

import model.Galgo;

import java.util.ArrayList;

public class Carrera {
    private final ArrayList<Galgo> galgos;
    private final int tiempo;

    public Carrera() {
        galgos = galgosDefault();
        tiempo = 10;
    }

    public Carrera(int tiempo) {
        this.tiempo = tiempo;
        this.galgos = galgosDefault();
    }

    public Carrera(int tiempo, ArrayList<Galgo> galgos) {
        this.tiempo = tiempo;
        this.galgos = galgos;
    }

    public ArrayList<Galgo> galgosDefault() {
        ArrayList<Galgo> galgos = new ArrayList<>();
        galgos.add(new Galgo("PatasLargas", "inglés", 1, tiempo));
        galgos.add(new Galgo("Melenudo", "persa", 2, tiempo));
        galgos.add(new Galgo("Vodka", "ruso", 3, tiempo));
        galgos.add(new Galgo("Flash", "whippet", 4, tiempo));

        return galgos;
    }

    public void carrera() {
        Boolean ganador = false;
        for (Galgo item : galgos) {
             item.start();
        }
        while (!ganador){
            for (Galgo item : galgos) {
                if (item.isFin()){
                    System.out.println("Nuestro ganador de la carrera ha sido " + item);
                    ganador =true;
                    break;
                }
            }
        }


    }

}
