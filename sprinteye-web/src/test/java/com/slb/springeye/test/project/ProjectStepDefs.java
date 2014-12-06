package com.slb.springeye.test.project;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.slb.springeye.test.signup.SignupHelperSteps;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")

public class ProjectStepDefs {

	private MockMvc mockMvc;
	
	@Resource
	private WebApplicationContext webApplicationContext;


	
	
	@Autowired
	SignupHelperSteps signupHelperSteps; 
	
	@Autowired
	ProjectHelperSteps projectHelperSteps; 

	@Resource
	private ApplicationContext ctx;
	

	
	

	@Before
	public void setUp() {
		// txStatus = txMgr.getTransaction(new DefaultTransactionDefinition());

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.dispatchOptions(true).build();
		signupHelperSteps.setMockMvc(mockMvc);
		projectHelperSteps.setMockMvc(mockMvc);
		

	}



	@Given("the User complete all fields of project form")
	public void given_the_User_complete_all_fields_of_project_form() {
		projectHelperSteps.given_the_User_complete_all_fields_of_project_form();

	}

	@When("click button register of project form")
	public void click_button_register_of_project_form() throws Exception {

		projectHelperSteps.when_click_button_register_of_project_form();

	}

	@Then("should register the new project")
	public void then_should_register_the_new_project() throws Exception {

		projectHelperSteps.then_should_register_the_new_project();

	}
	
	@Given("that Project was created")
	public void given_that_project_was_created() throws Exception {
		projectHelperSteps.given_the_User_complete_all_fields_of_project_form();

		projectHelperSteps.when_click_button_register_of_project_form();

		projectHelperSteps.then_should_register_the_new_project();

	}

}
