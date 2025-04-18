package EasyLevel.PrintUsingNThreads;

public class SharedNumberClass {
    int MAX_LIMIT;
    int currentNumber = 1;

    public SharedNumberClass(int MAX_LIMIT) {
        this.MAX_LIMIT = MAX_LIMIT;
    }

    public void increment() {
        System.out.printf("%d printed by %s\n", currentNumber, Thread.currentThread().getName());
        currentNumber++;
    }
}
