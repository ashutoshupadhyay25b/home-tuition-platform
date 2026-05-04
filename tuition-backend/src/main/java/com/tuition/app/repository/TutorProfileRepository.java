package com.tuition.app.repository;

import com.tuition.app.entity.TutorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorProfileRepository extends JpaRepository<TutorProfile, Long> {
    Optional<TutorProfile> findByUserId(Long userId);
    List<TutorProfile> findBySubjectsContainingIgnoreCaseAndClassLevelContainingIgnoreCase(String subjects, String classLevel);
}
