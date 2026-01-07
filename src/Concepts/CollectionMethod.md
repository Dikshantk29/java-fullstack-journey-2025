Understood. Below is a **corrected and complete `.md` note** that **adds every IMPORTANT and commonly used function** for each Collection type.
This is **exam-oriented + real-project oriented**. Nothing unnecessary; nothing missing.

You can **replace your previous file** with this one.

---

````md
# 📦 Java Collections Framework – Complete Notes (With All Important Methods)

---

## 1️⃣ Why Collections Framework?

Collections solve problems of:
- Fixed size arrays
- Manual searching/sorting
- No standard APIs

They provide **ready-made data structures + algorithms**.

---

## 2️⃣ Core Interfaces Overview

| Interface | Purpose |
|---------|--------|
| Collection | Root interface |
| List | Ordered, duplicates allowed |
| Set | No duplicates |
| Queue | FIFO / priority |
| Map | Key–Value pairs (NOT part of Collection) |

---

## 3️⃣ Collection Interface – Important Methods

These methods are **available to all Lists, Sets, Queues**.

```java
add(E e)
addAll(Collection c)
remove(Object o)
removeAll(Collection c)
clear()

contains(Object o)
containsAll(Collection c)

size()
isEmpty()

iterator()
toArray()
````

---

## 4️⃣ List Interface – Important Methods

List supports **index-based operations**.

```java
add(int index, E e)
get(int index)
set(int index, E e)
remove(int index)

indexOf(Object o)
lastIndexOf(Object o)

listIterator()
subList(int from, int to)
```

---

## 5️⃣ ArrayList – Important Methods

### Key Properties

* Dynamic array
* Fast read, slow middle insert/delete
* Allows duplicates & null

### Commonly Used Methods

```java
ArrayList<Integer> list = new ArrayList<>();

list.add(10);
list.add(20);

list.get(0);
list.set(1, 100);

list.remove(0);        // by index
list.remove(Integer.valueOf(10)); // by object

list.size();
list.isEmpty();
list.contains(100);

list.clear();
```

---

## 6️⃣ LinkedList – Important Methods

Implements **List + Deque**

### Special Methods (Very Important)

```java
addFirst(E e)
addLast(E e)

removeFirst()
removeLast()

getFirst()
getLast()

peek()
poll()
```

### Example

```java
LinkedList<Integer> list = new LinkedList<>();

list.addFirst(10);
list.addLast(20);

System.out.println(list.getFirst());
System.out.println(list.removeLast());
```

---

## 7️⃣ Queue Interface – Core Methods

| Method    | Throws Exception | Returns null |
| --------- | ---------------- | ------------ |
| add()     | ✅                | ❌            |
| offer()   | ❌                | ✅            |
| remove()  | ✅                | ❌            |
| poll()    | ❌                | ✅            |
| element() | ✅                | ❌            |
| peek()    | ❌                | ✅            |

---

## 8️⃣ ArrayDeque – Important Methods

### Use instead of Stack & Queue

```java
ArrayDeque<Integer> dq = new ArrayDeque<>();

dq.addFirst(10);
dq.addLast(20);

dq.removeFirst();
dq.removeLast();

dq.peekFirst();
dq.peekLast();
```

⚠️ Null values NOT allowed

---

## 9️⃣ PriorityQueue – Important Methods

### Heap-based Queue

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.add(30);
pq.add(10);

pq.peek();    // highest priority
pq.poll();    // remove highest priority

pq.size();
pq.isEmpty();
```

---

## 🔟 Set Interface – Important Methods

```java
add(E e)
remove(Object o)
contains(Object o)

size()
isEmpty()
clear()

iterator()
```

---

## 1️⃣1️⃣ HashSet – Important Methods

### Characteristics

* No duplicates
* No order
* One null allowed

```java
HashSet<String> set = new HashSet<>();

set.add("A");
set.add("B");

set.contains("A");
set.remove("B");

set.size();
```

---

## 1️⃣2️⃣ LinkedHashSet – Important Methods

Maintains **insertion order**

```java
LinkedHashSet<Integer> set = new LinkedHashSet<>();

set.add(1);
set.add(2);
set.add(1); // ignored
```

Methods same as HashSet.

---

## 1️⃣3️⃣ TreeSet – Important Methods (VERY IMPORTANT)

### Sorted Set

```java
TreeSet<Integer> set = new TreeSet<>();

set.add(30);
set.add(10);
set.add(20);
```

### Navigation Methods (Interview Favorite)

```java
first()
last()

higher(E e)
lower(E e)

ceiling(E e)
floor(E e)

headSet(E e)
tailSet(E e)
subSet(E from, E to)
```

---

## 1️⃣4️⃣ Iterator – Important Methods

```java
Iterator<Integer> it = list.iterator();

while (it.hasNext()) {
    Integer val = it.next();
    it.remove(); // safe removal
}
```

⚠️ Prevents `ConcurrentModificationException`

---

## 1️⃣5️⃣ ListIterator – Important Methods

```java
ListIterator<Integer> li = list.listIterator();

li.hasNext()
li.next()

li.hasPrevious()
li.previous()

li.add(E e)
li.set(E e)
li.remove()
```

---

## 1️⃣6️⃣ Legacy Classes & Enumeration

### Enumeration Methods

```java
hasMoreElements()
nextElement()
```

### Legacy Classes

* Vector
* Stack
* Hashtable

(Use only for legacy systems)

---

## 1️⃣7️⃣ Map Interface – Core Methods (MOST IMPORTANT)

```java
put(K key, V value)
putIfAbsent(K key, V value)

get(Object key)
getOrDefault(K key, V defaultValue)

remove(Object key)
remove(Object key, Object value)

containsKey(Object key)
containsValue(Object value)

keySet()
values()
entrySet()

size()
isEmpty()
clear()
```

---

## 1️⃣8️⃣ HashMap – Important Methods (Deep Focus)

### Example

```java
HashMap<Integer, String> map = new HashMap<>();

map.put(1, "A");
map.put(2, "B");

map.get(1);
map.remove(2);

map.containsKey(1);
map.containsValue("A");
```

### Iteration (Interview Standard)

```java
for (Map.Entry<Integer, String> e : map.entrySet()) {
    System.out.println(e.getKey() + " " + e.getValue());
}
```

---

## 1️⃣9️⃣ Important Interview Rules (Must Remember)

* ArrayList → fast access
* LinkedList → fast insertion/deletion
* HashSet → no duplicates
* TreeSet → sorted, no null
* PriorityQueue → heap
* HashMap → one null key
* Iterator → safe removal

---

## 🔚 End of Notes

```

---

