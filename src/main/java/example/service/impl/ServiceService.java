package example.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import example.config.jwt.CustomUserDetails;
import example.entity.AreaEntity;
import example.entity.CategoryEntity;
import example.entity.ServiceEntity;
import example.entity.UserEntity;
import example.payload.request.ServiceRequest;
import example.repository.AreaRepository;
import example.repository.CategoryRepository;
import example.repository.ServiceRepository;
import example.repository.UserRepository;
import example.service.IServiceService;


@Service
public class ServiceService implements IServiceService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	AreaRepository areaRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ServiceRepository serviceRepository;

	@Override
	public Optional<ServiceEntity> createService(ServiceRequest request) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		UserEntity userEntity = userRepository.findOneByUsername(userDetails.getUsername());
		CategoryEntity categoryEntity = categoryRepository.findOneById(request.getIdCategory());
		AreaEntity areaEntity = areaRepository.findOneById(request.getIdArea());
		
		ServiceEntity serviceEntity = new ServiceEntity();
		// Set data service
		serviceEntity.setAddress(request.getAddress());
		serviceEntity.setDescription(request.getDescription());
		serviceEntity.setEvent(request.getEvent());
		serviceEntity.setName(request.getName());
		serviceEntity.setNote(request.getNote());
		serviceEntity.setStatus(true);
		serviceEntity.setCategoryService(categoryEntity);
		serviceEntity.setAreaService(areaEntity);
		serviceEntity.setUserService(userEntity);
		serviceEntity.setCreateDate(new Date());
		serviceEntity.setModifiedDate(new Date());
		
		serviceEntity = serviceRepository.save(serviceEntity);
		
		return Optional.ofNullable(serviceEntity);
	}

	@Override
	public Optional<ServiceEntity> modifyService(ServiceRequest request, Long idService) {
		ServiceEntity serviceEntity = serviceRepository.findOneById(idService);
		serviceEntity.setAddress(request.getAddress());
		serviceEntity.setDescription(request.getDescription());
		serviceEntity.setEvent(request.getEvent());
		serviceEntity.setName(request.getName());
		serviceEntity.setNote(request.getNote());
		serviceEntity.setStatus(true);
		serviceEntity.setModifiedDate(new Date());
		
		serviceEntity = serviceRepository.save(serviceEntity);
		
		return Optional.ofNullable(serviceEntity);
	}
	
}
