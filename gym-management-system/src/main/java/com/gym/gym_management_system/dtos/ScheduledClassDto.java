package com.gym.gym_management_system.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ScheduledClassDto {
    private Long id;
    private String className;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer durationMinutes;
    private Integer capacity;
    private TrainerDto trainer;
}