package example.service;

import example.elasticsearch.ESMService;

public interface IESService {
	void addServiceIntoElastic(ESMService esmService);
	void modifyServiceElastic(ESMService esmServvice);
}
