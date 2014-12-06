package com.slb.sprinteye.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.slb.core.model.BaseEntity;

@Entity
public class Role extends BaseEntity<Long> {

	   /**
	 * 
	 */
	private static final long serialVersionUID = 1499253127161524349L;


	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	   
	   
	    @Column(length = 100,nullable = false,name="role_name")
	    private String name;

	   public Role(){
		  
	   }
	   
	   public Role(String name){
		   setName(name);
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
	
	   
	   
	   
	   
}
