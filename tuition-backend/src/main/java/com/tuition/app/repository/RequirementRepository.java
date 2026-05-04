package com.tuition.app.repository;

import com.tuition.app.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {
    List<Requirement> findByActiveTrueOrderByCreatedAtDesc();
    List<Requirement> findByStudentIdOrderByCreatedAtDesc(Long studentId);
}
