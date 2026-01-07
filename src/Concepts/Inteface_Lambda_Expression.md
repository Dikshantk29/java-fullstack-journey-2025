# 🔌 Interfaces & Lambda Expressions in Java – Detailed Notes

## Table of Contents

1. [What is an Interface?](#what-is-an-interface)
2. [Interface Implementation](#interface-implementation)
3. [Need for Interface (with Examples)](#need-for-interface-with-examples)
4. [Key Points of Interface](#key-points-of-interface)
5. [Abstract Class vs Interface](#abstract-class-vs-interface)
6. [Java 8 Features](#java-8-features)
7. [Functional Interfaces](#functional-interfaces)
8. [Inner Classes](#inner-classes)
9. [Anonymous Inner Classes](#anonymous-inner-classes)
10. [Lambda Expressions](#lambda-expressions)

---

## What is an Interface?

An **interface** is a blueprint that contains **abstract methods**, **default methods**, and **static methods**. It represents **100% abstraction (before Java 8)**.

```java
interface Animal {
    void sound();
}
```
In **Java interfaces**, methods can be of three main types: **abstract**, **default**, and **static**. Here’s a clear comparison 👇

---

## 1. Abstract Method

* **No implementation** (no method body)
* Must be implemented by the implementing class
* Implicitly `public` and `abstract`

### Example

```java
interface Animal {
    void sound();   // abstract method
}
```

```java
class Dog implements Animal {
    public void sound() {
        System.out.println("Bark");
    }
}
```

---

## 2. Default Method

* **Has an implementation**
* Introduced in **Java 8**
* Can be **overridden** by implementing classes
* Used to add new methods to interfaces without breaking existing code

### Example

```java
interface Animal {
    default void sleep() {
        System.out.println("Sleeping...");
    }
}
```

```java
class Dog implements Animal {
    // inherits default method
}
```

---

## 3. Static Method

* Belongs to the **interface itself**, not to implementing classes
* **Cannot be overridden**
* Called using the **interface name**

### Example

```java
interface Animal {
    static void info() {
        System.out.println("Animals are living beings");
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        Animal.info();   // static method call
    }
}

```

### Characteristics:

* Cannot create object of interface
* Used to achieve abstraction
* Supports multiple inheritance

---

## Interface Implementation

A class uses `implements` keyword to implement an interface.

```java
class Dog implements Animal {
    public void sound() {
        System.out.println("Bark");
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.sound();
    }
}
```

✔ Method must be **public** while implementing

---

## Need for Interface (with Examples)

### Problem Without Interface

```java
class Laptop {
    void code() {}
}
```

Hard dependency → poor flexibility

### Solution Using Interface

```java
interface Computer {
    void code();
}

class Laptop implements Computer {
    public void code() {
        System.out.println("Coding on laptop");
    }
}

class Desktop implements Computer {
    public void code() {
        System.out.println("Coding on desktop");
    }
}
```

✔ Loose coupling
✔ Easy scalability

---

## Key Points of Interface

* Methods are **public & abstract** by default
* Variables are **public static final**
* Supports multiple inheritance
* Cannot have constructors

```java
interface Demo {
    int x = 10; // public static final
    void show(); // public abstract
}
```

---

## Abstract Class vs Interface

| Feature              | Abstract Class      | Interface                         |
| -------------------- | ------------------- | --------------------------------- |
| Methods              | Abstract + Concrete | Abstract (default/static allowed) |
| Variables            | Instance allowed    | public static final               |
| Multiple Inheritance | ❌                   | ✔                                 |
| Constructor          | ✔                   | ❌                                 |

---

## Java 8 Features

Major changes introduced in Java 8:

* Lambda Expressions
* Functional Interfaces
* Default Methods
* Static Methods in Interface
* Stream API
* Optional Class

### Default Method Example

```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle started");
    }
}
```

---

## Functional Interfaces

An interface with **only one abstract method**.

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}
```

### Predefined Functional Interfaces

| Interface | Method   |
| --------- | -------- |
| Runnable  | run()    |
| Callable  | call()   |
| Predicate | test()   |
| Function  | apply()  |
| Consumer  | accept() |
| Supplier  | get()    |

---

## Inner Classes

A class defined **inside another class**.

### Types:

* Member Inner Class
* Static Inner Class
* Local Inner Class
* Anonymous Inner Class

```java
class Outer {
    class Inner {
        void show() {
            System.out.println("Inner class");
        }
    }
}
```

---

## Anonymous Inner Classes

Class without name, used for **one-time implementation**.

```java
interface Greeting {
    void sayHello();
}

public class Test {
    public static void main(String[] args) {
        Greeting g = new Greeting() {
            public void sayHello() {
                System.out.println("Hello");
            }
        };
        g.sayHello();
    }
}
```

✔ Reduces boilerplate code

---

## Lambda Expressions

Lambda provides **compact syntax** to implement functional interfaces.

### Syntax:

```
(parameters) -> expression
```

### Example Without Lambda

```java
Calculator c = new Calculator() {
    public int add(int a, int b) {
        return a + b;
    }
};
```

### Using Lambda

```java
Calculator c = (a, b) -> a + b;
System.out.println(c.add(10, 20));
```

### Advantages:

* Less code
* Readable
* Supports functional programming

---

## Lambda with Threads

```java
Runnable r = () -> {
    System.out.println("Thread running");
};

new Thread(r).start();
```

---

## Summary

| Concept              | Key Point              |
| -------------------- | ---------------------- |
| Interface            | 100% abstraction       |
| Functional Interface | Single abstract method |
| Lambda               | Short implementation   |
| Java 8               | Functional programming |
| Anonymous Class      | One-time use           |

---
Here’s a **clear and exam/interview-ready explanation of Java 8 features** with short examples 👇

---

# **Java 8 Features**

Java 8 introduced **functional programming** concepts and major improvements to make code **simpler, cleaner, and more readable**.

---

## **1. Lambda Expressions**

* Provide a concise way to represent **anonymous functions**
* Used mainly with **functional interfaces**
* Improves readability and reduces boilerplate code

### Syntax

```java
(parameters) -> expression
```

### Example

```java
Runnable r = () -> System.out.println("Running thread");
```

---

## **2. Functional Interfaces**

* Interface with **exactly one abstract method**
* Can have **default and static methods**
* Annotated with `@FunctionalInterface` (optional but recommended)

### Example

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}
```

```java
Calculator c = (a, b) -> a + b;
System.out.println(c.add(10, 20));
```

---

## **3. Default Methods**

* Methods with **implementation inside an interface**
* Introduced to add new methods without breaking existing implementations
* Can be overridden by implementing classes

### Example

```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle started");
    }
}
```

---

## **4. Static Methods in Interface**

* Belong to the **interface itself**
* Cannot be overridden
* Called using **interface name**

### Example

```java
interface MathUtils {
    static int square(int x) {
        return x * x;
    }
}
```

```java
int result = MathUtils.square(5);
```

---

## **5. Stream API**

* Used to process collections in a **functional style**
* Supports operations like `filter`, `map`, `reduce`
* Improves performance using **lazy evaluation**

### Example

```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);

nums.stream()
    .filter(n -> n % 2 == 0)
    .forEach(System.out::println);
```

---

## **6. Optional Class**

* Used to avoid **NullPointerException**
* Acts as a container that may or may not contain a value

### Example

```java
Optional<String> name = Optional.ofNullable(null);

System.out.println(name.orElse("Default Name"));
```

In **Java**, the symbol **`::`** is called the **Method Reference operator** (introduced in **Java 8**).

---

## **What does `::` mean?**

`::` is a **shorthand notation for calling a method using a lambda expression**.

👉 It makes code **shorter, cleaner, and more readable**.

---

## **Types of Method References**

### **1. Reference to a Static Method**

```java
ClassName::staticMethodName
```

#### Example

```java
class Test {
    static void show() {
        System.out.println("Hello");
    }
}

Runnable r = Test::show;
```

Equivalent Lambda:

```java
Runnable r = () -> Test.show();
```

---

### **2. Reference to an Instance Method (of a particular object)**

```java
object::instanceMethod
```

#### Example

```java
Test t = new Test();
Runnable r = t::show;
```

---

### **3. Reference to an Instance Method (of an arbitrary object of a class)**

```java
ClassName::instanceMethod
```

#### Example

```java
List<String> names = Arrays.asList("A", "B", "C");
names.forEach(System.out::println);
```

Equivalent Lambda:

```java
names.forEach(name -> System.out.println(name));
```

---

### **4. Reference to a Constructor**

```java
ClassName::new
```

#### Example

```java
Supplier<Test> s = Test::new;
```

---

## **Key Points**

* `::` is used with **functional interfaces**
* It replaces lambda expressions when a **method already exists**
* Improves **readability and maintainability**

---

## **Simple Definition (Interview Ready)**

👉 **`::` is a method reference operator in Java 8 used to refer to a method or constructor without invoking it.**

If you want, I can give **comparison with lambda**, **real-time examples**, or **interview questions** 😊

---

## **Summary Table**

| Feature               | Purpose                          |
| --------------------- | -------------------------------- |
| Lambda Expressions    | Shorter function implementation  |
| Functional Interfaces | Basis for lambdas                |
| Default Methods       | Add methods to interfaces safely |
| Static Methods        | Utility methods in interfaces    |
| Stream API            | Functional data processing       |
| Optional Class        | Handle null values safely        |

---

If you want, I can also give **one-line definitions**, **real-time use cases**, or **interview questions** based on Java 8 features 👍

## Quick Revision Code

```java
Runnable r = () -> System.out.println("Hello Lambda");
new Thread(r).start();
```

---

✅ **These notes are interview-critical and Java 8 focused**
