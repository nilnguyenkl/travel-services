package example.service;

import java.util.Optional;

import example.payload.request.CartRequest;
import example.payload.response.CartResponse;

public interface ICartService {
	Optional<CartResponse> createCart(CartRequest request);
	boolean deleteCart(Long cartItemId);
	Optional<CartResponse> updateCart(CartRequest request, Long cartItemId);
}
