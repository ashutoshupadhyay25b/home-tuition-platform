package com.tuition.app.controller;

import com.tuition.app.entity.Request;
import com.tuition.app.entity.RequestStatus;
import com.tuition.app.service.RequestService;
import com.tuition.app.repository.RequestRepository;
import com.tuition.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public record CreateRequestDto(Long studentId, Long tutorId) {}
    public record UpdateStatusDto(RequestStatus status) {}

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody CreateRequestDto dto) {
        return ResponseEntity.ok(requestService.createRequest(dto.studentId(), dto.tutorId()));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Request>> getRequestsByStudent(@PathVariable Long studentId) {
        return userRepository.findById(studentId)
                .map(requestRepository::findByStudent)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<Request>> getRequestsByTutor(@PathVariable Long tutorId) {
        return userRepository.findById(tutorId)
                .map(requestRepository::findByTutor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{requestId}/status")
    public ResponseEntity<Request> updateStatus(@PathVariable Long requestId, @RequestBody UpdateStatusDto dto) {
        return ResponseEntity.ok(requestService.updateRequestStatus(requestId, dto.status()));
    }
}
