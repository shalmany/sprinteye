package com.slb.sprinteye.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.slb.sprinteye.project.model.Project;
import com.slb.sprinteye.project.repository.ProjectRepository;

@Service
@Repository
@Transactional(propagation=Propagation.REQUIRED,rollbackFor={Exception.class})
public class ProjectService {

	@Autowired
	 private ProjectRepository projectRepository;
	
	public Project save(Project project){
		
		project =projectRepository.saveAndFlush(project);
		return project;
	}
	
	
	
}
