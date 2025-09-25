package com.gym.gym_management_system.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleClassRequestDto {

    @NotNull
    private Long classTemplateId;

    @NotNull
    private Long trainerId;

    @NotNull
    @Future(message = "Start time must be in the future")
    private LocalDateTime startTime;

    @NotNull
    @Min(value = 1, message = "Capacity must be at least 1")
    private Integer capacity;
}