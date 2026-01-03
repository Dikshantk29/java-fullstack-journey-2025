package code_practice.Thread;
//normal way
public class Main {
    public static void main(String[] args) {
        Hi1 obj1 = new Hi1();
        Hello1 obj2 = new Hello1();


       obj1.start();;
       obj2.start();

    }
}

class Hi1 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hi, from class Hi1");

        }
    }
}

class Hello1 extends Thread {
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello, from class Hello1");
        }
    }
}
