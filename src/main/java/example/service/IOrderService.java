package example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

import example.entity.OrderEntity;
import example.entity.OrderItemEntity;
import example.payload.request.CartRequest;
import example.payload.request.InforRequest;
import example.payload.request.OrderRequest;
import example.payload.response.OrderObjectResponse;
import example.payload.response.OrderResponse;
import example.payload.response.TicketResponse;

public interface IOrderService {
	Optional<OrderResponse> createOrder(OrderRequest request);
	List<OrderObjectResponse> findAll(Pageable pageable);
	OrderObjectResponse findOne(Long idOrder);
	int totalOrderPrice(List<CartRequest> items);
	int totalOrderItemPrice(List<TicketResponse> tickets);
	InforRequest convertToInforRequest(Long idOrder);
	List<CartRequest> convertToListCartRequest(List<OrderItemEntity> listOrderItem);
	OrderObjectResponse convertToOrderObjectResponse(OrderEntity order);
	int totalItem();
}
