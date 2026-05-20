# 30-DAY FULL STACK / JAVA BACKEND INTERVIEW PREPARATION ROADMAP
### Tailored for Dikshant Koriwar | B.E. IT | CGPA 8.34 | 2025 Graduate
### Target Roles: Full Stack Developer | Java Backend Developer | Spring Boot Developer | SDE Fresher

---

> **Goal:** Become 90% interview-ready in 30 days — capable of clearing OAs, Coding Rounds, Technical Interviews, Project Discussions, and HR Rounds for Java/Spring Boot/Full Stack roles.

---

## OVERVIEW & WEEKLY GOALS

| Week | Theme | Focus |
|------|-------|-------|
| Week 1 (Days 1–7) | Foundation Solidification | Java Core, DSA Basics, Spring Boot Fundamentals, SQL, React Basics |
| Week 2 (Days 8–14) | Deep Technical Mastery | Advanced Java, Spring Boot Advanced, DSA Medium, System Design Intro |
| Week 3 (Days 15–21) | Project Deep Dive + CS Fundamentals | Project Articulation, DBMS/OS/CN, DSA Medium-Hard, Upskilling |
| Week 4 (Days 22–30) | Mock Interviews + Final Polish | Mock Sessions, Resume Revision, HR Prep, Communication, Final Revision |

---

## DAILY TIME BLOCK TEMPLATE (8–10 Hours/Day)

| Time Block | Duration | Activity |
|------------|----------|----------|
| 6:00 AM – 7:30 AM | 1.5 hrs | DSA (LeetCode problems) |
| 7:30 AM – 9:00 AM | 1.5 hrs | Java / Spring Boot Concepts |
| 9:00 AM – 10:30 AM | 1.5 hrs | Database / CS Fundamentals |
| 10:30 AM – 12:00 PM | 1.5 hrs | React / Frontend / Upskilling |
| 12:00 PM – 1:00 PM | 1 hr | Break + Lunch |
| 1:00 PM – 2:30 PM | 1.5 hrs | Project Discussion Preparation |
| 2:30 PM – 4:00 PM | 1.5 hrs | Interview Q&A Practice |
| 4:00 PM – 5:00 PM | 1 hr | HR / Communication / Behavioral |
| 5:00 PM – 6:00 PM | 1 hr | Daily Revision + Notes |

---

# WEEK 1: FOUNDATION SOLIDIFICATION (Days 1–7)

**Week 1 Goal:** Build a rock-solid foundation in Java Core, Spring Boot Basics, SQL, React Fundamentals, and DSA Easy/Medium problems. Start articulating your projects clearly.

---

## DAY 1 — Java OOP Deep Dive + Arrays DSA

### DSA (1.5 hrs)
**Topic:** Arrays
- Solve: Two Sum (LeetCode #1), Best Time to Buy and Sell Stock (#121), Contains Duplicate (#217)
- Solve: Maximum Subarray (Kadane's Algorithm) (#53), Move Zeroes (#283)
- **Target:** 5 Easy problems, timed at 15–20 min each
- **Approach:** Before coding, always say out loud: "My approach is…" — practice thinking aloud

### Java (1.5 hrs)
**Topic:** OOP — The 4 Pillars in Depth
- **Encapsulation:** Private fields, public getters/setters, why it matters in Spring beans
- **Inheritance:** extends keyword, method overriding, super keyword, constructor chaining
- **Polymorphism:** Compile-time (overloading) vs Runtime (overriding), dynamic dispatch
- **Abstraction:** Abstract classes vs Interfaces, when to use which
- **Key distinctions:** Abstract class can have constructors; Interface (Java 8+) can have default/static methods
- Practice: Write code for a small Animal → Dog/Cat hierarchy demonstrating all 4 pillars

**Interview Questions to Practice Answering (Out Loud):**
1. What is the difference between abstraction and encapsulation?
2. Can we instantiate an abstract class? Why not?
3. What is method overriding vs overloading?
4. What is the difference between interface and abstract class in Java 8+?
5. What is dynamic method dispatch?

**Mistake to Avoid:** Don't just define OOP concepts — always give a real-world example and then tie it to your project (BookMySeat uses OOP extensively — mention entities like Show, Seat, Booking).

### Database (1.5 hrs)
**Topic:** SQL Basics + Relationships
- Revise: SELECT, WHERE, GROUP BY, HAVING, ORDER BY, LIMIT
- Practice: Write queries for: "Find all movies with more than 100 bookings", "Get top 5 theaters by revenue"
- Understand: One-to-One, One-to-Many, Many-to-Many relationships
- Revise: Primary Key, Foreign Key, Unique, Not Null constraints
- Relate to your project: Draw the BookMySeat ER diagram (Users, Movies, Theaters, Screens, Seats, Shows, Bookings)

### React (1.5 hrs)
**Topic:** Components, Props, State
- Revise: Functional components vs Class components
- Revise: Props — passing data parent to child, prop drilling
- Revise: useState hook — how state updates trigger re-render
- Practice: Create a simple MovieCard component that receives movie name, genre, rating as props

### Project Discussion Prep (1.5 hrs)
**BookMySeat — Architecture Overview**
- Practice explaining this in 2–3 minutes:
  > "BookMySeat is a full-stack movie ticket booking application. The backend is built with Java 21 and Spring Boot, using a layered Controller-Service-Repository architecture. The frontend is built with React.js. The database is MySQL. I exposed 20+ RESTful APIs covering 8 domain modules: Users, Cities, Movies, Theaters, Screens, Seats, Shows, and Bookings."
- Draw and memorize the layered architecture flow: Request → Controller → Service → Repository → DB

### Interview Q&A (1 hr)
**Top Java OOP Questions to Answer:**
1. Explain OOP with a real-world example from your project
2. What is the IS-A vs HAS-A relationship?
3. Can an interface extend another interface?
4. What is a marker interface? Give examples.
5. What is the difference between final, finally, finalize?

### HR / Communication (30 min)
- Write your "Tell me about yourself" answer (2 minutes max)
- Structure: Name → Education → Internship → Projects → Skills → Why this role
- Practice saying it out loud 3 times

### Daily Revision (30 min)
- Write 5 OOP definitions in your own words
- Note 3 things you weren't sure about and look them up

---

## DAY 2 — Java Collections + Strings + HashMap Internals

### DSA (1.5 hrs)
**Topic:** Strings + HashMap
- Solve: Valid Anagram (#242), Ransom Note (#383), Group Anagrams (#49)
- Solve: Two Sum using HashMap (#1 again — now implement HashMap approach), Longest Substring Without Repeating Characters (#3)
- **Target:** 4–5 problems
- **Focus:** Practice verbalizing your HashMap approach: "I'll use a HashMap to store frequency/index because lookup is O(1)"

### Java (1.5 hrs)
**Topic:** Collections Framework Deep Dive
- **List:** ArrayList vs LinkedList — internal structure, when to use each
- **Set:** HashSet (no order, O(1)), LinkedHashSet (insertion order), TreeSet (sorted, O(log n))
- **Map:** HashMap (how it works internally — buckets, hashing, load factor, rehashing), LinkedHashMap, TreeMap
- **Queue/Deque:** PriorityQueue, ArrayDeque
- **HashMap Internals (Critical):**
  - Array of Node objects (buckets), hash function, collision handling (chaining → treeification in Java 8+)
  - Default capacity 16, load factor 0.75, threshold = 16 × 0.75 = 12
  - When threshold is crossed → resize to 2× and rehash
  - Why HashMap is not thread-safe → use ConcurrentHashMap

**Interview Questions:**
1. How does HashMap work internally?
2. What happens when two keys have the same hash code?
3. What is the difference between HashMap and Hashtable?
4. How do you make a HashMap thread-safe?
5. What is the difference between fail-fast and fail-safe iterators?
6. Explain the difference between Comparable and Comparator.

### Database (1.5 hrs)
**Topic:** SQL Joins
- Master: INNER JOIN, LEFT JOIN, RIGHT JOIN, FULL OUTER JOIN, SELF JOIN, CROSS JOIN
- Practice query: "Get all bookings with user name, movie name, screen name, and seat number" (multi-table JOIN from BookMySeat schema)
- Practice: "Get all movies that have never been booked" (LEFT JOIN + WHERE IS NULL)
- Understand: JOIN vs Subquery — when to prefer each

### React (1.5 hrs)
**Topic:** useEffect Hook
- Revise: Side effects (API calls, subscriptions, timers)
- Understand: Dependency array [] — runs once; [variable] — runs on change; no array — runs every render
- Practice: Create a component that fetches movie data from a mock API on mount
- Revise: Cleanup function in useEffect (prevent memory leaks)

### Project Discussion Prep (1.5 hrs)
**BookMySeat — API Design**
- List and practice explaining your 20+ APIs grouped by module:
  - User Module: Register, Login, Get Profile, Update Profile
  - Movie Module: Create Movie, Get All Movies, Get by City/Genre, Update, Delete
  - Show Module: Create Show, Get Shows by Movie/Theater/Date
  - Booking Module: Book Seat, Cancel Booking, Get Booking History
- For each API, know: HTTP method, URL pattern, request body, response body, status codes

**Expected Interview Question:** "Walk me through the API design of BookMySeat."
**Your Answer Structure:** Domain → Resources → HTTP verbs → URL conventions → Request/Response format

### Interview Q&A (1 hr)
1. What is the Java Collections hierarchy?
2. What is the difference between List, Set, and Map?
3. When would you use a LinkedList over an ArrayList?
4. What is ConcurrentModificationException and how do you avoid it?
5. How does TreeMap maintain sorted order?

### HR / Communication (30 min)
- Practice: "Why do you want to work as a Java Backend Developer?"
- Write and say out loud 3 times

### Daily Revision (30 min)
- Flashcard: HashMap internals (5 bullet points max)

---

## DAY 3 — Exception Handling + Multithreading + Sliding Window DSA

### DSA (1.5 hrs)
**Topic:** Sliding Window + Two Pointers
- Solve: Maximum Average Subarray I (#643), Minimum Size Subarray Sum (#209)
- Solve: Valid Palindrome (#125), 3Sum (#15 — optional if time permits)
- Solve: Container With Most Water (#11)
- **Focus:** Identify sliding window pattern: "When do I shrink/expand the window?"

### Java (1.5 hrs)
**Topic:** Exception Handling
- **Exception hierarchy:** Throwable → Error / Exception → Checked / Unchecked
- **Checked Exceptions:** Must be declared or caught (IOException, SQLException)
- **Unchecked (RuntimeException):** NullPointerException, ArrayIndexOutOfBoundsException, IllegalArgumentException
- **Keywords:** try, catch, finally, throw, throws
- **Custom Exceptions:** How to create them (extend RuntimeException or Exception)
- **Best practices:** Catch specific exceptions, don't swallow exceptions, use finally for cleanup
- **In your project:** You used @RestControllerAdvice — explain how centralized exception handling works

**Interview Questions:**
1. What is the difference between checked and unchecked exceptions?
2. Can we have a try block without catch?
3. What is the difference between throw and throws?
4. What happens if an exception is thrown in a finally block?
5. How did you implement centralized exception handling in Spring Boot?

### Database (1.5 hrs)
**Topic:** Normalization
- 1NF: Atomic values, no repeating groups
- 2NF: 1NF + No partial dependency (all non-key attributes depend on the whole primary key)
- 3NF: 2NF + No transitive dependency
- BCNF: Stronger form of 3NF
- Denormalization: When and why (performance trade-off)
- Practice: Normalize the BookMySeat Booking table to 3NF

### React (1.5 hrs)
**Topic:** API Integration + Routing
- Revise: Axios / fetch API — GET, POST, PUT, DELETE calls
- Revise: React Router — BrowserRouter, Route, Link, useNavigate, useParams
- Practice: Create a simple app with a MovieList page and MovieDetail page with routing

### Project Discussion Prep (1.5 hrs)
**BookMySeat — Database Schema**
- Draw and practice explaining the schema:
  - Users: id, name, email, password, role, createdAt
  - Cities: id, name
  - Movies: id, title, genre, duration, language, releaseDate
  - Theaters: id, name, cityId (FK), address
  - Screens: id, name, theaterId (FK), capacity
  - Seats: id, screenId (FK), seatNumber, type (REGULAR/PREMIUM/VIP)
  - Shows: id, movieId (FK), screenId (FK), showTime, price
  - Bookings: id, userId (FK), showId (FK), seatId (FK), status, bookingTime, totalAmount
- Practice explaining relationships: "Booking is the central joining entity between User, Show, and Seat"

### Interview Q&A (1 hr)
1. What is @RestControllerAdvice? How is it different from @ExceptionHandler?
2. What is @ControllerAdvice?
3. How do you return custom error responses from a Spring Boot API?
4. What are the best practices for exception handling in REST APIs?
5. How did you handle exceptions in your internship project?

### HR Prep (30 min)
- Practice: "Tell me about your internship experience"
- Use STAR format: Situation → Task → Action → Result

### Daily Revision (30 min)
- Revise Exception hierarchy with examples

---

## DAY 4 — Java 8 Features + Streams API + Stack/Queue DSA

### DSA (1.5 hrs)
**Topic:** Stack and Queue
- Solve: Valid Parentheses (#20), Min Stack (#155), Implement Queue using Stacks (#232)
- Solve: Daily Temperatures (#739), Next Greater Element I (#496)
- **Focus:** Monotonic stack pattern recognition

### Java (1.5 hrs)
**Topic:** Java 8 Features — This is a VERY common interview topic
- **Lambda Expressions:** Functional interface, syntax, replacing anonymous classes
- **Functional Interfaces:** Predicate<T>, Function<T,R>, Consumer<T>, Supplier<T>, BiFunction
- **Stream API:**
  - Intermediate: filter(), map(), flatMap(), distinct(), sorted(), limit(), skip()
  - Terminal: collect(), forEach(), count(), min(), max(), reduce(), anyMatch(), allMatch()
  - Collectors: toList(), toMap(), groupingBy(), joining()
- **Optional:** Avoid NullPointerException — isPresent(), orElse(), orElseGet(), map()
- **Default and Static methods in interfaces**
- **Method References:** ClassName::methodName

**Practice Code:**
```java
// Filter employees with salary > 50000 and collect names
List<String> result = employees.stream()
    .filter(e -> e.getSalary() > 50000)
    .map(Employee::getName)
    .sorted()
    .collect(Collectors.toList());

// Group employees by department
Map<String, List<Employee>> byDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
```

**Interview Questions:**
1. What is a functional interface? Give examples.
2. What is the difference between map() and flatMap()?
3. What is the difference between intermediate and terminal operations in streams?
4. What is Optional and why was it introduced?
5. What are method references and when would you use them?
6. How does stream() differ from parallelStream()?

### Database (1.5 hrs)
**Topic:** Indexing + Query Optimization
- What is an index? How does it speed up reads?
- B-Tree index (default in MySQL) vs Hash index
- Primary index vs Secondary index
- Composite index — column order matters
- When NOT to index: Write-heavy tables, low-cardinality columns
- EXPLAIN statement in MySQL — how to read query execution plans
- Query optimization: Avoid SELECT *, use specific columns; avoid functions on indexed columns in WHERE clause

### React (1.5 hrs)
**Topic:** Forms + State Management
- Controlled vs Uncontrolled components
- Form handling with useState
- Form validation (basic client-side)
- Lifting state up
- Context API basics — when and why to use it

### Project Discussion Prep (1.5 hrs)
**Blog API — Architecture and Features**
- Practice explaining: "Blog API is a production-ready REST API built with Java 21 and Spring Boot 3. It follows Controller-Service-Repository architecture with DTO-based request/response contracts."
- Key features to explain:
  - JPA Specification for dynamic filtering (by tag, date range, combined criteria)
  - Pagination: PageRequest, Pageable, Page<T>, total elements, total pages
  - Sorting: Sort.by("createdAt").descending()
  - Swagger/OpenAPI: @Operation, @ApiResponse, @Schema annotations
  - Bean Validation: @NotBlank, @Size, @Email, @NotNull with custom error messages
  - Centralized exception handling with @RestControllerAdvice

### Interview Q&A (1 hr)
1. What is JPA Specification? When would you use it over regular repository methods?
2. How does pagination work in Spring Data JPA?
3. How do you implement sorting in Spring Boot?
4. What is Swagger/OpenAPI and why is it important?
5. How does @Valid work in Spring Boot?

### HR Prep (30 min)
- Write answers to: "What are your strengths?" and "What are your weaknesses?"

### Daily Revision (30 min)
- Code 3 Stream examples from memory

---

## DAY 5 — Spring Boot Core + DI/IoC + Binary Search DSA

### DSA (1.5 hrs)
**Topic:** Binary Search
- Solve: Binary Search (#704), Search Insert Position (#35), Find Minimum in Rotated Sorted Array (#153)
- Solve: Search a 2D Matrix (#74), Find Peak Element (#162)
- **Focus:** Practice identifying when to apply binary search: "If the array is sorted or the problem has a monotonic property → think binary search"

### Java (1.5 hrs)
**Topic:** Multithreading Basics
- Thread lifecycle: NEW, RUNNABLE, BLOCKED, WAITING, TIMED_WAITING, TERMINATED
- Creating threads: extends Thread vs implements Runnable — which is preferred and why
- Synchronization: synchronized keyword, why it's needed (race conditions)
- volatile keyword: ensures visibility of variable across threads
- Thread methods: start(), run(), sleep(), join(), wait(), notify()
- Deadlock: what it is, how to prevent it
- Thread pools: ExecutorService, Executors.newFixedThreadPool()

**Interview Questions:**
1. What is the difference between process and thread?
2. What is a race condition? How do you fix it?
3. What is deadlock? How do you prevent it?
4. What is the difference between wait() and sleep()?
5. What is the volatile keyword?
6. What is thread pool and why should you use it?

### Database (1.5 hrs)
**Topic:** Transactions + ACID Properties
- **ACID:** Atomicity, Consistency, Isolation, Durability — define each with example
- **Isolation Levels:** READ UNCOMMITTED, READ COMMITTED, REPEATABLE READ, SERIALIZABLE
- **Problems:** Dirty Read, Non-Repeatable Read, Phantom Read — which level prevents which
- **Spring @Transactional:** How it works, propagation types (REQUIRED, REQUIRES_NEW, SUPPORTS), rollback rules
- **In your project:** Where did you use @Transactional? (Booking creation — should be atomic)

### Spring Boot (1.5 hrs)
**Topic:** Spring Core — DI and IoC
- **IoC (Inversion of Control):** The framework controls the lifecycle, not you
- **DI (Dependency Injection):** Objects receive their dependencies from the container
- **Types of DI:** Constructor Injection (preferred), Setter Injection, Field Injection (@Autowired)
- **Why Constructor Injection is preferred:** Immutability, easier to test, mandatory dependencies
- **@Component, @Service, @Repository, @Controller:** Stereotypes
- **@Bean:** Manual bean declaration in @Configuration class
- **ApplicationContext:** Container that manages beans

**Interview Questions:**
1. What is IoC and DI? How are they related?
2. What is the difference between @Component, @Service, @Repository?
3. What are the different types of dependency injection in Spring?
4. Why is constructor injection preferred over field injection?
5. What is ApplicationContext vs BeanFactory?

### Project Discussion Prep (1.5 hrs)
**BookMySeat — Seat Booking Logic**
Practice explaining this critical flow:
> "When a user selects a seat and confirms booking: (1) A POST /bookings request is made with userId, showId, seatId. (2) The Service layer validates: Is the show still future? Is the seat available for this show? Is the user already booked? (3) If valid, a Booking entity is created with status=CONFIRMED, and the seat becomes unavailable for this show. (4) This entire operation is wrapped in a @Transactional block to ensure atomicity — if anything fails, the booking is rolled back."

**Follow-up Questions to Prepare For:**
- How do you handle concurrent seat booking? (Two users booking same seat at same time)
- How would you implement a seat lock/hold mechanism?
- What happens if the payment fails after seat is reserved?

### Interview Q&A (1 hr)
1. Explain the request flow in your Spring Boot application from HTTP request to database
2. What is Spring Boot auto-configuration?
3. What is @SpringBootApplication?
4. What is application.properties used for?
5. How does Spring Boot differ from traditional Spring?

### HR Prep (30 min)
- Write answer: "Where do you see yourself in 2 years?"

### Daily Revision (30 min)
- Revise DI types with code examples

---

## DAY 6 — REST APIs Deep Dive + Linked List DSA

### DSA (1.5 hrs)
**Topic:** Linked List
- Solve: Reverse Linked List (#206), Merge Two Sorted Lists (#21), Linked List Cycle (#141)
- Solve: Middle of the Linked List (#876), Remove Nth Node From End (#19)
- **Focus:** Learn the slow/fast pointer (Floyd's algorithm) technique

### Java (1.5 hrs)
**Topic:** Java Memory Management + String
- **JVM Architecture:** Class Loader, JIT Compiler, Garbage Collector, Memory Areas
- **Memory areas:** Heap (objects), Stack (method calls, local variables), Method Area (class data), PC Register
- **Garbage Collection:** Mark and Sweep, how GC works, when to trigger GC
- **String Immutability:** Why strings are immutable in Java, String pool, intern()
- **String vs StringBuilder vs StringBuffer:** Performance comparison, thread safety
- **== vs .equals() for Strings**

**Interview Questions:**
1. Why is String immutable in Java?
2. What is the String pool?
3. What is the difference between String, StringBuilder, and StringBuffer?
4. How does garbage collection work in Java?
5. What is the difference between stack and heap memory?
6. What is a memory leak in Java?

### Database (1.5 hrs)
**Topic:** SQL Advanced Queries
- Practice complex queries:
  - Window functions: ROW_NUMBER(), RANK(), DENSE_RANK(), LEAD(), LAG()
  - Aggregate with GROUP BY + HAVING
  - Subqueries vs CTEs (Common Table Expressions)
  - CASE WHEN expressions
- Practice: "Rank theaters by total bookings per city"
- Practice: "Find users who have booked more than 5 tickets in the last 30 days"

### Spring Boot (1.5 hrs)
**Topic:** REST API Design Best Practices
- **HTTP Methods:** GET (read), POST (create), PUT (full update), PATCH (partial update), DELETE
- **HTTP Status Codes:**
  - 200 OK, 201 Created, 204 No Content
  - 400 Bad Request, 401 Unauthorized, 403 Forbidden, 404 Not Found, 409 Conflict
  - 500 Internal Server Error
- **URL Design:** Nouns not verbs, plural resources, hierarchical (/theaters/{id}/screens)
- **Request/Response:** Always use DTOs, never expose entity directly
- **Idempotency:** GET, PUT, DELETE are idempotent; POST is not
- **HATEOAS basics:** Understanding hypermedia links

**Interview Questions:**
1. What is the difference between PUT and PATCH?
2. What HTTP status code would you return for a duplicate resource?
3. Why should you use DTOs instead of entities in your API response?
4. What is idempotency and which HTTP methods are idempotent?
5. How do you version your APIs?

### React (1.5 hrs)
**Topic:** Component Lifecycle + Performance
- Revise: React component lifecycle (mount, update, unmount)
- useEffect equivalents: componentDidMount, componentDidUpdate, componentWillUnmount
- React.memo for preventing unnecessary re-renders
- useMemo and useCallback hooks
- Key prop in lists — why it's important

### Project Discussion Prep (1.5 hrs)
**BookMySeat — Admin Dashboard**
Practice explaining:
> "The Admin Dashboard allows administrators to manage the entire system. Admins can: add/update cities, theaters, and screens; configure seat layouts; schedule shows; set ticket prices; view booking analytics. I implemented role-based access — regular users get USER role, administrators get ADMIN role. Admin-specific endpoints are secured with role checks."

**Interview Questions:**
1. How did you implement role-based access control?
2. How do you differentiate admin and user in the API?
3. How would you add authentication to BookMySeat?

### Interview Q&A (1 hr)
- Practice answering all REST API questions above out loud
- Time yourself: aim for 1–2 minutes per answer

### HR Prep (30 min)
- Practice: "Tell me about a challenge you faced and how you solved it"

### Daily Revision (30 min)
- Write HTTP status codes with use cases from memory

---

## DAY 7 — WEEK 1 REVISION + MOCK INTERVIEW #1

### Morning (3 hrs) — Full Revision
- Revise all Java topics from Days 1–6: OOP, Collections, Exceptions, Java 8, Strings, Multithreading
- Revise all SQL topics: Joins, Normalization, Indexing, Transactions
- Revise Spring Boot basics: DI, IoC, REST API design
- Revise React: Components, Props, State, useEffect, Routing

### DSA Practice (1.5 hrs)
- Solve 3 mixed problems from the week's topics under timed conditions (20 min each)
- Problems: Attempt a random array, string, and HashMap problem from LeetCode

### Mock Interview #1 (2 hrs)
**Simulate a real interview session:**

**Round 1 — DSA (45 min):**
- Solve: Valid Parentheses (#20) — verbalize your approach before coding
- Solve: Group Anagrams (#49) — explain time/space complexity

**Round 2 — Java + Spring Boot (45 min):**
- Answer these rapid-fire:
  1. OOP with example from your project
  2. HashMap internals
  3. What is DI and IoC?
  4. How does @Transactional work?
  5. Design a simple REST API for a library system

**Round 3 — Project Discussion (30 min):**
- Explain BookMySeat from scratch as if interviewer knows nothing
- Answer: "What challenges did you face and how did you solve them?"

### HR Practice (30 min)
- Practice your full "Tell me about yourself" out loud — time it at exactly 2 minutes
- Write answers to 5 behavioral questions

### Weekly Review (30 min)
- Note gaps from the week — what questions you couldn't answer well
- Plan to address gaps in Week 2

**Weekly Upskilling Goal (Week 1):**
- Set up Docker Desktop on your machine (installation only — just get familiar)
- Read about what Docker is and why developers use it (30 min)
- Watch a 20-min intro video on System Design basics

---

# WEEK 2: DEEP TECHNICAL MASTERY (Days 8–14)

**Week 2 Goal:** Master advanced Spring Boot, JPA/Hibernate, Security basics, advanced SQL, medium DSA problems, and begin system design fundamentals.

---

## DAY 8 — Spring Boot Advanced: JPA/Hibernate Deep Dive

### DSA (1.5 hrs)
**Topic:** Recursion + Backtracking Intro
- Solve: Climbing Stairs (#70), Fibonacci (#509), Power of Two (#231)
- Solve: Subsets (#78), Permutations (#46) — understand recursion tree
- **Focus:** Always draw the recursion tree before coding

### Spring Boot (3 hrs — extended today, critical topic)
**Topic:** Spring Data JPA + Hibernate
- **ORM Concept:** Object-Relational Mapping — map Java objects to DB tables
- **Entity:** @Entity, @Table, @Id, @GeneratedValue, @Column
- **Relationships:**
  - @OneToOne, @OneToMany, @ManyToOne, @ManyToMany
  - mappedBy: which side owns the relationship
  - CascadeType: PERSIST, MERGE, REMOVE, ALL — understand what each does
  - FetchType.LAZY vs FetchType.EAGER — default behavior, N+1 problem
- **JPA Repository Methods:**
  - findById(), findAll(), save(), delete()
  - Derived query methods: findByTitle(), findByGenreAndLanguage()
  - @Query annotation for JPQL and native SQL
- **@Transactional:** Read-only transactions for performance
- **N+1 Problem:** What it is, how to detect, how to solve (JOIN FETCH, @EntityGraph)
- **Auditing:** @CreatedDate, @LastModifiedDate, @EnableJpaAuditing

**Interview Questions:**
1. What is ORM? What is Hibernate?
2. What is the difference between JPA and Hibernate?
3. What are the different cascade types?
4. What is the N+1 problem? How do you solve it?
5. What is FetchType.LAZY vs EAGER? What is the default?
6. How do you write a custom JPQL query in Spring Data JPA?
7. What is the difference between save() and saveAndFlush()?

### Database (1.5 hrs)
**Topic:** MongoDB Basics (since it's on your resume)
- Document model vs relational model
- Collections, Documents, Fields
- BSON vs JSON
- Basic CRUD operations
- When to use MongoDB vs MySQL
- Embedded documents vs References

### React (1.5 hrs)
**Topic:** BookMySeat Frontend — Seat Selection UI
- Revise how you built the visual seat selection UI
- Practice explaining: "I built a grid-based seat map using a 2D array. Each seat is color-coded by type (Regular/Premium/VIP) and availability (available/selected/booked). On selection, state updates and the total price recalculates dynamically."

### Project Discussion Prep (1 hr)
- Practice explaining the JPA entity relationships in BookMySeat
- Specifically: Booking entity linking to User, Show, and Seat via foreign keys

### Interview Q&A (1 hr)
- 10 rapid-fire Spring Data JPA questions

### HR Prep (30 min)
- Practice: "Why did you choose Java for backend development?"

### Daily Revision (30 min)
- Note JPA relationship annotations and cascade types

---

## DAY 9 — Spring Security + JWT Authentication

### DSA (1.5 hrs)
**Topic:** Trees — Basics
- Solve: Maximum Depth of Binary Tree (#104), Invert Binary Tree (#226), Same Tree (#100)
- Solve: Level Order Traversal (#102), Symmetric Tree (#101)
- **Focus:** Master recursive tree traversal (preorder, inorder, postorder)

### Spring Boot (3 hrs)
**Topic:** Spring Security + JWT
- **Spring Security Basics:**
  - Security filter chain concept
  - Authentication vs Authorization
  - @PreAuthorize, @RolesAllowed
- **JWT (JSON Web Token):**
  - Structure: Header.Payload.Signature (Base64 encoded)
  - Stateless authentication — no session on server
  - Access token vs Refresh token
  - How to validate JWT on each request
- **Implementation flow:**
  1. User sends credentials (POST /auth/login)
  2. Server validates, generates JWT, returns token
  3. Client sends JWT in Authorization header (Bearer token)
  4. JwtFilter extracts and validates token, sets SecurityContext
  5. Controller receives authenticated request

**Practice Explaining:**
> "In BookMySeat (or your upgraded version), I'd implement JWT-based authentication. The user logs in with email and password. The server validates credentials, generates a JWT with the user's ID and role, signed with a secret key. On subsequent requests, the client includes this JWT in the Authorization header. The JwtFilter intercepts each request, validates the token, and sets the authentication context."

**Interview Questions:**
1. What is the difference between authentication and authorization?
2. How does JWT work? What are its three parts?
3. What is the advantage of JWT over session-based authentication?
4. How would you implement JWT in Spring Boot?
5. What is the Bearer token?
6. How do you secure specific endpoints by role?

### Database (1.5 hrs)
**Topic:** PostgreSQL vs MySQL — Differences
- Data types unique to each
- PostgreSQL advantages: JSONB, better compliance, advanced indexing
- When to use which
- Connection pooling basics (HikariCP — Spring Boot's default)

### React (1.5 hrs)
**Topic:** Authentication in React
- Token storage: localStorage vs sessionStorage vs memory (security trade-offs)
- Axios interceptors for attaching Authorization header
- Protected routes with React Router
- Context for storing auth state

### Project Discussion Prep (1 hr)
- Prepare answer: "How would you add authentication to BookMySeat?"
- This is a very common follow-up question

### Interview Q&A (1 hr)
- Spring Security and JWT deep dive Q&A

### HR Prep (30 min)
- Practice: "What is your biggest achievement?"

### Daily Revision (30 min)
- JWT flow diagram from memory

---

## DAY 10 — CS Fundamentals: OS + CN Basics

### DSA (1.5 hrs)
**Topic:** Sorting Algorithms
- Code from scratch: Bubble Sort, Selection Sort, Insertion Sort, Merge Sort, Quick Sort
- Understand time complexity: O(n²) vs O(n log n)
- Know: In-place vs not in-place, stable vs unstable
- LeetCode: Sort Colors (#75), Merge Intervals (#56), Sort Array by Parity (#905)

### CS Fundamentals — OS (3 hrs)
**Topic:** Operating Systems for Interviews
- **Process vs Thread:** Definition, differences, context switching
- **CPU Scheduling:** FCFS, SJF, Round Robin, Priority Scheduling — know all algorithms
- **Memory Management:** Paging, segmentation, virtual memory, page fault, thrashing
- **Deadlock:** Conditions (Mutual Exclusion, Hold and Wait, No Preemption, Circular Wait), prevention, detection, recovery
- **Synchronization:** Mutex, Semaphore, Monitor — differences and use cases
- **File Systems basics**

**Key Interview Questions (OS):**
1. What is the difference between process and thread?
2. What is deadlock? How do you prevent it?
3. What is virtual memory? Why is it used?
4. What is the difference between mutex and semaphore?
5. What is context switching?
6. What is thrashing?

### CS Fundamentals — Computer Networks (1.5 hrs)
- **OSI Model:** 7 layers — Application, Presentation, Session, Transport, Network, Data Link, Physical
- **TCP vs UDP:** Connection-oriented vs connectionless, reliability, use cases
- **HTTP vs HTTPS:** TLS/SSL, why HTTPS is important for APIs
- **HTTP/1.1 vs HTTP/2:** Multiplexing, header compression
- **DNS:** How domain name resolution works
- **REST over HTTP:** Request/Response cycle in detail
- **CORS:** What it is, why it matters for your React frontend calling Spring Boot backend

**Key Interview Questions (CN):**
1. What are the OSI layers? Which layer does HTTP operate on?
2. What is the difference between TCP and UDP?
3. What is HTTPS? How does SSL/TLS work at a high level?
4. What is CORS? How did you handle CORS in BookMySeat?
5. What happens when you type a URL in a browser?

### Project Discussion Prep (1 hr)
- Practice: "How did you handle CORS in BookMySeat?"
- Answer: @CrossOrigin annotation or global CORS configuration in WebMvcConfigurer

### Interview Q&A (1 hr)
- Mix of OS and CN questions

### HR Prep (30 min)
- Practice: "How do you handle working under pressure?"

### Daily Revision (30 min)
- OSI model layers + TCP vs UDP

---

## DAY 11 — System Design Basics for Freshers

### DSA (1.5 hrs)
**Topic:** Greedy Algorithms
- Solve: Jump Game (#55), Jump Game II (#45), Gas Station (#134)
- Solve: Assign Cookies (#455), Non-overlapping Intervals (#435)
- **Focus:** Greedy logic — "Choose the locally optimal solution at each step"

### System Design (3 hrs — critical for senior-looking freshers)
**Topic:** System Design Fundamentals

**1. How to approach System Design (as a fresher):**
> "I'm a fresher, so I'll approach this practically from my project experience while discussing scalability considerations."

**2. Key Concepts to Know:**
- **Client-Server Architecture**
- **Database:** Relational vs NoSQL — when to choose each
- **Caching:** What is caching, Redis for caching, cache-aside pattern
- **Load Balancer:** Distributes traffic across multiple servers
- **Horizontal vs Vertical Scaling**
- **CDN:** Content Delivery Network for static assets
- **Message Queue:** Async processing (RabbitMQ, Kafka — concept only)
- **Monolith vs Microservices:** Pros/cons of each

**3. Practice: System Design for BookMySeat at Scale**

> "If BookMySeat needed to handle 1 million users: I would: (1) Put the database behind connection pooling (already using HikariCP). (2) Add Redis caching for movie listings and show schedules which are read frequently. (3) Use a load balancer in front of multiple Spring Boot instances. (4) For the seat booking concurrency problem, I'd use optimistic locking (@Version in JPA) or pessimistic locking to prevent double-booking. (5) Static assets would be served from a CDN. (6) I'd add an async notification service using a message queue for booking confirmation emails."

**Interview Questions:**
1. How would you scale BookMySeat to handle 1 million users?
2. What is caching? Where would you use it in your project?
3. What is the difference between horizontal and vertical scaling?
4. What is a load balancer?
5. What is the difference between monolithic and microservices architecture?

### Spring Boot (1.5 hrs)
**Topic:** Bean Scope + Bean Lifecycle
- Scopes: Singleton (default), Prototype, Request, Session, Application
- Lifecycle: Instantiation → Dependency Injection → @PostConstruct → In Use → @PreDestroy → Destruction
- @PostConstruct: Execute logic after bean is initialized
- ApplicationListener and ApplicationEvent

### React (1.5 hrs)
**Topic:** Error Handling + Loading States
- Error boundaries (class components)
- Loading states: show spinner while API call is in progress
- Error messages: show user-friendly error when API fails
- Retry logic basics

### Project Discussion Prep (1 hr)
- Prepare: "What would you improve about BookMySeat?"
- Suggested improvements: JWT auth, Redis caching, email notifications, unit tests, Docker deployment

### Interview Q&A (1 hr)
- System design scenario questions

### HR Prep (30 min)
- Practice: "Tell me about a time you worked in a team"

### Daily Revision (30 min)
- System design keywords and their one-line explanations

---

## DAY 12 — Advanced SQL + Database Design

### DSA (1.5 hrs)
**Topic:** Dynamic Programming — Introduction
- Solve: Climbing Stairs (#70), House Robber (#198), Coin Change (#322)
- **Focus:** Understand DP as "memoized recursion" — top-down and bottom-up approaches
- **Pattern Recognition:** Overlapping subproblems + Optimal substructure

### Database (3 hrs)
**Topic:** Advanced Database Concepts

**1. Stored Procedures vs Functions vs Triggers vs Views**
- Stored Procedure: Precompiled SQL, can have side effects
- Function: Returns a value, used in SELECT
- Trigger: Auto-executes on INSERT/UPDATE/DELETE events
- View: Virtual table from a query — simplify complex queries

**2. Database Design Best Practices**
- Proper indexing strategy
- Avoid storing calculated fields
- Use appropriate data types (VARCHAR vs TEXT, INT vs BIGINT)
- Soft delete vs Hard delete (@Where(clause="deleted=false") in Hibernate)

**3. Transactions and Locking**
- Optimistic Locking: @Version in JPA — check version before update
- Pessimistic Locking: Database-level LOCK (SELECT FOR UPDATE)
- When to use each: Optimistic for low-contention, Pessimistic for high-contention (seat booking)

**4. SQL Practice — Write these queries:**
- "Find the most popular movie (most bookings) per city"
- "Find seats that were never booked"
- "Get the revenue per theater per month for last 6 months"

**Interview Questions:**
1. What is the difference between optimistic and pessimistic locking?
2. How would you prevent double-booking in a ticket system?
3. What is a database view? When would you use it?
4. What is a trigger? Give a practical example.
5. What is soft delete? How do you implement it in Hibernate?

### Spring Boot (1.5 hrs)
**Topic:** Spring Boot Profiles + Configuration
- @Profile: Different configs for dev/test/prod
- application-dev.properties, application-prod.properties
- @ConfigurationProperties: Type-safe configuration binding
- Environment variables vs application.properties
- Externalized configuration best practices

### React (1.5 hrs)
**Topic:** Final React Revision
- Complete revision: Components, Props, State, Hooks (useState, useEffect, useContext, useMemo, useCallback)
- React rendering optimization
- Common React interview questions from the list at end of this roadmap

### Project Discussion Prep (1 hr)
- Practice explaining JPA Specification in Blog API:
  > "JPA Specification allows dynamic, composable query predicates. Instead of writing a separate repository method for each filter combination (tag, date, author), I created a Specification that builds the JPA Criteria query based on whichever filters are present in the request. This is much more maintainable than 10 different repository methods."

### HR Prep (30 min)
- Practice: "What do you know about our company?" (generic answer for any company)

### Daily Revision (30 min)
- SQL locking mechanisms

---

## DAY 13 — Full Backend Interview Simulation Day

### DSA (2 hrs)
- Solve 4 problems under exam conditions (25 min each, no hints):
  1. Two Sum (Easy)
  2. Longest Substring Without Repeating Characters (Medium)
  3. Find the Duplicate Number (Medium)
  4. Jump Game (Medium)
- After each: Write time complexity and space complexity

### Full Technical Mock Interview (4 hrs)
**Simulate a 3-round technical interview:**

**Round 1 — Java Core (60 min):**
1. What is the difference between ArrayList and LinkedList?
2. How does HashMap handle collisions?
3. What is Java 8's Stream API? Write a stream query live.
4. What is the difference between final, finally, finalize?
5. Explain Java's memory model (heap, stack, GC)

**Round 2 — Spring Boot + DB (60 min):**
1. Explain the layered architecture in your project
2. How does @Transactional work?
3. What is N+1 problem? How do you solve it?
4. Write a JPA query for fetching bookings with user and show details
5. What is the difference between @Controller and @RestController?
6. How do you handle validation in Spring Boot?

**Round 3 — Project Discussion (60 min):**
1. Walk me through BookMySeat end-to-end
2. How did you design the database schema?
3. How does the seat booking flow work?
4. What challenges did you face?
5. How would you improve this project?
6. What would you do differently?

### HR Practice (1 hr)
- Full HR round simulation: Answer 10 behavioral questions
- Time each answer — aim for 1.5–2 minutes each

### Daily Revision (30 min)
- Note all questions where you struggled

---

## DAY 14 — Week 2 Revision + Mock Interview #2

### DSA (1.5 hrs)
- Solve 3 medium problems from week 2 topics (Trees, DP, Greedy)

### Full Revision (3 hrs)
- Revise: JPA/Hibernate, Spring Security, JWT, Bean lifecycle, Bean scopes
- Revise: OS concepts (deadlock, scheduling, memory), CN (OSI, TCP/UDP, CORS)
- Revise: Advanced SQL (locking, views, indexes)
- Revise: System design terms

### Mock Interview #2 (2 hrs)
- Focus on weak areas from Mock Interview #1
- Attempt a full technical round + project discussion

### Weekly Upskilling (1 hr)
**Week 2 Upskilling Goal: Docker**
- Complete a "Docker for Java developers" tutorial
- Run your Spring Boot app in a Docker container:
  ```dockerfile
  FROM openjdk:21-jdk-slim
  COPY target/bookmyseat.jar app.jar
  ENTRYPOINT ["java", "-jar", "/app.jar"]
  ```
- Understand: docker build, docker run, docker ps, docker images
- Understand: Why Docker solves "works on my machine" problem

### HR Prep (30 min)
- Practice: "Do you have any questions for us?" — prepare 3 smart questions

---

# WEEK 3: PROJECT DEEP DIVE + CS FUNDAMENTALS + UPSKILLING (Days 15–21)

**Week 3 Goal:** Master project articulation, solidify CS fundamentals (DBMS, OS, CN), solve medium-hard DSA, and add upskilling topics to your profile.

---

## DAY 15 — BookMySeat: Complete Deep Dive

### DSA (1.5 hrs)
**Topic:** Medium-Hard problems
- Solve: LRU Cache (#146), Find All Anagrams in a String (#438), Subarray Sum Equals K (#560)

### Project: BookMySeat Complete Preparation (5 hrs)

**1. Architecture (30 min — practice out loud)**
- Monolithic full-stack Spring Boot app
- Backend: Spring Boot + Spring Data JPA + MySQL
- Frontend: Integrated HTML/CSS/JS (or React if separate)
- Layered: Presentation (Controller) → Business (Service) → Data (Repository) → Database

**2. Entity Relationship Design (1 hr)**
- Practice explaining each entity and its relationships
- Key relationships to master:
  - Show is the central booking entity (connects Movie + Screen + Time)
  - Booking connects User + Show + Seat
  - Screen belongs to Theater; Theater belongs to City
  - Seat belongs to Screen

**3. API Design (1 hr)**
- For each of your 20+ APIs, practice explaining:
  - Endpoint, Method, Purpose, Request, Response, Status codes
- Focus on: POST /bookings (most complex — has validation, transaction, seat locking)

**4. Seat Booking Concurrency (1 hr)**
Practice this answer:
> "The main challenge with seat booking is race conditions — two users trying to book the same seat simultaneously. I'd handle this using optimistic locking in JPA with @Version annotation on the Seat or Booking entity. When two concurrent requests come in, the first one succeeds; the second one gets an OptimisticLockException, which I catch and return a 409 Conflict response to the user."

**5. Admin Dashboard (30 min)**
- Practice explaining all admin operations
- Role-based endpoint protection

**6. Scalability Discussion (1 hr)**
Practice: "If BookMySeat got 1 million users…"
- Redis for caching movie/show listings
- Database read replicas
- CDN for static assets
- Message queue for async notifications
- Horizontal scaling of Spring Boot instances

**Expected Interview Questions:**
1. How did you ensure data consistency in seat booking?
2. What is the most complex API you built? Walk me through it.
3. How does the Admin Dashboard work?
4. How did you deploy BookMySeat?
5. What is the biggest technical challenge you solved?

---

## DAY 16 — Blog API: Complete Deep Dive

### DSA (1.5 hrs)
- Solve: Find First and Last Position (#34), Minimum Window Substring (#76)

### Project: Blog API Complete Preparation (5 hrs)

**1. Architecture (30 min)**
- Production-ready RESTful API in Java 21 + Spring Boot 3
- DTO-based request/response contracts (never expose entity directly)
- Layered: Controller → Service → Repository

**2. JPA Specification Deep Dive (1.5 hrs)**

Practice explaining:
> "JPA Specification uses the Criteria API to build dynamic queries at runtime. I implemented a BlogSpecification class that implements Specification<Blog>. Based on the filter parameters passed in the request (tag, startDate, endDate), it builds predicate conditions. Multiple specifications are combined with Specification.where().and() for AND conditions. This approach is much cleaner than writing 10 different repository methods."

Practice code (know by heart):
```java
public class BlogSpecification {
    public static Specification<Blog> hasTag(String tag) {
        return (root, query, cb) -> cb.equal(root.get("tag"), tag);
    }
    
    public static Specification<Blog> createdAfter(LocalDate date) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), date);
    }
}
```

**3. Pagination and Sorting (1 hr)**
- How Pageable is constructed: PageRequest.of(page, size, Sort.by("createdAt").descending())
- Page<T> response: content, totalElements, totalPages, size, number
- Practice: "List all blogs, page 2, 10 per page, sorted by creation date descending"

**4. Swagger/OpenAPI (30 min)**
Practice: "Swagger auto-generates interactive API documentation from annotations. I used @Operation to describe each endpoint, @ApiResponse to document response codes, and @Schema to document request/response bodies. This makes the API self-documenting and allows frontend or other teams to easily understand and test the APIs."

**5. Validation + Exception Handling (30 min)**
- @Valid triggers Jakarta Bean Validation
- Validation annotations: @NotBlank, @Size, @Email, @NotNull, @Min, @Max
- When validation fails → MethodArgumentNotValidException → handled by @RestControllerAdvice
- Custom error response structure: { "field": "title", "message": "Title cannot be blank" }

**6. Advanced Follow-up Questions (1 hr)**
1. How does JPA Specification differ from @Query?
2. What is the difference between Page and Slice?
3. How do you handle large datasets efficiently with pagination?
4. How would you add authentication to Blog API?
5. How would you add caching to the blog listing endpoint?

---

## DAY 17 — Internship Project: Fake Product Review Monitoring System

### DSA (1.5 hrs)
- Solve: Rotate Array (#189), Product of Array Except Self (#238), Missing Number (#268)

### Internship Project Preparation (3 hrs)

Practice explaining:
> "During my internship at SohamGlobal Group from January to March 2025, I worked as a Trainee Application Developer. I contributed to a Fake Product Review Monitoring System — a backend system to detect and flag potentially fake reviews for e-commerce products. I worked on: Designing and implementing REST APIs for product review management using Java and Spring Boot. I applied the Controller-Service-Repository architecture. I used Spring Data JPA with MySQL for data persistence. I implemented centralized exception handling using @RestControllerAdvice to standardize error responses. I participated in Agile development workflows including sprint planning, daily standups, and code reviews."

**Expected Interview Questions about Internship:**
1. What was your role and responsibility in the internship?
2. What was the most challenging part?
3. How did you work in an Agile team?
4. Can you walk me through an API you built?
5. What did you learn from the internship?
6. What was the architecture of the project?

**STAR Answers to Prepare:**
- "Tell me about a challenge at your internship" → Situation: tight deadline + complex API design → Task: build review filtering API → Action: broke down into smaller tasks, asked senior → Result: delivered on time
- "Tell me about teamwork at your internship" → Situation: team code reviews → Action: gave and received constructive feedback → Result: improved code quality

### CS Fundamentals (1.5 hrs)
**Topic:** DBMS Concepts (for CS fundamentals interview)
- ER Diagram: Entity, Attribute, Relationship, Cardinality
- Functional Dependency
- Normalization: 1NF, 2NF, 3NF, BCNF — definitions and examples
- SQL Constraints: PRIMARY KEY, FOREIGN KEY, UNIQUE, NOT NULL, CHECK, DEFAULT
- Keys: Primary, Candidate, Super, Foreign, Composite Key

### Spring Boot (1.5 hrs)
**Topic:** Logging + Monitoring Basics
- Spring Boot Actuator: /health, /metrics, /info endpoints
- SLF4J + Logback: Logger, log levels (TRACE, DEBUG, INFO, WARN, ERROR)
- Proper logging practices: Log at service layer, include request context
- Why logging matters in production

### Upskilling (1 hr)
**Topic: Redis Basics**
- What is Redis? In-memory key-value store
- Use cases: Caching, session storage, rate limiting, pub/sub
- Basic commands: SET, GET, EXPIRE, DEL, TTL
- Spring Boot + Redis: @Cacheable, @CacheEvict
- How Redis would improve Blog API (cache popular posts)

---

## DAY 18 — Communication + Technical Speaking Practice

### DSA (1.5 hrs)
- Solve 3 medium problems — focus on thinking out loud while solving

### Communication Practice (3 hrs)
**This day is dedicated to improving how you communicate technical topics**

**Exercise 1: The 2-Minute Project Pitch (1 hr)**
- Record yourself explaining BookMySeat in exactly 2 minutes
- Criteria: Clear structure, no filler words ("um", "uh"), confident tone, proper technical terms
- Repeat until you're satisfied

**Exercise 2: Technical Explanation Practice (1 hr)**
- Explain these concepts in simple terms (as if to a non-technical person):
  1. What is an API? (Use a restaurant analogy)
  2. What is a database? (Use a filing cabinet analogy)
  3. What is OOP? (Use real-world objects)
  4. What is Spring Boot? (A pre-configured toolkit)
- Then explain the same things technically (as to an interviewer)

**Exercise 3: Structured Answer Practice (1 hr)**
- Use the **PREP framework** for technical questions:
  - **P**oint: State your main point first
  - **R**eason: Explain why
  - **E**xample: Give a concrete example
  - **P**oint: Restate your conclusion
- Practice with: "Why did you choose Spring Boot for your project?"

### Java Revision (1.5 hrs)
- Quick revision of all Java interview questions from weeks 1–2
- Focus on questions you answered poorly in mock interviews

### HR Interview Preparation (2 hrs)
**Prepare complete answers (write + practice out loud) for:**
1. Tell me about yourself (2 min structured answer)
2. Why Java/Backend development?
3. Where do you see yourself in 5 years?
4. What are your strengths? (give 3, tie to your skills)
5. What is your weakness? (honest + improvement plan)
6. Why did you choose this company?
7. Tell me about a challenging situation
8. How do you handle failure?
9. Describe yourself in 3 words
10. Do you prefer working alone or in a team?
11. What motivates you?
12. Are you willing to relocate?
13. Expected salary (research market rates for Java fresher)
14. Why should we hire you?

---

## DAY 19 — Online Assessment Preparation

### DSA (3 hrs — OA Focus)
**Simulate an OA session (exactly like a real test):**
- Set a timer for 90 minutes
- Solve 3 problems without looking at hints:
  1. Easy problem (try to solve in 10–15 min)
  2. Medium problem (try to solve in 25–30 min)
  3. Medium problem (attempt; partial credit counts)
- After OA, review and understand each solution

**OA Strategy:**
- Read all problems first (2 min) — start with easiest
- For each problem: Read carefully, write 2–3 examples, identify pattern, code, test edge cases
- Edge cases to always check: empty array, null input, single element, negative numbers, duplicates
- Partial credit: Even if you can't solve fully, write a brute force approach with correct structure

**Common OA Problem Types:**
- String manipulation (reverse, palindrome, anagram)
- Array operations (subarray, sliding window)
- HashMap counting problems
- Basic recursion
- Simple sorting/searching

### Coding Style (1.5 hrs)
**Write Clean Interview Code:**
- Meaningful variable names (not a, b, c — use left, right, count)
- Add brief comments explaining your approach
- Handle null/empty inputs first
- Use helper methods to keep main method clean
- Always state time and space complexity at the end

### SQL OA Preparation (1.5 hrs)
- Practice 5 SQL questions under timed conditions
- Common OA SQL questions:
  1. Second highest salary
  2. Departments with no employees
  3. Consecutive dates logic
  4. Ranking with ties (DENSE_RANK)
  5. Self-join for hierarchical data

### Resume Revision (1 hr)
**Review your resume critically:**
- Ensure action verbs: Designed, Implemented, Built, Developed, Optimized, Integrated
- Quantify where possible: "20+ RESTful APIs", "8 domain modules", "150+ LeetCode problems"
- Check for clarity: Can a non-technical HR person understand your project descriptions?
- Ensure LinkedIn is updated to match resume
- Your resume looks strong — focus on polishing the language

---

## DAY 20 — Full Stack Interview Preparation

### DSA (1.5 hrs)
- Solve: Merge k Sorted Lists (#23 — medium-hard), Top K Frequent Elements (#347), Kth Largest Element (#215)

### Full Stack Interview Preparation (4 hrs)

**What Full Stack interviews typically ask:**

**Frontend Side:**
1. How does React's virtual DOM work?
2. What is the difference between controlled and uncontrolled components?
3. How do you handle API calls in React?
4. What is prop drilling and how do you avoid it?
5. How do you manage global state?
6. What is CORS and how did you handle it?
7. How do you optimize React performance?
8. What is lazy loading in React?

**Backend Side:**
1. How does your frontend communicate with your backend?
2. How do you handle CORS between React and Spring Boot?
3. How do you structure your API responses for the frontend?
4. How do you handle API errors on the frontend?
5. What is the request-response cycle in your application?

**Integration Questions:**
1. Walk me through what happens when a user books a seat in BookMySeat
2. How does the frontend know if a seat is available?
3. How do you handle authentication between React and Spring Boot?
4. How did you deploy the full stack application?

### Upskilling (1.5 hrs)
**Topic: Git/GitHub Collaboration Workflow**
- Feature branch workflow: main → feature/feature-name → PR → code review → merge
- Git commands to know: clone, pull, push, fetch, merge, rebase, stash, reset, revert
- Resolving merge conflicts
- Meaningful commit messages: "feat: add JWT authentication", "fix: resolve N+1 query issue"
- .gitignore: What to exclude (application.properties with secrets, target/, node_modules/)
- GitHub Actions: Concept of CI/CD pipeline (build → test → deploy on push)

### Project Discussion Prep (1 hr)
- Practice: "How did you deploy your projects?"
- Honest answer: "I deployed BookMySeat backend on Render and the frontend on Vercel (if applicable). I generated the JAR using Maven (mvn clean package) and deployed to Render's free tier."

---

## DAY 21 — Week 3 Revision + Mock Interview #3

### DSA (1.5 hrs)
- 3 timed medium problems from all topics covered so far

### Full Revision (2 hrs)
- Project deep dive summary for both projects
- Internship talking points
- Upskilling additions (Docker, Redis concepts, Git workflow)

### Mock Interview #3 — Full Stack Focus (3 hrs)
**Round 1 — Full Stack Technical (60 min):**
- Explain end-to-end flow: React frontend → Spring Boot API → MySQL
- How did you handle CORS?
- Write a React component that fetches and displays movies
- What is useEffect and when do you use it?

**Round 2 — System Design (45 min):**
- Design a simple URL shortener (Bit.ly)
- Walk through: API design, database schema, caching, scalability

**Round 3 — Project + HR (45 min):**
- Full project walkthrough for BookMySeat
- Behavioral: Tell me about a time you had to learn something new quickly

### Upskilling (1 hr)
**Week 3 Upskilling Goal: CI/CD Basics**
- Understand what CI/CD means: Continuous Integration + Continuous Deployment
- GitHub Actions workflow file concept
- Simple workflow: on push → build Maven project → run tests → deploy to Render
- This shows industry maturity in interviews

---

# WEEK 4: MOCK INTERVIEWS + FINAL POLISH (Days 22–30)

**Week 4 Goal:** Integrate everything, simulate real interviews daily, refine communication, polish resume, and achieve maximum confidence.

---

## DAY 22 — Advanced DSA + Spring Boot Final Topics

### DSA (2 hrs)
- Attempt LeetCode medium-hard: Longest Palindromic Substring (#5), Decode Ways (#91)
- Practice: 2 contest problems on LeetCode (weekly contest problems)

### Spring Boot Advanced (2 hrs)
**Remaining Important Topics:**
- **@Async:** Asynchronous method execution
- **Scheduled tasks:** @Scheduled, @EnableScheduling
- **Spring Boot Testing:** @SpringBootTest, @WebMvcTest, @DataJpaTest, Mockito, @MockBean
- **ResponseEntity<T>:** Full control over HTTP response (status, headers, body)
- **@RequestParam vs @PathVariable vs @RequestBody**
- **Content negotiation:** Producing JSON vs XML
- **Global CORS configuration**

**Interview Questions:**
1. What is the difference between @RequestParam and @PathVariable?
2. How do you write unit tests for a Spring Boot service?
3. What is @MockBean in Spring Boot testing?
4. How do you return a custom HTTP status code from a controller?
5. What is ResponseEntity?

### Communication Practice (1 hr)
- Practice explaining all your technical concepts in 60 seconds each
- Record and review for clarity, pace, and confidence

### Resume Final Review (1 hr)
- Have someone else read your resume and give feedback
- Ensure all project descriptions are crisp and accurate
- Verify all links (GitHub, LinkedIn, LeetCode) work

---

## DAY 23 — Mock OA Simulation + Behavioral Interview

### OA Simulation (3 hrs)
- Full timed OA: 3 DSA + 1 SQL + 10 MCQ (Java/CS concepts)
- MCQ topics: Java output prediction, time complexity, SQL output, OS concepts
- Review every wrong answer thoroughly

### Behavioral Interview Deep Practice (2 hrs)
**STAR Method (Situation-Task-Action-Result) Practice:**

Prepare and practice these 10 behavioral questions:
1. Tell me about a time you faced a technical challenge
2. Describe a situation where you had to learn something quickly
3. Tell me about a time you made a mistake and how you handled it
4. Describe working in a team on your internship
5. Tell me about a time you went above and beyond
6. How do you prioritize when you have multiple tasks?
7. Describe a project you're most proud of
8. Tell me about a time you received critical feedback
9. How do you stay current with technology?
10. Tell me about a time you disagreed with a team decision

**For each:** Write a 4-sentence STAR answer. Practice saying it out loud.

### Java Final Rapid Revision (2 hrs)
- Go through the Top 50 Java interview questions list (in the appendix)
- Answer each one out loud in 30–60 seconds
- Time yourself ruthlessly

---

## DAY 24 — Full Backend Engineering Interview Simulation

### DSA (1.5 hrs)
- Solve: 3 medium problems you haven't seen before

### Full Backend Mock Interview (4 hrs)
Simulate a full backend engineering interview:

**Technical Round 1 — Java Core (60 min):**
- Deep Java: JVM internals, classloading, GC algorithms
- Collections: Iterator pattern, fail-fast vs fail-safe
- Multithreading: Producer-Consumer problem, thread pool
- Java 8: Complex stream chain, Optional chaining

**Technical Round 2 — Spring Boot Deep Dive (60 min):**
- Spring Boot internals: Auto-configuration magic
- JPA: N+1, lazy loading, @EntityGraph
- Security: Complete JWT flow implementation discussion
- Testing: Mocking, @WebMvcTest, Mockito verify

**Technical Round 3 — Database (60 min):**
- Write complex SQL queries live
- Database design for a new scenario (e.g., design schema for a hospital system)
- Explain indexing strategy for your scenario
- Transactions and isolation levels

**HR Round (60 min):**
- Full behavioral interview using STAR method
- Culture fit questions
- "Do you have any questions for us?"

---

## DAY 25 — Upskilling Integration + Clean Code

### DSA (1.5 hrs)
- Practice: Hard problem attempt (e.g., Trapping Rain Water #42, Word Search #79)
- Focus on thinking aloud, not necessarily solving

### Clean Code + Software Engineering (2 hrs)
**Industry-Expected Practices:**

**SOLID Principles:**
- **S**ingle Responsibility: One class, one reason to change
- **O**pen/Closed: Open for extension, closed for modification
- **L**iskov Substitution: Subclasses should be substitutable for parent
- **I**nterface Segregation: Don't force classes to implement unused methods
- **D**ependency Inversion: Depend on abstractions, not concretions

**Clean Code Practices:**
- Meaningful naming: BookingService not BS
- Functions do one thing
- Comments explain "why", not "what"
- Avoid magic numbers: use constants
- DRY: Don't Repeat Yourself

**Code Review Mindset:**
- What to look for: correctness, readability, edge cases, performance, test coverage
- How to give constructive feedback: "Consider using..." not "This is wrong"
- How to receive feedback: Thank, understand, implement

### AI Tools for Developers (1 hr)
- GitHub Copilot: AI code completion — understand its strengths and limitations
- ChatGPT/Claude for debugging: How to describe your problem effectively
- How to use AI tools responsibly (not for understanding, but for productivity)

### Performance Optimization (1 hr)
- Backend: Database query optimization, connection pooling, caching
- Frontend: Code splitting, lazy loading, image optimization, minification
- API: Response compression (gzip), pagination instead of loading all data

### Logging and Monitoring (1 hr)
- Why logging matters: Debug in production without deploying
- Log levels: Use DEBUG for dev, INFO for production
- What to log: Request/response, exceptions, business events
- Monitoring tools concept: Prometheus, Grafana (conceptual knowledge only)

---

## DAY 26 — Resume-Based Interview Preparation

### DSA (1.5 hrs)
- Solve 3 problems from topics you're weakest in

### Resume-Deep Interview Preparation (4 hrs)
**Every item on your resume is fair game — prepare deeply:**

**"Java" on resume → Expected questions:**
- What version of Java are you using?
- What Java 8 features did you use?
- Can you write a Streams query right now?
- What is your favorite Java feature?

**"Spring Boot" on resume → Expected questions:**
- How do you configure your Spring Boot application?
- What is @SpringBootApplication and what does it do?
- How do you handle security in Spring Boot?
- What is the difference between @RestController and @Controller?

**"Spring Data JPA + Hibernate" → Expected questions:**
- Explain JPA relationships in your project
- What is the difference between JPA and Hibernate?
- How do you handle lazy loading exceptions?

**"REST APIs" → Expected questions:**
- Design a REST API for [new scenario]
- What is REST? What are its constraints?
- How do you document your APIs?

**"MySQL + PostgreSQL" → Expected questions:**
- What is the difference between MySQL and PostgreSQL?
- When would you choose PostgreSQL?
- Write a complex query for [scenario]

**"MongoDB" → Expected questions:**
- How is MongoDB different from MySQL?
- When would you use MongoDB over MySQL?
- What is a document in MongoDB?

**"Multithreading" → Expected questions:**
- Write a thread-safe singleton in Java
- What is the difference between synchronized and ReentrantLock?
- Have you used any concurrency utilities?

**"CGPA 8.34" → Expected questions:**
- What was your favorite subject?
- Did you top any subject?
- How did your education help you become a developer?

**"LeetCode 150+" → Expected questions:**
- What is your LeetCode username?
- What is your favorite problem?
- What is the hardest problem you solved?
- What topics do you find most challenging?

---

## DAY 27 — Communication + Confidence Building

### DSA (1.5 hrs)
- 2 medium problems — practice code review style (explain every line as you write)

### Confidence Building (2 hrs)
**Exercises:**
1. Record a 5-minute "technical talk" on Spring Boot for beginners — watch it back
2. Practice standing up and presenting your resume (simulate standing interviews)
3. Mirror exercise: Talk to yourself in the mirror about your projects for 10 minutes
4. Read positive affirmations specific to your skills: "I built a full-stack booking system. I have real internship experience. I have 150+ LeetCode problems. I am ready."

### Interview Conversation Practice (2 hrs)
**Practice these conversational bridges:**
- When asked a question you know well: Start with "That's a great area — in [project/internship], we..."
- When asked something you partially know: "I'm familiar with the concept at a high level — [explain what you know] — I'd like to learn more about [specific aspect]"
- When asked something you don't know: "I haven't used that directly, but I understand the concept is [explain]. I'd be happy to learn it — I typically approach new technologies by [your learning approach]"
- When asked about improvement areas: "I'm actively working on [Docker/System Design/Testing] and I've [specific recent learning]"

### Full Rapid Fire Q&A (2 hrs)
- Set a 60-second timer per question
- Go through 30 random questions from the appendix
- This builds interview speed and confidence

---

## DAY 28 — Full Mock Interview Day (Most Important)

**No theory today — only simulation.**

### Morning (4 hrs) — Technical Simulation
- **DSA Round (45 min):** 2 random medium problems, timed
- **Java Core Round (45 min):** 10 questions, 4 min each
- **Spring Boot Round (45 min):** Architecture discussion + code review
- **Project Round (45 min):** Full BookMySeat walkthrough + cross-questioning
- **System Design Round (45 min):** Design a simple notification system
- **Database Round (30 min):** Write 3 complex SQL queries

### Afternoon (2 hrs) — HR Simulation
- Full HR interview: 10 behavioral questions using STAR method
- "Tell me about yourself" — final polished version
- Company research questions
- "Do you have questions for us?"

### Evening (1 hr) — Post-Interview Analysis
- Write down every question from the simulation
- Mark green (confident), yellow (partial), red (struggled)
- Plan targeted revision for Day 29

---

## DAY 29 — Targeted Weak Area Attack + Final Upskilling

### Morning (3 hrs) — Address Red Areas from Day 28
- Intensively study every topic you marked red
- For each: Study concept → Write key points → Explain out loud → Practice question

### Upskilling Final (2 hrs)
**Trending Technologies to Mention in Interviews:**
- **Microservices:** You know Spring Boot — mention microservices awareness
- **Kubernetes concept:** Orchestrates Docker containers at scale
- **GraphQL vs REST:** GraphQL allows clients to request exactly what they need
- **Reactive Programming:** Project Reactor, WebFlux — mention you're learning
- **Message Queues:** RabbitMQ/Kafka concept — async processing
- **Cloud basics:** AWS/GCP/Azure — S3 (file storage), EC2 (VM), RDS (managed DB)

**How to answer: "Are you learning any new technologies?"**
> "Yes — I'm currently deepening my knowledge in Docker and containerization. I've also been studying system design patterns for scalability. I'm also exploring how Spring Boot integrates with Redis for caching. I believe continuous learning is essential in software engineering."

### Evening (2 hrs) — Final Revision
- Top 20 Java questions
- Top 20 Spring Boot questions
- Top 10 project discussion questions
- Top 10 HR questions

---

## DAY 30 — FINAL DAY: Review + Mental Preparation

### Morning (3 hrs) — Complete Rapid Revision
- 5-minute review of each major topic: Java, Spring Boot, JPA, SQL, React, OS, CN, System Design
- Quick DSA: 2 easy + 1 medium problems to warm up
- Read through your project notes one final time

### Project Story Final Practice (1 hr)
- BookMySeat: Say the full explanation 3 times — time it, perfect it
- Blog API: Same exercise
- Internship: Crisp 2-minute explanation

### Communication Final Polish (1 hr)
- Record and replay your "Tell me about yourself" — is it perfect?
- Practice your energy level — be enthusiastic, not monotone

### Mental Preparation (1 hr)
- You have: Real internship experience, 2 strong projects, 150+ LeetCode, strong tech stack
- You are: A Java + Spring Boot + React developer with 8.34 CGPA and real API-building experience
- Repeat: "I am ready. I have built real applications. I can explain my work confidently."

### Final Checklist
- [ ] Resume: Updated, no typos, all links working
- [ ] GitHub: Projects pushed with clean README files
- [ ] LinkedIn: Updated with skills, projects, internship
- [ ] LeetCode: Profile visible
- [ ] Portfolio: Projects deployed (Render/Vercel) and accessible

---

# APPENDIX A: TOP 100 INTERVIEW QUESTIONS

## Top 30 Java Interview Questions
1. What are the 4 pillars of OOP? Explain with examples.
2. What is the difference between abstract class and interface in Java 8+?
3. What is method overloading vs overriding?
4. How does HashMap work internally?
5. What is the difference between HashMap and ConcurrentHashMap?
6. What is the difference between ArrayList and LinkedList?
7. What is Java's Collections hierarchy?
8. What are the Java 8 features you've used?
9. What is a functional interface? Give 3 examples.
10. What is the difference between map() and flatMap() in streams?
11. What is Optional? Why was it introduced?
12. What is the difference between checked and unchecked exceptions?
13. What is final, finally, finalize?
14. What is String immutability? Why is String immutable?
15. What is the String pool?
16. What is the difference between == and .equals()?
17. What is a deadlock? How do you prevent it?
18. What is volatile in Java?
19. What is the difference between wait() and sleep()?
20. What is synchronized? What is a synchronized block vs method?
21. What is the JVM? What are its components?
22. What is the difference between stack and heap memory?
23. What is garbage collection? What are GC algorithms?
24. What is a marker interface? Give examples.
25. What is the Comparable vs Comparator interface?
26. What is fail-fast vs fail-safe iterator?
27. What is autoboxing and unboxing?
28. What is var in Java 10+?
29. What is the difference between throw and throws?
30. What is a generic type? Why do we use generics?

## Top 25 Spring Boot Interview Questions
1. What is Spring Boot? How is it different from Spring?
2. What is @SpringBootApplication? What does it include?
3. What is IoC and DI? How are they related?
4. What are the different types of dependency injection?
5. Why is constructor injection preferred over field injection?
6. What is the difference between @Component, @Service, @Repository, @Controller?
7. What is the bean lifecycle in Spring?
8. What are Spring Bean scopes?
9. What is @Transactional? What are its propagation types?
10. What is the difference between @Controller and @RestController?
11. What is @RequestParam vs @PathVariable vs @RequestBody?
12. What is ResponseEntity? When do you use it?
13. What is @RestControllerAdvice? How does it work?
14. What is Spring Data JPA? How is it different from Hibernate?
15. What is the N+1 problem? How do you solve it?
16. What is FetchType.LAZY vs EAGER?
17. What is CascadeType?
18. What is JPA Specification?
19. How does pagination work in Spring Data JPA?
20. What is Spring Security?
21. How do you implement JWT in Spring Boot?
22. What is @Valid and how does Bean Validation work?
23. What is Spring Boot Actuator?
24. How do you configure multiple profiles in Spring Boot?
25. What is auto-configuration in Spring Boot?

## Top 20 Database Interview Questions
1. What is normalization? Explain 1NF, 2NF, 3NF.
2. What are ACID properties?
3. What is the difference between INNER JOIN and LEFT JOIN?
4. What is an index? When should you use it?
5. What is the difference between clustered and non-clustered index?
6. What are database transactions?
7. What is an isolation level? What are the four levels?
8. What is a deadlock in a database?
9. What is the difference between DELETE, TRUNCATE, and DROP?
10. What is a subquery vs a JOIN?
11. What are aggregate functions?
12. What is a stored procedure?
13. What is a view?
14. What is optimistic vs pessimistic locking?
15. What is connection pooling?
16. What is the difference between SQL and NoSQL?
17. When would you choose MongoDB over MySQL?
18. What is EXPLAIN in MySQL?
19. What is referential integrity?
20. What is a composite key?

## Top 15 React Interview Questions
1. What is the virtual DOM? How does React's reconciliation work?
2. What is the difference between state and props?
3. What is useState hook?
4. What is useEffect hook? What are its dependency array behaviors?
5. What is the difference between controlled and uncontrolled components?
6. What is lifting state up?
7. What is Context API?
8. What is useMemo? When do you use it?
9. What is useCallback?
10. What is React.memo?
11. What is the key prop and why is it important?
12. How do you handle API calls in React?
13. What is React Router?
14. What is lazy loading in React?
15. What is prop drilling?

## Top 10 CS Fundamentals Questions
1. What is the difference between process and thread?
2. What is deadlock? How do you prevent it?
3. What are the OSI layers?
4. What is the difference between TCP and UDP?
5. What is CORS? How do you handle it?
6. What is virtual memory?
7. What is a semaphore vs mutex?
8. What happens when you type a URL in a browser?
9. What is HTTP vs HTTPS?
10. What is CPU scheduling? Name 3 algorithms.

## Top 10 HR Interview Questions
1. Tell me about yourself
2. Why do you want to join [company]?
3. What are your strengths and weaknesses?
4. Where do you see yourself in 5 years?
5. Why should we hire you?
6. What is your expected salary?
7. Are you comfortable with relocation?
8. Tell me about a challenge you overcame
9. How do you handle failure?
10. Do you have any questions for us?

---

# APPENDIX B: PROJECT DISCUSSION MASTER Q&A

## BookMySeat — 20 Expected Interview Questions

1. **What is BookMySeat?** → End-to-end full-stack movie ticket booking system with Java/Spring Boot backend and React frontend
2. **What is the architecture?** → Monolithic Spring Boot app, layered Controller-Service-Repository
3. **How many APIs did you build and what do they cover?** → 20+ APIs across 8 domain modules
4. **Walk me through the seat booking flow** → Request → validation → seat availability check → @Transactional booking → confirmation
5. **How did you handle concurrency in seat booking?** → Optimistic locking with @Version, rollback on conflict
6. **What is the database schema?** → 8 tables: Users, Cities, Movies, Theaters, Screens, Seats, Shows, Bookings
7. **What is the relationship between Show, Seat, and Booking?** → Booking is the join entity; one booking references one show and one seat
8. **How does the Admin Dashboard work?** → Dedicated admin endpoints protected by role-based access; ADMIN role required
9. **How did you handle authentication?** → Currently session-based or no auth; plan to add JWT
10. **How would you add JWT to BookMySeat?** → Spring Security + JWT filter + token generation on login
11. **What was the most complex API you built?** → POST /bookings — validation, seat lock, transaction, response
12. **What challenges did you face?** → Seat concurrency, complex entity relationships, admin role separation
13. **How did you test your APIs?** → Postman for manual testing; plan to add JUnit/Mockito
14. **How did you deploy the application?** → Maven build → JAR → deployed on Render
15. **What would you improve about BookMySeat?** → JWT auth, Redis caching, unit tests, email notifications, Docker
16. **What design patterns did you use?** → Repository pattern, DTO pattern, Singleton (Spring beans), Factory (Spring)
17. **How does the visual seat selection work?** → 2D grid, color-coded by type and availability, state updates dynamically
18. **What is the biggest lesson learned from this project?** → Database design matters most — schema changes later are costly
19. **How did you manage the frontend-backend communication?** → REST API calls from frontend; CORS configured in Spring Boot
20. **How long did it take to build?** → [Your actual timeline] — give honest answer

## Blog API — 15 Expected Interview Questions

1. What is Blog API? → Production-ready REST API for a blogging platform
2. What makes it "production-ready"? → DTO contracts, validation, error handling, Swagger docs, pagination
3. What is JPA Specification? How did you use it? → Dynamic query predicates via Criteria API
4. How does pagination work? → PageRequest with Pageable, Page<T> response with metadata
5. How did you implement sorting? → Sort.by("field").direction in PageRequest
6. What is Swagger and how did you set it up? → springdoc-openapi dependency + annotations
7. How does Bean Validation work? → @Valid + Jakarta annotations + @RestControllerAdvice
8. What is a DTO and why do you use it? → Separation of API contract from database entity
9. How do you handle filtering? → JPA Specification combined with Specification.where().and()
10. What is the difference between @Query and JPA Specification? → Specification is programmatic + composable vs static JPQL string
11. What validations did you implement? → @NotBlank (title/content), @Size (length limits), @Email
12. What does centralized exception handling look like in code? → @RestControllerAdvice class handling specific exceptions
13. What is the structure of your error response? → { timestamp, status, error, message, path }
14. What would you add to make it even more production-ready? → Authentication, rate limiting, caching, logging, monitoring
15. What is the difference between Page and Slice? → Page has total count (extra query); Slice just knows if there's a next page

---

# APPENDIX C: HOW TO ANSWER INTERVIEW QUESTIONS PROFESSIONALLY AS A FRESHER

## 1. Communication Foundation

**The Golden Rule:** Speak slower than you think you should. Nervousness makes everyone speak too fast.

**Filler Word Elimination:** Replace "um", "uh", "like" with a brief pause. A pause shows confidence; a filler shows uncertainty.

**Sentence Structure:** Start with the conclusion, then explain:
- Bad: "So I was thinking, and I looked at the problem, and I think arrays might work because..."
- Good: "I'd use a HashMap here because lookup is O(1). Let me walk through my reasoning..."

## 2. The Structured Answer Framework (PREP)

**P** — Point: State your main answer first (1 sentence)
**R** — Reason: Explain why (1-2 sentences)
**E** — Example: Concrete example from your project (1-2 sentences)
**P** — Point: Restate conclusion (1 sentence)

**Example:**
Q: "What is constructor injection and why do you prefer it?"
A: "Constructor injection is the preferred way to inject dependencies in Spring Boot. I prefer it because it ensures all required dependencies are provided at object creation time, making the class immutable and easier to test. In BookMySeat, all my service classes use constructor injection — for example, BookingService receives its dependencies (BookingRepository, ShowRepository) through the constructor. This makes the code cleaner and prevents null injection issues."

## 3. How to Explain Your Projects

**The 3-Layer Explanation:**

**Layer 1 — Executive Summary (30 seconds):**
"BookMySeat is a full-stack movie ticket booking application. Java and Spring Boot on the backend, React on the frontend, MySQL as the database."

**Layer 2 — Technical Overview (2 minutes):**
"The backend follows a layered Controller-Service-Repository architecture. I built 20+ REST APIs covering 8 domain modules. The database has 8 tables with complex relationships — the Booking entity is the central table linking Users, Shows, and Seats."

**Layer 3 — Deep Dive (on demand, 3–5 minutes):**
"Let me walk you through the seat booking flow specifically. When a user selects a seat and confirms..."

Always start at Layer 1 and only go deeper when the interviewer asks.

## 4. The STAR Method for Behavioral Questions

**S** — Situation: Set the scene briefly (1-2 sentences)
**T** — Task: What was your responsibility? (1 sentence)
**A** — Action: Exactly what YOU did (2-3 sentences — this is the main part)
**R** — Result: What was the outcome? Quantify if possible (1-2 sentences)

**Example:**
Q: "Tell me about a technical challenge you faced."
A: "During my internship at SohamGlobal Group, I was tasked with designing the RESTful APIs for our product review management module. [S+T] The challenge was that reviews needed complex filtering — by product, rating, date range, and authenticity flag — and I wasn't sure how to design this cleanly. [A] I researched JPA Specification and implemented a dynamic filtering approach where the query was built based on whichever filters were present in the request. I also discussed my approach with my senior before implementing. [R] The API handled all filter combinations without code duplication, and my senior appreciated the clean design during code review."

## 5. When You Don't Know the Answer

**Option A — Partial Knowledge:**
"I'm familiar with [topic] at a conceptual level. My understanding is [explain what you know]. I haven't implemented it directly, but I'd approach it by [your thinking]."

**Option B — Complete Unknown:**
"I haven't worked with that specific technology yet, but I'm familiar with the problem it solves. I'd be glad to learn it — I typically approach new technologies by [reading docs, building a small project, watching a tutorial]. Could you give me a high-level overview of what you're using it for?"

**Never say:** "I don't know" and stop. Always bridge to what you DO know.

## 6. Technical Thinking Out Loud (Coding Interviews)

**Before Writing a Single Line:**
1. "Let me understand the problem first..." → Restate it in your words
2. "Let me think about a few examples..." → Walk through 2 input/output examples
3. "For edge cases, I need to consider..." → Empty input, single element, negatives
4. "My initial approach would be..." → State brute force first
5. "But we can optimize this by..." → Better approach if you see one
6. "The time complexity would be O(...) and space complexity O(...)"
7. "Let me start coding..."

**While Coding:**
- Narrate what each section does: "This loop iterates through the array..."
- If stuck: "I need to think about this part for a moment... [brief pause, not silence]"
- "I'm going to handle the base case first, then the main logic"

**After Coding:**
- Walk through your code with an example
- Check edge cases
- State: "I think this is correct. The time complexity is O(n) and space is O(n) due to the HashMap."

## 7. Common Fresher Interview Mistakes to AVOID

1. **Memorizing answers without understanding** → Interviewers always ask follow-up questions
2. **Starting to code without thinking** → Always articulate approach first
3. **Saying "I don't know" and stopping** → Always bridge to related knowledge
4. **Being too quiet in coding interviews** → Think out loud constantly
5. **Overpromising project features** → Only claim what you actually built
6. **Not asking for clarification** → "Could I ask a clarifying question?" is always fine
7. **Monotone delivery** → Practice varying your tone
8. **Speaking too fast when nervous** → Breathe, pause intentionally
9. **Ignoring time complexity** → Always state it after coding
10. **Not connecting answers to your project** → Always ground answers in real experience

## 8. Questions to Ask the Interviewer

Always prepare 3 smart questions. These show genuine interest:
1. "What does the onboarding process look like for a fresher joining the team?"
2. "What does the tech stack at [company] look like, and are there opportunities to work with new technologies?"
3. "What does a typical day look like for a junior developer on your team?"
4. "What are the opportunities for growth and learning at [company]?"

**Never ask:** Salary (in first technical round), leave policies, or "What exactly does your company do?" (Research beforehand)

## 9. Confidence Mindset for Interviews

**Remember your real achievements:**
- You built a full-stack movie booking system with 20+ APIs
- You have real internship experience building REST APIs in a team
- You have 150+ LeetCode problems solved
- You have an 8.34 CGPA from engineering
- You know Java, Spring Boot, React, MySQL, MongoDB — a real full-stack developer

**Pre-interview ritual:**
- Review your project notes 30 minutes before
- Read your own resume
- Take 3 deep breaths before entering
- Remember: The interviewer wants you to succeed — they're not your enemy

## 10. How to Leave a Strong Final Impression

1. **Be genuinely enthusiastic:** "I'm really excited about the opportunity to work on [specific aspect of their work]"
2. **Summarize your value:** "I believe my experience with Spring Boot APIs, real internship work, and my projects align well with this role"
3. **Thank them sincerely:** "Thank you for this opportunity — I really enjoyed this conversation"
4. **Follow up:** Send a thank-you email within 24 hours: "Thank you for the interview. I enjoyed discussing [specific topic]. I'm very excited about this opportunity."

---

# APPENDIX D: WEEKLY UPSKILLING SUMMARY

| Week | Topic | Goal |
|------|-------|------|
| Week 1 | Docker | Install + understand containers; Dockerfile basics |
| Week 2 | Docker | Run Spring Boot app in Docker container |
| Week 3 | CI/CD | Understand GitHub Actions; create simple workflow |
| Week 4 | Redis | Understand caching; Spring Boot + Redis @Cacheable |

## How to Balance Everything Efficiently
- **DSA:** 1.5 hrs/day — consistent; don't skip even on busy days
- **Core study:** 4–5 hrs/day — rotate Java/Spring Boot/DB/React
- **Upskilling:** 1 hr/day — treat it as bonus, not core
- **Projects:** 1 hr/day — articulation practice, not new development
- **HR/Communication:** 30 min/day — write + speak, not just read

## How to Stay Industry-Ready as a Fresher
1. Follow tech blogs: Baeldung (Java/Spring), Dev.to, Medium Engineering blogs
2. Follow developers on LinkedIn: Share your learning journey
3. GitHub activity: Keep your repos clean and updated
4. Push code regularly — green squares on GitHub matter to recruiters
5. Write about what you learn: LinkedIn posts about your projects get visibility

---

*Roadmap created for Dikshant Koriwar | B.E. Information Technology | CGPA 8.34 | Target: Full Stack / Java Backend / SDE Fresher Roles*

*You have the skills. Execute the plan. Get the offer.*
