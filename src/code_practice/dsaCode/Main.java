package code_practice.dsaCode;

public class Main {
    public static void main(String[] args) {
        System.out.println("Manager (main thread) starts work");

        // Hire two workers
        MyWorker worker1 = new MyWorker("Worker-A");
        MyWorker worker2 = new MyWorker("Worker-B");

        // Tell workers to start working
        worker1.start();
        worker2.start();

        // Manager also works
        System.out.println("Manager is also doing paperwork");
    }
}
    // Like creating a special type of worker
    class MyWorker extends Thread {
        private String workerName;

        MyWorker(String name) {
            this.workerName = name;
        }

        // This is what the worker will do
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println(workerName + " is working on task " + i);
                try {
                    Thread.sleep(1000); // Simulate work
                } catch (Exception e) {
                }
            }
        }
    }

