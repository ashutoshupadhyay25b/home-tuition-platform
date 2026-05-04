package com.tuition.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String classLevel;
    private String city;
    
    @Column(length = 1000)
    private String description;

    private Double budget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    private LocalDateTime createdAt = LocalDateTime.now();
    
    private boolean active = true;
}
