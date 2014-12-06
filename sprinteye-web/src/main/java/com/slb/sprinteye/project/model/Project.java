package com.slb.sprinteye.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.slb.core.model.BaseEntity;
import com.slb.sprinteye.user.model.User;

@Entity
@Table(name = "projects")
public class Project extends  BaseEntity<Long> {

		/**
	 * 
	 */
	private static final long serialVersionUID = 2817055274217225521L;

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
		
		@NotNull()
	 	@ManyToOne(optional=false)
	 	private User user;
		
		@NotEmpty
	 	@Column( length = 100, nullable = false)
	    private String name;
		
		@Column( length = 500)
	 	private String description;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
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
