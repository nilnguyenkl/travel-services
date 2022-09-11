package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import example.entity.ServiceEntity;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long>{
	ServiceEntity findOneById(Long id);
}
