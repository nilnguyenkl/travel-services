package example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import example.entity.OrderEntity;
import example.entity.OrderItemEntity;
import example.entity.ServiceEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>{
	OrderItemEntity findOneById(Long id);
	List<OrderItemEntity> findAllByOrderOrderItemId(Long id);
	
	List<OrderItemEntity> findAllByOrderOrderItem(OrderEntity entity);
	List<OrderItemEntity> findAllByBookDayAndServiceOrderItemAndBookTime(String day, ServiceEntity serviceEntity, String time);
	List<OrderItemEntity> findAllByOrderOrderItemAndStatus(OrderEntity entity, String status);
	
}
