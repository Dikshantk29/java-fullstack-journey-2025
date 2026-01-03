package code_practice.Thread;

public class MainThreadPrioritySleep {
    public static void main(String[] args) {

        Hi2 obj1 = new Hi2();
        Hello2 obj2 = new Hello2();

        obj1.setPriority(Thread.MIN_PRIORITY); // 1
        obj2.setPriority(Thread.MAX_PRIORITY); // 10

        obj1.start();
        obj2.start();
    }
}

class Hi2 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hi, from class Hi");
        }
    }
}

class Hello2 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello, from class Hello");
        }
    }
}
