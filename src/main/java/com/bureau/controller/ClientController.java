package com.bureau.controller;

import com.bureau.model.dto.request.entities.ClientDto;
import com.bureau.model.dto.response.ClientResponse;
import com.bureau.model.entity.Client;
import com.bureau.service.ClientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
import java.util.List;

@RestController
@RequestMapping("/api/admin/client")
@PreAuthorize("hasRole('ROLE_ADMIN')")
@Tag(name = "Clients configuration API")
@SecurityRequirement(name = "security")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    @GetMapping
    public Page<ClientResponse> getPage(Pageable pageable) {
        return clientService.findPage(pageable);
    }
    @GetMapping("/all")
    public List<ClientResponse> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public ClientResponse getById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody ClientDto clientDto) {
        clientService.update(id, clientDto);
    }

    @PostMapping
    public void create(@Valid @RequestBody ClientDto clientDto) {
        clientService.create(clientDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clientService.delete(id);
    }
}
