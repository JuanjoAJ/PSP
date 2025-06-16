package controller;

import model.Cliente;

import java.util.ArrayList;
import java.util.Random;

public class Supermercado {
    private final ArrayList<Thread> clientes;

    public Supermercado() {
        clientes = rellenarClientes();
    }

    public void run() {
        for (Thread item : clientes) {
            item.start();
        }
        for (Thread item : clientes) {
            try {
                item.join();
            } catch (InterruptedException e) {
                System.out.println("Error durante el join");
            }
        }
        System.out.printf("""
                El total acumulado de toda la tienda es de %d euros:
                 En la caja 1 se ha conseguido %d €.
                 En la caja 2 se ha acumulado un total de %d €.
                 Por último, este es el dinero conseguido en la caja 3, %d € \n""", Cliente.getCompraTotal(),
                Cliente.getCaja1Total(), Cliente.getCaja2Total(), Cliente.getCaja3Total());
    }


    private ArrayList<Thread> rellenarClientes() {
        ArrayList<Thread> clientes = new ArrayList<>();
        Random r = new Random();

        for (int i = 0; i < r.nextInt(15, 30); i++) {
            Cliente cliente = new Cliente(r.nextInt(1, 100));
            clientes.add(new Thread(cliente));
        }
        return clientes;
    }


}
