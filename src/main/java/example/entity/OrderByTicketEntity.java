package example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderbyticket")
public class OrderByTicketEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "orderitem_id", foreignKey = @ForeignKey(name = "orderitem_id_fk_service"))
    private OrderItemEntity orderItemBy;

	@ManyToOne
	@JoinColumn(name = "ticket_id", foreignKey = @ForeignKey(name = "ticket_id_fk_service"))
    private TicketEntity ticketBy;
	
	@Column
	private int amount;
}
