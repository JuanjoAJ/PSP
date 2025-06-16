package VariaciónEjercicio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private PrintWriter writer;
    private BufferedReader reader;

    public Server(int port) throws IOException { //Constructor para indicar en que puerto y direccion IP
        serverSocket=new ServerSocket();
        InetSocketAddress address= new InetSocketAddress("localhost",port); //Creamos la dirección
        serverSocket.bind(address);
    }

    public void start() throws IOException {
        socket = serverSocket.accept(); //realizamos la conexión con el objeto socket
        inputStream = socket.getInputStream();//Creamos los flujos IO
        reader = new BufferedReader(new InputStreamReader(inputStream));
        outputStream = socket.getOutputStream();
        writer = new PrintWriter(outputStream, true);

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
            String name = server.reader.readLine(); //Leemos la respuesta del nombre
            server.writer.println("Bienvenido " + name); //Le pasamos un mensaje de bienvenida
            int age = Integer.parseInt(server.reader.readLine());
            if(age>18) server.writer.println(String.format("%s con %d años ya eres mayor de edad", name,age ));
            //Si la respuesta del cliente es 18(debemos parsearla) le mandamos un mensaje
            else server.writer.println(name + " sigues siendo menor de edad"); //Si no le mandamos el mensaje de menor de edad
            server.stop();

        } catch (IOException e) {
            System.out.println("500. Fallo en la conexión con el servidor");
            System.out.println(e.getMessage());
        }


    }
}
