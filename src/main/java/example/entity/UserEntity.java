package example.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
	
	@Column
    private String username;
	
	@Column
	private String password;
	
	@Column
	private String firstname;
	
	@Column
	private String lastname;
	
	@Column
	private String phone;
	
	@Column
	private String email;
	
	@Column
	private String sex;
	
	@Column
	private String avatar;
	
	@Column(name = "resetPasswordToken")
    private String resetPasswordToken;
	
	@ManyToOne
	@JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "role_id_fk_user"))
    private RoleEntity roleUser;
	
	@OneToMany(mappedBy = "userService")
	private List<ServiceEntity> listService = new ArrayList<>();
	
	@OneToMany(mappedBy = "userReview")
	private List<ReviewEntity> listReview = new ArrayList<>();
	
	@OneToMany(mappedBy = "userOrder")
	private List<OrderEntity> listOrder = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RoleEntity getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(RoleEntity roleUser) {
		this.roleUser = roleUser;
	}

	public List<ReviewEntity> getListReview() {
		return listReview;
	}

	public void setListReview(List<ReviewEntity> listReview) {
		this.listReview = listReview;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<ServiceEntity> getListService() {
		return listService;
	}

	public void setListService(List<ServiceEntity> listService) {
		this.listService = listService;
	}
	
}
