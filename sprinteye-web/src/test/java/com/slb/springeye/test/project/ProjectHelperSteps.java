package com.slb.springeye.test.project;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.teste.slb.TestUtil;

import com.slb.springeye.test.signup.SignupHelperSteps;
import com.slb.sprinteye.project.view.ProjectForm;
import com.slb.sprinteye.user.view.SignupForm;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


@Component
public class ProjectHelperSteps {

	private MockMvc mockMvc;
	private ResultActions resultActions;

	private ProjectForm projectForm;

	@Autowired
	SignupHelperSteps signupHelperSteps;

	private MockHttpSession session;

	public void given_the_User_complete_all_fields_of_project_form() {
		projectForm = new ProjectForm();
		projectForm.setName("New Project");
		projectForm.setDescription("desciption project");

	}

	
	public void when_click_button_register_of_project_form() throws Exception {

		resultActions = mockMvc
				.perform(
						MockMvcRequestBuilders
								.post("/project/save")
								.session(signupHelperSteps.getSession())
								.contentType(TestUtil.APPLICATION_JSON_UTF8)
								//.sessionAttr("userDetails", userDetails)
								.content(
										TestUtil.convertObjectToJsonBytes(projectForm))

				).andDo(MockMvcResultHandlers.print());

	}

	
	public void then_should_register_the_new_project() throws Exception {

		resultActions.andExpect(status().isOk())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.id",
								Matchers.greaterThan(0)));
		;

	}


	public MockMvc getMockMvc() {
		return mockMvc;
	}


	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
	
	
	
}
