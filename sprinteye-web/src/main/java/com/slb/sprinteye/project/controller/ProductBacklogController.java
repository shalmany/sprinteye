package com.slb.sprinteye.project.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.slb.sprinteye.project.model.ProductBacklog;
import com.slb.sprinteye.project.model.ProductBacklogItem;
import com.slb.sprinteye.project.model.Project;
import com.slb.sprinteye.project.repository.ProductBacklogItemRepository;
import com.slb.sprinteye.project.repository.ProductBacklogRepository;
import com.slb.sprinteye.project.repository.ProjectRepository;
import com.slb.sprinteye.project.view.ProductBacklogItemForm;
import com.slb.sprinteye.user.model.User;
import com.slb.sprinteye.user.repository.UserRepository;

@Controller
@Transactional(rollbackFor={Exception.class,RuntimeException.class})
@SessionAttributes({"user","project"})
public class ProductBacklogController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProductBacklogRepository productBacklogRepository;
	
	@Autowired
	ProductBacklogItemRepository productBacklogItemRepository;
	
	 @RequestMapping(value={"project/product_backlog"}, method=RequestMethod.GET)
	    public ModelAndView showBoats(WebRequest request,Model model) {
		     
		      
	        return new ModelAndView("project/product_backlog/product_backlog_list");
	    }
	 
	 @RequestMapping(value={"project/product_backlog/create"}, method=RequestMethod.GET)
	    public ModelAndView showCreateBoat(WebRequest request,Model model) {
		     
		      
	        return new ModelAndView("project/product_backlog/product_backlog_form");
	    }
	 
	 @RequestMapping(value={"project/product_backlog/show/{id}"}, method=RequestMethod.GET)
	  public ModelAndView showFormEdit(@PathVariable long id ,WebRequest request,Model model,RedirectAttributes redirectAttrs) {
		 
		   
		   Map<String, Object> map = new HashMap<String, Object>();  
		   map.put("id", id);
	      return new ModelAndView("project/product_backlog/product_backlog_form",map);
	  }
	 
	  @RequestMapping(value={"project/product_backlog/add"}, method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	   @ResponseBody
	    public ResponseEntity<ProductBacklogItemForm> addProductBacklogItem(@RequestBody @Valid ProductBacklogItemForm productBacklogItemForm,@ModelAttribute("user") User user,
	    		@ModelAttribute("project") Project project,
         WebRequest request,Model model)   {
		  ProductBacklogItem productBacklogItem=  new ProductBacklogItem();
		  productBacklogItem.setId(productBacklogItemForm.getId());
		  if(productBacklogItem.isNew() ){
			  
			  ProductBacklog productBacklog =productBacklogRepository.findByProject(project);
			  if(productBacklog==null){
				  productBacklog = new ProductBacklog();
				  productBacklog.setProject(project);
				  productBacklogRepository.save(productBacklog);
				  
			  }
			
			  productBacklogItem.setProductBacklog(productBacklogRepository.findByProject(project));	 
		  }else{
			  productBacklogItem=productBacklogItemRepository.findOne(productBacklogItem.getId());
		  }
		  
		 BeanUtils.copyProperties(productBacklogItemForm, productBacklogItem);
		
		  productBacklogItem= productBacklogItemRepository.save(productBacklogItem);
		 
		  BeanUtils.copyProperties(productBacklogItem, productBacklogItemForm);
		  
		   return new ResponseEntity<ProductBacklogItemForm>(productBacklogItemForm,HttpStatus.OK);
	    }
}
