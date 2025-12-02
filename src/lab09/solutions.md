# Java Generics – Laboratory Solutions
## Solutions & Hints

These are model answers / reference implementations for the lab on Java Generics, Wildcards, and PECS.

---

## Exercise 1 – Fix the errors

Code:

```java
List<? extends Number> nums = new ArrayList<Integer>();
nums.add(10); // Why is this forbidden?
Number n = nums.get(0); // Why is this allowed?
```

Explanation:

- `List<? extends Number>` means: a list of **some unknown subtype** of `Number`.  
  It could be `List<Integer>`, `List<Double>`, `List<Long>`, etc.
- The compiler does not know which one, so it **cannot safely allow insertion** of a concrete subtype, because it might be wrong.

Why `nums.add(10)` is forbidden:

- Suppose at runtime `nums` actually refers to `List<Double>`.  
  Adding an `Integer` would violate type safety.
- Therefore, the compiler forbids any `add` except `null`.

Why `Number n = nums.get(0)` is allowed:

- Whatever subtype the list holds (`Integer`, `Double`, etc.), it is **at least** a `Number`.
- So reading an element and treating it as `Number` is safe.

This is **Producer Extends**: you can safely **read/produce** elements, but not **add/consume** them.

---

## Exercise 2 – Implement a Generic Pair

Usage:

```java
Pair<String, Integer> p = new Pair<>("Age", 20);
```

Implementation:

```java
public class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair{" +
               "key=" + key +
               ", value=" + value +
               '}';
    }
}
```

---

## Exercise 3 – Generic Method `choose`

Requirement: return whichever parameter is not `null`.

```java
public class Utils {

    public static <T> T choose(T a, T b) {
        if (a != null) {
            return a;
        }
        return b;
    }
}
```

Possible improvements:

- If both are null, returns null.
- If both non-null, you could decide to always prefer `a` or throw an exception, depending on your design.

---

## Exercise 4 – PECS with Animals

Given:

```java
class Animal {
    @Override
    public String toString() {
        return "Animal";
    }
}

class Cat extends Animal {
    @Override
    public String toString() {
        return "Cat";
    }
}

class Dog extends Animal {
    @Override
    public String toString() {
        return "Dog";
    }
}
```

### 4.1 `List<? extends Animal>`

```java
List<? extends Animal> animals = new ArrayList<Cat>();
// animals.add(new Cat()); // ERROR
// animals.add(new Dog()); // ERROR
Animal a = animals.get(0); // OK
```

Reason:

- `? extends Animal` = list produces `Animal`s (you can safely read).
- The compiler doesn’t know if it’s `List<Cat>`, `List<Dog>`, etc., so no adding.
- But every element is at least an `Animal`, so reading is safe.

### 4.2 `List<? super Cat>`

```java
List<? super Cat> cats = new ArrayList<Animal>();
cats.add(new Cat()); // OK
// Dog d = cats.get(0); // ERROR
Object o = cats.get(0); // OK (only safe type)
```

Reason:

- `? super Cat` = list can consume `Cat` (and its subclasses).
- We can safely add `Cat` because the list is guaranteed to hold `Cat` or a supertype (`Animal`, `Object`).
- But we can only read as `Object`, because the elements’ actual types could be supertype of `Cat`.

### 4.3 Summary

- `? extends T` → Producer: you can **get** T (or supertype), but not **put**.
- `? super T` → Consumer: you can **put** T, but can only **get** Object.

PECS: **Producer Extends, Consumer Super**.

---

## Exercise 5 – Sort Any List

Method:

```java
public static <T extends Comparable<? super T>> void sort(List<T> list) {
    // You can either call Collections.sort or implement a simple sort:
    Collections.sort(list);
}
```

Why `Comparable<? super T>`?

- `T` must be comparable to itself or to one of its supertypes.
- Example: class `Integer` implements `Comparable<Integer>`, but in advanced designs you might have:
  `class MyNumber implements Comparable<Number>`.
- `Comparable<? super T>` allows more flexibility, so that if `T` is a subclass, it can still use a comparable implemented for a supertype.

---

## Exercise 6 – Type Erasure Explanation

Code:

```java
if (list instanceof List<String>) {}
```

is illegal because of **type erasure**.

Reason:

- At runtime, Java erases generic types.
- `List<String>` and `List<Integer>` both become just `List`.
- JVM cannot distinguish `List<String>` from `List<Integer>`.
- Therefore, `instanceof List<String>` is not allowed; you can only use `instanceof List`.

Correct usage:

```java
if (list instanceof List) {
    // allowed, but not about the type parameter
}
```

---

## Exercise 7 – NumberBox

Extend `Box<T>` so it only accepts subclasses of `Number`.

```java
class NumberBox<T extends Number> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public double doubleValue() {
        if (value == null) {
            throw new IllegalStateException("No value set");
        }
        return value.doubleValue();
    }
}
```

Usage:

```java
NumberBox<Integer> b = new NumberBox<>();
b.set(10);
double d = b.doubleValue(); // 10.0
```

---

## Exercise 8 – Wildcard Capture

Code:

```java
List<?> items = new ArrayList<String>();
items.set(0, "Hello"); // error
```

Why error?

- `List<?>` is a list of unknown type.
- The compiler does not know if it is `List<String>` or `List<Integer>` or something else.
- You cannot safely write any non-null element.

Fix with wildcard capture:

```java
public class Lists {

    public static <T> void fill(List<T> list, T value) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, value);
        }
    }
}
```

Usage:

```java
List<String> names = new ArrayList<>();
names.add("A");
names.add("B");
Lists.fill(names, "Same");
```

Here, `T` is captured when calling `fill`, and the compiler knows that `T` is `String`.

---

## Exercise 9 – Safe Supplier and Consumer

Interfaces:

```java
interface Supplier<T> {
    T get();
}

interface Consumer<T> {
    void accept(T t);
}
```

### 9.1 Supplier

Method:

```java
public static void printNumber(Supplier<? extends Number> supplier) {
    Number n = supplier.get(); // Safe
    System.out.println("Number: " + n);
}
```

Usage:

```java
Supplier<Integer> intSupplier = () -> 42;
printNumber(intSupplier); // OK
```

### 9.2 Consumer

Method:

```java
public static void feedIntegers(Consumer<? super Integer> consumer) {
    consumer.accept(10);
    consumer.accept(20);
}
```

Usage:

```java
Consumer<Number> numberConsumer = n -> System.out.println("Consumed: " + n);
feedIntegers(numberConsumer); // OK
```

Explanation:

- `Supplier<? extends Number>` → Producer of Number. We **get** values.
- `Consumer<? super Integer>` → Consumer that can accept Integers. We **put** values.

---

## Exercise 10 – Covariant and Contravariant API (AnimalShelter)

Example design:

```java
class AnimalShelter<T extends Animal> {
    private final List<T> animals = new ArrayList<>();

    // Shelter "consumes" T
    public void addAnimal(T animal) {
        animals.add(animal);
    }

    // Shelter "produces" T
    public T getAnimal(int index) {
        return animals.get(index);
    }

    // Method using PECS
    public void addAllFrom(List<? extends T> source) {
        // source produces T
        animals.addAll(source);
    }

    public void moveAllTo(List<? super T> destination) {
        // destination consumes T
        destination.addAll(animals);
        animals.clear();
    }
}
```

Explanation:

- The shelter **stores** animals of type `T`.
- `addAllFrom(List<? extends T>)` uses `extends` because `source` is a **producer** of `T`. We read from `source`.
- `moveAllTo(List<? super T>)` uses `super` because `destination` is a **consumer** of `T`. We write to `destination`.

---

## Optional Challenge – Generic Repository

Minimal implementation:

```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Repository<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> find(Predicate<? super T> predicate) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (predicate.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    public void removeIf(Predicate<? super T> predicate) {
        items.removeIf(predicate);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }
}
```

Where PECS?

- `Predicate<? super T>` is a **consumer** of `T` → `? super T`.

---

## Summary of the Key Ideas

- `? extends T` → read (producer). No safe writes.
- `? super T` → write (consumer). Reads only as `Object`.
- Type erasure explains why no `instanceof List<String>`.
- Bounded generics (`<T extends Number>`) restrict acceptable types.
- PECS = “Producer Extends, Consumer Super” is your rule of thumb.

*End of Solutions.*