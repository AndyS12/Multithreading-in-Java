package EasyLevel.PrintABCABC;

public class PrinterThread implements Runnable {

    SharedClass sharedClass;
    int n;  // number of times ABC should be printed
    int threadId;

    public PrinterThread(SharedClass sharedClass, int n, int threadId) {
        this.sharedClass = sharedClass;
        this.n = n;
        this.threadId = threadId;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (sharedClass) {
                while (sharedClass.numberOfCharactersPrinted < n*3 && threadId != sharedClass.turn) {
                    try {
                        sharedClass.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (sharedClass.numberOfCharactersPrinted == n*3) {
                    sharedClass.notifyAll();
                    break;
                }
                sharedClass.print(threadId);
                sharedClass.notifyAll();
            }
        }
    }
}
