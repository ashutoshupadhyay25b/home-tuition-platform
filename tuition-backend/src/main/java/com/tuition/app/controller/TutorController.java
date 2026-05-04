package com.tuition.app.controller;

import com.tuition.app.dto.PaginatedResponse;
import com.tuition.app.dto.TutorProfileDTO;
import com.tuition.app.entity.TutorProfile;
import com.tuition.app.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutors")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @PostMapping("/profile")
    public ResponseEntity<TutorProfileDTO> saveProfile(@RequestParam Long userId, @RequestBody TutorProfile profile) {
        return ResponseEntity.ok(tutorService.saveProfile(userId, profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorProfileDTO> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.getProfile(id));
    }

    @GetMapping("/search")
    public ResponseEntity<PaginatedResponse<TutorProfileDTO>> search(
            @RequestParam(required = false) String subjects,
            @RequestParam(required = false) String classLevel,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(tutorService.searchTutors(subjects, classLevel, city, minPrice, maxPrice, pageable));
    }
}
