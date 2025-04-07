package dam.ad.ad07_te01_01.repository;

import dam.ad.ad07_te01_01.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
