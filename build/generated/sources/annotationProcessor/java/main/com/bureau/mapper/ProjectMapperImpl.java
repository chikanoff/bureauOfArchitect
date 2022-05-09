package com.bureau.mapper;

import com.bureau.model.dto.request.entities.ProjectDto;
import com.bureau.model.dto.response.CityResponse;
import com.bureau.model.dto.response.ClientResponse;
import com.bureau.model.dto.response.ProjectResponse;
import com.bureau.model.dto.response.UserInfoResponse;
import com.bureau.model.dto.response.UserRoleResponse;
import com.bureau.model.entity.City;
import com.bureau.model.entity.Client;
import com.bureau.model.entity.Project;
import com.bureau.model.entity.Project.ProjectBuilder;
import com.bureau.model.entity.User;
import com.bureau.model.entity.UserRole;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-13T12:57:14+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public Project projectDtoToProject(ProjectDto projectDto) {
        if ( projectDto == null ) {
            return null;
        }

        ProjectBuilder project = Project.builder();

        project.name( projectDto.getName() );
        project.date( projectDto.getDate() );
        project.notes( projectDto.getNotes() );
        project.address( projectDto.getAddress() );
        project.active( projectDto.isActive() );
        project.projectUrl( projectDto.getProjectUrl() );

        return project.build();
    }

    @Override
    public ProjectResponse projectToProjectResponse(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectResponse projectResponse = new ProjectResponse();

        projectResponse.setId( project.getId() );
        projectResponse.setName( project.getName() );
        projectResponse.setType( project.getType() );
        projectResponse.setCity( cityToCityResponse( project.getCity() ) );
        projectResponse.setClient( clientToClientResponse( project.getClient() ) );
        projectResponse.setDate( project.getDate() );
        projectResponse.setNotes( project.getNotes() );
        projectResponse.setAddress( project.getAddress() );
        projectResponse.setActive( project.isActive() );
        projectResponse.setProjectUrl( project.getProjectUrl() );
        projectResponse.setUsers( userSetToUserInfoResponseSet( project.getUsers() ) );

        return projectResponse;
    }

    @Override
    public void updateProjectWithProjectDto(ProjectDto projectDto, Project project) {
        if ( projectDto == null ) {
            return;
        }

        project.setName( projectDto.getName() );
        project.setDate( projectDto.getDate() );
        project.setNotes( projectDto.getNotes() );
        project.setAddress( projectDto.getAddress() );
        project.setActive( projectDto.isActive() );
        project.setProjectUrl( projectDto.getProjectUrl() );
    }

    @Override
    public List<ProjectResponse> projectListToProjectResponseList(List<Project> projectList) {
        if ( projectList == null ) {
            return null;
        }

        List<ProjectResponse> list = new ArrayList<ProjectResponse>( projectList.size() );
        for ( Project project : projectList ) {
            list.add( projectToProjectResponse( project ) );
        }

        return list;
    }

    protected CityResponse cityToCityResponse(City city) {
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

    protected ClientResponse clientToClientResponse(Client client) {
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

    protected UserRoleResponse userRoleToUserRoleResponse(UserRole userRole) {
        if ( userRole == null ) {
            return null;
        }

        UserRoleResponse userRoleResponse = new UserRoleResponse();

        userRoleResponse.setId( userRole.getId() );
        userRoleResponse.setName( userRole.getName() );

        return userRoleResponse;
    }

    protected UserInfoResponse userToUserInfoResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserInfoResponse userInfoResponse = new UserInfoResponse();

        userInfoResponse.setId( user.getId() );
        userInfoResponse.setFullName( user.getFullName() );
        userInfoResponse.setUsername( user.getUsername() );
        userInfoResponse.setEmail( user.getEmail() );
        userInfoResponse.setRole( userRoleToUserRoleResponse( user.getRole() ) );

        return userInfoResponse;
    }

    protected Set<UserInfoResponse> userSetToUserInfoResponseSet(Set<User> set) {
        if ( set == null ) {
            return null;
        }

        Set<UserInfoResponse> set1 = new HashSet<UserInfoResponse>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( User user : set ) {
            set1.add( userToUserInfoResponse( user ) );
        }

        return set1;
    }
}
