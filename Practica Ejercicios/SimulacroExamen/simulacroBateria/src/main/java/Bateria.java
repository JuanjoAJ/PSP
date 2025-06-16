public class Bateria {

    private boolean carga;
    private int capacidad;


    public Bateria() {
        carga=true;
        capacidad=0;
    }

    public synchronized void cargaBateria() throws InterruptedException {
        if(carga)
        {
            capacidad+=10;

            if(capacidad>100) capacidad=100;
            System.out.println("Carga de bateria de 10. Total " + capacidad);
            if(capacidad==100)
            {
                System.out.print("Carga completa");
                carga=false;
                wait();
            } else if (capacidad>=90) {
                notifyAll();
            }


        } else if (capacidad<=10) {
            System.out.printf("Carga bateria de 5 por ciento. Total %d bateria \n", capacidad);
            capacidad+=5;
        }

    }

    public synchronized void descargaBateria() throws InterruptedException {
        if(!carga)
        {
            capacidad-=10;
            if (capacidad<0) capacidad=0;
            System.out.println("Descarga de bateria. Total: " + capacidad);
            if(capacidad==0)
            {
                System.out.println("Descarga completa");
                carga=true;
                wait();
            } else if (capacidad<=10) {
                notifyAll();
            }
        }else if(capacidad>=90)
        {
            System.out.printf("Consumo bateria de 5 por ciento. Total %d bateria \n", capacidad);
            capacidad-=5;
        }
    }


}
