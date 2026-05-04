package com.tuition.app.service;

import com.tuition.app.dto.PaginatedResponse;
import com.tuition.app.dto.TutorProfileDTO;
import com.tuition.app.entity.TutorProfile;
import com.tuition.app.entity.User;
import com.tuition.app.repository.TutorProfileRepository;
import com.tuition.app.repository.UserRepository;
import com.tuition.app.repository.specification.TutorSpecification;
import com.tuition.app.util.AppMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        TutorProfile saved = tutorProfileRepository.save(profile);
        return AppMapper.toTutorProfileDTO(saved);
    }

    public TutorProfileDTO getProfile(Long id) {
        TutorProfile profile = tutorProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return AppMapper.toTutorProfileDTO(profile);
    }

    public PaginatedResponse<TutorProfileDTO> searchTutors(
            String subjects, String classLevel, String city, 
            Double minPrice, Double maxPrice, Pageable pageable) {
        
        Specification<TutorProfile> spec = Specification.where(TutorSpecification.hasSubject(subjects))
                .and(TutorSpecification.hasClassLevel(classLevel))
                .and(TutorSpecification.hasCity(city))
                .and(TutorSpecification.priceGreaterThan(minPrice))
                .and(TutorSpecification.priceLessThan(maxPrice));

        Page<TutorProfile> page = tutorProfileRepository.findAll(spec, pageable);
        
        List<TutorProfileDTO> content = page.getContent().stream()
                .map(AppMapper::toTutorProfileDTO)
                .collect(Collectors.toList());

        return new PaginatedResponse<>(
                content,
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber()
        );
    }
}
