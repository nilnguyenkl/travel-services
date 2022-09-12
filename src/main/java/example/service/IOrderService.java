package example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

import example.payload.request.OrderRequest;
import example.payload.response.OrderObjectResponse;
import example.payload.response.OrderResponse;

public interface IOrderService {
	Optional<OrderResponse> createOrder(OrderRequest request);
	List<OrderObjectResponse> findAll(Pageable pageable);
	OrderObjectResponse findOne(Long idOrder);
}
