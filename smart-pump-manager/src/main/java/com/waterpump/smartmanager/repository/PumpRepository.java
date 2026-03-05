package com.waterpump.smartmanager.repository;

import com.waterpump.smartmanager.model.Pump;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PumpRepository extends JpaRepository<Pump, Long> {

    // 1. Find all pumps that have a specific status (e.g., "BROKEN")
    // SQL equivalent: SELECT * FROM pumps WHERE status = ?
    List<Pump> findByStatus(String status);

    // 2. Find pumps by location
    // SQL equivalent: SELECT * FROM pumps WHERE location = ?
    List<Pump> findByLocation(String location);

    // 3. Find pumps where flow rate is lower than a threshold (Alert Logic)
    // SQL equivalent: SELECT * FROM pumps WHERE flow_rate < ?
    List<Pump> findByFlowRateLessThan(Double threshold);
}