package dam.ad.ad07_te01_01.controller;

import dam.ad.ad07_te01_01.domain.Category;
import dam.ad.ad07_te01_01.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    
    @Autowired
    CategoryRepository categoryRepository;
    
    // Obtiene todas las categorías
    @GetMapping({"/", ""})
    public ResponseEntity<List<Category>> index() {
        try {
            // Recupera todas las categorías de la base de datos
            List<Category> categories = categoryRepository.findAll();
            
            // Retorna la lista de categorías con el estado HTTP 200 (OK)
            return new ResponseEntity<>(categories, HttpStatus.OK);
            
        } catch (Exception ex) {
            // Imprime el stack trace y devuelve un error 500
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Obtiene una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> show(@PathVariable("id") Long id) {
        try {
            // Busca la categoría por su ID
            Optional<Category> category = categoryRepository.findById(id);
            
            if (category.isPresent()) {
                // Si la categoría existe, la devuelve con el estado HTTP 200 (OK)
                return new ResponseEntity<>(category.get(), HttpStatus.OK);
            } else {
                // Si no se encuentra la categoría, retorna un error 404 (NOT FOUND)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            // Imprime el stack trace y devuelve un error 500
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Crea una nueva categoría
    @PostMapping({"/", ""})
    public ResponseEntity<Category> create(@RequestBody Category category) {
        try {
            // Guarda la nueva categoría en la base de datos
            Category savedCategory = categoryRepository.save(category);
            
            // Retorna la categoría guardada con el estado HTTP 201 (CREATED)
            return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
            
        } catch (Exception ex) {
            // Imprime el stack trace y devuelve un error 500
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Actualiza una categoría existente
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable("id") Long id) {
        try {
            // Busca la categoría a actualizar por su ID
            Optional<Category> tempCategory = categoryRepository.findById(id);
            
            if (tempCategory.isPresent()) {
                // Si la categoría existe, actualiza el nombre y guarda los cambios
                Category existingCategory = tempCategory.get();
                existingCategory.setName(category.getName());
                Category updatedCategory = categoryRepository.save(existingCategory);
                
                // Retorna la categoría actualizada con el estado HTTP 200 (OK)
                return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
                
            } else {
                // Si no se encuentra la categoría, retorna un error 404 (NOT FOUND)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            // Imprime el stack trace y devuelve un error 500
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Elimina una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            // Verifica si la categoría existe antes de intentar eliminarla
            if (categoryRepository.existsById(id)) {
                
                // Si existe, elimina la categoría y retorna el estado HTTP 204 (NO CONTENT)
                categoryRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                
            } else {
                // Si no se encuentra la categoría, retorna un error 404 (NOT FOUND)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            // Imprime el stack trace y devuelve un error 500
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
