package MediumLevel.ZeroEvenOdd;

public class Main {
    public static void main(String[] args) {
        ZeroOddEven zeroOddEven = new ZeroOddEven(10);
        Thread thread1 = new Thread(() -> {
            try {
                zeroOddEven.printZero();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(zeroOddEven::printOdd);
        Thread thread3 = new Thread(zeroOddEven::printEven);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
