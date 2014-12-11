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

import com.slb.sprinteye.project.model.Project;
import com.slb.sprinteye.project.repository.ProjectRepository;
import com.slb.sprinteye.project.service.ProjectService;
import com.slb.sprinteye.project.view.ProjectDTO;
import com.slb.sprinteye.user.model.User;
import com.slb.sprinteye.user.repository.UserRepository;

@Controller
@Transactional(rollbackFor={Exception.class,RuntimeException.class})
@SessionAttributes({"user","project"})
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	 @RequestMapping(value={"project/"}, method=RequestMethod.GET)
	    public ModelAndView showBoats(WebRequest request,Model model) {
		     
		      
	        return new ModelAndView("project/project_list");
	    }
	 
	 @RequestMapping(value={"project/create"}, method=RequestMethod.GET)
	    public ModelAndView showCreateBoat(WebRequest request,Model model) {
		     
		      
	        return new ModelAndView("project/project_form");
	    }
	 
	 @RequestMapping(value={"project/show/{id}"}, method=RequestMethod.GET)
	  public ModelAndView showFormEdit(@PathVariable long id ,WebRequest request,Model model,RedirectAttributes redirectAttrs) {
		 
		   
		   Map<String, Object> map = new HashMap<String, Object>();  
		   map.put("id", id);
	      return new ModelAndView("project/project_form",map);
	  }
	 
	  @RequestMapping(value={"project/save"}, method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	   @ResponseBody
	    public ResponseEntity<ProjectDTO> save(@RequestBody @Valid ProjectDTO projectForm,@ModelAttribute("user") User user,
         WebRequest request,Model model)   {
		  Project project = new Project();
		  project.setId(projectForm.getId());
		  if(project.isNew() ){
			   project.setUser(userRepository.findByEmail( user.getEmail()));
			  
		  }else{
			   project=projectRepository.findOne(project.getId());
		  }
		  
		  BeanUtils.copyProperties(projectForm, project);
	
		   project= projectService.save(project);
		   
		   BeanUtils.copyProperties(project, projectForm);
		   model.addAttribute(project);
		  
		   return new ResponseEntity<ProjectDTO>(projectForm,HttpStatus.OK);
	    }
	 
}
