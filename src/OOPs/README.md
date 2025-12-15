# 📚 COMPLETE Java OOP Notes - Exam & Interview Master Sheet

## 🎯 **Java OOP - The Big Picture**

### **4 Pillars of OOP (Remember "A PIE")**
- **A**bstraction
- **P**olymorphism
- **I**nheritance
- **E**ncapsulation

---

## 1️⃣ **CLASSES & OBJECTS**

### 📖 Definition
- **Class**: Blueprint/template (like a cookie cutter)
- **Object**: Instance created from class (like a cookie)

### 🎯 Why Needed
- Model real-world entities
- Organize code logically

### 📝 Syntax
```java
class ClassName {          // Class definition
    // variables
    // methods
}

ClassName obj = new ClassName();  // Object creation
```

### 💡 Example
```java
class Car {
    String model;
    void start() {
        System.out.println("Car starting...");
    }
}

Car myCar = new Car();
myCar.model = "Toyota";
myCar.start();
```

### ✅ Key Points
- Class is logical, Object is physical
- `new` allocates memory
- One class → Many objects
- Objects have state (data) + behavior (methods)

### ❌ Common Mistakes
- Forgetting `new` keyword
- Confusing class with object

### 🎓 Memory Trick
**"Class = Recipe, Object = Cake"**

---

## 2️⃣ **ACCESS MODIFIERS**

### 📖 Definition
Control visibility of class members

### 🎯 Why Needed
- Security (hide data)
- Encapsulation
- Control access

### 📝 Syntax
```java
public class Example {
    public int a;      // Anywhere
    private int b;     // Class only
    protected int c;   // Package + Subclasses
    int d;            // Package only (default)
}
```

### ✅ Key Points
| Modifier | Class | Package | Subclass | World |
|----------|-------|---------|----------|-------|
| Private  | ✓     | ✗       | ✗        | ✗     |
| Default  | ✓     | ✓       | ✗        | ✗     |
| Protected| ✓     | ✓       | ✓        | ✗     |
| Public   | ✓     | ✓       | ✓        | ✓     |

### 🎓 Memory Trick
**"Please People Don't Pry"** (Public → Protected → Default → Private)

---

## 3️⃣ **GETTERS & SETTERS**

### 📖 Definition
- **Getter**: Read private variable
- **Setter**: Modify private variable

### 🎯 Why Needed
- Control access to private data
- Add validation
- Maintain encapsulation

### 📝 Syntax
```java
class Student {
    private int age;
    
    // Getter
    public int getAge() {
        return age;
    }
    
    // Setter
    public void setAge(int age) {
        if(age > 0) this.age = age;
    }
}
```

### ✅ Key Points
- Variables → private
- Getters/setters → public
- Naming: getVariable() / setVariable()
- `this` refers to current object

### 🎓 Memory Trick
**"Setters are gatekeepers, Getters are messengers"**

---

## 4️⃣ **ENCAPSULATION**

### 📖 Definition
Wrapping data + methods together, hiding internal details

### 🎯 Why Needed
- Data protection
- Flexibility to change implementation
- Code maintainability

### 📝 Syntax
```java
class BankAccount {
    private double balance;
    
    public void deposit(double amount) {
        if(amount > 0) balance += amount;
    }
    
    public double getBalance() {
        return balance;
    }
}
```

### ✅ Key Points
- Data hiding with `private`
- Access through public methods
- Foundation of OOP

### 🎓 Memory Trick
**"Capsule hides medicine, Encapsulation hides data"**

---

## 5️⃣ **CONSTRUCTORS**

### 📖 Definition
Special method that initializes object

### 🎯 Why Needed
- Initialize object values
- Ensure object ready to use

### 📝 Syntax
```java
class Student {
    String name;
    
    // Constructor
    Student(String n) {
        name = n;  // Initialization
    }
}

Student s = new Student("John");
```

### ✅ Key Points
- Same name as class
- No return type
- Called automatically with `new`
- Can be overloaded

### 🎓 Memory Trick
**"Constructor = Birth certificate for object"**

---

## 6️⃣ **TYPES OF CONSTRUCTORS**

### 🔸 **Default Constructor**
```java
class A {
    A() { }  // Java provides if none defined
}
```

### 🔸 **Parameterized Constructor**
```java
class B {
    int x;
    B(int val) {
        x = val;
    }
}
```

### 🔸 **Copy Constructor**
```java
class C {
    int x;
    C(C obj) {  // Copy constructor
        x = obj.x;
    }
}
```

### ✅ Key Points
- Default: No parameters
- Parameterized: Custom initialization
- Copy: Duplicate object
- Constructor overloading allowed

---

## 7️⃣ **SHALLOW vs DEEP COPY**

### 📖 Definition
- **Shallow**: Copies references (shared)
- **Deep**: Creates new objects (independent)

### 💡 Example
```java
class Address {
    String city;
}

class Student {
    String name;
    Address addr;
    
    // SHALLOW COPY (default clone())
    Student shallowCopy() throws CloneNotSupportedException {
        return (Student) super.clone();  // addr SHARED
    }
    
    // DEEP COPY
    Student deepCopy() {
        Student copy = new Student();
        copy.name = this.name;
        copy.addr = new Address();  // NEW object
        copy.addr.city = this.addr.city;
        return copy;
    }
}
```

### ✅ Difference Table
| Aspect | Shallow Copy | Deep Copy |
|--------|--------------|-----------|
| Objects | One new object | Multiple new objects |
| References | Shared | Separate |
| Speed | Fast | Slow |
| Memory | Less | More |

### 🎓 Memory Trick
**"Shallow = Shadow (follows changes), Deep = Duplicate (independent)"**

---

## 8️⃣ **DESTRUCTOR (Java)**

### 📖 Definition
Java has **NO explicit destructor** - uses Garbage Collector

### 📝 Alternative
```java
class MyClass {
    // finalize() - Deprecated in Java 9+
    protected void finalize() throws Throwable {
        // Cleanup code
        super.finalize();
    }
}
```

### ✅ Key Points
- Automatic garbage collection
- `finalize()` unreliable
- Use try-with-resources for cleanup
- GC runs automatically

### 🎓 Memory Trick
**"Java has a maid (GC) that cleans automatically"**

---

## 9️⃣ **INHERITANCE**

### 📖 Definition
Child class gets properties/methods from parent

### 🎯 Why Needed
- Code reusability
- Method overriding (polymorphism)
- Real-world relationships (IS-A)

### 📝 Syntax
```java
class Parent { }
class Child extends Parent { }  // extends keyword
```

### ✅ Key Points
- `extends` keyword
- Child gets all non-private members
- Single inheritance only (one parent)
- Constructors NOT inherited

### 🎓 Memory Trick
**"Child inherits father's property (but not secrets - private)"**

---

## 🔟 **TYPES OF INHERITANCE**

### 🔸 **Single Level**
```java
class A { }
class B extends A { }  // B → A
```

### 🔸 **Multi Level**
```java
class A { }
class B extends A { }
class C extends B { }  // C → B → A
```

### 🔸 **Hierarchical**
```java
      A
     / \
    B   C   // Both B and C extend A
```

### 🔸 **Hybrid** (Through interfaces only)
```java
interface A { }
interface B { }
class C { }
class D extends C implements A, B { }
```

### ✅ Key Points
- Java supports all types through classes/interfaces
- Multiple inheritance via interfaces only
- Hybrid = Combination of types

---

## 1️⃣1️⃣ **POLYMORPHISM**

### 📖 Definition
"Many forms" - same thing behaves differently

### 🎯 Types
1. **Compile-time**: Method overloading
2. **Runtime**: Method overriding

### 💡 Example
```java
// COMPILE-TIME (Overloading)
class Math {
    int add(int a, int b) { return a+b; }
    double add(double a, double b) { return a+b; }
}

// RUNTIME (Overriding)
class Animal {
    void sound() { System.out.println("Animal sound"); }
}
class Dog extends Animal {
    void sound() { System.out.println("Bark"); }  // Override
}
```

### ✅ Difference Table
| Aspect | Method Overloading | Method Overriding |
|--------|-------------------|-------------------|
| Location | Same class | Parent-child |
| Parameters | Must differ | Must be same |
| Binding | Compile-time | Runtime |
| Return type | Can differ | Must be same/subtype |

### 🎓 Memory Trick
**"OverLOADing = LOAD more parameters, OverRIDing = RIDE over parent's method"**

---

## 1️⃣2️⃣ **METHOD OVERLOADING**

### 📖 Definition
Same method name, different parameters in SAME class

### 📝 Syntax
```java
class Calculator {
    int add(int a, int b) { return a+b; }
    int add(int a, int b, int c) { return a+b+c; }  // Overloaded
    double add(double a, double b) { return a+b; }   // Overloaded
}
```

### ✅ Key Points
- Same class
- Same name
- Different parameters (type/number/order)
- Return type can differ

---

## 1️⃣3️⃣ **METHOD OVERRIDING**

### 📖 Definition
Child provides specific implementation of parent's method

### 📝 Syntax
```java
class Parent {
    void show() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    @Override  // Annotation (recommended)
    void show() {
        System.out.println("Child");  // Override
    }
}
```

### ✅ Key Points
- Inheritance required
- Same method signature
- Access cannot be more restrictive
- Use `@Override` annotation

---

## 1️⃣4️⃣ **PACKAGES**

### 📖 Definition
Folder/namespace grouping related classes

### 🎯 Why Needed
- Avoid naming conflicts
- Better organization
- Access control

### 📝 Syntax
```java
package com.company.mypackage;  // First line

import java.util.ArrayList;     // Import specific
import java.util.*;            // Import all
```

### ✅ Key Points
- Hierarchical naming (reverse domain)
- `package` = first line
- `import` after package
- `java.lang` auto-imported

### 🎓 Memory Trick
**"Package = School bag, Classes = Books in bag"**

---

## 1️⃣5️⃣ **ABSTRACTION**

### 📖 Definition
Hiding complex implementation, showing only essentials

### 🎯 Why Needed
- Reduce complexity
- Focus on "what" not "how"
- Security

### 📝 Implementation
1. Abstract classes
2. Interfaces

### 💡 Example
```java
abstract class Vehicle {
    abstract void start();  // What to do (abstract)
    
    void stop() {           // How to do (concrete)
        System.out.println("Stopping...");
    }
}
```

### ✅ Key Points
- Shows essential, hides non-essential
- Cannot create object of abstract class
- Can have abstract + concrete methods

### 🎓 Memory Trick
**"Abstraction = TV remote (buttons visible, circuits hidden)"**

---

## 1️⃣6️⃣ **ABSTRACT CLASSES**

### 📖 Definition
Class that cannot be instantiated, may have abstract methods

### 📝 Syntax
```java
abstract class Shape {
    abstract double area();  // No body
    
    void display() {         // Can have concrete methods
        System.out.println("Shape");
    }
}

class Circle extends Shape {
    double area() {          // MUST implement
        return 3.14 * r * r;
    }
}
```

### ✅ Key Points
- `abstract` keyword
- Cannot instantiate
- Child MUST implement all abstract methods
- Can have constructors

### 🎓 Memory Trick
**"Abstract = Incomplete recipe"**

---

## 1️⃣7️⃣ **INTERFACES**

### 📖 Definition
**100% abstract class** - defines contract

### 🎯 Why Needed
- Multiple inheritance
- Define contract
- Loose coupling

### 📝 Syntax
```java
interface Animal {
    void eat();      // public abstract by default
    void sleep();    // public abstract by default
    
    // Java 8+
    default void breathe() {    // Default method
        System.out.println("Breathing...");
    }
    
    static boolean isAnimal() {  // Static method
        return true;
    }
}

class Dog implements Animal {
    public void eat() { /* Must implement */ }
    public void sleep() { /* Must implement */ }
}
```

### ✅ Key Points
- All methods public abstract (until Java 8)
- All variables public static final
- Cannot instantiate
- Class can implement MULTIPLE interfaces
- Interface can extend MULTIPLE interfaces

### 📊 Interface vs Abstract Class
| Aspect | Interface | Abstract Class |
|--------|-----------|----------------|
| Inheritance | Multiple | Single |
| Methods | Abstract + default/static | Abstract + concrete |
| Variables | Only constants | Any |
| Constructors | No | Yes |
| When to use | Contract/capability | Share code |

### 🎓 Memory Trick
**"Interface = What you CAN do, Abstract class = What you ARE"**

---

## 1️⃣8️⃣ **STATIC KEYWORD**

### 📖 Definition
Belongs to CLASS, not object (shared)

### 🎯 Why Needed
- Memory efficiency (one copy)
- Utility methods
- Constants

### 📝 Syntax
```java
class Student {
    String name;           // Instance variable
    static String school;  // Class variable
    
    static void show() {   // Static method
        // Cannot use 'this' or 'super'
    }
    
    static {               // Static block
        school = "ABC School";
    }
}
```

### ✅ Key Points
- One copy per CLASS
- Can access without object: `Student.school`
- Cannot use `this` or `super`
- Main method is static
- Static block runs when class loads

### 🎓 Memory Trick
**"Static = Shared (like a common notebook)"**

---

## 1️⃣9️⃣ **SUPER KEYWORD**

### 📖 Definition
Refers to immediate PARENT class

### 🎯 Uses
1. Call parent constructor
2. Call parent method
3. Access parent variable

### 📝 Syntax
```java
class Parent {
    String color = "Red";
    void show() { System.out.println("Parent"); }
}

class Child extends Parent {
    String color = "Blue";
    
    void display() {
        System.out.println(color);      // Blue (child's)
        System.out.println(super.color); // Red (parent's)
        super.show();                   // Call parent's method
    }
    
    Child() {
        super();  // Call parent constructor (FIRST LINE)
    }
}
```

### ✅ Key Points
- First line in constructor if used
- Cannot use in static context
- Refers to immediate parent only

### 🎓 Memory Trick
**"Super = Parent's superhero cape"**

---

## 📊 **QUICK COMPARISON TABLES**

### **OOP Pillars Comparison**
| Concept | Purpose | Example |
|---------|---------|---------|
| Abstraction | Hide complexity | Abstract class, Interface |
| Encapsulation | Hide data | Private variables + getters/setters |
| Inheritance | Code reuse | extends keyword |
| Polymorphism | Many forms | Overloading, Overriding |

### **Class vs Object**
| Aspect | Class | Object |
|--------|-------|--------|
| Nature | Logical | Physical |
| Memory | No allocation | Memory allocated |
| Creation | Once | Many times |
| Keyword | class | new |

### **Abstract Class vs Interface**
| Decision Factor | Choose Abstract Class | Choose Interface |
|-----------------|----------------------|------------------|
| Need default implementation | ✓ | ✗ |
| Multiple inheritance needed | ✗ | ✓ |
| Share code among related classes | ✓ | ✗ |
| Unrelated classes need capability | ✗ | ✓ |
| Need to declare non-static fields | ✓ | ✗ |

---

## 🚨 **COMMON MISTAKES & SOLUTIONS**

### **Mistake 1: Constructor with return type**
```java
class A {
    void A() { }  // WRONG! This is a method, not constructor
    A() { }       // CORRECT: No return type
}
```

### **Mistake 2: Overloading with only return type**
```java
class B {
    int add(int a, int b) { return a+b; }
    double add(int a, int b) { return a+b; }  // ERROR! Not overloaded
}
```

### **Mistake 3: Reducing access in overriding**
```java
class Parent {
    public void show() { }
}
class Child extends Parent {
    void show() { }  // ERROR! Default is less restrictive than public
}
```

### **Mistake 4: Multiple inheritance with classes**
```java
class A { }
class B { }
class C extends A, B { }  // ERROR! Not allowed in Java
```

### **Mistake 5: Abstract method in non-abstract class**
```java
class D {
    abstract void method();  // ERROR! Class must be abstract
}
```

---

## 🎓 **INTERVIEW GOLD QUESTIONS**

### **Q1: Can we override static method?**
**Answer**: No, it's method hiding (not overriding). Static belongs to class.

### **Q2: Can abstract class have constructor?**
**Answer**: Yes, for initializing common variables.

### **Q3: Can interface have constructor?**
**Answer**: No, interfaces cannot be instantiated.

### **Q4: Difference: final vs finally vs finalize?**
**Answer**:
- `final`: variable/class/method cannot be changed
- `finally`: block that always executes (exception handling)
- `finalize()`: method called before object destruction

### **Q5: Can we have try without catch?**
**Answer**: Yes, with finally block: `try { } finally { }`

### **Q6: Can we override private method?**
**Answer**: No, private not accessible in child class.

### **Q7: Can we inherit constructors?**
**Answer**: No, but child can call parent constructor using `super()`

### **Q8: Can abstract method be static?**
**Answer**: No, abstract needs overriding, static cannot be overridden.

### **Q9: Can we declare interface as final?**
**Answer**: No, must be implemented by classes.

### **Q10: Can we reduce exception in overriding?**
**Answer**: Yes, can reduce or remove, but cannot add new checked exceptions.

---

## 📝 **CODE PATTERNS TO REMEMBER**

### **Singleton Pattern**
```java
class Singleton {
    private static Singleton instance;
    
    private Singleton() { }  // Private constructor
    
    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### **Factory Pattern**
```java
interface Vehicle { void drive(); }
class Car implements Vehicle { public void drive() { } }
class Bike implements Vehicle { public void drive() { } }

class VehicleFactory {
    Vehicle getVehicle(String type) {
        if(type.equals("car")) return new Car();
        else return new Bike();
    }
}
```

### **Builder Pattern**
```java
class Student {
    private String name;
    private int age;
    
    // Builder class
    static class Builder {
        private String name;
        private int age;
        
        Builder name(String name) { this.name = name; return this; }
        Builder age(int age) { this.age = age; return this; }
        Student build() { return new Student(this); }
    }
}
```

---

## 🎯 **EXAM TIPS**

### **Last Minute Revision**
1. **4 OOP Pillars**: A PIE
2. **Inheritance Types**: Single, Multi, Hierarchical, Hybrid
3. **Polymorphism Types**: Overloading (compile), Overriding (runtime)
4. **Access Modifiers**: Public > Protected > Default > Private
5. **Static vs Instance**: Class-level vs Object-level
6. **Abstract vs Interface**: When to use which
7. **super vs this**: Parent vs Current object
8. **Shallow vs Deep Copy**: Shared vs Independent

### **Common Output Questions**
```java
// Question 1
class A { void show() { System.out.print("A"); } }
class B extends A { void show() { System.out.print("B"); } }
A obj = new B();
obj.show();  // Output: B (Runtime polymorphism)

// Question 2
class C { static void display() { System.out.print("C"); } }
class D extends C { static void display() { System.out.print("D"); } }
C obj = new D();
obj.display();  // Output: C (Method hiding, not overriding)
```

### **Memory Diagrams**
```
Student Class
┌─────────────────┐
│ name: String    │
│ age: int        │
│ school: static  │
├─────────────────┤
│ display()       │
│ static show()   │
└─────────────────┘
    ↑
    | new Student()
    ↓
Heap Memory
┌─────────────────┐
│ Student Object  │
│ name = "John"   │
│ age = 20        │
│ school → "ABC"  │
└─────────────────┘
```

---

## 🏆 **FINAL CHEAT SHEET**

### **Keywords Summary**
| Keyword | Purpose | Example |
|---------|---------|---------|
| `class` | Define class | `class A { }` |
| `new` | Create object | `A obj = new A();` |
| `extends` | Inheritance | `class B extends A { }` |
| `implements` | Interface | `class C implements I { }` |
| `abstract` | Abstract class/method | `abstract class D { }` |
| `interface` | Interface | `interface I { }` |
| `static` | Class-level | `static int count;` |
| `final` | Cannot change | `final int MAX = 100;` |
| `super` | Parent reference | `super.method();` |
| `this` | Current object | `this.variable = value;` |
| `private` | Class access | `private int x;` |
| `public` | Anywhere access | `public void show() { }` |
| `protected` | Package + subclass | `protected int y;` |

### **Rules Summary**
1. **One class = One file** (public class)
2. **main() method** = public static void main(String[] args)
3. **Constructor name** = Class name, no return type
4. **Overloading** = Same class, same name, different parameters
5. **Overriding** = Parent-child, same signature
6. **Abstract method** = No body, class must be abstract
7. **Interface method** = Public abstract by default
8. **Static method** = Cannot use `this` or `super`
9. **Final variable** = Constant, cannot change
10. **Private members** = Not inherited

### **Common Interview Acronyms**
- **OOP**: Object-Oriented Programming
- **API**: Application Programming Interface
- **JVM**: Java Virtual Machine
- **JDK**: Java Development Kit
- **JRE**: Java Runtime Environment
- **SOLID**: OOP design principles
- **A PIE**: OOP pillars (Abstraction, Polymorphism, Inheritance, Encapsulation)

---

## 🌟 **PRO TIPS FOR EXAMS**

### **Before Exam**
1. **Revise** all keywords and their usage
2. **Practice** output questions
3. **Draw** memory diagrams for complex problems
4. **Memorize** difference tables

### **During Exam**
1. **Read questions twice** before answering
2. **Check** for trick questions (static, final, private)
3. **Remember**: Java is case-sensitive
4. **Default values**: int=0, String=null, boolean=false

### **For Coding Questions**
```java
// Always start with structure
public class ClassName {
    // Variables
    // Constructor
    // Methods
    // main method (if needed)
    
    public static void main(String[] args) {
        // Test code
    }
}
```

---

## ✅ **QUICK CHECKLIST**

### **Must Know For Exam**
- [ ] 4 OOP pillars
- [ ] Inheritance types
- [ ] Polymorphism types
- [ ] Access modifiers hierarchy
- [ ] Abstract vs Interface
- [ ] Static vs Instance
- [ ] super vs this
- [ ] Overloading vs Overriding rules
- [ ] Constructor rules
- [ ] Exception handling basics

---

## 🎉 **FINAL ENCOURAGEMENT**

**Remember**:
1. **Understand concepts**, don't just memorize
2. **Practice coding** - it's a practical subject
3. **Read questions carefully** in exam
4. **You know more than you think!**

**Good luck! You've got this! 🍀**