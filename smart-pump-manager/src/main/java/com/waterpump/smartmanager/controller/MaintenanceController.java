package com.waterpump.smartmanager.controller;

import com.waterpump.smartmanager.model.MaintenanceRecord;
import com.waterpump.smartmanager.repository.MaintenanceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    @Autowired
    private MaintenanceRecordRepository maintenanceRepository;

    @GetMapping
    public List<MaintenanceRecord> getAllRecords() {
        return maintenanceRepository.findAll();
    }
}