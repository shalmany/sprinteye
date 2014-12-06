package com.slb.springeye.test.signup;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.teste.slb.TestUtil;

import com.slb.sprinteye.user.view.SignupForm;

@Component

public class SignupSteps {

	private MockMvc mockMvc;

	public SignupForm given_signupForm_WithAllFields() {
		SignupForm registrationForm = new SignupForm();
		registrationForm.setEmail("unitTest@test.com");
		registrationForm.setFirstName("unitTestFirstName");
		registrationForm.setLastName("unitTestLastName");
		registrationForm.setPassword("1234fgtrsrrr");
		registrationForm.setPasswordVerification("1234fgtrsrrr");
		return registrationForm;
	}

	public ResultActions when_submitForm(SignupForm registrationForm)
			throws IOException, Exception {
		ResultActions resultActions = mockMvc
				.perform(
						MockMvcRequestBuilders
								.post("/todo/signup/save")
								.contentType(TestUtil.APPLICATION_JSON_UTF8)
								.content(
										TestUtil.convertObjectToJsonBytes(registrationForm))

				).andDo(MockMvcResultHandlers.print());
		return resultActions;
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

	public void setMockMvc(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

}
