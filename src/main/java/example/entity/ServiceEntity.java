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
@Table(name = "service")
public class ServiceEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private String name;
	
	@Column
	private String address;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Column(columnDefinition = "TEXT")
	private String note;
	
	@Column(columnDefinition = "TEXT")
	private String event;
	
	@Column
	private Date createDate;
	
	@Column
	private Date modifiedDate;
	
	@Column
	private boolean status;
	
	
	@ManyToOne
	@JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "category_id_fk_service"))
    private CategoryEntity categoryService;
	
	@ManyToOne
	@JoinColumn(name = "area_id", foreignKey = @ForeignKey(name = "area_id_fk_service"))
    private AreaEntity areaService;
	
	@OneToMany(mappedBy = "serviceOrderItem")
	private List<OrderItemEntity> listOrderItem = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_fk_service"))
    private UserEntity userService;
	
	@OneToMany(mappedBy = "serviceStorage")
	private List<StorageEntity> listStorage = new ArrayList<>();
	
	@OneToMany(mappedBy = "serviceReview")
	private List<ReviewEntity> listReview = new ArrayList<>();
	
	@OneToMany(mappedBy = "serviceTicket")
	private List<TicketEntity> listTicket = new ArrayList<>();
	
}
