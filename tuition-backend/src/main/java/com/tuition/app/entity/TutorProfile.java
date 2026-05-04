package com.tuition.app.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tutor_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    private String subjects;
    
    private String classLevel;
    
    private Double hourlyRate;
    
    @Column(columnDefinition = "TEXT")
    private String bio;
    
    private Integer experience;
    
    private String teachingMode;
}
