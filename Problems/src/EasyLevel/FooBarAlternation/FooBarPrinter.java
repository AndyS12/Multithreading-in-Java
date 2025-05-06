package EasyLevel.FooBarAlternation;

public class FooBarPrinter implements Runnable {

    SharedClass sharedClass;
    int threadID;

    public FooBarPrinter(SharedClass sharedClass, int threadID) {
        this.sharedClass = sharedClass;
        this.threadID = threadID;
    }

    @Override
    public void run() {
        while (true) {
            sharedClass.lock.lock();
            while (threadID != sharedClass.turn) {
                try {
                    sharedClass.condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            sharedClass.print();
            sharedClass.condition.signal();
            sharedClass.lock.unlock();
        }
    }
}
