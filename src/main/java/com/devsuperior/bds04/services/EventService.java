package com.devsuperior.bds04.services;

import com.devsuperior.bds04.components.CityMapper;
import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repsoitory.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;


    @Autowired
    private CityService cityService;

    public Page<EventDTO> findAll(Pageable pageable){
        Page<Event> eventList = repository.findAll(pageable);
        return eventList.map(EventDTO::new) ;
    }


    public EventDTO insertEvent(EventDTO dto){
        try {
            Event event = new Event(dto);
            event.setCity(CityMapper.dtoToEntity(cityService.getCityById(dto.getCityId())));
            event = repository.save(event);
            return new EventDTO(event);
        }catch (IllegalArgumentException e){
            e.getMessage();
        }
        return null;
    }




}
