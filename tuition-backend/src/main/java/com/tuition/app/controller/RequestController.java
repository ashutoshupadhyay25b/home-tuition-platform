package com.tuition.app.controller;

import com.tuition.app.dto.RequestDTO;
import com.tuition.app.entity.RequestStatus;
import com.tuition.app.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    public ResponseEntity<RequestDTO> createRequest(@RequestBody Map<String, Long> body) {
        return ResponseEntity.ok(requestService.createRequest(body.get("studentId"), body.get("tutorId")));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<RequestDTO>> getStudentRequests(@PathVariable Long studentId) {
        return ResponseEntity.ok(requestService.getRequestsByStudent(studentId));
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<RequestDTO>> getTutorRequests(@PathVariable Long tutorId) {
        return ResponseEntity.ok(requestService.getRequestsByTutor(tutorId));
    }

    @PutMapping("/{requestId}/status")
    public ResponseEntity<RequestDTO> updateStatus(@PathVariable Long requestId, @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(requestService.updateStatus(requestId, RequestStatus.valueOf(body.get("status"))));
    }
}
