# 📊 Arrays in Java – Detailed Notes

## Table of Contents

1. [Why Arrays?](#why-arrays)
2. [Creating Arrays](#creating-arrays)
3. [Different Ways to Create Arrays](#different-ways-to-create-arrays)
4. [2D Regular Arrays](#2d-regular-arrays)
5. [2D Jagged Arrays](#2d-jagged-arrays)
6. [Enhanced For Loop](#enhanced-for-loop)
7. [Anonymous Arrays](#anonymous-arrays)
8. [ArrayIndexOutOfBoundsException](#arrayindexoutofboundsexception)
9. [Disadvantages of Arrays](#disadvantages-of-arrays)

---

## Why Arrays?

Arrays are used to **store multiple values of the same data type** under a single variable name.

### Without Arrays:

```java
int m1 = 85;
int m2 = 90;
int m3 = 78;
```

### With Arrays:

```java
int[] marks = {85, 90, 78};
```

### Advantages:

* Reduces code length
* Easy traversal using loops
* Organized data storage

---

## Creating Arrays

### Syntax:

```java
dataType[] arrayName = new dataType[size];
```

### Example:

```java
int[] arr = new int[5];
arr[0] = 10;
arr[1] = 20;
```

### Memory:

* Array object stored in **heap**
* Reference stored in **stack**

---

## Different Ways to Create Arrays

### 1. Declaration + Allocation

```java
int[] a = new int[3];
```

### 2. Declaration + Initialization

```java
int[] a = {10, 20, 30};
```

### 3. Using new keyword

```java
int[] a = new int[]{1, 2, 3};
```

### Valid Declarations:

```java
int[] a;
int a[];
```

---

## 2D Regular Arrays

A **rectangular matrix** where each row has equal columns.

### Declaration:

```java
int[][] matrix = new int[2][3];
```

### Initialization:

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6}
};
```

### Traversal:

```java
for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
}
```

---

## 2D Jagged Arrays

An array of arrays where **each row can have different size**.

### Declaration:

```java
int[][] arr = new int[3][];
```

### Initialization:

```java
arr[0] = new int[2];
arr[1] = new int[3];
arr[2] = new int[1];
```

### Example:

```java
int[][] jagged = {
    {1, 2},
    {3, 4, 5},
    {6}
};
```

### Use Case:

* Variable-length data
* Memory efficient

---

## Enhanced For Loop

Used to traverse arrays easily.

### Syntax:

```java
for (dataType var : array) {
    // use var
}
```

### Example:

```java
int[] nums = {10, 20, 30};
for (int n : nums) {
    System.out.println(n);
}
```

### Limitations:

* No index access
* Cannot modify array elements

---

## Anonymous Arrays

Arrays **without reference variable**.

### Syntax:

```java
new int[]{1, 2, 3};
```

### Example:

```java
static void print(int[] arr) {
    for (int x : arr) {
        System.out.println(x);
    }
}

public static void main(String[] args) {
    print(new int[]{10, 20, 30});
}
```

### Use Case:

* One-time use
* Cleaner method calls

---

## ArrayIndexOutOfBoundsException

Occurs when accessing **invalid index**.

### Example:

```java
int[] a = {1, 2, 3};
System.out.println(a[5]); // Exception
```

### Reason:

* Index < 0
* Index ≥ array length

### Prevention:

```java
if (index >= 0 && index < a.length) {
    System.out.println(a[index]);
}
```

---

## Disadvantages of Arrays

### Limitations:

* Fixed size
* Homogeneous elements only
* No built-in methods
* Insertion/deletion costly

| Feature | Arrays  | Collections |
| ------- | ------- | ----------- |
| Size    | Fixed   | Dynamic     |
| Type    | Same    | Multiple    |
| Methods | Limited | Rich        |

---

## Summary

| Concept      | Key Point          |
| ------------ | ------------------ |
| Array        | Same-type elements |
| 2D Array     | Matrix             |
| Jagged       | Variable columns   |
| Enhanced for | Easy traversal     |
| Anonymous    | No reference       |
| Exception    | Invalid index      |

---

## Quick Revision Code

```java
int[] arr = {1, 2, 3};
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```

---

✅ **Arrays notes are interview-ready, DSA-focused, and beginner-friendly**
