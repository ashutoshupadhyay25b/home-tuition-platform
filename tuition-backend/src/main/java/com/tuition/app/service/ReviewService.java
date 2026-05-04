package com.tuition.app.service;

import com.tuition.app.dto.ReviewDTO;
import com.tuition.app.entity.Review;
import com.tuition.app.entity.TutorProfile;
import com.tuition.app.entity.User;
import com.tuition.app.repository.ReviewRepository;
import com.tuition.app.repository.TutorProfileRepository;
import com.tuition.app.repository.UserRepository;
import com.tuition.app.util.AppMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final TutorProfileRepository tutorProfileRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReviewDTO addReview(Long tutorUserId, Long studentId, Review review) {
        User tutorUser = userRepository.findById(tutorUserId)
                .orElseThrow(() -> new RuntimeException("Tutor not found"));
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        
        review.setTutor(tutorUser);
        review.setStudent(student);
        Review saved = reviewRepository.save(review);

        // Update Tutor Profile Rating
        TutorProfile profile = tutorProfileRepository.findByUserId(tutorUserId)
                .orElseThrow(() -> new RuntimeException("Tutor profile not found"));
        
        List<Review> allReviews = reviewRepository.findByTutorIdOrderByCreatedAtDesc(tutorUserId);
        double avgRating = allReviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
        
        profile.setRating(avgRating);
        profile.setReviewsCount(allReviews.size());
        tutorProfileRepository.save(profile);

        return AppMapper.toReviewDTO(saved);
    }

    public List<ReviewDTO> getTutorReviews(Long tutorUserId) {
        return reviewRepository.findByTutorIdOrderByCreatedAtDesc(tutorUserId).stream()
                .map(AppMapper::toReviewDTO)
                .collect(Collectors.toList());
    }
}
