package code_practice.Thread;

import javax.swing.plaf.TableHeaderUI;

class A implements Runnable{
    public  void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("Hi, Im from class A");
            try{
                Thread.sleep(1000);

            }catch (InterruptedException e){
                System.out.println("Thread interrupted: "+e);

            }
        }
    }
}

class B implements Runnable{
    public  void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("Hello, Im from class B");
            try{
                Thread.sleep(1000);

            }catch (InterruptedException e){
                System.out.println("Thread interrupted: "+e);

            }
        }
    }
}



public class MainRunnableVSThread {

    public static void main(String[] args) {
         Runnable obj1 = new A();
         Runnable obj2 = new B();

         Thread t1 = new Thread(obj1);
        Thread t2  = new Thread(obj2);

        t1.start();
        t2.start();
//        System.out.println(t2.getState());
    }
}
