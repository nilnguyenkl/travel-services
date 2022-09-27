package example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.payload.response.GetServiceResponse;
import example.service.impl.ESService;

@CrossOrigin
@RestController
public class PublicAPI {

	@Autowired
	ESService esService;

	@GetMapping(value = "/public/service2")
	public GetServiceResponse getService(@RequestParam("page") int page, @RequestParam("limit") int limit,
			@RequestParam("sort") String sort, @RequestParam(value = "search", defaultValue = "") String search,
			@RequestParam(value = "area", defaultValue = "0") Long idArea,
			@RequestParam(value = "category", defaultValue = "0") Long idCategory) {

		GetServiceResponse response = new GetServiceResponse();
		response.setPage(page);
		// Sort sortable = null;
		Pageable pageable = PageRequest.of(page - 1, limit);
		response.setItems(esService.getAllService(pageable, idCategory, idArea, search));
		response.setTotalPage(0);
		return response;

	}
	
	@GetMapping(value = "/public/service")
	public GetServiceResponse getService2(@RequestParam("search") String search) {

		GetServiceResponse response = new GetServiceResponse();
		//response.setPage(page);
		// Sort sortable = null;
		//Pageable pageable = PageRequest.of(page - 1, limit);
		response.setItems(esService.getAllService2(search));
		//response.setTotalPage(0);
		return response;

	}
	
	

}
