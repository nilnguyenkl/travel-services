package example.payload.response;

import java.util.List;

public class LoginResponse {

	private String accessToken;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private String refreshToken;

	public LoginResponse(String accessToken, String refreshToken, Long id, String username, String email, List<String> roles) {
		this.id = id;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;		
		this.username = username;
		this.email = email;
		this.roles = roles;
	}


	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}



	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}



	
}
