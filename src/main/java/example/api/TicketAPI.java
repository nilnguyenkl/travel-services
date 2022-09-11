package example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import example.payload.request.CreateTicketRequest;
import example.payload.response.TicketResponse;
import example.service.impl.TicketService;

@CrossOrigin
@RestController
public class TicketAPI {
	
	@Autowired
	TicketService ticketService;
	
	@PostMapping(value = "/admin/ticket")
	public TicketResponse createTicket(@RequestBody CreateTicketRequest request) {
		TicketResponse ticketResponse = new TicketResponse();
		ticketResponse = ticketService.createTicket1(request);
		return ticketResponse; 
	}
}
