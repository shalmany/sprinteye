package com.slb.sprinteye.project.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sprint_task")
public class SprintTask extends  BaseTask{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7297112434522751152L;

	
 	
 	
 	
	@NotNull()
 	@ManyToOne(optional=false)
 	private ProductBacklogItem productBacklogItem;





	public ProductBacklogItem getProductBacklogItem() {
		return productBacklogItem;
	}





	public void setProductBacklogItem(ProductBacklogItem productBacklogItem) {
		this.productBacklogItem = productBacklogItem;
	}
	


}
