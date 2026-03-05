package com.waterpump.smartmanager.service;

import com.waterpump.smartmanager.model.MaintenanceRecord;
import com.waterpump.smartmanager.model.Pump;
import com.waterpump.smartmanager.model.Technician;
import com.waterpump.smartmanager.repository.MaintenanceRecordRepository;
import com.waterpump.smartmanager.repository.PumpRepository;
import com.waterpump.smartmanager.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PumpService {

    @Autowired
    private PumpRepository pumpRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private MaintenanceRecordRepository maintenanceRepository;

    public List<Pump> getAllPumps() {
        return pumpRepository.findAll();
    }

    public Pump savePump(Pump pump) {
        if (pump.getFlowRate() != null && pump.getFlowRate() <= 0) {
            pump.setStatus("BROKEN");
        } else if (pump.getStatus() == null) {
            pump.setStatus("WORKING");
        }
        return pumpRepository.save(pump);
    }

    public List<Pump> getBrokenPumps() {
        return pumpRepository.findByStatus("BROKEN");
    }

    public List<Pump> getPumpsByLocation(String location) {
        return pumpRepository.findByLocation(location);
    }

    public List<Pump> getLowPressureAlerts(Double threshold) {
        return pumpRepository.findByFlowRateLessThan(threshold);
    }
    // Inside PumpService.java

    public Pump markAsFixed(Long id) {
        Pump pump = pumpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pump not found"));

        pump.setStatus("WORKING");
        pump.setFlowRate(50.0); // Reset to standard operational flow
        return pumpRepository.save(pump);
    }

    // NEW: Logic to link a Pump and a Technician via a Maintenance Record
    public MaintenanceRecord assignRepair(Long pumpId, Long techId, String description) {
        // Find the pump or throw error
        Pump pump = pumpRepository.findById(pumpId)
                .orElseThrow(() -> new RuntimeException("Pump ID " + pumpId + " not found"));

        // Find the technician or throw error
        Technician tech = technicianRepository.findById(techId)
                .orElseThrow(() -> new RuntimeException("Technician ID " + techId + " not found"));

        // Create the link
        MaintenanceRecord record = new MaintenanceRecord();
        record.setPump(pump);
        record.setTechnician(tech);
        record.setDescription(description);
        record.setScheduledDate(LocalDateTime.now());

        return maintenanceRepository.save(record);
    }

    public void deletePump(Long id) {
        pumpRepository.deleteById(id);
    }

    // Inside PumpService.java

    public Pump updatePump(Long id, Pump updatedPump) {
        Pump existingPump = pumpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pump not found"));

        existingPump.setName(updatedPump.getName());
        existingPump.setLocation(updatedPump.getLocation());
        existingPump.setModelNo(updatedPump.getModelNo());
        existingPump.setFlowRate(updatedPump.getFlowRate());

        // Logic: Recalculate status based on new flow rate
        if (existingPump.getFlowRate() <= 0) {
            existingPump.setStatus("BROKEN");
        } else {
            existingPump.setStatus("WORKING");
        }

        return pumpRepository.save(existingPump);
    }

// deletePump(Long id) should already be in your service from previous steps
}