package com.example.codeclan.whiskytracker.repositories;

import com.example.codeclan.whiskytracker.models.Distillery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistilleryRepository extends JpaRepository<Distillery, Long> {
}
