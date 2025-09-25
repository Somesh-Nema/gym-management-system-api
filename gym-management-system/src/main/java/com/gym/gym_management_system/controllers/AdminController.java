package com.gym.gym_management_system.controllers;

import com.gym.gym_management_system.dtos.*;
import com.gym.gym_management_system.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/trainers")
    public ResponseEntity<UserDto> createTrainer(@Valid @RequestBody CreateUserRequestDto requestDto) {
        UserDto createdTrainer = adminService.createTrainer(requestDto);
        return new ResponseEntity<>(createdTrainer, HttpStatus.CREATED);
    }
    @PostMapping("/class-templates")
    public ResponseEntity<ClassTemplateDto> createClassTemplate(@Valid @RequestBody ClassTemplateRequestDto requestDto) {
        ClassTemplateDto createdTemplate = adminService.createClassTemplate(requestDto);
        return new ResponseEntity<>(createdTemplate, HttpStatus.CREATED);
    }
    @PostMapping("/schedule")
    public ResponseEntity<ScheduledClassDto> scheduleClass(@Valid @RequestBody ScheduleClassRequestDto requestDto) {
        ScheduledClassDto scheduledClass = adminService.scheduleClass(requestDto);
        return new ResponseEntity<>(scheduledClass, HttpStatus.CREATED);
    }
}