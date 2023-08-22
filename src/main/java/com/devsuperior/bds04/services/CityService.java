package com.devsuperior.bds04.services;

import com.devsuperior.bds04.components.CityMapper;
import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repsoitory.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;


    public CityDTO saveCity(CityDTO dto){
        try {
            City city = new City();
            CityMapper.dtoToEntity(dto, city);
            return CityMapper.entityToDto(repository.save(city));
        }catch (HttpClientErrorException.UnprocessableEntity e){
                e.getMessage();
        }catch (IllegalArgumentException e){
            e.getStackTrace();
        }catch (NullPointerException e){
            e.getMessage();
        }
        return null;
    }

    public List<CityDTO> getlAllCities(){
        return repository.findAll(Sort.by("name")).stream().map(CityMapper::entityToDto).collect(Collectors.toList());
    }

    public CityDTO getCityById(Long id){
        return CityMapper.entityToDto(repository.findById(id).orElseThrow(() -> new RuntimeException("Erro")));
    }

    public CityDTO updateCity(Long id, CityDTO dto){
        try{
            City city = repository.findById(id).orElseThrow(() -> new RuntimeException("Nenhum registro encontrado"));
            if(dto.getId() != null){
                throw new RuntimeException("O body não deve conter Id");
            }
            city.setName(dto.getName());
            city = repository.save(city);
            return CityMapper.entityToDto(city);
        }catch (IllegalArgumentException | NullPointerException e){
            e.getMessage();
        }
        return null;
    }

    public void deleteCity(Long id){
        try{
            repository.deleteById(id);
        }catch (
        EmptyResultDataAccessException e) {
            throw new RuntimeException("Id not found");
        }catch(
        DataIntegrityViolationException e) {
            throw new RuntimeException("Integrity Violation");
        }
    }


}
