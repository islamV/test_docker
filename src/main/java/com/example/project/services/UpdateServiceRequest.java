package com.example.project.services;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter @Setter
public class UpdateServiceRequest {

    @Size(max = 200)
    private String name;
    private MultipartFile photo;}
