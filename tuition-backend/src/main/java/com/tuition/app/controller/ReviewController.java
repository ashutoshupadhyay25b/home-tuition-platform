package com.tuition.app.controller;

import com.tuition.app.dto.ReviewDTO;
import com.tuition.app.entity.Review;
import com.tuition.app.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/tutor/{tutorUserId}")
    public ResponseEntity<ReviewDTO> addReview(
            @PathVariable Long tutorUserId,
            @RequestParam Long studentId,
            @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.addReview(tutorUserId, studentId, review));
    }

    @GetMapping("/tutor/{tutorUserId}")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long tutorUserId) {
        return ResponseEntity.ok(reviewService.getTutorReviews(tutorUserId));
    }
}
