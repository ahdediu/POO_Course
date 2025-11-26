# Lab: Java Generics (Parametric Polymorphism)

## Objetivos
Neste laboratório os estudantes irão:
- compreender como declarar e usar tipos genéricos;
- trabalhar com classes, interfaces e métodos genéricos;
- explorar wildcards (`?`, `extends`, `super`);
- analisar erros típicos de cast e type-safety;
- criar uma pequena biblioteca genérica inspirada em coleções Java.

------------------------------------------------------------
1. Exercício Base — Classe Genérica Simples
------------------------------------------------------------

1.1. Crie uma classe genérica `Box<T>`

   ``` public class Box<T> {
        private T value;

        public Box(T value) {
            this.value = value;
        }

        public T get() { return value; }
        public void set(T value) { this.value = value; }
    }
```
Tarefas

1. Instancie diferentes caixas:

   Box<String> a = new Box<>("Olá");
   Box<Integer> b = new Box<>(42);

2. Explique porque `Box<int>` não compila (dica: autoboxing e tipos primitivos).

------------------------------------------------------------
2. Classe Genérica com Dois Parâmetros
------------------------------------------------------------

Crie a seguinte classe:
```

    public class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey()   { return key; }
        public V getValue() { return value; }
    }
```

Tarefas

- Crie um `Pair<String, Integer>` para representar um aluno e uma nota.
- Crie um `Pair<Integer, Box<String>>`.
- Explique se o seguinte é válido ou não e porquê:

  Pair<Object, Object> p = new Pair<String, Integer>("A", 10);

------------------------------------------------------------
3. Restrição de Tipos com `extends`
------------------------------------------------------------

Considere:

    class Person { String name; }
    class Actor extends Person { }
    class Director extends Person { }

3.1. Crie uma classe genérica restrita:
```

    public class Registry<T extends Person> {
        private List<T> people = new ArrayList<>();

        public void add(T p) {
            people.add(p);
        }
    }
```

Tarefas

1. Explique porque isto compila:

       Registry<Actor> r1 = new Registry<>();

2. Explique porque isto NÃO compila:

       Registry<String> r2 = new Registry<>();

------------------------------------------------------------
4. Wildcards — `?`, `? extends`, `? super`
------------------------------------------------------------

4.1. Método que imprime nomes:
```

    public static void printPeople(List<? extends Person> list) {
        for (Person p : list) {
            System.out.println(p.name);
        }
    }
```

Tarefas

- Explique porque `List<Actor>` é permitido, mas `List<Object>` não.
- Escreva um método que permita ADICIONAR pessoas a uma lista, usando `? super Person`:

  public static void addDefaultPerson(List<? super Person> list) {
  list.add(new Person());
  }

------------------------------------------------------------
5. Métodos Genéricos
------------------------------------------------------------

5.1. Crie um método que troque dois elementos de um array:
```

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
```

Tarefas

- Teste com arrays de `Integer`, `String` e `Person`.
- Indique onde o operador diamond `<>` é usado quando chama o método (ou não é necessário?).

------------------------------------------------------------
6. Erros Típicos com Generics (Type-Safety)
------------------------------------------------------------

Exemplo com raw types:
```

    List raw = new ArrayList();
    raw.add("Olá");
    raw.add(10);
```

Tarefas

1. Mostre como isto pode gerar um `ClassCastException`:
```

       List<String> wrong = raw;   // warning mas compila
       String s = wrong.get(1);    // boom em runtime
```

2. Reescreva o código sem warnings, usando tipos parametrizados:
```

       List<String> safe = new ArrayList<>();
       safe.add("Olá");
       // safe.add(10);   // não compila
```

Explique porque a versão type-safe é melhor.

------------------------------------------------------------
7. Mini-Projeto — Implementar uma Estrutura Genérica
------------------------------------------------------------

Implemente uma classe `MyList<T>` com:

- `void add(T value)`
- `T get(int index)`
- `T remove(int index)`
- `int size()`

Exemplo de uso:
```

    MyList<Person> lista = new MyList<>();
    lista.add(new Actor());
    lista.add(new Director());
    System.out.println("Tamanho = " + lista.size());
```

Bónus

- Faça `MyList<T>` implementar `Iterable<T>` para poder usar:

       for (Person p : lista) { ... }

- Mostre como poderia aceitar um `MyList<? extends Person>` num método que apenas lê dados.

------------------------------------------------------------
8. Questões de Reflexão
------------------------------------------------------------

1. Porque Java não permite `new T()` numa classe genérica?
2. Porque não existe `List<int>` mas existe `List<Integer>`?
3. Explique a diferença entre:
    - `List<Person>`
    - `List<? extends Person>`
    - `List<? super Person>`
4. Dê um exemplo real onde wildcards resolvem um problema de compatibilidade entre métodos.

------------------------------------------------------------
Fim do laboratório.
------------------------------------------------------------