package org.sagarmala.restful.webservice;

import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/project")
public class ProjectControllerService {
	
	@Autowired
	ProjectService projectService;
	
	@ApiOperation(value = "View a list of available projects",response = ServiceResponse.class)    
	@RequestMapping(value = "/projectlist/{projectId}", method = RequestMethod.GET)
	public ServiceResponse fetchProject(@PathVariable int projectId) {
		return projectService.fetchProject(projectId);
	}
}
