package example.service;

import java.util.Optional;

import example.entity.ServiceEntity;
import example.payload.request.ServiceRequest;

public interface IServiceService {
	Optional<ServiceEntity> createService(ServiceRequest request);
	Optional<ServiceEntity> modifyService(ServiceRequest request, Long idService);
}
