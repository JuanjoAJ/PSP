package Controller;

import java.io.*;
import java.util.concurrent.Semaphore;

public class SorteadorLoteria{

    private File file;
    private static final Semaphore SEMAPHORE = new Semaphore(1);

    public SorteadorLoteria(String url) {
        file = new File(url);
    }


    ///Función para generar numero de la loteria, nos aseguramos que el archivo existe si no es así lo creamos
    ///  generamos un numero aleatorio de 0 a 99999, creamos el objeto FileWriter
    ///  pasamos el numero como string
    public void generateNumber() throws IOException {
        if(!file.exists())
        {
            file.createNewFile();
        }
        int lotteryNumber =(int) (Math.random()*100000);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(String.valueOf(lotteryNumber));
        fileWriter.close();
    }

    ///Función de lectura del número, mediante el uso de semaforos nos aseguramos que queda bloqueado hasta que termina,
    /// dejando así al siguiente hilo acceder
   public String readNumber()
   {
       String number="";
       try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
           SEMAPHORE.acquire();
           number = bf.readLine();
       } catch (InterruptedException e) {
           System.out.println("Fallo al leer el número");
           Thread.currentThread().interrupt();
       } catch (FileNotFoundException e) {
           System.out.println("Archivo no encontrado");
       } catch (IOException e) {
           System.out.println("Error IO" + e.getMessage());
       } finally {
           SEMAPHORE.release();
       }
       return number;
   }
}
