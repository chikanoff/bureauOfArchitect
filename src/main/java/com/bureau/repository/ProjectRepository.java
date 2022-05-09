package com.bureau.repository;

import com.bureau.model.entity.Project;
import com.bureau.util.SqlQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findAll(Pageable pageable);

    @Query(value = SqlQuery.SELECT_USER_PROJECTS, nativeQuery = true)
    List<Project> findUserProjects(Long userId);

    @Query(value = SqlQuery.SELECT_PROJECTS_BETWEEN, nativeQuery = true)
    List<Project> findProjectsBetweenTwoDates(Date startDate, Date endDate);
}
