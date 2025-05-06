package EasyLevel.FooBarAlternation;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore pingSemaphore = new Semaphore(1);
        Semaphore pongSemaphore = new Semaphore(0);

        new Thread(() -> {
            while (true) {
                try {
                    pingSemaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Foo printed by " + Thread.currentThread().getName());
                pongSemaphore.release();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    pongSemaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Bar printed by " + Thread.currentThread().getName());
                pingSemaphore.release();
            }
        }).start();
    }
}
