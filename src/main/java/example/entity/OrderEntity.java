package example.entity;

import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "orders")
public class OrderEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_fk_order"))
    private UserEntity userOrder;
	
	@OneToMany(mappedBy = "orderOrderItem")
	private List<OrderItemEntity> listOrderItem = new ArrayList<>();
	
	@Column
	private String firstname;
	
	@Column
	private String lastname;
	
	@Column
	private String phone;
	
	@Column
	private String email;
	
	@Column
	private String note;
	
	@Column
	private int total;
	
	// 3 trạng thái : chuẩn bị booking, booking, xác nhận booking
	@Column
	private String status;
	
	@Column
	private Date createDate;
	
	@Column
	private Date modifiedDate;
	
	//alter table order add constraint user_id_fk_orrder foreign key (user_id) references user (id)
	
}
