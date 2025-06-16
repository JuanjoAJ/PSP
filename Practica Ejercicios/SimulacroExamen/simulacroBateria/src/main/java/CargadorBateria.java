public class CargadorBateria extends Thread {
    private final Bateria bateria;

    public CargadorBateria(Bateria bateria) {
        this.bateria = bateria;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                bateria.cargaBateria();
            }
        } catch (InterruptedException e) {
            System.out.println("Fallo al cargar bateria");
        }
    }
}
