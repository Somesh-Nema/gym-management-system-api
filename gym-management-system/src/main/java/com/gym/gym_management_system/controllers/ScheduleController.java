package com.gym.gym_management_system.controllers;

import com.gym.gym_management_system.dtos.ScheduledClassDto;
import com.gym.gym_management_system.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduledClassDto>> getAllScheduledClasses() {
        List<ScheduledClassDto> schedule = scheduleService.findAllScheduledClasses();
        return ResponseEntity.ok(schedule);
    }
}