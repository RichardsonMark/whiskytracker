package com.example.codeclan.whiskytracker.repositories;

import com.example.codeclan.whiskytracker.models.Whisky;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhiskyRepository extends JpaRepository<Whisky, Long> {
    List<Whisky> findByYear(Integer year);

    List<Whisky> findByDistilleryRegionIgnoreCase(String distilleryRegion);

    List<Whisky> findByDistilleryNameAndAge(String distilleryName, Integer whiskyAge);

    List<Whisky> findByAge(Integer whiskyAge);

    List<Whisky> findByDistilleryNameIgnoreCase(String distilleryName);
}
