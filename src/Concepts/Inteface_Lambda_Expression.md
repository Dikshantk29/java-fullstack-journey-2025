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

## Quick Revision Code

```java
Runnable r = () -> System.out.println("Hello Lambda");
new Thread(r).start();
```

---

✅ **These notes are interview-critical and Java 8 focused**
