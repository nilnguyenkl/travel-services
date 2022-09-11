package example.service;

import java.util.Optional;

import example.payload.request.OrderRequest;
import example.payload.response.OrderResponse;

public interface IOrderService {
	Optional<OrderResponse> createOrder(OrderRequest request);
}
