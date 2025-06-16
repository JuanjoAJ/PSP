package model;



public class Impar extends Numero{

    private static int acumulativoImpar;


    @Override
    public void run() {
       System.out.println("Comienza el hilo " + getNumHilo());
        for (int i = 1; i < 10; i+=2) {
            System.out.printf("%d. El número impar es : %d \n", getNumHilo(), i);
            acumulativoImpar+=i;
        }
        System.out.println("Termina el hilo " + getNumHilo());
    }


    public static int getAcumulativoImpar() {
    	return acumulativoImpar;
    }
    
    
    
}
