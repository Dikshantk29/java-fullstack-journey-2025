package collection_framework;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


// Collection Framework Notes:
public class CollectionFramework {

    public static void main(String[] args) {

        /* =========================================================
           1️⃣ COLLECTION HIERARCHY (ROOT IDEA)
           Collection → List, Set, Queue
           Map is separate (key-value)
        ========================================================= */

        /* =========================================================
           2️⃣ LIST → Allows duplicates, maintains order
        ========================================================= */

        // ArrayList → fast access, slow insert/delete in middle
        List<Integer> arrayList = new ArrayList<>();

        arrayList.add(10);                 // add element
        arrayList.add(20);
        arrayList.add(30);

        arrayList.get(0);                  // access by index
        arrayList.set(1, 25);              // update value
        arrayList.contains(30);            // search
        arrayList.size();                  // count
        arrayList.isEmpty();               // empty check

        // remove by index vs value
        arrayList.remove(1);               // removes index 1
        arrayList.remove(Integer.valueOf(30)); // removes value

        // iteration
        for (int i = 0; i < arrayList.size(); i++) { }
        for (Integer i : arrayList) { }

        Iterator<Integer> itr = arrayList.iterator();
        while (itr.hasNext()) {
            itr.next();
        }


        // LinkedList → fast insert/delete, slow access
        List<Integer> linkedList = new LinkedList<>();


        /* =========================================================
           3️⃣ STACK (LIFO) → Legacy, avoid in production
        ========================================================= */

        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.peek();      // top element
        stack.pop();       // remove top


        /* =========================================================
           4️⃣ QUEUE (FIFO)
        ========================================================= */

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(10);   // add
        queue.peek();      // first element
        queue.poll();      // remove first


        /* =========================================================
           5️⃣ PRIORITY QUEUE (Heap)
           Default → Min Heap
        ========================================================= */

        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(40);
        pq.offer(10);
        pq.offer(30);

        pq.poll();         // smallest element removed
        pq.peek();

        // Max Heap
        Queue<Integer> maxPQ =
                new PriorityQueue<>(Comparator.reverseOrder());


        /* =========================================================
           6️⃣ ARRAY DEQUE → Modern Stack + Queue replacement
        ========================================================= */

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerFirst(10);   // add front
        deque.offerLast(20);    // add back
        deque.pollFirst();      // remove front
        deque.pollLast();       // remove back


        /* =========================================================
           7️⃣ SET → No duplicates
        ========================================================= */

        // HashSet → random order
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(10);
        hashSet.add(10);   // ignored

        // LinkedHashSet → insertion order
        Set<Integer> linkedHashSet = new LinkedHashSet<>();

        // TreeSet → sorted order (no null)
        Set<Integer> treeSet = new TreeSet<>();


        /* =========================================================
           8️⃣ CUSTOM OBJECT IN SET
           equals + hashCode REQUIRED
        ========================================================= */

        Set<Student> students = new HashSet<>();
        students.add(new Student("Anuj", 1));
        students.add(new Student("Rohit", 1)); // duplicate rollNo → ignored


        /* =========================================================
           9️⃣ MAP → Key-Value pair
        ========================================================= */

        // HashMap → random order
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);

        hashMap.get("one");
        hashMap.containsKey("two");
        hashMap.containsValue(3);
        hashMap.remove("two");

        // safe insert
        hashMap.putIfAbsent("one", 10);

        // iteration
        for (Map.Entry<String, Integer> e : hashMap.entrySet()) {
            e.getKey();
            e.getValue();
        }

        // LinkedHashMap → insertion order
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();

        // TreeMap → sorted by key
        Map<String, Integer> treeMap = new TreeMap<>();


        /* =========================================================
           🔟 THREAD-SAFE MAP
        ========================================================= */

        Map<Integer, String> concurrentMap =
                new ConcurrentHashMap<>();


        /* =========================================================
           1️⃣1️⃣ COLLECTION vs COLLECTIONS
        ========================================================= */

        // Collection → interface
        Collection<Integer> col = new ArrayList<>();

        // Collections → utility class
        Collections.sort(arrayList);
        Collections.max(arrayList);
        Collections.min(arrayList);
        Collections.frequency(arrayList, 10);


        /* =========================================================
           1️⃣2️⃣ ARRAYS UTILITY CLASS
        ========================================================= */

        int[] arr = {5, 3, 2, 4, 1};
        Arrays.sort(arr);
        Arrays.binarySearch(arr, 3);
        Arrays.fill(arr, 9);


        /* =========================================================
           1️⃣3️⃣ COMPARABLE vs COMPARATOR
        ========================================================= */

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Anuj", 2));
        studentList.add(new Student("Ramesh", 1));

        // Comparable → natural sorting
        Collections.sort(studentList);

        // Comparator → custom sorting
        Collections.sort(studentList,
                (s1, s2) -> s1.name.compareTo(s2.name));


        /* =========================================================
           1️⃣4️⃣ MOST COMMON REAL-WORLD PATTERN
           Frequency count
        ========================================================= */

        int[] nums = {1, 2, 2, 3, 1};
        Map<Integer, Integer> freq = new HashMap<>();

        for (int n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }


        /* =========================================================
           1️⃣5️⃣ TIME COMPLEXITY (REMEMBER)
           ArrayList search → O(n)
           HashMap lookup → O(1)
           TreeMap → O(log n)
        ========================================================= */

    }
}


/* =========================================================
   STUDENT CLASS
========================================================= */

class Student implements Comparable<Student> {

    String name;
    int rollNo;

    Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    // natural sorting
    public int compareTo(Student s) {
        return this.rollNo - s.rollNo;
    }

    // logical equality
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student s = (Student) o;
        return rollNo == s.rollNo;
    }

    // bucket location
    public int hashCode() {
        return Objects.hash(rollNo);
    }

    public String toString() {
        return name + "-" + rollNo;
    }
}
