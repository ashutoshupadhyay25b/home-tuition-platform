package com.tuition.app.controller;

import com.tuition.app.dto.TutorProfileDTO;
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
    public ResponseEntity<TutorProfileDTO> saveProfile(@RequestParam Long userId, @RequestBody TutorProfile profile) {
        return ResponseEntity.ok(tutorService.saveProfile(userId, profile));
    }

    @GetMapping("/search")
    public ResponseEntity<List<TutorProfileDTO>> search(
            @RequestParam(required = false) String subjects,
            @RequestParam(required = false) String classLevel,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice) {
        return ResponseEntity.ok(tutorService.searchTutors(subjects, classLevel, city, minPrice, maxPrice));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<TutorProfileDTO> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(tutorService.getProfile(userId));
    }
}
