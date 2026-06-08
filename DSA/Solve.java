// ==================== ENCAPSULATION ====================

class BankAccount {

    // Private data member - cannot be accessed directly outside the class
    private double balance;

    // Public method to modify balance
    // (This behaves more like deposit() than setBalance())
    public void setBalance(double amount) {
        balance = balance + amount;
    }

    // Public method to access balance
    public double getBalance() {
        return balance;
    }
}

public class Solve {
    public static void main(String[] args) {

        BankAccount ba = new BankAccount();

        System.out.println("Previous Balance: " + ba.getBalance());

        ba.setBalance(4000);

        System.out.println("Current Balance: " + ba.getBalance());
    }
}

// ==================== ABSTRACTION ====================

// Abstract class
abstract class Car {

    // Abstract method (no implementation)
    abstract void start();

    // Concrete method (has implementation)
    void stop() {
        System.out.println("Car is about to stop...");
    }
}

class BMW extends Car {

    // Implementation of abstract method
    @Override
    void start() {
        System.out.println("BMW is about to start...");
    }
}

public class Solve {
    public static void main(String[] args) {

        // Upcasting
        Car car = new BMW();

        car.start();
        car.stop();
    }
}
// ==================== INHERITANCE ====================

// Parent Class
class Animal {

    void eat() {
        System.out.println("Eating...");
    }
}

// Child Class
class Dog extends Animal {

    void bark() {
        System.out.println("Barking...");
    }
}

// Another Child Class
class Cat extends Animal {

    void meow() {
        System.out.println("Meow Meow...");
    }
}

public class Solve {
    public static void main(String[] args) {

        Dog d = new Dog();
        d.eat();   // inherited method
        d.bark();  // own method

        Cat c = new Cat();
        c.eat();   // inherited method
        c.meow();  // own method
    }
}
// ==================== POLYMORPHISM ====================
// Compile-Time Polymorphism (Method Overloading)

class Calculator {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

public class Solve {
    public static void main(String[] args) {

        Calculator c = new Calculator();

        System.out.println(c.add(10, 20));
        System.out.println(c.add(10, 20, 30));
    }
}
// ==================== POLYMORPHISM ====================
// Runtime Polymorphism (Method Overriding)

class Animal {

    void sound() {
        System.out.println("Animal makes sound...");
    }
}

class Dog extends Animal {

    @Override
    void sound() {
        System.out.println("Dog Barks...");
    }
}

public class Solve {

    public static void main(String[] args) {

        // Upcasting
        Animal d = new Dog();

        // Runtime decides which method to call
        d.sound();
    }
}
// ==================== INTERFACE ====================

// Interface
interface Animal {

    // Abstract methods
    void eat();

    void sound();

    // Default method
    default void sleep() {
        System.out.println("Animal is sleeping");
    }

    // Static method
    static void info() {
        System.out.println("Animals are living beings");
    }
}

// Implementing Interface
class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("Dog is eating...");
    }

    @Override
    public void sound() {
        System.out.println("Dog barks");
    }
}

public class Solve {

    public static void main(String[] args) {

        Dog d = new Dog();

        d.eat();
        d.sound();
        d.sleep();

        // Static interface method
        Animal.info();
    }
}