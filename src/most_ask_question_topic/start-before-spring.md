# 🎯 Java Interview Priority Guide with Answers

## ⭐⭐⭐ MOST IMPORTANT (Must Master - Asked in 90%+ interviews)

---csf

### 💻 **Java Fundamentals**

#### **Q: What is WORA (Write Once Run Anywhere)?**
**A:** WORA means Java code compiled once can run on any platform without recompilation.

**How it works:**
1. `.java` source file → Java Compiler → `.class` bytecode
2. Bytecode is platform-independent
3. JVM (platform-specific) interprets/compiles bytecode to native machine code
4. Same `.class` file runs on Windows, Linux, Mac (each has its own JVM)

**Why portable?** Bytecode is universal; JVM handles platform differences.

---

#### **Q: Explain Type Casting. What is Truncation?**
**A:**
**Type Casting** = Converting one data type to another

**Types:**
1. **Implicit (Widening)** - Automatic, no data loss
   ```java
   int x = 10;
   double y = x; // int → double (safe)
   ```

2. **Explicit (Narrowing)** - Manual, may lose data
   ```java
   double a = 10.7;
   int b = (int) a; // b = 10 (decimal part lost)
   ```

**Truncation** = Loss of decimal part during narrowing cast.
```java
float f = 9.99f;
int i = (int) f; // i = 9 (0.99 truncated, not rounded!)
```

---

#### **Q: Difference between Statically and Dynamically Typed Languages?**
**A:**

| Feature | Static Typing | Dynamic Typing |
|---------|---------------|----------------|
| **Type Check** | Compile-time | Runtime |
| **Declaration** | Must declare type | No type declaration |
| **Example** | Java, C++, C# | Python, JavaScript |
| **Performance** | Faster (type known early) | Slower |
| **Flexibility** | Less flexible | More flexible |

```java
// Java (Static)
int x = 10;
x = "Hello"; // ❌ Compile error

# Python (Dynamic)
x = 10
x = "Hello" # ✅ Works fine
```

**Java = Statically typed** - Catches type errors early!

---

### 🧱 **Object-Oriented Programming (OOPs)**

#### **Q: What are Classes and Objects?**
**A:**
- **Class** = Blueprint/Template (defines structure)
- **Object** = Instance of class (actual entity in memory)

```java
// Class = Blueprint
class Car {
    String color;
    int speed;
    
    void accelerate() {
        speed += 10;
    }
}

// Objects = Real entities
Car myCar = new Car();
myCar.color = "Red";

Car yourCar = new Car();
yourCar.color = "Blue";
```

**Analogy:** Class = House blueprint, Objects = Actual houses built from it

---

#### **Q: Explain Encapsulation**
**A:** **Encapsulation** = Wrapping data (variables) and methods together + hiding internal details

**Benefits:**
- Data protection
- Controlled access
- Easy to maintain

```java
class BankAccount {
    private double balance; // Hidden from outside
    
    // Controlled access through methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    public double getBalance() {
        return balance;
    }
}

// Usage
BankAccount acc = new BankAccount();
// acc.balance = -500; // ❌ Cannot access directly
acc.deposit(1000); // ✅ Controlled access
```

**Real-world:** Like a TV remote - you use buttons (public methods), don't touch internal circuits (private data)

---

#### **Q: What is Inheritance? Types?**
**A:** **Inheritance** = Acquiring properties/methods from parent class

**Benefits:** Code reusability, method overriding

```java
// Parent class
class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

// Child class inherits from Animal
class Dog extends Animal {
    void bark() {
        System.out.println("Barking...");
    }
}

Dog d = new Dog();
d.eat();  // ✅ Inherited method
d.bark(); // ✅ Own method
```

**Types in Java:**
1. **Single** - A → B
2. **Multilevel** - A → B → C
3. **Hierarchical** - A → B, A → C
4. ❌ **Multiple** (Not supported via classes, use interfaces)
5. ❌ **Hybrid** (Partially through interfaces)

**Why no multiple?** Diamond problem (ambiguity)

---

#### **Q: Explain Polymorphism with examples**
**A:** **Polymorphism** = "Many forms" - Same method behaves differently

**Two types:**

**1. Compile-time (Method Overloading)** - Same name, different parameters
```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }
    
    double add(double a, double b) {
        return a + b;
    }
    
    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

**2. Runtime (Method Overriding)** - Child redefines parent method
```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark!");
    }
}

class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Meow!");
    }
}

// Runtime polymorphism
Animal a1 = new Dog();
Animal a2 = new Cat();
a1.sound(); // Bark!
a2.sound(); // Meow!
```

---

#### **Q: Abstract Class vs Interface - When to use what?**
**A:**

| Feature | Abstract Class | Interface |
|---------|---------------|-----------|
| **Methods** | Can have both abstract & concrete | All abstract (before Java 8) |
| **Variables** | Any type | Only public static final |
| **Inheritance** | Single (extends) | Multiple (implements) |
| **Constructor** | Yes | No |
| **Access Modifiers** | All types | Public only (methods) |
| **Use Case** | IS-A relationship | CAN-DO relationship |

```java
// Abstract Class - "IS-A" (partial implementation)
abstract class Vehicle {
    int wheels;
    
    abstract void start(); // Must implement
    
    void stop() { // Common implementation
        System.out.println("Stopping...");
    }
}

// Interface - "CAN-DO" (contract)
interface Flyable {
    void fly(); // Must implement
}

class Car extends Vehicle {
    void start() {
        System.out.println("Car starting...");
    }
}

class Airplane extends Vehicle implements Flyable {
    void start() {
        System.out.println("Airplane starting...");
    }
    
    public void fly() {
        System.out.println("Flying high!");
    }
}
```

**When to use:**
- **Abstract Class:** Common behavior + some implementation (Bird → Parrot, Sparrow)
- **Interface:** Unrelated classes share behavior (Car, Airplane both can GPS-enable)

---

#### **Q: Explain 'this' keyword**
**A:** **this** = Reference to current object

**Uses:**

**1. Resolve naming conflict**
```java
class Student {
    String name;
    
    Student(String name) {
        this.name = name; // this.name = instance variable
    }
}
```

**2. Call another constructor**
```java
class Box {
    int length, width;
    
    Box() {
        this(10, 20); // Calls parameterized constructor
    }
    
    Box(int length, int width) {
        this.length = length;
        this.width = width;
    }
}
```

**3. Pass current object**
```java
class A {
    void display() {
        B obj = new B();
        obj.show(this); // Passing current object
    }
}
```

---

#### **Q: Explain 'static' keyword**
**A:** **static** = Belongs to class, not object

**Memory:** Loaded once when class loads (shared by all objects)

```java
class Counter {
    static int count = 0; // Shared by all objects
    int id; // Unique to each object
    
    Counter() {
        count++; // Increments for all
        id = count;
    }
    
    static void showCount() {
        System.out.println("Total objects: " + count);
        // System.out.println(id); // ❌ Error - can't access non-static
    }
}

Counter c1 = new Counter();
Counter c2 = new Counter();
Counter.showCount(); // Total objects: 2
```

**Rules:**
- Static method can't access non-static members directly
- Static members accessed via class name
- No `this` or `super` in static context

**Use cases:** Utility methods (Math.sqrt), Constants (Math.PI), Counters

---

#### **Q: Explain 'final' keyword**
**A:** **final** = Cannot be changed/overridden

**3 uses:**

**1. Final Variable** - Constant
```java
final int MAX_AGE = 100;
// MAX_AGE = 200; // ❌ Error
```

**2. Final Method** - Cannot be overridden
```java
class Parent {
    final void show() {
        System.out.println("Final method");
    }
}

class Child extends Parent {
    // void show() {} // ❌ Error - cannot override
}
```

**3. Final Class** - Cannot be inherited
```java
final class Math {
    // Utility class
}

// class AdvancedMath extends Math {} // ❌ Error
```

**Real example:** `String` class is final (security, immutability)

---

#### **Q: What is Constructor? Types?**
**A:** **Constructor** = Special method to initialize objects

**Characteristics:**
- Same name as class
- No return type
- Called automatically when object created

**Types:**

**1. Default Constructor**
```java
class Car {
    Car() {
        System.out.println("Car created");
    }
}
```

**2. Parameterized Constructor**
```java
class Car {
    String model;
    
    Car(String model) {
        this.model = model;
    }
}

Car c = new Car("Tesla");
```

**3. Copy Constructor**
```java
class Car {
    String model;
    
    Car(Car c) {
        this.model = c.model;
    }
}
```

**Constructor Chaining:**
```java
class Box {
    Box() {
        this(10); // Calls Box(int)
    }
    
    Box(int side) {
        this(side, side); // Calls Box(int, int)
    }
    
    Box(int l, int w) {
        // Initialize
    }
}
```

---

#### **Q: Access Modifiers in Java**
**A:**

| Modifier | Same Class | Same Package | Subclass (Other Package) | Other Package |
|----------|-----------|--------------|-------------------------|---------------|
| **private** | ✅ | ❌ | ❌ | ❌ |
| **default** | ✅ | ✅ | ❌ | ❌ |
| **protected** | ✅ | ✅ | ✅ | ❌ |
| **public** | ✅ | ✅ | ✅ | ✅ |

```java
class Example {
    private int a;      // Only within class
    int b;              // Same package (default)
    protected int c;    // Same package + subclasses
    public int d;       // Everywhere
}
```

**Encapsulation best practice:** Keep data private, provide public getters/setters

---

### 📦 **Collections Framework**

#### **Q: ArrayList vs LinkedList**
**A:**

| Feature | ArrayList | LinkedList |
|---------|-----------|-----------|
| **Internal** | Dynamic Array | Doubly Linked List |
| **Access** | O(1) - Fast | O(n) - Slow |
| **Insert/Delete (middle)** | O(n) - Slow | O(1) - Fast |
| **Memory** | Less overhead | More (stores pointers) |
| **Use Case** | Frequent access | Frequent add/remove |

```java
// ArrayList - Good for retrieval
List<String> list1 = new ArrayList<>();
list1.add("A");
list1.get(0); // Fast O(1)

// LinkedList - Good for insertion
List<String> list2 = new LinkedList<>();
list2.add(0, "B"); // Fast at beginning
```

**Interview Tip:** "ArrayList for read-heavy, LinkedList for write-heavy operations"

---

#### **Q: HashMap Internal Working** ⭐⭐⭐ (VERY IMPORTANT)
**A:** HashMap stores key-value pairs using **hashing**.

**Internal Structure:**
- Array of buckets (Node<K,V>[] table)
- Each bucket can hold multiple entries (linked list or tree)

**Working:**

**1. put(key, value):**
```
1. Calculate hash: hash = key.hashCode()
2. Find bucket: index = hash & (n-1) // n = array length
3. Check bucket:
   - If empty → store entry
   - If collision → 
     * Java 7: Linked list
     * Java 8: Linked list (≤8) OR Red-Black Tree (>8)
4. If key exists → replace value
```

**2. get(key):**
```
1. Calculate hash
2. Find bucket
3. Search in bucket (list/tree)
4. Return value
```

**Example:**
```java
HashMap<String, Integer> map = new HashMap<>();
map.put("John", 25); // hashCode → index → store
map.put("Jane", 30);
map.get("John"); // hashCode → index → retrieve → 25
```

**Collision Handling:**
- **Java 7:** Linked list (O(n) worst case)
- **Java 8+:** Tree after 8 collisions (O(log n))

**Load Factor:** Default 0.75 - rehashing when 75% full (doubles capacity)

**Why override equals() and hashCode()?**
```java
class Person {
    String name;
    
    // Must override both!
    public boolean equals(Object o) {
        return this.name.equals(((Person)o).name);
    }
    
    public int hashCode() {
        return name.hashCode();
    }
}
```

**Contract:** If a.equals(b) is true, then a.hashCode() == b.hashCode()

---

#### **Q: HashSet vs TreeSet**
**A:**

| Feature | HashSet | TreeSet |
|---------|---------|---------|
| **Internal** | HashMap | Red-Black Tree |
| **Order** | No order | Sorted (natural/custom) |
| **Null** | Allows 1 null | No null |
| **Performance** | O(1) | O(log n) |
| **Use Case** | Fast operations | Sorted data |

```java
// HashSet - Unordered, fast
Set<Integer> set1 = new HashSet<>();
set1.add(3);
set1.add(1);
set1.add(2);
System.out.println(set1); // [1, 2, 3] or random order

// TreeSet - Sorted
Set<Integer> set2 = new TreeSet<>();
set2.add(3);
set2.add(1);
set2.add(2);
System.out.println(set2); // [1, 2, 3] always
```

---

#### **Q: Difference between Collection and Collections**
**A:**

**Collection** = Interface (root of hierarchy)
```java
Collection<String> col = new ArrayList<>();
```

**Collections** = Utility class (static methods)
```java
List<Integer> list = new ArrayList<>();
Collections.sort(list);
Collections.reverse(list);
Collections.shuffle(list);
```

**Similar to:** Array (type) vs Arrays (utility class)

---

#### **Q: Iterator vs ListIterator**
**A:**

| Feature | Iterator | ListIterator |
|---------|----------|--------------|
| **Direction** | Forward only | Both directions |
| **Applicable** | All collections | List only |
| **Methods** | hasNext(), next(), remove() | Also: hasPrevious(), previous(), add(), set() |

```java
List<String> list = new ArrayList<>();
list.add("A");
list.add("B");

// Iterator - Forward only
Iterator<String> it = list.iterator();
while(it.hasNext()) {
    System.out.println(it.next());
}

// ListIterator - Bidirectional
ListIterator<String> lit = list.listIterator();
while(lit.hasNext()) {
    System.out.println(lit.next());
}
while(lit.hasPrevious()) {
    System.out.println(lit.previous());
}
```

---

### ⚠️ **Exception Handling**

#### **Q: Checked vs Unchecked Exceptions**
**A:**

| Feature | Checked | Unchecked |
|---------|---------|-----------|
| **Check Time** | Compile-time | Runtime |
| **Handling** | Must handle | Optional |
| **Parent Class** | Exception | RuntimeException |
| **Examples** | IOException, SQLException | NullPointerException, ArithmeticException |

```java
// Checked - Must handle
void readFile() throws IOException { // Must declare
    FileReader fr = new FileReader("file.txt");
}

void caller() {
    try {
        readFile(); // Must handle
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Unchecked - Optional handling
void divide() {
    int result = 10 / 0; // ArithmeticException (no need to declare)
}
```

**Interview Tip:** "Checked = Predictable (file not found), Unchecked = Programming errors (null pointer)"

---

#### **Q: throw vs throws**
**A:**

| throw | throws |
|-------|--------|
| Keyword to manually throw exception | Declares exception method might throw |
| Inside method | In method signature |
| Followed by instance | Followed by class |
| throw new Exception() | throws Exception |

```java
// throws - Declaration
void withdraw(int amount) throws InsufficientFundsException {
    if (amount > balance) {
        throw new InsufficientFundsException("Low balance"); // throw - Actual throwing
    }
}

// Caller must handle
try {
    withdraw(1000);
} catch (InsufficientFundsException e) {
    System.out.println(e.getMessage());
}
```

---

#### **Q: finally block - Will it always execute?**
**A:** **Yes, almost always!** Executes even if exception occurs or return statement present.

```java
try {
    int x = 10 / 0;
} catch (Exception e) {
    System.out.println("Exception");
    return; // Even with return
} finally {
    System.out.println("Finally always runs!"); // ✅ This runs
}
```

**When finally won't run:**
1. System.exit() called
2. JVM crash
3. Thread death

**Use case:** Close resources (files, connections)

```java
FileReader fr = null;
try {
    fr = new FileReader("file.txt");
    // Read file
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (fr != null) {
        fr.close(); // ✅ Always close
    }
}
```

**Modern alternative:** Try-with-resources
```java
try (FileReader fr = new FileReader("file.txt")) {
    // Auto-closes resource
}
```

---

#### **Q: Custom Exception Example**
**A:**
```java
// Custom Exception
class AgeException extends Exception {
    AgeException(String message) {
        super(message);
    }
}

// Usage
class Voter {
    void checkAge(int age) throws AgeException {
        if (age < 18) {
            throw new AgeException("Not eligible to vote!");
        }
        System.out.println("Eligible!");
    }
}

// Main
try {
    Voter v = new Voter();
    v.checkAge(15);
} catch (AgeException e) {
    System.out.println(e.getMessage());
}
```

---

### 🧵 **Strings**

#### **Q: Why are Strings Immutable?** ⭐⭐⭐ (VERY IMPORTANT)
**A:** **Immutable** = Cannot change after creation

**Reasons:**

**1. Security:**
```java
String user = "admin";
authenticate(user); // user cannot be changed by method
```

**2. String Pool (Memory efficiency):**
```java
String s1 = "Hello"; // Pool
String s2 = "Hello"; // Reuses same object
System.out.println(s1 == s2); // true (same reference)
```

**3. Thread Safety:** Immutable = Safe to share between threads

**4. Hashcode Caching:** Used in HashMap keys
```java
HashMap<String, Integer> map = new HashMap<>();
String key = "name";
map.put(key, 25);
// If string was mutable, key.hashCode() could change → map breaks!
```

**What happens when you "modify":**
```java
String s = "Hello";
s = s + " World"; // Creates NEW string, old "Hello" remains in pool
```

---

#### **Q: String vs StringBuilder vs StringBuffer**
**A:**

| Feature | String | StringBuilder | StringBuffer |
|---------|--------|---------------|--------------|
| **Mutability** | Immutable | Mutable | Mutable |
| **Thread-Safe** | Yes | No | Yes (synchronized) |
| **Performance** | Slow (creates new objects) | Fast | Slower than StringBuilder |
| **Use Case** | Few modifications | Single thread | Multiple threads |

```java
// String - Creates multiple objects
String s = "Hello";
s += " World"; // Creates new object
s += "!";      // Creates new object
// Total: 3 objects created

// StringBuilder - Modifies same object
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World"); // Same object
sb.append("!");      // Same object
// Total: 1 object

// StringBuffer - Thread-safe StringBuilder
StringBuffer sbf = new StringBuffer("Hello");
sbf.append(" World"); // Synchronized
```

**Rule of thumb:**
- **String:** Constant values, few changes
- **StringBuilder:** Single-threaded, many modifications
- **StringBuffer:** Multi-threaded, many modifications

---

#### **Q: equals() vs == for Strings**
**A:**

**==** → Compares references (memory address)
**equals()** → Compares content (values)

```java
// String Pool
String s1 = "Hello";
String s2 = "Hello";
System.out.println(s1 == s2);        // true (same reference in pool)
System.out.println(s1.equals(s2));   // true (same content)

// Heap
String s3 = new String("Hello");
String s4 = new String("Hello");
System.out.println(s3 == s4);        // false (different objects)
System.out.println(s3.equals(s4));   // true (same content)
```

**Interview Gotcha:**
```java
String s1 = "Hello";
String s2 = new String("Hello");
System.out.println(s1 == s2); // false (pool vs heap)

s2 = s2.intern(); // Move to pool
System.out.println(s1 == s2); // true (now both in pool)
```

**Always use .equals() for string comparison!**

---

#### **Q: String Pool explained**
**A:** **String Pool** = Special memory area in heap for string literals

**Purpose:** Memory optimization (reuse strings)

```java
String s1 = "Java";    // Pool
String s2 = "Java";    // Reuses s1
String s3 = new String("Java"); // Heap (separate)

System.out.println(s1 == s2); // true
System.out.println(s1 == s3); // false

s3 = s3.intern(); // Moves to pool
System.out.println(s1 == s3); // true
```

**How it works:**
1. When you create "Java", JVM checks pool
2. If exists → return reference
3. If not → create new and add to pool

**intern() method:** Forces string to pool

---

## ⭐⭐ IMPORTANT (Frequently Asked - 60-80% interviews)

### 🧵 **Multithreading**

#### **Q: What is Multithreading? Why use it?**
**A:** **Multithreading** = Multiple threads run concurrently within a process

**Benefits:**
1. **Better CPU utilization** - Don't waste idle time
2. **Improved performance** - Parallel execution
3. **Responsive UI** - Background tasks don't freeze interface
4. **Resource sharing** - Threads share memory

```java
// Without threads - Sequential (slow)
downloadFile();  // 10 seconds
processData();   // 5 seconds
// Total: 15 seconds

// With threads - Parallel (fast)
Thread t1 = new Thread(() -> downloadFile());
Thread t2 = new Thread(() -> processData());
t1.start();
t2.start();
// Total: ~10 seconds (overlap)
```

**Real examples:**
- Video streaming (download + play simultaneously)
- Web servers (handle multiple requests)
- Games (rendering + physics + AI)

---

#### **Q: Thread Creation - Thread vs Runnable**
**A:** **Two ways:**

**1. Extend Thread class**
```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread running");
    }
}

MyThread t = new MyThread();
t.start(); // Starts thread
```

**2. Implement Runnable interface** ✅ **Preferred**
```java
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable running");
    }
}

Thread t = new Thread(new MyRunnable());
t.start();

// Or with lambda
Thread t2 = new Thread(() -> {
    System.out.println("Lambda thread");
});
t2.start();
```

**Why Runnable is better?**
- Java doesn't support multiple inheritance
- Better OOP (separate task from thread)
- Can implement multiple interfaces

```java
class MyClass extends SomeClass implements Runnable {
    // ✅ Can extend another class + implement Runnable
}
```

---

#### **Q: Thread Life Cycle**
**A:** **5 States:**

```
NEW → RUNNABLE → RUNNING → TERMINATED
         ↓           ↓
      BLOCKED/WAITING
```

**1. NEW** - Thread created but not started
```java
Thread t = new Thread(() -> {});
// State: NEW
```

**2. RUNNABLE** - start() called, waiting for CPU
```java
t.start();
// State: RUNNABLE
```

**3. RUNNING** - Thread executing
```java
// When CPU scheduler picks thread
// State: RUNNING
```

**4. BLOCKED/WAITING** - Waiting for resource/lock
```java
synchronized(obj) {
    // If lock held by another thread
    // State: BLOCKED
}

Thread.sleep(1000); // State: TIMED_WAITING
```

**5. TERMINATED** - Execution completed
```java
// After run() finishes
// State: TERMINATED
```

**Common methods:**
- `start()` - Start thread
- `run()` - Contains code to execute
- `sleep(ms)` - Pause thread
- `join()` - Wait for thread to finish

---

#### **Q: What is Synchronization? Why needed?**
**A:** **Synchronization** = Controlling access to shared resource by multiple threads

**Problem without sync:**
```java
class Counter {
    int count = 0;
    
    void increment() {
        count++; // Not atomic! (read → add → write)
    }
}

// Thread 1: reads 0 → adds 1 → writes 1
// Thread 2: reads 0 → adds 1 → writes 1
// Expected: 2, Actual: 1 ❌ (Race condition!)
```

**Solution with synchronized:**
```java
class Counter {
    int count = 0;
    
    synchronized void increment() {
        count++; // Only one thread at a time ✅
    }
}
```

**Types:**

**1. Method Level**
```java
synchronized void method() {
    // Thread-safe
}
```

**2. Block Level**
```java
void method() {
    synchronized(this) {
        // Only this part is synchronized
    }
}
```

**How it works:**
- Every object has a **monitor/lock**
- synchronized = acquire lock → execute → release lock
- Other threads wait

**Cost:** Performance overhead (threads wait)

---

#### **Q: What is Deadlock? How to avoid?**
**A:** **Deadlock** = Two or more threads waiting for each other forever

**Example:**
```java
Object lock1 = new Object();
Object lock2 = new Object();

// Thread 1
synchronized(lock1) {
    synchronized(lock2) {
        // Work
    }
}

// Thread 2
synchronized(lock2) {
    synchronized(lock1) { // ❌ Deadlock!
        // Work
    }
}
```

**What happens:**
1. Thread1 locks lock1, waits for lock2
2. Thread2 locks lock2, waits for lock1
3. Both wait forever ☠️

**Prevention strategies:**

**1. Lock Ordering** - Always acquire locks in same order
```java
// Both threads
synchronized(lock1) {
    synchronized(lock2) {
        // ✅ Same order
    }
}
```

**2. Timeout** - Don't wait forever
```java
if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
    try {
        // Work
    } finally {
        lock.unlock();
    }
}
```

**3. Avoid Nested Locks** - Use single lock
```java
synchronized(commonLock) {
    // Use one lock for all operations
}
```

---

#### **Q: join() and isAlive() methods**
**A:**

**join()** - Wait for thread to finish
```java
Thread t1 = new Thread(() -> {
    for(int i = 0; i < 5; i++) {
        System.out.println("T1: " + i);
    }
});

Thread t2 = new Thread(() -> {
    for(int i = 0; i < 5; i++) {
        System.out.println("T2: " + i);
    }
});

t1.start();
t1.join(); // Main thread waits for t1 to finish
t2.start();
t2.join(); // Main thread waits for t2 to finish

System.out.println("Done!"); // Prints after both finish
```

**isAlive()** - Check if thread running
```java
Thread t = new Thread(() -> {
    try {
        Thread.sleep(2000);
    } catch (InterruptedException e) {}
});

System.out.println(t.isAlive()); // false (not started)
t.start();
System.out.println(t.isAlive()); // true (running)
Thread.sleep(3000);
System.out.println(t.isAlive()); // false (finished)
```

**Use case:** Coordinate thread execution, ensure completion

---

#### **Q: Producer-Consumer Problem**
**A:** **Classic synchronization problem:**

**Problem:** Producer creates items, Consumer uses items. Need coordination.

**Solution using wait() and notify():**
```java
class SharedResource {
    int item = 0;
    boolean available = false;
    
    synchronized void produce(int value) {
        while (available) {
            try {
                wait(); // Wait if item available
            } catch (InterruptedException e) {}
        }
        item = value;
        available = true;
        System.out.println("Produced: " + value);
        notify(); // Wake up consumer
    }
    
    synchronized int consume() {
        while (!available) {
            try {
                wait(); // Wait if no item
            } catch (InterruptedException e) {}
        }
        available = false;
        System.out.println("Consumed: " + item);
        notify(); // Wake up producer
        return item;
    }
}

// Producer thread
new Thread(() -> {
    for (int i = 1; i <= 5; i++) {
        resource.produce(i);
    }
}).start();

// Consumer thread
new Thread(() -> {
    for (int i = 1; i <= 5; i++) {
        resource.consume();
    }
}).start();
```

**Output:**
```
Produced: 1
Consumed: 1
Produced: 2
Consumed: 2
...
```

**Modern solution:** Use BlockingQueue
```java
BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

// Producer
queue.put(item); // Blocks if full

// Consumer
int item = queue.take(); // Blocks if empty
```

---

### 📊 **Arrays**

#### **Q: Why use Arrays? Limitations?**
**A:**

**Advantages:**
- Fast access: O(1) using index
- Contiguous memory
- Cache-friendly

**Disadvantages:**
1. **Fixed size** - Cannot grow/shrink
```java
int[] arr = new int[5];
// Cannot add 6th element!
```

2. **Homogeneous** - Only one type
```java
int[] arr = {1, 2, 3};
// arr[3] = "Hello"; // ❌ Error
```

3. **No built-in methods** - No add(), remove()

**Solution:** Use ArrayList, LinkedList (Collections)

---

#### **Q: 2D Array - Regular vs Jagged**
**A:**

**Regular Array** - Fixed columns
```java
int[][] arr = new int[3][3];
// All rows have 3 columns
arr[0] = new int[]{1, 2, 3};
arr[1] = new int[]{4, 5, 6};
arr[2] = new int[]{7, 8, 9};
```

**Jagged Array** - Variable columns
```java
int[][] jagged = new int[3][];
jagged[0] = new int[]{1, 2};        // 2 columns
jagged[1] = new int[]{3, 4, 5};     // 3 columns
jagged[2] = new int[]{6, 7, 8, 9};  // 4 columns

// Memory efficient for irregular data
```

**Visualization:**
```
Regular:          Jagged:
1 2 3            1 2
4 5 6            3 4 5
7 8 9            6 7 8 9
```

---

#### **Q: Enhanced For Loop**
**A:** **For-each loop** - Simpler iteration

```java
// Traditional
int[] arr = {1, 2, 3, 4, 5};
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}

// Enhanced ✅ Cleaner
for (int num : arr) {
    System.out.println(num);
}
```

**Limitations:**
- No index access
- Cannot modify array
- Only forward iteration

```java
for (int num : arr) {
    num = num * 2; // ❌ Doesn't modify original array
}
```

---

### 🔗 **Interfaces & Lambda Expressions**

#### **Q: What is Functional Interface?**
**A:** **Functional Interface** = Interface with exactly ONE abstract method

**Marked with:** `@FunctionalInterface` (optional but recommended)

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b); // Only one abstract method
    
    // Can have default/static methods
    default void show() {
        System.out.println("Calculator");
    }
}
```

**Built-in functional interfaces:**
```java
// Runnable - No param, no return
Runnable r = () -> System.out.println("Running");

// Predicate<T> - Takes T, returns boolean
Predicate<Integer> isEven = num -> num % 2 == 0;

// Function<T, R> - Takes T, returns R
Function<String, Integer> length = str -> str.length();

// Consumer<T> - Takes T, returns nothing
Consumer<String> print = str -> System.out.println(str);

// Supplier<T> - No param, returns T
Supplier<Double> random = () -> Math.random();
```

---

#### **Q: Lambda Expressions - Syntax and Use**
**A:** **Lambda** = Shorthand for anonymous inner class (functional interfaces)

**Syntax:** `(parameters) -> expression/block`

**Examples:**

```java
// Old way - Anonymous inner class
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Running");
    }
};

// Lambda way ✅
Runnable r2 = () -> System.out.println("Running");

// With parameters
Calculator add = (a, b) -> a + b;
Calculator multiply = (a, b) -> a * b;

System.out.println(add.calculate(5, 3));      // 8
System.out.println(multiply.calculate(5, 3)); // 15

// Multi-line lambda
Calculator complex = (a, b) -> {
    int result = a + b;
    System.out.println("Adding...");
    return result;
};
```

**Benefits:**
- Less code
- Readable
- Enables functional programming

**Common use with Collections:**
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// Filter even numbers
numbers.stream()
       .filter(n -> n % 2 == 0)
       .forEach(n -> System.out.println(n));
```

---

#### **Q: Anonymous Inner Class**
**A:** **Anonymous Inner Class** = Class without name, used once

```java
// Interface
interface Greeting {
    void sayHello();
}

// Anonymous inner class
Greeting g = new Greeting() {
    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }
};
g.sayHello();

// Same with lambda (if functional interface)
Greeting g2 = () -> System.out.println("Hello!");
```

**Use cases:**
- Event handlers (button clicks)
- Thread creation
- One-time implementations

**Abstract class example:**
```java
abstract class Animal {
    abstract void sound();
}

Animal dog = new Animal() {
    void sound() {
        System.out.println("Bark!");
    }
};
dog.sound();
```

---

## ⭐ GOOD TO HAVE (Nice extras - 20-40% interviews)

### 🧬 **Generics**

#### **Q: What are Generics? Why use them?**
**A:** **Generics** = Type parameters for classes/methods (type safety at compile-time)

**Without Generics:**
```java
List list = new ArrayList();
list.add("Hello");
list.add(123);

String s = (String) list.get(1); // Runtime error! ❌
```

**With Generics:** ✅
```java
List<String> list = new ArrayList<>();
list.add("Hello");
// list.add(123); // Compile error! Caught early ✅

String s = list.get(0); // No casting needed
```

**Benefits:**
1. **Type safety** - Errors caught at compile-time
2. **No casting** - Cleaner code
3. **Reusability** - Same code for different types

---

#### **Q: Generic Class Example**
**A:**
```java
// Generic class
class Box<T> {
    private T value;
    
    void set(T value) {
        this.value = value;
    }
    
    T get() {
        return value;
    }
}

// Usage
Box<Integer> intBox = new Box<>();
intBox.set(123);
int num = intBox.get(); // No casting!

Box<String> strBox = new Box<>();
strBox.set("Hello");
String str = strBox.get();
```

---

#### **Q: Bounded Type Parameters - extends vs super**
**A:**

**Upper Bound (extends)** - Type must be T or subclass
```java
class NumberBox<T extends Number> {
    private T value;
    
    void set(T value) {
        this.value = value;
    }
    
    double doubleValue() {
        return value.doubleValue(); // ✅ Can use Number methods
    }
}

NumberBox<Integer> box1 = new NumberBox<>();  // ✅ Integer extends Number
NumberBox<Double> box2 = new NumberBox<>();   // ✅ Double extends Number
// NumberBox<String> box3 = new NumberBox<>(); // ❌ String doesn't extend Number
```

**Lower Bound (super)** - Type must be T or superclass
```java
// Used mainly with wildcards
void addNumbers(List<? super Integer> list) {
    list.add(10);    // ✅ Can add Integer
    list.add(20);    // ✅ Can add Integer
    // Integer x = list.get(0); // ❌ Can't guarantee return type
}

List<Number> numList = new ArrayList<>();
List<Object> objList = new ArrayList<>();
addNumbers(numList); // ✅ Number is super of Integer
addNumbers(objList); // ✅ Object is super of Integer
```

**PECS Rule:** Producer Extends, Consumer Super
- **extends** - When you READ from collection (producer)
- **super** - When you WRITE to collection (consumer)

---

### 📘 **Enums**

#### **Q: What are Enums? When to use?**
**A:** **Enum** = Special class for fixed set of constants

**Without Enum:**
```java
// String constants - Error prone
String DAY1 = "MONDAY";
String DAY2 = "TUESDAY";

if (day.equals("MONDAI")) { // ❌ Typo!
}
```

**With Enum:** ✅
```java
enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

Day today = Day.MONDAY;
if (today == Day.MONDAY) { // ✅ Type-safe
    System.out.println("Start of week");
}
```

**Benefits:**
- Type safety
- Compile-time checking
- Readable
- Can have methods/fields

---

#### **Q: Enum with Methods and Fields**
**A:**
```java
enum Planet {
    EARTH(6371, 5.97),
    MARS(3389, 0.642),
    JUPITER(69911, 1898);
    
    private final double radius; // km
    private final double mass;   // 10^24 kg
    
    // Constructor
    Planet(double radius, double mass) {
        this.radius = radius;
        this.mass = mass;
    }
    
    // Methods
    double surfaceGravity() {
        return 6.67e-11 * mass / (radius * radius);
    }
    
    double getRadius() {
        return radius;
    }
}

// Usage
Planet p = Planet.EARTH;
System.out.println(p.getRadius());        // 6371.0
System.out.println(p.surfaceGravity());   // 9.8 (approx)

// Built-in methods
System.out.println(Planet.EARTH.name());     // "EARTH"
System.out.println(Planet.EARTH.ordinal());  // 0 (index)
Planet[] all = Planet.values();              // All values
```

---

### 🏷️ **Annotations**

#### **Q: What are Annotations?**
**A:** **Annotations** = Metadata for code (information for compiler/runtime)

**Common built-in annotations:**

```java
// @Override - Ensures method overrides parent
class Animal {
    void sound() {}
}

class Dog extends Animal {
    @Override
    void sound() {  // ✅ Compiler checks
        System.out.println("Bark");
    }
    
    // @Override
    // void sounds() {} // ❌ Compile error - typo caught!
}

// @Deprecated - Mark old methods
class Calculator {
    @Deprecated
    int oldAdd(int a, int b) {
        return a + b;
    }
    
    int add(int a, int b) {
        return a + b;
    }
}

// @SuppressWarnings - Ignore warnings
@SuppressWarnings("unchecked")
List list = new ArrayList(); // No warning

// @FunctionalInterface - Ensure single abstract method
@FunctionalInterface
interface Task {
    void execute();
}
```

---

#### **Q: Creating Custom Annotation**
**A:**
```java
// Define annotation
@Retention(RetentionPolicy.RUNTIME) // Available at runtime
@Target(ElementType.METHOD)          // Can be applied to methods
@interface Author {
    String name();
    String date() default "Unknown";
}

// Usage
class Book {
    @Author(name = "John Doe", date = "2024-01-15")
    void write() {
        System.out.println("Writing...");
    }
    
    @Author(name = "Jane Smith") // date = "Unknown"
    void edit() {
        System.out.println("Editing...");
    }
}

// Reading annotation using Reflection
Method method = Book.class.getMethod("write");
if (method.isAnnotationPresent(Author.class)) {
    Author author = method.getAnnotation(Author.class);
    System.out.println("Author: " + author.name());
    System.out.println("Date: " + author.date());
}
```

**Retention Policies:**
- **SOURCE** - Discarded by compiler (e.g., @Override)
- **CLASS** - In .class file but not at runtime
- **RUNTIME** - Available via reflection

---

### 📁 **File Handling**

#### **Q: File I/O Basics**
**A:**

**Reading a file:**
```java
// Old way - FileReader
try {
    FileReader fr = new FileReader("input.txt");
    int ch;
    while ((ch = fr.read()) != -1) {
        System.out.print((char) ch);
    }
    fr.close();
} catch (IOException e) {
    e.printStackTrace();
}

// Modern way - BufferedReader (faster)
try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

**Writing to file:**
```java
try (FileWriter fw = new FileWriter("output.txt")) {
    fw.write("Hello World\n");
    fw.write("Java File I/O");
} catch (IOException e) {
    e.printStackTrace();
}

// Append mode
FileWriter fw = new FileWriter("output.txt", true); // true = append
```

---

#### **Q: Serialization and Deserialization**
**A:** **Serialization** = Converting object to byte stream (save to file/network)

```java
// Serializable class
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int age;
    transient String password; // transient = won't be serialized
    
    Student(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
}

// Serialization - Object to file
Student s = new Student("John", 20, "secret123");
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
    oos.writeObject(s);
    System.out.println("Serialized!");
}

// Deserialization - File to object
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("student.ser"))) {
    Student s2 = (Student) ois.readObject();
    System.out.println(s2.name);     // John
    System.out.println(s2.age);      // 20
    System.out.println(s2.password); // null (transient!)
}
```

**Use cases:**
- Save object state
- Send objects over network
- Caching

---

## 🔥 CRITICAL DEEP-DIVE QUESTIONS

#### **Q: Fail-fast vs Fail-safe Iterators**
**A:**

**Fail-fast** - Throws exception if collection modified during iteration
```java
List<String> list = new ArrayList<>();
list.add("A");
list.add("B");

Iterator<String> it = list.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
    list.add("C"); // ❌ ConcurrentModificationException
}
```

**Fail-safe** - Doesn't throw exception (works on copy)
```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
list.add("A");
list.add("B");

Iterator<String> it = list.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
    list.add("C"); // ✅ Works fine (iterates on copy)
}
```

**Collections:**
- **Fail-fast:** ArrayList, HashMap, HashSet
- **Fail-safe:** CopyOnWriteArrayList, ConcurrentHashMap

---

#### **Q: Comparable vs Comparator**
**A:**

**Comparable** - Natural ordering (inside class)
```java
class Student implements Comparable<Student> {
    String name;
    int marks;
    
    @Override
    public int compareTo(Student other) {
        return this.marks - other.marks; // Sort by marks
    }
}

List<Student> list = new ArrayList<>();
Collections.sort(list); // Uses compareTo
```

**Comparator** - Custom ordering (outside class)
```java
// Sort by name
Comparator<Student> nameComparator = (s1, s2) -> s1.name.compareTo(s2.name);
Collections.sort(list, nameComparator);

// Sort by marks (descending)
Comparator<Student> marksComparator = (s1, s2) -> s2.marks - s1.marks;
Collections.sort(list, marksComparator);

// Multiple comparators
Collections.sort(list, 
    Comparator.comparing(Student::getMarks)
              .thenComparing(Student::getName)
);
```

**When to use:**
- **Comparable:** Single, natural ordering
- **Comparator:** Multiple orderings, can't modify class

---

This covers all major topics! Would you like me to:
1. Add more coding examples?
2. Include common interview coding problems?
3. Add Spring/Hibernate questions?
4. Cover Java 8+ features in detail?

Let me know! 🚀