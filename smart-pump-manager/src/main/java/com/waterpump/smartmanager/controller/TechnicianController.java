package com.waterpump.smartmanager.controller;

import com.waterpump.smartmanager.model.Technician;
import com.waterpump.smartmanager.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/technicians")
public class TechnicianController {

    @Autowired
    private TechnicianRepository technicianRepository;

    // This method handles: GET http://localhost:8080/api/technicians
    @GetMapping
    public List<Technician> getAllTechnicians() {
        return technicianRepository.findAll();
    }
    // Inside TechnicianController.java
    @PostMapping
    public Technician createTechnician(@RequestBody Technician technician) {
        return technicianRepository.save(technician);
    }
}