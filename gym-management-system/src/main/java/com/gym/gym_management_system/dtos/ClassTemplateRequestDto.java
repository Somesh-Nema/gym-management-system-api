// File: src/main/java/com/gym/gym_management_system/dtos/ClassTemplateRequestDto.java
package com.gym.gym_management_system.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClassTemplateRequestDto {
    @NotBlank(message = "Class name cannot be blank")
    private String className;

    private String description;

    @NotNull(message = "Duration cannot be null")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer durationMinutes;
}