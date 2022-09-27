package example.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import example.elasticsearch.ESMService;
import example.elasticsearch.model.ServiceModel;
import example.repository.ESServiceRepository;
import example.service.IESService;

@Service
public class ESService implements IESService {

	@Autowired
	private ESServiceRepository esRepository;
	
	@Autowired
	private ElasticsearchOperations elasticsearchOperations;
	
	private static final String PRODUCT_INDEX = "service";

	@Override
	public void addServiceIntoElastic(ESMService esmService) {
		esRepository.save(esmService);
	}

	@Override
	public void modifyServiceElastic(ESMService esmService) {
		esRepository.save(esmService);

	}

	@Override
	public List<ServiceModel> getAllService(Pageable pageable, Long idCategory, Long idArea, String search) {
		List<ServiceModel> response = new ArrayList<>();
		List<ESMService> services = new ArrayList<>();
		if (search.isEmpty()) {
			if (idArea == 0) {
				if (idCategory != 0) {
					services = esRepository.findAllByIdCategory(idCategory, pageable).getContent();
				}
			} else {
				if (idCategory == 0) {
					services = esRepository.findAllByIdArea(idArea, pageable).getContent();
				} else {
					services = esRepository.findAllByIdAreaAndIdCategory(idArea, idCategory, pageable).getContent();
				}
			}
		} else {
			// services = esRepository.findAllByNameLikeIgnoreCase(search,
			// pageable).getContent();
		}
		for (ESMService service : services) {
			response.add(convertToServiceModel(service));
		}

		return response;
	}

	public List<ServiceModel> getAllService2(String txtsearch) {
		List<ServiceModel> response = new ArrayList<>();


		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				  .withQuery(QueryBuilders.multiMatchQuery(txtsearch)
				    .field("name")
				    .field("description")
				    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
				  .build();

		SearchHits<ESMService> services = elasticsearchOperations.search(searchQuery, ESMService.class, IndexCoordinates.of(PRODUCT_INDEX));
		
		for (SearchHit<ESMService> hit : services) {
			response.add(convertToServiceModel(hit.getContent()));
		}
		
		return response;
	}
	


	public ServiceModel convertToServiceModel(ESMService request) {
		ServiceModel model = new ServiceModel();
		model.setId(request.getId());
		model.setIdArea(request.getIdArea());
		model.setIdCategory(request.getIdCategory());
		model.setDescription(request.getDescription());
		model.setCreateDate(request.getCreateDate());
		model.setImage(request.getImage());
		model.setModifiedDate(request.getModifiedDate());
		model.setName(request.getName());
		model.setOrders(request.getOrders());
		model.setReviews(request.getReviews());
		model.setTicket(request.getTicket());
		return model;
	}
}
