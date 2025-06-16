import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    private int serverPort;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public Client(int serverPort) {
        this.serverPort = serverPort;
    }

    public void start() throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", serverPort));
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void close() throws IOException {
        if (reader!=null) reader.close();
        if (writer!=null)  writer.close();
        socket.close();
    }


    ///Realizamos un metodo de escribir y recibir mensaje para mantener privadas las variables
    public String enviarMensaje(String mensaje) {
        if (writer != null) {
            writer.println(mensaje);
            try {
                return reader.readLine(); // Espera la respuesta del servidor
            } catch (IOException e) {
                return "Error al recibir respuesta del servidor";
            }
        }
        return "No se pudo enviar el mensaje";
    }
}
