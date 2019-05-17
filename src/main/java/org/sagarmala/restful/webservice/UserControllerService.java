package org.sagarmala.restful.webservice;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.sagarmala.bean.RoleAuthorizationBean;
import org.sagarmala.bean.SearchRoleBean;
import org.sagarmala.bean.SearchUserBean;
import org.sagarmala.bean.UserBean;
import org.sagarmala.dto.CustomReponseStatus;
import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.enumtype.constants.Status;
import org.sagarmala.model.Agency;
import org.sagarmala.model.LoginTrail;
import org.sagarmala.model.RoleAuthorization;
import org.sagarmala.model.UserDetail;
import org.sagarmala.service.IEmailService;
import org.sagarmala.service.OtpService;
import org.sagarmala.service.SmsService;
import org.sagarmala.service.UserService;
import org.sagarmala.utility.CommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping("/api/user")
public class UserControllerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerService.class);

	@Autowired
	private UserService userService;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private DefaultTokenServices tokenServices;

	@Autowired
	public OtpService otpService;

	@Autowired
	public SmsService smsService;

	@Autowired
	public IEmailService emailService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/userData", method = RequestMethod.GET)
	public UserDetail getUserData(HttpServletRequest request) {
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
				return userService.findByUserName(jsonObject.getString("user_name"));
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@RequestMapping(value = "/logout/{loginId}", method = RequestMethod.GET)
	public ServiceResponse logout(@PathVariable(name = "loginId") int loginId, HttpServletRequest request) {
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null) {
			try {
				LOGGER.info("access " + authHeader.replace("Bearer", "").trim());
				String tokenValue = authHeader.replace("Bearer", "").trim();
				OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
				LOGGER.info(accessToken.getAdditionalInformation() + "Access token :- " + accessToken.toString());
				tokenServices.readAccessToken(tokenValue);
				LoginTrail trail = new LoginTrail();
				trail.setLoginId(loginId);
				trail.setIslogIn(0);
				userService.loginTrail(trail);
				LOGGER.info("******** :- " + tokenServices.getClientId(tokenValue));
				tokenServices.revokeToken(accessToken.toString());
				tokenServices.revokeToken(tokenValue);
				if (accessToken.getRefreshToken() != null) {
					tokenStore.removeRefreshToken(accessToken.getRefreshToken());
				}
				tokenStore.removeAccessToken(accessToken);
				responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
						Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
						Status.SUCCESS.getResponseDescription());
				response.put("customResponse", responseStatus);
			} catch (Exception e) {
				responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
						Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
				response.put("customResponse", responseStatus);
			}
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	/*
	 * Sending Link at Forget Password - Ramsagar
	 */
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public ServiceResponse forgetPassword(@RequestBody UserBean userBean) {
		LOGGER.info("Executing forgetPassword Password ... " + userBean.getUserName());
		UserDetail userDetail = CommonUtility.convertUserBean(userBean);
		return userService.forgetPassword(userDetail);
	}

	/*
	 * Verify Token Vailid or Not - Ramsagar
	 */
	@RequestMapping(value = "/verifyToken", method = RequestMethod.POST)
	public ServiceResponse verifyTokenByUsername(@RequestBody UserBean userBean) {
		LOGGER.info("Executing verifyTokenByUsername ... " + userBean.getUserName());
		return userService.verifyTokenByUsername(userBean);
	}

	/*
	 * Resetting Password - Ramsagar
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ServiceResponse resetPassword(@RequestBody UserBean userBean) {
		LOGGER.info("Executing resetPassword Password ... " + userBean.getUserName());
		return userService.resetPassword(userBean);
	}

	/*
	 * For fetching org-role-mapping
	 */
	@RequestMapping(value = "/rolelist/{searchRole}", method = RequestMethod.GET)
	public ServiceResponse fetchRole(@PathVariable String searchRole) {
		LOGGER.info("Executing resetPassword Password ... " + searchRole);
		Gson gsonObject = new Gson();
		Type type = new TypeToken<SearchRoleBean>() {
		}.getType();
		SearchRoleBean role = gsonObject.fromJson(searchRole, type);
		LOGGER.info("Getting All Role  ");
		return userService.fetchRole(role);
	}

	@RequestMapping(value = "/userlist/{searchUser}", method = RequestMethod.GET)
	public ServiceResponse fetchUser(@PathVariable String searchUser) {
		System.err.println("entering............");
		LOGGER.info("Executing resetPassword Password ... " + searchUser);
		Gson gsonObject = new Gson();
		Type type = new TypeToken<SearchUserBean>() {
		}.getType();
		SearchUserBean searchUserBean = gsonObject.fromJson(searchUser, type);
		LOGGER.info("Getting All User  ");
		return userService.fetchUser(searchUserBean);
	}

	@RequestMapping(value = "/userlist/search", method = RequestMethod.POST)
	public ServiceResponse fetchUserSearch(@RequestBody SearchUserBean searchBean) {
		LOGGER.info("Executing fetchUserSearch... " + searchBean);
		LOGGER.info("Getting All User  ");
		return userService.fetchUser(searchBean);
	}

	@RequestMapping(value = "/saveUserDetails", method = RequestMethod.POST)
	public ServiceResponse registerUser(@RequestBody UserDetail user) {
		System.out.println("--User Data ----> " + user.toString());
		return userService.saveUserDetail(user);
	}

	/*
	 * For role authorization by ramsagar
	 */
	@RequestMapping(value = "/createRoleAuthorization", method = RequestMethod.POST)
	public ServiceResponse roleAuthorization(@RequestBody List<RoleAuthorizationBean> roleAuthorizationBean)
			throws JsonProcessingException {
		System.out.println("edce" + new ObjectMapper().writeValueAsString(roleAuthorizationBean));
		LOGGER.info("Executing roleAuthorization ..of UserControllerService");
		List<RoleAuthorization> roleAuthorization = CommonUtility.convertRoleAuthorizationBean(roleAuthorizationBean);
		return userService.roleAuthorization(roleAuthorization);
	}

	/*
	 * For fetching role authorization by ramsagar
	 */
	@RequestMapping(value = "/fetchRoleAuthorization", method = RequestMethod.GET)
	public ServiceResponse fetchRoleAuthorization() {
		LOGGER.info("Executing fetchRoleAuthorization of UserControllerService");
		return userService.fetchRoleAuthorization();
	}

	@RequestMapping(value = "/activeorDeactiveUserDetail", method = RequestMethod.POST)
	public ServiceResponse activeOrDeactiveUser(@RequestBody UserBean user) {
		return userService.activeOrDeactiveUser(user);
	}

	/*
	 * For changing password...By Ramsagar
	 */
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ServiceResponse changePassword(@RequestBody UserBean userBean) {
		LOGGER.info("Executing changePassword Password ... " + userBean.getUserName());
		return userService.changePassword(userBean);
	}

	/*
	 * when username forgot...By Ramsagar
	 */
	@RequestMapping(value = "/sendUserNameAtForgetLoginId", method = RequestMethod.POST)
	public ServiceResponse sendUserNameAtForgetLoginId(@RequestBody UserBean userBean) {
		LOGGER.info("Executing sendUserNameAtForgetLoginId Password ... " + userBean.getUserName());
		return userService.sendUserNameAtForgetLoginId(userBean);
	}

	@RequestMapping(value = "/generateLoginId/{userData}", method = RequestMethod.GET)
	public ServiceResponse generateLoginId(@PathVariable String userData) {
		LOGGER.info("Executing resetPassword Password ... " + userData);
		Gson gsonObject = new Gson();
		Type type = new TypeToken<SearchUserBean>() {
		}.getType();
		SearchUserBean searchUserBean = gsonObject.fromJson(userData, type);
		LOGGER.info("Getting LoginId ");
		return userService.generateLoginId(searchUserBean);
	}

	@RequestMapping(value = "/fetchRoleAuthorizationByRoleId/{roleId}", method = RequestMethod.GET)
	public ServiceResponse fetchRoleAuthorizationByRoleId(@PathVariable long roleId) {
		LOGGER.info("Executing fetchRoleAuthorizationByRoleId ... " + roleId);
		return userService.fetchRoleAuthorizationByRoleId(roleId);
	}

	@RequestMapping(value = "/downloadUserExcelFile/{searchUser}", method = RequestMethod.GET)
	public ServiceResponse downdloadUserExcelFile(@PathVariable String searchUser) {
		LOGGER.info("Executing downdloadUserExcelFile .. " + searchUser);
		Gson gsonObject = new Gson();
		Type type = new TypeToken<SearchUserBean>() {
		}.getType();
		SearchUserBean searchUserBean = gsonObject.fromJson(searchUser, type);
		LOGGER.info("Getting All User  ");
		return userService.downdloadUserExcelFile(searchUserBean);
	}

	@RequestMapping(value = "/downloadUserPdfFile/{searchUser}", method = RequestMethod.GET)
	public ServiceResponse downdloadUserPdfFile(@PathVariable String searchUser) {
		LOGGER.info("Executing downdloadUserPdfFile .. " + searchUser);
		Gson gsonObject = new Gson();
		Type type = new TypeToken<SearchUserBean>() {
		}.getType();
		SearchUserBean searchUserBean = gsonObject.fromJson(searchUser, type);
		LOGGER.info("Getting All User  ");
		return userService.downdloadUserPdfFile(searchUserBean);
	}

	@RequestMapping(value = "/generateOtp/{type}/{param}", method = RequestMethod.GET)
	public String generateOtp(@PathVariable("type") String type, @PathVariable("param") String param) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		int otp = otpService.generateOTP(username);

		logger.info("OTP : " + otp);
		logger.info("UserName" + username);
		if (type.equalsIgnoreCase("mail")) {

			String message = "<h1 align='center'>OTP FOR SAGARMALA MIS USER UPDATE</h1>"
					+ "<h3 align='center'>Please use the following otp on the screen</h3>" + "<div align ='center'>"
					+ otp + "</div>";
			emailService.sendVerificationMail(param, null, null, "OTP", "" + message);
		} else if (type.equalsIgnoreCase("phone")) {

			smsService.sendOTP(param, "Please use this OTP:" + String.valueOf(otp));
		}

		Map<String, String> replacements = new HashMap<String, String>();
		replacements.put("user", username);
		replacements.put("otpnum", String.valueOf(otp));

		// String message = template.getTemplate(replacements);

		return String.valueOf(otp);
	}

	@RequestMapping(value = "/validateOtp/{otpnum}", method = RequestMethod.GET)
	public @ResponseBody String validateOtp(@PathVariable("otpnum") int otpnum) {

		final String SUCCESS = "SUCCESS";

		final String FAIL = "FAILED";

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		logger.info(" Otp Number : " + otpnum);

		// Validate the Otp
		if (otpnum >= 0) {
			int serverOtp = otpService.getOtp(username);

			if (serverOtp > 0) {
				if (otpnum == serverOtp) {
					otpService.clearOTP(username);
					return SUCCESS;
				} else {
					return FAIL;
				}
			} else {
				return FAIL;
			}
		} else {
			return FAIL;
		}
	}

	@RequestMapping(value = "/findauthdata/{roleId}", method = RequestMethod.GET)
	public String editAssignDetails(@PathVariable Long roleId) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(userService.findroleAuthByRoleId(roleId));

	}

	/*
	 * reset password by super admin
	 */
	@RequestMapping(value = "/resetPasswordBySuperAdmin", method = RequestMethod.POST)
	public ServiceResponse resetPasswordBySuperAdmin(@RequestBody UserBean userBean) {
		LOGGER.info("Executing resetPasswordBySuperAdmin  ... " + userBean.getUserName());
		return userService.resetPasswordBySuperAdmin(userBean);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ServiceResponse forgetPassword(@RequestBody Agency agency) {
		LOGGER.info("Executing forgetPassword Password ... ");
		// UserDetail userDetail = CommonUtility.convertUserBean(userBean);
		return userService.saveA(agency);
	}

	@RequestMapping(value = "/updateUserForAssignProject", method = RequestMethod.POST)
	public ServiceResponse updateUserForAssignProject(@RequestBody UserDetail user) {
		System.err.println("--User Data ----> " + user.toString());
		return userService.updateUserForAssignProj(user);
	}

	@RequestMapping(value = "/viewAssignProject/{userName}", method = RequestMethod.GET)
	public UserDetail viewUserDetail(@PathVariable String userName) {
		// model.addAttribute("userDetail",
		// userService.findByUserName(userName));
		UserDetail userDetail = userService.findByUserName(userName);
		return userDetail;
	}
}
