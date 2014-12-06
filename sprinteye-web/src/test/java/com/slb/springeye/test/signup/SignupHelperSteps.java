package com.slb.springeye.test.signup;

import static org.junit.Assert.assertNotNull;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.teste.slb.TestUtil;

import com.slb.sprinteye.user.view.SignupForm;

@Component
public class SignupHelperSteps {

	private MockMvc mockMvc;
	private ResultActions resultActions;

	private SignupForm signupForm;

	
	

	private MockHttpSession session;

	public void given_signup_form_without_password() throws Exception {
		signupForm = new SignupForm();
		signupForm.setEmail("unitTest@test.com");
		signupForm.setFirstName("unitTestFirstName");
		signupForm.setLastName("unitTestLastName");
	}
	
	public void given_signupForm_WithAllFields() {
		signupForm = new SignupForm();
		signupForm.setEmail("unitTest@test.com");
		signupForm.setFirstName("unitTestFirstName");
		signupForm.setLastName("unitTestLastName");
		signupForm.setPassword("1234fgtrsrrr");
		signupForm.setPasswordVerification("1234fgtrsrrr");

	}

	public void when_click_button_register_of_sign_up_form() throws Exception {

		resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post("/todo/signup/save")
						.contentType(TestUtil.APPLICATION_JSON_UTF8)
						.content(TestUtil.convertObjectToJsonBytes(signupForm))

		).andDo(MockMvcResultHandlers.print());

	}

	public void then_should_register_the_new_user() throws Exception {

		MvcResult mvcResult = resultActions.andReturn();
		session = (MockHttpSession) mvcResult.getRequest().getSession();
		System.out.println("(----session.getAttribute(user)="
				+ session.getAttribute("user"));
		assertNotNull(session.getAttribute("user"));
		// .andExpect(MockMvcResultMatchers.model().attributeExists("user"));

	}

	

	public SignupForm getSignupForm() {
		return signupForm;
	}

	public void setSignupForm(SignupForm signupForm) {
		this.signupForm = signupForm;
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

	

	public MockHttpSession getSession() {
		return session;
	}

	
	
}
