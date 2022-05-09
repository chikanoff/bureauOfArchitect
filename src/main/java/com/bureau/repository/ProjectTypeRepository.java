package com.bureau.repository;

import com.bureau.model.dto.response.ProjectTypeResponse;
import com.bureau.model.entity.Project;
import com.bureau.model.entity.ProjectType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long> {
    Page<ProjectType> findAll(Pageable pageable);
}
