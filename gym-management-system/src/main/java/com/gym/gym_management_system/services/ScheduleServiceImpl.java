package com.gym.gym_management_system.services;

import com.gym.gym_management_system.dtos.ScheduledClassDto;
import com.gym.gym_management_system.dtos.TrainerDto;
import com.gym.gym_management_system.entities.ScheduledClass;
import com.gym.gym_management_system.repositories.ScheduledClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduledClassRepository scheduledClassRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduledClassRepository scheduledClassRepository) {
        this.scheduledClassRepository = scheduledClassRepository;
    }

    @Override
    public List<ScheduledClassDto> findAllScheduledClasses() {
        List<ScheduledClass> scheduledClasses = scheduledClassRepository.findAllByOrderByStartTimeAsc();

        return scheduledClasses.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ScheduledClassDto convertToDto(ScheduledClass scheduledClass) {
        ScheduledClassDto dto = new ScheduledClassDto();
        dto.setId(scheduledClass.getId());
        dto.setClassName(scheduledClass.getClassTemplate().getClassName());
        dto.setStartTime(scheduledClass.getStartTime());
        dto.setEndTime(scheduledClass.getEndTime());
        dto.setDurationMinutes(scheduledClass.getClassTemplate().getDurationMinutes());
        dto.setCapacity(scheduledClass.getCapacity());

        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setId(scheduledClass.getTrainer().getId());
        trainerDto.setFirstName(scheduledClass.getTrainer().getFirstName());
        trainerDto.setLastName(scheduledClass.getTrainer().getLastName());
        dto.setTrainer(trainerDto);

        return dto;
    }
}