package org.sagarmala.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.sagarmala.bean.CommonMasterBean;
import org.sagarmala.dao.IRoleServiceDao;
import org.sagarmala.dao.MasterDao;
import org.sagarmala.dto.CustomReponseStatus;
import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.enumtype.constants.ActiveDactiveStatus;
import org.sagarmala.enumtype.constants.MastersKeys;
import org.sagarmala.enumtype.constants.Status;
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
import org.sagarmala.model.Projects;
import org.sagarmala.model.RoleMasterr;
import org.sagarmala.model.StateMaster;
import org.sagarmala.service.MasterService;
import org.sagarmala.utility.BeanEntityConverssion;
import org.sagarmala.utility.CommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "masterService")
public class MasterServiceImpl implements MasterService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MasterServiceImpl.class);

	@Autowired
	private MasterDao masterDao;

	@Autowired
	private IRoleServiceDao iRoleServiceDao;

	/*
	 * (non-Javadoc) By Ramsagar
	 * 
	 * @see org.sagarmala.service.UserService#fetchCommonMasters()
	 */
	@Override
	public ServiceResponse fetchCommonMasters() {
		LOGGER.info("Executing fetchCommonMasters  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;

		List<OgatizationMaster> orgMaster = null;
		List<Agency> agencyMaster = null;
		List<StateMaster> stateMaster = null;
        List<LocationMaster> locationMasters=null;
		List<PORT> portList = null;
		List<MaritimeBoard> maritimeBoardList = null;
		List<Department> departmentList = null;
		List<MinorPortMaster> minorPortMaster = new ArrayList<>();
		List<Projects> projectsList = null;

		List<Object> lineMinistryMaster = new ArrayList<>();

		Map<String, Object> masterByType = new HashMap<>();

		try {

			orgMaster = masterDao.fetchOrgatnization();
			agencyMaster = masterDao.fetchAgency();
			stateMaster = masterDao.fetchStates();
			projectsList = masterDao.fetchAllProjects();
			locationMasters=masterDao.fetchAllLocations();
			minorPortMaster = masterDao.fetchAllMinorPortMasters();

			portList = masterDao.fetchAllPorts();
			maritimeBoardList = masterDao.fetchAllMaritimeBoard();
			departmentList = masterDao.fetchAllSubDepartment();

			/*
			 * portList=masterDao.fetchAllPorts();
			 * maritimeBoardList=masterDao.fetchAllMaritimeBoard();
			 * departmentList=masterDao.fetchAllSubDepartment();
			 * 
			 * 
			 * 
			 * portList = masterDao.fetchAllPorts(); maritimeBoardList =
			 * masterDao.fetchAllMaritimeBoard(); departmentList =
			 * masterDao.fetchAllSubDepartment();
			 */

			List<CommonMasters> commonMasters = masterDao.fetchCommonMasters();
			for (int i = 0; i < commonMasters.size(); i++) {
				if (commonMasters.get(i).getMasterType().equals(MastersKeys.LINEMINISTRY.getDescription())) {
					lineMinistryMaster.add(commonMasters.get(i));
				}
			}

			masterByType.put(MastersKeys.ORGANISATION.getDescription(), orgMaster);
			masterByType.put(MastersKeys.AGENCY.getDescription(), agencyMaster);
			masterByType.put(MastersKeys.STATE.getDescription(), stateMaster);
			masterByType.put(MastersKeys.LINEMINISTRY.getDescription(), lineMinistryMaster);
			// masterByType.put(MastersKeys.DESIGNATION.getDescription(),
			// designationMaster);
			masterByType.put(MastersKeys.MINORPORTS.getDescription(), minorPortMaster);

			masterByType.put(MastersKeys.PORT.getDescription(), portList);
			masterByType.put(MastersKeys.MARITIMEBOARD.getDescription(), maritimeBoardList);
			masterByType.put(MastersKeys.DEPARTMENT.getDescription(), departmentList);

			masterByType.put(MastersKeys.PROJECTS.getDescription(), projectsList);
			masterByType.put(MastersKeys.LOCATIONMASTER.getDescription(), locationMasters);
			/*
			 * commonMasterByType = commonMasters.stream()
			 * .collect(Collectors.groupingBy(CommonMasters::getMasterType));
			 */
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("commonMastersList", masterByType);
			response.put("totalSize", masterByType.size());
			response.put("roleList", iRoleServiceDao.fetchAllRole());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchCommonMasters  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchSubDepartmentByState(int stateId) {
		LOGGER.info("Executing fetchSubDepartmentByState  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<Department> subDepartments = masterDao.fetchSubDepartmentByState(stateId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("departmentList", subDepartments);
			response.put("totalSize", subDepartments.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchSubDepartmentByState  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchMaritimeBoardByState(int stateId) {
		LOGGER.info("Executing fetchMaritimeBoardByState  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<MaritimeBoard> maritimeBoards = masterDao.fetchMaritimeBoardByState(stateId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("maritimeList", maritimeBoards);
			response.put("totalSize", maritimeBoards.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchMaritimeBoardByState  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchRoleByOrganisation(int organisationId, int roleType) {
		LOGGER.info("Executing fetchRoleByOrganisation  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		// SearchRoleBean searchRoleBean = new SearchRoleBean();
		// searchRoleBean.setOrganisationId(organisationId);
		List<RoleMasterr> roles = new ArrayList<>();
		RoleMasterr roleMasterr = new RoleMasterr();
		try {
			// List<RoleMasterr> roles =
			// masterDao.fetchRoleByOrganisation(departmentId, roleType);
			/*
			 * List<OrgRoleMapping> orgRoleMapping =
			 * userDao.fetchRole(searchRoleBean, null); for (int i = 0; i <
			 * orgRoleMapping.size(); i++) {
			 * roles.add(orgRoleMapping.get(i).getRoleMaster()); }
			 */
			roleMasterr = masterDao.fetchRoleByRoleId(roleType);
			if (roleMasterr != null) {
				roles = masterDao.fetchRoleBysorting(roleMasterr.getSortId());
				System.err.println("sorted Roles are " + roles);
			}

			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("roleList", roles);
			response.put("totalSize", roles.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchRoleByOrganisation  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchPortByState(int stateId) {
		LOGGER.info("Executing fetchPortByState  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<PORT> ports = masterDao.fetchPortByState(stateId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("portList", ports);
			response.put("totalSize", ports.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchPortByState  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchparentAndChildMenu() {
		LOGGER.info("Executing fetchparentAndChildMenu  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<ParentMenu> parentandchildmenu = masterDao.fetchParentMenuAlongWithChild();
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("parentandchildmenuList", parentandchildmenu);
			response.put("totalSize", parentandchildmenu.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchparentAndChildMenu  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchAllPortsAndMBoardAndDepartments() {
		LOGGER.info("Executing fetchAllPortsAndMBoardAndDepartments  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<PORT> ports = masterDao.fetchAllPorts();
			List<Department> departments = masterDao.fetchAllSubDepartment();
			List<MaritimeBoard> mBoard = masterDao.fetchAllMaritimeBoard();
			List<MinorPortMaster> miPortMaster = masterDao.fetchAllMinnorPorts();

			// List<String> status=new ArrayList<>();
			/*
			 * Map<String,Integer> status=new HashMap<>();
			 * status.put("masterId", ActiveDactiveStatus.ACTIVE.getId());
			 * status.put("masterName", ActiveDactiveStatus.ACTIVE.getId());
			 */

			List<Agency> agency = masterDao.fetchAgency();
			List<StateMaster> states = masterDao.fetchStates();
			// List<LineM> states=masterDao.fetchStates();

			List<Object> lineMinistryMaster = new ArrayList<>();
			List<CommonMasters> commonMasters = masterDao.fetchCommonMasters();
			for (int i = 0; i < commonMasters.size(); i++) {
				if (commonMasters.get(i).getMasterType().equals(MastersKeys.LINEMINISTRY.getDescription())) {
					lineMinistryMaster.add(commonMasters.get(i));
				}
			}

			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);

			response.put("portList", ports);
			response.put("departmentList", departments);
			response.put("mBoard", mBoard);

			// response.put("status", status);
			response.put("agency", agency);
			response.put("states", states);
			response.put("lineMinistryMaster", lineMinistryMaster);
			response.put("miPorts", miPortMaster);

			response.put("totalSize", ports.size());
			LOGGER.info("--Result -->" + response.toString());
		} catch (Exception e) {
			e.printStackTrace();
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		System.err.print("ending.......................");
		return serviceResponse;
	}

	/*
	 * For fetch Agency by AgencyId Author : Ramsagar
	 * 
	 * @see org.sagarmala.service.MasterService#fetchDepartmenyById(java.lang.
	 * Integer)
	 */
	@Override
	public ServiceResponse fetchAgencyById(Integer agencyId) {
		LOGGER.info("Executing fetchAgencyById  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			Agency agencyData = masterDao.fetchAgencyById(agencyId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("agencyData", agencyData);

			LOGGER.info("--Result -->" + response.toString());
		} catch (Exception e) {
			e.printStackTrace();
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse saveOrUpdateMasters(CommonMasterBean commonMasterBean) {
		LOGGER.info("Executing saveOrUpdateMasters  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();

		if (commonMasterBean.getMasterTypeCode().equals(MastersKeys.AGENCY.getDescription())) {
			serviceResponse = saveOrUpdateAgency(commonMasterBean);

		} else if (commonMasterBean.getMasterTypeCode().equals(MastersKeys.DEPARTMENT.getDescription())) {
			serviceResponse = saveOrUpdateDepartment(commonMasterBean);

		} else if (commonMasterBean.getMasterTypeCode().equals(MastersKeys.LINEMINISTRY.getDescription())) {
			serviceResponse = saveOrUpdateLineMistry(commonMasterBean);

		} else if (commonMasterBean.getMasterTypeCode().equals(MastersKeys.FUNDINGAGENCY.getDescription())) {
			serviceResponse = saveOrUpdateFundingAgency(commonMasterBean);

		} else if (commonMasterBean.getMasterTypeCode().equals(MastersKeys.LOCATIONMASTER.getDescription())) {
			serviceResponse = saveOrUpdateLocationMaster(commonMasterBean);

		}
		else if (commonMasterBean.getMasterTypeCode().equals(MastersKeys.ORGANIZATION.getDescription())) {
			serviceResponse = saveOrUpdateorganizationMaster(commonMasterBean);

		}
		else if (commonMasterBean.getMasterTypeCode().equals(MastersKeys.DirectorateOfPorts.getDescription())) {
			serviceResponse = saveOrUpdateDirectorateOfPorts(commonMasterBean);

		}

		return serviceResponse;
	}

	/*
	 * For save or update agency master Author : Ramsagar
	 * 
	 * @see org.sagarmala.service.MasterService#fetchDepartmenyById(java.lang.
	 * Integer)
	 */
	public ServiceResponse saveOrUpdateAgency(CommonMasterBean commonMasterBean) {
		LOGGER.error("saveOrUpdateAgency method Execution Started of MasterServiceImpl class");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus customReponseStatus = null;
		Agency agency = new Agency();
		int maxValue = 0;
		String masterType = "";
		String masterTemp = "";
		try {
			boolean success = false;
			if (commonMasterBean != null) {
				if (commonMasterBean.getMasterId() == 0) {
					List<Agency> agencyByName = masterDao.fetchAgencytByName(commonMasterBean.getMasterName().trim());
					String agencyName[] = commonMasterBean.getMasterName().split(" ");

					if (agencyByName == null || agencyByName.size() == 0) {
						for (int i = 0; i < agencyName.length; i++) {
							masterTemp = masterTemp + agencyName[i].substring(0, 1);
						}
						List<Agency> agencyByTypeCode = masterDao.fetchAgencyByTypeCode(masterTemp.toUpperCase());
						if (agencyByTypeCode == null || agencyByTypeCode.size() == 0) {
							masterType = masterType + masterTemp;
						} else {
							masterType = masterType + masterTemp + agencyName[agencyName.length - 1].substring(1, 2);
						}
						maxValue = masterDao.fetchMaxAgency();
						agency = BeanEntityConverssion.setcommonMasterBeanToAgency(agency, commonMasterBean);
						agency.setMasterUniqueCode(maxValue + 1);
						agency.setMasterCode(masterType.toUpperCase());
						agency.setCreatedDate(CommonUtility.getCurrentDateTime());
						agency.setUpdatedDate(CommonUtility.getCurrentDateTime());
						agency.setIsActive(ActiveDactiveStatus.ACTIVE.getId());
						success = masterDao.saveOrUpdateAgency(agency);
						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				} else {
					List<Agency> agencyByName=null;
					try{
					agencyByName = masterDao.fetchAgencytByName(commonMasterBean.getMasterName().trim());
					}catch (Exception e) {
						LOGGER.info(e.toString());
					}
					if (agencyByName == null || agencyByName.size() == 0) {
						agency = masterDao.fetchAgencyById(commonMasterBean.getMasterId());
						agency = BeanEntityConverssion.setcommonMasterBeanToAgency(agency, commonMasterBean);
						agency.setUpdatedDate(CommonUtility.getCurrentDateTime());
						success = masterDao.saveOrUpdateAgency(agency);

						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				}
			} else {
				customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
						Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
						Status.FAILED.getResponseDescription());
				response.put("customResponse", customReponseStatus);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception occurred in saveOrUpdateAgency method of MasterServiceImpl class#" + ex);
			customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
					Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
					Status.FAILED.getResponseDescription());
			response.put("customResponse", customReponseStatus);
		}
		response.put("custumresponse", customReponseStatus);
		serviceResponse.setServiceResponse(response);
		LOGGER.error("saveOrUpdateAgency method Execution completed of MasterServiceImpl class");
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchMinnorPortsByState(int stateId) {
		LOGGER.info("Executing fetchMinnorPortsByState  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<MinorPortMaster> minnorPorts = masterDao.fetchMinnorPortsByState(stateId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put(MastersKeys.MINORPORTS.getDescription(), minnorPorts);
			response.put("totalSize", minnorPorts.size());
			LOGGER.info("--Result -->" + response.toString());
		} catch (Exception e) {
			e.printStackTrace();
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	/*
	 * For save or update department master Author : Ramsagar
	 * 
	 * @see org.sagarmala.service.MasterService#fetchDepartmenyById(java.lang.
	 * Integer)
	 */
	public ServiceResponse saveOrUpdateDepartment(CommonMasterBean commonMasterBean) {
		LOGGER.error("saveOrUpdateDepartment method Execution Started of MasterServiceImpl class");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus customReponseStatus = null;
		Department department = new Department();
		int maxValue;
		String masterType = "";
		String masterTemp = "";
		try {
			boolean success = false;
			if (commonMasterBean != null) {
				if (commonMasterBean.getMasterId() == 0) {
					List<Department> departmentByName = masterDao
							.fetchDepartmentByName(commonMasterBean.getMasterName().trim());
					String departmentName[] = commonMasterBean.getMasterName().split(" ");

					if (departmentByName == null || departmentByName.size() == 0) {
						for (int i = 0; i < departmentName.length; i++) {
							masterTemp = masterTemp + departmentName[i].substring(0, 1);
						}
						List<Department> departmentByTypeCode = masterDao
								.fetchDepartmentByTypeCode(masterTemp.toUpperCase());
						if (departmentByTypeCode == null || departmentByTypeCode.size() == 0) {
							masterType = masterType + masterTemp;
						} else {
							masterType = masterType + masterTemp
									+ departmentName[departmentName.length - 1].substring(1, 2);
						}
						maxValue = masterDao.fetchMaxDepartment();
						department = BeanEntityConverssion.setcommonMasterBeanToDepartment(department,
								commonMasterBean);
						department.setDepartmentCode(maxValue + 1);
						department.setDepartmentTypeCode(masterType.toUpperCase());
						department.setCreatedDate(CommonUtility.getCurrentDateTime());
						department.setUpdatedDate(CommonUtility.getCurrentDateTime());
						department.setIsActive(ActiveDactiveStatus.ACTIVE.getId());
						success = masterDao.saveOrUpdateDepartment(department);
						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				} else {
					List<Department> departmentByName=null;
					try{
					 departmentByName = masterDao
							.fetchDepartmentByName(commonMasterBean.getMasterName().trim());
					}catch (Exception e) {
						LOGGER.info(e.toString());
					}
					if (departmentByName == null || departmentByName.size() == 0) {
						department = masterDao.fetchDepartmentById(commonMasterBean.getMasterId());
						department = BeanEntityConverssion.setcommonMasterBeanToDepartment(department,
								commonMasterBean);
						department.setUpdatedDate(CommonUtility.getCurrentDateTime());
						success = masterDao.saveOrUpdateDepartment(department);

						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				}
			} else {
				customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
						Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
						Status.FAILED.getResponseDescription());
				response.put("customResponse", customReponseStatus);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception occurred in saveOrUpdateDepartment method of MasterServiceImpl class#", ex);
			customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
					Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
					Status.FAILED.getResponseDescription());
			response.put("customResponse", customReponseStatus);
		}
		response.put("custumresponse", customReponseStatus);
		serviceResponse.setServiceResponse(response);
		LOGGER.error("saveOrUpdateDepartment method Execution completed of MasterServiceImpl class");
		return serviceResponse;
	}

	/*
	 * For fetch Agency by DepartmentId Author : Ramsagar
	 * 
	 * @see org.sagarmala.service.MasterService#fetchDepartmenyById(java.lang.
	 * Integer)
	 */
	@Override
	public ServiceResponse fetchDepartmenyById(Integer departmentId) {
		LOGGER.info("Executing fetchDepartmenyById  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			Department department = masterDao.fetchDepartmentById(departmentId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("department", department);

		} catch (Exception e) {
			LOGGER.info("Exception Occured in fetchDepartmenyById  method of MasterServiceImpl");
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	/*
	 * For fetch all Department with search by state and Department Name
	 * 
	 * @see Author : Ramsagar
	 * 
	 * @see org.sagarmala.service.MasterService#fetchDepartmentMasterslist()
	 */
	@Override
	public ServiceResponse fetchDepartmentMasterslist(CommonMasterBean commonMasterBean) {
		LOGGER.info("Executing fetchDepartmentMasterslist  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		List<Department> departmentList = null;
		try {
			departmentList = masterDao.fetchDepartmentByStateIdOrDepartmentName(commonMasterBean);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("departmentList", departmentList);
			response.put("totalSize", departmentList.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchDepartmentMasterslist  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	/*
	 * (For fetch all Agency with search by state and Agency Name
	 * 
	 * @see Author : Ramsagar
	 * org.sagarmala.service.MasterService#fetchAgencyMasterslist(org.sagarmala.
	 * bean.CommonMasterBean)
	 */
	@Override
	public ServiceResponse fetchAgencyMasterslist(CommonMasterBean commonMasterBean) {
		LOGGER.info("Executing fetchAgencyMasterslist  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		List<Agency> agencyListData = null;
		try {
			agencyListData = masterDao.fetchAgencyByStateIdOrAgencyName(commonMasterBean);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("agencyListData", agencyListData);
			response.put("totalSize", agencyListData.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchAgencyMasterslist  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	/*
	 * (non-Javadoc) For fetching line ministry by id.
	 * 
	 * @Author : Ramsagar
	 * 
	 * @see org.sagarmala.service.MasterService#fetchLineMinistryById(java.lang.
	 * Integer)
	 */
	@Override
	public ServiceResponse fetchLineMinistryById(Integer lineministryId) {
		LOGGER.info("Executing fetchLineMinistryById  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			CommonMasters commonMastersData = masterDao.fetchLineMinistryById(lineministryId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("commonMastersData", commonMastersData);
		} catch (Exception e) {
			LOGGER.error("Exception in fetchLineMinistryById  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	public ServiceResponse saveOrUpdateLineMistry(CommonMasterBean commonMasterBean) {
		LOGGER.error("saveOrUpdateLineMistry method Execution Started of MasterServiceImpl class");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus customReponseStatus = null;
		CommonMasters commonMasters = new CommonMasters();
		int maxValue = 0;
		String masterType = "";
		String masterTemp = "";
		try {
			boolean success = false;
			if (commonMasterBean != null) {
				if (commonMasterBean.getMasterId() == 0) {
					List<CommonMasters> commonMastersByName = masterDao
							.fetchLineMinistryByName(commonMasterBean.getMasterName().trim());
					String lineMinistryName[] = commonMasterBean.getMasterName().split(" ");

					if (commonMastersByName == null || commonMastersByName.size() == 0) {
						for (int i = 0; i < lineMinistryName.length; i++) {
							masterTemp = masterTemp + lineMinistryName[i].substring(0, 1);
						}
						List<CommonMasters> commonMastersByTypeCode = masterDao
								.fetchLineMinistryByTypeCode(masterTemp.toUpperCase());
						if (commonMastersByTypeCode == null || commonMastersByTypeCode.size() == 0) {
							masterType = masterType + masterTemp;
						} else {
							masterType = masterType + masterTemp
									+ lineMinistryName[lineMinistryName.length - 1].substring(1, 2);
						}
						maxValue = masterDao.fetchMaxLineMinistry();
						commonMasters = BeanEntityConverssion.setcommonMasterBeanToCommonMasters(commonMasters,
								commonMasterBean);
						commonMasters.setMasterCode(maxValue + 1);
						commonMasters.setMasterTypeCode(masterType.toUpperCase());
						commonMasters.setMasterType(MastersKeys.LINEMINISTRY.getDescription());
						commonMasters.setCreatedDate(CommonUtility.getCurrentDateTime());
						commonMasters.setUpdatedDate(CommonUtility.getCurrentDateTime());
						commonMasters.setIsActive(ActiveDactiveStatus.ACTIVE.getId());
						success = masterDao.saveOrUpdateCommonMasters(commonMasters);

						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				} else {
					List<CommonMasters> commonMastersByName=null;
					try{
					 commonMastersByName = masterDao
							.fetchLineMinistryByName(commonMasterBean.getMasterName().trim());
					}catch (Exception e) {
						LOGGER.info(e.toString());
					}
					if (commonMastersByName == null || commonMastersByName.size() == 0) {
						commonMasters = masterDao.fetchLineMinistryById(commonMasterBean.getMasterId());
						commonMasters = BeanEntityConverssion.setcommonMasterBeanToCommonMasters(commonMasters,
								commonMasterBean);
						commonMasters.setUpdatedDate(CommonUtility.getCurrentDateTime());
						success = masterDao.saveOrUpdateCommonMasters(commonMasters);

						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				}
			} else {
				customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
						Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
						Status.FAILED.getResponseDescription());
				response.put("customResponse", customReponseStatus);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception occurred in saveOrUpdateLineMistry method of MasterServiceImpl", ex);
			customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
					Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
					Status.FAILED.getResponseDescription());
			response.put("customResponse", customReponseStatus);
		}
		serviceResponse.setServiceResponse(response);
		LOGGER.error("saveOrUpdateLineMistry method Execution completed of MasterServiceImpl class");
		return serviceResponse;
	}

	/*
	 * (non-Javadoc) For fetching all common masters list with search by name.
	 * Author: ramsagar.
	 * 
	 * @see
	 * org.sagarmala.service.MasterService#fetchCommonMasterslist(org.sagarmala.
	 * bean.CommonMasterBean)
	 */

	@Override
	public ServiceResponse fetchCommonMasterslist(CommonMasterBean commonMasterBean) {
		LOGGER.info("Executing fetchCommonMasterslist  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		List<CommonMasters> commonMastersData = null;
		try {
			commonMastersData = masterDao.fetchCommonMastersListByName(commonMasterBean);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("commonMastersData", commonMastersData);
			response.put("totalSize", commonMastersData.size());
		} catch (Exception e) {
			LOGGER.info("Exception in fetchCommonMasterslist  method of MasterServiceImpl");
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	public ServiceResponse saveOrUpdateFundingAgency(CommonMasterBean commonMasterBean) {
		LOGGER.error("saveOrUpdateFundingAgency method Execution Started of MasterServiceImpl class");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus customReponseStatus = null;
		FundingAgency fundingAgency = new FundingAgency();
		int maxValue = 0;
		String masterType = "";
		String masterTemp = "";
		try {
			boolean success = false;
			if (commonMasterBean != null) {
				if (commonMasterBean.getMasterId() == 0) {
					List<FundingAgency> fundingAgencyByName = masterDao
							.fetchFundingAgencytByName(commonMasterBean.getMasterName().trim());
					String fundingAgencyName[] = commonMasterBean.getMasterName().split(" ");

					if (fundingAgencyByName == null || fundingAgencyByName.size() == 0) {
						for (int i = 0; i < fundingAgencyName.length; i++) {
							masterTemp = masterTemp + fundingAgencyName[i].substring(0, 1);
						}
						List<FundingAgency> fundingAgencyByTypeCode = masterDao
								.fetchFundingAgencyByTypeCode(masterTemp.toUpperCase());
						if (fundingAgencyByTypeCode == null || fundingAgencyByTypeCode.size() == 0) {
							masterType = masterType + masterTemp;
						} else {
							masterType = masterType + masterTemp
									+ fundingAgencyName[fundingAgencyName.length - 1].substring(1, 2);
						}
						maxValue = masterDao.fetchMaxFundingAgency();
						fundingAgency = BeanEntityConverssion.setcommonMasterBeanToFundingAgency(fundingAgency,
								commonMasterBean);
						fundingAgency.setMasterUniqueCode(maxValue + 1);
						fundingAgency.setMasterCode(masterType.toUpperCase());
						fundingAgency.setCreatedDate(CommonUtility.getCurrentDateTime());
						fundingAgency.setUpdatedDate(CommonUtility.getCurrentDateTime());
						fundingAgency.setIsActive(ActiveDactiveStatus.ACTIVE.getId());
						success = masterDao.saveOrUpdateFundingAgency(fundingAgency);
						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				} else {
					List<FundingAgency> fundingAgencyByName=null;
					try {
					fundingAgencyByName = masterDao
							.fetchFundingAgencytByName(commonMasterBean.getMasterName().trim());
					}catch (Exception e) {
					LOGGER.info(e.toString());
					}
					if (fundingAgencyByName == null || fundingAgencyByName.size() == 0) {
						fundingAgency = masterDao.fetchFundingAgencyById(commonMasterBean.getMasterId());
						fundingAgency = BeanEntityConverssion.setcommonMasterBeanToFundingAgency(fundingAgency,
								commonMasterBean);
						fundingAgency.setUpdatedDate(CommonUtility.getCurrentDateTime());
						success = masterDao.saveOrUpdateFundingAgency(fundingAgency);

						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				}
			} else {
				customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
						Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
						Status.FAILED.getResponseDescription());
				response.put("customResponse", customReponseStatus);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in saveOrUpdateFundingAgency  method of MasterServiceImpl", e);
			customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
					Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
					Status.FAILED.getResponseDescription());
			response.put("customResponse", customReponseStatus);
		}
		response.put("custumresponse", customReponseStatus);
		serviceResponse.setServiceResponse(response);
		LOGGER.error("saveOrUpdateFundingAgency method Execution completed of MasterServiceImpl class");
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchFundingAgencyMasterslist(CommonMasterBean commonMasterBean) {
		LOGGER.info("Executing fetchFundingAgencyMasterslist  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		List<FundingAgency> fundingAgencyListData = null;
		try {
			fundingAgencyListData = masterDao.fetchFundingAgencyByStateIdOrAgencyName(commonMasterBean);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("fundingAgencyListData", fundingAgencyListData);
			response.put("totalSize", fundingAgencyListData.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchFundingAgencyMasterslist  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchFundingAgencyById(Integer agencyId) {
		LOGGER.info("Executing fetchFundingAgencyById  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			FundingAgency fundingAgencyData = masterDao.fetchFundingAgencyById(agencyId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("fundingAgencyData", fundingAgencyData);
		} catch (Exception e) {
			LOGGER.error("Exception in fetchFundingAgencyById  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchAgecyByState(int stateId) {
		LOGGER.info("Executing fetchAgecyByState  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<Agency> agencies = masterDao.fetchAgenciesByState(stateId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put(MastersKeys.AGENCY.getDescription(), agencies);
			response.put("totalSize", agencies.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchAgecyByState  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	/*
	 * (non-Javadoc)fetch location master list.
	 * 
	 * @ Ramsagar
	 * 
	 * @see org.sagarmala.service.MasterService#fetchLocationMasterslist(org.
	 * sagarmala.bean.CommonMasterBean)
	 */
	@Override
	public ServiceResponse fetchLocationMasterslist(CommonMasterBean commonMasterBean) {
		LOGGER.info("Executing fetchLocationMasterslist  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		List<LocationMaster> locationMasterListData = null;
		try {
			locationMasterListData = masterDao.fetchLocationMasterByStateIdOrLocationName(commonMasterBean);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("locationListData", locationMasterListData);
			response.put("totalSize", locationMasterListData.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchLocationMasterslist  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	public ServiceResponse saveOrUpdateLocationMaster(CommonMasterBean commonMasterBean) {
		LOGGER.error("saveOrUpdateLocationMaster method Execution Started of MasterServiceImpl class");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus customReponseStatus = null;
		LocationMaster locationMaster = new LocationMaster();

		try {
			boolean success = false;
			if (commonMasterBean != null) {
				if (commonMasterBean.getMasterId() == 0) {
					List<LocationMaster> locationMasterByName = masterDao
							.fetchLocationMasterByName(commonMasterBean.getMasterName().trim());

					if (locationMasterByName == null || locationMasterByName.size() == 0) {
						locationMaster = BeanEntityConverssion.setcommonMasterBeanToLocationMaster(locationMaster,
								commonMasterBean);
						locationMaster.setCreatedDate(CommonUtility.getCurrentDateTime());
						locationMaster.setUpdatedDate(CommonUtility.getCurrentDateTime());
						locationMaster.setIsActive(ActiveDactiveStatus.ACTIVE.getId());
						success = masterDao.saveOrUpdateLocationMaster(locationMaster);
						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				} else {
					List<LocationMaster> locationMasterByName = null;
					try {
						locationMasterByName = masterDao
								.fetchLocationMasterByName(commonMasterBean.getMasterName().trim());
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (locationMasterByName == null || locationMasterByName.size() == 0) {
						locationMaster = masterDao.fetchLocationMasterById(commonMasterBean.getMasterId());
						locationMaster = BeanEntityConverssion.setcommonMasterBeanToLocationMaster(locationMaster,
								commonMasterBean);
						locationMaster.setUpdatedDate(CommonUtility.getCurrentDateTime());
						success = masterDao.saveOrUpdateLocationMaster(locationMaster);

						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				}
			} else {
				customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
						Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
						Status.FAILED.getResponseDescription());
				response.put("customResponse", customReponseStatus);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in saveOrUpdateLocationMaster  method of MasterServiceImpl", e);
			customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
					Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
					Status.FAILED.getResponseDescription());
			response.put("customResponse", customReponseStatus);
		}
		response.put("custumresponse", customReponseStatus);
		serviceResponse.setServiceResponse(response);
		LOGGER.error("saveOrUpdateLocationMaster method Execution completed of MasterServiceImpl class");
		return serviceResponse;
	}

	/*
	 * (non-Javadoc) Fetch Location By Id
	 * 
	 * @Author: ramsagar
	 * 
	 * @see
	 * org.sagarmala.service.MasterService#fetchLocationMasterById(java.lang.
	 * Integer)
	 */
	@Override
	public ServiceResponse fetchLocationMasterById(Integer locationId) {
		LOGGER.info("Executing fetchLocationMasterById  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			LocationMaster locationMasterData = masterDao.fetchLocationMasterById(locationId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("locationMasterData", locationMasterData);
		} catch (Exception e) {
			LOGGER.error("Exception in fetchLocationMasterById  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchLocationByState(int stateId) {
		LOGGER.info("Executing fetchLocationByState  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<LocationMaster> location = masterDao.fetchLocationByState(stateId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("locList", location);
			response.put("totalSize", location.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchLocationByState  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
		
	}
	
	/*
	 * For save and update organization master 
	 * @Author: Ramsagar
	 */
	public ServiceResponse saveOrUpdateorganizationMaster(CommonMasterBean commonMasterBean) {
		LOGGER.error("saveOrUpdateorganizationMaster method Execution Started of MasterServiceImpl class");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus customReponseStatus = null;
		OgatizationMaster ogatizationMaster = new OgatizationMaster();
		int maxValue = 0;
		String masterType = "";
		String masterTemp = "";
		try {
			boolean success = false;
			if (commonMasterBean != null) {
				if (commonMasterBean.getMasterId() == 0) {
					List<OgatizationMaster> ogatizationMasterByName = masterDao.fetchOgatizationMasterByName(commonMasterBean.getMasterName().trim());
					String organizationName[] = commonMasterBean.getMasterName().split(" ");

					if (ogatizationMasterByName == null || ogatizationMasterByName.size() == 0) {
						for (int i = 0; i < organizationName.length; i++) {
							masterTemp = masterTemp + organizationName[i].substring(0, 1);
						}
						List<OgatizationMaster> ogatizationMastersByTypeCode = masterDao.fetchOrganizationByTypeCode(masterTemp.toUpperCase());
						if (ogatizationMastersByTypeCode == null || ogatizationMastersByTypeCode.size() == 0) {
							masterType = masterType + masterTemp;
						} else {
							masterType = masterType + masterTemp
									+ organizationName[organizationName.length - 1].substring(1, 2);
						}
						maxValue = masterDao.fetchMaxOrganization();
						ogatizationMaster = BeanEntityConverssion.setcommonMasterBeanToOrganizationMasters(ogatizationMaster,
								commonMasterBean);
						ogatizationMaster.setMasterUniquecode(maxValue + 1);
						ogatizationMaster.setMasterCode(masterType.toUpperCase());
						ogatizationMaster.setCreatedDate(CommonUtility.getCurrentDateTime());
						ogatizationMaster.setUpdatedDate(CommonUtility.getCurrentDateTime());
						ogatizationMaster.setIsActive(ActiveDactiveStatus.ACTIVE.getId());
						success = masterDao.saveOrUpdateOrganizationMasters(ogatizationMaster);

						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				} else {
					List<OgatizationMaster> ogatizationMasterByName=null;
					try{
						ogatizationMasterByName = masterDao.fetchOgatizationMasterByName(commonMasterBean.getMasterName().trim());
					}catch (Exception e) {
						LOGGER.info(e.toString());
					}
					if (ogatizationMasterByName == null || ogatizationMasterByName.size() == 0) {
						ogatizationMaster = masterDao.fetchOrganizationById(commonMasterBean.getMasterId());
						ogatizationMaster =BeanEntityConverssion.setcommonMasterBeanToOrganizationMasters(ogatizationMaster,commonMasterBean);
						ogatizationMaster.setUpdatedDate(CommonUtility.getCurrentDateTime());
						success = masterDao.saveOrUpdateOrganizationMasters(ogatizationMaster);

						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				}
			} else {
				customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
						Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
						Status.FAILED.getResponseDescription());
				response.put("customResponse", customReponseStatus);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception occurred in saveOrUpdateorganizationMaster method of MasterServiceImpl", ex);
			customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
					Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
					Status.FAILED.getResponseDescription());
			response.put("customResponse", customReponseStatus);
		}
		serviceResponse.setServiceResponse(response);
		LOGGER.error("saveOrUpdateorganizationMaster method Execution completed of MasterServiceImpl class");
		return serviceResponse;
	}
	/**
	 * @developer: Abhishek Tyagi
	 * @createdOn: 19/03/2019
	 * @purpose: Call the dao for fetching master organisation.
	 * @return: ServiceResponse
	 */
	public ServiceResponse fetchOrganisationMasterslist(CommonMasterBean commonMasterBean) {
		LOGGER.info("Executing fetchOrganisationMasterslist  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		List<OgatizationMaster> organisationListData = null;
		try {
			organisationListData = masterDao.fetchOrganisationMaster(commonMasterBean);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("organisationMasterListData", organisationListData);
			response.put("totalSize", organisationListData.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchOrganisationMasterslist  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}
	/**
	 * @developer: Abhishek Tyagi
	 * @createdOn: 20/03/2019
	 * @purpose: Call the dao for fetching master organisation for specific organisationId.
	 * @return: ServiceResponse
	 */
	public ServiceResponse fetchOrganisationMasterById(Integer locationId) {
		LOGGER.info("Executing fetchOrganisationById  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			OgatizationMaster ogatizationMaster = masterDao.fetchOrganizationById(locationId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("organisationData", ogatizationMaster);
			LOGGER.info("--Result -->" + response.toString());
		} catch (Exception e) {
			e.printStackTrace();
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}
	
	/*
	 * For save and update DirectorateOfPorts master. 
	 * @Author: Md Rashid Alam(UTL0997).
	 */
	public ServiceResponse saveOrUpdateDirectorateOfPorts(CommonMasterBean commonMasterBean) {
		LOGGER.error("saveOrUpdateDirectorateOfPorts method Execution Started of MasterServiceImpl class");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus customReponseStatus = null;
		DirectorateOfPortsMaster directorateOfPortsMaster=new DirectorateOfPortsMaster();
		int maxValue = 0;
		String masterType = "";
		String masterTemp = "";
		try {
			boolean success = false;
			if (commonMasterBean != null) {
				if (commonMasterBean.getMasterId() == 0) {
					//fetch directorateOfPortMasterByName from DirectorateOfPortsMaster.
					List<DirectorateOfPortsMaster> directorateOfPortMasterByName = masterDao.fetchDirectorateOfPortsByName(commonMasterBean.getMasterName().trim());
					//split the masterName of commonMasterBean between two words from space.
					String directorateOfPortName[] = commonMasterBean.getMasterName().split(" ");
           
					if (directorateOfPortMasterByName == null || directorateOfPortMasterByName.size() == 0) {
						for (int i = 0; i < directorateOfPortName.length; i++) {
							masterTemp = masterTemp + directorateOfPortName[i].substring(0, 1);
						}
						//fetch directorateOfPortMasterByTypeCode from DirectorateOfPortsMaster.
						List<DirectorateOfPortsMaster> directorateOfPortMasterByTypeCode = masterDao.fetchDirectorateOfPortsByTypeCode(masterTemp.toUpperCase());
						if (directorateOfPortMasterByTypeCode == null || directorateOfPortMasterByTypeCode.size() == 0) {
							masterType = masterType + masterTemp;
						} else {
							masterType = masterType + masterTemp
									+ directorateOfPortName[directorateOfPortName.length - 1].substring(1, 2);
						}
						maxValue = masterDao.fetchMaxDirectorateOfPorts();
						directorateOfPortsMaster = BeanEntityConverssion.setcommonMasterBeanToDirectorateOfPortsMaster(directorateOfPortsMaster,
								commonMasterBean);
						directorateOfPortsMaster.setMasterUniqueCode(maxValue+1);
						directorateOfPortsMaster.setMasterCode(masterType.toUpperCase());
						directorateOfPortsMaster.setCreatedDate(CommonUtility.getCurrentDateTime());
						directorateOfPortsMaster.setUpdatedDate(CommonUtility.getCurrentDateTime());
						directorateOfPortsMaster.setIsActive(ActiveDactiveStatus.ACTIVE.getId());
						success = masterDao.saveOrUpdateDirectorateOfPorts(directorateOfPortsMaster);

						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
							response.put("message","Record Saved Successfully.");
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				} else {
					List<DirectorateOfPortsMaster> directorateOfPortsMastersByName=null;
					try{
						directorateOfPortsMastersByName = masterDao.fetchDirectorateOfPortsByName(commonMasterBean.getMasterName().trim());
					}catch (Exception e) {
						LOGGER.info(e.toString());
					}
					if (directorateOfPortsMastersByName == null || directorateOfPortsMastersByName.size() == 0) {
						directorateOfPortsMaster = masterDao.fetchDirectorateOfPortsById(commonMasterBean.getMasterId());
						directorateOfPortsMaster =BeanEntityConverssion.setcommonMasterBeanToDirectorateOfPortsMaster(directorateOfPortsMaster,commonMasterBean);
						directorateOfPortsMaster.setUpdatedDate(CommonUtility.getCurrentDateTime());
						success = masterDao.saveOrUpdateDirectorateOfPorts(directorateOfPortsMaster);

						if (success) {
							customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
									Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
									Status.SUCCESS.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						} else {
							customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
									Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
									Status.FAILED.getResponseDescription());
							response.put("customResponse", customReponseStatus);
						}
					} else {
						customReponseStatus = new CustomReponseStatus(Status.ALREADY_NAME_EXIST.getResponseId(),
								Status.ALREADY_NAME_EXIST.getResponseCode(),
								Status.ALREADY_NAME_EXIST.getResponseMessage(),
								Status.ALREADY_NAME_EXIST.getResponseDescription());
						response.put("customResponse", customReponseStatus);
					}
				}
			} else {
				customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
						Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
						Status.FAILED.getResponseDescription());
				response.put("customResponse", customReponseStatus);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception occurred in saveOrUpdateDirectorateOfPorts method of MasterServiceImpl", ex);
			customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
					Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
					Status.FAILED.getResponseDescription());
			response.put("customResponse", customReponseStatus);
		}
		serviceResponse.setServiceResponse(response);
		LOGGER.error("saveOrUpdateDirectorateOfPorts method Execution completed of MasterServiceImpl class");
		return serviceResponse;
	}
	/**
	 * @developer: Md Rashid Alam.
	 * @createdOn: 22/03/2019
	 * @purpose: Call the dao for fetching master fetchDirectorateOfPortsMasterslist.
	 * @return: ServiceResponse
	 */
	@Override
	public ServiceResponse fetchDirectorateOfPortsMasterslist(CommonMasterBean commonMasterBean) {
		LOGGER.info("Executing fetchDirectorateOfPortsMasterslist  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		List<DirectorateOfPortsMaster> directorateOfPortsMasters = null;
		try {
			directorateOfPortsMasters = masterDao.fetchDirectorateOfPortsByStateIdOrDirectorateOfPortsName(commonMasterBean);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("directorateOfPortsMastersListData", directorateOfPortsMasters);
			response.put("totalSize", directorateOfPortsMasters.size());
		} catch (Exception e) {
			LOGGER.error("Exception in fetchDirectorateOfPortsMasterslist  method of MasterServiceImpl", e);
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}
	/**
	 * @developer: Md Rashid Alam.
	 * @createdOn: 25/03/2019
	 * @purpose: Call the dao for fetching master fetchDirectorateOfPortsId.
	 * @return: ServiceResponse
	 */
	@Override
	public ServiceResponse fetchDirectorateOfPortsId(Integer masterId) {
		LOGGER.info("Executing fetchDirectorateOfPortsId  method of MasterServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			DirectorateOfPortsMaster directorateOfPortsMaster = masterDao.fetchDirectorateOfPortsById(masterId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("directorateOfPortsData", directorateOfPortsMaster);

			LOGGER.info("--Result -->" + response.toString());
		} catch (Exception e) {
			e.printStackTrace();
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;

	}
}
