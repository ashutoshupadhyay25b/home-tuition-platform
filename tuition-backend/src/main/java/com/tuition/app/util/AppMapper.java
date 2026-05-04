package com.tuition.app.util;

import com.tuition.app.dto.RequirementDTO;
import com.tuition.app.dto.RequestDTO;
import com.tuition.app.dto.ReviewDTO;
import com.tuition.app.dto.TutorProfileDTO;
import com.tuition.app.dto.UserDTO;
import com.tuition.app.entity.Requirement;
import com.tuition.app.entity.Request;
import com.tuition.app.entity.Review;
import com.tuition.app.entity.TutorProfile;
import com.tuition.app.entity.User;

public class AppMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

    public static TutorProfileDTO toTutorProfileDTO(TutorProfile profile) {
        TutorProfileDTO dto = new TutorProfileDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUser().getId());
        dto.setUserName(profile.getUser().getName());
        dto.setSubjects(profile.getSubjects());
        dto.setClassLevel(profile.getClassLevel());
        dto.setCity(profile.getCity());
        dto.setHourlyRate(profile.getHourlyRate());
        dto.setBio(profile.getBio());
        dto.setExperience(profile.getExperience());
        dto.setTeachingMode(profile.getTeachingMode());
        dto.setRating(profile.getRating() != null ? profile.getRating() : 0.0);
        dto.setReviewsCount(profile.getReviewsCount() != null ? profile.getReviewsCount() : 0);
        return dto;
    }

    public static RequestDTO toRequestDTO(Request request) {
        RequestDTO dto = new RequestDTO();
        dto.setId(request.getId());
        dto.setStudentId(request.getStudent().getId());
        dto.setStudentName(request.getStudent().getName());
        dto.setTutorId(request.getTutor().getId());
        dto.setTutorName(request.getTutor().getName());
        dto.setStatus(request.getStatus());
        dto.setCreatedAt(request.getCreatedAt());
        return dto;
    }

    public static RequirementDTO toRequirementDTO(Requirement requirement) {
        RequirementDTO dto = new RequirementDTO();
        dto.setId(requirement.getId());
        dto.setSubject(requirement.getSubject());
        dto.setClassLevel(requirement.getClassLevel());
        dto.setCity(requirement.getCity());
        dto.setDescription(requirement.getDescription());
        dto.setBudget(requirement.getBudget());
        dto.setStudentName(requirement.getStudent().getName());
        dto.setCreatedAt(requirement.getCreatedAt());
        dto.setActive(requirement.isActive());
        return dto;
    }

    public static ReviewDTO toReviewDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setStudentName(review.getStudent().getName());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }
}
