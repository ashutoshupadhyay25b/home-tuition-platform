package com.tuition.app.service;

import com.tuition.app.dto.TutorProfileDTO;
import com.tuition.app.entity.TutorProfile;
import com.tuition.app.entity.User;
import com.tuition.app.repository.TutorProfileRepository;
import com.tuition.app.repository.UserRepository;
import com.tuition.app.util.AppMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorProfileRepository tutorProfileRepository;
    private final UserRepository userRepository;

    public TutorProfileDTO saveProfile(Long userId, TutorProfile profile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        profile.setUser(user);
        TutorProfile savedProfile = tutorProfileRepository.save(profile);
        return AppMapper.toTutorProfileDTO(savedProfile);
    }

    public List<TutorProfileDTO> searchTutors(String subjects, String classLevel, String city, Double minPrice, Double maxPrice) {
        String subjectsParam = (subjects != null && !subjects.isEmpty()) ? subjects : null;
        String classLevelParam = (classLevel != null && !classLevel.isEmpty()) ? classLevel : null;
        String cityParam = (city != null && !city.isEmpty()) ? city : null;

        // Default to first 50 results for performance
        Pageable pageable = PageRequest.of(0, 50);

        return tutorProfileRepository.searchTutors(subjectsParam, classLevelParam, cityParam, minPrice, maxPrice, pageable)
                .getContent().stream()
                .map(AppMapper::toTutorProfileDTO)
                .collect(Collectors.toList());
    }

    public TutorProfileDTO getProfile(Long userId) {
        TutorProfile profile = tutorProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return AppMapper.toTutorProfileDTO(profile);
    }
}
