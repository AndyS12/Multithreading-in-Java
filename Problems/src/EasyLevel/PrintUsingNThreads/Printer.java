package EasyLevel.PrintUsingNThreads;

public class Printer implements Runnable {

    final SharedNumberClass sharedNumberClass;
    int totalThreads;
    int threadNumber;

    public Printer(SharedNumberClass sharedNumberClass, int totalThreads, int threadNumber) {
        this.sharedNumberClass = sharedNumberClass;
        this.totalThreads = totalThreads;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {

        while(true) {
            synchronized (sharedNumberClass) {
                //int validThreadId = (sharedNumberClass.currentNumber - 1) % totalThreads; -- wrong

                while(sharedNumberClass.currentNumber <= sharedNumberClass.MAX_LIMIT &&
                        (sharedNumberClass.currentNumber - 1) % totalThreads != threadNumber) {
                    try {
                        sharedNumberClass.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (sharedNumberClass.currentNumber > sharedNumberClass.MAX_LIMIT) {
                    sharedNumberClass.notifyAll();
                    break;
                }

                sharedNumberClass.increment();
                sharedNumberClass.notifyAll();
            }
        }
    }
}
