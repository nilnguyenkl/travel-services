package example.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
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
		
		NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
				  .withQuery(QueryBuilders.multiMatchQuery(search)
				    .field("name")
				    .field("area.name")
				    .field("category.name")
				    .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
				  .build();		
		
		searchQuery.setPageable(pageable);
		SearchHits<ESMService> data = elasticsearchOperations.search(searchQuery, ESMService.class, IndexCoordinates.of(PRODUCT_INDEX));
		
		for (SearchHit<ESMService> hit : data) {
			services.add(hit.getContent());
		}
			
		
		for (ESMService service : services) {
			response.add(convertToServiceModel(service));
		}

		return response;
	}

	public ServiceModel convertToServiceModel(ESMService request) {
		ServiceModel model = new ServiceModel();
		model.setId(request.getId());
		model.setArea(request.getArea());
		model.setCategory(request.getCategory());
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
