package com.waterpump.smartmanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pumps")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pump {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    @Column(name = "model_no") // This fixes the MySQL 'field missing' error
    private String modelNo;

    private String status; // WORKING, BROKEN, MAINTENANCE
    private Double flowRate;

    // Relationship: One Pump can have many Maintenance Records
    @OneToMany(mappedBy = "pump", cascade = CascadeType.ALL)
    @JsonIgnore // Prevents infinite loops when sending JSON to the browser
    private List<MaintenanceRecord> maintenanceRecords;

    // Constructor for the DataSeeder (without the ID and List)
    public Pump(String name, String location, String modelNo, String status, Double flowRate) {
        this.name = name;
        this.location = location;
        this.modelNo = modelNo;
        this.status = status;
        this.flowRate = flowRate;
    }
}