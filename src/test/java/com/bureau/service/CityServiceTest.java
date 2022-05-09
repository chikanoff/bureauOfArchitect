package com.bureau.service;

import com.bureau.IntegrationTestBase;
import com.bureau.model.dto.request.entities.CityDto;
import com.bureau.model.entity.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CityServiceTest extends IntegrationTestBase {
    @Autowired
    private CityService cityService;

    @Test
    public void getByIdTest() {
        City city = createTestCity();
        assertThat(cityService.getById(city.getId()).getName()).isEqualTo(city.getName());
    }

    @Test
    public void createCityTest() {
        CityDto dto = new CityDto();
        dto.setName("test");
        cityService.create(dto);
        assertThat(getCityRepository().findAll().size()).isEqualTo(1);
    }

    @Test
    public void updateCityTest() {
        City city = createTestCity();
        CityDto dto = new CityDto();
        dto.setName("test");
        cityService.update(city.getId(), dto);
        assertThat(getCityRepository().findById(city.getId()).get().getName()).isEqualTo(dto.getName());
    }
}
