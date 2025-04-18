package EasyLevel.PrintUsingNThreads;

public class Main {
    public static void main(String[] args) {
        SharedNumberClass sharedNumberClass = new SharedNumberClass(16);
        int totalThreads = 3;
        for(int i = 0; i < totalThreads; i++){
            Thread thread = new Thread(new Printer(sharedNumberClass, totalThreads, i), "Thread - " + i);
            thread.start();
        }
    }
}
