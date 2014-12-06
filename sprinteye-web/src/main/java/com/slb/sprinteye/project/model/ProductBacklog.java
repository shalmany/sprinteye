package com.slb.sprinteye.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.slb.core.model.BaseEntity;

@Entity
@Table(name = "product_backlog")
public class ProductBacklog extends  BaseEntity<Long> {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6618805671746495242L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull()
 	@OneToOne(optional=false)
 	private Project project;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
}
