# Java Fundamentals - Detailed Notes

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

### Detailed Explanation
Java is a high-level, object-oriented programming language developed by Sun Microsystems (now owned by Oracle) in 1995. It was designed with the principle of "Write Once, Run Anywhere" (WORA), meaning compiled Java code can run on all platforms that support Java without recompilation.

**Key Features:**
- **Platform Independent**: Runs on JVM (Java Virtual Machine)
- **Object-Oriented**: Follows OOP principles (encapsulation, inheritance, polymorphism, abstraction)
- **Secure**: Built-in security features (no pointers, bytecode verification)
- **Robust**: Strong memory management, exception handling, type checking
- **Multithreaded**: Supports concurrent programming
- **Distributed**: Network-centric design

### Code Implementation
```java
// Simple Java Program
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Interview Questions
1. **Q: What is Java and why is it platform independent?**
    - A: Java is an object-oriented programming language that compiles to bytecode which runs on JVM. The JVM is platform-specific, but the bytecode is platform-independent.

2. **Q: Name 5 features of Java.**
    - A: Platform independent, object-oriented, secure, robust, multithreaded.

### Real-World Applications
- Android app development
- Enterprise applications (banking systems)
- Web applications (Spring framework)
- Big data technologies (Hadoop)
- Scientific applications

---

## How Java Made Portable (WORA)

### Detailed Explanation
**WORA (Write Once, Run Anywhere)** is achieved through:
1. **Java Source Code** (.java files)
2. **Java Compiler** (javac) converts to **Bytecode** (.class files)
3. **JVM (Java Virtual Machine)** interprets bytecode to machine code
4. **JRE (Java Runtime Environment)** provides libraries and JVM

**Architecture:**
```
.java → [Compiler] → .bytecode → [JVM] → Machine Code → [OS/Hardware]
```

### Comparison Table
| Component | Purpose | Platform Dependency |
|-----------|---------|-------------------|
| JDK | Development Kit | Platform dependent |
| JRE | Runtime Environment | Platform dependent |
| JVM | Executes bytecode | Platform dependent |
| Bytecode | Intermediate code | Platform independent |

### Interview Questions
**Q: Explain JVM, JRE, and JDK.**
- **JVM**: Virtual machine that executes Java bytecode
- **JRE**: Runtime environment (JVM + libraries)
- **JDK**: Development kit (JRE + development tools)

**Q: How does JVM achieve platform independence?**
- A: JVM provides a consistent runtime environment across platforms. Each OS has its own JVM implementation that converts platform-independent bytecode to platform-specific machine code.

---

## Main Method

### Detailed Explanation
The `main` method is the entry point for any Java application. JVM looks for this method to start program execution.

**Syntax:**
```java
public static void main(String[] args)
```

**Components:**
- `public`: Accessible from anywhere
- `static`: Can be called without creating an object
- `void`: Returns nothing
- `String[] args`: Command-line arguments

### Code Implementation
```java
public class MainMethodDemo {
    public static void main(String[] args) {
        System.out.println("Number of arguments: " + args.length);
        for(int i = 0; i < args.length; i++) {
            System.out.println("Argument " + i + ": " + args[i]);
        }
    }
}
```

### Interview Questions
**Q: Why is main method static in Java?**
- A: So JVM can call it without creating an object of the class.

**Q: Can we overload main method?**
- A: Yes, but JVM will only call the standard `public static void main(String[] args)`.

**Q: What happens if we don't write main method?**
- A: Code compiles but throws `RuntimeException: NoSuchMethodError: main`.

---

## Statically vs Dynamically Typed Languages

### Detailed Explanation

**Statically Typed Languages:**
- Type checking at compile-time
- Variables must be declared with type
- Examples: Java, C, C++, C#

**Dynamically Typed Languages:**
- Type checking at runtime
- Variables don't need explicit type declaration
- Examples: Python, JavaScript, Ruby

### Comparison Table
| Aspect | Static Typing | Dynamic Typing |
|--------|--------------|----------------|
| Type Checking | Compile-time | Runtime |
| Performance | Faster | Slower |
| Error Detection | Early (compile-time) | Runtime errors |
| Flexibility | Less flexible | More flexible |
| Examples | Java, C++ | Python, JavaScript |

### Code Examples
```java
// Java (Statically typed)
int number = 10;  // Type must be declared
// number = "hello";  // Compile error

// Python (Dynamically typed - for comparison)
# number = 10  # No type declaration
# number = "hello"  # Allowed
```

### Interview Questions
**Q: What are the advantages of static typing?**
- A: Early error detection, better performance, self-documenting code.

**Q: Is Java purely statically typed?**
- A: Mostly, but with generics and type erasure, some type information is lost at runtime.

---

## Variables and Data Types

### Detailed Explanation
**Variables** are containers for storing data values. In Java, each variable must be declared with a specific data type.

**Data Types Classification:**
1. **Primitive Data Types** (8 types)
2. **Non-Primitive/Reference Types** (Objects, Arrays, Strings)

### Primitive Data Types Table
| Type | Size | Range | Default | Example |
|------|------|-------|---------|---------|
| byte | 1 byte | -128 to 127 | 0 | `byte b = 100;` |
| short | 2 bytes | -32,768 to 32,767 | 0 | `short s = 1000;` |
| int | 4 bytes | -2³¹ to 2³¹-1 | 0 | `int i = 100000;` |
| long | 8 bytes | -2⁶³ to 2⁶³-1 | 0L | `long l = 100000L;` |
| float | 4 bytes | ±3.4E-38 to ±3.4E+38 | 0.0f | `float f = 3.14f;` |
| double | 8 bytes | ±1.7E-308 to ±1.7E+308 | 0.0d | `double d = 3.14;` |
| char | 2 bytes | 0 to 65,535 | '\u0000' | `char c = 'A';` |
| boolean | 1 bit | true/false | false | `boolean flag = true;` |

### Code Implementation
```java
public class DataTypesDemo {
    public static void main(String[] args) {
        // Primitive types
        byte b = 127;
        short s = 32000;
        int i = 1000000;
        long l = 10000000000L;
        float f = 3.14f;
        double d = 3.14159265359;
        char c = 'A';
        boolean bool = true;
        
        // Reference types
        String str = "Hello Java";
        int[] array = {1, 2, 3};
        
        System.out.println("Byte: " + b);
        System.out.println("String: " + str);
    }
}
```

### Interview Questions
**Q: What's the default value of local variables?**
- A: Local variables don't have default values; they must be initialized before use.

**Q: Difference between float and double?**
- A: float is 32-bit (single precision), double is 64-bit (double precision). Use `f` suffix for float, `d` (optional) for double.

**Q: Why is char 2 bytes in Java?**
- A: Java uses Unicode (UTF-16) which supports international characters.

---

## Type Casting and Truncation

### Detailed Explanation
**Type Casting** converts one data type to another.

**Two Types:**
1. **Widening (Implicit)**: Smaller to larger type (automatic)
2. **Narrowing (Explicit)**: Larger to smaller type (manual)

**Truncation**: Loss of data when converting from larger to smaller types (especially floating-point to integer).

### Code Implementation
```java
public class TypeCastingDemo {
    public static void main(String[] args) {
        // Widening Casting (Implicit)
        int intValue = 100;
        long longValue = intValue;  // Automatic
        
        // Narrowing Casting (Explicit)
        double doubleValue = 9.78;
        int intFromDouble = (int) doubleValue;  // Manual
        System.out.println(intFromDouble);  // Output: 9 (truncation)
        
        // Truncation examples
        float f = 3.99f;
        int i = (int) f;  // i = 3 (decimal part lost)
        
        // Data loss in large conversions
        int largeInt = 1000;
        byte smallByte = (byte) largeInt;  // May produce unexpected results
        System.out.println(smallByte);  // Output: -24 (due to overflow)
    }
}
```

### Interview Questions
**Q: What is type promotion in expressions?**
- A: Smaller types are automatically promoted to larger types in expressions:
    - byte, short, char → int
    - If one operand is long, entire expression promoted to long
    - If one operand is float, entire expression promoted to float
    - If one operand is double, entire expression promoted to double

**Q: What happens when we cast float/double to int?**
- A: Decimal part is truncated (not rounded).

**Q: Can boolean be cast to int?**
- A: No, boolean is not numeric in Java.

---

## Identifiers and Naming Conventions

### Detailed Explanation
**Identifiers** are names given to variables, methods, classes, etc.

**Rules for Identifiers:**
1. Must start with letter (A-Z, a-z), currency ($), or underscore (_)
2. Subsequent characters can be letters, digits, $, or _
3. Cannot be Java keywords
4. Case-sensitive

**Naming Conventions:**
- **Class**: PascalCase (`MyClass`, `EmployeeDetails`)
- **Method/Variable**: camelCase (`calculateTotal()`, `studentName`)
- **Constant**: UPPER_SNAKE_CASE (`MAX_SIZE`, `PI`)
- **Package**: lowercase with dots (`com.example.project`)

### Code Examples
```java
public class NamingConventions {
    // Constants
    public static final int MAX_COUNT = 100;
    public static final String DEFAULT_NAME = "Unknown";
    
    // Variables
    private int studentAge;
    private String employeeName;
    
    // Methods
    public void calculateSalary() {
        // method body
    }
    
    public boolean isValidUser() {
        return true;
    }
}
```

### Common Mistakes
```java
// Invalid identifiers
// int 2ndValue;      // Starts with digit
// int my-value;       // Contains hyphen
// int class;          // Java keyword
// int my value;       // Contains space
```

### Interview Questions
**Q: Is `_` (underscore) a valid identifier?**
- A: Yes, but from Java 9, single underscore `_` is a keyword and cannot be used.

**Q: Difference between `name` and `Name`?**
- A: They are different identifiers (case-sensitive).

---

## Operators

### Detailed Explanation
Operators are symbols that perform operations on variables and values.

### Operator Categories Table
| Category | Operators | Description |
|----------|-----------|-------------|
| Arithmetic | `+ - * / %` | Mathematical operations |
| Assignment | `= += -= *= /= %=` | Assign values |
| Comparison | `== != > < >= <=` | Compare values |
| Logical | `&& \|\| !` | Boolean operations |
| Bitwise | `& \| ^ ~ << >> >>>` | Bit-level operations |
| Ternary | `? :` | Conditional operator |
| Instanceof | `instanceof` | Type comparison |

### Code Implementation
```java
public class OperatorsDemo {
    public static void main(String[] args) {
        // Arithmetic Operators
        int a = 10, b = 3;
        System.out.println("a + b = " + (a + b));  // 13
        System.out.println("a - b = " + (a - b));  // 7
        System.out.println("a * b = " + (a * b));  // 30
        System.out.println("a / b = " + (a / b));  // 3 (integer division)
        System.out.println("a % b = " + (a % b));  // 1
        
        // Assignment Operators
        int x = 10;
        x += 5;  // x = x + 5
        System.out.println("x = " + x);  // 15
        
        // Comparison Operators
        System.out.println("a == b: " + (a == b));  // false
        System.out.println("a != b: " + (a != b));  // true
        System.out.println("a > b: " + (a > b));    // true
        
        // Logical Operators
        boolean p = true, q = false;
        System.out.println("p && q: " + (p && q));  // false
        System.out.println("p || q: " + (p || q));  // true
        System.out.println("!p: " + (!p));          // false
        
        // Ternary Operator
        int max = (a > b) ? a : b;
        System.out.println("Max: " + max);  // 10
        
        // Bitwise Operators
        int m = 5, n = 3;  // 5=0101, 3=0011
        System.out.println("m & n: " + (m & n));  // 1 (0001)
        System.out.println("m | n: " + (m | n));  // 7 (0111)
        System.out.println("m ^ n: " + (m ^ n));  // 6 (0110)
    }
}
```

### Interview Questions
**Q: Difference between `&` and `&&`?**
- A: `&` is bitwise AND, `&&` is logical AND. `&&` short-circuits (if left is false, right not evaluated).

**Q: What is the result of `10 / 3` and `10.0 / 3`?**
- A: `10 / 3` = 3 (integer division), `10.0 / 3` = 3.333... (floating division).

**Q: What does `>>>` operator do?**
- A: Unsigned right shift. Fills leftmost bits with 0 regardless of sign.

---

## Increment and Decrement Operators

### Detailed Explanation
- **Post-increment** (`i++`): Use current value, then increment
- **Pre-increment** (`++i`): Increment first, then use value
- **Post-decrement** (`i--`): Use current value, then decrement
- **Pre-decrement** (`--i`): Decrement first, then use value

### Code Implementation
```java
public class IncrementDecrementDemo {
    public static void main(String[] args) {
        // Post-increment
        int a = 5;
        int b = a++;  // b = 5, a = 6
        System.out.println("a = " + a + ", b = " + b);
        
        // Pre-increment
        int c = 5;
        int d = ++c;  // d = 6, c = 6
        System.out.println("c = " + c + ", d = " + d);
        
        // Complex example
        int x = 5;
        int y = x++ + ++x;  // 5 + 7 = 12
        System.out.println("x = " + x + ", y = " + y);
        
        // Decrement examples
        int p = 10;
        int q = p--;  // q = 10, p = 9
        
        int r = 10;
        int s = --r;  // s = 9, r = 9
    }
}
```

### Common Interview Problems
```java
// Tricky interview questions
int i = 1;
i = i++;  // What's the value of i?
System.out.println(i);  // Answer: 1

int j = 1;
j = ++j;  // What's the value of j?
System.out.println(j);  // Answer: 2
```

### Interview Questions
**Q: Explain `i++` vs `++i` with example.**
- A: `i++` returns value then increments, `++i` increments then returns value.

**Q: What is the output of `int x = 5; x = x++ + ++x;`?**
- A: 12 (5 + 7)

---

## Conditional Statements

### Detailed Explanation
Conditional statements control program flow based on conditions.

### Types of Conditional Statements
1. **if statement**
2. **if-else statement**
3. **if-else-if ladder**
4. **switch statement**
5. **Ternary operator**

### Code Implementation
```java
import java.util.Scanner;

public class ConditionalStatements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 1. if statement
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        if (age >= 18) {
            System.out.println("You are eligible to vote.");
        }
        
        // 2. if-else statement
        System.out.print("Enter number: ");
        int num = scanner.nextInt();
        if (num % 2 == 0) {
            System.out.println("Even number");
        } else {
            System.out.println("Odd number");
        }
        
        // 3. if-else-if ladder
        System.out.print("Enter marks: ");
        int marks = scanner.nextInt();
        char grade;
        
        if (marks >= 90) {
            grade = 'A';
        } else if (marks >= 80) {
            grade = 'B';
        } else if (marks >= 70) {
            grade = 'C';
        } else if (marks >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        System.out.println("Grade: " + grade);
        
        // 4. switch statement (Java 14+ enhanced)
        System.out.print("Enter day number (1-7): ");
        int day = scanner.nextInt();
        
        switch (day) {
            case 1 -> System.out.println("Monday");
            case 2 -> System.out.println("Tuesday");
            case 3 -> System.out.println("Wednesday");
            case 4 -> System.out.println("Thursday");
            case 5 -> System.out.println("Friday");
            case 6 -> System.out.println("Saturday");
            case 7 -> System.out.println("Sunday");
            default -> System.out.println("Invalid day");
        }
        
        // 5. Ternary operator
        int a = 10, b = 20;
        String result = (a > b) ? "a is greater" : "b is greater";
        System.out.println(result);
        
        scanner.close();
    }
}
```

### Comparison Table: if-else vs switch
| Aspect | if-else | switch |
|--------|---------|---------|
| Expression | Any boolean expression | Integer, string, enum |
| Multiple conditions | Supported | Limited (case values) |
| Readability | Less for many conditions | Better for many conditions |
| Performance | O(n) in worst case | O(1) with jump table |
| Default | else block | default case |

### Interview Questions
**Q: When to use switch over if-else?**
- A: When testing a single variable against multiple constant values.

**Q: Can switch work with strings?**
- A: Yes, from Java 7 onwards.

**Q: What happens if we forget break in switch?**
- A: Fall-through occurs (executes all subsequent cases).

---

## Loops

### Detailed Explanation
Loops execute a block of code repeatedly until a condition is met.

### Types of Loops
1. **for loop**: Known number of iterations
2. **while loop**: Condition checked before iteration
3. **do-while loop**: Condition checked after iteration (executes at least once)
4. **Enhanced for loop**: Iterating over arrays/collections

### Code Implementation
```java
public class LoopsDemo {
    public static void main(String[] args) {
        System.out.println("=== For Loop ===");
        // Standard for loop
        for (int i = 1; i <= 5; i++) {
            System.out.println("Iteration: " + i);
        }
        
        // Multiple variables in for loop
        for (int i = 1, j = 5; i <= 5; i++, j--) {
            System.out.println("i = " + i + ", j = " + j);
        }
        
        System.out.println("\n=== While Loop ===");
        // While loop
        int count = 1;
        while (count <= 5) {
            System.out.println("Count: " + count);
            count++;
        }
        
        System.out.println("\n=== Do-While Loop ===");
        // Do-while loop (executes at least once)
        int number = 6;
        do {
            System.out.println("Number: " + number);
            number++;
        } while (number <= 5);
        
        System.out.println("\n=== Enhanced For Loop ===");
        // Enhanced for loop (for-each)
        int[] numbers = {1, 2, 3, 4, 5};
        for (int num : numbers) {
            System.out.println("Array element: " + num);
        }
        
        System.out.println("\n=== Loop Control Statements ===");
        // break statement
        for (int i = 1; i <= 10; i++) {
            if (i == 5) {
                break;  // Exit loop
            }
            System.out.println("i = " + i);
        }
        
        // continue statement
        for (int i = 1; i <= 5; i++) {
            if (i == 3) {
                continue;  // Skip current iteration
            }
            System.out.println("i = " + i);
        }
        
        // Nested loops
        System.out.println("\n=== Nested Loops ===");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.print("(" + i + "," + j + ") ");
            }
            System.out.println();
        }
        
        // Labeled break
        System.out.println("\n=== Labeled Break ===");
        outerLoop:
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (i == 2 && j == 2) {
                    break outerLoop;  // Break outer loop
                }
                System.out.println("i=" + i + ", j=" + j);
            }
        }
    }
}
```

### Loop Comparison Table
| Loop Type | When to Use | Pros | Cons |
|-----------|-------------|------|------|
| for | Known iterations | Compact syntax | Less readable for complex conditions |
| while | Unknown iterations, condition check first | Flexible | Infinite loop risk |
| do-while | At least one execution needed | Guaranteed execution | Less commonly used |
| for-each | Array/Collection iteration | Simple syntax | No index access |

### Real-World Applications
- **for loop**: Processing arrays, mathematical computations
- **while loop**: Reading files until EOF, game loops
- **do-while**: Menu-driven programs, input validation
- **Enhanced for**: Iterating collections, array processing

### Interview Questions
**Q: Difference between while and do-while?**
- A: while checks condition before execution, do-while checks after (executes at least once).

**Q: When would you use an infinite loop?**
- A: Server listening for connections, game main loops, background services.

**Q: How to avoid infinite loops?**
- A: Ensure loop condition changes, use break statements, implement timeouts.

---

## Scanner Class (Console User Input)

### Detailed Explanation
The `Scanner` class (in `java.util` package) is used to read input from various sources (keyboard, files, strings).

### Scanner Methods Table
| Method | Description | Example |
|--------|-------------|---------|
| `next()` | Reads next token (whitespace delimited) | `scanner.next()` |
| `nextLine()` | Reads entire line | `scanner.nextLine()` |
| `nextInt()` | Reads integer | `scanner.nextInt()` |
| `nextDouble()` | Reads double | `scanner.nextDouble()` |
| `nextBoolean()` | Reads boolean | `scanner.nextBoolean()` |
| `hasNext()` | Checks if more input available | `scanner.hasNext()` |

### Code Implementation
```java
import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {
        // Create Scanner object
        Scanner scanner = new Scanner(System.in);
        
        // Reading different data types
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        
        System.out.print("Enter your height (in meters): ");
        double height = scanner.nextDouble();
        
        System.out.print("Are you a student? (true/false): ");
        boolean isStudent = scanner.nextBoolean();
        
        // Consume leftover newline
        scanner.nextLine();
        
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        
        // Display collected information
        System.out.println("\n=== User Information ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height + "m");
        System.out.println("Student: " + isStudent);
        System.out.println("Address: " + address);
        
        // Token-based reading
        System.out.print("\nEnter three words separated by spaces: ");
        String word1 = scanner.next();
        String word2 = scanner.next();
        String word3 = scanner.next();
        System.out.println("Words: " + word1 + ", " + word2 + ", " + word3);
        
        // Reading until specific input
        System.out.println("\nEnter numbers (type 'stop' to finish):");
        int sum = 0;
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                sum += scanner.nextInt();
            } else {
                String input = scanner.next();
                if (input.equalsIgnoreCase("stop")) {
                    break;
                }
                System.out.println("Invalid input. Enter number or 'stop'.");
            }
        }
        System.out.println("Sum: " + sum);
        
        // Input validation example
        int validNumber = 0;
        boolean validInput = false;
        
        while (!validInput) {
            System.out.print("Enter a number between 1 and 100: ");
            if (scanner.hasNextInt()) {
                validNumber = scanner.nextInt();
                if (validNumber >= 1 && validNumber <= 100) {
                    validInput = true;
                } else {
                    System.out.println("Number must be between 1 and 100.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
        System.out.println("Valid number entered: " + validNumber);
        
        // Close scanner
        scanner.close();
    }
}
```

### Common Issues and Solutions
```java
// Problem: nextInt() followed by nextLine() issue
Scanner sc = new Scanner(System.in);
System.out.print("Enter age: ");
int age = sc.nextInt();  // Reads number but leaves newline
System.out.print("Enter name: ");
String name = sc.nextLine();  // Reads empty string!

// Solution: Consume leftover newline
System.out.print("Enter age: ");
int age = sc.nextInt();
sc.nextLine();  // Consume newline
System.out.print("Enter name: ");
String name = sc.nextLine();  // Works correctly
```

### Interview Questions
**Q: Why close Scanner?**
- A: To release system resources (though System.in Scanner closing is optional).

**Q: Difference between `next()` and `nextLine()`?**
- A: `next()` reads tokens (whitespace delimited), `nextLine()` reads entire line including spaces.

**Q: How to handle invalid input with Scanner?**
- A: Use `hasNextInt()`, `hasNextDouble()` etc., to validate before reading.

### Real-World Applications
- Command-line tools and utilities
- Interactive console applications
- Data entry programs
- Configuration file readers
- Simple text-based games

---

## Summary
Java fundamentals provide the foundation for all Java programming. Understanding these concepts thoroughly is essential for:
1. Writing efficient and maintainable code
2. Passing technical interviews
3. Learning advanced Java features
4. Building robust applications

**Key Takeaways:**
- Java is platform-independent through JVM
- Strong typing helps catch errors early
- Proper naming conventions improve code readability
- Control structures (conditionals and loops) manage program flow
- Scanner enables interactive console applications

**Practice Recommendations:**
1. Write programs using all data types
2. Implement different loop patterns
3. Create input validation with Scanner
4. Solve problems using conditional statements
5. Practice type casting scenarios