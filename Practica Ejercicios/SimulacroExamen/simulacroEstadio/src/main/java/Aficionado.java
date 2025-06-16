import java.util.concurrent.Semaphore;

public class Aficionado implements Runnable {
    //Una sala vip para todo el estadio
    private static Semaphore salaVip = new Semaphore(1);
    private static Semaphore[] tornos = {new Semaphore(1),new Semaphore(1), new Semaphore(1), new Semaphore(1)};
    private static int contador;
    private int aficionado;
    private boolean salaVipLibre = false;
    private static int torno1;
    private static int torno2;
    private static int torno3;
    private static int torno4;

    public Aficionado() {
        contador++;
        aficionado=contador;
    }

    @Override
    public void run() {
        try {
        do {
            Thread.sleep(2000L);
            if(tornos[0].tryAcquire())
            {
                System.out.printf("El aficionado %d pasa el primer torno \n", aficionado);
                salaVip.acquire();
                Thread.sleep(2000L);
                System.out.printf("El aficionado %d deja la sala vip \n", aficionado);
                salaVip.release();
                salaVipLibre=true;
                tornos[0].release();
                torno1++;
            } else if (tornos[1].tryAcquire()) {
                System.out.printf("El aficionado %d pasa el segundo torno \n", aficionado);
                salaVip.acquire();
                Thread.sleep(2000L);
                System.out.printf("El aficionado %d deja la sala vip \n", aficionado);
                salaVip.release();
                salaVipLibre=true;
                tornos[1].release();
                torno2++;

            } else if (tornos[2].tryAcquire()) {
                System.out.printf("El aficionado %d pasa el tercer torno \n", aficionado);
                salaVip.acquire();
                Thread.sleep(2000L);
                System.out.printf("El aficionado %d deja la sala vip \n", aficionado);
                salaVip.release();
                salaVipLibre=true;
                tornos[2].release();

                torno3++;

            } else if (tornos[3].tryAcquire()) {
                System.out.printf("El aficionado %d pasa el cuarto torno \n", aficionado);
                salaVip.acquire();
                Thread.sleep(2000L);
                System.out.printf("El aficionado %d deja la sala vip \n", aficionado);
                salaVip.release();
                salaVipLibre=true;
                tornos[3].release();

                torno4++;
            }
        }while (!salaVipLibre);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static int getTorno4() {
        return torno4;
    }

    public static int getTorno3() {
        return torno3;
    }

    public static int getTorno2() {
        return torno2;
    }

    public static int getTorno1() {
        return torno1;
    }
}
