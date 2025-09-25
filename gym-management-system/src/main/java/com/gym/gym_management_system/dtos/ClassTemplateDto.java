// File: src/main/java/com/gym/gym_management_system/dtos/ClassTemplateDto.java
package com.gym.gym_management_system.dtos;

import lombok.Data;

@Data
public class ClassTemplateDto {
    private Long id;
    private String className;
    private String description;
    private Integer durationMinutes;
}