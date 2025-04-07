package dam.ad.ad07_te01_01.controller;

import dam.ad.ad07_te01_01.domain.Category;
import dam.ad.ad07_te01_01.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import dam.ad.ad07_te01_01.domain.Book;
import dam.ad.ad07_te01_01.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/books")
public class BookController {
    
    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    CategoryRepository categoryRepository;
    
    // Obtiene todos los libros
    @GetMapping({"/", ""})
    public ResponseEntity<List<Book>> index() {
        try {
            // Recupera todos los libros de la base de datos
            List<Book> books = bookRepository.findAll();
            
            // Retorna la lista de libros con el estado HTTP 200 (OK)
            return new ResponseEntity<>(books, HttpStatus.OK);
            
        } catch (Exception ex) {
            // Imprime el stack trace y se devuelve un error 500
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Obtiene un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> show(@PathVariable("id") Long id) {
        try {
            // Busca el libro por su ID
            Optional<Book> book = bookRepository.findById(id);
            
            if (book.isPresent()) {
                // Si el libro existe, lo devuelve con el estado HTTP 200 (OK)
                return new ResponseEntity<>(book.get(), HttpStatus.OK);
                
            } else {
                // Si no se encuentra el libro, retorna un error 404 (NOT FOUND)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            // Imprime el stack trace y se devuelve un error 500
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Crea un nuevo libro
    @PostMapping({"/", ""})
    public ResponseEntity<Book> create(@RequestBody Book book) {
        try {
            // Si el libro tiene una categoría con un id, lo buscamos en la base de datos
            if (book.getCategory() != null && book.getCategory().getId() != null) {
                Optional<Category> category = categoryRepository.findById(book.getCategory().getId());
                
                if (category.isPresent()) {
                    // Asocia la categoría al libro
                    book.setCategory(category.get());
                } else {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            }
            
            // Guarda el nuevo libro en la base de datos
            Book savedBook = bookRepository.save(book);
            
            // Retorna el libro creado con el estado HTTP 201 (CREATED)
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
            
        } catch (Exception ex) {
            // Imprime el stack trace y se devuelve un error 500
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Actualiza un libro existente
    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable("id") Long id) {
        try {
            // Busca el libro a actualizar por su ID
            Optional<Book> tempBook = bookRepository.findById(id);
            
            if (tempBook.isPresent()) {
                // Si el libro existe, actualiza sus atributos y guarda los cambios
                Book existingBook = tempBook.get();
                existingBook.setTitle(book.getTitle());
                existingBook.setAuthor(book.getAuthor());
                existingBook.setReadDate(book.getReadDate());
                existingBook.setRating(book.getRating());
                existingBook.setStatus(book.getStatus());
                
                // Si el libro tiene una categoría con un id, lo buscamos en la base de datos
                if (book.getCategory() != null && book.getCategory().getId() != null) {
                    Optional<Category> category = categoryRepository.findById(book.getCategory().getId());
                    
                    if (category.isPresent()) {
                        // Asocia la categoría al libro
                        existingBook.setCategory(category.get());
                    } else {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                    }
                }
                
                // Guarda el libro actualizado y lo devuelve con el estado HTTP 200 (OK)
                Book updatedBook = bookRepository.save(existingBook);
                return new ResponseEntity<>(updatedBook, HttpStatus.OK);
                
            } else {
                // Si no se encuentra el libro, retorna un error 404 (NOT FOUND)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            // Imprime el stack trace y se devuelve un error 500
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Elimina un libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            // Verifica si el libro existe antes de intentar eliminarlo
            if (bookRepository.existsById(id)) {
                
                // Si existe, elimina el libro y retorna el estado HTTP 204 (NO CONTENT)
                bookRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                
            } else {
                // Si no se encuentra el libro, retorna un error 404 (NOT FOUND)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            // Imprime el stack trace y se devuelve un error 500
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
