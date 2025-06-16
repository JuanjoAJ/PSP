import Controller.SorteadorLoteria;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private SorteadorLoteria sorteadorLoteria;
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Server(int port, String url) throws IOException {
    sorteadorLoteria = new SorteadorLoteria(url);
    sorteadorLoteria.generateNumber();
    serverSocket = new ServerSocket();
    InetSocketAddress address= new InetSocketAddress("localhost",port);
    serverSocket.bind(address);
    }

    public void start() throws IOException {
        socket = serverSocket.accept();
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
    }

    public void stop() throws IOException {
        reader.close();
        writer.close();
        socket.close();
        serverSocket.close();
    }


    public static void main(String[] args) {
        try {
            Server server=new Server(8080,"src/main/java/Utils/NumeroLoteria" );
            server.start();
            String numberClient = server.reader.readLine();//Leemos el numero del cliente
            String numberLottery = server.sorteadorLoteria.readNumber();
            if(numberClient.equals(numberLottery)) server.writer.println("Enhorabuena, has ganado la loteria");
            else server.writer.println("Que pena, no has ganado esta vez el numero premiado ha sido " + numberLottery);
            server.stop();
        } catch (IOException e) {
            System.out.println("500. Fallo en el servidor");
        }

    }
}
