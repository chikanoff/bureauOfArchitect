package com.bureau.service;

import com.bureau.exception.ItemNotFoundException;
import com.bureau.mapper.ClientMapper;
import com.bureau.model.dto.request.entities.ClientDto;
import com.bureau.model.dto.response.ClientResponse;
import com.bureau.model.entity.Client;
import com.bureau.repository.ClientRepository;
import com.bureau.util.ErrorStatusCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public Page<ClientResponse> findPage(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::clientToClientResponse);
    }

    public ClientResponse findById(Long id) {
        return clientMapper.clientToClientResponse(findOrThrow(id));
    }

    public void create(ClientDto clientDto) {
        clientRepository.save(clientMapper.clientDtoToClient(clientDto));
    }

    public void update(Long id, ClientDto clientDto) {
        Client client = findOrThrow(id);
        clientMapper.updateClientWithClientDto(clientDto, client);
        clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.delete(findOrThrow(id));
    }

    public Client findOrThrow(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Client with id " + id + " not found", ErrorStatusCodes.CLIENT_NOT_FOUND));
    }
}
