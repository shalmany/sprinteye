package com.slb.sprinteye.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slb.sprinteye.project.model.ProductBacklog;
import com.slb.sprinteye.project.model.Project;

public interface ProductBacklogRepository extends JpaRepository<ProductBacklog, Long> {
 
	ProductBacklog findByProject(Project project);
}
