package controller;

import model.Galgo;

import java.util.ArrayList;

public class Carrera {
    private ArrayList<Galgo> galgos;
    private int tiempo;

    public Carrera() {
        galgos = new ArrayList<>();
        tiempo = (int) (Math.random()*10)+1;
    }

    public void addGalgo(Galgo galgo){
        if (existeGalgo(galgo.getDorsal())){
        System.out.println("Ese dorsal ya está registrado");
        }else {
        galgos.add(galgo);
        System.out.println("Galgo agregado correctamente");
        }
    }

    public boolean existeGalgo(int dorsal){
        for (Galgo item:galgos){
            if (item.getDorsal()==dorsal) return true;
        }
        for (Galgo item:galgosDefault()){
            if (item.getDorsal()==dorsal) return true;
        }
        return false;
    }


    public void listarGalgos(){
        if (galgos.isEmpty()){
            System.out.println("Aún no has registrado galgos");
        }else{
            for (Galgo item:galgos) {
                System.out.println(item);
            }
        }

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
        if (galgos.isEmpty()){
            this.galgos=galgosDefault();
        } else if (galgos.size() == 1) {
            galgos.addAll(galgosDefault());
        }

        boolean ganador = false;
        for (Galgo item : galgos) {
            new Thread(item).start();
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

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getTiempo() {
        return tiempo;
    }
}
