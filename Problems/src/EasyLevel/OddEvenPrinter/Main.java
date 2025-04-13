package EasyLevel.OddEvenPrinter;

public class Main {
    public static void main(String[] args) {
        SharedClass sharedClass = new SharedClass(25);

        Thread thread1 = new Thread(new Printer(sharedClass, 0), "Even Printer");
        Thread thread2 = new Thread(new Printer(sharedClass, 1), "Odd Printer");
        thread1.start();
        thread2.start();
    }
}
