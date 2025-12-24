# 💻 Java Fundamentals - Detailed Notes

## Table of Contents

1. [Introduction to Java](#introduction-to-java)
2. [How Java Made Portable (WORA)](#how-java-made-portable-wora)
3. [Main Method](#main-method)
4. [Statically vs Dynamically Typed Languages](#statically-vs-dynamically-typed-languages)
5. [Variables and Data Types](#variables-and-data-types)
6. [Type Casting and Truncation](#type-casting-and-truncation)
7. [Identifiers and Naming Conventions](#identifiers-and-naming-conventions)
8. [Operators](#operators)
9. [Increment and Decrement Operators](#increment-and-decrement-operators)
10. [Conditional Statements](#conditional-statements)
11. [Loops](#loops)
12. [Scanner Class (Console User Input)](#scanner-class-console-user-input)

---

## Introduction to Java

**Java** is a high-level, object-oriented, platform-independent programming language developed by **Sun Microsystems (1995)**, now owned by **Oracle**.

### Key Features:

* Object-Oriented
* Platform Independent
* Secure
* Robust
* Multithreaded
* High Performance (via JIT)

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

---

## How Java Made Portable (WORA)

**WORA = Write Once, Run Anywhere**

### Java Execution Flow:

```
.java (Source Code)
   ↓ javac
.class (Bytecode)
   ↓ JVM
Machine Code (OS dependent)
```

### Why Java is Portable:

* Java compiler generates **bytecode**
* Bytecode runs on **JVM**
* JVM is platform-specific, Java code is not

```text
Windows → JVM → Bytecode
Linux   → JVM → Bytecode
Mac     → JVM → Bytecode
```

---

## Main Method

The **entry point** of any Java program.

```java
public static void main(String[] args)
```

### Breakdown:

* `public` → accessible by JVM
* `static` → no object needed
* `void` → returns nothing
* `String[] args` → command-line arguments

```java
public class Test {
    public static void main(String[] args) {
        System.out.println(args[0]);
    }
}
```

---

## Statically vs Dynamically Typed Languages

### Statically Typed (Java):

* Variable type known at compile time
* More safe and optimized

```java
int x = 10; // valid
x = "Hello"; // compile-time error
```

### Dynamically Typed (Python/JS):

* Type decided at runtime

```python
x = 10
x = "Hello"  # valid
```

| Feature    | Static       | Dynamic |
| ---------- | ------------ | ------- |
| Type Check | Compile time | Runtime |
| Speed      | Faster       | Slower  |
| Errors     | Early        | Late    |

---

## Variables and Data Types

### Variables:

Containers to store data.

```java
int age = 20;
```

### Primitive Data Types:

| Type    | Size    | Example              |
| ------- | ------- | -------------------- |
| byte    | 1 byte  | byte b = 10;         |
| short   | 2 bytes | short s = 100;       |
| int     | 4 bytes | int x = 10;          |
| long    | 8 bytes | long l = 100L;       |
| float   | 4 bytes | float f = 10.5f;     |
| double  | 8 bytes | double d = 10.5;     |
| char    | 2 bytes | char c = 'A';        |
| boolean | 1 bit   | boolean flag = true; |

### Non-Primitive:

* String
* Arrays
* Classes
* Interfaces

---

## Type Casting and Truncation

### Implicit Casting (Widening):

```java
int a = 10;
double b = a; // automatic
```

### Explicit Casting (Narrowing):

```java
double x = 10.75;
int y = (int) x; // 10 (data loss)
```

### Truncation:

* Decimal part is removed

```java
System.out.println((int) 9.99); // 9
```

---

## Identifiers and Naming Conventions

### Identifiers:

Names given to variables, methods, classes.

### Rules:

* Cannot start with digit
* No spaces
* Cannot use keywords
* Case-sensitive

```java
int _count = 10; // valid
int 1num = 5;   // invalid
```

### Naming Conventions:

* Class → PascalCase (`StudentData`)
* Variable → camelCase (`studentAge`)
* Constant → UPPER_CASE (`MAX_SIZE`)

---

## Operators

### Arithmetic Operators:

```java
+  -  *  /  %
```

### Relational Operators:

```java
==  !=  >  <  >=  <=
```

### Logical Operators:

```java
&&  ||  !
```

### Assignment Operators:

```java
=  +=  -=  *=  /=
```

### Bitwise Operators:

```java
&  |  ^  ~  <<  >>
```

---

## Increment and Decrement Operators

### Pre-Increment:

```java
int a = 5;
System.out.println(++a); // 6
```

### Post-Increment:

```java
int b = 5;
System.out.println(b++); // 5
System.out.println(b);   // 6
```

| Operator | Action             |
| -------- | ------------------ |
| ++a      | increment then use |
| a++      | use then increment |

---

## Conditional Statements

### if-else:

```java
if (age >= 18) {
    System.out.println("Eligible");
} else {
    System.out.println("Not Eligible");
}
```

### else-if Ladder:

```java
if (marks > 90) grade = 'A';
else if (marks > 75) grade = 'B';
else grade = 'C';
```

### switch:

```java
switch(day) {
    case 1: System.out.println("Monday"); break;
    case 2: System.out.println("Tuesday"); break;
    default: System.out.println("Invalid");
}
```

---

## Loops

### for Loop:

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

### while Loop:

```java
int i = 1;
while (i <= 5) {
    System.out.println(i);
    i++;
}
```

### do-while Loop:

```java
int i = 1;
do {
    System.out.println(i);
    i++;
} while (i <= 5);
```

---

## Scanner Class (Console User Input)

Used to take input from user.

```java
import java.util.Scanner;

public class InputDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        System.out.println("Age: " + age);
        sc.close();
    }
}
```

### Common Methods:

| Method       | Input       |
| ------------ | ----------- |
| nextInt()    | int         |
| nextDouble() | double      |
| next()       | single word |
| nextLine()   | full line   |

⚠️ **nextInt() + nextLine() issue**

```java
sc.nextLine(); // consume leftover newline
```

---

## Summary

| Topic      | Key Point                    |
| ---------- | ---------------------------- |
| Java       | Platform Independent         |
| WORA       | Bytecode + JVM               |
| main()     | Program entry point          |
| Data Types | Primitive & Non-Primitive    |
| Casting    | Widening & Narrowing         |
| Operators  | Arithmetic, Logical, Bitwise |
| Loops      | for, while, do-while         |
| Scanner    | User Input                   |

---

## Quick Revision Code

```java
Scanner sc = new Scanner(System.in);
int a = sc.nextInt();
int b = sc.nextInt();
System.out.println(a + b);
```

---

✅ **These notes are interview-ready, exam-oriented, and DSA-friendly.**
