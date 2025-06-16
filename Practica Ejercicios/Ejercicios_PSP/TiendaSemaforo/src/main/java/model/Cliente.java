package model;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cliente implements Runnable{
    private static int compraTotal;
    private static int caja1Total;
    private static int caja2Total;
    private static int caja3Total;
    private static int contador;
    private final static Semaphore tienda=new Semaphore(15);
    private final static Semaphore[] caja= {new Semaphore(1), new Semaphore(1), new Semaphore(1)};
    private boolean cajaLibre=false;
    private int compraIndividual;
    private int idCliente;

    public Cliente(int compraIndividual) {
        this.compraIndividual = compraIndividual;
        contador++;
        this.idCliente=contador;
    }


    @Override
    public void run() {
        Random r=new Random();
        try {

            tienda.acquire();
            System.out.printf("El cliente %d entra en la tienda \n",idCliente);
            Thread.sleep(r.nextInt(1, 10)* 1000L);
            do{
                if(caja[0].tryAcquire()){
                    System.out.printf("El cliente %d accede por la caja 1 y realiza una compra de %d \n",idCliente, compraIndividual);
                    Thread.sleep(3000L);
                    caja[0].release();
                    cajaLibre=true;
                    compraTotal+=compraIndividual;
                    caja1Total+=compraIndividual;
                    System.out.printf("El cliente %d sale de la caja 1 \n", idCliente);
                } else if(caja[1].tryAcquire()){
                    System.out.printf("El cliente %d accede por la caja 2 y realiza una compra de %d \n",idCliente, compraIndividual);
                    Thread.sleep(3000L);
                    caja[1].release();
                    cajaLibre=true;
                    compraTotal+=compraIndividual;
                    caja2Total+=compraIndividual;
                    System.out.printf("El cliente %d sale de la caja 2 \n", idCliente);
                }else if(caja[2].tryAcquire()){
                    System.out.printf("El cliente %d accede por la caja 3 y realiza una compra de %d \n",idCliente, compraIndividual);
                    Thread.sleep(3000L);
                    caja[2].release();
                    cajaLibre=true;
                    compraTotal+=compraIndividual;
                    caja3Total+=compraIndividual;
                    System.out.printf("El cliente %d sale de la caja 3 \n", idCliente);
                }

            }while(!cajaLibre);
            System.out.printf("El cliente %d sale de la tienda \n",idCliente);
            tienda.release();

        } catch (InterruptedException e) {
            System.out.println("Error en la ejecución de semáforos");
        }
    }

    public static int getCompraTotal() {
        return compraTotal;
    }
    public static int getCaja3Total() {
        return caja3Total;
    }
    public static void setCaja3Total(int caja3Total) {
        Cliente.caja3Total = caja3Total;
    }
    public static int getCaja2Total() {
        return caja2Total;
    }
    public static void setCaja2Total(int caja2Total) {
        Cliente.caja2Total = caja2Total;
    }
    public static int getCaja1Total() {
        return caja1Total;
    }
    public static void setCaja1Total(int caja1Total) {
        Cliente.caja1Total = caja1Total;
    }



}
