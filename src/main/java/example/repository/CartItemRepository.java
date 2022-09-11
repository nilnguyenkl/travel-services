package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.entity.CartItemEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
	CartItemEntity findOneById(Long id);
	
}
