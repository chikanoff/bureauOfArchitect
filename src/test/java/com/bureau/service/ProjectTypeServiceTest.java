package com.bureau.service;

import com.bureau.IntegrationTestBase;
import com.bureau.model.dto.request.entities.ProjectTypeDto;
import com.bureau.model.entity.ProjectType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProjectTypeServiceTest extends IntegrationTestBase {
    @Autowired
    private ProjectTypeService projectTypeService;

    @Test
    public void findByIdTest() {
        ProjectType projectType = createProjectType();
        assertThat(projectTypeService.findById(projectType.getId()).getName()).isEqualTo(projectType.getName());
    }

    @Test
    public void createProjectTypeTest() {
        ProjectTypeDto dto = new ProjectTypeDto();
        dto.setName("Test");
        projectTypeService.create(dto);
        assertThat(getProjectTypeRepository().findAll().size()).isEqualTo(1);
    }

    @Test
    public void deleteProjectTypeTest() {
        ProjectType projectType = createProjectType();
        projectTypeService.delete(projectType.getId());
        assertThat(getProjectTypeRepository().findAll().size()).isEqualTo(0);
    }

    @Test
    public void updateProjectTypeTest() {
        ProjectType projectType = createProjectType();
        ProjectTypeDto dto = new ProjectTypeDto();
        dto.setName("new name");
        projectTypeService.update(projectType.getId(), dto);
        assertThat(getProjectTypeRepository().findById(projectType.getId()).get().getName()).isEqualTo(dto.getName());
    }
}
