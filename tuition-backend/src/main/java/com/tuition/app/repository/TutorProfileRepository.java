package com.tuition.app.repository;

import com.tuition.app.entity.TutorProfile;
import com.tuition.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorProfileRepository extends JpaRepository<TutorProfile, Long> {
    Optional<TutorProfile> findByUser(User user);
    List<TutorProfile> findBySubjectContainingIgnoreCase(String subject);
    List<TutorProfile> findByLocationContainingIgnoreCase(String location);
    List<TutorProfile> findBySubjectContainingIgnoreCaseAndClassLevelIgnoreCase(String subject, String classLevel);
}
