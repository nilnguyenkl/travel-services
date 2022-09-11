package example.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import example.payload.request.CartRequest;
import example.payload.response.CartResponse;
import example.payload.response.MessageResponse;
import example.service.impl.CartService;

@CrossOrigin
@RestController
public class CartAPI {
	
	@Autowired
	CartService cartService;
	
	@PostMapping(value = "/cart")
	public Optional<CartResponse> createCart(@RequestBody CartRequest request) {
		return cartService.createCart(request);
	}
	
	@PutMapping(value = "/cart/{cartItemId}")
	public Optional<CartResponse> updateCart(@PathVariable("cartItemId") String id, @RequestBody CartRequest request) {
		return cartService.updateCart(request, Long.parseLong(id));
	}
	
	@DeleteMapping(value = "/cart/{cartItemId}")
	public MessageResponse deleteCart(@PathVariable("cartItemId") String id) {
		boolean status = cartService.deleteCart(Long.parseLong(id));
		if (status) {
			return new MessageResponse("Success");
		} else {
			return new MessageResponse("Failed");
		}
	}
}