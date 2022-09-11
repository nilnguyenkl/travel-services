package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import example.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity findOneById(Long id);
}
