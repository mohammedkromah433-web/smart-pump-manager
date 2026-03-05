package com.waterpump.smartmanager.controller;

import com.waterpump.smartmanager.model.MaintenanceRecord;
import com.waterpump.smartmanager.model.Pump;
import com.waterpump.smartmanager.service.PumpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pumps")
public class PumpController {

    @Autowired
    private PumpService pumpService;

    // 1. READ: Get all pumps
    @GetMapping
    public List<Pump> getPumps() {
        return pumpService.getAllPumps();
    }

    // 2. CREATE: Add a new pump
    @PostMapping
    public Pump createPump(@RequestBody Pump pump) {
        return pumpService.savePump(pump);
    }

    // 3. UPDATE: Edit an existing pump
    @PutMapping("/{id}")
    public Pump updatePump(@PathVariable Long id, @RequestBody Pump pump) {
        return pumpService.updatePump(id, pump);
    }

    // 4. UPDATE: Specifically mark a pump as fixed (Resetting status)
    @PutMapping("/{id}/fix")
    public Pump fixPump(@PathVariable Long id) {
        return pumpService.markAsFixed(id);
    }

    // 5. DELETE: Remove a pump
    @DeleteMapping("/{id}")
    public void deletePump(@PathVariable Long id) {
        pumpService.deletePump(id);
    }

    // 6. ACTION: Assign a technician to a pump repair
    @PostMapping("/{pumpId}/assign/{techId}")
    public MaintenanceRecord assignTechnician(
            @PathVariable Long pumpId,
            @PathVariable Long techId,
            @RequestParam String description) {

        return pumpService.assignRepair(pumpId, techId, description);
    }

    // 7. FILTER: Get broken pumps for alerts
    @GetMapping("/alerts")
    public List<Pump> getBrokenPumps() {
        return pumpService.getBrokenPumps();
    }
}