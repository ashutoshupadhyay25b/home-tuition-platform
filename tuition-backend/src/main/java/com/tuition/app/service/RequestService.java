package com.tuition.app.service;

import com.tuition.app.entity.Request;
import com.tuition.app.entity.RequestStatus;
import com.tuition.app.entity.User;
import com.tuition.app.repository.RequestRepository;
import com.tuition.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public Request createRequest(Long studentId, Long tutorId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        User tutor = userRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor not found"));

        Request request = Request.builder()
                .student(student)
                .tutor(tutor)
                .status(RequestStatus.PENDING)
                .build();

        return requestRepository.save(request);
    }

    public Request updateRequestStatus(Long requestId, RequestStatus newStatus) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        
        request.setStatus(newStatus);
        return requestRepository.save(request);
    }
}
