package controller;

import java.util.ArrayList;

import model.Perro;

public class Bebedero {
	ArrayList<Perro> jauria;

	public Bebedero() {
		jauria = rellenarJauria();
	}

	public ArrayList<Perro> rellenarJauria() {
		ArrayList<Perro> jauria=new ArrayList<>();
		for (int i = 0; i < (Math.random() * 10) + 10; i++) {
			Perro perro = new Perro((int) (Math.random() * 5) + 1);
			jauria.add(perro);
		}
		return jauria;
	}

	public void run() {
		for (Perro perro : jauria) {
			perro.start();
		}
		for (Perro perro : jauria) {
			try {
				perro.join();
			} catch (InterruptedException e) {
			System.out.println("Fallo en la ejecución del join");
			}
		}
		
		System.out.printf("Los perros beben un total de %d litros \n", Perro.getAguaAcumulada());
	}
}
