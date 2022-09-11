package example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
	ReviewEntity findOneById(Long id);
}
