package EasyLevel.FooBarAlternation;

public class Main {
    public static void main(String []args){
        SharedClass sharedClass = new SharedClass("foo");
        for(int i = 0; i<2; i++){
            Thread thread = new Thread(new FooBarPrinter(sharedClass, i));
            thread.start();
        }
    }
}
