package org.sagarmala.restful.webservice;

import java.lang.reflect.Type;

import org.sagarmala.bean.CommonMasterBean;
import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.service.MasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/api/master")
public class MasterControllerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MasterControllerService.class);

	@Autowired
	private MasterService masterService;

	/*
	 * for fetching common masters by ramsagar.
	 */
	@RequestMapping(value = "/commonMasterslist", method = RequestMethod.GET)
	public ServiceResponse fetchCommonMasters() {
		LOGGER.info("Executing fetchCommonMasters of MasterControllerService");
		return masterService.fetchCommonMasters();
	}

	/*
	 * for fetching Sub Department
	 */
	@RequestMapping(value = "/fetchDepartmentByState/{stateId}", method = RequestMethod.GET)
	public ServiceResponse fetchSubDepartmentByState(@PathVariable Integer stateId) {
		LOGGER.info("Executing fetchSubDepartmentByState of MasterControllerService");
		return masterService.fetchSubDepartmentByState(stateId);
	}

	/*
	 * for fetching Maritime Board
	 */
	@RequestMapping(value = "/fetchMaritimeBoardByState/{stateId}", method = RequestMethod.GET)
	public ServiceResponse fetchMaritimeBoardByState(@PathVariable Integer stateId) {
		LOGGER.info("Executing fetchMaritimeBoardByState of MasterControllerService");
		return masterService.fetchMaritimeBoardByState(stateId);
	}

	/*
	 * for fetching role by organisation
	 */
	@RequestMapping(value = "/fetchRoleByOrganisation/{organisationId}/{roleId}", method = RequestMethod.GET)
	public ServiceResponse fetchRoleByDepartment(@PathVariable Integer organisationId, @PathVariable Integer roleId) {
		LOGGER.info("Executing fetchRoleByDepartment of MasterControllerService");
		return masterService.fetchRoleByOrganisation(organisationId, roleId);
	}

	/*
	 * for fetching Major Port
	 */
	@RequestMapping(value = "/fetchPortByState/{stateId}", method = RequestMethod.GET)
	public ServiceResponse fetchPortByState(@PathVariable Integer stateId) {
		LOGGER.info("Executing fetchPortByState of MasterControllerService");
		return masterService.fetchPortByState(stateId);
	}

	@RequestMapping(value = "/menu-list", method = RequestMethod.GET)
	public ServiceResponse fetchmenuList() {
		return masterService.fetchparentAndChildMenu();
	}

	/*
	 * for fetching Maritime Board
	 */
	@RequestMapping(value = "/fetchAllPortsAndMBoardAndDep", method = RequestMethod.GET)
	public ServiceResponse fetchAllPortsAndMBoardAndDep() {
		LOGGER.info("Executing fetchAllPortsAndMBoardAndDep of MasterControllerService");
		return masterService.fetchAllPortsAndMBoardAndDepartments();
	}

	/*
	 * for fetching agency masters by Id Author : ramsagar.
	 */
	@RequestMapping(value = "/fetchAgencyById/{agencyId}", method = RequestMethod.GET)
	public ServiceResponse fetchAgencyById(@PathVariable Integer agencyId) {
		LOGGER.info("Executing fetchAgencyById of MasterControllerService");
		return masterService.fetchAgencyById(agencyId);
	}

	/*
	 * Save Or Update Masters Author : ramsagar.
	 */
	@RequestMapping(value = "/createOrUpdateMasters", method = RequestMethod.POST, produces = "application/json")
	public ServiceResponse createOrUpdateMasters(@RequestBody CommonMasterBean commonMasterBean,Model model) {

		LOGGER.info("Executing createOrUpdateMasters Method MasterControllerService... ");
		System.out.println(commonMasterBean);
		ServiceResponse serviceResponse = null;
		if (commonMasterBean != null) {
			serviceResponse = masterService.saveOrUpdateMasters(commonMasterBean);
			//model.addAttribute("message", "record saved successfully.");
		}
		return serviceResponse;
	}

	
	@RequestMapping(value = "/fetchMinnorPortsByState/{stateId}", method = RequestMethod.GET)
	public ServiceResponse fetchMinnorPortByState(@PathVariable Integer stateId) {
		LOGGER.info("Executing fetchMinnorPortByState of MasterControllerService");
		return masterService.fetchMinnorPortsByState(stateId);
	}
	
	/*
	 * for fetching fetchAgencyByState  by Id Author : ck.
	 */
	@RequestMapping(value = "/fetchAgencyByState/{stateId}", method = RequestMethod.GET)
	public ServiceResponse fetchAgencyByState(@PathVariable Integer stateId) {
		LOGGER.info("Executing fetchAgencyByState of MasterControllerService");
		return masterService.fetchAgecyByState(stateId);
	}
	
	/*
	 * for fetching deprtment masters by Id Author : ramsagar.
	 */
	@RequestMapping(value = "/fetchDepartmenyById/{departmentId}", method = RequestMethod.GET)
	public ServiceResponse fetchDepartmenyById(@PathVariable Integer departmentId) {
		LOGGER.info("Executing fetchDepartmenyById of MasterControllerService");
		return masterService.fetchDepartmenyById(departmentId);
	}

	/*
	 * for fetching department masters list with search Author : ramsagar.
	 */
	@RequestMapping(value = "/fetchDepartmentMasterslist/{searchMasterBean}", method = RequestMethod.GET)
	public ServiceResponse fetchDepartmentMasterslist(@PathVariable String searchMasterBean) {
		LOGGER.info("Executing fetchDepartmentMasterslist of MasterControllerService");
		Gson gsonObject = new Gson();
		Type type = new TypeToken<CommonMasterBean>() {
		}.getType();
		CommonMasterBean commonMasterBean = gsonObject.fromJson(searchMasterBean, type);

		return masterService.fetchDepartmentMasterslist(commonMasterBean);
	}

	/*
	 * for fetching agency masters list with search Author : ramsagar.
	 */
	@RequestMapping(value = "/fetchAgencyMasterslist/{searchMasterBean}", method = RequestMethod.GET)
	public ServiceResponse fetchAgencyMasterslist(@PathVariable String searchMasterBean) {
		LOGGER.info("Executing fetchAgencyMasterslist of MasterControllerService");
		Gson gsonObject = new Gson();
		Type type = new TypeToken<CommonMasterBean>() {
		}.getType();
		CommonMasterBean commonMasterBean = gsonObject.fromJson(searchMasterBean, type);

		return masterService.fetchAgencyMasterslist(commonMasterBean);
	}
	
	/*
	 * for fetching common masters list with search Author : ramsagar.
	 */
	@RequestMapping(value = "/fetchCommonMasterslist/{searchMasterBean}", method = RequestMethod.GET)
	public ServiceResponse fetchCommonMasterslist(@PathVariable String searchMasterBean) {
		LOGGER.info("Executing fetchCommonMasterslist of MasterControllerService");
		Gson gsonObject = new Gson();
		Type type = new TypeToken<CommonMasterBean>() {
		}.getType();
		CommonMasterBean commonMasterBean = gsonObject.fromJson(searchMasterBean, type);

		return masterService.fetchCommonMasterslist(commonMasterBean);
	}
	
	/*
	 * for fetching funding agency masters list with search Author : ramsagar.
	 */
	@RequestMapping(value = "/fetchFundingAgencyMasterslist/{searchMasterBean}", method = RequestMethod.GET)
	public ServiceResponse fetchFundingAgencyMasterslist(@PathVariable String searchMasterBean) {
		LOGGER.info("Executing fetchFundingAgencyMasterslist of MasterControllerService");
		Gson gsonObject = new Gson();
		Type type = new TypeToken<CommonMasterBean>() {
		}.getType();
		CommonMasterBean commonMasterBean = gsonObject.fromJson(searchMasterBean, type);

		return masterService.fetchFundingAgencyMasterslist(commonMasterBean);
	}
	
	/*
	 * for fetching location master list with search Author : ramsagar.
	 * 25/02/2019
	 */
	@RequestMapping(value = "/fetchLocationMasterslist/{searchMasterBean}", method = RequestMethod.GET)
	public ServiceResponse fetchLocationMasterslist(@PathVariable String searchMasterBean) {
		LOGGER.info("Executing fetchLocationMasterslist of MasterControllerService");
		Gson gsonObject = new Gson();
		Type type = new TypeToken<CommonMasterBean>() {
		}.getType();
		CommonMasterBean commonMasterBean = gsonObject.fromJson(searchMasterBean, type);

		return masterService.fetchLocationMasterslist(commonMasterBean);
	}
	/*
	 * for fetching Major Port
	 */
	@RequestMapping(value = "/fetchLocationByState/{stateId}", method = RequestMethod.GET)
	public ServiceResponse fetchLocationByState(@PathVariable Integer stateId) {
		System.out.println(stateId);
		LOGGER.info("Executing fetchPortByState of MasterControllerService");
		return masterService.fetchLocationByState(stateId);
	}
	
	/**
	 * @developer: Abhishek Tyagi
	 * @createdOn: 19/03/2019
	 * @purpose: Call the service method for fetching organisation.
	 * @return: ServiceResponse
	 */
	@RequestMapping(value = "/fetchOrganisationMasterslist/{searchMasterBean}", method = RequestMethod.GET)
	public ServiceResponse fetchOrganisationMasterslist(@PathVariable String searchMasterBean) {
		LOGGER.info("Executing fetchLocationMasterslist of MasterControllerService");
		Gson gsonObject = new Gson();
		Type type = new TypeToken<CommonMasterBean>() {
		}.getType();
		CommonMasterBean commonMasterBean = gsonObject.fromJson(searchMasterBean, type);

		return masterService.fetchOrganisationMasterslist(commonMasterBean);
	}
	
	/* @developer: Md Rashid Alam
	 * @createdOn: 19/03/2019
	 * @purpose: for fetching DirectorateOfPort masters list with search..
	 * @return: ServiceResponse
	 */
	@RequestMapping(value = "/fetchDirectorateOfPortsMasterslist/{searchMasterBean}", method = RequestMethod.GET)
	public ServiceResponse fetchDirectorateOfPortsMasterslist(@PathVariable String searchMasterBean) {
		LOGGER.info("Executing fetchAgencyMasterslist of MasterControllerService");
		LOGGER.info("Search Master Bean => "+searchMasterBean);
		Gson gsonObject = new Gson();
		Type type = new TypeToken<CommonMasterBean>() {
		}.getType();
		CommonMasterBean commonMasterBean = gsonObject.fromJson(searchMasterBean, type);
		LOGGER.info("Common Master Bean => "+commonMasterBean);
		return masterService.fetchDirectorateOfPortsMasterslist(commonMasterBean);
		
	}
	/**
	 * @developer: Abhishek Tyagi
	 * @createdOn: 25/03/2019
	 * @purpose: Call the service method for fetch specific organisation.
	 * @return: ServiceResponse
	 */
	@RequestMapping(value = "/edit-organisation-master/{organisationId}", method = RequestMethod.GET)
	public ServiceResponse fetchSingleOrganisationMaster(@PathVariable Integer organisationId) {
		LOGGER.info("Executing fetchSingleLocationMaster of MasterControllerService");
		return masterService.fetchOrganisationMasterById(organisationId);
	}
	/**
	 * @developer: Md Rashid Alam
	 * @createdOn: 25/03/2019
	 * @purpose: Call the service method for fetch specific directorateOfPort.
	 * @return: ServiceResponse
	 */
	@RequestMapping(value = "/edit-directorateOfPort-master/{directorateportId}", method = RequestMethod.GET)
	public ServiceResponse editDirectoratePortMaster(@PathVariable Integer directorateportId, Model model) {
		System.out.println("-----"+directorateportId+"---------");
		ServiceResponse fetchDirectorateOfPortsId = masterService.fetchDirectorateOfPortsId(directorateportId);
		return fetchDirectorateOfPortsId;
	}
}
