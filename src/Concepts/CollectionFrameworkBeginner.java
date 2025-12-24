package Concepts;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ═══════════════════════════════════════════════════════════
 * JAVA COLLECTION FRAMEWORK - BEGINNER FRIENDLY NOTES
 * ═══════════════════════════════════════════════════════════
 *
 * What is Collection Framework?
 * → Pre-built data structures to store groups of objects
 * → Like ready-made containers for your data
 *
 * Why use it?
 * → Don't write your own ArrayList, HashMap from scratch
 * → Tested, optimized, and ready to use
 * → Saves time and reduces bugs
 */

public class CollectionFrameworkBeginner {

    public static void main(String[] args) {

        /* ═══════════════════════════════════════════════════════
           📚 1. COLLECTION HIERARCHY (THE FAMILY TREE)
           Link : https://www.tutorialspoint.com/java/images/hierarchy-of-collection-framework.jpg
           ═══════════════════════════════════════════════════════
           Interface - an abstract type that is used to declare a behavior that classes must implement


           Collection (Interface) - Top parent
               ├── List (Interface) → Order matters, duplicates OK
               │     ├── ArrayList
               │     ├── LinkedList
               │     └── Vector (old, don't use)
               │
               ├── Set (Interface) → No duplicates allowed
               │     ├── HashSet (random order)
               │     ├── LinkedHashSet (insertion order)
               │     └── TreeSet (sorted order)
               │
               └── Queue (Interface) → FIFO (First In First Out)
                     ├── LinkedList (can be used as Queue)
                     ├── PriorityQueue (priority-based)
                     └── ArrayDeque (modern choice)

           Map (Interface) - Separate family (key-value pairs)
               ├── HashMap (random order)
               ├── LinkedHashMap (insertion order)
               └── TreeMap (sorted by keys)
        */


        /* ═══════════════════════════════════════════════════════
           📋 2. ARRAYLIST - Most Common and Beginner Friendly
           ═══════════════════════════════════════════════════════

           What: Dynamic array (size grows automatically)
           When: Need list that can grow, fast access by index
           Example: Storing list of students, products, etc.
        */

        // Creating an ArrayList of integers
        List<Integer> numbers = new ArrayList<>();

        // Adding elements (like putting items in a bag)
        numbers.add(10);        // [10]
        numbers.add(20);        // [10, 20]
        numbers.add(30);        // [10, 20, 30]
        numbers.add(20);        // [10, 20, 30, 20] - duplicates allowed!

        // Accessing elements by index (like array)
        int firstNumber = numbers.get(0);     // Gets 10 (index starts at 0)
        int secondNumber = numbers.get(1);    // Gets 20

        // Updating element at specific position
        numbers.set(1, 25);     // Changes 20 to 25 → [10, 25, 30, 20]

        // Checking if element exists
        boolean hasThirty = numbers.contains(30);  // true
        boolean hasFifty = numbers.contains(50);   // false

        // Getting size (how many elements)
        int size = numbers.size();              // 4

        // Checking if empty
        boolean empty = numbers.isEmpty();      // false

        // Removing elements
        numbers.remove(1);                      // Removes element at index 1
        // Result: [10, 30, 20]

        numbers.remove(Integer.valueOf(30));    // Removes value 30 (not index!)
        // Result: [10, 20]

        // Clearing all elements
        // numbers.clear();                     // Makes list empty []


        // ─────────────────────────────────────────────────────
        // ITERATING (Going through each element)
        // ─────────────────────────────────────────────────────

        // Method 1: For loop with index
        System.out.println("Method 1: Using index");
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println(numbers.get(i));
        }

        // Method 2: Enhanced for loop (easiest!)
        System.out.println("Method 2: Enhanced for loop");
        for (Integer num : numbers) {
            System.out.println(num);
        }

        // Method 3: Iterator (old style, but still used)
        System.out.println("Method 3: Iterator");
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            System.out.println(num);
        }


        /* ═══════════════════════════════════════════════════════
           🔗 3. LINKEDLIST - Good for Frequent Insert/Delete
           ═══════════════════════════════════════════════════════

           What: Elements linked like a chain
           When: Need to add/remove from middle frequently
           Note: Slower for accessing by index than ArrayList
        */

        List<String> names = new LinkedList<>();
        names.add("Alice");      // [Alice]
        names.add("Bob");        // [Alice, Bob]
        names.add("Charlie");    // [Alice, Bob, Charlie]

        // LinkedList has special methods
        LinkedList<String> linkedNames = new LinkedList<>();
        linkedNames.addFirst("First");   // Add at beginning
        linkedNames.addLast("Last");     // Add at end
        linkedNames.removeFirst();       // Remove from beginning
        linkedNames.removeLast();        // Remove from end
        linkedNames.remove();            //Removes first element



        /* ═══════════════════════════════════════════════════════
           📚 4. STACK - LIFO (Last In First Out)
           ═══════════════════════════════════════════════════════

           What: Like stack of plates (add/remove from top only)
           When: Undo operations, function calls, expression evaluation
           Note: Legacy class, prefer Deque for new code

           Memory trick: Stack of plates 🍽️
        */

        Stack<Integer> stack = new Stack<>();

        stack.push(10);     // Add to top → [10]
        stack.push(20);     // Add to top → [10, 20]
        stack.push(30);     // Add to top → [10, 20, 30]

        int top = stack.peek();      // See top without removing → 30
        int removed = stack.pop();   // Remove and return top → 30
        // Stack now: [10, 20]

        boolean stackEmpty = stack.isEmpty();  // Check if empty


        /* ═══════════════════════════════════════════════════════
           🎫 5. QUEUE - FIFO (First In First Out)
           ═══════════════════════════════════════════════════════

           What: Like line at ticket counter (first come, first served)
           When: Task scheduling, BFS algorithm, print queue

           Memory trick: Movie theater line 🎬
        */

        Queue<String> queue = new LinkedList<>();

        queue.offer("First Person");   // Add to end (rear)
        queue.offer("Second Person");  // Add to end
        queue.offer("Third Person");   // Add to end

        String front = queue.peek();   // See front person → "First Person"
        String served = queue.poll();  // Remove front → "First Person"
        // Queue now: [Second, Third]

        // NOTE: offer/poll/peek don't throw exceptions (safer)
        // add/remove/element throw exceptions if operation fails


        /* ═══════════════════════════════════════════════════════
           🏥 6. PRIORITY QUEUE - Priority Based Processing
           ═══════════════════════════════════════════════════════

           What: Elements come out in priority order (not insertion order)
           When: Always need smallest/largest element fast
           Default: Min Heap (smallest comes out first)

           Memory trick: Hospital emergency 🏥 (critical first)
        */

        Queue<Integer> minHeap = new PriorityQueue<>();

        minHeap.offer(40);     // Add elements in any order
        minHeap.offer(10);
        minHeap.offer(30);
        minHeap.offer(20);

        System.out.println(minHeap.poll());  // 10 (smallest)
        System.out.println(minHeap.poll());  // 20 (next smallest)
        System.out.println(minHeap.poll());  // 30
        System.out.println(minHeap.poll());  // 40

        // For Max Heap (largest first)
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.offer(40);
        maxHeap.offer(10);
        maxHeap.offer(30);

        System.out.println(maxHeap.poll());  // 40 (largest)


        /* ═══════════════════════════════════════════════════════
           ⚡ 7. ARRAYDEQUE - Modern Stack & Queue
           ═══════════════════════════════════════════════════════

           What: Can add/remove from both ends (double-ended queue)
           When: Need modern Stack or Queue implementation
           Best: Faster than Stack and LinkedList
        */

        Deque<Integer> deque = new ArrayDeque<>();

        // Use as Stack (add/remove from one end)
        deque.push(10);         // [10]
        deque.push(20);         // [20, 10]
        deque.pop();            // Returns 20, deque: [10]

        // Use as Queue (add one end, remove other end)
        deque.offerLast(30);    // Add to end: [10, 30]
        deque.offerLast(40);    // Add to end: [10, 30, 40]
        deque.pollFirst();      // Remove from front: returns 10

        // Can work from both ends!
        deque.offerFirst(5);    // Add to front: [5, 30, 40]
        deque.offerLast(50);    // Add to end: [5, 30, 40, 50]


        /* ═══════════════════════════════════════════════════════
           🚫 8. HASHSET - No Duplicates Allowed
           ═══════════════════════════════════════════════════════

           What: Collection that stores only unique elements
           When: Need to remove duplicates, check if exists
           Note: No specific order maintained

           Memory trick: Club with no-duplicate-entry policy 🚫
        */

        Set<String> uniqueNames = new HashSet<>();

        uniqueNames.add("Alice");      // Added → [Alice]
        uniqueNames.add("Bob");        // Added → [Alice, Bob]
        uniqueNames.add("Alice");      // IGNORED (duplicate!)
        // Still: [Alice, Bob]

        System.out.println(uniqueNames.size());  // 2 (not 3!)

        // Check if element exists (VERY FAST - O(1))
        boolean hasAlice = uniqueNames.contains("Alice");  // true
        boolean hasCharlie = uniqueNames.contains("Charlie");  // false

        // Remove element
        uniqueNames.remove("Bob");     // [Alice]

        // Common use case: Remove duplicates from list
        List<Integer> numbersWithDups = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        Set<Integer> uniqueNumbers = new HashSet<>(numbersWithDups);
        // uniqueNumbers now: [1, 2, 3, 4]


        /* ═══════════════════════════════════════════════════════
           🎫 9. LINKEDHASHSET - Unique + Maintains Order
           ═══════════════════════════════════════════════════════

           What: HashSet + remembers insertion order
           When: Need unique elements AND order matters
        */

        Set<Integer> orderedSet = new LinkedHashSet<>();
        orderedSet.add(30);
        orderedSet.add(10);
        orderedSet.add(20);
        orderedSet.add(10);  // Duplicate, ignored

        System.out.println(orderedSet);  // [30, 10, 20] - order maintained!


        /* ═══════════════════════════════════════════════════════
           📚 10. TREESET - Unique + Sorted
           ═══════════════════════════════════════════════════════

           What: Set that keeps elements in sorted order
           When: Need unique elements in sorted order
           Note: Cannot store null

           Memory trick: Books on sorted shelf 📖
        */

        Set<Integer> sortedSet = new TreeSet<>();
        sortedSet.add(30);
        sortedSet.add(10);
        sortedSet.add(50);
        sortedSet.add(20);

        System.out.println(sortedSet);  // [10, 20, 30, 50] - automatically sorted!

        // Special methods for TreeSet
        TreeSet<Integer> treeSet = new TreeSet<>(sortedSet);
        int smallest = treeSet.first();      // 10
        int largest = treeSet.last();        // 50
        int higher = treeSet.higher(20);     // 30 (next higher than 20)
        int lower = treeSet.lower(30);       // 20 (next lower than 30)


        /* ═══════════════════════════════════════════════════════
           🎓 11. CUSTOM OBJECTS IN SET - Important!
           ═══════════════════════════════════════════════════════

           Problem: HashSet uses equals() and hashCode() to check duplicates
           Solution: Override these methods in your class

           Without override: Two students with same rollNo treated as different
           With override: Same rollNo = duplicate (ignored)
        */

        Set<Student> students = new HashSet<>();

        students.add(new Student("Anuj", 1));
        students.add(new Student("Rohit", 1));    // Same rollNo = duplicate!
        // Will be ignored

        students.add(new Student("Priya", 2));    // Different rollNo = added

        System.out.println(students.size());      // 2 (not 3!)


        /* ═══════════════════════════════════════════════════════
           📖 12. HASHMAP - Key-Value Storage
           ═══════════════════════════════════════════════════════

           What: Like a dictionary (word → meaning)
           When: Need to store pairs, fast lookup by key

           Memory trick: Phone directory 📱 (name → phone number)
        */

        Map<String, Integer> scores = new HashMap<>();

        // Adding key-value pairs (put = add/update)
        scores.put("Alice", 95);      // {"Alice": 95}
        scores.put("Bob", 87);        // {"Alice": 95, "Bob": 87}
        scores.put("Charlie", 92);    // {"Alice": 95, "Bob": 87, "Charlie": 92}

        scores.put("Alice", 98);      // Updates Alice's score to 98
        // Keys are unique, values can repeat!

        // Getting value by key
        int aliceScore = scores.get("Alice");      // 98
        Integer daveScore = scores.get("Dave");    // null (key not found)

        // Safe get with default value
        int score = scores.getOrDefault("Dave", 0);  // Returns 0 if not found

        // Checking if key or value exists
        boolean hasAlice1 = scores.containsKey("Alice");        // true
        boolean hasScore100 = scores.containsValue(100);       // false

        // Removing entry
        scores.remove("Bob");         // Removes Bob's entry

        // Safe insert (only if key doesn't exist)
        scores.putIfAbsent("Alice", 100);  // Won't change (Alice exists)
        scores.putIfAbsent("Dave", 88);    // Will add (Dave doesn't exist)


        // ─────────────────────────────────────────────────────
        // ITERATING THROUGH MAP (3 ways)
        // ─────────────────────────────────────────────────────

        // Method 1: Iterate through entries (most common)
        System.out.println("All entries:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            String name = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(name + " scored " + value);
        }

        // Method 2: Iterate through keys only
        System.out.println("All keys:");
        for (String name : scores.keySet()) {
            System.out.println(name);
        }

        // Method 3: Iterate through values only
        System.out.println("All values:");
        for (Integer value : scores.values()) {
            System.out.println(value);
        }


        /* ═══════════════════════════════════════════════════════
           📋 13. LINKEDHASHMAP - HashMap + Maintains Order
           ═══════════════════════════════════════════════════════

           What: HashMap that remembers insertion order
           When: Need key-value pairs in specific order
        */

        Map<Integer, String> orderedMap = new LinkedHashMap<>();
        orderedMap.put(3, "Three");
        orderedMap.put(1, "One");
        orderedMap.put(2, "Two");

        System.out.println(orderedMap);  // {3=Three, 1=One, 2=Two} - order kept!


        /* ═══════════════════════════════════════════════════════
           📚 14. TREEMAP - Sorted by Keys
           ═══════════════════════════════════════════════════════

           What: Map with keys in sorted order
           When: Need key-value pairs sorted by keys
        */

        Map<Integer, String> sortedMap = new TreeMap<>();
        sortedMap.put(3, "Three");
        sortedMap.put(1, "One");
        sortedMap.put(2, "Two");

        System.out.println(sortedMap);  // {1=One, 2=Two, 3=Three} - sorted by keys!


        /* ═══════════════════════════════════════════════════════
           🔒 15. THREAD-SAFE MAP (For Multi-Threading)
           ═══════════════════════════════════════════════════════

           What: HashMap that multiple threads can use safely
           When: Multiple threads need to access/modify map
           Note: Don't worry about this until you learn multithreading
        */

        Map<Integer, String> threadSafeMap = new ConcurrentHashMap<>();
        threadSafeMap.put(1, "One");
        threadSafeMap.put(2, "Two");


        /* ═══════════════════════════════════════════════════════
           ⚖️ 16. COLLECTION vs COLLECTIONS (Confusing Names!)
           ═══════════════════════════════════════════════════════

           Collection → Interface (parent of List, Set, Queue)
           Collections → Utility class with helpful methods
        */

        // Collection - interface (blueprint)
        Collection<Integer> col = new ArrayList<>();
        col.add(10);

        // Collections - utility class (helper methods)
        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));

        Collections.sort(nums);              // Sorts the list
        int max = Collections.max(nums);     // Finds maximum
        int min = Collections.min(nums);     // Finds minimum

        int frequency = Collections.frequency(nums, 5);  // How many times 5 appears

        Collections.reverse(nums);           // Reverse the list
        Collections.shuffle(nums);           // Randomly shuffle


        /* ═══════════════════════════════════════════════════════
           🔧 17. ARRAYS UTILITY CLASS
           ═══════════════════════════════════════════════════════

           What: Helper methods for arrays (not Collections!)
           When: Working with regular arrays
        */

        int[] arr = {5, 3, 2, 4, 1};

        Arrays.sort(arr);                    // Sorts array: [1, 2, 3, 4, 5]

        int index = Arrays.binarySearch(arr, 3);  // Finds 3, returns index 2
        // NOTE: Array must be sorted!

        Arrays.fill(arr, 9);                 // Fills all with 9: [9, 9, 9, 9, 9]

        String arrString = Arrays.toString(arr);  // Converts to string for printing


        /* ═══════════════════════════════════════════════════════
           📊 18. COMPARABLE vs COMPARATOR (Sorting Custom Objects)
           ═══════════════════════════════════════════════════════

           Comparable: Natural sorting (one way only)
                      → Implement in the class itself

           Comparator: Custom sorting (multiple ways)
                      → Create separate sorting logic
        */

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Anuj", 3));
        studentList.add(new Student("Ramesh", 1));
        studentList.add(new Student("Priya", 2));

        // Comparable: Natural sorting (defined in Student class)
        // Sorts by rollNo (default behavior)
        Collections.sort(studentList);
        System.out.println("Sorted by rollNo: " + studentList);

        // Comparator: Custom sorting (on-the-fly)
        // Sort by name instead of rollNo
        Collections.sort(studentList,
                (s1, s2) -> s1.name.compareTo(s2.name));
        System.out.println("Sorted by name: " + studentList);

        // Another Comparator: Sort by name length
        Collections.sort(studentList,
                (s1, s2) -> Integer.compare(s1.name.length(), s2.name.length()));


        /* ═══════════════════════════════════════════════════════
           🔥 19. MOST COMMON PATTERN - Frequency Count
           ═══════════════════════════════════════════════════════

           Problem: Count how many times each element appears
           Solution: Use HashMap<Element, Count>

           This pattern appears in 80% of DSA problems!
        */

        int[] numbers2 = {1, 2, 2, 3, 1, 4, 1, 3};

        // Create frequency map
        Map<Integer, Integer> frequency1 = new HashMap<>();

        for (int num : numbers2) {
            // If num exists, get its count and add 1
            // If num doesn't exist, start with 0 and add 1
            frequency1.put(num, frequency1.getOrDefault(num, 0) + 1);
        }

        System.out.println("Frequency map: " + frequency1);
        // Output: {1=3, 2=2, 3=2, 4=1}
        // Meaning: 1 appears 3 times, 2 appears 2 times, etc.


        /* ═══════════════════════════════════════════════════════
           ⏱️ 20. TIME COMPLEXITY - Remember These!
           ═══════════════════════════════════════════════════════

           ArrayList:
           - Access by index: O(1) - FAST
           - Search: O(n) - SLOW
           - Add at end: O(1) - FAST
           - Add at middle: O(n) - SLOW

           LinkedList:
           - Access by index: O(n) - SLOW
           - Add/remove at ends: O(1) - FAST

           HashSet/HashMap:
           - Add: O(1) - FAST
           - Remove: O(1) - FAST
           - Search/Contains: O(1) - FAST (This is why we use them!)

           TreeSet/TreeMap:
           - Add: O(log n)
           - Remove: O(log n)
           - Search: O(log n)

           Interview tip: When you need FAST lookup, use Hash structures!
        */


        /* ═══════════════════════════════════════════════════════
           ✅ 21. QUICK DECISION GUIDE
           ═══════════════════════════════════════════════════════

           Need to store list → ArrayList
           Need unique items → HashSet
           Need key-value → HashMap
           Need sorted → TreeSet or TreeMap
           Need fast lookup → HashMap or HashSet
           Need FIFO queue → LinkedList or ArrayDeque
           Need priority → PriorityQueue
           Need LIFO stack → ArrayDeque (push/pop)

           Remember: 90% of time you'll use ArrayList, HashMap, HashSet!
        */

    } // End of main method


    /* ═══════════════════════════════════════════════════════
       📝 PRACTICE PROBLEMS TO TRY:
       ═══════════════════════════════════════════════════════

       1. Remove duplicates from an array using HashSet
       2. Find frequency of each character in a string using HashMap
       3. Implement a simple Queue using LinkedList
       4. Find kth largest element using PriorityQueue
       5. Check if two strings are anagrams using HashMap
    */

} // End of class


/* ═══════════════════════════════════════════════════════════
   👨‍🎓 STUDENT CLASS - For Custom Object Examples
   ═══════════════════════════════════════════════════════════ */

class Student implements Comparable<Student> {

    String name;
    int rollNo;

    // Constructor - creates a Student object
    Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    // ─────────────────────────────────────────────────────
    // COMPARABLE: Natural sorting (default sorting behavior)
    // ─────────────────────────────────────────────────────

    /**
     * Defines how Students are sorted by default
     * Here: Sort by rollNo (ascending)
     *
     * Returns:
     * - Negative if this student should come before other
     * - Zero if they are equal
     * - Positive if this student should come after other
     */
    @Override
    public int compareTo(Student other) {
        return this.rollNo - other.rollNo;
    }

    // ─────────────────────────────────────────────────────
    // EQUALS: Logical equality (are two students same?)
    // ─────────────────────────────────────────────────────

    /**
     * Two students are equal if they have same rollNo
     * This is used by HashSet to check duplicates
     */
    @Override
    public boolean equals(Object obj) {
        // Step 1: Check if same object in memory
        if (this == obj) return true;

        // Step 2: Check if obj is a Student
        if (!(obj instanceof Student)) return false;

        // Step 3: Cast and compare rollNo
        Student other = (Student) obj;
        return this.rollNo == other.rollNo;
    }

    // ─────────────────────────────────────────────────────
    // HASHCODE: Bucket location in HashMap/HashSet
    // ─────────────────────────────────────────────────────

    /**
     * Generates a number used by HashSet/HashMap
     * Rule: If two objects are equal, they must have same hashCode
     * Here: Based on rollNo
     */
    @Override
    public int hashCode() {
        return Objects.hash(rollNo);
    }

    // ─────────────────────────────────────────────────────
    // TOSTRING: How Student is printed
    // ─────────────────────────────────────────────────────

    /**
     * Returns string representation of Student
     * Without this, would print: Student@4a574795 (ugly!)
     * With this, prints: Anuj-1 (readable!)
     */
    @Override
    public String toString() {
        return name + "-" + rollNo;
    }
}


/* ═══════════════════════════════════════════════════════════
   🎯 INTERVIEW QUICK TIPS:
   ═══════════════════════════════════════════════════════════

   1. "Remove duplicates" → Think HashSet
   2. "Count frequency" → Think HashMap
   3. "Need sorted" → Think TreeSet/TreeMap
   4. "Fast lookup" → Think HashMap/HashSet
   5. "FIFO processing" → Think Queue
   6. "Kth largest/smallest" → Think PriorityQueue

   Common mistakes:
   - Using ArrayList when HashSet would be faster
   - Forgetting equals() and hashCode() for custom objects in Set/Map
   - Using == instead of equals() for String comparison

   Remember:
   - Collection = Interface (singular)
   - Collections = Utility class (plural)
*/