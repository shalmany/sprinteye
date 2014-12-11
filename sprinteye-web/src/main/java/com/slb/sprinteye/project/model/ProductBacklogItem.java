package com.slb.sprinteye.project.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_backlog_item")
public class ProductBacklogItem extends  BaseTask {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8003300782945167829L;


	@NotNull()
 	@ManyToOne(optional=false)
 	private ProductBacklog productBacklog;
	
	
	private Integer businessValue;


	public ProductBacklog getProductBacklog() {
		return productBacklog;
	}


	public void setProductBacklog(ProductBacklog productBacklog) {
		this.productBacklog = productBacklog;
	}


	public Integer getBusinessValue() {
		return businessValue;
	}


	public void setBusinessValue(Integer businessValue) {
		this.businessValue = businessValue;
	}
	


	
}
