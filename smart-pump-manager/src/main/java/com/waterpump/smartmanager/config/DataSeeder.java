package com.waterpump.smartmanager.config;

import com.waterpump.smartmanager.model.Pump;
import com.waterpump.smartmanager.model.Technician;
import com.waterpump.smartmanager.repository.PumpRepository;
import com.waterpump.smartmanager.repository.TechnicianRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(PumpRepository pumpRepository, TechnicianRepository technicianRepository) {
        return args -> {
            // 1. Seed Pumps if empty
            if (pumpRepository.count() == 0) {
                Pump p1 = new Pump("Main Station Alpha", "Downtown", "PRO-X1", "WORKING", 45.5);
                Pump p2 = new Pump("Substation Beta", "North Hills", "STD-V2", "MAINTENANCE", 12.0);
                Pump p3 = new Pump("Emergency Pump 1", "Industrial Zone", "EMG-01", "BROKEN", 0.0);

                pumpRepository.saveAll(List.of(p1, p2, p3));
                System.out.println("✅ Pumps seeded.");
            }

            // 2. Seed Technicians if empty
            if (technicianRepository.count() == 0) {
                Technician t1 = new Technician(null, "John Smith", "Hydraulic Specialist", "555-0123");
                Technician t2 = new Technician(null, "Sarah Connor", "Electrical Engineer", "555-0987");

                technicianRepository.saveAll(List.of(t1, t2));
                System.out.println("✅ Technicians seeded.");
            }
        };
    }
}