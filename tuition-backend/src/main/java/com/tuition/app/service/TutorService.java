package com.tuition.app.service;

import com.tuition.app.entity.TutorProfile;
import com.tuition.app.entity.User;
import com.tuition.app.entity.Role;
import com.tuition.app.repository.TutorProfileRepository;
import com.tuition.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorService {

    private final TutorProfileRepository tutorProfileRepository;
    private final UserRepository userRepository;

    public TutorProfile createTutorProfile(Long userId, TutorProfile profile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
                
        if (user.getRole() != Role.TUTOR) {
            throw new RuntimeException("User is not a tutor");
        }

        profile.setUser(user);
        return tutorProfileRepository.save(profile);
    }

    public List<TutorProfile> searchTutors(String subject, String classLevel) {
        return tutorProfileRepository.findBySubjectContainingIgnoreCaseAndClassLevelIgnoreCase(subject, classLevel);
    }
}
