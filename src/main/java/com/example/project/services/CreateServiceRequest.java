package com.example.project.services;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class CreateServiceRequest {

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotBlank
    @Pattern(regexp = "^(hospital|hotel|club|cafe)$",
            message = "type must be one of: hospital, hotel, club, cafe")
    private String type;

    @Size(max = 300)
    private String address;

    @Size(max = 150)
    private String contactPerson;

    @Size(max = 50)
    private String phone;

    private MultipartFile photo;
}
