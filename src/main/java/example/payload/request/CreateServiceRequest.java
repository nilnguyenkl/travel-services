package example.payload.request;

import java.util.List;

import example.payload.response.LinkDataResponse;

public class CreateServiceRequest {
	
	private ServiceRequest service;
	
	private List<TicketRequest> ticket;
	
	private List<LinkDataResponse> galleries;
	
	public ServiceRequest getService() {
		return service;
	}
	public void setService(ServiceRequest service) {
		this.service = service;
	}
	public List<TicketRequest> getTicket() {
		return ticket;
	}
	public void setTicket(List<TicketRequest> ticket) {
		this.ticket = ticket;
	}
	public List<LinkDataResponse> getGalleries() {
		return galleries;
	}
	public void setGalleries(List<LinkDataResponse> galleries) {
		this.galleries = galleries;
	}
}
