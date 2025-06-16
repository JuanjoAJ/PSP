package model;

public class Donacion extends Thread{
	private int numDonacion;
	private int dineroDonacionIndividual;
	private static int contador;
	private static int acumuladoDonacion;
	
	public Donacion(int dineroDonacionIndividual) {
		contador++;
		this.numDonacion = contador;
		this.dineroDonacionIndividual=dineroDonacionIndividual;
		acumuladoDonacion+=dineroDonacionIndividual;
		
	}
	

	@Override
	public void run() {
		System.out.printf("Donación %d con una cantidad de %d € \n", numDonacion, dineroDonacionIndividual);
		try {
			Thread.sleep((long) ((Math.random()*5)+1)*1000);
			System.out.printf("Donación %d completada \n", numDonacion);
		} catch (InterruptedException e) {
			System.out.println("Error al dormir el hilo");
		}
	}

	public int getNumDonacion() {
		return numDonacion;
	}

	public void setNumDonacion(int numDonacion) {
		this.numDonacion = numDonacion;
	}

	public int getDineroDonacionIndividual() {
		return dineroDonacionIndividual;
	}

	public void setDineroDonacionIndividual(int dineroDonacionIndividual) {
		this.dineroDonacionIndividual = dineroDonacionIndividual;
	}

	public static int getContador() {
		return contador;
	}

	public static void setContador(int contador) {
		Donacion.contador = contador;
	}

	public static int getAcumuladoDonacion() {
		return acumuladoDonacion;
	}

	public static void setAcumuladoDonacion(int acumuladoDonacion) {
		Donacion.acumuladoDonacion = acumuladoDonacion;
	}
	
	

}
