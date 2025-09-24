package com.example.project.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @JsonProperty("full_name")
    private String fullName;

    @NotBlank
    private String role;

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

    private Boolean active = true;
}
