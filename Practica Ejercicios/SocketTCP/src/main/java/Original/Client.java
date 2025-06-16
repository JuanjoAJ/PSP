package Original;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Client {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream in;
    private OutputStream ou;

    public Client(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void start() throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(serverIP, serverPort));
        in = socket.getInputStream();
        ou = socket.getOutputStream();
    }

    public void close() throws IOException {
        in.close();
        ou.close();
        socket.close();
    }

    public static void main(String[] args) {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Client client = new Client("localhost", 8080);

        try {
            client.start();
            System.out.println("Introduce tu edad: ");
            client.ou.write(Integer.parseInt(bf.readLine())); //Pedimos por consola un entero
            int response = client.in.read();  //Recogemos la respuesta del servidor
            if(response==1) System.out.println("Eres mayor de edad"); //Dependiendo de la respuesta establecemos que es o no mayor de edad
            else System.out.println("Eres menor de edad");
            client.close();
        } catch (IOException e) {
            System.out.println("Fallo en la conexión con el cliente");
        }


    }
}
