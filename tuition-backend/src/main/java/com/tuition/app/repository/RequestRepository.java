package com.tuition.app.repository;

import com.tuition.app.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByStudentId(Long studentId);
    List<Request> findByTutorId(Long tutorId);
}
