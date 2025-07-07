package MediumLevel.ZeroEvenOdd;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ZeroOddEven {

    ReentrantLock lock = new ReentrantLock();
    Condition zeroCondition = lock.newCondition();
    Condition oddCondition = lock.newCondition();
    Condition evenCondition = lock.newCondition();
    int n;
    int state = 0;    // 0 - zero, 1 - odd, 2 - even
    public ZeroOddEven(int n) {
        this.n = n;
    }

    public void printZero(){
        for(int i=1; i<=n; i++){
            lock.lock();
            try{
                while(state!=0){
                    zeroCondition.await();
                }
                System.out.println('0' + " printed by " + Thread.currentThread().getName());
                if(i%2 != 0 ){
                    state = 1;
                    oddCondition.signal();
                } else {
                    state = 2;
                    evenCondition.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printOdd(){
        for(int i=1; i<=n; i+=2){
            lock.lock();
            try{
                while(state!=1){
                    oddCondition.await();
                }
                System.out.println(i + " printed by " + Thread.currentThread().getName());
                state = 0;
                zeroCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printEven(){
        for(int i=2; i<=n; i+=2){
            lock.lock();
            try{
                while(state!=2){
                    evenCondition.await();
                }
                System.out.println(i + " printed by " + Thread.currentThread().getName());
                state = 0;
                zeroCondition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
