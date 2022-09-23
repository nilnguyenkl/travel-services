package example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import example.payload.response.ProfileUserResponse;
import example.service.impl.UserService;

@CrossOrigin
@RestController
public class ProfileAPI {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/user/profile")
	public ProfileUserResponse getProfileUser() {
		ProfileUserResponse response = new ProfileUserResponse();
		
		response.setInfor(userService.getProfile());
		response.setNumOrder(userService.totalOrder());
	
		return response;
	}
}
