package com.slb.sprinteye.user.security.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

import com.slb.sprinteye.user.model.EnumRole;
import com.slb.sprinteye.user.model.Role;
import com.slb.sprinteye.user.model.SocialMediaEnum;


public class UserDetails extends SocialUser {

    /**
	 * 
	 */
	private static final long serialVersionUID = 187379237968629830L;

	private Long id;

    private String firstName;

    private String lastName;
    
	private SocialMediaEnum socialSignInProvider;

    public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

	public SocialMediaEnum getSocialSignInProvider() {
        return socialSignInProvider;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", getUsername())
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("socialSignInProvider", socialSignInProvider)
                .toString();
    }

    public static class Builder {

        private Long id;

        private String username;

        private String firstName;

        private String lastName;

        private String password;

        private EnumRole role;

        private SocialMediaEnum socialSignInProvider;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }

            this.password = password;
            return this;
        }

        public Builder roles(Collection<Role> roles) {
            
        	for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
        		Role role = (Role) iterator.next();
				 SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
		         this.authorities.add(authority);	
			}

            return this;
        }

        public Builder socialSignInProvider(SocialMediaEnum socialSignInProvider) {
            this.socialSignInProvider = socialSignInProvider;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public UserDetails build() {
            UserDetails user = new UserDetails(username, password, authorities);

            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            user.socialSignInProvider = socialSignInProvider;

            return user;
        }
    }
}
