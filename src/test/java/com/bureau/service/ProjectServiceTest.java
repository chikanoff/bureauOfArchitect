package com.bureau.service;

import com.bureau.IntegrationTestBase;
import com.bureau.model.dto.request.entities.ProjectDto;
import com.bureau.model.entity.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProjectServiceTest extends IntegrationTestBase {
    @Autowired
    private ProjectService projectService;

    @Test
    public void createProjectTest() {
        ProjectDto dto = new ProjectDto();
        dto.setName("Test project");
        dto.setAddress("address");
        dto.setNotes("notes");
        dto.setProjectUrl("url");
        dto.setTypeId(createProjectType().getId());
        dto.setCityId(createTestCity().getId());
        dto.setClientId(createTestClient().getId());
        projectService.create(dto);
        assertThat(getProjectRepository().findAll().size()).isEqualTo(1);
    }
}
