package EasyLevel.OddEvenPrinter;

public class Printer implements Runnable {

    public final SharedClass sharedClass;
    private final int threadId;

    public Printer(SharedClass sharedClass, int threadId) {
        this.sharedClass = sharedClass;
        this.threadId = threadId;
    }

    @Override
    public void run() {
        while(true){
            synchronized (sharedClass) {
                while (sharedClass.getCurrentNumber() <= sharedClass.getMAX_LIMIT() && sharedClass.getCurrentNumber() % 2 != threadId) {
                    try {
                        sharedClass.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (sharedClass.currentNumber > sharedClass.getMAX_LIMIT()) {
                    sharedClass.notifyAll();
                    break;
                }
                sharedClass.print();
                sharedClass.notifyAll();
            }
        }
    }
}
