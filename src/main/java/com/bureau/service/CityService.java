package com.bureau.service;

import com.bureau.exception.ItemNotFoundException;
import com.bureau.mapper.CityMapper;
import com.bureau.model.dto.request.entities.CityDto;
import com.bureau.model.dto.response.CityResponse;
import com.bureau.model.entity.City;
import com.bureau.repository.CityRepository;
import com.bureau.util.ErrorStatusCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public Page<CityResponse> getPage(Pageable pageable) {
        return cityRepository.findAll(pageable).map(cityMapper::cityToCityResponse);
    }

    public CityResponse getById(Long id) {
        return cityMapper.cityToCityResponse(findOrThrow(id));
    }

    public void create(CityDto cityDto) {
        cityRepository.save(cityMapper.cityDtoToCity(cityDto));
    }

    public void update(Long id, CityDto cityDto) {
        City city = findOrThrow(id);
        cityMapper.updateWithCityDto(cityDto, city);
        cityRepository.save(city);
    }

    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    public City findOrThrow(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("City with id " + id + " not found", ErrorStatusCodes.CITY_NOT_FOUND));
    }
}
