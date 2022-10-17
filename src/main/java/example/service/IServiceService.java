package example.service;

import java.util.Optional;

import example.entity.LinkDataEntity;
import example.entity.ReviewEntity;
import example.entity.ServiceEntity;
import example.payload.request.ServiceRequest;
import example.payload.response.LinkDataResponse;
import example.payload.response.ReviewsResponse;
import example.payload.response.ServiceDetailsResponse;

public interface IServiceService {
	Optional<ServiceEntity> createService(ServiceRequest request);
	Optional<ServiceEntity> modifyService(ServiceRequest request, Long idService);
	ServiceDetailsResponse getServiceDetails(Long id);
	ReviewsResponse convertToReviewResponse(ReviewEntity entity);
	LinkDataResponse convertToLinkDataReponse(LinkDataEntity linkDataEntity);
}
