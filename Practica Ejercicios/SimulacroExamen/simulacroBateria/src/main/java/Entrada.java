public class Entrada {
    public static void main(String[] args) {
        Bateria bateria= new Bateria();

        CargadorBateria cargadorBateria=new CargadorBateria(bateria);
        ConsumidorBateria consumidorBateria=new ConsumidorBateria(bateria);


        cargadorBateria.start();
        consumidorBateria.start();


    }
}
