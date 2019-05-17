package org.sagarmala.utility;

import org.sagarmala.bean.CommonMasterBean;
import org.sagarmala.model.Agency;
import org.sagarmala.model.CommonMasters;
import org.sagarmala.model.Department;
import org.sagarmala.model.DirectorateOfPortsMaster;
import org.sagarmala.model.FundingAgency;
import org.sagarmala.model.LocationMaster;
import org.sagarmala.model.OgatizationMaster;
import org.sagarmala.model.StateMaster;

public class BeanEntityConverssion {
	
	public static Agency setcommonMasterBeanToAgency(Agency agency, CommonMasterBean commonMasterBean) {
		StateMaster stateId = new StateMaster();
		stateId.setMasterId(commonMasterBean.getStateId());

		if (commonMasterBean.getMasterId() != 0) {
			agency.setMasterId(commonMasterBean.getMasterId());
		}
		if (commonMasterBean.getMasterName() != null) {
			agency.setMasterName(commonMasterBean.getMasterName().trim());
		}
		if (commonMasterBean.getMasterCode() != 0) {
			agency.setMasterUniqueCode(commonMasterBean.getMasterCode());
		}
		if (commonMasterBean.getStateId() != 0) {
			agency.setStateId(stateId);
		}
		if (commonMasterBean.getCreatedBy() != null  && commonMasterBean.getCreatedBy() != 0) {
			agency.setCreatedBy(commonMasterBean.getCreatedBy());
		}
		if (commonMasterBean.getUpdatedBy() != null && commonMasterBean.getUpdatedBy() != 0) {
			agency.setUpdatedBy(commonMasterBean.getUpdatedBy());
		}
		agency.setIsActive(commonMasterBean.getIsActive());
		return agency;
	}

	public static Department setcommonMasterBeanToDepartment(Department department, CommonMasterBean commonMasterBean) {
		StateMaster stateId = new StateMaster();
		stateId.setMasterId(commonMasterBean.getStateId());

		if (commonMasterBean.getMasterId() != 0) {
			department.setDepartmentId(commonMasterBean.getMasterId());
		}
		if (commonMasterBean.getMasterName() != null) {
			department.setDepartmentName(commonMasterBean.getMasterName().trim());
		}
		if (commonMasterBean.getMasterCode() != 0) {
			department.setDepartmentCode(commonMasterBean.getMasterCode());
		} else {
			department.setDepartmentCode(012);
		}
		if (commonMasterBean.getStateId() != 0) {
			department.setStateId(stateId);
		}
		if (commonMasterBean.getCreatedBy() != null && commonMasterBean.getCreatedBy() != 0) {
			department.setCreatedBy(commonMasterBean.getCreatedBy());
		}
		if (commonMasterBean.getUpdatedBy() != null && commonMasterBean.getUpdatedBy() != 0) {
			department.setUpdatedBy(commonMasterBean.getUpdatedBy());
		}
		department.setIsActive(commonMasterBean.getIsActive());
			
		return department;
	}

	public static CommonMasters setcommonMasterBeanToCommonMasters(CommonMasters commonMasters,
			CommonMasterBean commonMasterBean) {
		if (commonMasterBean.getMasterId() != 0) {
			commonMasters.setMasterId(commonMasterBean.getMasterId());
		}
		if (commonMasterBean.getMasterName() != null) {
			commonMasters.setMasterName(commonMasterBean.getMasterName().trim());
		}
		if (commonMasterBean.getMasterCode() != 0) {
			commonMasters.setMasterCode(commonMasterBean.getMasterCode());
		}
		if (commonMasterBean.getMasterType() != null) {
			commonMasters.setMasterType(commonMasterBean.getMasterType());
		}
		if (commonMasterBean.getCreatedBy() !=null && commonMasterBean.getCreatedBy() != 0) {
			commonMasters.setCreatedBy(commonMasterBean.getCreatedBy());
		}
		if (commonMasterBean.getUpdatedBy() != null && commonMasterBean.getUpdatedBy() != 0) {
			commonMasters.setUpdatedBy(commonMasterBean.getUpdatedBy());
		}
		commonMasters.setIsActive(commonMasterBean.getIsActive());
		
		return commonMasters;
	}

	public static FundingAgency setcommonMasterBeanToFundingAgency(FundingAgency fundingAgency,
			CommonMasterBean commonMasterBean) {
		StateMaster stateId = new StateMaster();
		stateId.setMasterId(commonMasterBean.getStateId());

		if (commonMasterBean.getMasterId() != 0) {
			fundingAgency.setMasterId(commonMasterBean.getMasterId());
		}
		if (commonMasterBean.getMasterName() != null) {
			fundingAgency.setMasterName(commonMasterBean.getMasterName().trim());
		}
		if (commonMasterBean.getMasterCode() != 0) {
			fundingAgency.setMasterUniqueCode(commonMasterBean.getMasterCode());
		}
		if (commonMasterBean.getStateId() != 0) {
			fundingAgency.setStateId(stateId);
		}
		if (commonMasterBean.getCreatedBy() != null  && commonMasterBean.getCreatedBy() != 0) {
			fundingAgency.setCreatedBy(commonMasterBean.getCreatedBy());
		}
		if (commonMasterBean.getUpdatedBy() != null && commonMasterBean.getUpdatedBy() != 0) {
			fundingAgency.setUpdatedBy(commonMasterBean.getUpdatedBy());
		}
		fundingAgency.setIsActive(commonMasterBean.getIsActive());
		return fundingAgency;
	}

	public static LocationMaster setcommonMasterBeanToLocationMaster(LocationMaster locationMaster,
			CommonMasterBean commonMasterBean) {
		StateMaster stateId = new StateMaster();
		stateId.setMasterId(commonMasterBean.getStateId());

		if (commonMasterBean.getMasterId() != 0) {
			locationMaster.setLocationId(commonMasterBean.getMasterId());
		}
		if (commonMasterBean.getMasterName() != null) {
			locationMaster.setLocationName(commonMasterBean.getMasterName().trim());
		}
		if (commonMasterBean.getStateId() != 0) {
			locationMaster.setStateId(stateId);
		}
		if (commonMasterBean.getCreatedBy() != null  && commonMasterBean.getCreatedBy() != 0) {
			locationMaster.setCreatedBy(commonMasterBean.getCreatedBy());
		}
		if (commonMasterBean.getUpdatedBy() != null && commonMasterBean.getUpdatedBy() != 0) {
			locationMaster.setUpdatedBy(commonMasterBean.getUpdatedBy());
		}
		locationMaster.setIsActive(commonMasterBean.getIsActive());
		return locationMaster;
	}

	public static OgatizationMaster setcommonMasterBeanToOrganizationMasters(OgatizationMaster ogatizationMaster,
			CommonMasterBean commonMasterBean) {
		if (commonMasterBean.getMasterId() != 0) {
			ogatizationMaster.setMasterId(commonMasterBean.getMasterId());
		}
		if (commonMasterBean.getMasterName() != null) {
			ogatizationMaster.setMasterName(commonMasterBean.getMasterName().trim());
		}
		if (commonMasterBean.getMasterCode() != 0) {
			ogatizationMaster.setMasterCode(commonMasterBean.getMasterType());
		}
		if (commonMasterBean.getMasterType() != null) {
			ogatizationMaster.setMasterUniquecode(commonMasterBean.getMasterCode());
		}
		if (commonMasterBean.getCreatedBy() !=null && commonMasterBean.getCreatedBy() != 0) {
			ogatizationMaster.setCreatedBy(commonMasterBean.getCreatedBy());
		}
		if (commonMasterBean.getUpdatedBy() != null && commonMasterBean.getUpdatedBy() != 0) {
			ogatizationMaster.setUpdatedBy(commonMasterBean.getUpdatedBy());
		}
		ogatizationMaster.setIsActive(commonMasterBean.getIsActive());
		
		return ogatizationMaster;
	}
	
	public static DirectorateOfPortsMaster setcommonMasterBeanToDirectorateOfPortsMaster(DirectorateOfPortsMaster directorateOfPortsMaster,
			CommonMasterBean commonMasterBean) {
		StateMaster stateId = new StateMaster();
		stateId.setMasterId(commonMasterBean.getStateId());
		if (commonMasterBean.getMasterId() != 0) {
			directorateOfPortsMaster.setMasterId(commonMasterBean.getMasterId());
		}
		if (commonMasterBean.getMasterName() != null) {
			directorateOfPortsMaster.setMasterName(commonMasterBean.getMasterName().trim());
		}
		if (commonMasterBean.getMasterCode() != 0) {
			directorateOfPortsMaster.setMasterCode(commonMasterBean.getMasterType());
		}
		if (commonMasterBean.getStateId() != 0) {
			directorateOfPortsMaster.setStateId(stateId);
		}
		if (commonMasterBean.getMasterType() != null) {
			directorateOfPortsMaster.setMasterUniqueCode(commonMasterBean.getMasterCode());
		}
		if (commonMasterBean.getCreatedBy() !=null && commonMasterBean.getCreatedBy() != 0) {
			directorateOfPortsMaster.setCreatedBy(commonMasterBean.getCreatedBy());
		}
		if (commonMasterBean.getUpdatedBy() != null && commonMasterBean.getUpdatedBy() != 0) {
			directorateOfPortsMaster.setUpdatedBy(commonMasterBean.getUpdatedBy());
		}
		directorateOfPortsMaster.setIsActive(commonMasterBean.getIsActive());
		
		return directorateOfPortsMaster;
	}
}
