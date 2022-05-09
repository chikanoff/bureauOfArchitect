package com.bureau.mapper;

import com.bureau.model.dto.request.entities.CityDto;
import com.bureau.model.dto.response.CityResponse;
import com.bureau.model.entity.City;
import com.bureau.model.entity.City.CityBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-13T11:28:47+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class CityMapperImpl implements CityMapper {

    @Override
    public City cityDtoToCity(CityDto cityDto) {
        if ( cityDto == null ) {
            return null;
        }

        CityBuilder city = City.builder();

        city.name( cityDto.getName() );

        return city.build();
    }

    @Override
    public CityResponse cityToCityResponse(City city) {
        if ( city == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = city.getId();
        name = city.getName();

        CityResponse cityResponse = new CityResponse( id, name );

        return cityResponse;
    }

    @Override
    public void updateWithCityDto(CityDto cityDto, City city) {
        if ( cityDto == null ) {
            return;
        }

        city.setName( cityDto.getName() );
    }
}
