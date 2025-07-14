package MediumLevel.TrafficSignal;

public class Main {
    public static void main(String[] args) {
        TrafficSignalController trafficSignalController = new TrafficSignalController();
        Thread thread1 = new Thread(() -> {
            try {
                trafficSignalController.signal(Direction.NORTH);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                trafficSignalController.signal(Direction.EAST);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                trafficSignalController.signal(Direction.SOUTH);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread4 = new Thread(() -> {
            try {
                trafficSignalController.signal(Direction.WEST);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
