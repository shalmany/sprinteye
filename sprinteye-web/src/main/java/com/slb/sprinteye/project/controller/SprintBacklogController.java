package com.slb.sprinteye.project.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.slb.sprinteye.project.model.ProductBacklog;
import com.slb.sprinteye.project.model.ProductBacklogItem;
import com.slb.sprinteye.project.model.Project;
import com.slb.sprinteye.project.model.SprintBacklog;
import com.slb.sprinteye.project.repository.ProductBacklogItemRepository;
import com.slb.sprinteye.project.repository.ProductBacklogRepository;
import com.slb.sprinteye.project.repository.ProjectRepository;
import com.slb.sprinteye.project.repository.SprintBacklogRepository;
import com.slb.sprinteye.project.view.ProductBacklogItemDTO;
import com.slb.sprinteye.project.view.SprintBacklogDTO;
import com.slb.sprinteye.user.model.User;
import com.slb.sprinteye.user.repository.UserRepository;

@Controller
@Transactional(rollbackFor={Exception.class,RuntimeException.class})
@SessionAttributes({"user","project"})
public class SprintBacklogController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	ProductBacklogRepository productBacklogRepository;
	
	@Autowired
	ProductBacklogItemRepository productBacklogItemRepository;
	
	@Autowired
	SprintBacklogRepository sprintBacklogRepository;
	
	 @RequestMapping(value={"project/sprint_backlog/save"}, method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	   @ResponseBody
	    public ResponseEntity<SprintBacklogDTO> saveSprintBacklogItem(@RequestBody @Valid SprintBacklogDTO sprintBacklogForm,@ModelAttribute("user") User user,
	    		@ModelAttribute("project") Project project,
       WebRequest request,Model model)   {
		  SprintBacklog sprintBacklog=  new SprintBacklog();
		  sprintBacklog.setId(sprintBacklogForm.getId());
		  if(sprintBacklog.isNew() ){
			  
			  ProductBacklog productBacklog =productBacklogRepository.findByProject(project);
			
			  sprintBacklog.setProductBacklog(productBacklog);	 
		  }else{
			  sprintBacklog=sprintBacklogRepository.findOne(sprintBacklog.getId());
		  }
		  
		 BeanUtils.copyProperties(sprintBacklogForm, sprintBacklog);
		
		  sprintBacklog= sprintBacklogRepository.saveAndFlush(sprintBacklog);
		 
		  BeanUtils.copyProperties(sprintBacklog, sprintBacklogForm);
		  
		   return new ResponseEntity<SprintBacklogDTO>(sprintBacklogForm,HttpStatus.OK);
	    }

	 
	 @RequestMapping(value={"project/sprint_backlog/add"}, method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	   @ResponseBody
	    public ResponseEntity saveSprintBacklogItem(@RequestBody  List<ProductBacklogItemDTO> items,@ModelAttribute("user") User user,
	    		@ModelAttribute("project") Project project,
     WebRequest request,Model model)   {
		  
		 
		  for (ProductBacklogItemDTO productBacklogItemDTO : items) {
			  ProductBacklogItem productBacklogItem =productBacklogItemRepository.findOne(productBacklogItemDTO.getId());
			  productBacklogItem.setSprintBacklog(sprintBacklogRepository.findOne(productBacklogItemDTO.getSprintBacklogDTO().getId()));
			  productBacklogItemRepository.saveAndFlush(productBacklogItem);
		}
		   return new ResponseEntity(null,HttpStatus.OK);
	    }

}
