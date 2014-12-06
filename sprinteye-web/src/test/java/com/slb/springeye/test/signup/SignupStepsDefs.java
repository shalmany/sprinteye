package com.slb.springeye.test.signup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")

public class SignupStepsDefs {

	private MockMvc mockMvc;

	private ResultActions resultActions;
	@Resource
	private WebApplicationContext webApplicationContext;

	
	@Autowired
	private SignupHelperSteps signupHelperSteps;
	


	
 

  
    
	@Before
	public void setUp() {
		//txStatus = txMgr.getTransaction(new DefaultTransactionDefinition());
		 
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.dispatchOptions(true).build();
		
		signupHelperSteps.setMockMvc(mockMvc);
		
		
	}
	
	

	@Given("^the User has posted the message \"(.*?)\"$")
	public void the_User_has_posted_the_message(String content) {
		System.out.println("teste");
	}

	@Given("^the User has requested a signup page \"(.*?)\"$")
	public void signup_ShouldShowSignupPage(String url) throws Exception {

		resultActions = mockMvc.perform(get(url));

		
	}

	@Then("should show signup page")
	public void should_show_signup_page() throws Exception {

		resultActions.andExpect(status().isOk());

		// assertTrue(responseEntity.getStatusCode()==HttpStatus.OK);
	}

	@Given("^a User filled form without password$")
	public void signup_form_without_password() throws Exception {
		signupHelperSteps.given_signup_form_without_password();
	
	}

	@When("click button register of sign up form")
	public void click_button_register_of_sign_up_form() throws Exception {
		signupHelperSteps.when_click_button_register_of_sign_up_form();
		resultActions = signupHelperSteps.getResultActions();

	}

	@Then("should show validation Error for Password field")
	public void should_show_validation_Error_for_Password_field()
			throws Exception {

		resultActions
				.andExpect(status().isInternalServerError())
				.andExpect(
						MockMvcResultMatchers
								.jsonPath("$.globalErrors[?(@.field=='%s')]",
										"com.slb.sprinteye.user.service.PasswordIsEmptyException")
								.exists());

	}
	
	@Given("the User complete all fields of sign up form")
	public void given_signupForm_WithAllFields() {
		
		signupHelperSteps.given_signupForm_WithAllFields();
		
	}
	
	@Then("should register the new user")
	public void should_register_the_new_user()
			throws Exception {

		signupHelperSteps.then_should_register_the_new_user();
	
	}
	
	@Given("that User is registered")
	public void given_that_user_is_registered() throws Exception {
		signupHelperSteps.given_signupForm_WithAllFields();

		signupHelperSteps.when_click_button_register_of_sign_up_form();

		signupHelperSteps.then_should_register_the_new_user();

	}

}
