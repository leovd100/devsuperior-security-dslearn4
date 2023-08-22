package com.devsuperior.bds04.controllers;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService service;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAllEvents(Pageable pageable){
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<EventDTO> saveEvents(@Valid @RequestBody EventDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insertEvent(dto));
    }
}
