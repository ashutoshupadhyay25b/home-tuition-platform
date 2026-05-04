package com.tuition.app.service;

import com.tuition.app.dto.RequirementDTO;
import com.tuition.app.entity.Requirement;
import com.tuition.app.entity.User;
import com.tuition.app.repository.RequirementRepository;
import com.tuition.app.repository.UserRepository;
import com.tuition.app.util.AppMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequirementService {

    private final RequirementRepository requirementRepository;
    private final UserRepository userRepository;

    public RequirementDTO createRequirement(Long studentId, Requirement requirement) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        requirement.setStudent(student);
        Requirement saved = requirementRepository.save(requirement);
        return AppMapper.toRequirementDTO(saved);
    }

    public List<RequirementDTO> getAllActiveRequirements() {
        return requirementRepository.findByActiveTrueOrderByCreatedAtDesc().stream()
                .map(AppMapper::toRequirementDTO)
                .collect(Collectors.toList());
    }

    public List<RequirementDTO> getRequirementsByStudent(Long studentId) {
        return requirementRepository.findByStudentIdOrderByCreatedAtDesc(studentId).stream()
                .map(AppMapper::toRequirementDTO)
                .collect(Collectors.toList());
    }
}
