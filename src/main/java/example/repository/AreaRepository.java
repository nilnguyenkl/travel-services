package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import example.entity.AreaEntity;

public interface AreaRepository extends JpaRepository<AreaEntity, Long> {
	AreaEntity findOneById(Long id);
}
