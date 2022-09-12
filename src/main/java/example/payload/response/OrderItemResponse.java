package example.payload.response;

import java.util.Date;
import java.util.List;

public class OrderItemResponse {
	
	private Long idService;
	
	private Date bookingDate;
	
	private Date createDate;
	
	private int totalItem;
	
	private List<TicketResponse> tickets;
	
	public Long getIdService() {
		return idService;
	}
	
	public void setIdService(Long idService) {
		this.idService = idService;
	}
	
	public Date getBookingDate() {
		return bookingDate;
	}
	
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public int getTotalItem() {
		return totalItem;
	}
	
	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}
	
	public List<TicketResponse> getTickets() {
		return tickets;
	}
	
	public void setTickets(List<TicketResponse> tickets) {
		this.tickets = tickets;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
