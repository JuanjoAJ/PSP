package controller;

import model.Persona;

public class ParquePublico {
    private Persona[] personas;

    public ParquePublico() {
        this.personas = rellenarPersonas();
    }

    private Persona[] rellenarPersonas(){
        Persona[] personas = new Persona[(int) (Math.random()*20)+1];
        for (int i = 0; i < personas.length; i++) {
            personas[i]=new Persona();
        }
        return personas;
    }


    public void run(){
        for (Persona item:personas) {
            item.start();
        }
        for (Persona item:personas) {
            try {
                item.join();
            } catch (InterruptedException e) {
                System.out.println("Fallo en join");
            }
        }
        System.out.printf("""
                Han entrado un total de %d personas en el parque, de las cuales:
                %d han pasado por el torno A.
                %d eligieron entrar por el torno B.
                %d entraron por el torno C.
                %d pasaron por el torno D.
                %d escogieron el torno E.
                
                """, Persona.getContador(), Persona.getContadorTornoA(), Persona.getContadorTornoB(), Persona.getContadorTornoC(),
        Persona.getContadorTornoD(), Persona.getContadorTornoE());


    }
}
