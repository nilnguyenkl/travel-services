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
@Table(name = "ticket")
public class TicketEntity {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private int value;
	
	@Column
	private String type;
	
	@Column
	private String note;
	
	@ManyToOne
	@JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "service_id_fk_ticket"))
    private ServiceEntity serviceTicket;
	
	@OneToMany(mappedBy = "ticketBy")
	private List<OrderByTicketEntity> listOrderByTicket = new ArrayList<>();
	
}
