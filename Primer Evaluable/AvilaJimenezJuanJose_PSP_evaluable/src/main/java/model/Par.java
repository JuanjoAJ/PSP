package model;




public class Par extends Numero{

    private static int acumulativoPar;

    @Override
    public void run() {
        System.out.println("Comienza el hilo " + getNumHilo());
        for (int i = 2; i <= 10; i+=2) {
            System.out.printf("%d. El número par es : %d \n", getNumHilo(), i);
            acumulativoPar+=i;
        }
        System.out.println("Termina el hilo " + getNumHilo());
    }

    public static int getAcumulativoPar() {
    	return acumulativoPar;
    }

}
