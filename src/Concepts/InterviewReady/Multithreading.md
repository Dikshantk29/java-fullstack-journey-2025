# Java Multithreading
## Quick Revision Notes for Interviews

---

## 1. Core Concepts

### What is a Thread?

**Definition:** A thread is the smallest unit of execution within a process. Multiple threads can run concurrently within the same process, sharing memory but executing independently.

| Aspect | Thread | Process |
|--------|--------|---------|
| Memory | Shares memory | Separate memory |
| Weight | Lightweight | Heavyweight |
| Creation Cost | Low | High |
| Communication | Easy (shared memory) | Complex (IPC) |

### Why Multithreading?

- **Better CPU utilization** - Run tasks in parallel
- **Improved responsiveness** - UI remains responsive during background tasks
- **Resource sharing** - Threads share process memory efficiently
- **Faster execution** - Parallel processing on multi-core systems

---

## 2. Creating Threads

### Method 1: Extending Thread Class

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}

MyThread t = new MyThread();
t.start();  // Start the thread
```

**Key Points:**
- Must override `run()` method
- Call `start()`, not `run()` - `start()` creates new thread
- Cannot extend other classes (single inheritance)

### Method 2: Implementing Runnable (Recommended)

```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Task running");
    }
}

Thread t = new Thread(new MyTask());
t.start();
```

**Advantages:**
- Can extend other classes
- Better separation of task and thread
- Same Runnable can be shared by multiple threads
- Preferred approach in most cases

### Method 3: Lambda Expression (Java 8+)

```java
Thread t = new Thread(() -> {
    System.out.println("Lambda thread");
});
t.start();
```

---

## 3. Thread Lifecycle & States

| State | Description | How to Reach |
|-------|-------------|--------------|
| **NEW** | Thread created but not started | `new Thread()` |
| **RUNNABLE** | Ready to run, waiting for CPU | `t.start()` |
| **BLOCKED** | Waiting for monitor lock | Trying to enter synchronized block |
| **WAITING** | Waiting indefinitely | `wait()`, `join()` |
| **TIMED_WAITING** | Waiting for specific time | `sleep(ms)`, `wait(ms)` |
| **TERMINATED** | Thread finished execution | `run()` method completes |

**State Transition:**
```
NEW → RUNNABLE → RUNNING → BLOCKED/WAITING/TIMED_WAITING → TERMINATED
```

---

## 4. Important Thread Methods

### start() vs run()

| `start()` | `run()` |
|-----------|---------|
| Creates new thread | No new thread created |
| Calls run() internally | Executes like normal method |
| Can be called only once | Can be called multiple times |

### sleep()

```java
Thread.sleep(1000);  // Sleep for 1 second
```

- Pauses current thread for specified milliseconds
- Does NOT release lock if inside synchronized block
- Throws `InterruptedException`

### join()

```java
thread.join();  // Wait for thread to complete
```

- Makes current thread wait until target thread dies
- Can specify timeout: `join(1000)`
- Used to ensure thread completion before proceeding

### interrupt()

```java
thread.interrupt();  // Request thread to stop
```

- Sets interrupt flag to true
- If thread is sleeping/waiting, throws `InterruptedException`
- Thread should check interrupt status and respond

### isAlive()

```java
boolean status = thread.isAlive();
```

- Returns true if thread has been started and not yet died
- Returns false for NEW and TERMINATED states

---

## 5. Synchronization

### Why Synchronization?

**Problem:** Multiple threads accessing shared data can cause race conditions and data inconsistency.

**Solution:** Use `synchronized` keyword to ensure only one thread accesses critical section at a time.

### Method 1: Synchronized Method

```java
public synchronized void increment() {
    count++;  // Thread-safe
}
```

- Locks entire method
- Lock is on the object (this)

### Method 2: Synchronized Block

```java
public void increment() {
    synchronized(this) {
        count++;  // Thread-safe
    }
}
```

- More granular control
- Can lock on any object
- Better performance (smaller critical section)

### Method 3: Static Synchronization

```java
public static synchronized void method() {
    // Lock on Class object
}
```

- Lock is on the Class object, not instance
- Used for synchronizing static methods

---

## 6. Inter-thread Communication

| Method | Description |
|--------|-------------|
| `wait()` | Releases lock and waits until `notify()` or `notifyAll()` is called. Must be inside synchronized block. |
| `notify()` | Wakes up one waiting thread. Must be inside synchronized block. |
| `notifyAll()` | Wakes up all waiting threads. Must be inside synchronized block. |

### Producer-Consumer Example

```java
class SharedResource {
    private int data;
    private boolean hasData = false;

    public synchronized void produce(int value) {
        while(hasData) {
            wait();  // Wait if data already present
        }
        data = value;
        hasData = true;
        notify();  // Notify consumer
    }

    public synchronized int consume() {
        while(!hasData) {
            wait();  // Wait if no data
        }
        hasData = false;
        notify();  // Notify producer
        return data;
    }
}
```

---

## 7. Deadlock

**Definition:** A situation where two or more threads are blocked forever, waiting for each other to release resources.

### Conditions for Deadlock (All 4 Required)

1. **Mutual Exclusion:** Resource cannot be shared
2. **Hold and Wait:** Thread holds resource while waiting for another
3. **No Preemption:** Resource cannot be forcibly taken
4. **Circular Wait:** Circular chain of threads waiting for resources

### Deadlock Example

```java
// Thread 1
synchronized(resource1) {
    synchronized(resource2) {
        // Do work
    }
}

// Thread 2
synchronized(resource2) {
    synchronized(resource1) {
        // Do work
    }
}
// Deadlock! Each waits for the other's resource
```

### Deadlock Prevention

- **Lock Ordering:** Always acquire locks in same order
- **Lock Timeout:** Use `tryLock()` with timeout
- **Avoid Nested Locks:** Minimize synchronized block nesting
- **Deadlock Detection:** Use `ThreadMXBean` to detect deadlocks

---

## 8. Thread Priority

```java
thread.setPriority(Thread.MAX_PRIORITY);  // 10
thread.setPriority(Thread.NORM_PRIORITY); // 5 (default)
thread.setPriority(Thread.MIN_PRIORITY);  // 1
```

- Range: 1 (lowest) to 10 (highest)
- Default priority: 5
- Hint to scheduler, not guaranteed
- Child thread inherits parent's priority

---

## 9. Daemon Threads

```java
thread.setDaemon(true);  // Set before start()
thread.start();
```

- Background service threads (GC, finalizer)
- JVM exits when only daemon threads are running
- Must be set before `start()`, else `IllegalThreadStateException`
- User threads are non-daemon by default

---

## 10. Volatile Keyword

```java
private volatile boolean flag = false;
```

- Ensures visibility of changes across threads
- Prevents thread-local caching of variable
- Reads/writes directly from/to main memory
- Does NOT provide atomicity (use synchronized for that)
- Good for flags, not for compound operations like `count++`

---

## 11. ThreadLocal

```java
ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
threadLocal.set(100);
Integer value = threadLocal.get();
```

- Provides thread-local variables
- Each thread has its own isolated copy
- Useful for user session, transaction context
- Must call `remove()` to prevent memory leaks

---

## 12. Executor Framework (Advanced)

### Why Use Executor?

- Thread pool management
- Reuses threads, reduces overhead
- Better resource management
- Supports task scheduling

### Common Executors

```java
// Fixed thread pool
ExecutorService executor = Executors.newFixedThreadPool(5);

// Cached thread pool (creates threads as needed)
ExecutorService executor = Executors.newCachedThreadPool();

// Single thread executor
ExecutorService executor = Executors.newSingleThreadExecutor();

// Scheduled executor
ScheduledExecutorService executor = 
    Executors.newScheduledThreadPool(3);
```

**Usage:**

```java
// Submit task
executor.submit(() -> {
    System.out.println("Task executed");
});

// Shutdown executor
executor.shutdown();
```

---

## 13. Common Interview Questions

### Beginner Level

**Q1. What is a thread?**

A thread is the smallest unit of execution within a process. Multiple threads share the same memory space but execute independently.

**Q2. Difference between process and thread?**

Process has separate memory, threads share memory. Threads are lightweight, processes are heavyweight.

**Q3. How to create a thread in Java?**

Two ways: (1) Extend Thread class, (2) Implement Runnable interface (recommended).

**Q4. Difference between start() and run()?**

`start()` creates new thread and calls `run()`. Calling `run()` directly executes in same thread like normal method.

**Q5. What is synchronization?**

Mechanism to control access of multiple threads to shared resources, preventing race conditions.

**Q6. Can we start a thread twice?**

No. Calling `start()` twice throws `IllegalThreadStateException`.

---

### Intermediate Level

**Q7. Explain thread lifecycle.**

NEW → RUNNABLE → RUNNING → BLOCKED/WAITING/TIMED_WAITING → TERMINATED

**Q8. What is deadlock? How to prevent it?**

Deadlock occurs when threads wait for each other's resources forever. Prevent by: lock ordering, timeouts, avoiding nested locks.

**Q9. Difference between wait() and sleep()?**

| `wait()` | `sleep()` |
|----------|-----------|
| Releases lock | Does not release lock |
| Object class method | Thread class method |
| Must be in synchronized | Can be called anywhere |
| Woken by notify() | Wakes after time |

**Q10. What is volatile keyword?**

Ensures visibility of changes across threads. Variable is read from/written to main memory, not cached.

**Q11. Why use Runnable over Thread?**

Runnable allows extending other classes, better design (separation of task and thread), same task can be shared by multiple threads.

**Q12. What is thread priority?**

Hint to scheduler about importance (1-10). Not guaranteed, depends on OS scheduler.

---

### Advanced Level

**Q13. Explain Producer-Consumer problem.**

Classic synchronization problem where producer produces data and consumer consumes it using shared buffer. Use `wait()` and `notify()` for coordination.

**Q14. What is ThreadLocal?**

Provides thread-local variables where each thread has its own isolated copy. Used for user sessions, transaction contexts.

**Q15. Difference between synchronized and Lock?**

`synchronized` is implicit, automatic release. `Lock` is explicit, needs manual `unlock()`, provides `tryLock()` with timeout, more flexible.

**Q16. What is thread pool? Benefits?**

Pre-created collection of threads. Benefits: reuses threads, reduces creation overhead, better resource management, task queue management.

**Q17. What is daemon thread?**

Background service thread (like GC). JVM exits when only daemon threads running. Must set before `start()`.

**Q18. Can we synchronize static method?**

Yes. Lock is on Class object, not instance. Prevents concurrent access to static method by multiple threads.

**Q19. What is race condition?**

When multiple threads access shared data and try to change it simultaneously, causing incorrect results. Fixed by synchronization.

**Q20. Explain CountDownLatch.**

Synchronization aid that allows one or more threads to wait until operations in other threads complete. Count decrements on each completion.

---

## 14. Common Coding Problems

### Problem 1: Print Even-Odd Using 2 Threads

```java
class EvenOdd {
    private int count = 1;
    private int max = 10;

    public synchronized void printEven() {
        while(count <= max) {
            while(count % 2 != 0) {
                try { wait(); } catch(Exception e) {}
            }
            System.out.println("Even: " + count);
            count++;
            notify();
        }
    }

    public synchronized void printOdd() {
        while(count <= max) {
            while(count % 2 == 0) {
                try { wait(); } catch(Exception e) {}
            }
            System.out.println("Odd: " + count);
            count++;
            notify();
        }
    }
}
```

### Problem 2: Thread-Safe Counter

```java
class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

// Using AtomicInteger (better)
class Counter {
    private AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
```

---

## 15. Best Practices

- **Prefer Runnable** over extending Thread class
- **Use Executor framework** instead of creating threads manually
- **Minimize synchronized scope** - lock only critical section
- **Use concurrent collections** - ConcurrentHashMap, CopyOnWriteArrayList
- **Avoid nested locks** to prevent deadlock
- **Always release locks** in finally block
- **Use volatile for flags**, synchronized for compound operations
- **Handle InterruptedException** properly
- **Name your threads** for easier debugging
- **Document thread-safety** in javadoc

---

## 16. Quick Reference Card

| Task | Code |
|------|------|
| Create thread | `new Thread(() -> {...}).start();` |
| Sleep | `Thread.sleep(1000);` |
| Wait for thread | `thread.join();` |
| Synchronized method | `synchronized void method() {}` |
| Synchronized block | `synchronized(obj) {...}` |
| Thread pool | `Executors.newFixedThreadPool(5);` |
| Interrupt | `thread.interrupt();` |
| Wait/Notify | `obj.wait(); obj.notify();` |
| Get thread state | `thread.getState();` |
| Check if alive | `thread.isAlive();` |

---

## 17. Important Concurrent Classes

### AtomicInteger/AtomicLong

```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();  // Thread-safe increment
count.getAndIncrement();  // Get then increment
```

### ConcurrentHashMap

```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.put("key", 1);  // Thread-safe
```

### CopyOnWriteArrayList

```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
list.add("item");  // Thread-safe for read-heavy scenarios
```

### BlockingQueue

```java
BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);
queue.put(item);  // Blocks if full
Integer item = queue.take();  // Blocks if empty
```

### CountDownLatch

```java
CountDownLatch latch = new CountDownLatch(3);
latch.countDown();  // Decrement count
latch.await();  // Wait until count reaches zero
```

### CyclicBarrier

```java
CyclicBarrier barrier = new CyclicBarrier(3);
barrier.await();  // Wait for all threads to reach barrier
```

### Semaphore

```java
Semaphore semaphore = new Semaphore(3);
semaphore.acquire();  // Acquire permit
semaphore.release();  // Release permit
```

---

## 18. Common Thread Issues

### 1. Race Condition

**Problem:** Multiple threads modify shared data concurrently
**Solution:** Use synchronization or atomic classes

```java
// Problem
count++;  // Not atomic

// Solution 1: Synchronized
synchronized(this) { count++; }

// Solution 2: Atomic
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();
```

### 2. Deadlock

**Problem:** Circular wait for resources
**Solution:** Lock ordering, timeouts

```java
// Always acquire locks in same order
synchronized(lockA) {
    synchronized(lockB) {
        // Work
    }
}
```

### 3. Starvation

**Problem:** Thread never gets CPU time
**Solution:** Fair scheduling, avoid high priority differences

### 4. Livelock

**Problem:** Threads keep changing state but make no progress
**Solution:** Add randomness to retry logic

### 5. Memory Consistency Errors

**Problem:** Different threads see inconsistent values
**Solution:** Use volatile or synchronization

---

## 19. Thread Safety Levels

1. **Immutable** - Objects that cannot be modified (String, Integer)
2. **Thread-safe** - Can be used by multiple threads without synchronization
3. **Conditionally thread-safe** - Thread-safe for some operations only
4. **Not thread-safe** - Must be synchronized externally (ArrayList, HashMap)

---

## 20. Performance Tips

- Use thread pools instead of creating new threads
- Minimize synchronized scope
- Prefer `java.util.concurrent` classes
- Use concurrent collections over synchronized collections
- Avoid synchronization on hot paths when possible
- Use `volatile` for simple flags
- Profile before optimizing
- Consider lock-free algorithms for high contention

---

## Key Takeaways

✅ **Thread Creation:** Prefer Runnable over Thread  
✅ **Synchronization:** Use for protecting shared mutable state  
✅ **wait() vs sleep():** wait() releases lock, sleep() doesn't  
✅ **Deadlock:** Prevent with lock ordering  
✅ **Executor Framework:** Use for thread pool management  
✅ **volatile:** Good for visibility, not atomicity  
✅ **Thread-safe Collections:** Use ConcurrentHashMap, CopyOnWriteArrayList  
✅ **Best Practice:** Document thread-safety assumptions

---

**End of Java Multithreading Notes**  
*For quick revision and interview preparation*