package com.slb.sprinteye.project.view;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.slb.sprinteye.project.model.ItemTypeEnum;
import com.slb.sprinteye.project.model.SprintBacklog;

public class ProductBacklogItemDTO {

	 private Long id;
		
		
		
		@NotEmpty
	    private String name;
		@NotNull
		private ItemTypeEnum itemTypeEnum;
		
	 	private String description;
	 	
	 	 private Integer businessValue;
	 	

	     private Integer estimate;
	     
	     
	  	private SprintBacklogDTO sprintBacklogDTO;


		public SprintBacklogDTO getSprintBacklogDTO() {
			return sprintBacklogDTO;
		}


		public void setSprintBacklogDTO(SprintBacklogDTO sprintBacklogDTO) {
			this.sprintBacklogDTO = sprintBacklogDTO;
		}


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


		public ItemTypeEnum getItemTypeEnum() {
			return itemTypeEnum;
		}


		public void setItemTypeEnum(ItemTypeEnum itemTypeEnum) {
			this.itemTypeEnum = itemTypeEnum;
		}
	 	
	 	

}
