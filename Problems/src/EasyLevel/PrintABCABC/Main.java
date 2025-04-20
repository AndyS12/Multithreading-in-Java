package EasyLevel.PrintABCABC;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");
        SharedClass sharedClass = new SharedClass(map);
        for (int i = 0; i < 3; i++) {
            Thread printerThread = new Thread(new PrinterThread(sharedClass, 9, i));
            printerThread.start();
        }
    }
}
