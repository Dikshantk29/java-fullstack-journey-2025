# 📁 Note.md (Continued)

### ⚡ Enums — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- A special class type that represents a fixed set of constants.
- Introduced in Java 5 to replace integer constants and type-safe enum pattern.
- Key purpose: Define named constants that are type-safe, readable, and can have behavior.
- Enums are implicitly `final` and extend `java.lang.Enum`.

#### 2️⃣ Must-Remember Points
- Enums are classes; each constant is an instance of the enum type.
- Constructors are always `private` (implicitly).
- Constants are `public static final` instances, defined first with semicolon after last constant.
- Can implement interfaces but cannot extend any class (already extends `Enum`).
- Can have fields, methods, constructors, and implement abstract methods per constant.
- `ordinal()` returns declaration position (0-based); avoid relying on it for logic.
- `values()` returns array of all constants in declaration order.
- `valueOf(String)` returns enum constant by name (case-sensitive).
- Use `==` for comparison (thread-safe and fast).

#### 3️⃣ Key Code Patterns (Java)
```java
// Basic enum
enum Day { MONDAY, TUESDAY, WEDNESDAY }

// Enum with fields and constructor
enum Planet {
    MERCURY(3.303e23),
    VENUS(4.869e24);
    private final double mass;
    Planet(double mass) { this.mass = mass; }
    public double getMass() { return mass; }
}

// Enum with constant-specific behavior
enum Operation {
    PLUS { double apply(double x, double y) { return x + y; } },
    MINUS { double apply(double x, double y) { return x - y; } };
    abstract double apply(double x, double y);
}
```

#### 4️⃣ Internals / Behind the Scenes
- Each enum constant is a `static final` instance created when enum class is loaded.
- Compiler generates `values()` and `valueOf()` methods.
- Enums are serializable but have special handling; deserialization won't create new instances.
- Underlying type is `Enum<E extends Enum<E>>` - self-bounded generic.

#### 5️⃣ Common Interview Traps
- Comparing enums with `.equals()` works but `==` is preferred (null-safe with `Enum::equals` handles null).
- Changing enum constant order breaks `ordinal()` dependent code.
- Trying to instantiate enum via `new` keyword (compiler error).
- Forgetting semicolon when enum has fields/methods after constants.

#### 6️⃣ Interview Q&A (High Yield)
- **Q: Why use enums over integer constants?** A: Type safety, compile-time checking, meaningful names, and ability to add behavior.
- **Q: Can enums have constructors?** A: Yes, but they're always private (implicitly) and called once per constant at class loading.
- **Q: Difference between `values()` and `valueOf()`?** A: `values()` returns all constants as array; `valueOf(String)` returns single constant matching name (throws `IllegalArgumentException` if not found).
- **Q: Can enums be extended?** A: No, they're implicitly `final`.
- **Q: When to use `enum` vs `switch`?** A: Use `enum` with `switch` for readable control flow; enums make `switch` exhaustive.
- **Q: Can enums implement interfaces?** A: Yes, and each constant can override interface methods differently.
- **Q: Are enums singleton?** A: Each constant is a singleton instance; the enum type itself controls instance creation.

---

### ⚡ Introduction to Enums — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Pre-Java 5 alternative: `public static final int` constants (type-unsafe, no namespace).
- Enums provide a type-safe way to define fixed sets of named constants.
- They're full-fledged classes that can have fields, methods, and implement interfaces.

#### 2️⃣ Must-Remember Points
- Replaces "int enum pattern" and "typesafe enum pattern" from pre-Java 5.
- Constants are instances of the enum class.
- Implicitly extend `java.lang.Enum`.

#### 6️⃣ Interview Q&A (High Yield)
- **Q: What problem do enums solve?** A: They eliminate magic numbers/strings, provide compile-time type safety, and allow behavior to be attached to constants.

---

### ⚡ Benefits of Enums — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- **Type Safety**: Compiler prevents assigning wrong values.
- **Namespace**: Constants are scoped within enum type.
- **Rich API**: Built-in methods (`values()`, `valueOf()`, `ordinal()`).
- **Behavior Attachment**: Can have methods, fields, and implement interfaces.

#### 2️⃣ Must-Remember Points
- Compile-time checking eliminates runtime errors.
- Can be used in `switch` statements.
- Serializable and comparable by default.
- Singleton nature ensures single instance per constant.

#### 6️⃣ Interview Q&A (High Yield)
- **Q: Three main benefits of enums?** A: 1) Type safety 2) Self-documenting code 3) Ability to add methods and behavior.

---

### ⚡ Defining Enums — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Use `enum` keyword instead of `class`.
- List constants first, separated by commas, ending with semicolon if followed by members.
- Can have constructors, fields, and methods like regular classes.

#### 3️⃣ Key Code Patterns (Java)
```java
enum Status { PENDING, PROCESSING, COMPLETED, FAILED }
// With class body
enum Direction {
    NORTH, SOUTH, EAST, WEST;
    public Direction opposite() { /*...*/ }
}
```

#### 5️⃣ Common Interview Traps
- Forgetting that constants must come before any fields/methods.
- Missing semicolon after constants when members follow.

#### 6️⃣ Interview Q&A (High Yield)
- **Q: What's the syntax difference between enum and class?** A: Use `enum` keyword, list constants first, private constructor only.

---

### ⚡ Enum Constants — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Each constant is a `public static final` instance of the enum type.
- Created when enum class is loaded (like static fields).
- Can have constructor arguments passed in parentheses.

#### 3️⃣ Key Code Patterns (Java)
```java
enum HttpStatus {
    OK(200, "OK"),
    NOT_FOUND(404, "Not Found");
    
    private final int code;
    private final String message;
    
    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
```

#### 5️⃣ Common Interview Traps
- Adding `new` keyword before constants (invalid syntax).
- Passing wrong number/type of arguments to constant.

#### 6️⃣ Interview Q&A (High Yield)
- **Q: Are enum constants objects?** A: Yes, they're singleton instances of the enum class.

---

### ⚡ Enum Methods — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Enums can have instance and static methods.
- Can override methods from `Object` or `Enum` class.
- Each constant can override methods individually.

#### 3️⃣ Key Code Patterns (Java)
```java
enum Coin {
    PENNY(1), NICKEL(5);
    private final int value;
    Coin(int value) { this.value = value; }
    public int getValue() { return value; }
    @Override
    public String toString() { return name() + ": " + value + "¢"; }
}
```

#### 5️⃣ Common Interview Traps
- Forgetting that `name()` and `ordinal()` are `final` in `Enum` class (cannot override).

---

### ⚡ values(), valueOf(), ordinal() — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- `values()`: Static method returning array of all constants in declaration order.
- `valueOf(String)`: Static method returning constant with given name.
- `ordinal()`: Instance method returning position (0-based) in declaration.

#### 3️⃣ Key Code Patterns (Java)
```java
Day[] days = Day.values(); // [MONDAY, TUESDAY...]
Day monday = Day.valueOf("MONDAY"); // Day.MONDAY
int pos = Day.MONDAY.ordinal(); // 0
```

#### 5️⃣ Common Interview Traps
- Relying on `ordinal()` for business logic (breaks if constant order changes).
- `valueOf()` throws `IllegalArgumentException` if name doesn't match exactly.

#### 6️⃣ Interview Q&A (High Yield)
- **Q: Are `values()` and `valueOf()` compiler-generated?** A: Yes, they're added by compiler, not in `Enum` base class.
- **Q: When does `valueOf()` throw exception?** A: When string doesn't match any constant name (case-sensitive).

---

### ⚡ Custom Methods in Enums — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Add instance methods for behavior common to all constants.
- Can add abstract method that each constant must implement.
- Can add static utility methods.

#### 3️⃣ Key Code Patterns (Java)
```java
enum MathOperation {
    ADD { public int apply(int a, int b) { return a + b; } },
    SUBTRACT { public int apply(int a, int b) { return a - b; } };
    public abstract int apply(int a, int b);
}
```

#### 5️⃣ Common Interview Traps
- Adding abstract method without implementing it in every constant.
- Trying to add non-final instance variables when constants should be immutable.

---

### ⚡ Enums in Switch Statements — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Enums work naturally with `switch` statements (Java 5+).
- Case labels are enum constant names (unqualified).
- Exhaustive switching helps catch missing cases at compile-time.

#### 3️⃣ Key Code Patterns (Java)
```java
Day day = Day.MONDAY;
switch(day) {
    case MONDAY: 
        System.out.println("Start of week");
        break;
    case FRIDAY:
        System.out.println("Almost weekend");
        break;
    default:
        System.out.println("Midweek");
}
```

#### 5️⃣ Common Interview Traps
- Qualifying constant with enum name in case label (`case Day.MONDAY:` is wrong).
- Forgetting `break` leads to fall-through (but sometimes intentional).

#### 6️⃣ Interview Q&A (High Yield)
- **Q: Why are enums good for switch?** A: Compiler checks for exhaustiveness, and cases are clear (no magic numbers).

---

### ⚡ Enums with Fields, Constructors & Methods — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Enums can have instance fields (usually `final`).
- Constructor is `private` (explicitly or implicitly).
- Can have getters and other instance methods.

#### 3️⃣ Key Code Patterns (Java)
```java
enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L");
    
    private final String abbreviation;
    
    private Size(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    
    public String getAbbreviation() {
        return abbreviation;
    }
}
```

#### 5️⃣ Common Interview Traps
- Making constructor `public` or `protected` (compiler error).
- Non-final fields can lead to mutable enum constants (generally bad practice).

---

### ⚡ Overriding Methods in Enums — Fast Revision

#### 1️⃣ Core Idea (In 3–4 Lines)
- Each enum constant can override methods individually.
- Useful for constant-specific behavior.
- Override `toString()` for custom string representation.

#### 3️⃣ Key Code Patterns (Java)
```java
enum Logger {
    FILE {
        public void log(String msg) { /* write to file */ }
    },
    CONSOLE {
        public void log(String msg) { System.out.println(msg); }
    };
    public abstract void log(String msg);
}
```

#### 5️⃣ Common Interview Traps
- Cannot override `name()`, `ordinal()`, or `final` methods from `Enum`.
- Forgetting to make method abstract if constants must implement it.

#### 6️⃣ Interview Q&A (High Yield)
- **Q: Can enum constants have different behavior?** A: Yes, by overriding methods per constant or using abstract method pattern.

---

## 🚀 Final Java Full-Stack Interview Rapid Revision
**Top Do's & Don'ts for Enums:**
- **DO** use `==` for enum comparison (it's safe and fast).
- **DO** make enum fields `final` for immutability.
- **DON'T** rely on `ordinal()` for business logic.
- **DON'T** use `new` with enums; they're instantiated automatically.

**Golden Rules for Enums:**
1. **Type Safety:** Enums eliminate magic numbers and strings.
2. **Singleton Constants:** Each constant is a single instance.
3. **Full Classes:** Can have fields, methods, constructors, and implement interfaces.
4. **Switch Friendly:** Perfect for exhaustive switch statements.

**Last-Minute Reminders:**
- `values()` returns all constants in declaration order.
- `valueOf(String)` is case-sensitive and throws exception if not found.
- Constructors are always private (even if not explicitly written).
- Enums extend `java.lang.Enum` (single inheritance already used).

**Things Interviewers LOVE to Ask About Enums:**
1. Difference between enums and integer constants.
2. How to add behavior to enums (fields, methods).
3. Can enums have constructors? Are they public?
4. Explain constant-specific behavior with example.
5. Why use `==` instead of `.equals()` for enums?