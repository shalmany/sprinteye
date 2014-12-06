package com.slb.sprinteye.user.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.slb.core.model.BaseEntity;


@Entity
@Table(name = "users")
public class User extends BaseEntity<Long> {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5355032699008356815L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    @NotEmpty
    @Size(max = 100)
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "first_name", length = 100,nullable = false)
    private String firstName;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    
    @Column(name = "password", length = 255)
    private String password;
    
   
    @Transient
    private String passwordVerification;

    @JsonIgnore
    @Column( nullable = true)
    @OneToMany(cascade=CascadeType.ALL)
    protected List<Role> roles = new ArrayList<>();

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "sign_in_provider", length = 20)
    private SocialMediaEnum signInProvider;
    
    @Column(unique=true)
    @Size(min=10,max=10)
	private String phone;

    public User() {

    }

    public static Builder getBuilder() {
        return new Builder();
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

   

    public SocialMediaEnum getSignInProvider() {
        return signInProvider;
    }

    
    
    
    
    public void setId(Long id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	

	public void setSignInProvider(SocialMediaEnum signInProvider) {
		this.signInProvider = signInProvider;
	}

	



	public List<Role> getRoles() {
		return roles;
	}

	
	@JsonIgnore
	public String getPasswordVerification() {
		return passwordVerification;
	}

	@JsonProperty
	public void setPasswordVerification(String passwordVerification) {
		this.passwordVerification = passwordVerification;
	}

	
    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public static class Builder {

        private User user;

        public Builder() {
            user = new User();
            
        }

        public Builder email(String email) {
            user.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            user.password = password;
            return this;
        }

        public Builder signInProvider(SocialMediaEnum signInProvider) {
            user.signInProvider = signInProvider;
            return this;
        }

        public User build() {
            return user;
        }

	
    }

    @JsonIgnore
	 public boolean isNormalRegistration() {
        return signInProvider == null;
    }
	 
	 @JsonIgnore
	 public boolean isSocialSignIn() {
	        return signInProvider != null;
	    }
}
