package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.Donacion;

public class CuentaBanco {
	ArrayList<Donacion> donaciones;

	public CuentaBanco() {
		donaciones=new ArrayList<>();
	}
	
	public void nuevaDonacion(int dineroDonacion) {
		donaciones.add(new Donacion(dineroDonacion));
	}
	
	public void donacionesRandom() {
		Random random=new Random();
		for (int i = 0; i < random.nextInt(10)+1; i++) {
			Donacion donacion=new Donacion(Math.abs(random.nextInt()+1));
			donaciones.add(donacion);
		}
	}
	
	
	public void run() {
		for (Donacion item : donaciones) {
			item.start();
		}
		for (Donacion item : donaciones) {
			try {
				item.join();
			} catch (InterruptedException e) {
			System.out.println("Fallo en el join del hilo " + item.getNumDonacion());
			}
		}
		System.out.println();
		System.out.println("El total de donaciones ha sido de " + Donacion.getContador());
		System.out.printf("Se ha donado un total de %d € \n", Donacion.getAcumuladoDonacion());
		
	}
	
	
	

}
