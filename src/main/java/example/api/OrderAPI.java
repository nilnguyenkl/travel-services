package example.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.payload.request.OrderRequest;
import example.payload.response.GetOrderResponse;
import example.payload.response.OrderObjectResponse;
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
	
	@GetMapping(value = "/orders")
	public GetOrderResponse getOrders(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam("sort") String sort) {
		GetOrderResponse result = new GetOrderResponse();
		result.setPage(page);
		Sort sortable = null;
		if (sort.equals("ASC")) {
			sortable = Sort.by("id").ascending();
		}
		if (sort.equals("DESC")) {
			sortable = Sort.by("id").descending();
		}
		Pageable pageable =  PageRequest.of(page-1,limit, sortable);
		result.setData(orderService.findAll(pageable));
		result.setTotalPage((int)Math.ceil((double)orderService.totalItem() / limit));
		return result;
	}
	
	@GetMapping(value = "/order/{idOrder}")
	public OrderObjectResponse getOrderDetails(@PathVariable("idOrder") int idOrder) {
		return orderService.findOne((long) idOrder);
	}
}
