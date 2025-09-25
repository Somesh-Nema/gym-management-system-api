package com.gym.gym_management_system.dtos;

import lombok.Data;

@Data
public class BookingRequestDto {
    private Long memberId;
    private Long scheduledClassId;
}