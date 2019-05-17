package org.sagarmala.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.sagarmala.bean.SearchRoleBean;
import org.sagarmala.dao.UserDao;
import org.sagarmala.dto.CustomReponseStatus;
import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.enumtype.constants.Status;
import org.sagarmala.model.OrgRoleMapping;
import org.sagarmala.model.RoleMasterr;
import org.sagarmala.service.IOrgRoleMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("organisationRoleMapping")
public class IOrgRoleMappingServiceImpl implements IOrgRoleMappingService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IOrgRoleMappingServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public ServiceResponse findRoleByOrgRoleId(Integer id) {
		ServiceResponse serviceResponse=new ServiceResponse();
		LinkedHashMap<Object, Object> response=new LinkedHashMap<>();
		CustomReponseStatus customReponseStatus=null;
		RoleMasterr role=null;
		try{
			
			
			SearchRoleBean searchRoleBean=new SearchRoleBean();
			searchRoleBean.setOrgRoleId(id);
			List<OrgRoleMapping> orgRoleMapping = userDao.fetchRole(searchRoleBean, null);
			
			//role=iRoleServiceDao.findByRoleId(id);
			if(orgRoleMapping!=null ){
				customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
						Status.SUCCESS.getResponseCode(),
						Status.SUCCESS.getResponseMessage(),
						Status.SUCCESS.getResponseDescription());
				response.put("custumresponse",customReponseStatus);
				response.put("role", orgRoleMapping.get(0));
			}
			else{
				customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
						Status.FAILED.getResponseCode(),
						Status.FAILED.getResponseMessage(),
						Status.FAILED.getResponseDescription());
				response.put("custumresponse",customReponseStatus);
			}
		}
		catch(Exception ex){
			LOGGER.error("Exception occurred in findById method of IRoleServiceImpl class####" + ex);
			customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
					Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(),
					Status.FAILED.getResponseDescription());
			response.put("custumresponse",customReponseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
		
	}
	@Override
	public ServiceResponse findOrgByOrgIdId(Integer id) {
		
		return null;
	}

	

}
