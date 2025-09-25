package com.gym.gym_management_system.services;

import com.gym.gym_management_system.dtos.*;

public interface AdminService {
    UserDto createTrainer(CreateUserRequestDto requestDto);
    ClassTemplateDto createClassTemplate(ClassTemplateRequestDto requestDto);
    ScheduledClassDto scheduleClass(ScheduleClassRequestDto requestDto);
}