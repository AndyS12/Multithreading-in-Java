package MediumLevel.FooBar4Threads;

import java.util.concurrent.Semaphore;

public class FizzBuzzSemaphore {
    int n;
    int counter = 1;
    Semaphore fizz = new Semaphore(0);
    Semaphore buzz = new Semaphore(0);
    Semaphore fizzBuzz = new Semaphore(0);
    Semaphore number = new Semaphore(1);

    public FizzBuzzSemaphore(int n) {
        this.n = n;
    }

    public void fizz() throws InterruptedException {
        while(true){
            fizz.acquire();
            if(counter > n)
                break;
            System.out.println("Fizz printed by " + Thread.currentThread().getName());
            counter++;
            number.release();
        }
    }
    public void buzz() throws InterruptedException {
        while(true){
            buzz.acquire();
            if(counter > n)
                break;
            System.out.println("Buzz printed by " + Thread.currentThread().getName());
            counter++;
            number.release();
        }
    }
    public void fizzbuzz() throws InterruptedException {
        while(true){
            fizzBuzz.acquire();
            if(counter > n)
                break;
            System.out.println("FizzBuzz printed by " + Thread.currentThread().getName());
            counter++;
            number.release();
        }
    }
    public void number() throws InterruptedException {
        while(true){
            number.acquire();
            if(counter > n){
                fizz.release();
                buzz.release();
                fizzBuzz.release();
                break;
            }
            if (counter % 3 == 0 && counter % 5 == 0) {
                fizzBuzz.release();
            } else if (counter % 3 == 0) {
                fizz.release();
            } else if (counter % 5 == 0) {
                buzz.release();
            } else {
                System.out.println(counter + " printed by " + Thread.currentThread().getName());
                counter++;
                number.release();
            }
        }
    }
}
