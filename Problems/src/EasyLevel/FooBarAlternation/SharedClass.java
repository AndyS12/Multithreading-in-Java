package EasyLevel.FooBarAlternation;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class SharedClass {

    String curr;
    int turn = 0;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public SharedClass(String curr) {
        this.curr = curr;
    }

    public void print() {
        System.out.println(curr);
        if (curr.equalsIgnoreCase("foo")){
            curr = "bar";
        } else {
            curr = "foo";
        }
        turn = (turn+1) % 2;
    }
}
