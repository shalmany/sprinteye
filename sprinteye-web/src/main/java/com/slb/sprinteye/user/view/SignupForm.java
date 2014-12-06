package com.slb.sprinteye.user.view;

import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slb.sprinteye.user.model.SocialMediaEnum;


/*@PasswordsNotEmpty(
        triggerFieldName = "signInProvider",
        passwordFieldName = "password",
        passwordVerificationFieldName = "passwordVerification"
)
@PasswordsNotEqual(
        passwordFieldName = "password",
        passwordVerificationFieldName = "passwordVerification"
)*/
public class SignupForm {

    public static final String FIELD_NAME_EMAIL = "email";

    @Email
    @NotEmpty
    @Size(max = 100)
    private String email;

    @NotEmpty
    @Size(max = 100)
    private String firstName;

    @NotEmpty
    @Size(max = 100)
    private String lastName;

    private String password;

    private String passwordVerification;

    private SocialMediaEnum signInProvider;

    public SignupForm() {

    }

    @JsonIgnore
    public boolean isNormalRegistration() {
        return signInProvider == null;
    }

    @JsonIgnore
    public boolean isSocialSignIn() {
        return signInProvider != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerification() {
        return passwordVerification;
    }

    public void setPasswordVerification(String passwordVerification) {
        this.passwordVerification = passwordVerification;
    }

    public SocialMediaEnum getSignInProvider() {
        return signInProvider;
    }

    public void setSignInProvider(SocialMediaEnum signInProvider) {
        this.signInProvider = signInProvider;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("signInProvider", signInProvider)
                .toString();
    }
}
