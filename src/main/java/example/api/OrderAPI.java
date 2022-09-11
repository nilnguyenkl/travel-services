package example.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import example.payload.request.OrderRequest;
import example.payload.response.MessageResponse;
import example.payload.response.OrderResponse;
import example.service.impl.OrderService;

@CrossOrigin
@RestController
public class OrderAPI {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping(value = "/order")
	public Optional<OrderResponse> createOrder(@RequestBody OrderRequest request) {
		return orderService.createOrder(request);
	}
}
