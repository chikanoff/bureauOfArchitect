package com.bureau.mapper;

import com.bureau.model.dto.request.entities.CityDto;
import com.bureau.model.dto.response.CityResponse;
import com.bureau.model.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CityMapper {
    @Mapping(target = "id", ignore = true)
    City cityDtoToCity(CityDto cityDto);
    CityResponse cityToCityResponse(City city);

    @Mapping(target = "id", ignore = true)
    void updateWithCityDto(CityDto cityDto, @MappingTarget City city);
}
