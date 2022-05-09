package com.bureau.mapper;

import com.bureau.model.dto.request.entities.ClientDto;
import com.bureau.model.dto.response.ClientResponse;
import com.bureau.model.entity.Client;
import com.bureau.model.entity.Client.ClientBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-13T11:28:47+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client clientDtoToClient(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        ClientBuilder client = Client.builder();

        client.fullName( clientDto.getFullName() );
        client.email( clientDto.getEmail() );
        client.phone( clientDto.getPhone() );
        client.organization( clientDto.getOrganization() );

        return client.build();
    }

    @Override
    public ClientResponse clientToClientResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        Long id = null;
        String fullName = null;
        String email = null;
        String phone = null;
        String organization = null;

        id = client.getId();
        fullName = client.getFullName();
        email = client.getEmail();
        phone = client.getPhone();
        organization = client.getOrganization();

        ClientResponse clientResponse = new ClientResponse( id, fullName, email, phone, organization );

        return clientResponse;
    }

    @Override
    public void updateClientWithClientDto(ClientDto clientDto, Client client) {
        if ( clientDto == null ) {
            return;
        }

        client.setFullName( clientDto.getFullName() );
        client.setEmail( clientDto.getEmail() );
        client.setPhone( clientDto.getPhone() );
        client.setOrganization( clientDto.getOrganization() );
    }
}
