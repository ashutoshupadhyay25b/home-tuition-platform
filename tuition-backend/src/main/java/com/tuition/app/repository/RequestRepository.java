package com.tuition.app.repository;

import com.tuition.app.entity.Request;
import com.tuition.app.entity.RequestStatus;
import com.tuition.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByStudent(User student);
    List<Request> findByTutor(User tutor);
    List<Request> findByStatus(RequestStatus status);
    List<Request> findByTutorAndStatus(User tutor, RequestStatus status);
}
