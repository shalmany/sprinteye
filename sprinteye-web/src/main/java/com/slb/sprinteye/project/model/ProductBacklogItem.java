package com.slb.sprinteye.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slb.core.model.BaseEntity;
import com.slb.sprinteye.user.model.SocialMediaEnum;

@Entity
@Table(name = "product_backlog_item")
public class ProductBacklogItem extends  BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6618805671746495242L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull()
 	@ManyToOne(optional=false)
 	private ProductBacklog ProductBacklog;
	
	@NotEmpty
 	@Column( length = 100, nullable = false)
    private String name;
	
	@NotEmpty
 	@Column( length = 500)
    private String description;
	

    private Integer businessValue;
	

    private Integer estimate;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable=false)
    private ItemTypeEnum itemTypeEnum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductBacklog getProductBacklog() {
		return ProductBacklog;
	}

	public void setProductBacklog(ProductBacklog productBacklog) {
		ProductBacklog = productBacklog;
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

	public Integer getBusinessValue() {
		return businessValue;
	}

	public void setBusinessValue(Integer businessValue) {
		this.businessValue = businessValue;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}
	
	
	
}
