package example.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import example.elasticsearch.ESMService;

public interface ESServiceRepository extends ElasticsearchRepository<ESMService, Long>{
	ESMService findOneById(Long id);
}
