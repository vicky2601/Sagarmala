package org.sagarmala.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.sagarmala.dao.ProjectDao;
import org.sagarmala.dto.CustomReponseStatus;
import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.enumtype.constants.Status;
import org.sagarmala.model.Projects;
import org.sagarmala.model.RoleAuthorization;
import org.sagarmala.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Autowired
	private ProjectDao projectDao;
	/*
	 * @author UTL0857
	 * 
	 * If project Id is 0 then fetch all project otherwise fetch particular project only
	 * 		
	*/	
	@Override
	@Transactional
	public ServiceResponse fetchProject(int projectId) {
		LOGGER.info("Executing fetchProject  method of ProjectServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<Projects> projects = new ArrayList<>();
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			if (projectId == 0) {
				projects = projectDao.fetchProject();
				response.put("totalSize", projects.size());
				response.put("projectList", projects);
			} else {
				response.put("project", projectDao.fetchProject(projectId));
			}
			
		} catch (Exception e) {
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
			e.printStackTrace();
		}
		LOGGER.info("--Project Result -->" + response.toString());
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

}
