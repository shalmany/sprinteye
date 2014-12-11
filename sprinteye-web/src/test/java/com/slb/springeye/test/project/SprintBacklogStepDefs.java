package com.slb.springeye.test.project;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.minidev.json.JSONArray;

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

import com.jayway.jsonpath.JsonPath;
import com.slb.core.jackson.CustomObjectMapper;
import com.slb.springeye.test.signup.SignupHelperSteps;
import com.slb.sprinteye.project.model.ProductBacklogItem;
import com.slb.sprinteye.project.view.ProductBacklogItemDTO;
import com.slb.sprinteye.project.view.SprintBacklogDTO;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
@ContextConfiguration("classpath:cucumber.xml")
public class SprintBacklogStepDefs {

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

	private SprintBacklogDTO sprintBacklogDTO;
	
	@Autowired
	private CustomObjectMapper customObjectMapper;
	
	private List<ProductBacklogItemDTO> selectedItems;
	

	@Before
	public void setUp() {
		// txStatus = txMgr.getTransaction(new DefaultTransactionDefinition());

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.dispatchOptions(true).build();
		signupHelperSteps.setMockMvc(mockMvc);
		projectHelperSteps.setMockMvc(mockMvc);
		sprintBacklogDTO = new SprintBacklogDTO();
	}
	
	@Given("that the user has filled the fields of sprint backlog form")
	public void given_that_the_user_has_filled_the_fields_of_sprint_backlog_form(List<SprintBacklogDTO> entities) {
		given_that_the_user_has_filled_the_fields_of_sprint_backlog_form(entities.get(0));
	}

	public void given_that_the_user_has_filled_the_fields_of_sprint_backlog_form(SprintBacklogDTO sprintBacklogForm) {
		this.sprintBacklogDTO =sprintBacklogForm;
	}
	
	@When("submit sprint backlog form")
	public void when_submit_sprint_backlog_form() throws Exception {

		resultActions = mockMvc
				.perform(
						MockMvcRequestBuilders
								.post("/project/sprint_backlog/save")
								.session(signupHelperSteps.getSession())
								.contentType(TestUtil.APPLICATION_JSON_UTF8)
								// .sessionAttr("userDetails", userDetails)
								.content(
										TestUtil.convertObjectToJsonBytes(sprintBacklogDTO))

				).andDo(MockMvcResultHandlers.print());

	}
	
	@Then("should register the  sprint backlog")
	public void then_should_register_the_sprint_backlog()
			throws Exception {

		resultActions.andExpect(status().isOk())
				.andExpect(
						MockMvcResultMatchers.jsonPath("$.id",
								Matchers.greaterThan(0)))
								.andExpect(
						MockMvcResultMatchers.jsonPath("$.name",
								Matchers.is(sprintBacklogDTO.getName())))
							.andExpect(
						MockMvcResultMatchers.jsonPath("$.description",
								Matchers.is(sprintBacklogDTO.getDescription())))
								;
		;
		
		String content =resultActions.andReturn().getResponse().getContentAsString();
		Integer id =JsonPath.read(content, "$.id");
		sprintBacklogDTO.setId(id.longValue());

	}
	
	@Then("should show validation Error for Name field of sprint backlog")
	public void then_should_show_validation_Error_for_name_field_of_sprint_backlog()
			throws Exception {

		resultActions
				.andExpect(status().isBadRequest())
				.andExpect(
						MockMvcResultMatchers
								.jsonPath("$.fieldErrors[?(@.field=='%s')]",
										"name")
								.exists());

	}
	
	@Given("that Sprint Backlog was created")
	public void given__that_Product_Backlog_was_created(List<SprintBacklogDTO> entities) throws Exception {
		
		for (SprintBacklogDTO sprintBacklogForm : entities) {
			this.given_that_the_user_has_filled_the_fields_of_sprint_backlog_form(sprintBacklogForm);
            this.when_submit_sprint_backlog_form();
			this.then_should_register_the_sprint_backlog();
		}
		

	}
	
	@Given("that selected some items from product backlog")
	public void given_that_selected_some_items_from_product_backlog() throws Exception {
		
		resultActions = mockMvc
				.perform(
						MockMvcRequestBuilders
								.get("/project/product_backlog/item/list")
								.param("page", "0")
								.param("size", "10")
								.session(signupHelperSteps.getSession())
								.contentType(TestUtil.APPLICATION_JSON_UTF8)
								

				).andDo(MockMvcResultHandlers.print());
		
		resultActions.andExpect(status().isOk())
		.andExpect(
				MockMvcResultMatchers.jsonPath("$.numberOfElements",
						Matchers.greaterThan(0)));
		
		String content =resultActions.andReturn().getResponse().getContentAsString();
		JSONArray items=JsonPath.read(content, "$.content[*].id");
		
	      selectedItems =new ArrayList<>();
	     for (int i = 0; i < items.size(); i++) {
	    	 ProductBacklogItemDTO productBacklogItemDTO = new ProductBacklogItemDTO();
	    	 productBacklogItemDTO.setId(((Integer)items.get(i)).longValue());
	    	 productBacklogItemDTO.setSprintBacklogDTO(sprintBacklogDTO);
	    	 selectedItems.add(productBacklogItemDTO);
		}
			
	}
	
	@When("add items to sprint backlog")
	public void when_add_items_to_sprint_backlog() throws Exception {
		
		resultActions = mockMvc
				.perform(
						MockMvcRequestBuilders
								.post("/project/sprint_backlog/add")
								.content(
										TestUtil.convertObjectToJsonBytes(selectedItems))
										.session(signupHelperSteps.getSession())
								.contentType(TestUtil.APPLICATION_JSON_UTF8)
								

				).andDo(MockMvcResultHandlers.print());
		
		
			
	}
	
	
	@Then("should register the items  in sprint backlog")
	public void then_should_register_the_items_in_sprint_backlog()
			throws Exception {

		resultActions
				.andExpect(status().isOk());

	}

}
