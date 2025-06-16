package model;

public class Galgo extends Thread{
    private String nombre;
    private String raza;
    private int dorsal;
    private int velocidad;
    private final int tiempoCarrera;
    private boolean fin;


    public Galgo(String nombre, String raza, int dorsal, int tiempoCarrera) {
        this.nombre = nombre;
        this.raza = raza;
        this.dorsal = dorsal;
        this.tiempoCarrera=tiempoCarrera;
        this.velocidad=(int) (Math.random()*10)+1;
        this.fin=false;
    }

    @Override
    public void run() {
        System.out.printf("%s con dorsal %d empieza a correr%n", nombre, dorsal);
        try {
            Thread.sleep((1000L *tiempoCarrera)-(velocidad* 100L));
            this.fin=true;
        } catch (InterruptedException e) {
            System.out.println("Fallo en la carrera" + e.getMessage());
        }

    }

    @Override
    public String toString() {
        return String.format("%s con el dorsal %d y una velocidad de %d. Una gran carrera de nuestro galgo %s",
                nombre, dorsal, velocidad, raza);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }
}
