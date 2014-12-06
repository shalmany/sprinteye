package com.slb.sprinteye.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slb.sprinteye.project.model.Project;

public interface ProjectRepository   extends JpaRepository<Project, Long> {

}
