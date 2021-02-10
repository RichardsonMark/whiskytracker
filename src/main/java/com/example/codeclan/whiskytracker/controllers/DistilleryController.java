package com.example.codeclan.whiskytracker.controllers;

import com.example.codeclan.whiskytracker.models.Distillery;
import com.example.codeclan.whiskytracker.models.Whisky;
import com.example.codeclan.whiskytracker.repositories.DistilleryRepository;
import com.example.codeclan.whiskytracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @Autowired
    WhiskyRepository whiskyRepository;

    // /distilleries/?region=Island  findByRegionIgnoreCase -- works

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleries(
            @RequestParam(name="region", required=false) String regionName
    )
    {
        if (regionName != null) {
            List<Distillery> distilleryByRegion = distilleryRepository.findByRegionIgnoreCase(regionName);
            return new ResponseEntity<>(distilleryByRegion, HttpStatus.OK);
        }
        {
            List<Distillery> allDistilleries = distilleryRepository.findAll();
            return new ResponseEntity<>(allDistilleries, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/distilleries/{id}")
    public ResponseEntity<Optional<Distillery>> getDistillery(@PathVariable Long distilleryId) {
        return new ResponseEntity<>(distilleryRepository.findById(distilleryId), HttpStatus.OK);
    }

}
