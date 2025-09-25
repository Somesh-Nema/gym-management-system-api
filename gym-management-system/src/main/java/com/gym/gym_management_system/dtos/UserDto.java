package com.gym.gym_management_system.dtos;

import com.gym.gym_management_system.entities.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private User.Role role;
}