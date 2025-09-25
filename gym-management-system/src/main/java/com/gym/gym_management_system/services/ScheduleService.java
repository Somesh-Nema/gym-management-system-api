package com.gym.gym_management_system.services;

import com.gym.gym_management_system.dtos.ScheduledClassDto;
import java.util.List;

public interface ScheduleService {
    List<ScheduledClassDto> findAllScheduledClasses();
}