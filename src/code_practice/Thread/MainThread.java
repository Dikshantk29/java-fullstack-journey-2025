package code_practice.Thread;


/**
 *
 * Thread life cycle
 * new thread ---> runnable() -----> running()----> waiting()----> dead();
 *
 * */
public class MainThread {
    public static void main(String[] args) {
        Hi obj1 = new Hi();
        Hello obj2 = new Hello();


        obj1.show();
        obj2.show();
    }
}

class Hi {
    public void show() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hi, from class Hi");

        }
    }
}

class Hello {
    public void show() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello, from class Hello");
        }
    }
}


