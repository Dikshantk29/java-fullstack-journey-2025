# Java Exception Handling - Detailed Notes

## Table of Contents
1. [Types of Errors](#types-of-errors)
2. [What is an Exception?](#what-is-an-exception)
3. [try-catch Blocks](#try-catch-blocks)
4. [Multiple Catch Blocks](#multiple-catch-blocks)
5. [Handling vs Ducking Exceptions](#handling-vs-ducking-exceptions)
6. [throw, throws, finally](#throw-throws-finally)
7. [Custom Exceptions](#custom-exceptions)
8. [Exception Hierarchy](#exception-hierarchy)
9. [Try-With-Resources](#try-with-resources)

---

## Types of Errors

In Java, there are three main types of errors:

### 1. **Compile-Time Errors (Syntax Errors)**
Errors detected by the compiler during compilation.

```java
// Missing semicolon
public class Example {
    public static void main(String[] args) {
        System.out.println("Hello")  // Compile-time error: missing semicolon
    }
}
```

### 2. **Runtime Errors (Exceptions)**
Errors that occur during program execution.

```java
public class RuntimeErrorExample {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        System.out.println(numbers[5]); // ArrayIndexOutOfBoundsException
    }
}
```

### 3. **Logical Errors**
Program runs without crashes but produces incorrect results.

```java
public class LogicalErrorExample {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i < 10; i++) { // Should be i <= 10
            sum += i;
        }
        System.out.println("Sum: " + sum); // Wrong result due to logical error
    }
}
```

---

## What is an Exception?

An **exception** is an event that disrupts the normal flow of a program's execution. It's an object that represents an error condition.

### Common Exceptions:

```java
public class CommonExceptions {
    public static void main(String[] args) {
        // 1. ArithmeticException
        int result = 10 / 0; // Division by zero
        
        // 2. NullPointerException
        String str = null;
        System.out.println(str.length()); // Calling method on null
        
        // 3. ArrayIndexOutOfBoundsException
        int[] arr = new int[5];
        System.out.println(arr[10]); // Invalid index
        
        // 4. NumberFormatException
        int num = Integer.parseInt("abc"); // Invalid number format
        
        // 5. FileNotFoundException
        FileReader file = new FileReader("nonexistent.txt");
    }
}
```

### When Exceptions Occur:
- JVM creates an exception object
- Exception contains information about the error
- Normal program flow is disrupted
- Exception is thrown to be handled

---

## try-catch Blocks

The `try-catch` mechanism allows you to handle exceptions gracefully.

### Basic Syntax:

```java
try {
    // Code that might throw an exception
} catch (ExceptionType e) {
    // Code to handle the exception
}
```

### Example:

```java
public class TryCatchExample {
    public static void main(String[] args) {
        try {
            int[] numbers = {1, 2, 3};
            System.out.println(numbers[5]); // This will throw exception
            System.out.println("This line won't execute");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index is out of bounds!");
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Program continues...");
    }
}
```

### Output:
```
Array index is out of bounds!
Error: Index 5 out of bounds for length 3
Program continues...
```

### Practical Example - Division:

```java
public class SafeDivision {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.print("Enter numerator: ");
            int numerator = scanner.nextInt();
            
            System.out.print("Enter denominator: ");
            int denominator = scanner.nextInt();
            
            int result = numerator / denominator;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero!");
        } catch (InputMismatchException e) {
            System.out.println("Please enter valid integers!");
        }
    }
}
```

---

## Multiple Catch Blocks

You can have multiple catch blocks to handle different types of exceptions.

### Example:

```java
public class MultipleCatchExample {
    public static void main(String[] args) {
        try {
            String str = args[0]; // May throw ArrayIndexOutOfBoundsException
            int num = Integer.parseInt(str); // May throw NumberFormatException
            int result = 100 / num; // May throw ArithmeticException
            System.out.println("Result: " + result);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please provide a command-line argument!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero!");
        }
    }
}
```

### Multi-Catch (Java 7+):

Handle multiple exceptions in one catch block:

```java
public class MultiCatchExample {
    public static void main(String[] args) {
        try {
            String str = args[0];
            int num = Integer.parseInt(str);
            int result = 100 / num;
            System.out.println("Result: " + result);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Input error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Math error: " + e.getMessage());
        }
    }
}
```

### Order Matters:

```java
public class CatchOrderExample {
    public static void main(String[] args) {
        try {
            int[] arr = new int[5];
            arr[10] = 50;
        } 
        // Specific exception first
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index error!");
        }
        // General exception last
        catch (Exception e) {
            System.out.println("General error!");
        }
        
        // This would cause compile error (unreachable code):
        // catch (Exception e) { }
        // catch (ArrayIndexOutOfBoundsException e) { }
    }
}
```

---

## Handling vs Ducking Exceptions

### Handling (try-catch)
Deal with the exception where it occurs.

```java
public class HandlingExample {
    public static void readFile() {
        try {
            FileReader file = new FileReader("data.txt");
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();
            System.out.println(line);
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        readFile(); // Exception handled inside method
    }
}
```

### Ducking (throws)
Pass the exception responsibility to the caller.

```java
public class DuckingExample {
    // Method ducks the exception
    public static void readFile() throws IOException {
        FileReader file = new FileReader("data.txt");
        BufferedReader reader = new BufferedReader(file);
        String line = reader.readLine();
        System.out.println(line);
        reader.close();
    }
    
    public static void main(String[] args) {
        try {
            readFile(); // Caller must handle
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### When to Use Each:

**Handle (try-catch)**:
- You can recover from the error
- You have context to handle it properly
- You want to provide user-friendly error messages

**Duck (throws)**:
- Caller has more context to handle error
- Multiple methods need same exception handling
- You're creating a library/API

---

## throw, throws, finally

### `throw` - Explicitly Throw an Exception

```java
public class ThrowExample {
    public static void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18 or older");
        }
        System.out.println("Age is valid: " + age);
    }
    
    public static void main(String[] args) {
        try {
            validateAge(15);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### `throws` - Declare Exceptions

```java
public class ThrowsExample {
    // Method declares it may throw IOException
    public static void writeFile(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write("Hello, World!");
        writer.close();
    }
    
    // Can declare multiple exceptions
    public static void processData() throws IOException, SQLException {
        writeFile("output.txt");
        // database operations...
    }
    
    public static void main(String[] args) {
        try {
            writeFile("test.txt");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
```

### `finally` - Always Execute

The `finally` block always executes, whether an exception occurs or not.

```java
public class FinallyExample {
    public static void main(String[] args) {
        FileReader reader = null;
        
        try {
            reader = new FileReader("data.txt");
            // Read file operations
            System.out.println("Reading file...");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } finally {
            // This ALWAYS executes
            System.out.println("Finally block executed");
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file");
            }
        }
    }
}
```

### Complete Example:

```java
public class DatabaseConnection {
    public static void connectToDatabase() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            // Try to connect
            conn = DriverManager.getConnection("jdbc:mysql://localhost/db");
            stmt = conn.createStatement();
            
            // Execute query
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            
            // Process results
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            throw e; // Re-throw for caller to handle
        } finally {
            // Always close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection");
            }
        }
    }
}
```

---

## Custom Exceptions

Create your own exception classes for specific error scenarios.

### Creating Custom Exceptions:

```java
// Custom checked exception
public class InsufficientFundsException extends Exception {
    private double amount;
    
    public InsufficientFundsException(double amount) {
        super("Insufficient funds: need $" + amount);
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
}

// Custom unchecked exception
public class InvalidAccountException extends RuntimeException {
    public InvalidAccountException(String message) {
        super(message);
    }
}
```

### Using Custom Exceptions:

```java
public class BankAccount {
    private double balance;
    private String accountNumber;
    
    public BankAccount(String accountNumber, double initialBalance) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new InvalidAccountException("Account number cannot be empty");
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            double shortage = amount - balance;
            throw new InsufficientFundsException(shortage);
        }
        balance -= amount;
        System.out.println("Withdrawal successful. New balance: $" + balance);
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }
}

// Test the custom exceptions
public class BankTest {
    public static void main(String[] args) {
        try {
            BankAccount account = new BankAccount("ACC123", 1000.0);
            account.withdraw(1500.0); // Will throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Short by: $" + e.getAmount());
        }
        
        try {
            BankAccount invalidAccount = new BankAccount("", 100);
        } catch (InvalidAccountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Best Practices for Custom Exceptions:

```java
public class UserRegistrationException extends Exception {
    private String username;
    private String reason;
    
    // Constructor with message
    public UserRegistrationException(String message) {
        super(message);
    }
    
    // Constructor with message and cause
    public UserRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    // Constructor with detailed information
    public UserRegistrationException(String username, String reason) {
        super("Failed to register user: " + username + ". Reason: " + reason);
        this.username = username;
        this.reason = reason;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getReason() {
        return reason;
    }
}
```

---

## Exception Hierarchy

```
                    Object
                      |
                  Throwable
                  /       \
            Exception     Error
            /       \
     IOException  RuntimeException    OutOfMemoryError
     /                    |            StackOverflowError
FileNotFoundException   NullPointerException
                        ArithmeticException
                        ArrayIndexOutOfBoundsException
```

### Key Classes:

```java
public class ExceptionHierarchyDemo {
    public static void main(String[] args) {
        // 1. Checked Exceptions (must be handled or declared)
        try {
            FileReader file = new FileReader("file.txt"); // IOException
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 2. Unchecked Exceptions (RuntimeException)
        try {
            int result = 10 / 0; // ArithmeticException
        } catch (RuntimeException e) {
            System.out.println("Runtime error: " + e);
        }
        
        // 3. Errors (usually not caught)
        // OutOfMemoryError, StackOverflowError, etc.
    }
}
```

### Checked vs Unchecked:

```java
public class CheckedVsUnchecked {
    // Checked Exception - MUST handle or declare
    public static void readFile() throws IOException {
        FileReader file = new FileReader("data.txt");
    }
    
    // Unchecked Exception - optional to handle
    public static void divide(int a, int b) {
        int result = a / b; // May throw ArithmeticException
    }
    
    public static void main(String[] args) {
        // Must handle checked exception
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("File error");
        }
        
        // Optional to handle unchecked exception
        divide(10, 0); // Will crash if not handled
    }
}
```

### Exception Information Methods:

```java
public class ExceptionInfoExample {
    public static void main(String[] args) {
        try {
            int[] arr = new int[5];
            arr[10] = 100;
        } catch (Exception e) {
            // Get exception message
            System.out.println("Message: " + e.getMessage());
            
            // Get exception type
            System.out.println("Type: " + e.getClass().getName());
            
            // Get string representation
            System.out.println("toString: " + e.toString());
            
            // Print stack trace
            e.printStackTrace();
            
            // Get stack trace as array
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement element : stackTrace) {
                System.out.println(element);
            }
        }
    }
}
```

---

## Try-With-Resources

Automatically closes resources that implement `AutoCloseable` interface.

### Old Way (Before Java 7):

```java
public class OldResourceManagement {
    public static void readFile() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("data.txt"));
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close(); // Manual closing
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### New Way (Java 7+):

```java
public class TryWithResourcesExample {
    public static void readFile() {
        // Resources declared in parentheses are auto-closed
        try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // reader is automatically closed here
    }
}
```

### Multiple Resources:

```java
public class MultipleResourcesExample {
    public static void copyFile(String source, String destination) {
        try (
            FileInputStream input = new FileInputStream(source);
            FileOutputStream output = new FileOutputStream(destination)
        ) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            System.out.println("File copied successfully");
        } catch (IOException e) {
            System.out.println("Error copying file: " + e.getMessage());
        }
        // Both streams automatically closed in reverse order
    }
}
```

### Database Connection Example:

```java
public class DatabaseExample {
    public static void queryDatabase() {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String query = "SELECT * FROM users WHERE age > ?";
        
        try (
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, 18);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Age: " + rs.getInt("age"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        // All resources closed automatically
    }
}
```

### Custom AutoCloseable Resource:

```java
public class CustomResource implements AutoCloseable {
    private String name;
    
    public CustomResource(String name) {
        this.name = name;
        System.out.println("Opening resource: " + name);
    }
    
    public void doWork() {
        System.out.println("Working with: " + name);
    }
    
    @Override
    public void close() {
        System.out.println("Closing resource: " + name);
    }
}

public class CustomResourceTest {
    public static void main(String[] args) {
        try (CustomResource resource = new CustomResource("MyResource")) {
            resource.doWork();
        } // close() called automatically
        
        System.out.println("After try-with-resources");
    }
}
```

### Output:
```
Opening resource: MyResource
Working with: MyResource
Closing resource: MyResource
After try-with-resources
```

### Try-With-Resources with Catch and Finally:

```java
public class CompleteExample {
    public static void processFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            System.out.println("Process completed");
        }
        // reader closed before catch/finally blocks
    }
}
```

---

## Best Practices

### 1. Be Specific with Exceptions

```java
// Bad
try {
    // code
} catch (Exception e) { }

// Good
try {
    // code
} catch (FileNotFoundException e) {
    // handle file not found
} catch (IOException e) {
    // handle other IO errors
}
```

### 2. Don't Swallow Exceptions

```java
// Bad
try {
    // code
} catch (Exception e) {
    // Empty catch block - exception lost!
}

// Good
try {
    // code
} catch (Exception e) {
    System.err.println("Error: " + e.getMessage());
    e.printStackTrace();
    // Or log it properly
}
```

### 3. Use Appropriate Exception Types

```java
// Use standard exceptions when possible
throw new IllegalArgumentException("Invalid input");
throw new IllegalStateException("Object not initialized");
throw new UnsupportedOperationException("Operation not supported");
```

### 4. Document Exceptions

```java
/**
 * Transfers money between accounts
 * @param fromAccount source account
 * @param toAccount destination account
 * @param amount amount to transfer
 * @throws InsufficientFundsException if source account has insufficient funds
 * @throws InvalidAccountException if either account is invalid
 */
public void transferMoney(Account fromAccount, Account toAccount, double amount)
    throws InsufficientFundsException, InvalidAccountException {
    // implementation
}
```

### 5. Clean Up Resources

```java
// Use try-with-resources whenever possible
try (FileWriter writer = new FileWriter("output.txt")) {
    writer.write("data");
}
```

---

## Summary

| Concept | Purpose | Example |
|---------|---------|---------|
| **try-catch** | Handle exceptions | `try { } catch (Exception e) { }` |
| **throw** | Explicitly throw exception | `throw new Exception()` |
| **throws** | Declare exception | `method() throws IOException` |
| **finally** | Always execute | `finally { cleanup() }` |
| **Custom Exception** | Specific error types | `class MyException extends Exception` |
| **try-with-resources** | Auto-close resources | `try (Resource r = ...) { }` |

### Exception Types:
- **Checked**: Must handle (IOException, SQLException)
- **Unchecked**: Optional to handle (RuntimeException, NullPointerException)
- **Errors**: System-level (OutOfMemoryError)

---

## Quick Reference

```java
// Basic structure
try {
    // risky code
} catch (SpecificException e) {
    // handle specific
} catch (Exception e) {
    // handle general
} finally {
    // cleanup
}

// Try-with-resources
try (Resource r = new Resource()) {
    // use resource
} catch (Exception e) {
    // handle
}

// Throwing exceptions
throw new CustomException("error message");

// Declaring exceptions
public void method() throws IOException, SQLException {
    // code
}
```