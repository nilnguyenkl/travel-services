package example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.elasticsearch.ESMService;
import example.repository.ESServiceRepository;
import example.service.IESService;

@Service
public class ESService implements IESService {
	
	@Autowired
	private ESServiceRepository esRepository;
	
	@Override
	public void addServiceIntoElastic(ESMService esmService) {
		esRepository.save(esmService);
	}

	@Override
	public void modifyServiceElastic(ESMService esmService) {
		esRepository.save(esmService);
		
	}
}
