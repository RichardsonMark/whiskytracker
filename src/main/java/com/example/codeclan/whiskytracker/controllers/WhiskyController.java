package com.example.codeclan.whiskytracker.controllers;

import com.example.codeclan.whiskytracker.models.Whisky;
import com.example.codeclan.whiskytracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    // /whiskies/?year=2017  findByYear  -- works
    // /whiskies/?whiskyAge=12  findByAge  -- works
    // /whiskies/?distilleryName=Balvenie  findByDistilleryNameIgnoreCase  -- works
    // /whiskies/?distilleryRegion=Highland  findByDistilleryRegionIgnoreCase  -- works
    // /whiskies/?whiskyAge=12&distilleryName=Balvenie  findByDistilleryNameAndAge  -- works - adding IgnoreCase doesn't work

    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> getWhiskies(
            @RequestParam(name="year", required=false) Integer year,
            @RequestParam(name="whiskyAge", required=false) Integer whiskyAge,
            @RequestParam(name="distilleryName", required = false) String distilleryName,
            @RequestParam(name="distilleryRegion", required = false) String distilleryRegion
            )
    {
        if (year != null) {
            List<Whisky> whiskyByYear = whiskyRepository.findByYear(year);
            return new ResponseEntity<>(whiskyByYear, HttpStatus.OK);
        }
        if (distilleryRegion != null) {
            List<Whisky> whiskyByDistilleryRegion = whiskyRepository.findByDistilleryRegionIgnoreCase(distilleryRegion);
            return new ResponseEntity<>(whiskyByDistilleryRegion, HttpStatus.OK);
        }
        if (whiskyAge != null && distilleryName == null) {
            List<Whisky> whiskyByAge = whiskyRepository.findByAge(whiskyAge);
            return new ResponseEntity<>(whiskyByAge, HttpStatus.OK);
        }
        if (distilleryName != null && whiskyAge == null) {
            List<Whisky> whiskyByDistilleryName = whiskyRepository.findByDistilleryNameIgnoreCase(distilleryName);
            return new ResponseEntity<>(whiskyByDistilleryName, HttpStatus.OK);
        }
        if (distilleryName != null && whiskyAge != null) {
            List<Whisky> whiskyByNameAndAge = whiskyRepository.findByDistilleryNameAndAge(distilleryName, whiskyAge);
            return new ResponseEntity<>(whiskyByNameAndAge, HttpStatus.OK);
        }
        {
            List<Whisky> allWhiskies = whiskyRepository.findAll();
            return new ResponseEntity<>(allWhiskies, HttpStatus.OK);
        }
    }


    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity<Optional<Whisky>> getWhisky(Long whiskyId) {
        return new ResponseEntity<>(whiskyRepository.findById(whiskyId), HttpStatus.OK);
    }
}
