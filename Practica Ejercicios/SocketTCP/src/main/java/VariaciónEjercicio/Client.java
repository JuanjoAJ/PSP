package VariaciónEjercicio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Client {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream in;
    private OutputStream ou;
    private PrintWriter writer;
    private BufferedReader reader;

    public Client(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void start() throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(serverIP, serverPort));
        in = socket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(in));
        ou = socket.getOutputStream();
        writer = new PrintWriter(ou, true);
    }

    public void close() throws IOException {
        in.close();
        ou.close();
        reader.close();
        writer.close();
        socket.close();
    }

    public static void main(String[] args) {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Client client = new Client("localhost", 8080);

        try {
            client.start();
            System.out.println("Introduce tu nombre");
            String name = bf.readLine(); //Pedimos por consola el nombre
            client.writer.println(name); // Se lo pasamos al servidor
            String responseWelcome= client.reader.readLine(); //Leemos la respuesta
            System.out.println(responseWelcome);
            System.out.println("Introduce tu edad: ");
            String age = bf.readLine(); //Pedimos por consola la edad
            client.writer.println(age); //La enviamos
            String response = client.reader.readLine();  //Recogemos la respuesta del servidor
            System.out.println(response);
            client.close();
        } catch (IOException e) {
            System.out.println("Fallo en la conexión con el cliente");
        }


    }
}
