package com.gym.gym_management_system.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "class_templates")
public class ClassTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Lob
    private String description;

    @Column(name = "duration_minutes", nullable = false)
    private Integer durationMinutes;
}