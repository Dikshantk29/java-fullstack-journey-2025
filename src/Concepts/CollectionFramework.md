# Collections Framework in Java – Detailed Notes

## Table of Contents

1. Why Collections?
2. Collection Framework Overview
3. Collection Hierarchy
4. ArrayList
5. LinkedList
6. ArrayDeque
7. PriorityQueue
8. HashSet
9. LinkedHashSet
10. TreeSet
11. Iterator & ListIterator
12. Legacy Classes & Enumeration
13. Introduction to Map
14. Map Hierarchy
15. HashMap

---

## Why Collections?

Arrays have several limitations:

* Fixed size (cannot grow/shrink dynamically)
* No built-in methods for insertion, deletion, searching, sorting
* Stores only homogeneous data

Collections solve these problems by providing:

* Dynamic resizing
* Ready-made data structures
* Utility methods
* Better performance and flexibility

---

## Collection Framework Overview

The **Java Collections Framework (JCF)** is a set of **classes and interfaces** that implement commonly reusable data structures.

### Key Interfaces:

* Collection
* List
* Set
* Queue
* Deque
* Map (does NOT extend Collection)

---

## Collection Hierarchy

```
Iterable
   |
Collection
   |-----------------------------
   |            |               |
 List          Set            Queue
   |            |               |
ArrayList   HashSet        PriorityQueue
LinkedList  LinkedHashSet  ArrayDeque
            TreeSet
```

---

## ArrayList

### What is ArrayList?

* Resizable array implementation of List
* Maintains insertion order
* Allows duplicate elements
* Allows random access

### Example:

```java
ArrayList<Integer> list = new ArrayList<>();
list.add(10);
list.add(20);
list.add(10);
System.out.println(list);
```

### Key Points:

* Underlying structure: Dynamic array
* Default capacity: 10
* Not synchronized

---

## LinkedList

### What is LinkedList?

* Doubly linked list implementation
* Implements List and Deque

### Example:

```java
LinkedList<String> list = new LinkedList<>();
list.add("A");
list.addFirst("B");
list.addLast("C");
```

### Comparison with ArrayList:

| Feature   | ArrayList | LinkedList |
| --------- | --------- | ---------- |
| Access    | Fast      | Slow       |
| Insertion | Slow      | Fast       |
| Memory    | Less      | More       |

---

## ArrayDeque

* Double-ended queue
* Faster than Stack and LinkedList
* No capacity restriction

```java
ArrayDeque<Integer> dq = new ArrayDeque<>();
dq.addFirst(10);
dq.addLast(20);
```

---

## PriorityQueue

* Elements processed based on priority
* Default: Min-Heap

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.add(30);
pq.add(10);
pq.add(20);
System.out.println(pq.poll());
```

---

## HashSet

* Implements Set
* No duplicates
* No order guaranteed

```java
HashSet<Integer> set = new HashSet<>();
set.add(10);
set.add(20);
set.add(10);
```

---

## LinkedHashSet

* Maintains insertion order
* Slightly slower than HashSet

```java
LinkedHashSet<String> set = new LinkedHashSet<>();
set.add("A");
set.add("B");
```

---

## TreeSet

* Sorted Set implementation
* Uses Red-Black Tree

```java
TreeSet<Integer> ts = new TreeSet<>();
ts.add(30);
ts.add(10);
ts.add(20);
```

---

## Iterator & ListIterator

### Iterator

* Forward traversal only

```java
Iterator<Integer> itr = list.iterator();
while(itr.hasNext()){
    System.out.println(itr.next());
}
```

### ListIterator

* Bidirectional
* Allows modification

---

## Legacy Classes & Enumeration

### Legacy Classes:

* Vector
* Stack
* Hashtable

### Enumeration:

```java
Enumeration e = vector.elements();
while(e.hasMoreElements()){
    System.out.println(e.nextElement());
}
```

---

## Introduction to Map

* Stores data as key-value pairs
* Keys must be unique
* Does not extend Collection

---

## Map Hierarchy

```
Map
 |-------------------
HashMap   LinkedHashMap   TreeMap
```

---

## HashMap

### What is HashMap?

* Unordered key-value storage
* Allows one null key, multiple null values

```java
HashMap<Integer, String> map = new HashMap<>();
map.put(1, "Java");
map.put(2, "Python");
System.out.println(map.get(1));
```

### Key Points:

* Not synchronized
* Uses hashing
* Fast retrieval

---

## Summary Table

| Collection    | Order  | Duplicates | Thread-safe |
| ------------- | ------ | ---------- | ----------- |
| ArrayList     | Yes    | Yes        | No          |
| HashSet       | No     | No         | No          |
| LinkedHashSet | Yes    | No         | No          |
| TreeSet       | Sorted | No         | No          |
| HashMap       | No     | Keys No    | No          |

---

## Interview Tips 🚀

* Know **ArrayList vs LinkedList**
* Understand **HashMap internal working**
* Iterator vs Enumeration
* Set vs List vs Map differences

---

## Next Topics Recommended

* Concurrent Collections
* TreeMap & LinkedHashMap deep dive
* Comparable vs Comparator
* Internal working of HashMap
