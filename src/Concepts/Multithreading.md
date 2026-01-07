Below is **the same content**, **not changed**, only **formatted properly** with **correct syntax, headings, spacing, and code blocks** for clean notes and easy reading.

---

# 🧵 Multithreading in Java – Detailed Notes

## 📚 Table of Contents

1. [Threads in Java](#threads-in-java)
2. [Need for Multithreading](#need-for-multithreading)
3. [Main Thread](#main-thread)
4. [Creating Threads](#creating-threads)
5. [Multiple Threads Creation](#multiple-threads-creation)
6. [Thread vs Runnable](#thread-vs-runnable)
7. [Thread Life Cycle & States](#thread-life-cycle--states)
8. [join() and isAlive()](#join-and-isalive)
9. [interrupt() Method](#interrupt-method)
10. [Synchronization](#synchronization)
11. [Deadlock](#deadlock)
12. [Producer–Consumer Problem](#producerconsumer-problem)

---

## Threads in Java

A **thread** is a lightweight sub-process that executes independently.

✔ Java supports **multithreading** using `Thread` class and `Runnable` interface.

---

## Need for Multithreading

* Better CPU utilization
* Parallel execution
* Responsive applications
* Used in servers, games, UI apps

**Example:** Web server handling multiple clients

---

## Main Thread

* JVM creates a **main thread** automatically
* Execution starts from `main()`

```java
public class Test {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
    }
}
```

---

## Creating Threads

### 1️⃣ Extending Thread class

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}

public class Test {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
```

⚠ Always call `start()`, not `run()`

---

### 2️⃣ Implementing Runnable Interface

```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Runnable thread");
    }
}

public class Test {
    public static void main(String[] args) {
        Thread t = new Thread(new MyTask());
        t.start();
    }
}
```

✔ Preferred approach

---

## Multiple Threads Creation

```java
class Task extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

public class Test {
    public static void main(String[] args) {
        Task t1 = new Task();
        Task t2 = new Task();
        t1.start();
        t2.start();
    }
}
```

---

## Thread vs Runnable

| Feature              | Thread         | Runnable            |
| -------------------- | -------------- | ------------------- |
| Inheritance          | Extends Thread | Implements Runnable |
| Multiple inheritance | ❌              | ✔                   |
| Object separation    | ❌              | ✔                   |
| Preferred            | ❌              | ✔                   |

---

## Thread Life Cycle & States

```
NEW → RUNNABLE → RUNNING → TERMINATED
            ↓
     BLOCKED / WAITING / TIMED_WAITING
```

### States

* NEW
* RUNNABLE
* BLOCKED
* WAITING
* TIMED_WAITING
* TERMINATED

---

## join() and isAlive()

### 1. join() Method

👉 What does `join()` do?

* Makes the current thread wait until another thread finishes execution
* Mostly used when one thread depends on another

📌 Simple Meaning

> “Main thread, wait until this thread completes.”

### ✅ Example

```java
class JoinExample {
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            System.out.println("Thread started");
            try {
                Thread.sleep(2000); // Simulate work
            } catch (Exception e) {
            }
            System.out.println("Thread finished");
        });

        t.start();
        t.join();   // Main thread waits here

        System.out.println("Main thread resumes");
    }
}
```

🧠 Output

```
Thread started
Thread finished
Main thread resumes
```

✔ Main thread waits until `t` completes

---

### 2. isAlive() Method

👉 What does `isAlive()` do?

* Checks whether a thread is still running
* Returns `true` or `false`

### ✅ Example

```java
class IsAliveExample {
    public static void main(String[] args) throws Exception {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        });

        t.start();
        System.out.println(t.isAlive()); // true

        t.join();
        System.out.println(t.isAlive()); // false
    }
}
```

✔ true → Thread running
✔ false → Thread finished

---

## interrupt() Method

👉 What does `interrupt()` do?

* Used to stop or interrupt a thread that is:

    * sleeping
    * waiting
    * blocked

⚠️ It does not stop the thread forcibly, it sends an interrupt signal

### ✅ Example

```java
class InterruptExample {
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        });

        t.start();
        t.interrupt();
    }
}
```

🧠 Output

```
Thread interrupted
```

✔ Thread wakes up from sleep and handles interruption

---

## Synchronization

👉 Why Synchronization?

* Used to prevent data inconsistency
* Ensures only one thread accesses shared data at a time

🚨 Problem: Race Condition
Multiple threads updating the same data simultaneously.
``` java
class Bank {

private int balance = 1000;

    // synchronized method ensures one thread at a time
    public synchronized void withdraw(int amount) {
        if(balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
            balance -= amount;

            try {
                Thread.sleep(1000); // simulate delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " completed. Remaining balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance!");
        }
    }
}

public class Test {
public static void main(String[] args) {
Bank bank = new Bank();

        Runnable r1 = () -> bank.withdraw(700);
        Runnable r2 = () -> bank.withdraw(700);

        Thread t1 = new Thread(r1, "Thread-A");
        Thread t2 = new Thread(r2, "Thread-B");

        t1.start();
        t2.start();
    }
}
```

### Synchronized Method

Locks the entire method

```java
class Counter {
    int count = 0;

    synchronized void increment() {
        count++;
    }
}
```

✔ Only one thread can execute `increment()` at a time

---

### Synchronized Block

Locks only a specific block (more efficient)

```java
class Counter {
    int count = 0;

    void increment() {
        synchronized(this) {
            count++;
        }
    }
}
```

✔ Better performance
✔ Used when only part of method needs locking

### ✅ Synchronization Benefits

✔ Prevents race condition
✔ Ensures thread safety

⚠️ But may reduce performance

---

## Deadlock

👉 What is Deadlock?

Occurs when two or more threads wait forever for each other’s resources.

🔁 Example Scenario

```
Thread 1 → holds Lock A → waiting for Lock B
Thread 2 → holds Lock B → waiting for Lock A
```

✔ Program freezes
✔ Threads never execute again

### 🛑 Deadlock Prevention

✔ Avoid nested locks
✔ Maintain lock ordering
✔ Use timeout-based locks

---

## Producer–Consumer Problem

👉 What is it?

A classic synchronization problem where:

* Producer produces data
* Consumer consumes data
* Both share a common buffer

---

### Using `wait()` and `notify()`

**Producer**

```java
synchronized void produce() throws InterruptedException {
    System.out.println("Producing...");
    wait();   // Wait for consumer
    notify(); // Notify consumer
}
```

**Consumer**

```java
synchronized void consume() throws InterruptedException {
    System.out.println("Consuming...");
    notify(); // Notify producer
    wait();   // Wait for producer
}
```

🧠 Key Rules
✔ `wait()` releases the lock
✔ `notify()` wakes up waiting thread
✔ Must be used inside synchronized block/method

---
