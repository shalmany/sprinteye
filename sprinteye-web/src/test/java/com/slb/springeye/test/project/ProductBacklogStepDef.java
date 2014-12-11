package com.slb.springeye.test.project;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import com.slb.sprinteye.project.model.ItemTypeEnum;
import com.slb.sprinteye.project.view.ProductBacklogItemDTO;

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

	private ProductBacklogItemDTO productBacklogItemForm;

	@Before
	public void setUp() {
		// txStatus = txMgr.getTransaction(new DefaultTransactionDefinition());

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.dispatchOptions(true).build();
		signupHelperSteps.setMockMvc(mockMvc);
		projectHelperSteps.setMockMvc(mockMvc);
		productBacklogItemForm = new ProductBacklogItemDTO();
	}

	@Given("that fill (.*) in name field  of product backlog item form")
	public void given_the_User_complete_name_field_of_product_backlog_item_form(String name) {
		
		productBacklogItemForm.setName(name);
	
	}
	
	@Given("that fill (.*) in type field  of product backlog item form")
	public void given_the_User_complete_type_field_of_product_backlog_item_form(String type) {
		
		productBacklogItemForm.setItemTypeEnum(ItemTypeEnum.valueOf(type));
	
	}
	
	@Given("that fill (.*) in description field  of product backlog item form")
	public void given_the_User_complete_description_fields_of_product_backlog_item_form(String description) {
		
		productBacklogItemForm.setDescription(description);

	}
	
	@Given("that fill (\\d+) in business value field  of product backlog item form")
	public void given_the_User_complete_business_value_field_of_product_backlog_item_form(Integer businessValue) {
		
		productBacklogItemForm.setBusinessValue(businessValue);

	}
	
	@Given("that fill (\\d+) in estimate field  of product backlog item form")
	public void given_the_User_complete_estimate_field_of_product_backlog_item_form(Integer estimate) {
		
		productBacklogItemForm.setEstimate(estimate);

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
								Matchers.greaterThan(0)))
								.andExpect(
						MockMvcResultMatchers.jsonPath("$.name",
								Matchers.is(productBacklogItemForm.getName())))
							.andExpect(
						MockMvcResultMatchers.jsonPath("$.itemTypeEnum",
								Matchers.is(productBacklogItemForm.getItemTypeEnum().toString())))
							.andExpect(
						MockMvcResultMatchers.jsonPath("$.description",
								Matchers.is(productBacklogItemForm.getDescription())))
								.andExpect(
						MockMvcResultMatchers.jsonPath("$.businessValue",
								Matchers.is(productBacklogItemForm.getBusinessValue())))
								.andExpect(
						MockMvcResultMatchers.jsonPath("$.estimate",
								Matchers.is(productBacklogItemForm.getEstimate())))
							;
		;

	}
	
	@Then("should show validation Error for Name field of product backlog item")
	public void then_should_show_validation_Error_for_name_field_of_product_backlog_item()
			throws Exception {

		resultActions
				.andExpect(status().isBadRequest())
				.andExpect(
						MockMvcResultMatchers
								.jsonPath("$.fieldErrors[?(@.field=='%s')]",
										"name")
								.exists());

	}
	
	@Then("should show validation Error for type field of product backlog item")
	public void then_should_show_validation_Error_for_type_field_of_product_backlog_item()
			throws Exception {

		resultActions
				.andExpect(status().isBadRequest())
				.andExpect(
						MockMvcResultMatchers
								.jsonPath("$.fieldErrors[?(@.field=='%s')]",
										"itemTypeEnum")
								.exists());

	}
	
	@Given("that Product Backlog was created")
	public void given__that_Product_Backlog_was_created(List<ProductBacklogItemDTO> entities) throws Exception {
		
		for (ProductBacklogItemDTO productBacklogItemForm : entities) {
			this.given_that_the_user_has_filled_the_fields_of_product_backlog_item_form_with_data_below(productBacklogItemForm);
            this.when_add_item_to_product_backlog();
			this.then_should_register_the_new_item_to_product_backlog();
		}
		

	}

	@Given("that the user has filled the fields  of product backlog item form with data below")
	public void given_that_the_user_has_filled_the_fields_of_product_backlog_item_form_with_data_below(List<ProductBacklogItemDTO> entities) {
		given_that_the_user_has_filled_the_fields_of_product_backlog_item_form_with_data_below(entities.get(0));
	}

	public void given_that_the_user_has_filled_the_fields_of_product_backlog_item_form_with_data_below(ProductBacklogItemDTO productBacklogItemForm) {
		this.productBacklogItemForm =productBacklogItemForm;
	}
}
