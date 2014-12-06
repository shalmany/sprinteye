package com.slb.core.model;

import java.io.Serializable;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@MappedSuperclass
public abstract class BaseEntity<ID> implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "creation_time", nullable = false,updatable=false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationTime;

    @Column(name = "modification_time", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationTime;

    @Version
    private long version;

    public abstract ID getId();

  

    public long getVersion() {
        return version;
    }
    
 

    public DateTime getCreationTime() {
        return creationTime;
    }

    public DateTime getModificationTime() {
        return modificationTime;
    }



    @PrePersist
    public void prePersist() {
        DateTime now = DateTime.now();
        this.creationTime = now;
        this.modificationTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = DateTime.now();
    }

    
    @JsonIgnore
    public boolean  isNew(){
    	return getId()==null;
    }
 
}


