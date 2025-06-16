package model;

import java.util.concurrent.Semaphore;

public class Persona extends Thread{
    private static Semaphore puertaAcceso=new Semaphore(5);
    private static Semaphore tornoA=new Semaphore(1);
    private static Semaphore tornoB=new Semaphore(1);
    private static Semaphore tornoC=new Semaphore(1);
    private static Semaphore tornoD=new Semaphore(1);
    private static Semaphore tornoE=new Semaphore(1);
    private static int contador;
    private int id;
    private boolean tornoOcupado;
    private static int contadorTornoA;
    private static int contadorTornoB;
    private static int contadorTornoC;
    private static int contadorTornoD;
    private static int contadorTornoE;
    public Persona() {
        id=++contador;
        tornoOcupado=true;
    }

    @Override
    public void run() {
        try {
            puertaAcceso.acquire();
            System.out.printf("La persona número %d entra en el parque \n", id);
            Thread.sleep(6000L);
            do{
                if(tornoA.tryAcquire()){
                    System.out.printf("La persona %d entra por el torno A \n", id);
                    Thread.sleep(2000L);
                    System.out.printf("La persona %d sale del torno A \n", id);
                    tornoA.release();
                    contadorTornoA++;
                    tornoOcupado=false;
                } else if (tornoB.tryAcquire()) {
                    System.out.printf("La persona %d entra por el torno B \n", id);
                    Thread.sleep(2000L);
                    System.out.printf("La persona %d sale del torno B \n", id);
                    tornoB.release();
                    contadorTornoB++;
                    tornoOcupado=false;
                } else if (tornoC.tryAcquire()) {
                    System.out.printf("La persona %d entra por el torno C \n", id);
                    Thread.sleep(2000L);
                    System.out.printf("La persona %d sale del torno C \n", id);
                    tornoC.release();
                    contadorTornoC++;
                    tornoOcupado=false;
                } else if (tornoD.tryAcquire()) {
                    System.out.printf("La persona %d entra por el torno D \n", id);
                    Thread.sleep(2000L);
                    System.out.printf("La persona %d sale del torno D \n", id);
                    tornoD.release();
                    contadorTornoD++;
                    tornoOcupado=false;
                } else if (tornoE.tryAcquire()) {
                    System.out.printf("La persona %d entra por el torno E \n", id);
                    Thread.sleep(2000L);
                    System.out.printf("La persona %d sale del torno E \n", id);
                    tornoE.release();
                    contadorTornoE++;
                    tornoOcupado=false;
                }
            }while (tornoOcupado);
            Thread.sleep(7000L);
            System.out.printf("La persona número %d sale del parque \n",id);
            puertaAcceso.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


    public static int getContadorTornoA() {
        return contadorTornoA;
    }

    public static int getContadorTornoB() {
        return contadorTornoB;
    }

    public static int getContadorTornoC() {
        return contadorTornoC;
    }

    public static int getContadorTornoD() {
        return contadorTornoD;
    }

    public static int getContadorTornoE() {
        return contadorTornoE;
    }

    public static int getContador() {
        return contador;
    }


}
