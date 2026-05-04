package com.tuition.app.service;

import com.tuition.app.dto.RequestDTO;
import com.tuition.app.entity.Request;
import com.tuition.app.entity.RequestStatus;
import com.tuition.app.entity.User;
import com.tuition.app.repository.RequestRepository;
import com.tuition.app.repository.UserRepository;
import com.tuition.app.util.AppMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public RequestDTO createRequest(Long studentId, Long tutorId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        User tutor = userRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor not found"));

        Request request = new Request();
        request.setStudent(student);
        request.setTutor(tutor);
        request.setStatus(RequestStatus.PENDING);
        request.setCreatedAt(LocalDateTime.now());

        Request savedRequest = requestRepository.save(request);
        return AppMapper.toRequestDTO(savedRequest);
    }

    public List<RequestDTO> getRequestsByStudent(Long studentId) {
        return requestRepository.findByStudentId(studentId).stream()
                .map(AppMapper::toRequestDTO)
                .collect(Collectors.toList());
    }

    public List<RequestDTO> getRequestsByTutor(Long tutorId) {
        return requestRepository.findByTutorId(tutorId).stream()
                .map(AppMapper::toRequestDTO)
                .collect(Collectors.toList());
    }

    public RequestDTO updateStatus(Long requestId, RequestStatus status) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(status);
        Request updatedRequest = requestRepository.save(request);
        return AppMapper.toRequestDTO(updatedRequest);
    }
}
