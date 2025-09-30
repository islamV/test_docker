package com.example.project.upload.service;

import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class FilesInitRunner implements CommandLineRunner {

    @Resource
    private FilesStorageService storageService;

    @Override
    public void run(String... args) throws Exception {
        storageService.init();
    }
}
