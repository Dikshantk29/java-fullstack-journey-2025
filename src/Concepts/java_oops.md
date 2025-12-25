# 🧱 Object-Oriented Programming (OOPs) in Java – Detailed Notes

## Table of Contents

1. [Classes and Objects](#classes-and-objects)
2. [JVM Data Areas](#jvm-data-areas)
3. [Instance Variables vs Local Variables](#instance-variables-vs-local-variables)
4. [Method Overloading](#method-overloading)
5. [Wrapper Classes](#wrapper-classes)
6. [Encapsulation](#encapsulation)
7. [this Keyword](#this-keyword)
8. [Constructors](#constructors)
9. [Static Keyword](#static-keyword)
10. [Class Loading Mechanism](#class-loading-mechanism)
11. [Inheritance](#inheritance)
12. [Packages and Access Modifiers](#packages-and-access-modifiers)
13. [Polymorphism](#polymorphism)
14. [Abstraction & abstract Keyword](#abstraction--abstract-keyword)
15. [final Keyword](#final-keyword)

---

## Classes and Objects

### Class

A **class** is a blueprint/template from which objects are created.

```java
class Student {
    int id;
    String name;

    void study() {
        System.out.println(name + " is studying");
    }
}
```

### Object

An **object** is a real-world entity created from a class.

```java
public class Test {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.id = 1;
        s1.name = "Rhea";
        s1.study();
    }
}
```

---

## JVM Data Areas

Memory areas created by JVM during program execution.

### 1. Method Area

* Class metadata
* Static variables
* Method bytecode

### 2. Heap Area

* Objects
* Instance variables

### 3. Stack Area

* Method calls
* Local variables
* Reference variables

### 4. PC Register

* Address of current instruction

### 5. Native Method Stack

* Native (C/C++) methods

```
Stack → Reference
Heap  → Object
```

---

## Instance Variables vs Local Variables

### Instance Variables

* Declared inside class
* Stored in heap
* Default values assigned

```java
class A {
    int x; // instance variable
}
```

### Local Variables

* Declared inside method/block
* Stored in stack
* No default value

```java
void show() {
    int y = 10; // local variable
}
```

| Feature       | Instance | Local  |
| ------------- | -------- | ------ |
| Memory        | Heap     | Stack  |
| Default value | Yes      | No     |
| Scope         | Object   | Method |

---

## Method Overloading

Multiple methods with **same name but different parameters**.

```java
class MathUtils {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }
}
```

### Rules:

* Different parameter list
* Return type alone not sufficient
* Compile-time polymorphism

---

## Wrapper Classes

Convert **primitive data types into objects**.

| Primitive | Wrapper   |
| --------- | --------- |
| int       | Integer   |
| double    | Double    |
| char      | Character |
| boolean   | Boolean   |

```java
int a = 10;
Integer b = Integer.valueOf(a); // Boxing
int c = b.intValue();           // Unboxing
```

### Autoboxing & Unboxing

```java
Integer x = 20; // autoboxing
int y = x;      // unboxing
```

---

## Encapsulation

Binding data and methods together and **hiding data**.

### Achieved Using:

* private variables
* public getters/setters

```java
class Account {
    private double balance;

    public void setBalance(double b) {
        if (b > 0) balance = b;
    }

    public double getBalance() {
        return balance;
    }
}
```

### Benefits:

* Data security
* Validation
* Maintainability

---

## this Keyword

Refers to **current object**.

### Uses:

1. Resolve name conflict
2. Call current class constructor
3. Pass current object

```java
class Person {
    int age;

    Person(int age) {
        this.age = age;
    }
}
```

---

## Constructors

Special method used to **initialize objects**.

```java
class Car {
    String model;

    Car(String model) {
        this.model = model;
    }
}
```

### Types:

* Default Constructor
* Parameterized Constructor
* Copy Constructor (manual)

```java
Car c2 = new Car(c1.model);
```

### Constructor Rules:

* Same name as class
* No return type
* Called automatically

---

## Static Keyword

Belongs to **class**, not object.

### Static Variable

```java
static int count = 0;
```

### Static Method

```java
static void display() {
    System.out.println("Static method");
}
```

### Static Block

```java
static {
    System.out.println("Runs once");
}
```

### Static vs Instance

| Static      | Instance        |
| ----------- | --------------- |
| Class level | Object level    |
| One copy    | Multiple copies |

---

## Class Loading Mechanism

### Steps:

1. Loading
2. Linking

    * Verification
    * Preparation
    * Resolution
3. Initialization

```java
Class.forName("com.mysql.cj.jdbc.Driver");
```

### Class Loaders:

* Bootstrap
* Extension
* Application

---

## Inheritance

Acquiring properties of parent class.

```java
class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}
```

### Types:

* Single
* Multilevel
* Hierarchical

❌ Multiple inheritance not supported (use interfaces)

---

## Packages and Access Modifiers

### Packages

Used to group related classes.

```java
package com.app.utils;
```

### Access Modifiers

| Modifier  | Class | Package | Subclass | World |
| --------- | ----- | ------- | -------- | ----- |
| public    | ✔     | ✔       | ✔        | ✔     |
| protected | ✔     | ✔       | ✔        | ❌     |
| default   | ✔     | ✔       | ❌        | ❌     |
| private   | ✔     | ❌       | ❌        | ❌     |

---

## Polymorphism

One interface, many implementations.

### Compile-time

* Method overloading

### Runtime

* Method overriding

```java
class A {
    void show() {
        System.out.println("A");
    }
}

class B extends A {
    void show() {
        System.out.println("B");
    }
}

A obj = new B();
obj.show(); // B
```

---
---
---
Alright bro 😎 let’s make this **super practical** with code and key points you must remember for interviews.

We’ll cover **all three cases you mentioned**.

---

## Java Code Example: Reference vs Object Type

```java
class A {
    int x = 10;

    void show() {
        System.out.println("A show method");
    }

    void display() {
        System.out.println("A display method");
    }
}

class B extends A {
    int y = 20;

    @Override
    void show() {
        System.out.println("B show method");
    }

    void print() {
        System.out.println("B print method");
    }
}

public class TestOOP {
    public static void main(String[] args) {
        // 1️⃣ Reference type = Object type = A
        A obj1 = new A();
        obj1.show();        // A show method
        obj1.display();     // A display method
        // obj1.print();    ❌ Compile-time error (not in A)

        // 2️⃣ Reference type = A, Object type = B
        A obj2 = new B();
        obj2.show();        // B show method (runtime polymorphism)
        obj2.display();     // A display method
        // obj2.print();    ❌ Compile-time error (only in B)

        // 3️⃣ Reference type = Object type = B
        B obj3 = new B();
        obj3.show();        // B show method
        obj3.display();     // A display method (inherited)
        obj3.print();       // B print method
    }
}
```

---

## ✅ Important Points to Remember

1. **Reference type decides what you can access at compile time**

    * Only members of the reference class are visible.

2. **Object type decides which overridden method runs at runtime**

    * This is **dynamic method dispatch** (runtime polymorphism).

3. **Variables are not polymorphic**

   ```java
   System.out.println(obj2.x); // 10 (from A)
   ```

4. **You can assign subclass object to superclass reference (Upcasting)**

    * `A obj = new B();` ✅
    * Useful for **loose coupling** and **polymorphism**

5. **Cannot assign superclass object to subclass reference (Downcasting) without explicit cast**

   ```java
   B obj = (B) new A(); // ❌ Runtime error: ClassCastException
   ```

6. **Always remember:**

    * **Reference type = Compile-time access**
    * **Object type = Runtime behavior**
---
---

## Abstraction & abstract Keyword

Hiding implementation, showing only functionality.

### Abstract Class

```java
abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    void draw() {
        System.out.println("Circle");
    }
}
```

### Rules:

* Cannot create object of abstract class
* Can have abstract + concrete methods

---

## final Keyword

### final Variable

```java
final int MAX = 100;
```

### final Method

```java
final void display() {}
```

### final Class

```java
final class Utility {}
```

### Effects:

| final on | Result          |
| -------- | --------------- |
| variable | Constant        |
| method   | Cannot override |
| class    | Cannot inherit  |

---

## Summary

| Concept       | Purpose      |
| ------------- | ------------ |
| Class         | Blueprint    |
| Object        | Instance     |
| Encapsulation | Data hiding  |
| Inheritance   | Code reuse   |
| Polymorphism  | Flexibility  |
| Abstraction   | Hide details |
| static        | Class level  |
| final         | Restriction  |

---

✅ **Interview-focused, DSA-aligned, and exam-ready OOPs notes**
