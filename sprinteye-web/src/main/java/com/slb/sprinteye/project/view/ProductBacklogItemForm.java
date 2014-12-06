package com.slb.sprinteye.project.view;

import org.hibernate.validator.constraints.NotEmpty;

public class ProductBacklogItemForm {

	 private Long id;
		
		
		
		@NotEmpty
	    private String name;
		
		
	 	private String description;


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
	 	
	 	

}
