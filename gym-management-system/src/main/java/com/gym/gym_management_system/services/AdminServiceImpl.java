package com.gym.gym_management_system.services;

import com.gym.gym_management_system.dtos.*;
import com.gym.gym_management_system.entities.ClassTemplate;
import com.gym.gym_management_system.entities.ScheduledClass;
import com.gym.gym_management_system.entities.User;
import com.gym.gym_management_system.exceptions.BookingException;
import com.gym.gym_management_system.repositories.ClassTemplateRepository;
import com.gym.gym_management_system.repositories.ScheduledClassRepository;
import com.gym.gym_management_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gym.gym_management_system.exceptions.ResourceNotFoundException;
import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ClassTemplateRepository classTemplateRepository;
    private final ScheduledClassRepository scheduledClassRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ClassTemplateRepository classTemplateRepository,ScheduledClassRepository scheduledClassRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.classTemplateRepository = classTemplateRepository;

        this.scheduledClassRepository = scheduledClassRepository;
    }
    @Override
    public ScheduledClassDto scheduleClass(ScheduleClassRequestDto requestDto) {
        // Find the template and trainer, or throw an exception if not found
        ClassTemplate classTemplate = classTemplateRepository.findById(requestDto.getClassTemplateId())
                .orElseThrow(() -> new ResourceNotFoundException("ClassTemplate not found"));

        User trainer = userRepository.findById(requestDto.getTrainerId())
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found"));

        // Verify the user is actually a trainer
        if (!trainer.getRole().equals(User.Role.TRAINER)) {
            throw new BookingException("User with ID " + trainer.getId() + " is not a trainer.");
        }

        // Create the new ScheduledClass entity
        ScheduledClass newScheduledClass = new ScheduledClass();
        newScheduledClass.setClassTemplate(classTemplate);
        newScheduledClass.setTrainer(trainer);
        newScheduledClass.setStartTime(requestDto.getStartTime());
        newScheduledClass.setCapacity(requestDto.getCapacity());

        // Calculate the end time based on the template's duration
        LocalDateTime endTime = requestDto.getStartTime().plusMinutes(classTemplate.getDurationMinutes());
        newScheduledClass.setEndTime(endTime);

        ScheduledClass savedClass = scheduledClassRepository.save(newScheduledClass);
        return convertToDto(savedClass);
    }
    @Override
    public ClassTemplateDto createClassTemplate(ClassTemplateRequestDto requestDto) {
        ClassTemplate classTemplate = new ClassTemplate();
        classTemplate.setClassName(requestDto.getClassName());
        classTemplate.setDescription(requestDto.getDescription());
        classTemplate.setDurationMinutes(requestDto.getDurationMinutes());

        ClassTemplate savedTemplate = classTemplateRepository.save(classTemplate);

        return convertToDto(savedTemplate);
    }

    @Override
    public UserDto createTrainer(CreateUserRequestDto requestDto) {
        // Check if user already exists
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new BookingException("Email is already in use.");
        }

        User trainer = new User();
        trainer.setFirstName(requestDto.getFirstName());
        trainer.setLastName(requestDto.getLastName());
        trainer.setEmail(requestDto.getEmail());
        trainer.setRole(User.Role.TRAINER);
        trainer.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        User savedTrainer = userRepository.save(trainer);

        return convertToDto(savedTrainer);
    }
    private ClassTemplateDto convertToDto(ClassTemplate classTemplate) {
        ClassTemplateDto dto = new ClassTemplateDto();
        dto.setId(classTemplate.getId());
        dto.setClassName(classTemplate.getClassName());
        dto.setDescription(classTemplate.getDescription());
        dto.setDurationMinutes(classTemplate.getDurationMinutes());
        return dto;
    }

        private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
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