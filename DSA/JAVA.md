## 1. Encapsulation

**Wrapping data and methods together and restricting direct access.**

```java
class Student {
    private String name; // private data

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class EncapsulationDemo {
    public static void main(String[] args) {
        Student s = new Student();

        s.setName("John");
        System.out.println(s.getName());
    }
}
```

---

## 2. Abstraction

**Hiding implementation details and showing only essential functionality.**

```java
abstract class Animal {
    abstract void sound();
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.sound();
    }
}
```

---

## 3. Inheritance

**One class acquires properties and behavior from another class.**

```java
class Animal {
    void eat() {
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Dog d = new Dog();

        d.eat();   // inherited method
        d.bark();
    }
}
```

---

## 4. Polymorphism

**One method behaves differently for different objects.**

```java
class Animal {
    void sound() {
        System.out.println("Animal sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("Cat meows");
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        Animal a1 = new Dog();
        Animal a2 = new Cat();

        a1.sound();
        a2.sound();
    }
}
```

### Easy Memory Trick

* **Encapsulation** → **Data Hiding** (`private`)
* **Abstraction** → **Hide Details** (`abstract`)
* **Inheritance** → **Reuse Code** (`extends`)
* **Polymorphism** → **Many Forms** (method overriding)
