package dam.ad.ad07_te01_01.repository;

import dam.ad.ad07_te01_01.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
