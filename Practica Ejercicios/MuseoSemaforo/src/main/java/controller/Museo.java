package controller;

import model.Visitante;

import java.util.ArrayList;

public class Museo {
    private ArrayList<Visitante> visitantes;

    public Museo() {
        this.visitantes = rellenarVisitantes();
    }


    private ArrayList<Visitante> rellenarVisitantes(){
        ArrayList<Visitante> visitantes=new ArrayList<>();
        for (int i = 0; i < (int) (Math.random()*50)+1; i++) {

            Visitante visitante=new Visitante();
            visitantes.add(visitante);
        }
        return visitantes;
    }

    public void run() throws InterruptedException {
        for (Visitante visitante:visitantes) {
            visitante.start();
        }
        for (Visitante visitante:visitantes) {
            visitante.join();
        }

        System.out.printf("Ha habido un total de %d visitantes en el museo");

    }
}
