package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import example.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	UserEntity findOneByUsername(String username);
	UserEntity findOneByEmail(String email);
	UserEntity findOneByResetPasswordToken(String token);
}
