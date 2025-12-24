# 🧵 Strings in Java – Detailed Notes

## Table of Contents

1. [Introduction to Strings](#introduction-to-strings)
2. [Types of Strings](#types-of-strings)
3. [Immutable Strings & Memory Map](#immutable-strings--memory-map)
4. [Ways to Compare Strings](#ways-to-compare-strings)
5. [String Concatenation](#string-concatenation)
6. [Inbuilt Methods of String Class](#inbuilt-methods-of-string-class)
7. [Introduction to Mutable Strings](#introduction-to-mutable-strings)
8. [final vs Immutability](#final-vs-immutability)
9. [StringBuffer vs StringBuilder](#stringbuffer-vs-stringbuilder)

---

## Introduction to Strings

A **String** is a sequence of characters. In Java, `String` is a **class**, not a primitive.

```java
String name = "Java";
```

### Key Points:

* Stored as objects
* Widely used in applications
* Part of `java.lang` package

---

## Types of Strings

### 1. String Literal

Stored in **String Constant Pool (SCP)**.

```java
String s1 = "Hello";
String s2 = "Hello";
```

✔ Both refer to the **same object** in SCP

### 2. String Object (new keyword)

Stored in **Heap memory**.

```java
String s3 = new String("Hello");
```

✔ Creates a **new object** every time

---

## Immutable Strings & Memory Map

### Immutability

Once a String object is created, **its value cannot be changed**.

```java
String s = "Java";
s.concat("World");
System.out.println(s); // Java
```

### Correct Way:

```java
s = s.concat("World");
```

### Memory Map

```java
String s1 = "Java";
String s2 = s1.concat("World");
```

```
SCP → "Java" , "JavaWorld"
```

✔ Original string remains unchanged

### Why Strings are Immutable?

* Security
* Thread safety
* Performance (String Pool)

---

## Ways to Compare Strings

### 1. == Operator

Compares **references**, not content.

```java
String a = "Java";
String b = "Java";
System.out.println(a == b); // true
```

### 2. equals()

Compares **content**.

```java
String a = new String("Java");
String b = new String("Java");
System.out.println(a.equals(b)); // true
```

### 3. compareTo()

Lexicographical comparison.

```java
System.out.println("A".compareTo("B")); // -1
```

---

## String Concatenation

### Using + Operator

```java
String s = "Hello" + " World";
```

### Using concat()

```java
String s = "Hello".concat(" World");
```

### Using StringBuilder (Best)

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World");
```

### Performance Order:

```
StringBuilder > StringBuffer > String (+)
```

---

## Inbuilt Methods of String Class

```java
String str = " Java Programming ";
```

| Method        | Description        |
| ------------- | ------------------ |
| length()      | Returns length     |
| charAt(i)     | Character at index |
| substring()   | Extract substring  |
| toUpperCase() | Uppercase          |
| toLowerCase() | Lowercase          |
| trim()        | Remove spaces      |
| replace()     | Replace chars      |
| contains()    | Check substring    |
| split()       | Split string       |

```java
str.trim();
str.substring(0,4);
```

---

## Introduction to Mutable Strings

Mutable strings **can be modified**.

### Classes:

* StringBuilder
* StringBuffer

```java
StringBuilder sb = new StringBuilder("Java");
sb.append(" World");
System.out.println(sb); // Java World
```

---

## final vs Immutability

### final Keyword

Cannot reassign reference.

```java
final String s = "Java";
// s = "Python"; ❌
```

### Immutability

Object content cannot change.

```java
String s = "Java";
s.concat("World"); // new object
```

| final               | Immutability      |
| ------------------- | ----------------- |
| Restricts reference | Restricts content |
| Compile-time        | Runtime           |

---

## StringBuffer vs StringBuilder

| Feature     | StringBuffer | StringBuilder |
| ----------- | ------------ | ------------- |
| Thread-safe | Yes          | No            |
| Performance | Slower       | Faster        |
| Introduced  | Java 1.0     | Java 1.5      |

### Example:

```java
StringBuffer sb1 = new StringBuffer("Hi");
sb1.append(" Java");

StringBuilder sb2 = new StringBuilder("Hi");
sb2.append(" Java");
```

### When to Use?

* **StringBuffer** → Multithreading
* **StringBuilder** → Single-threaded (preferred)

---

## Summary

| Concept       | Key Point         |
| ------------- | ----------------- |
| String        | Immutable         |
| SCP           | Saves memory      |
| equals()      | Content compare   |
| ==            | Reference compare |
| StringBuilder | Fast & mutable    |

---

## Quick Revision Code

```java
String s = "Java";
StringBuilder sb = new StringBuilder(s);
sb.reverse();
System.out.println(sb);
```

---

✅ **Strings notes are interview-critical, DSA-essential, and conceptually clear**
