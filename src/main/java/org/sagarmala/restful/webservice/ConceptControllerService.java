package org.sagarmala.restful.webservice;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

import org.sagarmala.bean.AddConceptBean;
import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.service.ConceptService;
import org.sagarmala.service.UserService;
import org.sagarmala.utility.FileUitility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;

@RestController
@RequestMapping("/api/concept")
public class ConceptControllerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConceptControllerService.class);
	@Autowired
	ConceptService conceptService;

	@Autowired
	private UserService userService;
	@Autowired
	private Environment environment;
	
	@Autowired
	private FileUitility fileUitility;
	@Autowired
	private UserControllerService userControllerService;

	@ApiOperation(value = "This form will let you add concept rlated data", response = ServiceResponse.class)
	@RequestMapping(value = "/saveconcept", method = RequestMethod.POST)
	public Integer saveConcept(@RequestBody AddConceptBean addConceptBean)
			throws IllegalAccessException, InvocationTargetException, ParseException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		addConceptBean.setCreatedBy(username);
		addConceptBean.setCreatedDate(LocalDate.now());
		return conceptService.saveConcept(addConceptBean);
	}

	@ApiOperation(value = "This form will let you update concept-details  related data", response = ServiceResponse.class)
	@RequestMapping(value = "/updateconceptwithconceptdetails", method = RequestMethod.POST)
	public void updateConceptWithConceptDetails(@RequestBody AddConceptBean addConceptBean) {
		LOGGER.info("Update Concept:- "+addConceptBean);		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		LOGGER.info("username :-",username);
		addConceptBean.setUpdatedBy(username);
		addConceptBean.setUpdatedDate(LocalDate.now());
		conceptService.updateConceptDetails(addConceptBean);

	}
	@ApiOperation(value = "This method is used for uploading file on a server", response = ServiceResponse.class)
	@RequestMapping(value = "/uploaduserdoctoserver", method = RequestMethod.POST)
	public String uploadUserDocToServer(@RequestParam("file") MultipartFile file) throws IOException {
		String path=File.separator+"resources"+File.separator+"concept_docs"+File.separator;
		return fileUitility.convertMultiPartFileToFileAndUploadToServer(file, path);
		}
	
	@RequestMapping("/conceptlist")
	public ServiceResponse fetchConceptList(Model m) throws IllegalAccessException, InvocationTargetException {
		return conceptService.fetchConceptDetails();
	}
	
	/*
	 * @Changing by ramsagar-13-03-2019
	 * fetch concept list with search keys.
	 */
	/*@RequestMapping(value = "/conceptlist/{searchBean}", method = RequestMethod.GET)
	public ServiceResponse fetchConceptList(@PathVariable String searchBean) {
		LOGGER.info("Executing fetchConceptList of ConceptControllerService");
		Gson gsonObject = new Gson();
		Type type = new TypeToken<SearchConceptBean>() {
		}.getType();
		SearchConceptBean searchConceptBean = gsonObject.fromJson(searchBean, type);

		return conceptService.fetchConceptDetails(searchConceptBean);
	}*/
	
	/*
	 * for fetching nodal list by organisation id.
	 * @Author: ramsagar
	 */
	@RequestMapping(value = "/fetchNodalByOrganisationId/{organisationId}", method = RequestMethod.GET)
	public ServiceResponse fetchNodalByOrganisationId(@PathVariable Integer organisationId) {
		System.out.println("OrganisationId :- "+organisationId);
		LOGGER.info("Executing fetchNodalByOrganisationId of ConceptControllerService");
		return conceptService.fetchNodalByOrganisationId(organisationId);
	}
	/**
	 *  for change status.
	 * @Author Md Rashid Alam
	 */
	@RequestMapping(value="/updatestatusAndRemarkById",method=RequestMethod.POST)
	public void updateStatusAndRemarkByConceptId(@RequestBody AddConceptBean addConceptBean) {
		System.out.println("Approved Status");
		addConceptBean.setUpdatedDate(LocalDate.now());
		conceptService.updateStatusAndRemarkById(addConceptBean);
	}
	/*@RequestMapping(value="/downloadAttachedFile/{filePath}",method=RequestMethod.POST)
	public void downloadFile(@PathVariable String filePath) {
		System.out.println("Download file");
		
	}*/

}
