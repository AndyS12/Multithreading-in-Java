package EasyLevel.FooBarAlternation;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore fooSemaphore = new Semaphore(1);
        Semaphore barSemaphore = new Semaphore(0);

        new Thread(() -> {
            while (true) {
                try {
                    fooSemaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Foo printed by " + Thread.currentThread().getName());
                barSemaphore.release();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    barSemaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Bar printed by " + Thread.currentThread().getName());
                fooSemaphore.release();
            }
        }).start();
    }
}
