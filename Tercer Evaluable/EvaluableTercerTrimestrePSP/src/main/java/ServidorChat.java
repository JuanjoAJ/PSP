// ServidorChat.java
import java.io.FileWriter;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ServidorChat {
    private static final int PORT = 5000;
    private static final List<HiloCliente> usuarios =
            Collections.synchronizedList(new ArrayList<>());
    private static final Semaphore semaforo = new Semaphore(1);

    public static void main(String[] args) {
        // Al iniciar el servidor, limpiamos el fichero de mensajes para empezar de 0
        try (FileWriter fw = new FileWriter("mensajes.txt", false)) {
        } catch (IOException e) {
            System.err.println("No se pudo limpiar mensajes.txt: " + e.getMessage());
        }

        try (ServerSocket servidor = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            while (true) {
                Socket cliente = servidor.accept();
                new Thread(new HiloCliente(cliente, usuarios, semaforo)).start();
            }
        } catch (BindException e) {
            System.out.println("Puerto actualmente en uso");
        } catch (IOException e) {
            System.out.println("Fallo IO. Mensaje: " + e.getMessage());
        }
    }
}
