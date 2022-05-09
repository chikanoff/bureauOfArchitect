package com.bureau.mapper;

import com.bureau.model.dto.request.entities.ProjectTypeDto;
import com.bureau.model.dto.response.ProjectTypeResponse;
import com.bureau.model.entity.ProjectType;
import com.bureau.model.entity.ProjectType.ProjectTypeBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-13T11:28:47+0300",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.jar, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class ProjectTypeMapperImpl implements ProjectTypeMapper {

    @Override
    public ProjectType projectTypeDtoToProjectType(ProjectTypeDto projectTypeDto) {
        if ( projectTypeDto == null ) {
            return null;
        }

        ProjectTypeBuilder projectType = ProjectType.builder();

        projectType.name( projectTypeDto.getName() );

        return projectType.build();
    }

    @Override
    public ProjectTypeResponse projectTypeToProjectTypeResponse(ProjectType projectType) {
        if ( projectType == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = projectType.getId();
        name = projectType.getName();

        ProjectTypeResponse projectTypeResponse = new ProjectTypeResponse( id, name );

        return projectTypeResponse;
    }

    @Override
    public void updateTypeWithTypeDto(ProjectTypeDto projectTypeDto, ProjectType projectType) {
        if ( projectTypeDto == null ) {
            return;
        }

        projectType.setName( projectTypeDto.getName() );
    }
}
