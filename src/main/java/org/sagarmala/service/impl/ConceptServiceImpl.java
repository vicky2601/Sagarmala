package org.sagarmala.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.sagarmala.bean.AddConceptBean;
import org.sagarmala.controller.HomeController;
import org.sagarmala.dao.ConceptDAO;
import org.sagarmala.dto.CustomReponseStatus;
import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.enumtype.constants.Status;
import org.sagarmala.model.AddConcept;
import org.sagarmala.model.RoleAuthorization;
import org.sagarmala.model.RoleMasterr;
import org.sagarmala.model.UserDetail;
import org.sagarmala.service.ConceptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("conceptService")
public class ConceptServiceImpl implements ConceptService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConceptServiceImpl.class);
	@Autowired
	ConceptDAO conceptDAO;

	@Override
	public Integer saveConcept(AddConceptBean addConceptBean) throws IllegalAccessException, InvocationTargetException  {
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		AddConcept addConcept = new AddConcept();
		BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
		BeanUtils.copyProperties(addConcept, addConceptBean);
		addConcept.setCreatedBy(addConceptBean.getCreatedBy());
		addConcept.setCreatedDate(addConceptBean.getCreatedDate());
		Integer id=	conceptDAO.saveConcept(addConcept);
		LOGGER.info("ID:- ",id);
		return id;

	}
	
	@Override
	public void updateConceptDetails(AddConceptBean addConceptBean)
	{
		
		conceptDAO.updateConceptDetails(addConceptBean);
	}

	@Override
	public ServiceResponse fetchConceptDetails(){
		ServiceResponse serviceResponse=new ServiceResponse();
		LinkedHashMap<Object, Object> response=new LinkedHashMap<>();
		CustomReponseStatus responseStatus=null;
		try {
			List<AddConcept> fetchConceptDetails = conceptDAO.fetchConceptDetails();
			LOGGER.info("Fetch Concept Details.",fetchConceptDetails.toString());
			List<AddConceptBean> listOfConceptBean=new ArrayList<>();
			for(AddConcept concept:fetchConceptDetails) {
				AddConceptBean conceptBean=new AddConceptBean();
				BeanUtils.copyProperties(conceptBean, concept);		
				listOfConceptBean.add(conceptBean);
			}
			LOGGER.info("ConceptBean :-> ",listOfConceptBean.toString());
			
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("totalSize", listOfConceptBean.size());
			response.put("conceptList", listOfConceptBean);
			
		} catch (Exception e) {
			e.printStackTrace();
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		LOGGER.info("Service :response Obj: ",serviceResponse);
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchConceptById(String conceptId) {
		ServiceResponse serviceResponse=new ServiceResponse();
		LinkedHashMap<Object,Object> response=new LinkedHashMap<>();
		CustomReponseStatus responseStatus=null;
		try {
			AddConcept concept=conceptDAO.fetchConceptById(conceptId);
			LOGGER.info("Fetch Concept Details by id.",concept.toString());
			AddConceptBean conceptBean=new AddConceptBean();
			BeanUtils.copyProperties(conceptBean,concept);
			LOGGER.info("ConceptBean :-> ",conceptBean.toString());
			responseStatus =new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("conceptBean", conceptBean);
		}catch (Exception e) {
			e.printStackTrace();
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		LOGGER.info("Service :response Obj: ",serviceResponse);
		return serviceResponse;
	}
	

	@Override
	public ServiceResponse fetchNodalByOrganisationId(Integer organisationId) {
		LOGGER.info("Executing fetchNodalByOrganisationId  method of ConceptService");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		List<UserDetail> userDetail ;

		try {
			userDetail = conceptDAO.fetchNodalByOrganisationId(organisationId);
			System.out.println(userDetail);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("userDetail", userDetail);
			response.put("totalSize", userDetail.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchNodalByOrganisationId  method of ConceptService", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public void updateStatusAndRemarkById(AddConceptBean addConceptBean) {
		conceptDAO.updateStatusAndRemarkByConceptId(addConceptBean);
		
	}
}
