package dam.ad.ad07_te01_01.repository;

import dam.ad.ad07_te01_01.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Busca libros por el nombre de la categoría
    List<Book> findByCategory_Name(String name);
}
