package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	OrderEntity findOneById(Long id);
}
