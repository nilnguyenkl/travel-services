package example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.elasticsearch.ESMArea;
import example.entity.AreaEntity;
import example.payload.response.AreaResponse;
import example.repository.AreaRepository;
import example.service.IAreaService;


@Service
public class AreaService implements IAreaService {
	
	@Autowired
	AreaRepository areaRepository;

	@Override
	public List<AreaResponse> getAllArea() {
		List<AreaResponse> list = new ArrayList<>();
		
		List<AreaEntity> areas = areaRepository.findAll();
		
		for (AreaEntity entity : areas) {
			list.add(convertToAreaResponse(entity));
		}
		
		return list;
	}
	
	@Override
	public AreaResponse convertToAreaResponse(AreaEntity entity) {
		AreaResponse response = new AreaResponse();
		response.setId(entity.getId());
		response.setName(entity.getName());
		response.setUrl(entity.getDataUrl());
		return response;
	}

	@Override
	public ESMArea convertToESMArea(AreaEntity entity) {
		ESMArea area = new ESMArea(entity.getId(), entity.getName());
		return area;
	}

}
