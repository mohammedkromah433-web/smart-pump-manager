package com.waterpump.smartmanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "technicians")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialization; // e.g., "Electrical", "Hydraulic"
    private String phoneNumber;
}