# Multithreading in Java - Comprehensive Technical Notes


# Multithreading in Java - Beginner Friendly Guide

## Table of Contents
1. [What is a Thread?](#what-is-a-thread)
2. [Why Use Multiple Threads?](#why-use-multiple-threads)
3. [Creating Your First Thread](#creating-your-first-thread)
4. [Two Ways to Create Threads](#two-ways-to-create-threads)
5. [Thread Life Cycle (Easy Version)](#thread-life-cycle-easy-version)
6. [Common Thread Methods](#common-thread-methods)
7. [Thread Safety Basics](#thread-safety-basics)
8. [Common Problems & Solutions](#common-problems--solutions)
9. [Simple Interview Questions](#simple-interview-questions)
10. [Real Examples You See Daily](#real-examples-you-see-daily)

---

## What is a Thread?

### Simple Analogy: Restaurant Kitchen
Think of a thread as a **chef in a restaurant kitchen**:

| Real Kitchen | Java Thread |
|-------------|-------------|
| One chef doing everything | Single-threaded program |
| Multiple chefs working together | Multi-threaded program |
| Each chef has their own station | Each thread has its own task |
| Chefs share the same kitchen | Threads share the same memory |
| Head chef coordinates others | Main thread creates other threads |

### Basic Example: One Thread
```java
public class SimpleThread {
    public static void main(String[] args) {
        System.out.println("I'm the main thread!");
        System.out.println("I do everything by myself...");
        
        for(int i = 1; i <= 3; i++) {
            System.out.println("Task " + i + " completed");
            try {
                Thread.sleep(1000); // Wait 1 second
            } catch (Exception e) {}
        }
    }
}
```

**Output:**
```
I'm the main thread!
I do everything by myself...
Task 1 completed
(wait 1 second)
Task 2 completed
(wait 1 second)
Task 3 completed
```

**Problem:** Everything happens one after another. Slow!

---

## Why Use Multiple Threads?

### Real-Life Example: Morning Routine
Imagine your morning:
- Brush teeth (2 minutes)
- Make coffee (5 minutes)
- Toast bread (3 minutes)

**Single-threaded (you alone):** 10 minutes total
**Multi-threaded (with help):** 5 minutes total (you brush teeth while coffee brews)

### Benefits Table

| Single Thread | Multiple Threads |
|--------------|-----------------|
| Does one task at a time | Does multiple tasks together |
| Like using one hand | Like using both hands |
| Simple but slow | Faster but needs coordination |
| No sharing issues | Need to share resources carefully |

### Code Example: Without vs With Threads

**Without Threads (Sequential):**
```java
class WithoutThreads {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        
        task1(); // Takes 2 seconds
        task2(); // Takes 3 seconds
        task3(); // Takes 1 second
        
        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end-start)/1000 + " seconds");
    }
    
    static void task1() { try { Thread.sleep(2000); } catch(Exception e) {} }
    static void task2() { try { Thread.sleep(3000); } catch(Exception e) {} }
    static void task3() { try { Thread.sleep(1000); } catch(Exception e) {} }
}
```
**Output:** `Total time: 6 seconds`

**With Threads (Parallel):**
```java
class WithThreads {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        
        Thread t1 = new Thread(() -> task1());
        Thread t2 = new Thread(() -> task2());
        Thread t3 = new Thread(() -> task3());
        
        t1.start(); // Start all threads
        t2.start();
        t3.start();
        
        t1.join(); // Wait for all to finish
        t2.join();
        t3.join();
        
        long end = System.currentTimeMillis();
        System.out.println("Total time: " + (end-start)/1000 + " seconds");
    }
    
    static void task1() { try { Thread.sleep(2000); } catch(Exception e) {} }
    static void task2() { try { Thread.sleep(3000); } catch(Exception e) {} }
    static void task3() { try { Thread.sleep(1000); } catch(Exception e) {} }
}
```
**Output:** `Total time: 3 seconds` (Much faster!)

---

## Creating Your First Thread

### Method 1: Extend Thread Class (Easier)
```java
// Like creating a special type of worker
class MyWorker extends Thread {
    private String workerName;
    
    MyWorker(String name) {
        this.workerName = name;
    }
    
    // This is what the worker will do
    public void run() {
        for(int i = 1; i <= 3; i++) {
            System.out.println(workerName + " is working on task " + i);
            try {
                Thread.sleep(1000); // Simulate work
            } catch(Exception e) {}
        }
    }
}

public class FirstThread {
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
```

**Possible Output:**
```
Manager (main thread) starts work
Manager is also doing paperwork
Worker-A is working on task 1
Worker-B is working on task 1
Worker-A is working on task 2
Worker-B is working on task 2
Worker-A is working on task 3
Worker-B is working on task 3
```

**Note:** Output order might change! That's because threads run independently.

### Method 2: Implement Runnable (Better for sharing)
```java
// Like giving job instructions that anyone can follow
class PrintJob implements Runnable {
    private String jobName;
    
    PrintJob(String name) {
        this.jobName = name;
    }
    
    // The job instructions
    public void run() {
        for(int i = 1; i <= 3; i++) {
            System.out.println(jobName + " - Page " + i + " printed");
            try { Thread.sleep(500); } catch(Exception e) {}
        }
    }
}

public class RunnableDemo {
    public static void main(String[] args) {
        // Create job instructions
        PrintJob job1 = new PrintJob("Report");
        PrintJob job2 = new PrintJob("Invoice");
        
        // Give jobs to workers (threads)
        Thread printer1 = new Thread(job1, "Printer-1");
        Thread printer2 = new Thread(job2, "Printer-2");
        
        printer1.start();
        printer2.start();
    }
}
```

---

## Two Ways to Create Threads

### Simple Comparison

| Extending Thread | Implementing Runnable |
|-----------------|---------------------|
| `class Worker extends Thread` | `class Job implements Runnable` |
| Worker IS A thread | Job IS A task |
| Use when worker is specialized | Use when task can be done by anyone |
| Can't extend other classes | Can extend other classes |
| Direct: `worker.start()` | Need thread: `new Thread(job).start()` |

### Which One to Use?
```java
// Use Thread when:
class Downloader extends Thread {
    // This is specifically a downloading thread
}

// Use Runnable when:
class DownloadTask implements Runnable {
    // This is a task that can be done by any thread
    // You can also extend other classes:
    // class DownloadTask extends NetworkTask implements Runnable
}
```

### Simple Rule:
- **Use Runnable** most of the time (more flexible)
- **Use Thread** only for very simple cases

---

## Thread Life Cycle (Easy Version)

### Thread States Diagram for Beginners
```
NEW (Just born) 
  ↓ start()
RUNNABLE (Ready to work)
  ↓ CPU picks it
RUNNING (Actually working)
  ↓ finishes or waits
TERMINATED (Done)
```

### Simple Example Showing States
```java
public class ThreadStatesSimple {
    public static void main(String[] args) throws Exception {
        Thread worker = new Thread(() -> {
            System.out.println("Worker: Starting work");
            try {
                Thread.sleep(2000); // Working
                System.out.println("Worker: Taking break");
                Thread.sleep(1000); // Waiting
                System.out.println("Worker: Work complete");
            } catch(Exception e) {}
        });
        
        System.out.println("1. State after creation: " + worker.getState()); // NEW
        
        worker.start();
        System.out.println("2. State after start: " + worker.getState()); // RUNNABLE
        
        Thread.sleep(500);
        System.out.println("3. State while working: " + worker.getState()); // TIMED_WAITING
        
        Thread.sleep(3000);
        System.out.println("4. State after finish: " + worker.getState()); // TERMINATED
    }
}
```

### State Cheat Sheet

| State | Simple Meaning | Example |
|-------|---------------|---------|
| **NEW** | Created but not started | `Thread t = new Thread()` |
| **RUNNABLE** | Ready to run | After `t.start()` |
| **RUNNING** | Actually executing | When CPU is running it |
| **WAITING** | Waiting for something | `t.join()`, `wait()` |
| **TIMED_WAITING** | Waiting with timeout | `Thread.sleep(1000)` |
| **TERMINATED** | Finished work | After `run()` completes |

---

## Common Thread Methods

### 1. join() - "Wait for me!"
```java
public class JoinDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("Mom: Time for dinner!");
        
        Thread kid = new Thread(() -> {
            System.out.println("Kid: Playing video games...");
            try { Thread.sleep(3000); } catch(Exception e) {}
            System.out.println("Kid: OK coming!");
        });
        
        kid.start();
        
        // Mom waits for kid
        System.out.println("Mom: Waiting for kid...");
        kid.join(); // Wait until kid finishes
        
        System.out.println("Mom: Let's eat!");
    }
}
```

### 2. sleep() - "Take a nap"
```java
public class SleepDemo {
    public static void main(String[] args) {
        Thread counter = new Thread(() -> {
            for(int i = 5; i > 0; i--) {
                System.out.println("Countdown: " + i);
                try {
                    Thread.sleep(1000); // Wait 1 second
                } catch(Exception e) {}
            }
            System.out.println("Boom!");
        });
        
        counter.start();
    }
}
```

### 3. interrupt() - "Stop what you're doing"
```java
public class InterruptDemo {
    public static void main(String[] args) throws Exception {
        Thread download = new Thread(() -> {
            for(int i = 1; i <= 10; i++) {
                System.out.println("Downloading " + i + "0%");
                try {
                    Thread.sleep(1000);
                } catch(Exception e) {
                    System.out.println("Download cancelled!");
                    return; // Stop the thread
                }
            }
            System.out.println("Download complete!");
        });
        
        download.start();
        Thread.sleep(3500); // Let it run for 3.5 seconds
        
        System.out.println("User clicked Cancel!");
        download.interrupt(); // Try to stop it
    }
}
```

### 4. isAlive() - "Are you done?"
```java
public class IsAliveDemo {
    public static void main(String[] args) throws Exception {
        Thread task = new Thread(() -> {
            try { Thread.sleep(2000); } catch(Exception e) {}
        });
        
        System.out.println("Task alive? " + task.isAlive()); // false
        
        task.start();
        System.out.println("Task alive? " + task.isAlive()); // true
        
        Thread.sleep(3000);
        System.out.println("Task alive? " + task.isAlive()); // false
    }
}
```

---

## Thread Safety Basics

### The Problem: Two People Editing Same Document
```java
class BankAccount {
    int balance = 1000;
    
    void withdraw(int amount) {
        // Check balance
        if(balance >= amount) {
            // Problem: Between check and withdraw, 
            // another thread might also withdraw!
            balance = balance - amount;
        }
    }
}
```

### Solution: Synchronized (Like a Bathroom Lock)
```java
class SafeBankAccount {
    int balance = 1000;
    
    // synchronized = only one thread can enter at a time
    synchronized void withdraw(int amount) {
        if(balance >= amount) {
            // Now safe! Other threads wait outside
            balance = balance - amount;
            System.out.println(Thread.currentThread().getName() 
                             + " withdrew " + amount);
        }
    }
}

public class ThreadSafetyDemo {
    public static void main(String[] args) throws Exception {
        SafeBankAccount account = new SafeBankAccount();
        
        // Husband tries to withdraw
        Thread husband = new Thread(() -> {
            account.withdraw(800);
        }, "Husband");
        
        // Wife tries to withdraw
        Thread wife = new Thread(() -> {
            account.withdraw(800);
        }, "Wife");
        
        husband.start();
        wife.start();
        
        husband.join();
        wife.join();
        
        System.out.println("Final balance: " + account.balance);
    }
}
```

### Simple Synchronization Rules
1. **Use `synchronized`** on methods that change shared data
2. **Keep synchronized blocks small** (don't lock too long)
3. **Better to use `synchronized(this)`** for blocks instead of whole methods

---

## Common Problems & Solutions

### Problem 1: Race Condition (Who gets there first?)
```java
// Two people trying to grab the last ticket
class TicketCounter {
    int tickets = 1;
    
    void buyTicket(String name) {
        if(tickets > 0) {
            // Between checking and buying, another might buy!
            tickets--;
            System.out.println(name + " got the last ticket!");
        } else {
            System.out.println(name + ": No tickets left");
        }
    }
}

// Solution: Add synchronized
synchronized void buyTicket(String name) {
    // Now only one can buy at a time
}
```

### Problem 2: Deadlock (Two people waiting for each other)
```java
// Person A has pen, needs paper
// Person B has paper, needs pen
// Both wait forever!

// Solution: Always acquire resources in same order
// Example: Always take pen first, then paper
```

### Problem 3: Producer-Consumer (Making and using things)
```java
// Simple example with a shared box
class MessageBox {
    private String message;
    private boolean hasMessage = false;
    
    synchronized void put(String msg) {
        while(hasMessage) {
            try { wait(); } catch(Exception e) {} // Wait if box full
        }
        message = msg;
        hasMessage = true;
        notify(); // Tell consumer there's a message
    }
    
    synchronized String get() {
        while(!hasMessage) {
            try { wait(); } catch(Exception e) {} // Wait if box empty
        }
        hasMessage = false;
        notify(); // Tell producer box is empty
        return message;
    }
}
```

---

## Simple Interview Questions

### Level 1: Basic Concepts

**Q1: What is a thread?**
**A:** A thread is like a separate path of execution in a program. If a program is a kitchen, threads are the chefs working together.

**Q2: How do you create a thread in Java?**
**A:** Two ways:
1. Extend Thread class: `class MyThread extends Thread`
2. Implement Runnable: `class MyTask implements Runnable`

**Q3: What's the difference between start() and run()?**
```java
Thread t = new Thread();
t.start(); // Creates new thread, calls run() in background
t.run();   // Just calls the method, no new thread
```

**Q4: What is synchronization?**
**A:** Making sure only one thread can access something at a time. Like a bathroom lock.

### Level 2: Practical Questions

**Q5: How can you make a thread wait for another?**
**A:** Use `join()` method:
```java
thread1.start();
thread1.join(); // Wait for thread1 to finish
thread2.start();
```

**Q6: What is thread priority?**
**A:** A hint to the OS about which thread is more important (1-10). But it's just a hint, not guaranteed!

**Q7: What's the volatile keyword?**
**A:** Makes sure all threads see the same value of a variable. Like a shared whiteboard everyone can see.

**Q8: What happens if multiple threads access same ArrayList?**
**A:** Might get errors! Use `Collections.synchronizedList()` or `CopyOnWriteArrayList`.

### Level 3: Problem Solving

**Q9: How to avoid deadlock?**
**A:**
1. Always acquire locks in same order
2. Use timeout on locks
3. Avoid nested synchronized blocks

**Q10: When would you use threads?**
**A:**
- Downloading files in background
- Processing multiple user requests
- Updating UI while doing calculations
- Reading multiple files at once

---

## Real Examples You See Daily

### Example 1: Web Browser
```java
// When you open a browser tab
class BrowserTab {
    public static void main(String[] args) {
        // Main thread: Show the window
        System.out.println("Browser window opened");
        
        // Thread 1: Load webpage
        Thread loadPage = new Thread(() -> {
            System.out.println("Loading HTML...");
            System.out.println("Loading CSS...");
            System.out.println("Loading images...");
        });
        
        // Thread 2: Run JavaScript
        Thread runJS = new Thread(() -> {
            System.out.println("Running JavaScript...");
        });
        
        // Thread 3: Check for updates
        Thread checkUpdates = new Thread(() -> {
            System.out.println("Checking for updates...");
        });
        
        loadPage.start();
        runJS.start();
        checkUpdates.start();
    }
}
```

### Example 2: Music Player
```java
class MusicPlayer {
    public static void main(String[] args) {
        System.out.println("Music Player Started");
        
        // Thread 1: Play music
        Thread playMusic = new Thread(() -> {
            for(int i = 1; i <= 10; i++) {
                System.out.println("Playing song part " + i);
                try { Thread.sleep(500); } catch(Exception e) {}
            }
        });
        
        // Thread 2: Download next song
        Thread downloadNext = new Thread(() -> {
            System.out.println("Downloading next song...");
            try { Thread.sleep(2000); } catch(Exception e) {}
            System.out.println("Next song ready!");
        });
        
        // Thread 3: Show lyrics
        Thread showLyrics = new Thread(() -> {
            System.out.println("Displaying lyrics...");
        });
        
        playMusic.start();
        downloadNext.start();
        showLyrics.start();
    }
}
```

### Example 3: Restaurant Order System
```java
class Restaurant {
    public static void main(String[] args) {
        System.out.println("Restaurant opens!");
        
        // Thread for taking orders
        Thread waiter = new Thread(() -> {
            for(int table = 1; table <= 5; table++) {
                System.out.println("Waiter taking order at table " + table);
                try { Thread.sleep(1000); } catch(Exception e) {}
            }
        });
        
        // Thread for cooking
        Thread chef = new Thread(() -> {
            for(int order = 1; order <= 5; order++) {
                System.out.println("Chef cooking order " + order);
                try { Thread.sleep(1500); } catch(Exception e) {}
            }
        });
        
        // Thread for cleaning
        Thread cleaner = new Thread(() -> {
            System.out.println("Cleaner cleaning tables...");
        });
        
        waiter.start();
        chef.start();
        cleaner.start();
    }
}
```

---

## Quick Reference Cheat Sheet

### Thread Creation
```java
// Method 1: Extend Thread
class MyThread extends Thread {
    public void run() {
        // Your code here
    }
}
MyThread t = new MyThread();
t.start();

// Method 2: Implement Runnable (Recommended)
class MyTask implements Runnable {
    public void run() {
        // Your code here
    }
}
Thread t = new Thread(new MyTask());
t.start();

// Method 3: Lambda (Java 8+)
Thread t = new Thread(() -> {
    // Your code here
});
t.start();
```

### Common Methods
```java
t.start();      // Start thread
t.join();       // Wait for thread to finish
t.sleep(1000);  // Sleep for 1 second
t.interrupt();  // Try to stop thread
t.isAlive();    // Check if still running
t.setName("MyThread");  // Give thread a name
t.getState();   // Get thread state
```

### Synchronization
```java
// On method
public synchronized void myMethod() { }

// On block
public void myMethod() {
    synchronized(this) {
        // Critical section
    }
}

// Wait/notify
synchronized(obj) {
    obj.wait();     // Wait for signal
    obj.notify();   // Signal one waiter
    obj.notifyAll();// Signal all waiters
}
```

---

## Common Mistakes to Avoid

1. **Calling run() instead of start()**
   ```java
   // WRONG: No new thread created!
   thread.run();
   
   // RIGHT: Creates new thread
   thread.start();
   ```

2. **Not handling InterruptedException**
   ```java
   // BAD
   Thread.sleep(1000);
   
   // GOOD
   try {
       Thread.sleep(1000);
   } catch(InterruptedException e) {
       Thread.currentThread().interrupt(); // Restore interrupt flag
   }
   ```

3. **Using too many threads**
   ```java
   // BAD: Creates 1000 threads
   for(int i = 0; i < 1000; i++) {
       new Thread(() -> {...}).start();
   }
   
   // GOOD: Use thread pool
   ExecutorService pool = Executors.newFixedThreadPool(10);
   ```

4. **Not synchronizing shared data**
   ```java
   // BAD: Two threads might overwrite each other
   class Counter {
       int count = 0;
       void increment() { count++; }
   }
   
   // GOOD: Use synchronization
   class Counter {
       int count = 0;
       synchronized void increment() { count++; }
   }
   ```

---

## Practice Exercises

### Exercise 1: Simple Counter
Create two threads that count from 1 to 5, printing their thread name with each count.

### Exercise 2: Download Simulator
Create a download thread that simulates downloading (use sleep) and a progress thread that shows percentage every second.

### Exercise 3: Bank Transaction
Create two threads trying to withdraw from same bank account. Make it thread-safe.

### Exercise 4: Traffic Light
Create three threads for Red, Yellow, Green lights that turn on in sequence.

---

## Where to Go Next

1. **Master the basics** from this guide
2. **Practice** with simple examples
3. **Learn ExecutorService** for thread pools
4. **Explore java.util.concurrent** package
5. **Study real projects** that use threads

Remember: **Threads are like learning to drive** - scary at first, but becomes natural with practice!

---

*Pro Tip: Start with simple examples, run them, modify them, break them, fix them. That's how you learn!*

## Table of Contents
1. [Introduction to Threads](#introduction-to-threads)
2. [Need for Multithreading](#need-for-multithreading)
3. [Main Thread](#main-thread)
4. [Creating Threads](#creating-threads)
5. [Multiple Threads Creation](#multiple-threads-creation)
6. [Thread vs Runnable](#thread-vs-runnable)
7. [Thread Life Cycle & States](#thread-life-cycle--states)
8. [join() and isAlive() Methods](#join-and-isalive-methods)
9. [interrupt() Method](#interrupt-method)
10. [Synchronization](#synchronization)
11. [Deadlock](#deadlock)
12. [Producer-Consumer Problem](#producer-consumer-problem)
13. [Interview Questions](#interview-questions)
14. [Real-World Applications](#real-world-applications)

---

## Introduction to Threads

### What is a Thread?
A thread is the smallest unit of execution within a process. Multiple threads can exist within a single process, sharing the process's memory space and resources while executing independently.

### Threads in Java
Java provides built-in support for multithreaded programming through:
- `java.lang.Thread` class
- `java.lang.Runnable` interface
- `java.util.concurrent` package (Java 5+)

**Key Characteristics:**
- Each thread has its own call stack
- Threads share heap memory
- Lightweight compared to processes
- Managed by JVM and OS thread scheduler

---

## Need for Multithreading

### Why Use Multithreading?

| Benefit | Description |
|---------|------------|
| **Improved Performance** | Better CPU utilization through parallel execution |
| **Responsiveness** | Applications remain responsive while performing background tasks |
| **Resource Sharing** | Threads share memory, reducing overhead compared to processes |
| **Economical** | Creating threads is cheaper than creating processes |
| **Scalability** | Better utilization of multi-core processors |

### Real-World Scenarios
1. **Web Servers**: Handling multiple client requests simultaneously
2. **GUI Applications**: Keeping UI responsive while performing computations
3. **Games**: Concurrent handling of graphics, physics, and AI
4. **Data Processing**: Parallel processing of large datasets

---

## Main Thread

### Default Thread in Java
When a Java program starts, the JVM creates the **main thread** which:
- Executes the `main()` method
- Has default name "main"
- Priority of 5 (NORM_PRIORITY)
- Can create additional threads

```java
public class MainThreadDemo {
    public static void main(String[] args) {
        // Getting main thread reference
        Thread mainThread = Thread.currentThread();
        
        System.out.println("Thread Name: " + mainThread.getName());
        System.out.println("Thread Priority: " + mainThread.getPriority());
        System.out.println("Thread ID: " + mainThread.getId());
        
        // Changing main thread name
        mainThread.setName("PrimaryThread");
        System.out.println("New Name: " + Thread.currentThread().getName());
    }
}
```

**Output:**
```
Thread Name: main
Thread Priority: 5
Thread ID: 1
New Name: PrimaryThread
```

---

## Creating Threads

### Two Ways to Create Threads

#### 1. Extending Thread Class
```java
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
            try {
                Thread.sleep(500); // Pause for 500ms
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.setName("Worker-1");
        t1.start(); // Start the thread
        
        // Main thread continues execution
        for (int i = 1; i <= 3; i++) {
            System.out.println("Main thread: " + i);
        }
    }
}
```

#### 2. Implementing Runnable Interface
```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }
}

public class RunnableExample {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread t1 = new Thread(myRunnable, "Runnable-Thread");
        t1.start();
    }
}
```

---

## Multiple Threads Creation

### Creating and Managing Multiple Threads
```java
class Task implements Runnable {
    private String taskName;
    
    public Task(String name) {
        this.taskName = name;
    }
    
    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(taskName + " - Iteration: " + i + 
                             " - Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep((long)(Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class MultipleThreadsDemo {
    public static void main(String[] args) {
        System.out.println("Main thread starting...");
        
        // Creating multiple threads
        Thread[] threads = new Thread[5];
        
        for (int i = 0; i < threads.length; i++) {
            Task task = new Task("Task-" + (i + 1));
            threads[i] = new Thread(task, "Thread-" + (i + 1));
            threads[i].start();
        }
        
        System.out.println("All threads started from main...");
    }
}
```

**Output (Sample):**
```
Main thread starting...
All threads started from main...
Task-1 - Iteration: 1 - Thread: Thread-1
Task-2 - Iteration: 1 - Thread: Thread-2
Task-3 - Iteration: 1 - Thread: Thread-3
Task-4 - Iteration: 1 - Thread: Thread-4
Task-5 - Iteration: 1 - Thread: Thread-5
... (interleaved execution continues)
```

---

## Thread vs Runnable

### Comparison Table

| Aspect | Extending Thread | Implementing Runnable |
|--------|-----------------|----------------------|
| **Inheritance** | Uses inheritance (can't extend other classes) | Uses interface (can extend other classes) |
| **Code Reusability** | Lower - tightly coupled to Thread class | Higher - can be passed to multiple threads |
| **Memory** | Each thread object creates separate object | Single Runnable can be shared among threads |
| **Thread Creation** | Direct: `MyThread t = new MyThread()` | Indirect: `Thread t = new Thread(runnable)` |
| **Best For** | Simple cases, when need to override Thread methods | Most cases, better design practice |

### Code Example: Runnable Advantages
```java
// Shared resource
class Counter {
    private int count = 0;
    
    public void increment() {
        count++;
    }
    
    public int getCount() {
        return count;
    }
}

// Runnable implementation
class CounterTask implements Runnable {
    private Counter counter;
    
    public CounterTask(Counter counter) {
        this.counter = counter;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}

public class RunnableAdvantage {
    public static void main(String[] args) throws InterruptedException {
        Counter sharedCounter = new Counter();
        
        // Multiple threads sharing same Runnable task
        Thread t1 = new Thread(new CounterTask(sharedCounter), "Thread-1");
        Thread t2 = new Thread(new CounterTask(sharedCounter), "Thread-2");
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        System.out.println("Final Count: " + sharedCounter.getCount());
        // Note: This has race condition - needs synchronization (covered later)
    }
}
```

---

## Thread Life Cycle & States

### Thread States Diagram
```
NEW → RUNNABLE → RUNNING → TERMINATED
            ↓        ↓
            BLOCKED/WAITING/TIMED_WAITING
```

### Detailed States

| State | Description | Trigger |
|-------|-------------|---------|
| **NEW** | Thread created but not started | `new Thread()` |
| **RUNNABLE** | Ready to run, waiting for CPU | `thread.start()` |
| **RUNNING** | Executing on CPU | OS Scheduler assigns CPU |
| **BLOCKED** | Waiting for monitor lock | Trying to enter synchronized block |
| **WAITING** | Waiting indefinitely | `object.wait()`, `thread.join()` |
| **TIMED_WAITING** | Waiting for specific time | `Thread.sleep(ms)`, `wait(timeout)` |
| **TERMINATED** | Execution completed | `run()` method completed |

### State Monitoring Example
```java
public class ThreadStatesDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("Thread state after start: " + 
                                 Thread.currentThread().getState());
                Thread.sleep(1000);
                
                synchronized (ThreadStatesDemo.class) {
                    ThreadStatesDemo.class.wait(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        System.out.println("Thread state after creation: " + thread.getState());
        
        thread.start();
        System.out.println("Thread state after start: " + thread.getState());
        
        Thread.sleep(100);
        System.out.println("Thread state during sleep: " + thread.getState());
        
        thread.join();
        System.out.println("Thread state after completion: " + thread.getState());
    }
}
```

---

## join() and isAlive() Methods

### join() Method
- Waits for thread to die (terminate)
- Can specify timeout period
- Throws `InterruptedException`

### isAlive() Method
- Returns `true` if thread is alive (not terminated)
- Thread is alive if it has been started and not died

### Code Example
```java
class WorkerThread extends Thread {
    private String workerName;
    private int duration;
    
    public WorkerThread(String name, int duration) {
        this.workerName = name;
        this.duration = duration;
    }
    
    @Override
    public void run() {
        System.out.println(workerName + " started working...");
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(workerName + " finished work.");
    }
}

public class JoinIsAliveDemo {
    public static void main(String[] args) throws InterruptedException {
        WorkerThread worker1 = new WorkerThread("Worker-1", 2000);
        WorkerThread worker2 = new WorkerThread("Worker-2", 3000);
        
        System.out.println("Starting workers...");
        worker1.start();
        worker2.start();
        
        // Check if threads are alive
        System.out.println("Worker1 alive: " + worker1.isAlive());
        System.out.println("Worker2 alive: " + worker2.isAlive());
        
        // Main thread waits for worker1 to complete
        System.out.println("Main waiting for Worker-1...");
        worker1.join();
        System.out.println("Worker-1 completed. Alive: " + worker1.isAlive());
        
        // Wait for worker2 with timeout
        System.out.println("Main waiting for Worker-2 (max 1.5s)...");
        worker2.join(1500);
        
        if (worker2.isAlive()) {
            System.out.println("Worker-2 still working. Interrupting...");
            worker2.interrupt();
        }
        
        // Final check
        Thread.sleep(500); // Give time for interruption
        System.out.println("Final - Worker2 alive: " + worker2.isAlive());
    }
}
```

---

## interrupt() Method

### Interrupting Threads
- Sets interrupt flag to `true`
- Doesn't stop thread immediately
- Thread should check interruption status and respond appropriately

### Methods for Interruption Handling
1. `interrupt()` - Sets interrupt flag
2. `isInterrupted()` - Checks interrupt status
3. `static interrupted()` - Checks and clears interrupt status

### Code Example
```java
class InterruptibleTask implements Runnable {
    @Override
    public void run() {
        try {
            // Method 1: Check interrupted flag
            for (int i = 1; i <= 10; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread interrupted! Cleaning up...");
                    return; // Exit gracefully
                }
                
                System.out.println("Processing item " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            // Method 2: Handle InterruptedException
            System.out.println("Thread was interrupted during sleep");
            // Restore interrupt status if needed
            Thread.currentThread().interrupt();
        }
    }
}

class NonResponsiveTask implements Runnable {
    @Override
    public void run() {
        // This thread doesn't check interruption
        while (true) {
            System.out.println("Non-responsive thread running...");
            // Heavy computation without sleep/waits
            // Can't be interrupted unless we add checks
        }
    }
}

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Interruptible Task Demo ===");
        Thread interruptibleThread = new Thread(new InterruptibleTask());
        interruptibleThread.start();
        
        Thread.sleep(1500);
        interruptibleThread.interrupt();
        interruptibleThread.join();
        
        System.out.println("\n=== Non-Responsive Task Demo ===");
        Thread nonResponsiveThread = new Thread(new NonResponsiveTask());
        nonResponsiveThread.start();
        
        Thread.sleep(100);
        nonResponsiveThread.interrupt();
        
        // This thread won't stop because it doesn't check interruption
        Thread.sleep(100);
        System.out.println("Non-responsive thread alive: " + 
                         nonResponsiveThread.isAlive());
        
        // Force stop (not recommended)
        nonResponsiveThread.stop();
    }
}
```

**Important Notes:**
- `Thread.stop()` is deprecated and unsafe
- Always design threads to respond to interruption
- Use interruption for cooperative cancellation

---

## Synchronization

### The Problem: Race Condition
```java
class Counter {
    private int count = 0;
    
    public void increment() {
        count++; // Not atomic: read → modify → write
    }
    
    public int getCount() {
        return count;
    }
}

public class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });
        
        t1.start();
        t2.start();
        
        t1.join();
        t2.join();
        
        System.out.println("Expected: 2000, Actual: " + counter.getCount());
        // Often prints less than 2000 due to race condition
    }
}
```

### Solutions for Synchronization

#### 1. Synchronized Method
```java
class SynchronizedCounter {
    private int count = 0;
    
    // Synchronized method - locks on 'this'
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}
```

#### 2. Synchronized Block
```java
class BlockCounter {
    private int count = 0;
    private final Object lock = new Object(); // Dedicated lock object
    
    public void increment() {
        synchronized (lock) { // More granular control
            count++;
        }
    }
    
    public int getCount() {
        synchronized (lock) {
            return count;
        }
    }
}
```

#### 3. Static Synchronization
```java
class StaticCounter {
    private static int count = 0;
    
    // Locks on Class object (StaticCounter.class)
    public static synchronized void increment() {
        count++;
    }
}
```

#### 4. ReentrantLock (java.util.concurrent)
```java
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

class LockCounter {
    private int count = 0;
    private final Lock lock = new ReentrantLock();
    
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock(); // Always unlock in finally block
        }
    }
}
```

### Complete Synchronization Example
```java
class BankAccount {
    private double balance;
    private final Object lock = new Object();
    
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    
    // Deposit with synchronization
    public void deposit(double amount) {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + 
                             " depositing " + amount);
            balance += amount;
            System.out.println("New balance after deposit: " + balance);
            lock.notifyAll(); // Notify waiting threads
        }
    }
    
    // Withdraw with synchronization and wait
    public void withdraw(double amount) throws InterruptedException {
        synchronized (lock) {
            while (balance < amount) {
                System.out.println(Thread.currentThread().getName() + 
                                 " waiting for funds...");
                lock.wait(); // Release lock and wait
            }
            System.out.println(Thread.currentThread().getName() + 
                             " withdrawing " + amount);
            balance -= amount;
            System.out.println("New balance after withdrawal: " + balance);
        }
    }
    
    public double getBalance() {
        synchronized (lock) {
            return balance;
        }
    }
}

public class SynchronizationDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);
        
        // Withdrawal thread
        Thread withdrawThread = new Thread(() -> {
            try {
                account.withdraw(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Withdraw-Thread");
        
        // Deposit threads
        Thread depositThread1 = new Thread(() -> {
            account.deposit(800);
        }, "Deposit-Thread-1");
        
        Thread depositThread2 = new Thread(() -> {
            account.deposit(700);
        }, "Deposit-Thread-2");
        
        withdrawThread.start();
        
        // Give withdraw thread time to start and wait
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        depositThread1.start();
        depositThread2.start();
    }
}
```

---

## Deadlock

### What is Deadlock?
A situation where two or more threads are blocked forever, each waiting for a resource held by another.

### Deadlock Conditions (Coffman Conditions)
1. **Mutual Exclusion**: Resources cannot be shared
2. **Hold and Wait**: Thread holds resource while waiting for another
3. **No Preemption**: Resources cannot be forcibly taken
4. **Circular Wait**: Circular chain of threads waiting for resources

### Deadlock Example
```java
class Resource {
    private final String name;
    
    public Resource(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public synchronized void use(Resource other) {
        System.out.println(Thread.currentThread().getName() + 
                         " using " + this.name + " and waiting for " + 
                         other.getName());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // This will cause deadlock
        other.release();
    }
    
    public synchronized void release() {
        System.out.println(Thread.currentThread().getName() + 
                         " released " + name);
    }
}

public class DeadlockDemo {
    public static void main(String[] args) {
        final Resource resourceA = new Resource("Resource-A");
        final Resource resourceB = new Resource("Resource-B");
        
        Thread thread1 = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println("Thread-1 locked Resource-A");
                try {
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Thread-1 waiting for Resource-B");
                synchronized (resourceB) {
                    System.out.println("Thread-1 locked both resources");
                }
            }
        }, "Thread-1");
        
        Thread thread2 = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println("Thread-2 locked Resource-B");
                try {
                    Thread.sleep(100); // Simulate work
                } catch (InterruptedException) {
                    e.printStackTrace();
                }
                
                System.out.println("Thread-2 waiting for Resource-A");
                synchronized (resourceA) {
                    System.out.println("Thread-2 locked both resources");
                }
            }
        }, "Thread-2");
        
        thread1.start();
        thread2.start();
        
        // Monitor for deadlock
        try {
            Thread.sleep(2000);
            System.out.println("\nChecking for deadlock...");
            
            // Check if threads are alive
            System.out.println("Thread-1 alive: " + thread1.isAlive());
            System.out.println("Thread-2 alive: " + thread2.isAlive());
            
            if (thread1.isAlive() && thread2.isAlive()) {
                System.out.println("DEADLOCK DETECTED!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

### Deadlock Prevention Strategies

#### 1. Lock Ordering
```java
class OrderedResource {
    private final String name;
    private final int id; // Used for ordering
    
    public OrderedResource(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    public void useWith(OrderedResource other) {
        OrderedResource first, second;
        
        // Always acquire locks in same order
        if (this.id < other.id) {
            first = this;
            second = other;
        } else {
            first = other;
            second = this;
        }
        
        synchronized (first) {
            synchronized (second) {
                System.out.println(Thread.currentThread().getName() + 
                                 " using " + this.name + " and " + other.name);
            }
        }
    }
}
```

#### 2. Lock Timeout
```java
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TimeoutResource {
    private final Lock lock = new ReentrantLock();
    private final String name;
    
    public TimeoutResource(String name) {
        this.name = name;
    }
    
    public boolean tryUseWith(TimeoutResource other, long timeout) 
            throws InterruptedException {
        long startTime = System.currentTimeMillis();
        
        while (true) {
            // Try to acquire first lock
            if (this.lock.tryLock(timeout, TimeUnit.MILLISECONDS)) {
                try {
                    // Try to acquire second lock
                    if (other.lock.tryLock(timeout, TimeUnit.MILLISECONDS)) {
                        try {
                            System.out.println(Thread.currentThread().getName() + 
                                             " acquired both locks");
                            return true; // Success
                        } finally {
                            other.lock.unlock();
                        }
                    }
                } finally {
                    this.lock.unlock();
                }
            }
            
            // Check timeout
            if (System.currentTimeMillis() - startTime >= timeout) {
                return false; // Timeout reached
            }
            
            Thread.sleep(100); // Wait before retry
        }
    }
}
```

#### 3. Deadlock Detection and Recovery
```java
// Using JMX to detect deadlocks
public class DeadlockDetector {
    public static void detectDeadlock() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        
        // Find deadlocked threads
        long[] deadlockedThreadIds = threadMXBean.findDeadlockedThreads();
        
        if (deadlockedThreadIds != null) {
            System.out.println("Deadlock detected!");
            
            ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(deadlockedThreadIds);
            
            for (ThreadInfo threadInfo : threadInfos) {
                System.out.println("Deadlocked Thread: " + threadInfo.getThreadName());
                System.out.println("Lock: " + threadInfo.getLockName());
                System.out.println("Owner: " + threadInfo.getLockOwnerName());
                
                // Get stack trace
                StackTraceElement[] stackTrace = threadInfo.getStackTrace();
                for (StackTraceElement element : stackTrace) {
                    System.out.println("\t" + element);
                }
            }
            
            // Recovery strategy
            // 1. Interrupt threads
            // 2. Use Thread.stop() (deprecated, last resort)
            // 3. System.exit() (drastic)
        }
    }
}
```

---

## Producer-Consumer Problem

### Problem Statement
- Producer produces data, Consumer consumes data
- Shared buffer with limited capacity
- Producer must wait if buffer is full
- Consumer must wait if buffer is empty
- Thread-safe access to buffer

### Solutions

#### 1. Using wait() and notify()
```java
import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private final Queue<Integer> buffer;
    private final int capacity;
    
    public SharedBuffer(int capacity) {
        this.buffer = new LinkedList<>();
        this.capacity = capacity;
    }
    
    public synchronized void produce(int item) throws InterruptedException {
        while (buffer.size() == capacity) {
            // Buffer full, wait for consumer
            System.out.println("Buffer full. Producer waiting...");
            wait();
        }
        
        buffer.add(item);
        System.out.println("Produced: " + item + 
                         " | Buffer size: " + buffer.size());
        
        // Notify consumer that item is available
        notifyAll();
    }
    
    public synchronized int consume() throws InterruptedException {
        while (buffer.isEmpty()) {
            // Buffer empty, wait for producer
            System.out.println("Buffer empty. Consumer waiting...");
            wait();
        }
        
        int item = buffer.poll();
        System.out.println("Consumed: " + item + 
                         " | Buffer size: " + buffer.size());
        
        // Notify producer that space is available
        notifyAll();
        
        return item;
    }
}

class Producer implements Runnable {
    private final SharedBuffer buffer;
    
    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        try {
            int item = 1;
            while (true) {
                buffer.produce(item++);
                Thread.sleep((long)(Math.random() * 1000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final SharedBuffer buffer;
    
    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                buffer.consume();
                Thread.sleep((long)(Math.random() * 1500));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProducerConsumerWaitNotify {
    public static void main(String[] args) throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer(5);
        
        Thread producer1 = new Thread(new Producer(buffer), "Producer-1");
        Thread producer2 = new Thread(new Producer(buffer), "Producer-2");
        Thread consumer1 = new Thread(new Consumer(buffer), "Consumer-1");
        Thread consumer2 = new Thread(new Consumer(buffer), "Consumer-2");
        
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        
        // Run for 10 seconds
        Thread.sleep(10000);
        
        // Interrupt threads
        producer1.interrupt();
        producer2.interrupt();
        consumer1.interrupt();
        consumer2.interrupt();
        
        producer1.join();
        producer2.join();
        consumer1.join();
        consumer2.join();
        
        System.out.println("Producer-Consumer simulation completed.");
    }
}
```

#### 2. Using BlockingQueue (Recommended)
```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class ProducerConsumerBlockingQueue {
    public static void main(String[] args) {
        // Thread-safe blocking queue
        BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);
        
        // Producer
        Thread producer = new Thread(() -> {
            try {
                int value = 0;
                while (true) {
                    buffer.put(value); // Blocks if queue is full
                    System.out.println("Produced: " + value + 
                                     " | Buffer size: " + buffer.size());
                    value++;
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer");
        
        // Consumer
        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    Integer value = buffer.take(); // Blocks if queue is empty
                    System.out.println("Consumed: " + value + 
                                     " | Buffer size: " + buffer.size());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer");
        
        producer.start();
        consumer.start();
    }
}
```

#### 3. Using Semaphores
```java
import java.util.concurrent.Semaphore;

class SemaphoreBuffer {
    private final int[] buffer;
    private int in = 0, out = 0;
    
    private final Semaphore emptySlots;
    private final Semaphore filledSlots;
    private final Semaphore mutex; // For mutual exclusion
    
    public SemaphoreBuffer(int capacity) {
        buffer = new int[capacity];
        emptySlots = new Semaphore(capacity);
        filledSlots = new Semaphore(0);
        mutex = new Semaphore(1);
    }
    
    public void produce(int item) throws InterruptedException {
        emptySlots.acquire(); // Wait for empty slot
        mutex.acquire(); // Enter critical section
        
        buffer[in] = item;
        in = (in + 1) % buffer.length;
        System.out.println("Produced: " + item);
        
        mutex.release(); // Exit critical section
        filledSlots.release(); // Signal that slot is filled
    }
    
    public int consume() throws InterruptedException {
        filledSlots.acquire(); // Wait for filled slot
        mutex.acquire(); // Enter critical section
        
        int item = buffer[out];
        out = (out + 1) % buffer.length;
        System.out.println("Consumed: " + item);
        
        mutex.release(); // Exit critical section
        emptySlots.release(); // Signal that slot is empty
        
        return item;
    }
}
```

---

## Interview Questions

### Basic Level
1. **What is a thread? How is it different from a process?**
    - Thread: Lightweight, shares memory, within same process
    - Process: Heavyweight, separate memory space

2. **How can you create a thread in Java?**
    - Extend Thread class
    - Implement Runnable interface
    - Implement Callable interface (returns result)
    - Use ExecutorService

3. **What is the difference between start() and run() methods?**
    - `start()`: Creates new thread, calls `run()` asynchronously
    - `run()`: Executes in current thread, no new thread

4. **What is thread priority?**
    - Range: 1 (MIN) to 10 (MAX), default 5 (NORM)
    - Hint to scheduler, not guaranteed

### Intermediate Level
1. **Explain thread states with example.**
    - NEW, RUNNABLE, RUNNING, BLOCKED, WAITING, TIMED_WAITING, TERMINATED

2. **What is synchronization? Why is it needed?**
    - Prevents race conditions
    - Ensures thread-safe access to shared resources

3. **Difference between synchronized method and synchronized block?**
    - Method: Locks on 'this' object
    - Block: Can lock on any object, more granular control

4. **What is deadlock? How to prevent it?**
    - Circular wait for resources
    - Prevention: Lock ordering, timeout, avoid nested locks

### Advanced Level
1. **What is thread starvation?**
    - Thread unable to gain regular access to resources
    - Often due to priority inversion or greedy threads

2. **Explain volatile keyword.**
    - Ensures visibility of changes across threads
    - Prevents caching of variables in thread-local memory

3. **Compare synchronized vs Lock interface.**
   ```java
   // Synchronized: implicit, automatic release, no timeout
   // Lock: explicit, manual control, tryLock with timeout
   ```

4. **What is ThreadLocal?**
    - Provides thread-local variables
    - Each thread has its own copy

5. **Explain executor framework.**
    - Thread pool management
    - `ExecutorService`, `ThreadPoolExecutor`, `ScheduledExecutorService`

### Coding Problems
1. **Print numbers 1-10 using 2 threads (odd-even)**
```java
class OddEvenPrinter {
    private final int MAX = 10;
    private boolean isOddTurn = true;
    
    public synchronized void printOdd() throws InterruptedException {
        for (int i = 1; i <= MAX; i += 2) {
            while (!isOddTurn) {
                wait();
            }
            System.out.println("Odd: " + i);
            isOddTurn = false;
            notify();
        }
    }
    
    public synchronized void printEven() throws InterruptedException {
        for (int i = 2; i <= MAX; i += 2) {
            while (isOddTurn) {
                wait();
            }
            System.out.println("Even: " + i);
            isOddTurn = true;
            notify();
        }
    }
}
```

2. **Implement custom ThreadPool**
```java
class CustomThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final List<WorkerThread> workers;
    
    class WorkerThread extends Thread {
        public void run() {
            while (!isInterrupted()) {
                try {
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    interrupt();
                }
            }
        }
    }
    
    public CustomThreadPool(int poolSize) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new ArrayList<>();
        
        for (int i = 0; i < poolSize; i++) {
            WorkerThread worker = new WorkerThread();
            worker.start();
            workers.add(worker);
        }
    }
    
    public void execute(Runnable task) {
        taskQueue.offer(task);
    }
    
    public void shutdown() {
        for (WorkerThread worker : workers) {
            worker.interrupt();
        }
    }
}
```

---

## Real-World Applications

### 1. Web Servers (Tomcat, Jetty)
- Thread pool handles HTTP requests
- Each request processed in separate thread
- Connection pooling for databases

### 2. Database Systems
- Connection pooling
- Query execution in parallel
- Transaction management

### 3. GUI Applications (Swing, JavaFX)
- Event Dispatch Thread (EDT) for UI updates
- Background threads for computations
- Worker threads for file I/O

### 4. Financial Trading Systems
- Real-time market data processing
- Order matching engines
- Risk calculation in parallel

### 5. Big Data Processing (Hadoop, Spark)
- MapReduce operations
- Parallel data processing
- Distributed computing

### 6. Gaming Engines
- Physics calculations
- AI processing
- Graphics rendering
- Network synchronization

### 7. Mobile Applications (Android)
- AsyncTask for background operations
- Services for long-running tasks
- Handler/Looper for thread communication

### 8. Scientific Computing
- Parallel algorithms
- Matrix operations
- Simulation modeling

### Best Practices
1. **Use thread pools** instead of creating threads directly
2. **Prefer Runnable** over Thread for better design
3. **Use concurrent collections** (ConcurrentHashMap, CopyOnWriteArrayList)
4. **Minimize synchronization scope**
5. **Use volatile for flags**, synchronized/locks for compound actions
6. **Always release resources** in finally blocks
7. **Avoid thread starvation** with fair locks
8. **Use higher-level abstractions** (ExecutorService, CompletableFuture)

### Performance Considerations
- **Context switching overhead**: Too many threads can degrade performance
- **Memory overhead**: Each thread has stack memory (~1MB default)
- **CPU utilization**: Optimal threads ≈ number of cores for CPU-bound tasks
- **I/O operations**: More threads beneficial for I/O-bound tasks

---

## Summary Table: Threading Concepts

| Concept | Description | Key Methods/Classes |
|---------|-------------|-------------------|
| **Thread Creation** | Two ways: extends Thread, implements Runnable | `Thread.start()`, `Thread.run()` |
| **Synchronization** | Prevents concurrent access issues | `synchronized`, `Lock`, `Semaphore` |
| **Thread States** | Lifecycle of thread | `NEW`, `RUNNABLE`, `BLOCKED`, etc. |
| **Thread Communication** | Inter-thread coordination | `wait()`, `notify()`, `notifyAll()` |
| **Thread Pool** | Reusable thread management | `ExecutorService`, `ThreadPoolExecutor` |
| **Deadlock** | Circular wait for resources | Prevention: ordering, timeout |
| **Producer-Consumer** | Classic synchronization problem | `BlockingQueue`, `wait/notify` |

## References & Further Reading
1. Java Concurrency in Practice - Brian Goetz
2. Oracle Java Documentation: java.util.concurrent
3. Java Language Specification - Chapter 17: Threads and Locks
4. Effective Java - Joshua Bloch (Concurrency Items)

---

*Last Updated: October 2023*  
*Author: Java Concurrency Expert*  
*Version: 2.0*