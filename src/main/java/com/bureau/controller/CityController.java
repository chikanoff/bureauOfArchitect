package com.bureau.controller;

import com.bureau.model.dto.request.entities.CityDto;
import com.bureau.model.dto.response.CityResponse;
import com.bureau.service.CityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/city")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Tag(name = "Cities configuration API")
@SecurityRequirement(name = "security")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    public Page<CityResponse> getPage(Pageable pageable) {
        return cityService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public CityResponse getById(@PathVariable Long id) {
        return cityService.getById(id);
    }

    @PostMapping
    public void create(@Valid @RequestBody CityDto cityDto) {
        cityService.create(cityDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody CityDto cityDto) {
        cityService.update(id, cityDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }
}
