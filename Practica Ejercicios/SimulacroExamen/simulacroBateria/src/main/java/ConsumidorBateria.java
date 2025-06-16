public class ConsumidorBateria extends Thread{
    private final Bateria bateria;

    public ConsumidorBateria(Bateria bateria) {
        this.bateria = bateria;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                bateria.descargaBateria();
            }
        } catch (InterruptedException e) {
            System.out.println("Fallo al consumir bateria");
        }
    }
}
