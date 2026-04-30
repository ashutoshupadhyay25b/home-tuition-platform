package com.tuition.app.controller;

import com.tuition.app.entity.TutorProfile;
import com.tuition.app.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @PostMapping("/profile")
    public ResponseEntity<TutorProfile> createProfile(@RequestParam Long userId, @RequestBody TutorProfile profile) {
        return ResponseEntity.ok(tutorService.createTutorProfile(userId, profile));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TutorProfile>> searchTutors(
            @RequestParam String subject,
            @RequestParam String classLevel) {
        return ResponseEntity.ok(tutorService.searchTutors(subject, classLevel));
    }
}
