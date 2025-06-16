package model;

public abstract class Numero implements Runnable{

    private  int numHilo;
    private static int contador;

    public Numero() {
        contador++;
        numHilo=contador;
    }
    
    public int getNumHilo() {
    	return numHilo;
    }
}
