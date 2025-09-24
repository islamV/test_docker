package com.example.project.user;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String fullName;
    private String role;
    private Boolean active;

}