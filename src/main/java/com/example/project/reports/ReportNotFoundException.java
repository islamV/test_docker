package com.example.project.reports;

import com.example.project.common.ResourceNotFoundException;

public class ReportNotFoundException extends ResourceNotFoundException {
    public ReportNotFoundException(Long id) {
        super("Report " + id + " not found");
    }
}
