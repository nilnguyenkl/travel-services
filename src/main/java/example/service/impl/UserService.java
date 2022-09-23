package example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import example.config.jwt.CustomUserDetails;
import example.entity.RoleEntity;
import example.entity.UserEntity;
import example.exception.UserNotFoundException;
import example.payload.request.RegisterRequest;
import example.payload.response.ProfileResponse;
import example.repository.OrderRepository;
import example.repository.RoleRepository;
import example.repository.UserRepository;
import example.service.IUserService;

@Service
public class UserService implements IUserService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public String createUser(RegisterRequest request) {
		UserEntity user = userRepository.findOneByUsername(request.getUsername());
		if (user != null) {
			return "Username already exists";
		} else {
			RoleEntity roleEntity = roleRepository.findOneById(request.getIdRole());
			UserEntity entity = new UserEntity();
			entity.setUsername(request.getUsername());
			entity.setEmail(request.getEmail());
			entity.setFirstname(request.getFirstname());
			entity.setLastname(request.getLastname());
			entity.setPassword(request.getPassword());
			entity.setPhone(request.getPhone());
			entity.setRoleUser(roleEntity);
			entity.setPassword(new BCryptPasswordEncoder().encode(entity.getPassword()));
			// Save to database
			entity = userRepository.save(entity);
			return "Success";
		}
	}
	
	@Override
	@Transactional
    public UserDetails loadUserByUsername(String username) {
        UserEntity user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return CustomUserDetails.build(user);
    }

	@Override
	public void updateResetPasswordToken(String token, String email) {
		UserEntity user = userRepository.findOneByEmail(email);
		if (user != null) {
			user.setResetPasswordToken(token);
			userRepository.save(user);
		} else {
			new UserNotFoundException("Could not find any user with the email " + email);
		}
		
	}

	@Override
	public void updatePassword(UserEntity user, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		user.setPassword(encodedPassword);
		user.setResetPasswordToken(null);
		userRepository.save(user);
	}

	@Override
	public UserEntity getByResetPasswordToken(String token) {
		return userRepository.findOneByResetPasswordToken(token);
	}

	@Override
	public ProfileResponse getProfile() {
		ProfileResponse response = new ProfileResponse();
		
		// Authentication
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		UserEntity userEntity = userRepository.findOneByUsername(userDetails.getUsername());
		
		response.setAvatar(userEntity.getAvatar());
		response.setEmail(userEntity.getEmail());
		response.setFirstname(userEntity.getFirstname());
		response.setLastname(userEntity.getLastname());
		response.setId(userEntity.getId());
		response.setPhone(userEntity.getPhone());
		response.setSex(userEntity.getSex());
		response.setRole(userEntity.getRoleUser().getRole());
		
		return response;
	}
	
	@Override
	public int totalOrder() {
		// Authentication
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		UserEntity userEntity = userRepository.findOneByUsername(userDetails.getUsername());
		
		return orderRepository.findAllByUserOrderId(userEntity.getId()).size();
	}
}
