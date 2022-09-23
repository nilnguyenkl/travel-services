package example.service;

import example.entity.UserEntity;
import example.payload.request.RegisterRequest;
import example.payload.response.ProfileResponse;

public interface IUserService {
	String createUser(RegisterRequest userDTO);
	void updateResetPasswordToken(String token, String email);
	void updatePassword(UserEntity user, String newPassword);
	UserEntity getByResetPasswordToken(String token);
	ProfileResponse getProfile();
	int totalOrder();
}
