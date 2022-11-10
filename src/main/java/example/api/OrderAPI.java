package example.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.payload.request.OrderRequest;
import example.payload.response.CalenderOrderResponse;
import example.payload.response.GetOrderItemResponse;
import example.payload.response.OrderResponse;
import example.payload.response.RangeOrderResponse;
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
	
//	@GetMapping(value = "/orders")
//	public GetOrderResponse getOrders(@RequestParam("page") int page, @RequestParam("limit") int limit, @RequestParam("sort") String sort) {
//		GetOrderResponse result = new GetOrderResponse();
//		result.setPage(page);
//		Sort sortable = null;
//		if (sort.equals("ASC")) {
//			sortable = Sort.by("id").ascending();
//		}
//		if (sort.equals("DESC")) {
//			sortable = Sort.by("id").descending();
//		}
//		Pageable pageable =  PageRequest.of(page-1,limit, sortable);
//		result.setData(orderService.findAll(pageable));
//		result.setTotalPage((int)Math.ceil((double)orderService.totalItem() / limit));
//		return result;
//	}
//	
//	@GetMapping(value = "/order/{idOrder}")
//	public OrderObjectResponse getOrderDetails(@PathVariable("idOrder") int idOrder) {
//		return orderService.findOne((long) idOrder);
//	}
	
	@GetMapping(value = "/orderitem/{status}")
	public List<GetOrderItemResponse> getOrderItemByStatus(@PathVariable("status") String status) {
		return orderService.getAllOrderItemByStatus(status);
	}
	
	@GetMapping(value = "/order/calender")
	public List<CalenderOrderResponse> calenderEvent() {
		return orderService.listCalenderOrderByUser();
	}
	
	@GetMapping(value = "/order/range")
	public RangeOrderResponse rangeOrder(@RequestParam("day") String day, @RequestParam("idService") Long idService) {
		return orderService.rangeOrder(day, idService);
	}
	
	
}
