package model;

import java.util.concurrent.Semaphore;

public class Perro extends Thread{
private static Semaphore capacidadBebedero;
private int aguaBebida;
private static int aguaAcumulada;
private static int contador;
private int numPerro;

public Perro(int aguaBebida) {
	capacidadBebedero=new Semaphore(3);
	contador++;
	numPerro=contador;
	this.aguaBebida=aguaBebida;
	aguaAcumulada+=aguaBebida;
}

@Override
	public void run() {
	try {
		capacidadBebedero.acquire();
		System.out.printf("Perro %d comienza a beber\n",numPerro );
		Thread.sleep(aguaBebida*1000);
		System.out.printf("Perro %d termina de beber \n",numPerro);
		capacidadBebedero.release();
	} catch (InterruptedException e) {
		System.out.println("Fallo al poner a dormir el hilo");
	}
	
	}

public int getAguaBebida() {
	return aguaBebida;
}

public void setAguaBebida(int aguaBebida) {
	this.aguaBebida = aguaBebida;
}

public static int getAguaAcumulada() {
	return aguaAcumulada;
}

public static void setAguaAcumulada(int aguaAcumulada) {
	Perro.aguaAcumulada = aguaAcumulada;
}

public static int getContador() {
	return contador;
}

public static void setContador(int contador) {
	Perro.contador = contador;
}

public int getNumPerro() {
	return numPerro;
}

public void setNumPerro(int numPerro) {
	this.numPerro = numPerro;
}



}
