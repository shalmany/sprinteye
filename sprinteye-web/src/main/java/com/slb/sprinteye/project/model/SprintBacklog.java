package com.slb.sprinteye.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import com.slb.core.model.BaseEntity;

@Entity
@Table(name = "sprint_backlog")
public class SprintBacklog extends  BaseEntity<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6618805671746495242L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@NotNull()
 	@ManyToOne(optional=false)
 	private ProductBacklog productBacklog;

	@NotEmpty
 	@Column( length = 100, nullable = false)
    private String name;
	
	
	@Column(name = "start_date", nullable = true)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime startDate;
	
	
	@Column(name = "end_date", nullable = true)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime endDate;
	
	private String description;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductBacklog getProductBacklog() {
		return productBacklog;
	}

	public void setProductBacklog(ProductBacklog productBacklog) {
		this.productBacklog = productBacklog;
	}
	
	

}
