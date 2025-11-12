# Lab 07 — Projeto Movie Manager
**Objetivo:**  
Implementar uma versão funcional do sistema *Movie Manager*, aplicando **Programação Orientada a Objetos (POO)**, 
**encapsulamento**, e **coleções** (`ArrayList`).

---

## O Que Vamos Fazer

Nesta aula, vamos:
- Criar as classes principais do sistema;
- Aplicar boas práticas de POO;
- Testar cada classe separadamente (JUnit);
- Usar dados de teste reais.

---

## Estrutura do Projeto

O projeto deve conter **3 classes principais**:

| Classe | Responsabilidade | Observações |
|--------|------------------|-------------|
| `Movie` | Representa um filme | Contém os dados (atributos) |
| `MovieManager` | Gere uma lista de filmes | Contém lógica de negócio |
| `MovieApp` | Interface textual (menu) | Apenas interação com o utilizador |

---

## 1. Classe `Movie`

**Atributos sugeridos:**
```java
private String title;
private int year;
private String genre;
private int rating; // 1–10
```

Tarefas:
1.	Criar construtor completo.
2.	Gerar getters e setters automaticamente (IntelliJ → Code → Generate).
3.	Adicionar método toString() com o formato:  Título (Ano) - Género - Rating
4.	(Opcional) Validar rating no setRating() (1–10).
        
##  2. Classe `MovieManager`

**Atributo sugerido:**
```java
private ArrayList<Movie> movies = new ArrayList<>();
```

**Tarefas:**
- `addMovie(Movie m)` — adiciona um filme se não existir um com o mesmo título e ano.
- `searchByTitle(String part)` — devolve todos os filmes cujo título contenha o texto.
- `listAll()` — devolve todos os filmes.
- `updateRating(String title, int year, int rating)` — altera o rating.
- `remove(String title, int year)` — apaga o filme.

⚙️ Para saber se um filme já existe, cria um método auxiliar `exists(String title, int year)`.

---

## 3. Classe `MovieApp`

Esta é a **interface em consola**.  
Cria um menu simples com `Scanner` e opções:

```
--- Movie Manager ---
1. Adicionar filme
2. Listar filmes
3. Procurar por título
4. Atualizar rating
5. Apagar filme
0. Sair
```

💡 Dica:  
Cada opção chama um método estático auxiliar, por exemplo:
```java
addMovie(Scanner in, MovieManager manager);
searchByTitle(Scanner in, MovieManager manager);
```
---
### 🧩 Exemplo prático com `Scanner`

```java
import java.util.Scanner;

public class MovieApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MovieManager manager = new MovieManager();
        int option;

        do {
            System.out.println("\n--- Movie Manager ---");
            System.out.println("1. Adicionar filme");
            System.out.println("2. Listar filmes");
            System.out.println("3. Procurar por título");
            System.out.println("4. Atualizar rating");
            System.out.println("5. Apagar filme");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            option = in.nextInt();
            in.nextLine(); // limpa o buffer

            switch (option) {
                case 1 -> {
                    System.out.print("Título: ");
                    String title = in.nextLine();
                    System.out.print("Ano: ");
                    int year = in.nextInt();
                    in.nextLine();
                    System.out.print("Género: ");
                    String genre = in.nextLine();
                    System.out.print("Rating (1–10): ");
                    int rating = in.nextInt();

                    Movie m = new Movie(title, year, genre, rating);
                    if (manager.addMovie(m))
                        System.out.println("Filme adicionado com sucesso!");
                    else
                        System.out.println("Filme já existe!");
                }
                case 2 -> manager.listAll().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Parte do título: ");
                    String part = in.nextLine();
                    manager.searchByTitle(part).forEach(System.out::println);
                }
                case 0 -> System.out.println("A sair...");
                default -> System.out.println("Opção inválida!");
            }
        } while (option != 0);
    }
}
```
---

## 4. Testes (JUnit)

Cria uma classe de testes para cada classe:
- `MovieTest`
- `MovieManagerTest`

### Exemplo — `MovieManagerTest`

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieManagerTest {

    @Test
    public void testAddMovie() {
        MovieManager m = new MovieManager();
        Movie a = new Movie("Inception", 2010, "Sci-Fi", 9);
        assertTrue(m.addMovie(a));
        assertFalse(m.addMovie(a)); // duplicado
    }

    @Test
    public void testSearchByTitle() {
        MovieManager m = new MovieManager();
        m.addMovie(new Movie("Inception", 2010, "Sci-Fi", 9));
        m.addMovie(new Movie("Interstellar", 2014, "Sci-Fi", 10));
        assertEquals(2, m.searchByTitle("In").size());
        assertEquals(1, m.searchByTitle("stellar").size());
    }
}
```

---

## 5. Dados de Teste

Sugestão para os primeiros filmes a inserir:

| Título | Ano | Género | Rating |
|--------|------|--------|--------|
| Inception | 2010 | Sci-Fi | 9 |
| Interstellar | 2014 | Sci-Fi | 10 |
| Oppenheimer | 2023 | Drama | 8 |
| The Matrix | 1999 | Action | 9 |
| Arrival | 2016 | Sci-Fi | 8 |

## 🧪 6. Testes (JUnit) — Garantir que o nosso código funciona

Nesta fase não queremos “adivinhar” se o código funciona.  
Vamos escrever **testes automáticos** para verificar o comportamento das classes.

A ideia é simples:
- Cada teste verifica **um cenário concreto**.
- Se algo estiver errado, o teste falha e sabemos onde olhar.

Vamos criar duas classes de teste:

- `MovieTest`
- `MovieManagerTest`

### 6.1 Testes para `Movie`

O que faz sentido testar?

- Se o construtor guarda corretamente os valores.
- Se `setRating` atualiza o rating.
- Se `toString()` devolve algo no formato esperado.

Exemplo:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    @Test
    public void testConstructorAndGetters() {
        Movie m = new Movie("Inception", 2010, "Sci-Fi", 9);
        assertEquals("Inception", m.getTitle());
        assertEquals(2010, m.getYear());
        assertEquals("Sci-Fi", m.getGenre());
        assertEquals(9, m.getRating());
    }

    @Test
    public void testSetRating() {
        Movie m = new Movie("Arrival", 2016, "Sci-Fi", 8);
        m.setRating(10);
        assertEquals(10, m.getRating());
    }
}
```

### 6.2 Testes para `MovieManager`

Aqui testamos a **lógica de gestão**:

- Adicionar filmes (sem duplicados).
- Procurar por parte do título.
- Atualizar rating.
- Remover filmes.

Exemplo:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class MovieManagerTest {

    @Test
    public void testAddMovieAndNoDuplicates() {
        MovieManager mm = new MovieManager();
        Movie m1 = new Movie("Inception", 2010, "Sci-Fi", 9);

        assertTrue(mm.addMovie(m1));          // primeiro deve conseguir
        assertFalse(mm.addMovie(m1));         // duplicado não deve entrar
        assertEquals(1, mm.listAll().size()); // continua só 1
    }

    @Test
    public void testSearchByTitle() {
        MovieManager mm = new MovieManager();
        mm.addMovie(new Movie("Inception", 2010, "Sci-Fi", 9));
        mm.addMovie(new Movie("Interstellar", 2014, "Sci-Fi", 10));

        List<Movie> result = mm.searchByTitle("in");
        assertEquals(2, result.size());

        result = mm.searchByTitle("stellar");
        assertEquals(1, result.size());
    }

    @Test
    public void testUpdateRating() {
        MovieManager mm = new MovieManager();
        mm.addMovie(new Movie("Matrix", 1999, "Action", 9));

        boolean ok = mm.updateRating("Matrix", 1999, 7);
        assertTrue(ok);
        assertEquals(7, mm.listAll().get(0).getRating());

        // filme inexistente
        assertFalse(mm.updateRating("Unknown", 2000, 5));
    }

    @Test
    public void testRemove() {
        MovieManager mm = new MovieManager();
        mm.addMovie(new Movie("Arrival", 2016, "Sci-Fi", 8));
        assertEquals(1, mm.listAll().size());

        boolean removed = mm.remove("Arrival", 2016);
        assertTrue(removed);
        assertEquals(0, mm.listAll().size());
    }
}
```

### 6.3 O que pedimos aos estudantes

- Criar e completar `MovieTest` e `MovieManagerTest`.
- Cada método de teste deve:
    - Preparar o cenário (criar objetos),
    - Chamar o método a testar,
    - Verificar o resultado com `assertEquals`, `assertTrue`, etc.
- Não é preciso testar tudo — mas os casos principais **devem** ser cobertos.

> Estes testes ajudam-nos a confiar no nosso código antes de avançar para versões mais complexas (ficheiros, GUI, etc.).