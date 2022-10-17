package example.service;

import java.util.List;

import example.payload.response.FavoriteResponse;
import example.payload.response.MessageResponse;

public interface IFavoriteService {
	List<FavoriteResponse> getAllFavoriteByUser();
	void deleteFavorite(Long idService);
	MessageResponse insertFavorite(Long idService);
}
