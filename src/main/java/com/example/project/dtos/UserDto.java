package com.example.project.dtos;

import com.example.project.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter

public class UserDto {
    private Long id;
    private String fullName;
   private String email;
   private String phone;
   private Role role;
   private boolean active;
}