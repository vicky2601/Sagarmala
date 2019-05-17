package org.sagarmala.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import javax.mail.internet.MimeMessage;
import org.apache.commons.beanutils.BeanUtils;
import org.sagarmala.bean.RoleAuthorizationBean;
import org.sagarmala.bean.SearchRoleBean;
import org.sagarmala.bean.SearchUserBean;
import org.sagarmala.bean.UserBean;
import org.sagarmala.configuration.UserOauthException;
import org.sagarmala.dao.MasterDao;
import org.sagarmala.dao.UserDao;
import org.sagarmala.dto.CustomReponseStatus;
import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.enumtype.constants.Status;
import org.sagarmala.model.Agency;
import org.sagarmala.model.CommonMasters;
import org.sagarmala.model.DynamicAddUserMenuDTO;
import org.sagarmala.model.DynamicAddUserMenuEntity;
import org.sagarmala.model.LoginTrail;
import org.sagarmala.model.MaritimeBoard;
import org.sagarmala.model.MinorPortMaster;
import org.sagarmala.model.OgatizationMaster;
import org.sagarmala.model.OrgRoleMapping;
import org.sagarmala.model.PORT;
import org.sagarmala.model.RoleAuthorization;
import org.sagarmala.model.StateMaster;
import org.sagarmala.model.UserDetail;
import org.sagarmala.service.IEmailService;
import org.sagarmala.service.SmsService;
import org.sagarmala.service.UserService;
import org.sagarmala.utility.CommonUtility;
import org.sagarmala.utility.CommonValidation;
import org.sagarmala.utility.UserNameMasking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Value("${verify.token.email}")
	private String verifyEmailURL;

	@Autowired
	private IEmailService emailServiceImpl;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private Environment environment;
	@Autowired
	private SmsService smsService;

	@Autowired
	private MasterDao masterDao;

	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			LOGGER.info("Username :- " + username);
			UserDetail user = userDao.findByUserName(username);
			LOGGER.info(user.toString());
			if (user == null) {
				throw new UserOauthException("Invalid username or password.");
			} else if (user.getIsActive() == 0) {
				throw new UserOauthException("Deactivate Users");
			}
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					getAuthority());
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserOauthException("Invalid username or password");
		}
	}

	@SuppressWarnings("rawtypes")
	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public UserDetail findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public int loginTrail(LoginTrail loginTrail) {
		return userDao.loginTrail(loginTrail);
	}

	@Override
	public void saveUser(UserDetail userDetail) {
		userDao.saveUser(userDetail);
	}

	/*
	 * Sending Link at Forget Password - Ramsagar
	 * 
	 * @see
	 * org.sagarmala.service.UserService#forgetPassword(org.sagarmala.model.
	 * UserDetail)
	 */
	@Override
	public ServiceResponse forgetPassword(UserDetail user) {
		LOGGER.info("Executing forgetPassword method of UserServiceImpl");
		CustomReponseStatus customReponseStatus = null;
		LinkedHashMap<Object, Object> response = new LinkedHashMap<Object, Object>();
		ServiceResponse serviceResponse = new ServiceResponse();
		UserDetail userDetail = null;

		if (user.getUserName() != null && !user.getUserName().isEmpty()) {
			userDetail = userDao.findByUserName(user.getUserName());
		}
		if (userDetail != null) {
			String emailId = userDetail.getEmail();
			String username = userDetail.getUserName();
			/*
			 * Generate random 36-character string token for reset password
			 */
			String token = UUID.randomUUID().toString();
			userDetail.setUserName(user.getUserName());
			userDetail.setResetToken(token);
			userDetail.setUpdatedDate(CommonUtility.getCurrentDateTime());
			userDao.saveUser(userDetail);

			try {
				if (emailId != null && !emailId.isEmpty()) {

					String url = verifyEmailURL+"reset-password"+"/"+token +"/"+username;
					String Link = "<a href=\"" + url + "\">Link</a>";

					String verificationLink = "<html> <head> <title> </title> </head> <table> <tr> <p> <span style=\"text-align: left;\">Dear Sir / Madam,</span> </p> </tr> <tr> <p> <span style=\"text-align: left;\">You have recently requested to reset your password for your sagar mala account. </span> </p> </tr>   <tr> <p> <span style=\"text-align: left;\">Click on the link below to reset your password. This link is only valid for <span>3 days</span>. <br /><a href=\""
							+ url
							+ "\">Link</a> </span> </p> </tr> <tr> <p> <span style=\"text-align: left;\">In case you face any difficulty, please contact your state helpline number/email.</span> </p> </tr> <tr> <p> <span style=\"text-align: left;\">This is auto generated email. Please do not reply to this.</span> </p> </tr>  <tr> <p> <span style=\"text-align: left;\">Thank You,<br />SagarMala Team </span> </p> </tr> </table>";

					String to = emailId;
					String cc = null;
					String bcc = null;
					String subject = "Sagar Mala – Password Reset";
					String body = verificationLink;

					MimeMessage mailMessage = emailServiceImpl.sendVerificationMail(to, cc, bcc, subject, body);
					emailServiceImpl.sendEmail(mailMessage);
					LOGGER.info("Email Send Successfully ###############");
					customReponseStatus = new CustomReponseStatus(Status.EMAIL_SEND_SUCCESSFULLY.getResponseId(),
							Status.EMAIL_SEND_SUCCESSFULLY.getResponseCode(),
							Status.EMAIL_SEND_SUCCESSFULLY.getResponseMessage(),
							Status.EMAIL_SEND_SUCCESSFULLY.getResponseDescription());
				} else {
					customReponseStatus = new CustomReponseStatus(Status.INVALID_EMAIL_ID.getResponseId(),
							Status.INVALID_EMAIL_ID.getResponseCode(), Status.INVALID_EMAIL_ID.getResponseMessage(),
							Status.INVALID_EMAIL_ID.getResponseDescription());
				}
			} catch (Exception ex) {
				LOGGER.error("Exception occurred in sending email" + ex);
				customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
						Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
						Status.FAILED.getResponseDescription());
			}
		} else {
			LOGGER.info("User does not exist.." + user.getUserName());
			customReponseStatus = new CustomReponseStatus(Status.USER_DOES_NOT_EXIST.getResponseId(),
					Status.USER_DOES_NOT_EXIST.getResponseCode(), Status.USER_DOES_NOT_EXIST.getResponseMessage(),
					Status.USER_DOES_NOT_EXIST.getResponseDescription());
		}
		response.put("customResponse", customReponseStatus);
		serviceResponse.setServiceResponse(response);
		LOGGER.info("forgetPassword method execution completed #####");
		return serviceResponse;
	}

	/*
	 * Verify Token Vailid or Not - Ramsagar
	 * 
	 * @see
	 * org.sagarmala.service.UserService#verifyTokenByUsername(org.sagarmala.
	 * bean.UserBean)
	 */
	@Override
	public ServiceResponse verifyTokenByUsername(UserBean userBean) {
		LOGGER.info("Executing verifyTokenByUsername  method of UserServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> masterResponse = new LinkedHashMap<Object, Object>();
		CustomReponseStatus customReponseStatus = null;
		UserDetail userDetail = null;

		if (userBean.getUserName() != null) {
			userDetail = userDao.findByUserName(userBean.getUserName());
		}
		if (userDetail.getUserName() != null && userDetail.getResetToken()!=null) {
			if (userDetail.getResetToken().equals(userBean.getToken())
					&& userDetail.getUserName().equals(userBean.getUserName())) {

				customReponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
						Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
						Status.SUCCESS.getResponseDescription());
			} else {
				customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
						Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
						Status.FAILED.getResponseDescription());
			}
			masterResponse.put("customResponse", customReponseStatus);
			serviceResponse.setServiceResponse(masterResponse);
		} else {
			customReponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
					Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
					Status.FAILED.getResponseDescription());
			masterResponse.put("customResponse", customReponseStatus);
			serviceResponse.setServiceResponse(masterResponse);
		}
		LOGGER.info("verifyTokenByUsername method execution completed #####");
		return serviceResponse;
	}

	/*
	 * Resetting Password - Ramsagar
	 * 
	 * @see org.sagarmala.service.UserService#resetPassword(org.sagarmala.bean.
	 * UserBean)
	 */
	@Override
	public ServiceResponse resetPassword(UserBean userBean) {
		LOGGER.info("Executing resetPassword method of UserServiceImpl");
		CustomReponseStatus reponseStatus = null;
		LinkedHashMap<Object, Object> response = new LinkedHashMap<Object, Object>();
		ServiceResponse serviceResponse = new ServiceResponse();
		UserDetail userDetail = null;
		/*
		 * Checking User if exist then or not
		 */

		if (userBean.getUserName() != null && !userBean.getUserName().isEmpty() && userBean.getNewPassword() != null && !userBean.getNewPassword().isEmpty()) {
			userDetail = userDao.findByUserName(userBean.getUserName().trim());
		}

		if (userDetail != null) {
			System.out.println("Password0" + userBean.getNewPassword().trim());
			System.out.println("Password0" + userBean.getUserName().trim());
			String encryptedNewPassword = passwordEncoder.encode(userBean.getNewPassword().trim());

			userDetail.setPassword(encryptedNewPassword);
			userDetail.setUserName(userBean.getUserName());
			userDetail.setUpdatedDate(CommonUtility.getCurrentDateTime());
			try {
				userDao.saveUser(userDetail);
				LOGGER.info("User password reset successfully...");
				reponseStatus = new CustomReponseStatus(Status.USER_PASSWORD_CHANGED_SUCCESS.getResponseId(),
						Status.USER_PASSWORD_CHANGED_SUCCESS.getResponseCode(),
						Status.USER_PASSWORD_CHANGED_SUCCESS.getResponseMessage(),
						Status.USER_PASSWORD_CHANGED_SUCCESS.getResponseDescription());
			} catch (Exception ex) {
				LOGGER.error("Exception occured in resetPassword method of UserServiceImpl..." + ex);
				reponseStatus = new CustomReponseStatus(Status.USER_PASSWORD_CHANGED_FAILED.getResponseId(),
						Status.USER_PASSWORD_CHANGED_FAILED.getResponseCode(),
						Status.USER_PASSWORD_CHANGED_FAILED.getResponseMessage(),
						Status.USER_PASSWORD_CHANGED_FAILED.getResponseDescription());
			}
			response.put("customResponse", reponseStatus);
			serviceResponse.setServiceResponse(response);
		} else {
			reponseStatus = new CustomReponseStatus(Status.INVALID_USER_NAME_PASSWORD.getResponseId(),
					Status.INVALID_USER_NAME_PASSWORD.getResponseCode(),
					Status.INVALID_USER_NAME_PASSWORD.getResponseMessage(),
					Status.INVALID_USER_NAME_PASSWORD.getResponseDescription());
			response.put("customResponse", reponseStatus);
			serviceResponse.setServiceResponse(response);
		}
		LOGGER.info("resetPassword method execution completed #####");
		return serviceResponse;
	}

	@Override
	public ServiceResponse fetchRole(SearchRoleBean searchRoleBean) {
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<OrgRoleMapping> roles = userDao.fetchRole(searchRoleBean, response);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("totalSize", roles.size());
			response.put("AllData", roles);

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
	public ServiceResponse fetchUser(SearchUserBean searchUserBean) {
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<UserDetail> userDetails = userDao.fetchUser(searchUserBean, response);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("totalSize", userDetails.size());
			response.put("AllData", userDetails);

		} catch (Exception e) {
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
			e.printStackTrace();
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	/*
	 * (non-Javadoc) For role authorization by ramsagar
	 * 
	 * @see
	 * org.sagarmala.service.UserService#roleAuthorization(org.sagarmala.model.
	 * RoleAuthorization)
	 */
	@Override
	public ServiceResponse roleAuthorization(List<RoleAuthorization> roleAuthorization) {
		LOGGER.info("Executing roleAuthorization  method of UserServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;

		try {
			boolean flag = userDao.saveRoleAuthorization(roleAuthorization);

			if (flag) {
				responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(),
						Status.SUCCESS.getResponseCode(), Status.SUCCESS.getResponseMessage(),
						Status.SUCCESS.getResponseDescription());
				response.put("customResponse", responseStatus);

			} else {
				responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
						Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
				response.put("customResponse", responseStatus);
			}
		} catch (Exception e) {
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse saveUserDetail(UserDetail user) {
		List<Integer> minnorPortList=null;
		if(user.getMinorPorts()!=null && user.getMinorPorts().size()!=0){
			minnorPortList=new ArrayList<>();
			for (MinorPortMaster stock : user.getMinorPorts()) {
				minnorPortList.add(stock.getMasterId());
				
	        }
			List<MinorPortMaster> minnorPorts=masterDao.fetchMinnorPortsByIds(minnorPortList);
			Set<MinorPortMaster> minnorPortsSet=new HashSet<>(minnorPorts);
			user.setMinorPorts(minnorPortsSet);
		}
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		UserDetail detail = null;
		UserDetail email = null;
		UserDetail phone = null;
		String token = UUID.randomUUID().toString();
		try {
			//UserProfileUtility.convertUserBeanToEntity(user);
			if (user.getUserName() != null && !user.getUserName().isEmpty()) {
				try {
					detail = userDao.findByUserName(user.getUserName());
				} catch (Exception e) {
					LOGGER.info(e.getMessage());
				}
			}
			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
				try {
					email = userDao.findByEmail(user.getEmail());
				} catch (Exception e) {
					LOGGER.info(e.getMessage());
				}
				
			}
			if (user.getMobileNumber() != null && !user.getMobileNumber().isEmpty()) {
				try {
					phone = userDao.findByPhoneNumber(user.getMobileNumber());
				} catch (Exception e) {
					LOGGER.info(e.getMessage());
				}
				
			}
			System.out.println("User Name Validation :- " + CommonValidation.isAlphabetInput(user.getName().trim()));
			if (!CommonValidation.validateMobile(user.getMobileNumber())) {
				responseStatus = new CustomReponseStatus(Status.INVALID_MOBILE_NUMBER.getResponseId(),
						Status.INVALID_MOBILE_NUMBER.getResponseCode(),
						Status.INVALID_MOBILE_NUMBER.getResponseMessage(),
						Status.INVALID_MOBILE_NUMBER.getResponseDescription());
				response.put("customResponse", responseStatus);
				serviceResponse.setServiceResponse(response);
			} else if (!CommonValidation.validateEmail(user.getEmail())) {
				responseStatus = new CustomReponseStatus(Status.INVALID_EMAIL.getResponseId(),
						Status.INVALID_EMAIL.getResponseCode(), Status.INVALID_EMAIL.getResponseMessage(),
						Status.INVALID_EMAIL.getResponseDescription());
				response.put("customResponse", responseStatus);
				serviceResponse.setServiceResponse(response);
			} else if (!CommonValidation.isAlphabetInput(user.getName().trim())) {
				responseStatus = new CustomReponseStatus(Status.INVALID_NAME.getResponseId(),
						Status.INVALID_NAME.getResponseCode(), Status.INVALID_NAME.getResponseMessage(),
						Status.INVALID_NAME.getResponseDescription());
				response.put("customResponse", responseStatus);
				serviceResponse.setServiceResponse(response);
			} else if (detail != null && (detail.getUserId()!=user.getUserId())) {
				responseStatus = new CustomReponseStatus(Status.ALREADY_NAME.getResponseId(),
						Status.ALREADY_NAME.getResponseCode(), Status.ALREADY_NAME.getResponseMessage(),
						Status.ALREADY_NAME.getResponseDescription());
				response.put("customResponse", responseStatus);
				serviceResponse.setServiceResponse(response);
			} else if (email != null && email.getUserId()!=user.getUserId()) {
				responseStatus = new CustomReponseStatus(Status.ALREADY_EMAIL.getResponseId(),
						Status.ALREADY_EMAIL.getResponseCode(), Status.ALREADY_EMAIL.getResponseMessage(),
						Status.ALREADY_EMAIL.getResponseDescription());
				response.put("customResponse", responseStatus);
				serviceResponse.setServiceResponse(response);
			} else if (phone != null && phone.getUserId()!=user.getUserId()) {
				responseStatus = new CustomReponseStatus(Status.ALREADY_PHONE.getResponseId(),
						Status.ALREADY_PHONE.getResponseCode(), Status.ALREADY_PHONE.getResponseMessage(),
						Status.ALREADY_PHONE.getResponseDescription());
				response.put("customResponse", responseStatus);
				serviceResponse.setServiceResponse(response);
			} else {
				System.out.println(user.getUserId());
				if (user.getUserId() != 0) {
					detail = userDao.findByUserId(user.getUserId());
					user.setPassword(detail.getPassword());
					userDao.updateUserDeatils(user);
					responseStatus = new CustomReponseStatus(Status.USER_PROFILE_UPDATE_SUCCESS.getResponseId(),
							Status.USER_PROFILE_UPDATE_SUCCESS.getResponseCode(),
							Status.USER_PROFILE_UPDATE_SUCCESS.getResponseMessage(),
							Status.USER_PROFILE_UPDATE_SUCCESS.getResponseDescription());
					response.put("customResponse", responseStatus);
					serviceResponse.setServiceResponse(response);
				} else {
					/*
					 * user.setUserPassword(RandomStringUtils.randomAlphanumeric(6));
					 */
					int status = 0;
					user.setIsFirstAttempt(1);
					user.setIsActive(1);
					user.setPassword(passwordEncoder.encode(environment.getProperty("user.default.password")));
					user.setResetToken(token);
					SearchRoleBean searchRoleBean=new SearchRoleBean();
					searchRoleBean.setOrganisationId(user.getOrganisationId().getMasterId());
					searchRoleBean.setRoleId(Integer.valueOf(String.valueOf(user.getRole().getRoleId())));
					List<OrgRoleMapping> orgRoleMapping = userDao.fetchRole(searchRoleBean, null);
					
					/*for(int i=0;i<orgRoleMapping.size();i++){
						roles.add(orgRoleMapping.get(i).getRoleMaster());
					}*/
					if (orgRoleMapping.size() == 1)
						user.setOrgRoleMapping(orgRoleMapping.get(0));
					
					status = userDao.saveUserDetails(user);
					if(status>=1){
						String username=user.getUserName();
						
						//String url = verifyEmailURL+"/reset/user/password"+"/"+token +"/"+username;
						//reset-password
						String url = verifyEmailURL+"/"+token +"/"+username;
						String Link = "<a href=\"" + url + "\">Link</a>";
						String emailId=user.getEmail();
						String mobileNum=user.getMobileNumber();
						String verificationLink = "<html> <head> <title> </title> </head> <table> <tr> <p> <span style=\"text-align: left;\">Dear Sir / Madam,</span> </p> </tr> <tr> <p> <span style=\"text-align: left;\">You have recently requested to reset your password for your sagar mala account. </span> </p> </tr>   <tr> <p> <span style=\"text-align: left;\">Click on the link below to reset your password. This link is only valid for <span>3 days</span>. <br />"
								+ "<a href=\""+ url+"\">Link</a> </span> </p> </tr> <tr> <p> <span style=\"text-align: left;\">In case you face any difficulty, please contact your state helpline number/email.</span> </p> </tr> <tr> <p> <span style=\"text-align: left;\">This is auto generated email. Please do not reply to this.</span> </p> </tr>  <tr> <p> <span style=\"text-align: left;\">Thank You,<br />SagarMala Team </span> </p> </tr> </table>";
							

						String to = emailId;
						String cc = null;
						String bcc = null;
						String subject = "Sagar Mala – User Management";
						String body = verificationLink;
						try {
							MimeMessage mailMessage = emailServiceImpl.sendVerificationMail(to, cc, bcc, subject, body);
							LOGGER.info("Email Send Successfully ###############");
							emailServiceImpl.sendEmail(mailMessage);
							String sendOTP = smsService.sendOTP(mobileNum,"your otp is generated This is auto generated email. Please do not reply to this" );
							LOGGER.info("SMS Send Succesfully"+sendOTP);
							System.out.println("SMS Send Succesfully"+sendOTP);
						} catch (Exception ex) {
							LOGGER.info("Exception Occured while Email Sending  & SMS Sending ###############" + ex);
						}

					}
					LOGGER.info("Created User Status :- " + status);
					if (status != 0) {
						responseStatus = new CustomReponseStatus(Status.USER_CREATION_SUCCESS.getResponseId(),
								Status.USER_CREATION_SUCCESS.getResponseCode(),
								Status.USER_CREATION_SUCCESS.getResponseMessage(),
								Status.USER_CREATION_SUCCESS.getResponseDescription());
						response.put("customResponse", responseStatus);
						serviceResponse.setServiceResponse(response);

					} else {
						responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(),
								Status.FAILED.getResponseCode(), Status.FAILED.getResponseMessage(),
								Status.FAILED.getResponseDescription());
						response.put("customResponse", responseStatus);
						serviceResponse.setServiceResponse(response);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			responseStatus = new CustomReponseStatus(Status.USER_CREATION_FAILED.getResponseId(),
					Status.USER_CREATION_FAILED.getResponseCode(), Status.USER_CREATION_FAILED.getResponseMessage(),
					Status.USER_CREATION_FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
			serviceResponse.setServiceResponse(response);
			LOGGER.error(e.getMessage());
		}
		return serviceResponse;
	}

	/*
	 * (non-Javadoc) fetch role authorization
	 * 
	 * @see org.sagarmala.service.UserService#fetchRoleAuthorization()
	 */
	@Override
	public ServiceResponse fetchRoleAuthorization() {
		LOGGER.info("Executing fetchRoleAuthorization  method of UserServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<RoleAuthorization> roleAuthorization = userDao.fetchRoleAuthorization();
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("totalSize", roleAuthorization.size());
			response.put("CommonMastersList", roleAuthorization);

		} catch (Exception e) {
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public ServiceResponse activeOrDeactiveUser(UserBean userBean) {
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			System.out.println(userBean.getUserId());
			UserDetail userDetail = userDao.findByUserId(userBean.getUserId());
			userDetail.setIsActive(userBean.getIsActive());
			userDetail.setUpdatedBy(userBean.getUpdatedBy());
			userDao.updateUserDeatils(userDetail);
			responseStatus = new CustomReponseStatus(Status.CHANGED_STATUS_SUCCESS.getResponseId(),
					Status.CHANGED_STATUS_SUCCESS.getResponseCode(), Status.CHANGED_STATUS_SUCCESS.getResponseMessage(),
					Status.CHANGED_STATUS_SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			serviceResponse.setServiceResponse(response);

		} catch (Exception e) {
			e.printStackTrace();
			responseStatus = new CustomReponseStatus(Status.CHANGED_STATUS_FAILED.getResponseId(),
					Status.CHANGED_STATUS_FAILED.getResponseCode(), Status.CHANGED_STATUS_FAILED.getResponseMessage(),
					Status.CHANGED_STATUS_FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
			serviceResponse.setServiceResponse(response);
			LOGGER.error(e.getMessage());
		}
		return serviceResponse;
	}

	/*
	 * (non-Javadoc) For Changing Password BY Ramsagar
	 * 
	 * @see org.sagarmala.service.UserService#changePassword(org.sagarmala.bean.
	 * UserBean)
	 */
	@Override
	public ServiceResponse changePassword(UserBean userBean) {
		LOGGER.info("Executing changePassword method of UserServiceImpl");
		CustomReponseStatus reponseStatus = null;
		LinkedHashMap<Object, Object> response = new LinkedHashMap<Object, Object>();
		ServiceResponse serviceResponse = new ServiceResponse();

		String encryptedNewPassword = passwordEncoder.encode(userBean.getNewPassword());
		/*
		 * Checking User if exist then or not
		 */
		UserDetail userDetail = userDao.findByUserName(userBean.getUserName());
		if (userDetail != null) {

			if (passwordEncoder.matches(userBean.getOldPassword(), userDetail.getPassword())) {

				if (!userBean.getOldPassword().equals(userBean.getNewPassword())) {

					if (userBean.getConfirmPassword().equals(userBean.getNewPassword())) {

						userDetail.setUserName(userBean.getUserName());
						userDetail.setPassword(encryptedNewPassword);
						userDetail.setUpdatedDate(CommonUtility.getCurrentDateTime());
						userDetail.setIsFirstAttempt(0);
						try {
							userDao.saveUser(userDetail);

							LOGGER.info("User password changed successfully...");
							String phone = userDetail.getMobileNumber();
							String emailId = userDetail.getEmail();
							String maskedUser = UserNameMasking.getMaskUser(userBean.getUserName());

							String verificationLink = "<html> <head> <title> </title> </head> <table> <tr> <p> <span style=\"text-align: left;\">Dear Sir / Madam,</span> </p> </tr> <tr> <p> <span style=\"text-align: left;\">Your Sagar Mala password for account <span><b> "
									+ maskedUser + " </b></span> has been changed successfully on<span><b> "
									+ CommonUtility.getCurrentDateTime()
									+ " </b></span>If you have changed the password, please ignore the this email. </span> </p> </tr>  <tr> <p> <span style=\"text-align: left;\">If you have not changed the password, please click here <a href=\"#\">sagarMala.gov.in</a> to recover your account.</span> </p> </tr><tr> <p> <span style=\"text-align: left;\">This is auto generated email. Please do not reply to this.</span> </p> </tr>  <tr> <p> <span style=\"text-align: left;\">Thank You,<br/>SagarMala Team </span> </p> </tr> </table>";

							String to = emailId;
							String cc = null;
							String bcc = null;
							String subject = "Sagar Mala – Password Change";
							String body = verificationLink;
							try {
								MimeMessage mailMessage = emailServiceImpl.sendVerificationMail(to, cc, bcc, subject,
										body);
								LOGGER.info("Email Send Successfully ###############");
								emailServiceImpl.sendEmail(mailMessage);
							} catch (Exception ex) {
								LOGGER.info("Exception Occured while Email Sending  ###############" + ex);
							}
							try {
								String message = "Your password changed successfuly for user name :" + maskedUser;
								smsService.sendOTP(phone, message);
								LOGGER.info("SMS Send Successfully ###############");
							} catch (Exception ex) {
								LOGGER.info("Exception Occured while SMS Sending  ###############" + ex);
							}
							reponseStatus = new CustomReponseStatus(
									Status.USER_PASSWORD_CHANGED_SUCCESS.getResponseId(),
									Status.USER_PASSWORD_CHANGED_SUCCESS.getResponseCode(),
									Status.USER_PASSWORD_CHANGED_SUCCESS.getResponseMessage(),
									Status.USER_PASSWORD_CHANGED_SUCCESS.getResponseDescription());
						} catch (Exception ex) {
							LOGGER.error("Exception occured in changePassword method of UserServiceImpl..." + ex);
							reponseStatus = new CustomReponseStatus(Status.USER_PASSWORD_CHANGED_FAILED.getResponseId(),
									Status.USER_PASSWORD_CHANGED_FAILED.getResponseCode(),
									Status.USER_PASSWORD_CHANGED_FAILED.getResponseMessage(),
									Status.USER_PASSWORD_CHANGED_FAILED.getResponseDescription());
						}
					} else {
						reponseStatus = new CustomReponseStatus(Status.INVALID_NEW_PASSWORD.getResponseId(),
								Status.INVALID_NEW_PASSWORD.getResponseCode(),
								Status.INVALID_NEW_PASSWORD.getResponseMessage(),
								Status.INVALID_NEW_PASSWORD.getResponseDescription());
					}
				} else {
					reponseStatus = new CustomReponseStatus(Status.INVALID_NEW_PASSWORD.getResponseId(),
							Status.INVALID_NEW_PASSWORD.getResponseCode(),
							Status.INVALID_NEW_PASSWORD.getResponseMessage(),
							Status.INVALID_NEW_PASSWORD.getResponseDescription());
				}

			} else {
				reponseStatus = new CustomReponseStatus(Status.INVALID_NEW_PASSWORD.getResponseId(),
						Status.INVALID_NEW_PASSWORD.getResponseCode(), Status.INVALID_NEW_PASSWORD.getResponseMessage(),
						Status.INVALID_NEW_PASSWORD.getResponseDescription());
			}
		} else {
			LOGGER.error("Invalid User..." + userBean.getUserId());
			reponseStatus = new CustomReponseStatus(Status.INVALID_USER.getResponseId(),
					Status.INVALID_USER.getResponseCode(), Status.INVALID_USER.getResponseMessage(),
					Status.INVALID_USER.getResponseDescription());
		}
		response.put("customResponse", reponseStatus);
		serviceResponse.setServiceResponse(response);
		LOGGER.info("changePassword method execution completed #####");
		return serviceResponse;
	}

	/*
	 * (non-Javadoc) When user forgot username ... ramsagar
	 * 
	 * @see org.sagarmala.service.UserService#sendUserNameAtForgetLoginId(org.
	 * sagarmala.bean.UserBean)
	 */
	@Override
	public ServiceResponse sendUserNameAtForgetLoginId(UserBean userBean) {
		LOGGER.info("Executing sendUserNameAtForgetLoginId method of UserServiceImpl");
		CustomReponseStatus reponseStatus = null;
		LinkedHashMap<Object, Object> response = new LinkedHashMap<Object, Object>();
		ServiceResponse serviceResponse = new ServiceResponse();
		UserDetail userDetail = null;

		boolean isNumeric = userBean.getFogetLoginId().chars().allMatch(Character::isDigit);

		if (isNumeric) {

			try {
				userDetail = userDao.findByPhoneNumber(userBean.getFogetLoginId());

				String phone = userDetail.getMobileNumber();
				String username = userDetail.getUserName();

				try {
					String message = "Your user name is :" + username + "use for login our profile";
					smsService.sendOTP(phone, message);

					reponseStatus = new CustomReponseStatus(Status.SMS_SENT_SUCCESS.getResponseId(),
							Status.SMS_SENT_SUCCESS.getResponseCode(), Status.SMS_SENT_SUCCESS.getResponseMessage(),
							Status.SMS_SENT_SUCCESS.getResponseDescription());

				} catch (Exception ex) {
					LOGGER.info("Exception Occured while Sending SMS ###############" + ex);
					reponseStatus = new CustomReponseStatus(Status.INVALID_MOBILE_NUMBER.getResponseId(),
							Status.INVALID_MOBILE_NUMBER.getResponseCode(),
							Status.INVALID_MOBILE_NUMBER.getResponseMessage(),
							Status.INVALID_MOBILE_NUMBER.getResponseDescription());
				}

			} catch (Exception ex) {
				LOGGER.error("Exception occured in sendUserNameAtForgetLoginId() method of UserServiceImpl..." + ex);
				reponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
						Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			}
		} else if (!isNumeric) {
			try {
				userDetail = userDao.findByEmail(userBean.getFogetLoginId());

				String emailId = userDetail.getEmail();
				String username = userDetail.getUserName();

				String verificationLink = "<html> <head> <title> </title> </head> <table> <tr> <p> <span style=\"text-align: left;\">Dear Sir / Madam,</span> </p> </tr> <tr> <p> <span style=\"text-align: left;\">Your sagar mala login id Is<span><b> "
						+ username + " </b></span> use this for login our account <span><b> "
						+ CommonUtility.getCurrentDateTime()
						+ " </b></span> please ignore the this email. </span> </p> </tr>  <tr> <p> <span style=\"text-align: left;\"> please click here <a href=\"#\">sagarMala.gov.in</a> to login your account.</span> </p> </tr><tr> <p> <span style=\"text-align: left;\">This is auto generated email. Please do not reply to this.</span> </p> </tr>  <tr> <p> <span style=\"text-align: left;\">Thank You,<br/>SagarMala Team </span> </p> </tr> </table>";

				String to = emailId;
				String cc = null;
				String bcc = null;
				String subject = "Sagar Mala – Forgot UserName";
				String body = verificationLink;
				try {
					MimeMessage mailMessage = emailServiceImpl.sendVerificationMail(to, cc, bcc, subject, body);
					emailServiceImpl.sendEmail(mailMessage);
					LOGGER.info("Email Send Successfully ###############");

					reponseStatus = new CustomReponseStatus(Status.EMAIL_SEND_SUCCESSFULLY.getResponseId(),
							Status.EMAIL_SEND_SUCCESSFULLY.getResponseCode(),
							Status.EMAIL_SEND_SUCCESSFULLY.getResponseMessage(),
							Status.EMAIL_SEND_SUCCESSFULLY.getResponseDescription());

				} catch (Exception ex) {
					LOGGER.info("Exception Occured while sms Sending  ###############" + ex);
					reponseStatus = new CustomReponseStatus(Status.INVALID_EMAIL.getResponseId(),
							Status.INVALID_EMAIL.getResponseCode(), Status.INVALID_EMAIL.getResponseMessage(),
							Status.INVALID_EMAIL.getResponseDescription());
				}

			} catch (Exception ex) {
				LOGGER.error("Exception occured in sendUserNameAtForgetLoginId() method of UserServiceImpl..." + ex);
				reponseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
						Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			}
		}
		response.put("customResponse", reponseStatus);
		serviceResponse.setServiceResponse(response);
		LOGGER.info("sendUserNameAtForgetLoginId method execution completed #####");
		return serviceResponse;
	}

	@Override
	public ServiceResponse generateLoginId(SearchUserBean userBean) {
		LOGGER.info("Executing fetchRoleAuthorization  method of UserServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("loginid", genarateUserName(userBean));
		} catch (Exception e) {
			e.printStackTrace();
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	public String genarateUserName(SearchUserBean userBean) {
		LOGGER.info("Executing genarateUserName  method of UserServiceImpl");
		String username = "";
		System.err.println("b======"+userBean);
		
		UserDetail userDetail = userDao.findByUserByDepartementId(userBean);
		
		if (userDetail != null) {
			
			System.out.println("If -->");
			int logid = Integer.parseInt(userDetail.getUserName().substring(userDetail.getUserName().length() - 3));
			username = userDetail.getUserName().substring(0, userDetail.getUserName().length() - 3)
					+ String.format("%03d", ++logid);
			
			
		} else {
			System.out.println("else-->" + userBean.toString());
			// MOS & SDCL
			if (userBean.getOrganisationId() == 34 || userBean.getOrganisationId() == 35) {
				OgatizationMaster oganization = userDao.findOganizationByMasterId(userBean.getOrganisationId());
				username = oganization.getMasterCode() + "001";
			}
			// Line Ministry
			else if (userBean.getOrganisationId() == 36) {
				CommonMasters commonMasters = userDao.findDepartmentByMasterId(userBean.getLineMinistryId());
				username = commonMasters.getMasterTypeCode() + "001";

			}
			// Major Port Official
			else if (userBean.getOrganisationId() == 37) {
				PORT port = userDao.findPortByMasterId(userBean.getPortId());
				username = port.getPortCode() + "001";
			}
			// Maritime Board
			else if (userBean.getOrganisationId() == 38) {
				MaritimeBoard maritimeBoard = masterDao.fetchMaritimeBoardById(userBean.getMaritimeBoardId()); // Official
				username = maritimeBoard.getMaritimeCode() + "001";
			}
			// State official
			else if (userBean.getOrganisationId() == 39) {
				StateMaster statemaster = userDao.findStateByMasterId(userBean.getStateId());
				username = statemaster.getMasterCode() + "001";
			}
			// Implementation Agency User
			else if (userBean.getOrganisationId() == 40) {
				Agency agency = userDao.findAgencyByMasterId(userBean.getAgencyId());
				username = agency.getMasterCode() + "001";
			}
			
			else if (userBean.getOrganisationId() == 41) {
				MinorPortMaster minnorPort = userDao.findMinnerPortMasterByMasterId(userBean.getMinnorPortId());
				System.err.println("minnorPort.getMasterCode() :"+minnorPort.getMasterCode());
				username = minnorPort.getMasterCode() + "001";
				System.err.println("üser name created :"+username);
			}
		}
		return username;
	}

	@Override
	public List<RoleAuthorizationBean> findroleAuthByRoleId(Long id) {
		List<RoleAuthorization> authorizationList = userDao.fetchRoleAuthByRoleId(id);
		List<RoleAuthorizationBean> roleBean = new ArrayList<RoleAuthorizationBean>();
		for (RoleAuthorization roleAuthorization : authorizationList) {
			RoleAuthorizationBean authorizationBean = new RoleAuthorizationBean();
			try {
				BeanUtils.copyProperties(authorizationBean, roleAuthorization);
				roleBean.add(authorizationBean);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return roleBean;
	}

	@Override
	public ServiceResponse fetchRoleAuthorizationByRoleId(long roleId) {
		LOGGER.info("Executing fetchRoleAuthorizationByRoleId  method of UserServiceImpl");
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<RoleAuthorization> roleAuthorization = userDao.fetchRoleAuthByRoleId(roleId);
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("totalSize", roleAuthorization.size());
			response.put("roleAuthList", roleAuthorization);

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
	public ServiceResponse downdloadUserExcelFile(SearchUserBean searchUserBean) {
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<UserDetail> userDetails = userDao.fetchUser(searchUserBean, response);
			Map<Integer, Object[]> userData = new TreeMap<Integer, Object[]>();
			userData.put(0, new Object[] { "S.No.", "Login ID", "User Name", "Organization", "Role", "State", "Port",
					"Maritime Board", "Line Ministry", "Department", "Agency" });
			int[] i = { 0 };
			userDetails.forEach(e -> {
				LOGGER.info(" " + e);
				userData.put(++i[0],
						new Object[] { i[0], e.getUserName(), e.getName(), e.getOrganisationId().getMasterName(),
								e.getRole().getRoleName(), e.getState().getMasterName(), e.getPort().getPortName(),
								e.getMaritimeBoardId().getMaritimeBoardName(), e.getLineMinistryId().getMasterName(),
								e.getDepartmentId().getDepartmentName(), e.getAgency().getMasterName() });
			});
			LOGGER.info(userData.toString());
			String filename = CommonUtility.createExcelFile(userData, environment.getRequiredProperty("file.location"));
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("path", environment.getRequiredProperty("file.url") + filename);
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
	public ServiceResponse downdloadUserPdfFile(SearchUserBean searchUserBean) {
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			List<UserDetail> userDetails = userDao.fetchUser(searchUserBean, response);
			Map<Integer, Object[]> userData = new TreeMap<Integer, Object[]>();
			userData.put(0, new Object[] { "S.No.", "Login ID", "User Name", "Organization", "Role", "State", "Port",
					"Maritime Board", "Line Ministry", "Department", "Agency" });
			int[] i = { 0 };
			userDetails.forEach(e -> {
				LOGGER.info(" " + e);
				userData.put(++i[0],
						new Object[] { i[0], e.getUserName(), e.getName(), e.getOrganisationId().getMasterName(),
								e.getRole().getRoleName(), e.getState().getMasterName(), e.getPort().getPortName(),
								e.getMaritimeBoardId().getMaritimeBoardName(), e.getLineMinistryId().getMasterName(),
								e.getDepartmentId().getDepartmentName(), e.getAgency().getMasterName() });
			});
			LOGGER.info(userData.toString());
			String filename = CommonUtility.createPdfFile(userData, environment.getRequiredProperty("file.location"));
			responseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("customResponse", responseStatus);
			response.put("path", environment.getRequiredProperty("file.url") + filename);

		} catch (Exception e) {
			responseStatus = new CustomReponseStatus(Status.FAILED.getResponseId(), Status.FAILED.getResponseCode(),
					Status.FAILED.getResponseMessage(), Status.FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
			e.printStackTrace();
		}
		serviceResponse.setServiceResponse(response);
		return serviceResponse;
	}

	@Override
	public List<DynamicAddUserMenuDTO> fetchAllSubMenuByMainMenuId(int id)
			throws IllegalAccessException, InvocationTargetException {
		List<DynamicAddUserMenuEntity> addUserMenuEntities = userDao.findAllSubMenusByMainMenuId(id);
		List<DynamicAddUserMenuDTO> addUserMenuDTOs = new ArrayList<>();
		for (DynamicAddUserMenuEntity dynamicAddUserMenuEntity : addUserMenuEntities) {
			DynamicAddUserMenuDTO addUserMenuDTO = new DynamicAddUserMenuDTO();
			BeanUtils.copyProperties(addUserMenuDTO, dynamicAddUserMenuEntity);
			addUserMenuDTOs.add(addUserMenuDTO);
		}
		return addUserMenuDTOs;
	}

	/*
	 * (non-Javadoc) reset password by super admin.. (ramsagar)
	 * 
	 * @see
	 * org.sagarmala.service.UserService#resetPasswordBySuperAdmin(org.sagarmala
	 * .bean.UserBean)
	 */
	@Override
	public ServiceResponse resetPasswordBySuperAdmin(UserBean userBean) {
		LOGGER.info("Executing resetPasswordBySuperAdmin method of UserServiceImpl");
		CustomReponseStatus reponseStatus = null;
		LinkedHashMap<Object, Object> response = new LinkedHashMap<Object, Object>();
		ServiceResponse serviceResponse = new ServiceResponse();

		String encryptedNewPassword = passwordEncoder.encode(userBean.getNewPassword());
		UserDetail userDetail = userDao.findByUserName(userBean.getUserName());

		if (userDetail != null) {
			if (!userDetail.getPassword().equals(userBean.getNewPassword())) {

				if (userBean.getConfirmPassword().equals(userBean.getNewPassword())) {

					userDetail.setUserName(userBean.getUserName());
					userDetail.setPassword(encryptedNewPassword);
					userDetail.setUpdatedDate(CommonUtility.getCurrentDateTime());
					userDetail.setIsFirstAttempt(0);
					try {
						userDao.saveUser(userDetail);

						LOGGER.info("User password changed successfully...");
						String username = userBean.getUserName();
						String password = userBean.getConfirmPassword();
						String phone = userDetail.getMobileNumber();
						String emailId = userDetail.getEmail();

						String verificationLink = "<html> <head> <title> </title> </head> <table> <tr> <p> <span style=\"text-align: left;\">Dear Sir / Madam,</span> </p> </tr> <tr> <p> <span style=\"text-align: left;\">Your Sagar Mala password for account <span><b> "
								+ username + " </b></span> has been changed successfully on<span><b> "
								+ CommonUtility.getCurrentDateTime()
								+ " </b></span>If you have changed the password, please ignore the this email. </span> </p> </tr>  <tr> <p> <span style=\"text-align: left;\">If you have not changed the password, please click here <a href=\"#\">sagarMala.gov.in</a> to recover your account.</span> </p> </tr><tr> <p> <span style=\"text-align: left;\">This is auto generated email. Please do not reply to this.</span> </p> </tr>  <tr> <p> <span style=\"text-align: left;\">Thank You,<br/>SagarMala Team </span> </p> </tr> </table>";

						String to = emailId;
						String cc = null;
						String bcc = null;
						String subject = "Sagar Mala – Password Reset";
						String body = verificationLink;
						try {
							MimeMessage mailMessage = emailServiceImpl.sendVerificationMail(to, cc, bcc, subject, body);
							LOGGER.info("Email Send Successfully ###############");
							emailServiceImpl.sendEmail(mailMessage);
						} catch (Exception ex) {
							LOGGER.info("Exception Occured while Email Sending  ###############" + ex);
						}
						try {
							String message = "Your password changed successfuly for user name :" + username;
							smsService.sendOTP(phone, message);
							LOGGER.info("SMS Send Successfully ###############");
						} catch (Exception ex) {
							LOGGER.info("Exception Occured while SMS Sending  ###############" + ex);
						}
						reponseStatus = new CustomReponseStatus(Status.USER_PASSWORD_RESET_SUCCESS.getResponseId(),
								Status.USER_PASSWORD_RESET_SUCCESS.getResponseCode(),
								Status.USER_PASSWORD_RESET_SUCCESS.getResponseMessage(),
								Status.USER_PASSWORD_RESET_SUCCESS.getResponseDescription());
					} catch (Exception ex) {
						LOGGER.error(
								"Exception occured in resetPasswordBySuperAdmin method of UserServiceImpl..." + ex);
						reponseStatus = new CustomReponseStatus(Status.USER_PASSWORD_CHANGED_FAILED.getResponseId(),
								Status.USER_PASSWORD_CHANGED_FAILED.getResponseCode(),
								Status.USER_PASSWORD_CHANGED_FAILED.getResponseMessage(),
								Status.USER_PASSWORD_CHANGED_FAILED.getResponseDescription());
					}
				} else {
					reponseStatus = new CustomReponseStatus(Status.INVALID_NEW_PASSWORD.getResponseId(),
							Status.INVALID_NEW_PASSWORD.getResponseCode(),
							Status.INVALID_NEW_PASSWORD.getResponseMessage(),
							Status.INVALID_NEW_PASSWORD.getResponseDescription());
				}
			} else {
				reponseStatus = new CustomReponseStatus(Status.INVALID_NEW_PASSWORD.getResponseId(),
						Status.INVALID_NEW_PASSWORD.getResponseCode(), Status.INVALID_NEW_PASSWORD.getResponseMessage(),
						Status.INVALID_NEW_PASSWORD.getResponseDescription());
			}
		} else {
			LOGGER.error("Invalid User..." + userBean.getUserId());
			reponseStatus = new CustomReponseStatus(Status.INVALID_USER.getResponseId(),
					Status.INVALID_USER.getResponseCode(), Status.INVALID_USER.getResponseMessage(),
					Status.INVALID_USER.getResponseDescription());
		}
		response.put("customResponse", reponseStatus);
		serviceResponse.setServiceResponse(response);
		LOGGER.info("resetPasswordBySuperAdmin method execution completed #####");
		return serviceResponse;
	}

	@Override
	public ServiceResponse saveA(Agency userBean) {
		CustomReponseStatus reponseStatus = null;
		LinkedHashMap<Object, Object> response = new LinkedHashMap<Object, Object>();
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			userDao.saveAgency(userBean);
			reponseStatus = new CustomReponseStatus(Status.SUCCESS.getResponseId(), Status.SUCCESS.getResponseCode(),
					Status.SUCCESS.getResponseMessage(), Status.SUCCESS.getResponseDescription());
			response.put("CustomReponseStatus", reponseStatus);
			serviceResponse.setServiceResponse(response);
		} catch (Exception ex) {

		}

		return serviceResponse;
	}

	@Override
	public ServiceResponse updateUserForAssignProj(UserDetail user) {
		ServiceResponse serviceResponse = new ServiceResponse();
		LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
		CustomReponseStatus responseStatus = null;
		try {
			//UserDetail userDetail = userDao.findByUserId(user.getUserId());
			UserDetail userDetail=userDao.findByUserId(user.getUserId());
			userDetail.setUpdatedBy(user.getUpdatedBy());
			userDetail.setUpdatedDate(CommonUtility.getCurrentDateTime());
			userDetail.setProjects(user.getProjects());
			
			userDao.saveUser(userDetail);
			
			if(user.getProjects()!=null && user.getProjects().size()!=0){
			responseStatus = new CustomReponseStatus(Status.PROJECT_ASSIGN_SUCCESS.getResponseId(),
					Status.PROJECT_ASSIGN_SUCCESS.getResponseCode(), Status.PROJECT_ASSIGN_SUCCESS.getResponseMessage(),
					Status.PROJECT_ASSIGN_SUCCESS.getResponseDescription());
			
			}
			else{
				responseStatus = new CustomReponseStatus(Status.PROJECT_DEALLOCATE_SUCCESS.getResponseId(),
						Status.PROJECT_DEALLOCATE_SUCCESS.getResponseCode(), Status.PROJECT_DEALLOCATE_SUCCESS.getResponseMessage(),
						Status.PROJECT_DEALLOCATE_SUCCESS.getResponseDescription());
			}
			response.put("customResponse", responseStatus);
			serviceResponse.setServiceResponse(response);
		} catch (Exception e) {
			e.printStackTrace();
			responseStatus = new CustomReponseStatus(Status.PROJECT_ASSIGN_FAILED.getResponseId(),
					Status.PROJECT_ASSIGN_FAILED.getResponseCode(), Status.PROJECT_ASSIGN_FAILED.getResponseMessage(),
					Status.PROJECT_ASSIGN_FAILED.getResponseDescription());
			response.put("customResponse", responseStatus);
			serviceResponse.setServiceResponse(response);
			LOGGER.error(e.getMessage());
		}
		return serviceResponse;
	}
}
