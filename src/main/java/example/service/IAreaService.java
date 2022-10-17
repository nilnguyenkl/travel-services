package example.service;

import java.util.List;

import example.elasticsearch.ESMArea;
import example.entity.AreaEntity;
import example.payload.response.AreaResponse;

public interface IAreaService {
	List<AreaResponse> getAllArea();
	AreaResponse convertToAreaResponse(AreaEntity entity);
	ESMArea convertToESMArea(AreaEntity entity);
}
