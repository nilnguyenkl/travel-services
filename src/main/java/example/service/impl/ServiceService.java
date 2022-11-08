package example.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import example.config.jwt.CustomUserDetails;
import example.entity.AreaEntity;
import example.entity.CategoryEntity;
import example.entity.LinkDataEntity;
import example.entity.ReviewEntity;
import example.entity.ServiceEntity;
import example.entity.TicketEntity;
import example.entity.UserEntity;
import example.payload.request.ServiceRequest;
import example.payload.response.LinkDataResponse;
import example.payload.response.ReviewsResponse;
import example.payload.response.ServiceDetailsResponse;
import example.payload.response.UserReviewResponse;
import example.repository.AreaRepository;
import example.repository.CategoryRepository;
import example.repository.LinkDataRepository;
import example.repository.ReviewRepository;
import example.repository.ServiceRepository;
import example.repository.TicketRepository;
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
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	TicketRepository ticketRepository;
	
	@Autowired
	LinkDataRepository linkDataRepository;

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

	@Override
	public ServiceDetailsResponse getServiceDetails(Long id) {
		
		ServiceDetailsResponse response = new ServiceDetailsResponse();
		
		// Set service basic
		ServiceEntity serviceEntity = serviceRepository.findOneById(id);
		response.setId(serviceEntity.getId());
		response.setAddress(serviceEntity.getAddress());
		response.setDescription(serviceEntity.getDescription());
		response.setEvent(serviceEntity.getEvent());
		response.setNote(serviceEntity.getNote());
		response.setName(serviceEntity.getName());
		response.setCreateByAuthId(serviceEntity.getUserService().getId());
		response.setUsernameAuth(serviceEntity.getUserService().getUsername());
		// Set list reviews
		List<ReviewEntity> reviews = reviewRepository.findAllByServiceReviewId(id, Sort.by(Sort.Direction.DESC, "id"));
		List<ReviewsResponse> reviewsResponse = new ArrayList<>();
		for (ReviewEntity review : reviews) {
			reviewsResponse.add(convertToReviewResponse(review));
		}
		response.setReviews(reviewsResponse);
		
		// Set min price
		List<TicketEntity> ticketEntities = ticketRepository.findAllByServiceTicketIdOrderByValueAsc(id);
		response.setMinPrice(ticketEntities.get(0).getValue());
		
		// Set galleries
		List<LinkDataEntity> links = linkDataRepository.findAllByServiceStorageId(id);
		List<LinkDataResponse> linksResponse = new ArrayList<>();
		for (LinkDataEntity entity : links) {
			linksResponse.add(convertToLinkDataReponse(entity));
		}
		response.setGalleries(linksResponse);
		
		return response;
	}
	
	@Override
	public ReviewsResponse convertToReviewResponse(ReviewEntity entity) {
		ReviewsResponse response = new ReviewsResponse();
		UserReviewResponse userReview = new UserReviewResponse(); 
		response.setId(entity.getId());
		response.setCreateDate(entity.getCreateDate());
		response.setModifiedDate(entity.getModifiedDate());
		response.setPoint(entity.getPoint());
		response.setContent(entity.getContent());
		
		UserEntity user = userRepository.findOneById(entity.getUserReview().getId());
		userReview.setId(user.getId());
		userReview.setAvatar(user.getAvatar());
		userReview.setFirstname(user.getFirstname());
		userReview.setLastname(user.getLastname());
		response.setUser(userReview);
		return response;
	}
	
	@Override
	public LinkDataResponse convertToLinkDataReponse(LinkDataEntity linkDataEntity) {
		LinkDataResponse response = new LinkDataResponse();
		response.setId(linkDataEntity.getId());
		response.setUrl(linkDataEntity.getDataUrl());
		response.setType(linkDataEntity.getType());
		return response;
	}
	
}
