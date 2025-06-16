package Original;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public Server(int port) throws IOException { //Constructor para indicar en que puerto y direccion IP
        serverSocket=new ServerSocket();
        InetSocketAddress address= new InetSocketAddress("localhost",port); //Creamos la dirección
        serverSocket.bind(address);
    }

    public void start() throws IOException {
        socket = serverSocket.accept(); //realizamos la conexión con el objeto socket

        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();//Creamos los flujos IO


    }

    ///Función para cerrar todas las conexiones
    public void stop() throws IOException {
        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();
    }


    public static void main(String[] args) {


        try {
            Server server = new Server(8080);
            server.start();

            if(server.inputStream.read()>18) server.outputStream.write(1);

            server.stop();

        } catch (IOException e) {
            System.out.println("500. Fallo en la conexión con el servidor");
            System.out.println(e.getMessage());
        }


    }
}
