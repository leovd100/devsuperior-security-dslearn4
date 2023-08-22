package com.devsuperior.bds04.components;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {

    public static void dtoToEntity (CityDTO dto, City entity){
        entity.setName(dto.getName());
    }

    public static City dtoToEntity (CityDTO dto){
        City city = new City();
        city.setName(dto.getName());
        city.setId(dto.getId());
        return city;
    }


    public static CityDTO entityToDto(City entity){
        CityDTO dto = new CityDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

}
