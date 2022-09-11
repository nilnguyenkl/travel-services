package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>{
	OrderItemEntity findOneById(Long id);
}
