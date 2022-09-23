package example.payload.response;

import java.util.List;

public class CartResponse {
	
	private Long idCartItem;
	
	private Long idService;
	
	private String bookDay;
	
	private String bookTime;
	
	private List<TicketResponse> tickets;
	
	public CartResponse(Long idCartItem, Long idService, String bookDay, String bookTime, List<TicketResponse> tickets) {
		this.idService = idService;
		this.bookDay = bookDay;
		this.bookTime = bookTime;
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

	public String getBookDay() {
		return bookDay;
	}

	public void setBookDay(String bookDay) {
		this.bookDay = bookDay;
	}

	public String getBookTime() {
		return bookTime;
	}

	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}

	public List<TicketResponse> getTickets() {
		return tickets;
	}
	
	public void setTickets(List<TicketResponse> tickets) {
		this.tickets = tickets;
	}
}
