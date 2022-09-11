package example.payload.request;

import java.util.List;

import example.payload.response.LinkDataResponse;
import example.payload.response.TicketResponse;

public class ModifyServiceRequest {
	
	private ServiceRequest service;
	
	private List<TicketResponse> ticket;
	
	private List<LinkDataResponse> galleries;

	public ServiceRequest getService() {
		return service;
	}

	public void setService(ServiceRequest service) {
		this.service = service;
	}

	public List<TicketResponse> getTicket() {
		return ticket;
	}

	public void setTicket(List<TicketResponse> ticket) {
		this.ticket = ticket;
	}

	public List<LinkDataResponse> getGalleries() {
		return galleries;
	}

	public void setGalleries(List<LinkDataResponse> galleries) {
		this.galleries = galleries;
	}
	
}
