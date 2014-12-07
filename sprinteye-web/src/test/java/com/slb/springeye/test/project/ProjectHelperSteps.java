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

import cucumber.api.java.en.Given;


@Component
public class ProjectHelperSteps {

	private MockMvc mockMvc;
	private ResultActions resultActions;

	private ProjectForm projectForm;

	@Autowired
	SignupHelperSteps signupHelperSteps;

	private MockHttpSession session;
	
	public void setup(){
		projectForm = new ProjectForm();
	}

	public void given_the_User_complete_all_fields_of_project_form() {
		
		projectForm.setName("New Project");
		projectForm.setDescription("desciption project");

	}

	
	public void given_the_User_filled_the_name_of_project_with(String name) {
		projectForm.setName(name);
	}
	
	
	public void given_the_User_filled_the_description_of_project_with(String description) {
		projectForm.setDescription(description);
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


	public ResultActions getResultActions() {
		return resultActions;
	}


	public void setResultActions(ResultActions resultActions) {
		this.resultActions = resultActions;
	}
	
	
	
}
