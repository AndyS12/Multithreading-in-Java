import java.util.concurrent.locks.ReentrantLock;

public class Main {

    static ReentrantLock lock = new ReentrantLock(true); // Change to true to test fair lock

    public static void main(String[] args) {
        for (int i = 1; i <= 3; i++) {
            int threadId = i;
            Thread t = new Thread(() -> {
                for (int j = 1; j <= 5; j++) {
                    try {
                        Thread.sleep(50); // Force contention
                        lock.lock();
                        System.out.println("Thread " + threadId + " printing " + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            });
            t.start();
        }
    }
}