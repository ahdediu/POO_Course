# Lab 02 — Projeto Movie Manager
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

Tarefas:
	1.	Criar construtor completo.
	2.	Gerar getters e setters automaticamente (IntelliJ → Code → Generate).
	3.	Adicionar método toString() com o formato:  Título (Ano) - Género - Rating
	4.	(Opcional) Validar rating no setRating() (1–10).
        
## 🗂️ 2. Classe `MovieManager`

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