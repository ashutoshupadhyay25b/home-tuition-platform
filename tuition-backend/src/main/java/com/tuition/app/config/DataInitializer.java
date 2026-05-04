package com.tuition.app.config;

import com.tuition.app.entity.Role;
import com.tuition.app.entity.TutorProfile;
import com.tuition.app.entity.User;
import com.tuition.app.repository.TutorProfileRepository;
import com.tuition.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TutorProfileRepository tutorProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final Random random = new Random();

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() > 0) {
            return; // Data already exists
        }

        String[] cities = {"Mumbai", "Delhi", "Bangalore", "Hyderabad", "Pune", "Chennai", "Kolkata"};
        String[] subjects = {"Mathematics", "Physics", "Chemistry", "Biology", "English", "History", "Computer Science"};
        String[] levels = {"Class 9-10", "Class 11-12", "College/Degree"};

        // Create 20 Students
        for (int i = 1; i <= 20; i++) {
            User student = new User();
            student.setName("Student " + i);
            student.setEmail("student" + i + "@example.com");
            student.setPassword(passwordEncoder.encode("password123"));
            student.setRole(Role.STUDENT);
            userRepository.save(student);
        }

        // Create 20 Tutors with Profiles
        for (int i = 1; i <= 20; i++) {
            User tutorUser = new User();
            tutorUser.setName("Tutor " + i);
            tutorUser.setEmail("tutor" + i + "@example.com");
            tutorUser.setPassword(passwordEncoder.encode("password123"));
            tutorUser.setRole(Role.TUTOR);
            userRepository.save(tutorUser);

            TutorProfile profile = new TutorProfile();
            profile.setUser(tutorUser);
            profile.setCity(cities[random.nextInt(cities.length)]);
            profile.setSubjects(subjects[random.nextInt(subjects.length)]);
            profile.setClassLevel(levels[random.nextInt(levels.length)]);
            profile.setHourlyRate((double) (500 + random.nextInt(1500)));
            profile.setExperience(random.nextInt(15) + 1);
            profile.setRating(3.5 + (random.nextDouble() * 1.5));
            profile.setReviewsCount(random.nextInt(50));
            profile.setTeachingMode(random.nextBoolean() ? "Online" : "Home Tuition");
            profile.setBio("I am an experienced professional dedicated to helping students excel in " + profile.getSubjects() + ".");
            
            tutorProfileRepository.save(profile);
        }

        System.out.println("✅ Database seeded with 20 Students and 20 Tutors.");
    }
}
