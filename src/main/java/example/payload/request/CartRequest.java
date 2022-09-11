package example.payload.request;
import java.util.List;

import example.payload.response.TicketResponse;

public class CartRequest {
	
	private Long idService;
	
	private String bookingDate;
	
	private String note;
	
	private Long idCartItem;
	
	private List<TicketResponse> tickets;

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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Long getIdCartItem() {
		return idCartItem;
	}

	public void setIdCartItem(Long idCartItem) {
		this.idCartItem = idCartItem;
	}
}
