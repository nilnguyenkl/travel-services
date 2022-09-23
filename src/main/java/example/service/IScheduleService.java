package example.service;

import example.entity.ScheduleEntity;
import example.payload.request.ScheduleRequest;
import example.payload.response.ScheduleResponse;
import example.payload.response.TicketResponse;

public interface IScheduleService {
	ScheduleResponse createSchedule2(ScheduleRequest request, Long serviceId);
	ScheduleResponse modifySchedule(ScheduleResponse request);
	ScheduleResponse convertToScheduleResponse(ScheduleEntity entity);
}
