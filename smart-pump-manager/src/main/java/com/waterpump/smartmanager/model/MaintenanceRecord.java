package com.waterpump.smartmanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private LocalDateTime scheduledDate;

    // Many records can belong to one pump
    @ManyToOne
    @JoinColumn(name = "pump_id")
    private Pump pump;

    // Many records can be assigned to one technician
    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;
}