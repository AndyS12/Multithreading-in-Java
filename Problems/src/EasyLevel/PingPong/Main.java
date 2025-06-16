package EasyLevel.PingPong;


import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore pingSemaphore = new Semaphore(1, true);
        Semaphore pongSemaphore = new Semaphore(0, true);
        int MAX_LIMIT = 5;

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < MAX_LIMIT; i++) {
                try {
                    pingSemaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Foo + " + Thread.currentThread().getName());
                pongSemaphore.release();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < MAX_LIMIT; i++)  {
                try {
                    pongSemaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Bar + " + Thread.currentThread().getName());
                pingSemaphore.release();
            }
        });
        thread1.start();
        thread2.start();
    }
}
