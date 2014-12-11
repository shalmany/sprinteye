package com.slb.sprinteye.project.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.slb.core.model.BaseEntity;

@MappedSuperclass
public abstract class BaseTask extends BaseEntity<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3685543485649997664L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotEmpty
 	@Column( length = 100, nullable = false)
    private String name;

 	@Column( length = 500)
    private String description;
 	
 	private Integer estimate;
 	
 	@NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable=false)
    private ItemTypeEnum itemTypeEnum;
 	
 	
 	@ManyToOne(optional=true)
 	private SprintBacklog sprintBacklog;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}

	public ItemTypeEnum getItemTypeEnum() {
		return itemTypeEnum;
	}

	public void setItemTypeEnum(ItemTypeEnum itemTypeEnum) {
		this.itemTypeEnum = itemTypeEnum;
	}

	public SprintBacklog getSprintBacklog() {
		return sprintBacklog;
	}

	public void setSprintBacklog(SprintBacklog sprintBacklog) {
		this.sprintBacklog = sprintBacklog;
	}

 	
 	
}
