package EasyLevel.PrintABCABC.Semaphores;

import java.util.concurrent.Semaphore;

public class ABCPrinter {

    private static final int PRINT_COUNT = 10;

    private static Semaphore semA = new Semaphore(1); // A starts first
    private static Semaphore semB = new Semaphore(0);
    private static Semaphore semC = new Semaphore(0);

    public static void main(String[] args) {

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < PRINT_COUNT; i++) {
                try {
                    semA.acquire();
                    System.out.print("A");
                    semB.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < PRINT_COUNT; i++) {
                try {
                    semB.acquire();
                    System.out.print("B");
                    semC.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            for (int i = 0; i < PRINT_COUNT; i++) {
                try {
                    semC.acquire();
                    System.out.print("C");
                    semA.release();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}