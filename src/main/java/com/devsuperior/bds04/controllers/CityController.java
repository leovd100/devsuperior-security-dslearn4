package com.devsuperior.bds04.controllers;


import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService service;

    @PostMapping
    public ResponseEntity<CityDTO> postCity( @Valid @RequestBody CityDTO dto){

       return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCity(dto));
    }

    @GetMapping
    public ResponseEntity<List<CityDTO>> getCity(){
        return ResponseEntity.ok(service.getlAllCities());
    }

}
