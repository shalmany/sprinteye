package com.slb.springeye.test.project;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.teste.slb.TestUtil;

import com.slb.springeye.test.signup.SignupHelperSteps;
import com.slb.sprinteye.project.view.ProductBacklogItemForm;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class ProductBacklogStepDef {

	private MockMvc mockMvc;
	private ResultActions resultActions;

	@Resource
	private WebApplicationContext webApplicationContext;

	@Autowired
	SignupHelperSteps signupHelperSteps;

	@Autowired
	ProjectHelperSteps projectHelperSteps;

	@Resource
	private ApplicationContext ctx;

	private ProductBacklogItemForm productBacklogItemForm;

	@Before
	public void setUp() {
		// txStatus = txMgr.getTransaction(new DefaultTransactionDefinition());

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.dispatchOptions(true).build();
		signupHelperSteps.setMockMvc(mockMvc);
		projectHelperSteps.setMockMvc(mockMvc);

	}

	@Given("the User complete all fields of product backlog item form")
	public void given_the_User_complete_all_fields_of_product_backlog_item_form() {
		productBacklogItemForm = new ProductBacklogItemForm();
		productBacklogItemForm.setName("teste item");
		productBacklogItemForm.setDescription("description");

	}

	@When("add item to product backlog")
	public void when_add_item_to_product_backlog() throws Exception {

		resultActions = mockMvc
				.perform(
						MockMvcRequestBuilders
								.post("/project/product_backlog/add")
								.session(signupHelperSteps.getSession())
								.contentType(TestUtil.APPLICATION_JSON_UTF8)
								// .sessionAttr("userDetails", userDetails)
								.content(
										TestUtil.convertObjectToJsonBytes(productBacklogItemForm))

				).andDo(MockMvcResultHandlers.print());

	}

	@Then("should register the new  item to product backlog")
	public void then_should_register_the_new_item_to_product_backlog()
			throws Exception {

		resultActions.andExpect(status().isOk())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.id",
								Matchers.greaterThan(0)));
		;

	}

}
