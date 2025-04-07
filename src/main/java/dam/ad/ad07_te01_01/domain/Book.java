package dam.ad.ad07_te01_01.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="book")
public class Book implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    // Identificador único del libro
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Título del libro
    private String title;
    
    // Autor del libro
    private String author;
    
    // Fecha de lectura del libro
    @Column(name="read_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate readDate; // Fecha de lectura
    
    // Valoración del libro
    private Integer rating;
    
    // Estado del libro
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    
    // Enumeración que define los posibles estados de un libro
    public enum BookStatus {
        TO_READ, CURRENTLY_READING, READ;
    }
    
    // Relación ManyToOne con la entidad Category
    // Un libro puede tener una sola categoría
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    
    // Devuelve el nombre de la categoría a la que pertenece el libro
    @JsonProperty("category")
    public String getCategoryName() {
        return category != null ? category.getName() : null;
    }
    
    // Devuelve el nombre del estado del libro formateado
    @JsonProperty("status")
    public String getStatusName() {
        return status.name().replace("_", " ");
    }
    
    
    // CONSTRUCTORES
    // Constructor por defecto
    public Book() {}
    
    // Constructor para inicializar todos los atributos del libro
    public Book(
            String title, String author, LocalDate readDate, Integer rating, BookStatus status, Category category) {
        this.title = title;
        this.author = author;
        this.readDate = readDate;
        this.rating = rating;
        this.status = status;
        this.category = category;
    }
    
    
    // GETTERS / SETTERS
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public LocalDate getReadDate() {
        return readDate;
    }
    
    public void setReadDate(LocalDate readDate) {
        this.readDate = readDate;
    }
    
    public Integer getRating() {
        return rating;
    }
    
    public void setRating(Integer rating) {
        this.rating = rating;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public BookStatus getStatus() {
        return status;
    }
    
    public void setStatus(BookStatus status) {
        this.status = status;
    }
}
