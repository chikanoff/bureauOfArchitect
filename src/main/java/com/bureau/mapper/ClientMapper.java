package com.bureau.mapper;

import com.bureau.model.dto.request.entities.ClientDto;
import com.bureau.model.dto.response.ClientResponse;
import com.bureau.model.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "id", ignore = true)
    Client clientDtoToClient(ClientDto clientDto);
    ClientResponse clientToClientResponse(Client client);

    @Mapping(target = "id", ignore = true)
    void updateClientWithClientDto(ClientDto clientDto, @MappingTarget Client client);
}
