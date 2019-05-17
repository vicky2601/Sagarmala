package org.sagarmala.dao;

import java.util.List;

import org.sagarmala.bean.CommonMasterBean;
import org.sagarmala.model.Agency;
import org.sagarmala.model.CommonMasters;
import org.sagarmala.model.Department;
import org.sagarmala.model.DirectorateOfPortsMaster;
import org.sagarmala.model.FundingAgency;
import org.sagarmala.model.LocationMaster;
import org.sagarmala.model.MaritimeBoard;
import org.sagarmala.model.MinorPortMaster;
import org.sagarmala.model.OgatizationMaster;
import org.sagarmala.model.PORT;
import org.sagarmala.model.ParentMenu;
import org.sagarmala.model.RoleMasterr;
import org.sagarmala.model.StateMaster;
import org.sagarmala.model.Projects;

public interface MasterDao {

	List<CommonMasters> fetchCommonMasters();

	List<RoleMasterr> fetchRoleByOrganisation(int organisationId, int roleType);

	List<Department> fetchSubDepartmentByState(int stateId);

	List<MaritimeBoard> fetchMaritimeBoardByState(int stateId);

	MaritimeBoard fetchMaritimeBoardById(int maritimeId);

	List<PORT> fetchPortByState(int stateId);

	List<ParentMenu> fetchParentMenuAlongWithChild();

	List<PORT> fetchAllPorts();

	List<MaritimeBoard> fetchAllMaritimeBoard();

	List<Department> fetchAllSubDepartment();

	List<OgatizationMaster> fetchOrgatnization();

	List<RoleMasterr> fetchRole();

	List<Agency> fetchAgency();

	List<StateMaster> fetchStates();

	Agency fetchAgencyById(Integer agencyId);

	boolean saveOrUpdateAgency(Agency agencyData);

	RoleMasterr fetchRoleByRoleId(int roleId);

	List<RoleMasterr> fetchRoleBysorting(int sortId);

	List<MinorPortMaster> fetchAllMinorPortMasters();

	List<MinorPortMaster> fetchMinnorPortsByState(int stateId);

	boolean saveOrUpdateDepartment(Department department);

	Department fetchDepartmentById(Integer departmentId);

	int fetchMaxDepartment();

	int fetchMaxAgency();

	List<Department> fetchDepartmentByStateIdOrDepartmentName(CommonMasterBean commonMasterBean);

	List<Agency> fetchAgencyByStateIdOrAgencyName(CommonMasterBean commonMasterBean);

	CommonMasters fetchLineMinistryById(Integer lineministryId);

	boolean saveOrUpdateCommonMasters(CommonMasters commonMasters);

	int fetchMaxLineMinistry();

	List<CommonMasters> fetchCommonMastersListByName(CommonMasterBean commonMasterBean);

	 List<CommonMasters> fetchLineMinistryByTypeCode(String string);

	 List<CommonMasters> fetchLineMinistryByName(String masterName);

	List<Department> fetchDepartmentByName(String masterName);

	List<Department> fetchDepartmentByTypeCode(String masterType);

	List<Agency> fetchAgencytByName(String masterName);

	List<Agency> fetchAgencyByTypeCode(String masterType);
	
	List<Projects> fetchAllProjects();

	List<FundingAgency> fetchFundingAgencytByName(String masterName);

	List<FundingAgency> fetchFundingAgencyByTypeCode(String upperCase);

	int fetchMaxFundingAgency();

	boolean saveOrUpdateFundingAgency(FundingAgency fundingAgency);

	FundingAgency fetchFundingAgencyById(Integer masterId);

	List<FundingAgency> fetchFundingAgencyByStateIdOrAgencyName(CommonMasterBean commonMasterBean);
	
	List<Agency> fetchAgenciesByState(int stateId);
	
	List<MinorPortMaster> fetchAllMinnorPorts();
	
	List<MinorPortMaster> fetchMinnorPortsByIds(List<Integer> ids);

	List<LocationMaster> fetchLocationMasterByStateIdOrLocationName(CommonMasterBean commonMasterBean);

	boolean saveOrUpdateLocationMaster(LocationMaster locationMaster);

	List<LocationMaster> fetchLocationMasterByName(String locationname);

	LocationMaster fetchLocationMasterById(int locationId);
	
	List<LocationMaster> fetchAllLocations();
	
	List<LocationMaster> fetchLocationByState(int stateId);

	List<OgatizationMaster> fetchOgatizationMasterByName(String organizationName);

	List<OgatizationMaster> fetchOrganizationByTypeCode(String upperCase);

	int fetchMaxOrganization();

	boolean saveOrUpdateOrganizationMasters(OgatizationMaster ogatizationMaster);

	OgatizationMaster fetchOrganizationById(int masterId);
	
	List<OgatizationMaster> fetchOrganisationMaster(CommonMasterBean commonMasterBean);
	
    List<DirectorateOfPortsMaster> fetchDirectorateOfPortsByName(String directorateOfPortName);
    
    List<DirectorateOfPortsMaster> fetchDirectorateOfPortsByTypeCode(String directorateOfPortCode);
    
    int fetchMaxDirectorateOfPorts();
    boolean saveOrUpdateDirectorateOfPorts(DirectorateOfPortsMaster directorateOfPortsMaster);
   
    List<DirectorateOfPortsMaster> fetchDirectorateOfPortsByStateIdOrDirectorateOfPortsName(CommonMasterBean commonMasterBean);
    
    DirectorateOfPortsMaster fetchDirectorateOfPortsById(int masterId);
}
