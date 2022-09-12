package example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import example.entity.OrderItemEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>{
	OrderItemEntity findOneById(Long id);
	List<OrderItemEntity> findAllByOrderOrderItemId(Long id);
}
