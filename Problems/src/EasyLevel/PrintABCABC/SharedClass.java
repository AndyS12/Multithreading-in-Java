package EasyLevel.PrintABCABC;

import java.util.HashMap;
import java.util.Map;

public class SharedClass {
    Map<Integer,String> map = new HashMap<>();
    int numberOfCharactersPrinted = 0;
    int turn = 0;

    public SharedClass(Map<Integer, String> map) {
        this.map = map;
    }

    public void print(int threadId) {
        System.out.print(map.get(threadId));
        numberOfCharactersPrinted++;
        turn = (turn + 1) % 3;
    }
}
