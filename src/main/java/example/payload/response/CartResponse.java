package example.payload.response;

import java.util.Date;
import java.util.List;

public class CartResponse {
	
	private Long idCartItem;
	
	private Long idService;
	
	private String bookingDate;
	
	private List<TicketResponse> tickets;
	
	public CartResponse(Long idCartItem, Long idService, String bookingDate, List<TicketResponse> tickets) {
		this.idService = idService;
		this.bookingDate = bookingDate;
		this.tickets = tickets;
		this.idCartItem = idCartItem;
	}
	
	public Long getIdCartItem() {
		return idCartItem;
	}

	public void setIdCartItem(Long idCartItem) {
		this.idCartItem = idCartItem;
	}

	public Long getIdService() {
		return idService;
	}
	
	public void setIdService(Long idService) {
		this.idService = idService;
	}
	
	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public List<TicketResponse> getTickets() {
		return tickets;
	}
	
	public void setTickets(List<TicketResponse> tickets) {
		this.tickets = tickets;
	}
}
