# 🧵 Multithreading in Java – Detailed Notes

## Table of Contents

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

### States:

* NEW
* RUNNABLE
* BLOCKED
* WAITING
* TIMED_WAITING
* TERMINATED

---

## join() and isAlive()

### join()

Waits for thread to finish.

```java
Thread t = new Thread(() -> System.out.println("Thread"));
t.start();
t.join();
```

---

### isAlive()

Checks if thread is alive.

```java
System.out.println(t.isAlive());
```

---

## interrupt() Method

Used to interrupt sleeping or waiting thread.

```java
Thread t = new Thread(() -> {
    try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        System.out.println("Thread interrupted");
    }
});

t.start();
t.interrupt();
```

---

## Synchronization

Used to prevent **data inconsistency**.

### Synchronized Method

```java
synchronized void increment() {
    count++;
}
```

### Synchronized Block

```java
synchronized(this) {
    count++;
}
```

✔ Prevents race condition

---

## Deadlock

Occurs when threads wait indefinitely for each other.

```java
Thread 1 → Lock A → waiting for Lock B
Thread 2 → Lock B → waiting for Lock A
```

### Prevention:

* Avoid nested locks
* Use lock ordering

---

## Producer–Consumer Problem

Classic synchronization problem.

### Concept:

* Producer produces data
* Consumer consumes data
* Shared buffer

### Using wait() & notify()

```java
synchronized void produce() throws InterruptedException {
    wait();
    notify();
}
```

```java
synchronized void consume() throws InterruptedException {
    notify();
    wait();
}
```

---

## Interview Traps ⚠

* start() vs run()
* Synchronization causes performance hit
* Deadlock is hard to debug

---

## Summary

| Concept         | Key Point            |
| --------------- | -------------------- |
| Thread          | Lightweight process  |
| Runnable        | Preferred approach   |
| join()          | Wait for completion  |
| interrupt       | Stop sleeping thread |
| Synchronization | Thread safety        |
| Deadlock        | Infinite wait        |

---

## Quick Revision Code

```java
new Thread(() -> System.out.println("Hello Thread")).start();
```

---

✅ **Multithreading is a CORE backend + interview topic**
