# 🧱 Object-Oriented Programming (OOPs) in Java – Complete Notes

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

## 1. Classes and Objects

### Class
- **Blueprint/template** for creating objects
- Logical entity that defines properties and behaviors
- Doesn't consume memory until object is created

### Object
- **Instance of a class** with actual memory allocation
- Real-world entity with state and behavior
- Created using `new` keyword

```java
// Class definition
class Student {
    // Instance variables (state)
    int rollNo;
    String name;
    
    // Method (behavior)
    void study() {
        System.out.println(name + " is studying");
    }
}

// Creating objects
public class Main {
    public static void main(String[] args) {
        // Object creation
        Student s1 = new Student();
        s1.rollNo = 101;
        s1.name = "John";
        s1.study();  // Output: John is studying
        
        Student s2 = new Student();
        s2.rollNo = 102;
        s2.name = "Jane";
        s2.study();  // Output: Jane is studying
    }
}
```

### Key Points for Interview:
1. **Class vs Object**: Class is blueprint, object is instance
2. **Object creation**: `new` keyword allocates memory in heap
3. **Multiple objects**: Each object has separate copy of instance variables
4. **Anonymous objects**: Objects without reference - used only once
   ```java
   new Student().study();  // Anonymous object
   ```
5. **Memory allocation**: Objects in Heap, references in Stack

---

## 2. JVM Data Areas

### Memory Structure During Execution:

```
┌─────────────────────────────────┐
│        JVM Memory Areas         │
├─────────────────────────────────┤
│ 1. Method Area (MetaSpace)      │ ← Class metadata, static variables
│                                 │
│ 2. Heap Area                    │ ← Objects, instance variables
│                                 │
│ 3. Stack Area                   │ ← Method calls, local variables
│                                 │
│ 4. PC Registers                 │ ← Thread instruction pointers
│                                 │
│ 5. Native Method Stacks         │ ← Native method calls
└─────────────────────────────────┘
```

### Detailed Explanation:

#### 1. Method Area (MetaSpace in Java 8+)
- **Stores**: Class structure, method data, static variables, runtime constant pool
- **Shared**: All threads share this area
- **Example**: `static int count = 0;`

#### 2. Heap Area
- **Stores**: All objects and arrays
- **Divided into**:
    - Young Generation (Eden + Survivor spaces)
    - Old Generation
- **Example**: `Student s1 = new Student();`
- **Garbage Collection**: Main target area

#### 3. Stack Area
- **Stores**: Method calls, local variables, partial results
- **Stack Frame**: Created for each method call
- **LIFO**: Last In First Out structure
- **Example**:
  ```java
  void method() {
      int x = 10;  // x stored in stack
      Student s = new Student();  // s in stack, object in heap
  }
  ```

#### 4. PC Register
- **Stores**: Address of current executing instruction
- **Thread-specific**: Each thread has its own PC register

#### 5. Native Method Stack
- **Stores**: Native method information (C/C++ code)

### Memory Example:
```java
class Test {
    static int count = 0;         // Method Area
    int id;                        // Heap (when object created)
    
    void display() {
        int x = 10;               // Stack
        System.out.println(x);
    }
}

public class Main {
    public static void main(String[] args) {
        Test t1 = new Test();     // t1 ref in Stack, object in Heap
        t1.id = 1;
        t1.display();             // New stack frame for display()
    }
}
```

### Interview Questions:
1. **Where are objects stored?** → Heap
2. **Where are local variables stored?** → Stack
3. **Where are static variables stored?** → Method Area
4. **What is difference between Heap and Stack?**
    - Heap: Dynamic allocation, objects, GC managed
    - Stack: Static allocation, method calls, faster access
5. **What happens when stack memory is full?** → StackOverflowError
6. **What happens when heap memory is full?** → OutOfMemoryError

---

## 3. Instance Variables vs Local Variables

### Comparison Table:
| Aspect | Instance Variables | Local Variables |
|--------|-------------------|----------------|
| **Declaration** | Inside class, outside methods | Inside method/block/constructor |
| **Memory** | Heap (with object) | Stack |
| **Default Value** | Yes (0, null, false) | No (must initialize) |
| **Scope** | Entire object | Only within method/block |
| **Lifetime** | Until object exists | Until method completes |
| **Access Specifiers** | Can have (public, private, etc.) | Cannot have |
| **static** | Can be static | Cannot be static |
| **Initialization** | Optional (auto-initialized) | Mandatory before use |

### Example:
```java
class Employee {
    // Instance Variables
    private String name;     // Default: null
    private int id;          // Default: 0
    private double salary;   // Default: 0.0
    static String company = "ABC Corp";  // Static variable
    
    void calculateBonus() {
        // Local Variables
        double bonusRate = 0.1;    // Must initialize
        int yearsOfService = 5;    // Must initialize
        
        double bonus = salary * bonusRate * yearsOfService;
        System.out.println("Bonus: " + bonus);
        
        // Local variable shadows instance variable
        String name = "Local Name";  // Different from instance 'name'
    }
    
    void printDetails() {
        // Can access instance variables directly
        System.out.println(name + " " + id + " " + salary);
    }
}
```

### Common Interview Questions:

**Q: What happens if we don't initialize local variables?**
```java
void test() {
    int x;
    System.out.println(x);  // COMPILE ERROR: variable x might not have been initialized
}
```

**Q: Can local variables have access modifiers?**
```java
void method() {
    private int x = 10;  // COMPILE ERROR: Illegal modifier for parameter x
}
```

**Q: Difference between instance variable and static variable?**
```java
class Test {
    int instanceVar;      // Each object has separate copy
    static int staticVar; // Shared by all objects
}
```

**Q: Shadowing example?**
```java
class Test {
    int x = 10;  // Instance variable
    
    void method(int x) {  // Parameter shadows instance variable
        System.out.println(x);        // 20 (parameter)
        System.out.println(this.x);   // 10 (instance variable)
    }
    
    public static void main(String[] args) {
        Test t = new Test();
        t.method(20);
    }
}
```

---

## 4. Method Overloading

### Definition:
- **Multiple methods** with **same name** but **different parameters** in same class
- **Compile-time polymorphism** (static binding)

### Rules:
1. **Different parameter list** (must):
    - Different number of parameters
    - Different types of parameters
    - Different order of parameters

2. **Return type** can be same or different (NOT sufficient alone)
3. **Access modifiers** can be different
4. **Can overload static, final, and private methods**

### Examples:

#### Type 1: Different Number of Parameters
```java
class Calculator {
    int add(int a, int b) {
        return a + b;
    }
    
    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

#### Type 2: Different Types of Parameters
```java
class Printer {
    void print(int num) {
        System.out.println("Printing integer: " + num);
    }
    
    void print(String text) {
        System.out.println("Printing string: " + text);
    }
    
    void print(double num) {
        System.out.println("Printing double: " + num);
    }
}
```

#### Type 3: Different Order of Parameters
```java
class Data {
    void display(int id, String name) {
        System.out.println("ID: " + id + ", Name: " + name);
    }
    
    void display(String name, int id) {
        System.out.println("Name: " + name + ", ID: " + id);
    }
}
```

### Invalid Overloading Examples:

```java
class Invalid {
    // INVALID: Only return type different
    int process(int x) { return x; }
    double process(int x) { return x * 1.0; }  // COMPILE ERROR
    
    // INVALID: Same signature
    void show(String s) { }
    void show(String s) { }  // COMPILE ERROR
}
```

### Method Overloading with Type Promotion:

```java
class Promotion {
    void display(int a) {
        System.out.println("int: " + a);
    }
    
    void display(long a) {
        System.out.println("long: " + a);
    }
    
    public static void main(String[] args) {
        Promotion p = new Promotion();
        p.display(10);     // Calls int version
        p.display(10L);    // Calls long version
        p.display('A');    // Calls int version (char → int promotion)
    }
}
```

### Can we overload main() method?
```java
public class Main {
    // Valid main method
    public static void main(String[] args) {
        System.out.println("Standard main");
        main(10);  // Calling overloaded main
    }
    
    // Overloaded main methods
    public static void main(int x) {
        System.out.println("Overloaded main with int: " + x);
    }
    
    public static void main(String args) {
        System.out.println("Overloaded main with String: " + args);
    }
}
```

### Interview Questions:

**Q: Why method overloading is compile-time polymorphism?**
- Method binding happens at compile time based on method signature

**Q: Can we overload methods by changing only return type?**
- No, compiler gives error

**Q: Can we overload static methods?**
- Yes, static methods can be overloaded

**Q: Can we overload main() method?**
- Yes, but JVM will only call `public static void main(String[] args)`

**Q: What is method signature in Java?**
- Method name + parameter list (return type NOT included)

---

## 5. Wrapper Classes

### Definition:
- **Wrap primitive types** into objects
- Provide utility methods for conversion and operations
- Enable primitives to be used in collections

### Wrapper Classes Table:
| Primitive | Wrapper Class | Super Class | Example |
|-----------|---------------|-------------|---------|
| byte | Byte | Number | `Byte b = 10;` |
| short | Short | Number | `Short s = 100;` |
| int | Integer | Number | `Integer i = 1000;` |
| long | Long | Number | `Long l = 10000L;` |
| float | Float | Number | `Float f = 3.14f;` |
| double | Double | Number | `Double d = 3.14159;` |
| char | Character | Object | `Character c = 'A';` |
| boolean | Boolean | Object | `Boolean b = true;` |

### Boxing and Unboxing:

```java
public class WrapperDemo {
    public static void main(String[] args) {
        // 1. Manual Boxing (before Java 5)
        int primitive1 = 100;
        Integer wrapper1 = Integer.valueOf(primitive1);  // Boxing
        
        // 2. Manual Unboxing
        int primitive2 = wrapper1.intValue();  // Unboxing
        
        // 3. Autoboxing (Java 5+)
        Integer autoBoxed = 200;  // Auto-boxing: int → Integer
        
        // 4. Auto-unboxing
        int autoUnboxed = autoBoxed;  // Auto-unboxing: Integer → int
        
        // 5. Examples with operations
        Integer a = 10;
        Integer b = 20;
        Integer c = a + b;  // Auto-unbox, add, auto-box
        
        // 6. Null handling
        Integer nullWrapper = null;
        // int val = nullWrapper;  // NullPointerException at runtime
    }
}
```

### Utility Methods:

```java
public class WrapperMethods {
    public static void main(String[] args) {
        // String to primitive
        int num = Integer.parseInt("123");
        double d = Double.parseDouble("45.67");
        boolean b = Boolean.parseBoolean("true");
        
        // String to Wrapper
        Integer wrapper = Integer.valueOf("456");
        
        // Primitive to String
        String str1 = Integer.toString(123);
        String str2 = Double.toString(45.67);
        
        // Comparison
        Integer x = 100;
        Integer y = 200;
        System.out.println(x.compareTo(y));  // -1 (x < y)
        System.out.println(Integer.compare(x, y));  // -1
        
        // Constants
        System.out.println(Integer.MAX_VALUE);  // 2147483647
        System.out.println(Integer.MIN_VALUE);  // -2147483648
        System.out.println(Integer.SIZE);       // 32 bits
        System.out.println(Integer.BYTES);      // 4 bytes
        
        // Binary/Hex conversion
        System.out.println(Integer.toBinaryString(10));  // 1010
        System.out.println(Integer.toHexString(255));    // ff
        
        // Character methods
        System.out.println(Character.isDigit('5'));   // true
        System.out.println(Character.isLetter('A'));  // true
        System.out.println(Character.isUpperCase('A')); // true
        System.out.println(Character.toLowerCase('A')); // 'a'
    }
}
```

### Integer Caching (-128 to 127):

```java
public class IntegerCache {
    public static void main(String[] args) {
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);  // true (cached)
        
        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);  // false (not cached)
        
        // Always use equals() for comparison
        System.out.println(c.equals(d));  // true
    }
}
```

### Interview Questions:

**Q: Why do we need wrapper classes?**
1. To use primitives in collections (which only store objects)
2. To provide utility methods
3. To support null values
4. For reflection API

**Q: What is autoboxing and unboxing?**
- Autoboxing: Automatic conversion primitive → wrapper
- Unboxing: Automatic conversion wrapper → primitive

**Q: What is Integer caching?**
- Java caches Integer objects from -128 to 127 for performance

**Q: `Integer i = new Integer(10)` vs `Integer i = 10`?**
- First uses constructor (creates new object always)
- Second uses autoboxing (may use cached value)

**Q: How to convert String to int?**
- `Integer.parseInt("123")`
- `Integer.valueOf("123")`

**Q: What happens if we try to parse invalid string?**
- `NumberFormatException`

---

## 6. Encapsulation

### Definition:
- **Wrapping data (variables) and methods** together as a single unit
- **Data hiding**: Making fields private and providing public methods
- **Protection**: Preventing direct access to internal data

### Implementation:

```java
// Without Encapsulation (BAD PRACTICE)
class BadAccount {
    public double balance;  // Direct access allowed
    
    public static void main(String[] args) {
        BadAccount acc = new BadAccount();
        acc.balance = -1000;  // Can set invalid value directly
    }
}

// With Encapsulation (GOOD PRACTICE)
class BankAccount {
    // 1. Private data members (data hiding)
    private String accountNumber;
    private String accountHolder;
    private double balance;
    
    // 2. Constructor
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        setBalance(initialBalance);  // Using setter for validation
    }
    
    // 3. Public getter methods (read access)
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // 4. Public setter methods with validation (write access)
    public void setAccountHolder(String accountHolder) {
        if (accountHolder != null && !accountHolder.trim().isEmpty()) {
            this.accountHolder = accountHolder;
        }
    }
    
    private void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Error: Balance cannot be negative");
        }
    }
    
    // 5. Business methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Error: Invalid deposit amount");
        }
    }
    
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
        } else {
            System.out.println("Error: Insufficient funds or invalid amount");
        }
    }
    
    // 6. Read-only example (no setter for accountNumber)
    // accountNumber can only be set through constructor
}

// Usage
public class Main {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("123456", "John Doe", 1000);
        
        // Can't access private members directly
        // acc.balance = 5000;  // COMPILE ERROR
        
        // Use public methods
        acc.deposit(500);
        acc.withdraw(200);
        System.out.println("Balance: $" + acc.getBalance());
        
        // Update account holder
        acc.setAccountHolder("John Smith");
        
        // accountNumber is read-only
        // acc.setAccountNumber("789");  // NO SUCH METHOD
    }
}
```

### Benefits of Encapsulation:
1. **Data Security**: Control over data access
2. **Validation**: Ensure data integrity
3. **Flexibility**: Change implementation without affecting clients
4. **Read-only/Write-only**: Control access level
5. **Maintainability**: Easier to debug and test

### Advanced Example with Builder Pattern:

```java
class Employee {
    // Required parameters
    private final String id;
    private final String name;
    
    // Optional parameters
    private final String email;
    private final String phone;
    private final String department;
    private final double salary;
    
    // Private constructor
    private Employee(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.department = builder.department;
        this.salary = builder.salary;
    }
    
    // Getters only (immutable)
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    
    // Builder class
    public static class Builder {
        // Required parameters
        private final String id;
        private final String name;
        
        // Optional parameters with defaults
        private String email = "N/A";
        private String phone = "N/A";
        private String department = "General";
        private double salary = 0.0;
        
        public Builder(String id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
        
        public Builder department(String department) {
            this.department = department;
            return this;
        }
        
        public Builder salary(double salary) {
            this.salary = salary;
            return this;
        }
        
        public Employee build() {
            return new Employee(this);
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee.Builder("E001", "John")
            .email("john@company.com")
            .department("IT")
            .salary(50000)
            .build();
            
        System.out.println(emp.getName() + " - " + emp.getDepartment());
    }
}
```

### Interview Questions:

**Q: What is encapsulation?**
- Wrapping data and methods together, hiding implementation details

**Q: How to achieve encapsulation in Java?**
1. Declare variables as private
2. Provide public getter and setter methods
3. Add validation in setters

**Q: What are getters and setters?**
- Getters: Methods to read private data
- Setters: Methods to modify private data with validation

**Q: Why make variables private?**
- Prevent direct access, maintain data integrity, control modifications

**Q: Can we have a read-only class?**
- Yes, provide only getters, no setters (and use final variables)

**Q: What is the difference between data hiding and encapsulation?**
- Data hiding: Making variables private (part of encapsulation)
- Encapsulation: Wrapping data + methods + data hiding

---

## 7. this Keyword

### Definition:
- **Reference variable** that refers to **current object**
- Used to differentiate between instance and local variables
- Can be used to call other constructors (constructor chaining)

### Uses of `this`:

#### 1. Resolve naming conflicts

```java
class Student {
    private int id;      // Instance variable
    private String name; // Instance variable
    
    // Parameter names same as instance variables
    public Student(int id, String name) {
        this.id = id;    // this.id refers to instance variable
        this.name = name; // id refers to parameter
    }
}
```

#### 2. Call current class method

```java
class Calculator {
    public void add(int a, int b) {
        System.out.println("Sum: " + (a + b));
    }
    
    public void calculate() {
        // Call another method of same class
        this.add(10, 20);  // this is optional here
        add(30, 40);       // Works without this
    }
}
```

#### 3. Constructor chaining

```java
class Person {
    private String name;
    private int age;
    private String city;
    
    // Default constructor
    public Person() {
        this("Unknown", 0, "Unknown");  // Calls parameterized constructor
    }
    
    // Constructor with 2 parameters
    public Person(String name, int age) {
        this(name, age, "Unknown");  // Calls 3-parameter constructor
    }
    
    // Main constructor with 3 parameters
    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }
}
```

#### 4. Return current object (method chaining)

```java
class StringBuilderExample {
    private StringBuilder sb = new StringBuilder();
    
    public StringBuilderExample append(String str) {
        sb.append(str);
        return this;  // Return current object for chaining
    }
    
    public String toString() {
        return sb.toString();
    }
    
    public static void main(String[] args) {
        StringBuilderExample sbe = new StringBuilderExample();
        sbe.append("Hello").append(" ").append("World");
        System.out.println(sbe);  // Hello World
    }
}
```

#### 5. Pass current object as parameter

```java
class A {
    public void display() {
        System.out.println("Display method");
    }
    
    public void show() {
        B b = new B();
        b.process(this);  // Pass current object to another class
    }
}

class B {
    public void process(A a) {
        a.display();
    }
}
```

### Common Mistakes:

```java
class ThisMistakes {
    private int x;
    
    // MISTAKE: this in static context
    public static void staticMethod() {
        // System.out.println(this.x);  // COMPILE ERROR
        // Cannot use this in static method
    }
    
    // MISTAKE: Unnecessary this
    public void method1(int x) {
        int y = 10;
        // this.y = 20;  // COMPILE ERROR - y is local, not instance
    }
}
```

### Interview Questions:

**Q: What is `this` keyword?**
- Reference to current object instance

**Q: Can we use `this` in static method?**
- No, static methods belong to class, not object

**Q: What is constructor chaining?**
- Calling one constructor from another using `this()`

**Q: Where should `this()` call be placed in constructor?**
- Must be first statement in constructor

**Q: Can we use `this` and `super` together?**
- No, both must be first statement, can't have both

---

## 8. Constructors

### Definition:
- **Special method** used to **initialize objects**
- Called automatically when object is created
- Has same name as class, no return type

### Types of Constructors:

#### 1. Default Constructor (No-argument)

```java
class Student {
    private String name;
    private int age;
    
    // Default constructor
    public Student() {
        name = "Unknown";
        age = 0;
        System.out.println("Default constructor called");
    }
}
```

#### 2. Parameterized Constructor

```java
class Student {
    private String name;
    private int age;
    
    // Parameterized constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Parameterized constructor called");
    }
}
```

#### 3. Copy Constructor

```java
class Student {
    private String name;
    private int age;
    
    // Parameterized constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Copy constructor
    public Student(Student other) {
        this.name = other.name;
        this.age = other.age;
        System.out.println("Copy constructor called");
    }
    
    public static void main(String[] args) {
        Student s1 = new Student("John", 20);
        Student s2 = new Student(s1);  // Copy s1 to s2
    }
}
```

### Constructor Overloading:

```java
class Rectangle {
    private int length;
    private int breadth;
    
    // Default constructor
    public Rectangle() {
        this.length = 0;
        this.breadth = 0;
    }
    
    // Square constructor
    public Rectangle(int side) {
        this.length = side;
        this.breadth = side;
    }
    
    // Rectangle constructor
    public Rectangle(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
    }
}
```

### Constructor Chaining:

```java
class Employee {
    private int id;
    private String name;
    private double salary;
    private String department;
    
    // Chain 1
    public Employee() {
        this(0, "Unknown", 0.0, "General");
    }
    
    // Chain 2
    public Employee(int id, String name) {
        this(id, name, 0.0, "General");
    }
    
    // Chain 3
    public Employee(int id, String name, double salary) {
        this(id, name, salary, "General");
    }
    
    // Main constructor
    public Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
}
```

### Private Constructor (Singleton Pattern):

```java
class Singleton {
    // Static variable to hold single instance
    private static Singleton instance;
    
    // Private constructor prevents external instantiation
    private Singleton() {
        System.out.println("Singleton instance created");
    }
    
    // Public method to provide access to instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    
    public void showMessage() {
        System.out.println("Hello from Singleton");
    }
}

public class Main {
    public static void main(String[] args) {
        // Singleton s = new Singleton();  // COMPILE ERROR
        
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        
        System.out.println(s1 == s2);  // true (same instance)
    }
}
```

### Constructor vs Method:

| Constructor | Method |
|------------|--------|
| Same name as class | Any valid name |
| No return type | Must have return type |
| Called automatically | Called explicitly |
| Cannot be static | Can be static |
| Cannot be inherited | Can be inherited |
| Can use `this()`, `super()` | Cannot use `this()`, `super()` |

### Interview Questions:

**Q: What is constructor?**
- Special method to initialize object state

**Q: Can constructor have return type?**
- No, not even void

**Q: Can constructor be private?**
- Yes, used in singleton pattern

**Q: Can constructor be static?**
- No, constructors are called during object creation

**Q: What is constructor overloading?**
- Multiple constructors with different parameters

**Q: What if no constructor is defined?**
- Compiler provides default no-arg constructor

**Q: Does constructor return any value?**
- Returns instance of class (implicitly)

**Q: Can we call constructor explicitly?**
- Yes, using `this()` or `super()`, or `new ClassName()`

---

## 9. Static Keyword

### Definition:
- Keyword for **class-level members** (not object-level)
- Belongs to class, shared by all objects
- Memory allocated only once when class is loaded

### Static Components:

#### 1. Static Variable (Class Variable)

```java
class Counter {
    // Instance variable - separate for each object
    int instanceCount = 0;
    
    // Static variable - shared by all objects
    static int staticCount = 0;
    
    Counter() {
        instanceCount++;
        staticCount++;
        System.out.println("Instance: " + instanceCount + ", Static: " + staticCount);
    }
    
    public static void main(String[] args) {
        Counter c1 = new Counter();  // Instance: 1, Static: 1
        Counter c2 = new Counter();  // Instance: 1, Static: 2
        Counter c3 = new Counter();  // Instance: 1, Static: 3
        
        // Access static variable
        System.out.println("Total objects: " + Counter.staticCount);  // 3
    }
}
```

#### 2. Static Method

```java
class MathUtils {
    // Static method
    public static int add(int a, int b) {
        return a + b;
    }
    
    // Static method
    public static double calculateCircleArea(double radius) {
        return Math.PI * radius * radius;
    }
    
    // Instance method
    public void display() {
        System.out.println("Instance method");
    }
    
    public static void main(String[] args) {
        // Call static methods without object
        int sum = MathUtils.add(5, 10);
        double area = MathUtils.calculateCircleArea(7.0);
        
        // Call instance method requires object
        MathUtils obj = new MathUtils();
        obj.display();
        
        // Can't call non-static from static directly
        // display();  // COMPILE ERROR
    }
}
```

#### 3. Static Block

```java
class Database {
    // Static variable
    static String url;
    static String username;
    static String password;
    
    // Static block - executes when class is loaded
    static {
        System.out.println("Static block executing...");
        url = "jdbc:mysql://localhost:3306/testdb";
        username = "admin";
        password = "password123";
        System.out.println("Database configuration loaded");
    }
    
    // Another static block (executes in order)
    static {
        System.out.println("Second static block");
    }
    
    public static void main(String[] args) {
        System.out.println("Main method executing...");
        System.out.println("URL: " + url);
    }
}
```

#### 4. Static Nested Class

```java
class Outer {
    private static String staticMessage = "Static message";
    private String instanceMessage = "Instance message";
    
    // Static nested class
    static class StaticNested {
        void display() {
            System.out.println(staticMessage);  // Can access static members
            // System.out.println(instanceMessage);  // COMPILE ERROR
        }
    }
    
    // Inner class (non-static)
    class Inner {
        void display() {
            System.out.println(staticMessage);     // Can access
            System.out.println(instanceMessage);   // Can access
        }
    }
    
    public static void main(String[] args) {
        // Create static nested class instance
        Outer.StaticNested nested = new Outer.StaticNested();
        nested.display();
        
        // Create inner class instance
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.display();
    }
}
```

### Static Import:

```java
// Without static import
import java.lang.Math;

class WithoutStaticImport {
    public static void main(String[] args) {
        double result1 = Math.sqrt(25);
        double result2 = Math.pow(2, 3);
        double pi = Math.PI;
    }
}

// With static import
import static java.lang.Math.*;
import static java.lang.System.out;

class WithStaticImport {
    public static void main(String[] args) {
        double result1 = sqrt(25);      // No Math. prefix
        double result2 = pow(2, 3);     // No Math. prefix
        double pi = PI;                 // No Math. prefix
        out.println("Result: " + result1);  // No System. prefix
    }
}
```

### Important Rules:

```java
class StaticRules {
    static int x = 10;
    int y = 20;
    
    static void staticMethod() {
        System.out.println(x);  // OK - static variable
        // System.out.println(y);  // COMPILE ERROR - non-static
        
        // Can't use 'this' or 'super' in static method
        // System.out.println(this.x);  // COMPILE ERROR
    }
    
    void instanceMethod() {
        System.out.println(x);  // OK - can access static
        System.out.println(y);  // OK - can access instance
        System.out.println(this.y);  // OK - can use this
    }
    
    // Can't make static variable inside method
    void method() {
        // static int z = 30;  // COMPILE ERROR
    }
}
```

### Interview Questions:

**Q: What is static keyword?**
- Used for class-level members shared by all objects

**Q: Where are static variables stored?**
- Method Area (before Java 8), Metaspace (Java 8+)

**Q: Can static method access instance variables?**
- No, without object reference

**Q: Can static method be overridden?**
- No, it's method hiding, not overriding

**Q: Can we use this/super in static method?**
- No

**Q: Why main method is static?**
- So JVM can call it without creating object

**Q: Can static block throw exception?**
- Yes, but must be caught or declared

**Q: What is static import?**
- Import static members so they can be used without class name

---

## 10. Class Loading Mechanism

### Class Loading Process:

```
1. Loading → 2. Linking → 3. Initialization
```

### Phase 1: Loading

- Reads `.class` file
- Creates `Class` object in heap
- Stores in method area

```java
// Ways to load a class:
public class ClassLoadingDemo {
    public static void main(String[] args) throws Exception {
        // 1. Using new keyword (most common)
        Student s = new Student();
        
        // 2. Using Class.forName() (explicit loading)
        Class c = Class.forName("Student");
        
        // 3. Using ClassLoader
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        Class c2 = cl.loadClass("Student");
    }
}
```

### Phase 2: Linking

#### Sub-phase 2a: Verification
- Checks bytecode validity
- Security checks
- Format verification

#### Sub-phase 2b: Preparation
- Allocates memory for static variables
- Assigns default values

```java
class VerificationExample {
    static int x;        // Memory allocated, x = 0
    static String name;  // name = null
    static boolean flag; // flag = false
}
```

#### Sub-phase 2c: Resolution
- Converts symbolic references to direct references
- Resolves classes, methods, fields

### Phase 3: Initialization
- Executes static blocks
- Initializes static variables with actual values

```java
class InitializationExample {
    // Step 1: Preparation sets default values
    static int x;        // x = 0 (default)
    static String name;  // name = null
    
    // Step 2: Static block executes
    static {
        System.out.println("Static block 1");
        x = 10;          // x = 10 (actual value)
    }
    
    static {
        System.out.println("Static block 2");
        name = "John";   // name = "John"
    }
    
    // Step 3: Main method executes
    public static void main(String[] args) {
        System.out.println("x = " + x);      // 10
        System.out.println("name = " + name); // John
    }
}
```

### Class Loaders Hierarchy:

```
Bootstrap ClassLoader (Native)
       ↓
Extension ClassLoader
       ↓
Application ClassLoader (System ClassLoader)
       ↓
Custom ClassLoader (if any)
```

#### 1. Bootstrap ClassLoader
- Loads core Java classes (rt.jar)
- Written in native code (C++)
- `java.lang.*`, `java.util.*`, etc.

#### 2. Extension ClassLoader
- Loads from `jre/lib/ext` directory
- Extension libraries

#### 3. Application ClassLoader
- Loads from classpath
- Your application classes

### Custom ClassLoader Example:

```java
import java.io.*;

class CustomClassLoader extends ClassLoader {
    
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, classData, 0, classData.length);
    }
    
    private byte[] loadClassData(String className) {
        String fileName = className.replace('.', '/') + ".class";
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            
            if (is == null) return null;
            
            int data;
            while ((data = is.read()) != -1) {
                bos.write(data);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            return null;
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        CustomClassLoader loader = new CustomClassLoader();
        Class<?> clazz = loader.loadClass("TestClass");
        Object obj = clazz.newInstance();
        System.out.println("Class loaded by: " + 
            obj.getClass().getClassLoader().getClass().getName());
    }
}
```

### When Class is Loaded?

```java
class WhenLoaded {
    static {
        System.out.println("Class WhenLoaded loaded");
    }
    
    // Different triggers:
    public static void main(String[] args) throws Exception {
        // 1. new instance
        new WhenLoaded();
        
        // 2. static member access
        // System.out.println(WhenLoaded.class);
        
        // 3. Class.forName()
        // Class.forName("WhenLoaded");
        
        // 4. Subclass initialization
        // class Child extends WhenLoaded {}
        // new Child();
    }
}
```

### Interview Questions:

**Q: What are the phases of class loading?**
- Loading → Linking (Verification, Preparation, Resolution) → Initialization

**Q: What is ClassLoader hierarchy?**
- Bootstrap → Extension → Application → Custom

**Q: Difference between Class.forName() and ClassLoader.loadClass()?**
- `Class.forName()` initializes class, `loadClass()` only loads

**Q: What is lazy loading?**
- Classes loaded only when needed

**Q: What is static block execution order?**
- In the order they appear in code

**Q: Can we have multiple static blocks?**
- Yes

**Q: When does ClassNotFoundException occur?**
- When class not found in classpath

**Q: When does NoClassDefFoundError occur?**
- When class was present at compile time but not at runtime

---

## 11. Inheritance

### Definition:
- **IS-A relationship** between classes
- Child class acquires properties and behaviors of parent class
- Promotes code reusability

### Syntax:

```java
class Parent {
    // Parent class members
}

class Child extends Parent {
    // Child class members + inherited members
}
```

### Types of Inheritance:

#### 1. Single Inheritance

```java
// Parent class
class Animal {
    void eat() {
        System.out.println("Animal is eating");
    }
}

// Child class
class Dog extends Animal {
    void bark() {
        System.out.println("Dog is barking");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();   // Inherited from Animal
        d.bark();  // Own method
    }
}
```

#### 2. Multilevel Inheritance

```java
class GrandParent {
    void method1() {
        System.out.println("GrandParent method");
    }
}

class Parent extends GrandParent {
    void method2() {
        System.out.println("Parent method");
    }
}

class Child extends Parent {
    void method3() {
        System.out.println("Child method");
    }
}

public class Main {
    public static void main(String[] args) {
        Child c = new Child();
        c.method1();  // From GrandParent
        c.method2();  // From Parent
        c.method3();  // Own method
    }
}
```

#### 3. Hierarchical Inheritance

```java
class Shape {
    void draw() {
        System.out.println("Drawing shape");
    }
}

class Circle extends Shape {
    void drawCircle() {
        System.out.println("Drawing circle");
    }
}

class Square extends Shape {
    void drawSquare() {
        System.out.println("Drawing square");
    }
}

public class Main {
    public static void main(String[] args) {
        Circle c = new Circle();
        c.draw();       // From Shape
        c.drawCircle(); // Own method
        
        Square s = new Square();
        s.draw();       // From Shape
        s.drawSquare(); // Own method
    }
}
```

#### 4. Multiple Inheritance (NOT supported in Java classes)

```java
// THIS WON'T COMPILE
class A {
    void show() {
        System.out.println("A");
    }
}

class B {
    void show() {
        System.out.println("B");
    }
}

// COMPILE ERROR: Multiple inheritance not allowed
// class C extends A, B { }
```

**Solution**: Use interfaces for multiple inheritance

```java
interface A {
    void show();
}

interface B {
    void display();
}

class C implements A, B {
    public void show() {
        System.out.println("Show from A");
    }
    
    public void display() {
        System.out.println("Display from B");
    }
}
```

### Inheritance with Constructors:

```java
class Parent {
    Parent() {
        System.out.println("Parent constructor");
    }
    
    Parent(String message) {
        System.out.println("Parent: " + message);
    }
}

class Child extends Parent {
    Child() {
        // super() called implicitly
        System.out.println("Child constructor");
    }
    
    Child(String message) {
        super("From Child");  // Explicit super call
        System.out.println("Child: " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        new Child();
        // Output:
        // Parent constructor
        // Child constructor
        
        new Child("Hello");
        // Output:
        // Parent: From Child
        // Child: Hello
    }
}
```

### Method Overriding in Inheritance:

```java
class Bank {
    double getInterestRate() {
        return 0.0;
    }
}

class SBI extends Bank {
    @Override
    double getInterestRate() {
        return 7.5;
    }
}

class HDFC extends Bank {
    @Override
    double getInterestRate() {
        return 8.0;
    }
}

public class Main {
    public static void main(String[] args) {
        Bank sbi = new SBI();
        Bank hdfc = new HDFC();
        
        System.out.println("SBI Rate: " + sbi.getInterestRate());  // 7.5
        System.out.println("HDFC Rate: " + hdfc.getInterestRate()); // 8.0
    }
}
```

### What is Inherited?

```java
class Parent {
    public int publicVar = 1;
    protected int protectedVar = 2;
    int defaultVar = 3;          // package-private
    private int privateVar = 4;  // NOT inherited
    
    public void publicMethod() { }
    protected void protectedMethod() { }
    void defaultMethod() { }
    private void privateMethod() { }  // NOT inherited
}

class Child extends Parent {
    void test() {
        System.out.println(publicVar);     // OK
        System.out.println(protectedVar);  // OK
        System.out.println(defaultVar);    // OK (if same package)
        // System.out.println(privateVar); // COMPILE ERROR
        
        publicMethod();     // OK
        protectedMethod();  // OK
        defaultMethod();    // OK (if same package)
        // privateMethod(); // COMPILE ERROR
    }
}
```

### Interview Questions:

**Q: What is inheritance?**
- Mechanism where child class acquires properties of parent class

**Q: Why multiple inheritance not supported in Java classes?**
- Due to diamond problem (ambiguity)

**Q: Can we inherit constructors?**
- No, but child constructor can call parent constructor using super()

**Q: Can we inherit private members?**
- No, private members are not accessible in child class

**Q: What is super keyword?**
- Refers to immediate parent class

**Q: Can we extend final class?**
- No

**Q: What is the root class in Java?**
- `java.lang.Object`

---

## 12. Packages and Access Modifiers

### Packages:

#### Definition:
- **Folder/namespace** for organizing classes
- Prevents naming conflicts
- Provides access control

#### Creating Package:

```java
// File: com/company/Employee.java
package com.company;

public class Employee {
    private String name;
    private int id;
    
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name);
    }
}
```

#### Using Package:

```java
// File: Main.java
import com.company.Employee;

public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee("John", 101);
        emp.display();
    }
}
```

#### Types of Import:

```java
// 1. Import specific class
import java.util.ArrayList;

// 2. Import all classes from package
import java.util.*;

// 3. Static import
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

// 4. Fully qualified name (no import needed)
java.util.Date date = new java.util.Date();
```

### Access Modifiers:

#### 1. private
- Access only within same class

```java
class Example {
    private int privateVar = 10;
    
    private void privateMethod() {
        System.out.println("Private method");
    }
    
    public void accessPrivate() {
        System.out.println(privateVar);  // OK - same class
        privateMethod();                 // OK - same class
    }
}

class Test {
    public static void main(String[] args) {
        Example e = new Example();
        // System.out.println(e.privateVar);  // COMPILE ERROR
        // e.privateMethod();                 // COMPILE ERROR
        e.accessPrivate();  // OK
    }
}
```

#### 2. default (package-private)
- Access within same package (no keyword)

```java
// File: package1/ClassA.java
package package1;

class ClassA {  // Default access
    int defaultVar = 20;
    
    void defaultMethod() {
        System.out.println("Default method");
    }
}

// File: package1/ClassB.java
package package1;

public class ClassB {
    public void test() {
        ClassA a = new ClassA();
        System.out.println(a.defaultVar);  // OK - same package
        a.defaultMethod();                 // OK - same package
    }
}

// File: package2/ClassC.java
package package2;
import package1.ClassA;

public class ClassC {
    public void test() {
        // ClassA a = new ClassA();  // COMPILE ERROR - default class
        // a.defaultMethod();        // COMPILE ERROR
    }
}
```

#### 3. protected
- Access within package + subclasses (even in different package)

```java
// File: package1/Parent.java
package package1;

public class Parent {
    protected int protectedVar = 30;
    
    protected void protectedMethod() {
        System.out.println("Protected method");
    }
}

// File: package2/Child.java
package package2;
import package1.Parent;

public class Child extends Parent {
    public void test() {
        System.out.println(protectedVar);  // OK - subclass
        protectedMethod();                 // OK - subclass
        
        Parent p = new Parent();
        // System.out.println(p.protectedVar);  // COMPILE ERROR - not through reference
        // p.protectedMethod();                 // COMPILE ERROR
    }
}
```

#### 4. public
- Access from anywhere

```java
// File: com/utility/Tools.java
package com.utility;

public class Tools {
    public static final double PI = 3.14159;
    
    public static int add(int a, int b) {
        return a + b;
    }
}

// File: Main.java (any package)
import com.utility.Tools;

public class Main {
    public static void main(String[] args) {
        System.out.println(Tools.PI);          // OK
        System.out.println(Tools.add(5, 10));  // OK
    }
}
```

### Access Modifiers Summary Table:

| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| private | ✅ | ❌ | ❌ | ❌ |
| default | ✅ | ✅ | ❌ | ❌ |
| protected | ✅ | ✅ | ✅ | ❌ |
| public | ✅ | ✅ | ✅ | ✅ |

### Real-world Example:

```java
// File: banking/Account.java
package banking;

public abstract class Account {
    // Private - only this class
    private String accountNumber;
    private String accountHolder;
    
    // Protected - subclasses can access
    protected double balance;
    
    // Public - anyone can call
    public Account(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }
    
    // Public getters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    // Protected setter - only subclasses can modify
    protected void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }
    
    // Public methods
    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    
    public double getBalance() {
        return balance;
    }
}

// File: banking/SavingsAccount.java
package banking;

public class SavingsAccount extends Account {
    private double interestRate;
    
    public SavingsAccount(String accNum, String holder, double balance, double rate) {
        super(accNum, holder, balance);
        this.interestRate = rate;
    }
    
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }
    }
    
    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        }
    }
    
    public void applyInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest applied: " + interest);
    }
    
    // Can access protected members
    public void updateHolder(String newHolder) {
        setAccountHolder(newHolder);  // Protected method
        // accountNumber = "NEW";     // COMPILE ERROR - private
    }
}
```

### Interview Questions:

**Q: What are access modifiers?**
- Keywords that control visibility of classes, methods, variables

**Q: What is default access?**
- Package-private, accessible only within same package

**Q: Can protected be accessed outside package?**
- Only by subclasses, not by non-subclass

**Q: Can we make class private/protected?**
- Top-level class: only public or default
- Inner class: can be private/protected

**Q: Why use packages?**
- Organization, naming conflict avoidance, access control

**Q: Can we have same class name in different packages?**
- Yes, fully qualified name distinguishes them

---

## 13. Polymorphism

### Definition:
- **One interface, multiple implementations**
- **"Poly" = many, "morph" = forms**
- Ability of object to take many forms

### Types:

#### 1. Compile-time Polymorphism (Static/Early Binding)
- Achieved by **Method Overloading**
- Resolved at compile time

```java
class Calculator {
    // Method overloading examples
    
    // 1. Different number of parameters
    int add(int a, int b) {
        return a + b;
    }
    
    int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // 2. Different types of parameters
    double add(double a, double b) {
        return a + b;
    }
    
    // 3. Different order of parameters
    void display(int id, String name) {
        System.out.println("ID: " + id + ", Name: " + name);
    }
    
    void display(String name, int id) {
        System.out.println("Name: " + name + ", ID: " + id);
    }
    
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        // Compile decides which method to call
        System.out.println(calc.add(5, 10));        // Calls int add(int, int)
        System.out.println(calc.add(5.5, 10.5));    // Calls double add(double, double)
        System.out.println(calc.add(1, 2, 3));      // Calls int add(int, int, int)
        
        calc.display(101, "John");    // Calls display(int, String)
        calc.display("Jane", 102);    // Calls display(String, int)
    }
}
```

#### 2. Runtime Polymorphism (Dynamic/Late Binding)
- Achieved by **Method Overriding**
- Resolved at runtime

```java
// Base class
class Animal {
    public void makeSound() {
        System.out.println("Animal makes sound");
    }
    
    public void eat() {
        System.out.println("Animal eats");
    }
}

// Derived classes
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
    
    public void fetch() {
        System.out.println("Dog fetches ball");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
    
    public void scratch() {
        System.out.println("Cat scratches");
    }
}

public class TestPolymorphism {
    public static void main(String[] args) {
        // Runtime polymorphism example
        
        Animal myAnimal;  // Reference of Animal type
        
        // Animal reference, Dog object
        myAnimal = new Dog();
        myAnimal.makeSound();  // Output: Dog barks (runtime decision)
        myAnimal.eat();        // Output: Animal eats
        
        // Animal reference, Cat object
        myAnimal = new Cat();
        myAnimal.makeSound();  // Output: Cat meows (runtime decision)
        myAnimal.eat();        // Output: Animal eats
        
        // Type casting example
        Animal animal = new Dog();
        
        // Downcasting - explicit cast needed
        Dog dog = (Dog) animal;
        dog.makeSound();  // Dog barks
        dog.fetch();      // Dog fetches ball
        
        // Invalid downcasting (Runtime ClassCastException)
        // Animal a2 = new Animal();
        // Dog d2 = (Dog) a2;  // ERROR at runtime
        
        // Safe casting using instanceof
        if (animal instanceof Dog) {
            Dog safeDog = (Dog) animal;
            safeDog.fetch();
        }
    }
}
```

### Polymorphism with Variables:

```java
class Parent {
    int x = 100;
    
    void display() {
        System.out.println("Parent display: " + x);
    }
}

class Child extends Parent {
    int x = 200;  // Hides parent x
    
    @Override
    void display() {
        System.out.println("Child display: " + x);
    }
    
    void show() {
        System.out.println("Child show: " + x);
    }
}

public class VariablePolymorphism {
    public static void main(String[] args) {
        Parent p = new Child();  // Upcasting
        
        // Variable access - based on reference type (Parent)
        System.out.println(p.x);  // 100 (NOT 200)
        
        // Method access - based on object type (Child)
        p.display();  // Child display: 200
        
        // Cannot access child-specific methods
        // p.show();  // COMPILE ERROR
        
        // Access child variable through casting
        Child c = (Child) p;
        System.out.println(c.x);  // 200
        c.show();  // OK
    }
}
```

### Real-world Example:

```java
// Payment System using Polymorphism
interface Payment {
    void pay(double amount);
}

class CreditCardPayment implements Payment {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card: " 
            + cardNumber.substring(cardNumber.length() - 4));
        // Process credit card payment
    }
}

class PayPalPayment implements Payment {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal: " + email);
        // Process PayPal payment
    }
}

class UpiPayment implements Payment {
    private String upiId;
    
    public UpiPayment(String upiId) {
        this.upiId = upiId;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using UPI: " + upiId);
        // Process UPI payment
    }
}

class ShoppingCart {
    private List<Item> items = new ArrayList<>();
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public double calculateTotal() {
        return items.stream().mapToDouble(Item::getPrice).sum();
    }
    
    public void checkout(Payment paymentMethod) {
        double total = calculateTotal();
        System.out.println("Total amount: $" + total);
        paymentMethod.pay(total);
        System.out.println("Payment successful!");
    }
}

class Item {
    private String name;
    private double price;
    
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }
}

public class ECommerce {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new Item("Laptop", 999.99));
        cart.addItem(new Item("Mouse", 29.99));
        
        // Polymorphism in action
        Payment payment1 = new CreditCardPayment("1234567812345678");
        cart.checkout(payment1);
        
        Payment payment2 = new PayPalPayment("user@example.com");
        cart.checkout(payment2);
        
        Payment payment3 = new UpiPayment("user@bank");
        cart.checkout(payment3);
    }
}
```

### Interview Questions:

**Q: What is polymorphism?**
- Ability of object to take multiple forms

**Q: Types of polymorphism?**
- Compile-time (overloading), Runtime (overriding)

**Q: Can we achieve polymorphism without inheritance?**
- Yes, through interfaces (interface polymorphism)

**Q: Difference between overloading and overriding?**
- Overloading: same class, different parameters, compile-time
- Overriding: parent-child, same signature, runtime

**Q: Can we override static method?**
- No, it's method hiding (compile-time)

**Q: Can we override private method?**
- No, private methods are not visible to child classes

**Q: What is upcasting and downcasting?**
- Upcasting: Child → Parent (implicit)
- Downcasting: Parent → Child (explicit, needs type cast)

---

## 14. Abstraction & abstract Keyword

### Definition:
- **Hiding implementation details**, showing only essential features
- Focus on **what** object does, not **how** it does
- Achieved through abstract classes and interfaces

### Abstract Class:

#### Features:
1. Cannot be instantiated (cannot create object)
2. Can have both abstract and concrete methods
3. Can have constructors, static methods, final methods
4. Used when there's an "IS-A" relationship

```java
// Abstract class example
abstract class Shape {
    // Abstract method (no implementation)
    abstract double calculateArea();
    abstract double calculatePerimeter();
    
    // Concrete method (has implementation)
    void display() {
        System.out.println("This is a shape");
    }
    
    // Static method
    static void info() {
        System.out.println("Shape abstract class");
    }
    
    // Constructor (called when child object is created)
    Shape() {
        System.out.println("Shape constructor called");
    }
    
    // Final method
    final void cannotOverride() {
        System.out.println("This cannot be overridden");
    }
}

// Concrete class extending abstract class
class Circle extends Shape {
    private double radius;
    
    Circle(double radius) {
        this.radius = radius;
    }
    
    // Must implement all abstract methods
    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    
    // Additional method specific to Circle
    void circleMethod() {
        System.out.println("Circle specific method");
    }
}

class Rectangle extends Shape {
    private double length;
    private double width;
    
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    double calculateArea() {
        return length * width;
    }
    
    @Override
    double calculatePerimeter() {
        return 2 * (length + width);
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        // Cannot create abstract class object
        // Shape s = new Shape();  // COMPILE ERROR
        
        // Can create references
        Shape shape;
        
        // Polymorphism with abstraction
        shape = new Circle(5.0);
        System.out.println("Circle Area: " + shape.calculateArea());
        System.out.println("Circle Perimeter: " + shape.calculatePerimeter());
        shape.display();
        shape.cannotOverride();
        // shape.circleMethod();  // COMPILE ERROR
        
        shape = new Rectangle(4.0, 6.0);
        System.out.println("\nRectangle Area: " + shape.calculateArea());
        System.out.println("Rectangle Perimeter: " + shape.calculatePerimeter());
        
        // Call static method
        Shape.info();
        
        // Access concrete class methods
        Circle c = new Circle(7.0);
        c.circleMethod();  // OK
    }
}
```

### Abstract Class with Partial Implementation:

```java
abstract class Database {
    // Abstract methods (to be implemented by child)
    abstract void connect();
    abstract void disconnect();
    abstract void executeQuery(String query);
    
    // Concrete method (common implementation)
    public void log(String message) {
        System.out.println("LOG: " + message);
    }
    
    // Template method pattern
    public final void process(String query) {
        connect();
        log("Executing query: " + query);
        executeQuery(query);
        log("Query executed");
        disconnect();
    }
}

class MySQLDatabase extends Database {
    @Override
    void connect() {
        System.out.println("Connecting to MySQL...");
    }
    
    @Override
    void disconnect() {
        System.out.println("Disconnecting from MySQL...");
    }
    
    @Override
    void executeQuery(String query) {
        System.out.println("Executing MySQL query: " + query);
    }
}

class OracleDatabase extends Database {
    @Override
    void connect() {
        System.out.println("Connecting to Oracle...");
    }
    
    @Override
    void disconnect() {
        System.out.println("Disconnecting from Oracle...");
    }
    
    @Override
    void executeQuery(String query) {
        System.out.println("Executing Oracle query: " + query);
    }
}

public class DatabaseDemo {
    public static void main(String[] args) {
        Database db1 = new MySQLDatabase();
        db1.process("SELECT * FROM users");
        
        System.out.println("\n---\n");
        
        Database db2 = new OracleDatabase();
        db2.process("SELECT * FROM employees");
    }
}
```

### When to Use Abstract Class vs Interface:

```java
// Use Abstract Class when:
// 1. You want to share code among related classes
// 2. You have common state or behavior
// 3. You want to declare non-static, non-final fields

abstract class Vehicle {
    // Common state
    protected String brand;
    protected int year;
    
    // Common behavior with implementation
    public void start() {
        System.out.println("Vehicle starting...");
    }
    
    // Abstract methods for specific behavior
    abstract void move();
    abstract void stop();
}

class Car extends Vehicle {
    private int doors;
    
    @Override
    void move() {
        System.out.println("Car is moving on road");
    }
    
    @Override
    void stop() {
        System.out.println("Car stopped using brakes");
    }
}

class Boat extends Vehicle {
    private boolean hasSail;
    
    @Override
    void move() {
        System.out.println("Boat is sailing on water");
    }
    
    @Override
    void stop() {
        System.out.println("Boat stopped using anchor");
    }
}
```

### Abstract Class Rules:

```java
abstract class Rules {
    // Rule 1: Can have both abstract and concrete methods
    abstract void method1();  // Abstract
    void method2() { }        // Concrete
    
    // Rule 2: Can have constructor
    Rules() {
        System.out.println("Constructor");
    }
    
    // Rule 3: Can have static methods
    static void staticMethod() { }
    
    // Rule 4: Can have final methods
    final void finalMethod() { }
    
    // Rule 5: Can have main method
    public static void main(String[] args) {
        System.out.println("Abstract class can have main");
    }
    
    // Rule 6: Can extend another class
    // abstract class Child extends Parent { }
    
    // Rule 7: Can implement interfaces
    // abstract class MyClass implements MyInterface { }
}

// Rule 8: Can't create object
// Rules r = new Rules();  // COMPILE ERROR

// Rule 9: If any method is abstract, class must be abstract
class Invalid {
    // abstract void method();  // COMPILE ERROR
}

abstract class Valid {
    abstract void method();  // OK
}
```

### Interview Questions:

**Q: What is abstraction?**
- Hiding complex implementation, showing only essential features

**Q: How to achieve abstraction in Java?**
- Abstract classes and interfaces

**Q: Can abstract class have constructor?**
- Yes, called when child object is created

**Q: Can we create object of abstract class?**
- No

**Q: Can abstract class have main method?**
- Yes

**Q: Can abstract class be final?**
- No, it must be extended

**Q: When to use abstract class vs interface?**
- Abstract class: Share code, common state, IS-A relationship
- Interface: Define contract, multiple inheritance, HAS-A capability

**Q: Can abstract class implement interface?**
- Yes, without implementing methods

---

## 15. final Keyword

### Definition:
- Keyword used to apply **restrictions** on classes, methods, and variables
- Meaning: **cannot be changed/modified/extended**

### 1. final Variable (Constant)

#### a) final Local Variable
```java
void method() {
    final int MAX_VALUE = 100;  // Must initialize
    // MAX_VALUE = 200;  // COMPILE ERROR - cannot change
    final int x;  // Declaration without initialization
    x = 10;       // Can initialize once
    // x = 20;    // COMPILE ERROR - cannot change
}
```

#### b) final Instance Variable
```java
class Student {
    // Must initialize in constructor or declaration
    final int ROLL_NO;  
    final String NAME = "Default";  // Initialized at declaration
    
    Student(int rollNo) {
        this.ROLL_NO = rollNo;  // Initialize in constructor
        // this.ROLL_NO = 200;  // COMPILE ERROR - already initialized
    }
    
    void method() {
        // NAME = "New";  // COMPILE ERROR - cannot change
    }
}
```

#### c) final Static Variable (Constant)
```java
class Constants {
    // Static final - true constants
    public static final double PI = 3.14159;
    public static final int MAX_USERS = 1000;
    public static final String DATABASE_URL;
    
    // Static block for initialization
    static {
        DATABASE_URL = "jdbc:mysql://localhost:3306/db";
    }
    
    public static void main(String[] args) {
        System.out.println(PI);  // 3.14159
        // PI = 3.14;  // COMPILE ERROR
    }
}
```

### 2. final Method
- Cannot be overridden by child class

```java
class Parent {
    // Normal method - can be overridden
    void normalMethod() {
        System.out.println("Parent normal method");
    }
    
    // Final method - cannot be overridden
    final void finalMethod() {
        System.out.println("Parent final method");
    }
    
    // Static method can also be final
    static final void staticFinalMethod() {
        System.out.println("Static final method");
    }
}

class Child extends Parent {
    @Override
    void normalMethod() {
        System.out.println("Child overrides normal method");
    }
    
    // COMPILE ERROR: Cannot override final method
    // @Override
    // void finalMethod() { }
}

public class FinalMethodDemo {
    public static void main(String[] args) {
        Child c = new Child();
        c.normalMethod();  // Child overrides normal method
        c.finalMethod();   // Parent final method
        Parent.staticFinalMethod();  // Static final method
    }
}
```

### 3. final Class
- Cannot be extended (inherited)

```java
// Final class - cannot be extended
final class FinalClass {
    void display() {
        System.out.println("Final class method");
    }
}

// COMPILE ERROR: Cannot extend final class
// class ChildClass extends FinalClass { }

// Example from Java API: String class
// public final class String { ... }
// No one can extend String class

// But final class can extend other classes
class NormalClass { }
final class MyFinalClass extends NormalClass {
    // OK
}
```

### 4. final Parameter
- Parameter value cannot be changed inside method

```java
class FinalParameter {
    void process(final int x) {
        System.out.println("Received: " + x);
        // x = 100;  // COMPILE ERROR - cannot modify
    }
    
    void modifyObject(final StringBuilder sb) {
        sb.append(" World");  // OK - object content can be modified
        // sb = new StringBuilder();  // COMPILE ERROR - reference cannot change
    }
    
    public static void main(String[] args) {
        FinalParameter fp = new FinalParameter();
        fp.process(10);
        
        StringBuilder sb = new StringBuilder("Hello");
        fp.modifyObject(sb);
        System.out.println(sb);  // Hello World
    }
}
```

### Real-world Examples:

#### Example 1: Immutable Class using final

```java
// Immutable class - state cannot be changed after creation
public final class ImmutablePerson {
    // All fields are private and final
    private final String name;
    private final int age;
    private final List<String> hobbies;
    
    // Initialize all fields in constructor
    public ImmutablePerson(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        // Defensive copy for mutable objects
        this.hobbies = new ArrayList<>(hobbies);
    }
    
    // Only getters, no setters
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public List<String> getHobbies() {
        // Return defensive copy
        return new ArrayList<>(hobbies);
    }
    
    // No methods that modify state
}

// Usage
public class Main {
    public static void main(String[] args) {
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Reading");
        hobbies.add("Swimming");
        
        ImmutablePerson person = new ImmutablePerson("John", 30, hobbies);
        
        System.out.println(person.getName());  // John
        
        // Try to modify
        hobbies.add("Gaming");  // Original list modified
        System.out.println(person.getHobbies());  // Still [Reading, Swimming]
        
        List<String> personHobbies = person.getHobbies();
        personHobbies.add("Gaming");  // Copy modified, not original
        System.out.println(person.getHobbies());  // Still [Reading, Swimming]
    }
}
```

#### Example 2: Configuration Constants

```java
public class AppConfig {
    // Application-wide constants
    public static final String APP_NAME = "MyApplication";
    public static final String VERSION = "1.0.0";
    public static final int MAX_LOGIN_ATTEMPTS = 3;
    public static final long SESSION_TIMEOUT = 30 * 60 * 1000; // 30 minutes
    
    // Database constants
    public static final class Database {
        public static final String URL = "jdbc:mysql://localhost:3306/appdb";
        public static final String USERNAME = "admin";
        public static final String PASSWORD = "secret";
        public static final int POOL_SIZE = 10;
    }
    
    // API constants
    public static final class API {
        public static final String BASE_URL = "https://api.example.com";
        public static final int TIMEOUT = 5000;
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        System.out.println("App: " + AppConfig.APP_NAME);
        System.out.println("DB URL: " + AppConfig.Database.URL);
        System.out.println("API URL: " + AppConfig.API.BASE_URL);
    }
}
```

#### Example 3: Final in Inheritance Chain

```java
class GrandParent {
    final void familySecret() {
        System.out.println("Family secret method");
    }
}

class Parent extends GrandParent {
    // Can't override final method
    // void familySecret() { }  // COMPILE ERROR
    
    final void parentMethod() {
        System.out.println("Parent final method");
    }
}

final class Child extends Parent {
    // Can't override parent's final method
    // void parentMethod() { }  // COMPILE ERROR
    
    void childMethod() {
        System.out.println("Child method");
    }
}

// Can't extend final class
// class GrandChild extends Child { }  // COMPILE ERROR
```

### Interview Questions:

**Q: What is final keyword?**
- Used to make classes, methods, variables unchangeable

**Q: Can final variable be initialized later?**
- Yes, but only once

**Q: Can final method be overloaded?**
- Yes, overloaded ≠ overridden

**Q: Can final class have subclasses?**
- No, cannot be extended

**Q: Can abstract class be final?**
- No, contradictory (abstract must be extended, final cannot be)

**Q: Can constructor be final?**
- No, constructors cannot be final

**Q: What is blank final variable?**
- final variable declared but not initialized

**Q: What is effect of final on reference variables?**
- Reference cannot point to another object, but object can be modified

**Q: Why String class is final?**
- Security, immutability, performance (caching), thread safety

---

## Summary Table - OOPs Concepts

| Concept | Purpose | Example | Key Points |
|---------|---------|---------|------------|
| **Class** | Blueprint for objects | `class Car { }` | Logical entity, no memory |
| **Object** | Instance of class | `Car myCar = new Car();` | Physical entity, has memory |
| **Encapsulation** | Data hiding | Private fields + public methods | Security, control, flexibility |
| **Inheritance** | Code reuse | `class BMW extends Car` | IS-A, extends keyword |
| **Polymorphism** | Multiple forms | Method overloading/overriding | Compile-time & runtime |
| **Abstraction** | Hide complexity | Abstract classes/interfaces | Show what, not how |
| **final** | Restrict changes | `final int MAX = 100;` | Constants, security |

## Common Interview Questions:

### Q: Difference between abstract class and interface?
**Abstract Class:**
- Can have abstract and concrete methods
- Can have constructors
- Can have instance variables
- Single inheritance
- Use for IS-A relationship

**Interface (Java 8+):**
- All methods public abstract (before Java 8)
- Can have default and static methods
- No constructors
- Only static final variables
- Multiple inheritance
- Use for HAS-A capability

### Q: Can we override static method?
- No, static methods are hidden, not overridden

### Q: Can we override private method?
- No, private methods are not visible to child classes

### Q: Can we override main method?
- No, but we can overload it

### Q: Can constructor be inherited?
- No

### Q: Can we make constructor final?
- No

### Q: Can abstract class have constructor?
- Yes, called when child object is created

### Q: Can we instantiate abstract class?
- No, but can create anonymous inner class

### Q: Can we have multiple inheritance in Java?
- Yes, through interfaces
- No, through classes (due to diamond problem)

### Q: What is diamond problem?
- Ambiguity when class inherits from two classes with same method

### Q: Can we overload main method?
- Yes

### Q: Can we declare class as static?
- Only nested classes can be static

### Q: Can we execute program without main method?
- Before Java 7: Yes, using static block
- Java 7+: No (except some special cases)

### Q: Why Java doesn't support pointers?
- Security, simplicity, garbage collection

### Q: What is singleton class?
- Class that allows only one instance
```java
class Singleton {
    private static Singleton instance;
    private Singleton() { }
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

This comprehensive guide covers all OOPs concepts with implementation details and interview-ready explanations. Each section includes practical examples and common interview questions to help you prepare effectively.
Table of Contents for Missing Topics
Interfaces

Association, Aggregation, Composition

Method Overriding

super Keyword

Object Class Methods

Coupling and Cohesion

SOLID Principles

