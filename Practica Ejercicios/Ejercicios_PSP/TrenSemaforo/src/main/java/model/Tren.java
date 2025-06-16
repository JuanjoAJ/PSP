package model;

import java.util.concurrent.Semaphore;

public class Tren extends  Thread{
    private static Semaphore estacion=new Semaphore(10);
    private static Semaphore viaPasajeroIda=new Semaphore(1);
    private static Semaphore viaPasajeroVuelta=new Semaphore(1);
    private static Semaphore viaCargaIda=new Semaphore(1);
    private static Semaphore viaCargaVuelta=new Semaphore(1);

    private static int contador;

    private  static int trenPasajeros;
    private static int trenCarga;
    private final int idTren;
    private boolean hasPasajeros;
    private boolean viaOcupada;

    public Tren(boolean hasPasajeros) {
        this.idTren = ++contador;
        this.hasPasajeros=hasPasajeros;
        viaOcupada=true;
    }

    @Override
    public void run() {
        try {
            estacion.acquire();
            System.out.println(this + " acaba de entrar en la estación");
            if(hasPasajeros){
               do{
                if (viaPasajeroIda.tryAcquire()){
                    System.out.println(this + " ha entrado en la via de Ida");
                    Thread.sleep(5000L);
                    System.out.println(this + " acaba de abandonar la via");
                    viaPasajeroIda.release();
                    viaOcupada=false;
                } else if (viaPasajeroVuelta.tryAcquire()) {
                    System.out.println(this + " ha entrado en la via de Vuelta");
                    Thread.sleep(2000L);
                    System.out.println(this + " acaba de abandonar la via");
                    viaPasajeroVuelta.release();
                    viaOcupada=false;
                }
               }while (viaOcupada);
               trenPasajeros++;
            }else{
                do{
                    if (viaCargaIda.tryAcquire()){
                        System.out.println(this + " ha entrado en la via de Ida");
                        Thread.sleep(5000L);
                        System.out.println(this + " acaba de abandonar la via");
                        viaCargaIda.release();
                        viaOcupada=false;
                    } else if (viaCargaVuelta.tryAcquire()) {
                        System.out.println(this + " ha entrado en la via de Vuelta");
                        Thread.sleep(2000L);
                        System.out.println(this + " acaba de abandonar la via");
                        viaCargaVuelta.release();
                        viaOcupada=false;
                    }
                }while (viaOcupada);
                trenCarga++;
            }
            System.out.println(this + " abandona la estación");
            estacion.release();
        } catch (InterruptedException e) {
            System.out.println("Error en semáforos");
        }

    }

    public int getIdTren() {
        return idTren;
    }


    public static int getTrenPasajeros() {
        return trenPasajeros;
    }

    public static int getTrenCarga() {
        return trenCarga;
    }


    public static int getContador() {
        return contador;
    }

    @Override
    public String toString() {
        if(hasPasajeros) return "Tren de pasajeros " + idTren;
        else return "Tren de carga " + idTren;
    }
}
