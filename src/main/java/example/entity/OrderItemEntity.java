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
@Table(name = "orderitem")
public class OrderItemEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "order_id", foreignKey = @ForeignKey(name = "order_id_fk_orderitem"))
    private OrderEntity orderOrderItem;
	
	@ManyToOne
	@JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "service_id_fk_orderitem"))
    private ServiceEntity serviceOrderItem;
	
	@OneToMany(mappedBy = "orderItemBy")
	private List<OrderItemByTicketEntity> listOrderByTicket = new ArrayList<>();
	
	@Column
	private String note;
	
	@Column
	private int total;
	
	@Column
	private Date bookingDate;
	
	@Column
	private Date createDate;
	
	@Column
	private Date modifiedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderEntity getOrderOrderItem() {
		return orderOrderItem;
	}

	public void setOrderOrderItem(OrderEntity orderOrderItem) {
		this.orderOrderItem = orderOrderItem;
	}

	public ServiceEntity getServiceOrderItem() {
		return serviceOrderItem;
	}

	public void setServiceOrderItem(ServiceEntity serviceOrderItem) {
		this.serviceOrderItem = serviceOrderItem;
	}

	public List<OrderItemByTicketEntity> getListOrderByTicket() {
		return listOrderByTicket;
	}

	public void setListOrderByTicket(List<OrderItemByTicketEntity> listOrderByTicket) {
		this.listOrderByTicket = listOrderByTicket;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
