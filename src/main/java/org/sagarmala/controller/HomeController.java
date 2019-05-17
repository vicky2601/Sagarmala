package org.sagarmala.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.sagarmala.bean.AddConceptBean;
import org.sagarmala.bean.UserBean;
import org.sagarmala.dto.CustomReponseStatus;
import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.restful.webservice.UserControllerService;
import org.sagarmala.service.ConceptService;
import org.sagarmala.service.IOrgRoleMappingService;
import org.sagarmala.service.MasterService;
import org.sagarmala.service.ProjectService;
import org.sagarmala.service.UserService;
import org.sagarmala.utility.CommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class HomeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	UserService userService;

	@Autowired
	private MasterService masterService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private IOrgRoleMappingService iOrgRoleMappingService;
	
	@Autowired
	private ConceptService conceptService;
	private UserControllerService userControllerService;
	@Autowired
	private TokenStore tokenStore;

	
	
	@ModelAttribute("master")
	public ServiceResponse fetchAllMasters() {
		System.out.println("Executing fetchAllMasters of MasterControllerService");
		return masterService.fetchCommonMasters();
	}

	@ModelAttribute("project")
	public ServiceResponse fetchAllProjects() {
		System.out.println("Executing fetchAllProjects of HomeController");
		return projectService.fetchProject(0);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "welcome";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		System.out.println("logutn");
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
	public String loginSuccessfully() {
		return "home";
	}

	@RequestMapping(value = "/forget/forget", method = RequestMethod.GET)
	public String forget() {
		return "web.forget";
	}

	@RequestMapping(value = "/reset-password/{token}/{username}", method = RequestMethod.GET)
	public String addUser(@PathVariable(name = "token") String token,
			@PathVariable(name = "username") String username) {
		UserBean userBean = new UserBean();
		userBean.setToken(token);
		userBean.setUserName(username);
		LinkedHashMap<Object, Object> custom = null;
		ServiceResponse response = userService.verifyTokenByUsername(userBean);
		custom = response.getServiceResponse();
		CustomReponseStatus customReponseStatus = (CustomReponseStatus) custom.get("customResponse");
		if (customReponseStatus.getResponseCode() == 200) {
			return "web.reset";
		} else {
			return "welcome";
		}
	}

	@RequestMapping(value = "/role-list", method = RequestMethod.GET)
	public String fetchroleList() {
		return "web.rolelist";
	}

	@RequestMapping(value = "/usermanagement", method = RequestMethod.GET)
	public String userManagement() {
		return "web.usermanagement";
	}

	@RequestMapping(value = "/thankyou", method = RequestMethod.GET)
	public String thankYou() {
		return "web.thankyou";
	}

	@RequestMapping(value = "/user-list", method = RequestMethod.GET)
	public String fetchuserList() {
		return "web.userlist";
	}

	@RequestMapping(value = "/user-action/{action}/{userName}", method = RequestMethod.GET)
	public String editUserDetail(@PathVariable String action, @PathVariable String userName, Model model) {
		model.addAttribute("userDetail", userService.findByUserName(userName));
		if (action.equalsIgnoreCase("view"))
			return "web.viewuser";
		else if (action.equalsIgnoreCase("reset"))
			return "web.resetpassword";
		else if (action.equalsIgnoreCase("editAssignProject"))
			return "web.assignProject";
		else if (action.equalsIgnoreCase("viewAssignProject"))
			return "web.assignProject";
		else if (action.equalsIgnoreCase("edit")) {
			return "web.edituser";
		} else {
			return "web.edituser";
		}

	}

	@RequestMapping(value = "edit_user_profile", method = RequestMethod.GET)
	public String editUserProfile(HttpServletRequest request, Model m) {
		String ipAddress = request.getRemoteAddr();
		System.out.println("ipaddress for user Login :- " + ipAddress);
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
			try {
				LOGGER.info("comingg... User Detail");
				String tokenValue = authHeader.replace("Bearer", "").trim();
				OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
				LOGGER.info(accessToken.getAdditionalInformation() + "Access token :- " + accessToken.toString());
				JSONObject jsonObject = new JSONObject(accessToken.getAdditionalInformation());
				LOGGER.info("Find By User Details ", userService.findByUserName(jsonObject.getString("user_name")));
				m.addAttribute("userDetails", userService.findByUserName(jsonObject.getString("user_name")));
			} catch (Exception e) {
				e.printStackTrace();
				// return null;
			}
		}
		// m.addAttribute("userDetails",userService.findByUserName(username) )
		return "web.edituserprofile";
	}

	/*
	 * @RequestMapping(value = "/edit-role/{roleId}", method = RequestMethod.GET)
	 * public String editRoleDetail(@PathVariable Long roleId, Model model) {
	 * model.addAttribute("roleDescription", iRoleService.findById(roleId)); return
	 * "web.editrole"; }
	 */
	@RequestMapping(value = "/redirectToAddRole", method = RequestMethod.GET)
	public String redirectToAddRole() {
		return "web.addrole";
	}

	@RequestMapping(value = "/role-authorise-list", method = RequestMethod.GET)
	public String fetchRoleAuthoriseList(Model model) {
		return "web.roleauthoriselist";
	}

	@RequestMapping(value = "/edit-assign/{OrgRoleMappingId}", method = RequestMethod.GET)
	public String editAssignDetail(@PathVariable Long OrgRoleMappingId, Model model) {
		// model.addAttribute("roleDescription",iRoleService.findById(OrgRoleMappingId));
		model.addAttribute("roleDescription",
				iOrgRoleMappingService.findRoleByOrgRoleId((int) (long) OrgRoleMappingId));
		model.addAttribute("roleAuthDescription", userService.findroleAuthByRoleId(OrgRoleMappingId));
		return "web.editassign";
	}

	@RequestMapping(value = "/changePassword/{attempt}", method = RequestMethod.GET)
	public String changePassword(@PathVariable int attempt) {
		System.err.println("change password===================");
		if (attempt == 1) {
			return "web.changepassword.first";
		} else {
			return "web.changepassword";
		}
	}

	@RequestMapping(value = "/forget/forgetUsername", method = RequestMethod.GET)
	public String forgetUsername() {
		System.err.println("forgetUsername==================");
		return "web.forgetUsername";
	}

	@RequestMapping(value = "/blank-list", method = RequestMethod.GET)
	public String displayPage() {
		return "web.blankPage";
	}

	@RequestMapping(value = "/adduserdynamically", method = RequestMethod.GET)
	public String displayAddUserDynamicPage() {
		return "web.adduserdynamic";
	}

	@RequestMapping(value = "/addconcept", method = RequestMethod.GET)
	public String addConceptPage(Model model) {
		return "web.addconcept";
	}

	@RequestMapping(value = "/addconceptdetails/{id}/{projecttype}/{agencies}", method = RequestMethod.GET)
	public String addConceptDetailsPage(@PathVariable("id") Integer id, @PathVariable("projecttype") String projectType,
			@PathVariable("agencies") String agencies, Model model) throws JsonProcessingException, IllegalAccessException, InvocationTargetException, ParseException {
		LOGGER.info("ID :-"+id);
		LOGGER.info("ProjectType :-"+projectType);
		LOGGER.info("agencies :-"+agencies);
		String generated = "C";
		generated += String.format("%02d", 000) + id;
		AddConceptBean addConcept = new  AddConceptBean();
		addConcept.setId(id);
		addConcept.setConceptId(generated);
		addConcept.setProjectType(projectType);
		addConcept.setIdFundingAgencies(agencies);
		//addConcept.setCreatedBy(aCreatedBy);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		addConcept.setCreatedBy(username);
		LocalDate ldObj = LocalDate.of(1600, 01, 1);
		addConcept.setCreatedDate(ldObj);
		addConcept.setUpdatedDate(ldObj);
		addConcept.setStartDate(ldObj);
		addConcept.setEndDate(ldObj);
		conceptService.saveConcept(addConcept);
		LOGGER.info("generated :-"+generated);
		boolean contains = agencies.matches(".*\\b,\\b.*");
		LOGGER.info("contains :-"+contains);
		List<String> agenciesList = new ArrayList<String>();
		if (contains) {
			String[] arr = agencies.split(",");
			for (int i = 0; i < arr.length; i++) {
				agenciesList.add(arr[i]);
			}

		} else {
			agenciesList.add(agencies);
		}
		LOGGER.info("agenciesList :-"+agenciesList);
//
		//ServiceResponse fetchNodalByOrganisationId = conceptService.fetchNodalByOrganisationId(36);
	ObjectMapper mapper=new ObjectMapper();
	
	
	//	model.addAttribute("nodalOfficerName", mapper.writeValueAsString(conceptService.fetchNodalByOrganisationId(36)));
	model.addAttribute("nodalOfficerName",conceptService.fetchNodalByOrganisationId(36));
	

	
	System.out.println("NSO"+mapper.writeValueAsString(conceptService.fetchNodalByOrganisationId(36)));
		model.addAttribute("id", id);
		model.addAttribute("generatedconceptid", generated);
		model.addAttribute("projecttype", projectType);
		model.addAttribute("agencieslist", agenciesList);

		return "web.addconceptdetails";
	}
	

	@RequestMapping(value="/conceptlist",method=RequestMethod.GET)
	public String conceptDetailsList(Model m)  {
	
		return "web.concept.conceptlist";
	}

	@RequestMapping(value = "/lineministry-list", method = RequestMethod.GET)
	public String lineMinistryList() {
		return "web.master.lineministry";
	}

	@RequestMapping(value = "/agency-list", method = RequestMethod.GET)
	public String agencyList() {
		return "web.master.agency";
	}

	@RequestMapping(value = "/department-list", method = RequestMethod.GET)
	public String departmentList() {
		return "web.master.department";
	}

	/*
	 * for update department master.
	 */
	@RequestMapping(value = "/edit-department/{departmentId}", method = RequestMethod.GET)
	public String editDepartment(@PathVariable Integer departmentId, Model model) {
		model.addAttribute("department", masterService.fetchDepartmenyById(departmentId));
		return "web.edit.department";
	}

	@RequestMapping(value = "/assignedProjectList", method = RequestMethod.GET)
	public String assignedProjectList() {
		return "web.assignedProjectList";
	}

	@RequestMapping(value = "/assignProject", method = RequestMethod.GET)
	public String assignProject() {
		return "web.assignProject";
	}

	/*
	 * for update agency master.
	 */
	@RequestMapping(value = "/edit-agency/{agencyId}", method = RequestMethod.GET)
	public String editAgency(@PathVariable Integer agencyId, Model model) {
		model.addAttribute("agency", masterService.fetchAgencyById(agencyId));
		return "web.edit.agency";
	}

	/*
	 * for update line-ministry master.
	 */
	@RequestMapping(value = "/edit-lineministry/{lineministry}", method = RequestMethod.GET)
	public String editLineMinistry(@PathVariable Integer lineministry, Model model) throws JsonProcessingException {
		model.addAttribute("lineministry", masterService.fetchLineMinistryById(lineministry));
		System.out.println("TOTAL RESPONSE IS........"
				+ new ObjectMapper().writeValueAsString(masterService.fetchLineMinistryById(lineministry)));
		return "web.edit.lineministry";
	}

	/*
	 * for goto funding agency master page.
	 */
	@RequestMapping(value = "/fundingAgency-list", method = RequestMethod.GET)
	public String fundingAgencyList() {
		return "web.master.fundingagency";
	}

	/*
	 * for update funding agency master.
	 */
	@RequestMapping(value = "/edit-funding-agency/{agencyId}", method = RequestMethod.GET)
	public String editFundingAgency(@PathVariable Integer agencyId, Model model) {
		model.addAttribute("fundingagency", masterService.fetchFundingAgencyById(agencyId));
		return "web.master.editfundingagency";
	}

	@RequestMapping(value = "/dummy", method = RequestMethod.GET)
	public String dummy(Model model) {
		// model.addAttribute("result", empService.findAll());
		return "dummy";
	}
	
	@RequestMapping(value = "/reset/user/password/{token}/{username}", method = RequestMethod.GET)
	public String resetUserPassword(@PathVariable(name = "token") String token,
			@PathVariable(name = "username") String username, Model model) {
		System.out.println("Ramsagar :"+ username);
		model.addAttribute("username",username);
		UserBean userBean = new UserBean();
		userBean.setToken(token);
		userBean.setUserName(username);
		LinkedHashMap<Object, Object> custom = null;
		ServiceResponse response = userService.verifyTokenByUsername(userBean);
		custom = response.getServiceResponse();
		CustomReponseStatus customReponseStatus = (CustomReponseStatus) custom.get("customResponse");
		if (customReponseStatus.getResponseCode() == 200) {
			return "web.change.password";
		} else {
			return "welcome";
		}
	}
	
	/*
	 * for goto location master page.
	 */
	@RequestMapping(value = "/locationMaster-list", method = RequestMethod.GET)
	public String locationMaster() {
		return "web.master.locationmaster";
	}
	/*
	 * for update funding agency master.
	 */
	@RequestMapping(value = "/edit-location-master/{locationId}", method = RequestMethod.GET)
	public String editLocationMaster(@PathVariable Integer locationId, Model model) {
		model.addAttribute("locationmaster", masterService.fetchLocationMasterById(locationId));
		return "web.edit.locationmaster";
	}
	@RequestMapping(value="/edit-concept/{conceptId}",method=RequestMethod.GET)
	public String editConceptDetails(@PathVariable String conceptId,Model m) {
		LOGGER.info("Id :-> ",conceptId);
		m.addAttribute("concept", conceptService.fetchConceptById(conceptId));
		return "web.edit.conceptdetails";
	}
	
	/*
	 * for goto oganization master page.
	 */
	@RequestMapping(value = "/organizationMaster-list", method = RequestMethod.GET)
	public String organizationMaster() {
		return "web.master.organization";
	}
	
	/*
	 * for goto role master page.
	 */
	@RequestMapping(value = "/roleMaster-list", method = RequestMethod.GET)
	public String roleMaster() {
		return "web.master.role";
	}
	
	/*
	 * for goto port type master page.
	 */
	@RequestMapping(value = "/portTypeMaster-list", method = RequestMethod.GET)
	public String portTypeMaster() {
		return "web.master.porttype";
	}
	
	/*
	 * for goto port master page.
	 */
	@RequestMapping(value = "/portMaster-list", method = RequestMethod.GET)
	public String portMaster() {
		return "web.master.port";
	}
	
	/*
	 * for goto maritime board master page.
	 */
	@RequestMapping(value = "/maritimeBoard-list", method = RequestMethod.GET)
	public String maritimeBoard() {
		return "web.master.maritimeboard";
	}
	
	/*
	 * for goto directorate of ports master page.
	 */
	@RequestMapping(value = "/directorateOfPorts-list", method = RequestMethod.GET)
	public String directorateOfPorts() {
		return "web.master.directorateofports";
	}
	

	/*
	 * for goto SPV Name master page.
	 */
	@RequestMapping(value = "/spvName-list", method = RequestMethod.GET)
	public String spvName() {
		return "web.master.dspvname";
	}
	
	@RequestMapping(value = "/resetpwd", method = RequestMethod.GET)
	public String resetPwd() {
		return "web.reset.pwd";
	}
}
