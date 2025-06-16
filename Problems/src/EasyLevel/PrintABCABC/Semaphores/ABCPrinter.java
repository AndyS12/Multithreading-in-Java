package EasyLevel.PrintABCABC.Semaphores;


import java.util.concurrent.Semaphore;

public class ABCPrinter {

    public static void main(String[] args) {
        Semaphore semA = new Semaphore(1);
        Semaphore semB = new Semaphore(0);
        Semaphore semC = new Semaphore(0);

        int n = 3;

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    semA.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("A " + Thread.currentThread().getName());
                semB.release();
            }
        });
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    semB.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("B " + Thread.currentThread().getName());
                semC.release();
            }
        });
        Thread threadC = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    semC.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("C " + Thread.currentThread().getName());
                semA.release();
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }
}