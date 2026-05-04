package com.tuition.app.repository;

import com.tuition.app.entity.TutorProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TutorProfileRepository extends JpaRepository<TutorProfile, Long> {
    Optional<TutorProfile> findByUserId(Long userId);

    @Query("SELECT tp FROM TutorProfile tp WHERE " +
           "(:subjects IS NULL OR LOWER(tp.subjects) LIKE LOWER(CONCAT('%', :subjects, '%'))) AND " +
           "(:classLevel IS NULL OR LOWER(tp.classLevel) LIKE LOWER(CONCAT('%', :classLevel, '%'))) AND " +
           "(:city IS NULL OR LOWER(tp.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
           "(:minPrice IS NULL OR tp.hourlyRate >= :minPrice) AND " +
           "(:maxPrice IS NULL OR tp.hourlyRate <= :maxPrice)")
    Page<TutorProfile> searchTutors(
        @Param("subjects") String subjects,
        @Param("classLevel") String classLevel,
        @Param("city") String city,
        @Param("minPrice") Double minPrice,
        @Param("maxPrice") Double maxPrice,
        Pageable pageable
    );
}
