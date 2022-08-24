package example.service;

import example.entity.UserEntity;
import example.payload.request.RegisterRequest;

public interface IUserService {
	String createUser(RegisterRequest userDTO);
	void updateResetPasswordToken(String token, String email);
	void updatePassword(UserEntity user, String newPassword);
	UserEntity getByResetPasswordToken(String token);
}
