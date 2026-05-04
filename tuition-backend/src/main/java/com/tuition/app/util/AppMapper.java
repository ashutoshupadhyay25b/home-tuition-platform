package com.tuition.app.util;

import com.tuition.app.dto.RequestDTO;
import com.tuition.app.dto.TutorProfileDTO;
import com.tuition.app.dto.UserDTO;
import com.tuition.app.entity.Request;
import com.tuition.app.entity.TutorProfile;
import com.tuition.app.entity.User;

public class AppMapper {

    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    public static TutorProfileDTO toTutorProfileDTO(TutorProfile profile) {
        if (profile == null) return null;
        TutorProfileDTO dto = new TutorProfileDTO();
        dto.setId(profile.getId());
        dto.setUserId(profile.getUser().getId());
        dto.setUserName(profile.getUser().getName());
        dto.setSubjects(profile.getSubjects());
        dto.setClassLevel(profile.getClassLevel());
        dto.setHourlyRate(profile.getHourlyRate());
        dto.setBio(profile.getBio());
        dto.setExperience(profile.getExperience());
        dto.setTeachingMode(profile.getTeachingMode());
        return dto;
    }

    public static RequestDTO toRequestDTO(Request request) {
        if (request == null) return null;
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
}
