package org.sagarmala.service;

import org.sagarmala.bean.CommonMasterBean;
import org.sagarmala.dto.ServiceResponse;

public interface MasterService {

	ServiceResponse fetchCommonMasters();

	ServiceResponse fetchSubDepartmentByState(int stateId);

	ServiceResponse fetchMaritimeBoardByState(int stateId);

	ServiceResponse fetchRoleByOrganisation(int departmentId,int roleType);
	
	ServiceResponse fetchPortByState(int stateId);
	
	ServiceResponse fetchparentAndChildMenu();
	
	ServiceResponse fetchAllPortsAndMBoardAndDepartments();

	ServiceResponse fetchAgencyById(Integer agencyId);

	ServiceResponse saveOrUpdateMasters(CommonMasterBean commonMasterBean);

	ServiceResponse fetchDepartmenyById(Integer departmentId);

	ServiceResponse fetchDepartmentMasterslist(CommonMasterBean commonMasterBean);

	ServiceResponse fetchAgencyMasterslist(CommonMasterBean commonMasterBean);
	
	ServiceResponse fetchMinnorPortsByState(int stateId);
	
	ServiceResponse fetchLineMinistryById(Integer lineministryId);

	ServiceResponse fetchCommonMasterslist(CommonMasterBean commonMasterBean);

	ServiceResponse fetchFundingAgencyMasterslist(CommonMasterBean commonMasterBean);

	ServiceResponse fetchFundingAgencyById(Integer agencyId);
	
	ServiceResponse fetchAgecyByState(int stateId);

	ServiceResponse fetchLocationMasterslist(CommonMasterBean commonMasterBean);

	ServiceResponse fetchLocationMasterById(Integer locationId);
	ServiceResponse fetchLocationByState(int stateId);
	ServiceResponse fetchOrganisationMasterslist(CommonMasterBean commonMasterBean);
	ServiceResponse fetchDirectorateOfPortsMasterslist(CommonMasterBean commonMasterBean);
	ServiceResponse fetchOrganisationMasterById(Integer locationId);
	public ServiceResponse fetchDirectorateOfPortsId(Integer masterId) ;

}
