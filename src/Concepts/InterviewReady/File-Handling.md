# 📁 File Handling in Java - Simple & Complete Guide

## 📚 What You'll Learn
1. **Reading Files** (Input Streams)
2. **Writing Files** (Output Streams)
3. **File Operations** (Create, Delete, Move)
4. **Saving Objects** (Serialization)
5. **Loading Objects** (Deserialization)

---

## 🎯 Quick Start: The Basics

### **Two Ways to Handle Files:**

```java
// OLD WAY (java.io) - Simple but slower
import java.io.*;

// NEW WAY (java.nio) - Faster, more features
import java.nio.file.*;
```

---

## 📥 PART 1: READING FILES (Input Streams)

### **Method 1: Read ENTIRE file at once** ⚡
```java
import java.nio.file.*;

public class ReadFileEasy {
    public static void main(String[] args) {
        try {
            // Read entire file as String
            String content = Files.readString(Path.of("myfile.txt"));
            System.out.println(content);
        } catch (Exception e) {
            System.out.println("File not found!");
        }
    }
}
```

### **Method 2: Read LINE BY LINE**
```java
import java.nio.file.*;
import java.util.List;

public class ReadLines {
    public static void main(String[] args) {
        try {
            // Read all lines into List
            List<String> lines = Files.readAllLines(Path.of("myfile.txt"));
            
            // Print each line
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }
}
```

### **Method 3: Read with Scanner** (User-friendly)
```java
import java.io.File;
import java.util.Scanner;

public class ReadWithScanner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("data.txt"))) {
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
            
        } catch (Exception e) {
            System.out.println("File error!");
        }
    }
}
```

### **Method 4: Read with BufferedReader** (Most Efficient)
```java
import java.io.*;

public class EfficientReader {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("bigfile.txt"))) {
            
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 📤 PART 2: WRITING FILES (Output Streams)

### **Method 1: Write ENTIRE content** ⚡
```java
import java.nio.file.*;

public class WriteFileEasy {
    public static void main(String[] args) {
        try {
            // Write entire string to file
            String content = "Hello, World!\nThis is Java!";
            Files.writeString(Path.of("output.txt"), content);
            System.out.println("File written successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### **Method 2: Write LINE BY LINE**
```java
import java.nio.file.*;
import java.util.*;

public class WriteLines {
    public static void main(String[] args) {
        try {
            // Create list of lines
            List<String> lines = Arrays.asList(
                "Line 1: Java is awesome",
                "Line 2: File handling is easy",
                "Line 3: Keep learning!"
            );
            
            // Write all lines
            Files.write(Path.of("output.txt"), lines);
            System.out.println("Lines written!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### **Method 3: Append to Existing File** (Don't Overwrite)
```java
import java.nio.file.*;
import java.util.*;

public class AppendToFile {
    public static void main(String[] args) {
        try {
            // Text to append
            String newText = "\nThis is appended text!";
            
            // Append mode
            Files.writeString(
                Path.of("output.txt"), 
                newText, 
                StandardOpenOption.APPEND
            );
            
            System.out.println("Text appended!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### **Method 4: Write with BufferedWriter** (Most Efficient)
```java
import java.io.*;

public class EfficientWriter {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
            
            writer.write("First line");
            writer.newLine();  // Add new line
            writer.write("Second line");
            writer.write(" - continued");
            
            System.out.println("Written successfully!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 🔧 PART 3: FILE OPERATIONS

### **1. Check if File Exists**
```java
import java.nio.file.*;

public class CheckFile {
    public static void main(String[] args) {
        Path path = Path.of("myfile.txt");
        
        if (Files.exists(path)) {
            System.out.println("✅ File exists!");
        } else {
            System.out.println("❌ File doesn't exist");
        }
    }
}
```

### **2. Create New File**
```java
import java.nio.file.*;

public class CreateFile {
    public static void main(String[] args) {
        try {
            Files.createFile(Path.of("newfile.txt"));
            System.out.println("📄 File created!");
        } catch (Exception e) {
            System.out.println("File already exists!");
        }
    }
}
```

### **3. Delete a File**
```java
import java.nio.file.*;

public class DeleteFile {
    public static void main(String[] args) {
        try {
            Files.delete(Path.of("fileToDelete.txt"));
            System.out.println("🗑️ File deleted!");
        } catch (Exception e) {
            System.out.println("Cannot delete - file doesn't exist");
        }
    }
}
```

### **4. Copy File**
```java
import java.nio.file.*;

public class CopyFile {
    public static void main(String[] args) {
        try {
            Files.copy(
                Path.of("source.txt"),
                Path.of("destination.txt"),
                StandardCopyOption.REPLACE_EXISTING
            );
            System.out.println("📋 File copied!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### **5. Move/Rename File**
```java
import java.nio.file.*;

public class MoveFile {
    public static void main(String[] args) {
        try {
            Files.move(
                Path.of("oldname.txt"),
                Path.of("newname.txt"),
                StandardCopyOption.REPLACE_EXISTING
            );
            System.out.println("🚚 File moved/renamed!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### **6. Get File Information**
```java
import java.nio.file.*;
import java.nio.file.attribute.*;

public class FileInfo {
    public static void main(String[] args) {
        try {
            Path path = Path.of("myfile.txt");
            
            System.out.println("📊 File Information:");
            System.out.println("Name: " + path.getFileName());
            System.out.println("Path: " + path.toAbsolutePath());
            
            // Check if it's a directory or file
            if (Files.isDirectory(path)) {
                System.out.println("Type: Directory");
            } else {
                System.out.println("Type: File");
            }
            
            // Get file size
            long size = Files.size(path);
            System.out.println("Size: " + size + " bytes");
            
            // Get last modified time
            FileTime lastModified = Files.getLastModifiedTime(path);
            System.out.println("Last Modified: " + lastModified);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 💾 PART 4: SERIALIZATION (Save Objects to File)

### **Step 1: Create a Serializable Class**
```java
import java.io.*;

// Must implement Serializable
class Student implements Serializable {
    String name;
    int age;
    transient String password;  // 'transient' means don't save this field
    
    // Constructor
    Student(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
    
    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
```

### **Step 2: Save Object to File**
```java
public class SaveObject {
    public static void main(String[] args) {
        // Create object
        Student student = new Student("John", 20, "secret123");
        
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("student.dat"))) {
            
            // Save object to file
            oos.writeObject(student);
            System.out.println("✅ Object saved!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 📂 PART 5: DESERIALIZATION (Load Objects from File)

```java
public class LoadObject {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("student.dat"))) {
            
            // Load object from file
            Student student = (Student) ois.readObject();
            
            // Use the object
            student.display();
            System.out.println("✅ Object loaded!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## 🤔 INTERVIEW QUESTIONS & ANSWERS

### **Q1: What's the difference between `FileReader` and `BufferedReader`?**
**Answer:**
- `FileReader` reads character by character (slow)
- `BufferedReader` reads chunks of data (fast) + has `readLine()` method

### **Q2: What does `try-with-resources` do?**
**Answer:** Automatically closes files after use - no need for `finally` block
```java
try (BufferedReader br = new FileReader("file.txt")) {
    // File auto-closes here
}
```

### **Q3: What is Serialization?**
**Answer:** Converting Java objects into bytes to save in files or send over network

### **Q4: What does `transient` keyword do?**
**Answer:** Skips that field during serialization (useful for passwords)

---

## 🏗️ REAL-WORLD EXAMPLES

### **Example 1: User Login System**
```java
import java.nio.file.*;
import java.util.*;

public class LoginSystem {
    public static void main(String[] args) {
        // Save user credentials
        String username = "admin";
        String password = "1234";
        String userData = username + "," + password;
        
        // Save to file
        try {
            Files.writeString(Path.of("users.txt"), userData);
            System.out.println("User saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### **Example 2: Simple To-Do List**
```java
import java.nio.file.*;
import java.util.*;

public class TodoList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();
        
        System.out.println("Enter tasks (type 'done' to finish):");
        while (true) {
            String task = scanner.nextLine();
            if (task.equals("done")) break;
            tasks.add(task);
        }
        
        // Save to file
        try {
            Files.write(Path.of("tasks.txt"), tasks);
            System.out.println("Tasks saved!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

---

## ✅ BEST PRACTICES

1. **ALWAYS close files** - Use `try-with-resources`
2. **Handle exceptions** - Use `try-catch`
3. **Use BufferedReader/BufferedWriter** for large files
4. **Check if file exists** before reading
5. **Use NIO (Files class)** for simple operations
6. **Specify character encoding** for text files
7. **Delete temporary files** after use

### **Good Example:**
```java
try (BufferedWriter writer = Files.newBufferedWriter(
        Path.of("file.txt"),
        StandardCharsets.UTF_8)) {
    
    writer.write("Hello World");
    
} catch (IOException e) {
    System.err.println("Error: " + e.getMessage());
}
```

---

## ❌ COMMON MISTAKES

### **Mistake 1: Forgetting to close file**
```java
// ❌ WRONG
FileReader fr = new FileReader("file.txt");
// File stays open!

// ✅ CORRECT
try (FileReader fr = new FileReader("file.txt")) {
    // Auto-closes
}
```

### **Mistake 2: Not handling exceptions**
```java
// ❌ WRONG
Files.readAllLines(Path.of("nonexistent.txt"));

// ✅ CORRECT
try {
    Files.readAllLines(Path.of("file.txt"));
} catch (IOException e) {
    System.out.println("File not found");
}
```

### **Mistake 3: Using wrong path**
```java
// ❌ Might not work everywhere
new File("C:\\Users\\file.txt");

// ✅ Works everywhere
Path.of("data", "files", "myfile.txt");
```

---

## 📊 QUICK COMPARISON TABLE

| Task | Simple Way | Efficient Way |
|------|------------|---------------|
| Read file | `Files.readString()` | `BufferedReader` |
| Write file | `Files.writeString()` | `BufferedWriter` |
| Read lines | `Files.readAllLines()` | `BufferedReader.readLine()` |
| Check exists | `Files.exists()` | `Files.exists()` |
| Copy file | `Files.copy()` | `Files.copy()` |

---

## 🧠 MEMORY TRICKS

### **ABC of File Handling:**
- **A**lways close files
- **B**uffer for big files
- **C**heck exceptions

### **Serialization Rule:**
"**S**ave **O**bjects, **L**oad **O**bjects"
- **S**erialize → Save
- **D**eserialize → Load

### **File Opening Modes:**
- **R**ead → "r" or `StandardOpenOption.READ`
- **W**rite → "w" or `StandardOpenOption.WRITE`
- **A**ppend → "a" or `StandardOpenOption.APPEND`

---

## 🚀 QUICK REFERENCE CARD

```java
// === READ FILE ===
String text = Files.readString(Path.of("file.txt"));

// === WRITE FILE ===
Files.writeString(Path.of("file.txt"), "Hello");

// === CHECK FILE ===
boolean exists = Files.exists(Path.of("file.txt"));

// === DELETE FILE ===
Files.delete(Path.of("file.txt"));

// === SAVE OBJECT ===
oos.writeObject(myObject);

// === LOAD OBJECT ===
MyClass obj = (MyClass) ois.readObject();
```

---

## 📝 SUMMARY

1. **Use `java.nio.file.Files`** for simple operations
2. **Use `BufferedReader/BufferedWriter`** for large files
3. **Always use `try-with-resources`** to auto-close
4. **Implement `Serializable`** to save objects
5. **Check file existence** before operations
6. **Handle exceptions properly**

---

## 🔗 NEXT STEPS TO PRACTICE

1. Create a student marks storage system
2. Build a simple note-taking app
3. Make a program that backs up files
4. Create a configuration file reader
5. Build a chat application that saves history

**Remember:** Practice with small files first, then try with larger ones!

---

## ❓ NEED HELP?

Common issues:
1. **FileNotFoundException** → Check file path
2. **SerializationException** → Class not Serializable
3. **AccessDeniedException** → Check file permissions
4. **OutOfMemoryError** → File too large, use buffering

**Pro Tip:** Start with `Files` class methods - they're the easiest!


# 📁 Note.md (Continued)

### ⚡ File Handling — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Java I/O API provides streams to read/write data from/to files, network, etc.
- Two main hierarchies: **byte streams** (InputStream/OutputStream) for binary data, and **character streams** (Reader/Writer) for text.
- Java NIO (New I/O) in `java.nio` package provides non-blocking and buffer-oriented operations.
- Core purpose: Persistent data storage, configuration, data exchange.

#### 2️⃣ Must-Remember Points
- **Byte streams**: `InputStream`/`OutputStream` abstract classes; used for images, videos, serialized objects.
- **Character streams**: `Reader`/`Writer` abstract classes; handle text with character encoding.
- **Decorator pattern**: Used heavily (e.g., `BufferedInputStream` wraps `FileInputStream`).
- Always close streams in `finally` block or use try-with-resources (Java 7+).
- `File` class (legacy) represents file/directory paths; `Path` interface (NIO) is preferred.
- **Serialization**: Converting object to byte stream; requires `Serializable` interface.
- **Deserialization**: Reconstructing object from byte stream.
- Use `transient` keyword to exclude fields from serialization.
- **NIO.2** (`java.nio.file`) provides `Files`, `Paths`, `Path` for modern file operations.

#### 3️⃣ Key Code Patterns (Java)
```java
// Try-with-resources (auto-close)
try (FileInputStream fis = new FileInputStream("file.bin");
     BufferedInputStream bis = new BufferedInputStream(fis)) {
    int data;
    while ((data = bis.read()) != -1) { /* process */ }
}

// Reading text file
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    String line;
    while ((line = br.readLine()) != null) { /* process */ }
}

// NIO.2 - Read all lines
List<String> lines = Files.readAllLines(Paths.get("file.txt"));

// Writing text file
try (PrintWriter pw = new PrintWriter(new FileWriter("output.txt"))) {
    pw.println("Hello");
}
```

#### 4️⃣ Internals / Behind the Scenes
- **Streams**: Sequential access to data; can be chained with decorators.
- **Buffering**: Wrapper streams (e.g., `BufferedReader`) reduce system calls by reading chunks.
- **Character encoding**: Default is platform-dependent; specify explicitly (e.g., UTF-8) using `InputStreamReader`/`OutputStreamWriter`.
- **Serialization**: Uses `ObjectOutputStream`; writes class metadata, field values.
- **Deserialization**: Creates new object without calling constructor; calls `readObject()` if defined.

#### 5️⃣ Common Interview Traps
- Not closing streams leads to resource leaks.
- Using byte streams for text (causes encoding issues).
- Assuming default encoding; always specify for text files.
- `File` methods like `delete()` return `false` on failure (no exception).
- Serializing static fields (they aren't serialized; stored per class, not per object).
- Forgetting `serialVersionUID` leads to `InvalidClassException` if class changes.

#### 6️⃣ Interview Q&A (High Yield)
- **Q: Difference between `InputStream` and `Reader`?** A: `InputStream` reads bytes; `Reader` reads characters (handles encoding).
- **Q: Why use `BufferedReader`?** A: It buffers input, reducing system calls and improving performance for reading text.
- **Q: What is `transient`?** A: Keyword indicating a field should not be serialized.
- **Q: How to handle file paths cross-platform?** A: Use `Paths.get()` or `File.separator`; better to use `/` as Java handles conversion.
- **Q: What is `serialVersionUID`?** A: A version ID for serialized class; if not defined, JVM generates one based on class structure—change in class breaks deserialization.
- **Q: Difference between `FileWriter` and `FileOutputStream`?** A: `FileWriter` writes characters (text) with default encoding; `FileOutputStream` writes bytes.
- **Q: What is `Externalizable`?** A: Interface extending `Serializable` allowing custom serialization logic via `writeExternal()`/`readExternal()`.

---

### ⚡ Input Streams — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Abstract class `java.io.InputStream` for reading binary data as bytes.
- Core method: `int read()` reads single byte (0–255) or -1 for EOF.
- Decorators like `BufferedInputStream`, `DataInputStream` add functionality.
- Used for non-text files (images, audio, serialized objects).

#### 2️⃣ Must-Remember Points
- `read()` returns `int` to handle -1 (EOF).
- `read(byte[] b)` reads up to `b.length` bytes into array.
- Always wrap with `BufferedInputStream` for performance.
- Close stream to release file handle.

#### 3️⃣ Key Code Patterns (Java)
```java
// Read file to byte array (Java 9+)
byte[] allBytes = inputStream.readAllBytes();

// Traditional reading with buffer
byte[] buffer = new byte[1024];
int bytesRead;
while ((bytesRead = inputStream.read(buffer)) != -1) {
    // process buffer[0..bytesRead-1]
}
```

#### 5️⃣ Common Interview Traps
- Ignoring return value of `read(byte[])` (could be less than array length).
- Not handling `IOException`.

#### 6️⃣ Interview Q&A (High Yield)
- **Q: What does `InputStream.read()` return?** A: `int` (0–255) for byte value, -1 for end of stream.

---

### ⚡ Output Streams — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Abstract class `java.io.OutputStream` for writing binary data as bytes.
- Core method: `void write(int b)` writes low 8 bits of `int`.
- Decorators: `BufferedOutputStream`, `DataOutputStream`, `PrintStream`.
- Flush to ensure data is written; close to release resources.

#### 2️⃣ Must-Remember Points
- `write(byte[] b)` writes entire array.
- `BufferedOutputStream` improves performance by batching writes.
- `PrintStream` (`System.out`) adds `print()`, `println()` methods.
- Always flush before closing (close does flush automatically).

#### 3️⃣ Key Code Patterns (Java)
```java
// Write byte array
byte[] data = ...;
try (FileOutputStream fos = new FileOutputStream("file.bin")) {
    fos.write(data);
}

// With buffering
try (BufferedOutputStream bos = new BufferedOutputStream(
        new FileOutputStream("file.bin"))) {
    bos.write(65); // writes 'A'
}
```

#### 5️⃣ Common Interview Traps
- Forgetting to flush before close when immediate write is needed.
- Using `OutputStream` for text without considering encoding.

---

### ⚡ File Operations — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Create, read, update, delete, and query files/directories.
- Legacy: `java.io.File` class.
- Modern: `java.nio.file.Path`, `Files`, `Paths` (NIO.2, Java 7+).

#### 2️⃣ Must-Remember Points
- `File` methods: `exists()`, `isFile()`, `isDirectory()`, `listFiles()`, `mkdir()`.
- `Files` utility class: `copy()`, `move()`, `delete()`, `readAllLines()`, `write()`.
- `Paths.get()` creates `Path` object from string.
- Atomic operations: `Files.move()` with `StandardCopyOption.ATOMIC_MOVE`.

#### 3️⃣ Key Code Patterns (Java)
```java
// Check if file exists
boolean exists = Files.exists(Paths.get("file.txt"));

// Read all lines
List<String> lines = Files.readAllLines(Paths.get("file.txt"));

// Copy file
Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

// Walk directory
Files.walk(Paths.get("/dir")).filter(Files::isRegularFile).forEach(System.out::println);
```

#### 5️⃣ Common Interview Traps
- `File.mkdir()` creates only last directory; `mkdirs()` creates parent directories.
- `Files.delete()` throws `IOException` if file doesn't exist; `deleteIfExists()` returns boolean.
- Not handling symbolic links correctly.

#### 6️⃣ Interview Q&A (High Yield)
- **Q: Difference between `File` and `Path`?** A: `File` is older, less feature-rich; `Path` (NIO.2) supports symbolic links, better directory walking, atomic operations.
- **Q: How to list all files in directory?** A: `Files.list(path)` or `Files.walk()` for recursive.

---

### ⚡ Serialization — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Converting object state to byte stream for storage/transmission.
- Implement `java.io.Serializable` (marker interface).
- Use `ObjectOutputStream` to serialize.
- Static and `transient` fields are not serialized.

#### 2️⃣ Must-Remember Points
- All non-transient, non-static fields must be serializable (primitive or `Serializable`).
- If superclass not serializable, its fields must have no-arg constructor (initialized to default values).
- `serialVersionUID`: Declare `private static final long` for version control.
- Custom serialization: Implement `private void writeObject(ObjectOutputStream oos)`.
- **Security risk**: Serialization can be exploited; validate data during deserialization.

#### 3️⃣ Key Code Patterns (Java)
```java
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private transient int ssn; // not serialized
    
    // Custom serialization
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // default handling
        // custom code
    }
}

// Serialize object
try (ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("person.ser"))) {
    oos.writeObject(person);
}
```

#### 5️⃣ Common Interview Traps
- Adding/removing fields without considering `serialVersionUID`.
- Serializing sensitive data (passwords, keys).
- Forgetting that inner classes need special handling (implicit reference to outer class).

#### 6️⃣ Interview Q&A (High Yield)
- **Q: What is `Serializable`?** A: Marker interface (no methods) indicating class can be serialized.
- **Q: Why declare `serialVersionUID`?** A: To control versioning; prevents `InvalidClassException` when class changes.
- **Q: What happens if superclass is not serializable?** A: Its fields get default values during deserialization; must have no-arg constructor.
- **Q: Can we serialize static variables?** A: No, they belong to class, not object state.

---

### ⚡ Deserialization — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Reconstructing object from byte stream.
- Use `ObjectInputStream.readObject()`.
- Constructor not called; fields populated from stream.
- Must handle `ClassNotFoundException`, `InvalidClassException`.

#### 2️⃣ Must-Remember Points
- Deserialization creates new object without invoking constructor.
- `transient` fields get default values (0, null, false).
- Custom deserialization: Implement `private void readObject(ObjectInputStream ois)`.
- `readResolve()` method can replace deserialized object (used in singletons).
- **Security**: Validate objects; `ObjectInputFilter` (Java 9+) to restrict classes.

#### 3️⃣ Key Code Patterns (Java)
```java
// Deserialize object
try (ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("person.ser"))) {
    Person p = (Person) ois.readObject();
}

// Custom deserialization and readResolve
private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
    ois.defaultReadObject();
    // custom initialization
}

private Object readResolve() {
    return this; // or replace with singleton instance
}
```

#### 5️⃣ Common Interview Traps
- Not casting return value of `readObject()` to correct type.
- Assuming deserialized object's `transient` fields are restored (they're not).
- Deserializing untrusted data leading to security vulnerabilities.

#### 6️⃣ Interview Q&A (High Yield)
- **Q: Is constructor called during deserialization?** A: No, object is instantiated without constructor.
- **Q: How to initialize `transient` fields after deserialization?** A: Implement `readObject()` method or set in `readResolve()`.
- **Q: What is `readResolve()`?** A: Method called after deserialization; can replace returned object (e.g., enforce singleton).
- **Q: What exception occurs if class changed after serialization?** A: `InvalidClassException` if `serialVersionUID` mismatch.

---

## 🚀 Final Java Full-Stack Interview Rapid Revision
**Top Do's & Don'ts for File Handling:**
- **DO** use try-with-resources for automatic stream closing.
- **DO** specify character encoding (UTF-8) for text files.
- **DO** use NIO.2 (`Files`, `Paths`) for modern file operations.
- **DON'T** ignore `IOException`—handle or declare it.
- **DON'T** serialize sensitive data without encryption.

**Golden Rules:**
1. **Streams**: Byte for binary (`InputStream`/`OutputStream`), character for text (`Reader`/`Writer`).
2. **Always Close**: Use try-with-resources (Java 7+).
3. **Buffering**: Wrap file streams with buffered streams for performance.
4. **Serialization**: Implement `Serializable`, declare `serialVersionUID`, mark sensitive fields `transient`.

**Last-Minute Reminders:**
- `Files.readAllLines()` reads entire file into memory (not for huge files).
- `ObjectInputStream`/`ObjectOutputStream` for serialization.
- `transient` fields are not serialized; get default values on deserialization.
- NIO.2 provides atomic operations and better symbolic link handling.

**Things Interviewers LOVE to Ask:**
1. Difference between `InputStream` and `Reader`.
2. Why use `BufferedReader`/`BufferedWriter`?
3. Explain `transient` and `serialVersionUID`.
4. How to handle files cross-platform?
5. Security concerns with serialization.
6. Difference between `File` and `Path` (NIO.2).