# Java Generics – Laboratory Sheet
## Universidade Autónoma de Lisboa
## Programming Paradigms – Java Module
## Week X – Generic Types, Wildcards, and PECS

---

## 1. Objectives
In this lab you will:
- understand why generics exist in Java,
- define generic classes and generic methods,
- work with bounded type parameters,
- understand wildcards (`?`),
- learn PECS (Producer Extends, Consumer Super),
- analyse type safety and type erasure,
- practice writing reusable type-safe code.

---

## 2. Introduction – Why Generics?
Java generics allow:
1. Compile-time type checking.
2. Avoiding ClassCastException.
3. Clearer and safer APIs.

Example without generics:
```java
List list = new ArrayList();
list.add("Hello");
Integer x = (Integer) list.get(0); // Runtime crash
```

With generics:
```java
List<String> list = new ArrayList<>();
String x = list.get(0); // Safe
```

---

## 3. Generic Classes
Example:
```java
class Box<T> {
    private T value;
    public void set(T v) { value = v; }
    public T get() { return value; }
}
```

Usage:
```java
Box<Integer> b = new Box<>();
b.set(42);
```

---

## 4. Generic Methods
```java
public <T> void print(T item) {
    System.out.println(item);
}
```

---

## 5. Bounded Type Parameters
Upper bound:
```java
<T extends Number>
```

Lower bound (only with wildcards):
```java
? super Integer
```

---

## 6. Wildcards (`?`)
### 6.1 Unbounded wildcard
```java
List<?> items;
```

### 6.2 Upper bound (`? extends T`)
Covariant. Read safely, cannot add.
```java
List<? extends Animal> list;
```
You can get:
```java
Animal a = list.get(0);
```
You cannot add:
```java
list.add(new Dog());  // error
```

### 6.3 Lower bound (`? super T`)
Contravariant. Can add, reading is Object.
```java
List<? super Dog> list;
list.add(new Dog()); // ok
Object o = list.get(0);
```

---

## 7. PECS – Producer Extends, Consumer Super
Mnemonic:
- Use `extends` when the list **produces** values for you.
- Use `super` when the list **consumes** values from you.

"If you GET, use EXTENDS. If you PUT, use SUPER."

---

## 8. Type Erasure
Java removes generic type information at runtime:
- You cannot use `instanceof List<String>`.
- You cannot create `new T[]`.

---

# 9. Exercises

---

## Exercise 1 – Fix the errors
Explain why the following does not compile:
```java
List<? extends Number> nums = new ArrayList<Integer>();
nums.add(10); // Why is this forbidden?
Number n = nums.get(0); // Why is this allowed?
```

---

## Exercise 2 – Implement a Generic Pair
Use generics for the keys and values:
```java
Pair<String, Integer> p = new Pair<>("Age", 20);
```

Implement:
```java
class Pair<K, V> {
    // TODO: attributes, constructor, getters
}
```

---

## Exercise 3 – Generic Method
Implement a method:
```java
<T> T choose(T a, T b)
```

It returns whichever parameter is not null.

---

## Exercise 4 – PECS
Given:
```java
class Animal {}
class Cat extends Animal {}
class Dog extends Animal {}
```

Tasks:
1. Create `List<? extends Animal>` and show what cannot be added.
2. Create `List<? super Cat>` and show what can be added.
3. Explain why.

---

## Exercise 5 – Sort Any List
Complete:
```java
public static <T extends Comparable<? super T>> void sort(List<T> list)
```

Question:  
Why is `? super T` used instead of `? extends T`?

---

## Exercise 6 – Type Erasure Explanation
Explain why the following is illegal:
```java
if (list instanceof List<String>) {}
```

---

## Exercise 7 – Number Box
Extend Box so that it:
- only accepts subclasses of Number,
- computes the double value of its stored element.

Example:
```java
NumberBox<Integer> b = new NumberBox<>();
b.set(10);
double d = b.doubleValue(); // returns 10.0
```

---

## Exercise 8 – Wildcard Capture
Explain why this does not compile:
```java
List<?> items = new ArrayList<String>();
items.set(0, "Hello"); // error
```

Fix using wildcard capture:
```java
<T> void fill(List<T> list, T value)
```

---

## Exercise 9 – A Safe Supplier and Consumer
Create:
```java
interface Supplier<T> { T get(); }
interface Consumer<T> { void accept(T t); }
```

Then:
1. Implement a method that accepts `Supplier<? extends Number>`.
2. Implement a method that accepts `Consumer<? super Integer>`.
3. Test them.

---

## Exercise 10 – Write a Covariant and Contravariant API
Create an `AnimalShelter<T>` class where:
- it can **provide** animals (`T get()`),
- and **accept** only subclasses safely.

Design the API using wildcards.

---

# 10. Optional Challenge
Implement a generic repository class:
```
Repository<T>
```
with:
- `add(T item)`
- `find(Predicate<T> p)`
- `removeIf(Predicate<T> p)`
- internal storage using `List<T>`

and upgrade it using:
- upper bounds
- lower bounds
- generic methods

Explain where PECS applies.

---

# 11. Deliverables
Submit:
- source files (.java),
- screenshots of successful compilation,
- a short pdf report (max 2 pages) explaining PECS examples.

Evaluation:
- 40% code correctness
- 40% understanding (report)
- 20% coding style and documentation

---

# 12. Extra Reading (Ad-Free)
- Oracle Java Generics Tutorial  
  https://docs.oracle.com/javase/tutorial/java/generics/index.html
- Jenkov Generics  
  https://jenkov.com/tutorials/java-generics
- Effective Java, Chapter “Generics” (Joshua Bloch) – recommended

---

*End of Lab*