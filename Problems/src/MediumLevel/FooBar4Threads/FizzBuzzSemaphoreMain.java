package MediumLevel.FooBar4Threads;

public class FizzBuzzSemaphoreMain {
    public static void main(String[] args) {
        FizzBuzzSemaphore fizzBuzzSemaphore = new FizzBuzzSemaphore(15);
        Thread thread1 = new Thread(() -> {
            try {
                fizzBuzzSemaphore.fizz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                fizzBuzzSemaphore.buzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                fizzBuzzSemaphore.fizzbuzz();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread4 = new Thread(() -> {
            try {
                fizzBuzzSemaphore.number();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
