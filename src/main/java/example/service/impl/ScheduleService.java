package example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.entity.ScheduleEntity;
import example.payload.request.ScheduleRequest;
import example.payload.response.ScheduleResponse;
import example.repository.ScheduleRepository;
import example.repository.ServiceRepository;
import example.service.IScheduleService;

@Service
public class ScheduleService implements IScheduleService {
	
	@Autowired
	ServiceRepository serviceRepository;
	
	@Autowired
	ScheduleRepository scheduleRepository;
	
	@Override
	public ScheduleResponse createSchedule2(ScheduleRequest request, Long idService) {
		ScheduleEntity entity = new ScheduleEntity();
		entity.setQuantityperday(request.getQuantityPerDay());
		entity.setTime(request.getTime());
		entity.setServiceSchedule(serviceRepository.findOneById(idService));
		return convertToScheduleResponse(scheduleRepository.save(entity));
	}
	
	@Override
	public ScheduleResponse convertToScheduleResponse(ScheduleEntity entity) {
		ScheduleResponse response = new ScheduleResponse();
		response.setId(entity.getId());
		response.setQuantityPerDay(entity.getQuantityperday());
		response.setTime(entity.getTime());
		return response;
	}

	@Override
	public ScheduleResponse modifySchedule(ScheduleResponse request) {
		ScheduleEntity entity = scheduleRepository.findOneById(request.getId());
		entity.setQuantityperday(request.getQuantityPerDay());
		entity.setTime(request.getTime());
		return convertToScheduleResponse(scheduleRepository.save(entity));
	}

}
