package dam.ad.ad07_te01_01.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
public class Category implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // Identificador único de la categoría
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Nombre de la categoría
    private String name;
    
    // Relación OneToMany con la entidad Book
    // Una categoría puede tener múltiples libros
    @JsonManagedReference
    @OneToMany (mappedBy = "category", cascade = CascadeType.ALL) // Propaga la eliminación de la categoría a los libros asociados
    List<Book> books = new ArrayList<>();
    
    
    // CONSTRUCTORES
    // Constructor por defecto
    public Category() {}
    
    // Constructor para inicializar con el atributo name
    public Category(String name) {
        this.name = name;
    }
    
    // Constructor para inicializar todos los atributos de la categoría
    public Category(List<Book> books) {
        super();
        this.books = books;
    }
    
    
    // GETTERS / SETTERS
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
    public void addBook(Book book) {
        if (this.books != null) {
            this.books.add(book);
        }
    }
}
