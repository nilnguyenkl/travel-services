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
	private List<OrderByTicketEntity> listOrderByTicket = new ArrayList<>();
	
	@Column
	private Date bookingDate;
}
