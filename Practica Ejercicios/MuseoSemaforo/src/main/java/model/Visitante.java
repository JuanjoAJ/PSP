package model;

import java.util.concurrent.Semaphore;

public class Visitante extends Thread{
private boolean salaOcupada;
private Semaphore museo=new Semaphore(12);


private static int contador;

    @Override
    public void run() {

    }
}
