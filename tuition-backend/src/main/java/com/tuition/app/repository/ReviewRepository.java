package com.tuition.app.repository;

import com.tuition.app.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByTutorIdOrderByCreatedAtDesc(Long tutorId);
}
