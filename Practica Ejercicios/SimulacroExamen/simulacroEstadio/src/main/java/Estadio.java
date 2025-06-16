import com.sun.tools.attach.AgentInitializationException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Estadio {
    private ArrayList<Thread> aficionados;


    public Estadio(int numAficionados) {
        this.aficionados = rellenarAficionados(numAficionados);
    }

    public void run()
    {
        for (Thread aficionado:aficionados) {
            aficionado.start();
        }

        for (Thread aficionado:aficionados) {
            try {
                aficionado.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.printf("""
                Han pasado un total de %d aficionados:
                Torno 1: %d aficionados. 
                Torno 2: %d aficionados. 
                Torno 3: %d aficionados. 
                Torno 4: %d aficionados. 
                """, aficionados.size(), Aficionado.getTorno1(), Aficionado.getTorno2(), Aficionado.getTorno3(), Aficionado.getTorno4());
    }


    private ArrayList<Thread> rellenarAficionados(int numAficionados)
    {
        ArrayList<Thread> aficionados = new ArrayList<>();
        for (int i = 0; i < numAficionados; i++) {
            Aficionado aficionado=new Aficionado();
            aficionados.add(new Thread(aficionado));
        }
        return aficionados;
    }

}
