package MediumLevel.FooBar4Threads;

public class FizzBuzz {
    private int counter = 1;
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    public void fizz() throws InterruptedException {
        while(true){
            synchronized (this) {
                while(counter <= n && !(counter % 3 == 0 && counter % 5 != 0)){
                    wait();
                }
                if(counter > n){
                    notifyAll();
                    break;
                }
                System.out.println("Fizz printed by " + Thread.currentThread().getName());
                counter++;
                notifyAll();
            }
        }
    }

    public void buzz() throws InterruptedException {
        while(true){
            synchronized (this) {
                while(counter <= n && !(counter % 3 != 0 && counter % 5 == 0)){
                    wait();
                }
                if(counter > n){
                    notifyAll();
                    break;
                }
                System.out.println("Buzz printed by " + Thread.currentThread().getName());
                counter++;
                notifyAll();
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        while(true){
            synchronized (this) {
                while(counter <= n && !(counter % 3 == 0 && counter % 5 == 0)){
                    wait();
                }
                if(counter > n){
                    notifyAll();
                    break;
                }
                System.out.println("FizzBuzz printed by " + Thread.currentThread().getName());
                counter++;
                notifyAll();
            }
        }
    }

    public void number() throws InterruptedException {
        while(true){
            synchronized (this) {
                while(counter <= n && !(counter % 3 != 0 && counter % 5 != 0)){
                    wait();
                }
                if(counter > n){
                    notifyAll();
                    break;
                }
                System.out.println(counter + " printed by " + Thread.currentThread().getName());
                counter++;
                notifyAll();
            }
        }
    }
}
