package org.sagarmala.enumtype.constants;

public enum Status {

	SUCCESS(1, 200, "Success", "Successfully Done"), FAILED(2, 500, "Failed", "Fail to Execute"), DUPLICATE_EXCEPTION(3,
			500, "Duplicate Value", "User Already Exists"), BLOCKED_USER(4, 500, "Unauthorised User",
					"Blocked User" + ""), INVALID_USER_NAME_PASSWORD(5, 401, "Bad Credentials",
							"Invalid Username or Password"), USER_CREATION_SUCCESS(6, 200, "Success",
									"User Created Successfully and Username & Password Sent in Email"), USER_CREATION_FAILED(
											7, 500, "Bad Request",
											"Failed to Create User"), USER_PASSWORD_CHANGED_SUCCESS(8, 200, "Success",
													"Password changed Successfully"), USER_PASSWORD_CHANGED_FAILED(9,
															500, "Bad Request",
															"Failed to Create User"), CHANGED_STATUS_SUCCESS(10, 200,
																	"Success",
																	"Status changed Successfully"), CHANGED_STATUS_FAILED(
																			11, 500, "Bad Request",
																			"Failed to Change Status"), USER_PROFILE_UPDATE_SUCCESS(
																					12, 200, "Success",
																					"User Profile Update Successfully"), USER_PROFILE_UPDATE_FAILED(
																							13, 500, "Bad Request",
																							"Failed to Update User Profile"), DEACTIVATE_USER(
																									14, 401,
																									"Unauthorized User",
																									"Deactivate User"), INVALID_MOBILE_NUMBER(
																											15, 500,
																											"Bad Request",
																											"Enter Correct Mobile Number"), INVALID_EMAIL(
																													16,
																													500,
																													"Bad Request",
																													"Invalid Email ID"), PASSWORD_CHANGED_FAILED(
																															17,
																															500,
																															"Bad Request",
																															"Enter Wrong Current Password"), INVALID_NAME(
																																	17,
																																	500,
																																	"Bad Request",
																																	"Invalid Name Input"), LEVEL_CONFIG(
																																			18,
																																			500,
																																			"Invalid",
																																			"Already Configured Level"), ASSIGN_NULL(
																																					19,
																																					500,
																																					"Atleast",
																																					"Please Select Atleast One"), SAME_PASSWORD_FAILED(
																																							20,
																																							500,
																																							"Bad Request",
																																							"Enter Same Password current And Old "), ASSIGN_USER(
																																									21,
																																									401,
																																									"Unauthorized User",
																																									"First Assign Permission To User."), EMAIL_SEND_SUCCESSFULLY(
																																											22,
																																											200,
																																											"Email Send Successfully",
																																											"Email sent. Follow instructions in email to verify your email id"
																																													+ ""), INVALID_EMAIL_ID(
																																															23,
																																															500,
																																															"Bad Input",
																																															"Invalid Email Address"), USER_DOES_NOT_EXIST(
																																																	24,
																																																	500,
																																																	"Bad Request",
																																																	"User Doesn't Exist"), USER_ROLE_CREATE(
																																																			25,
																																																			200,
																																																			"Role Creation Success",
																																																			"Role Created Successfully"), USER_ROLE_UPDATE(
																																																					26,
																																																					200,
																																																					"Role Updation Success",
																																																					"Role Updated Successfully"), ERROR_OCCUR(
																																																							27,
																																																							500,
																																																							"Error",
																																																							"Please Contact Admin Something Not Working"), INVALID_NEW_PASSWORD(
																																																									28,
																																																									500,
																																																									"Bad Request",
																																																									"Old Password And New Password Should Not Be Same"
																																																											+ ""), INVALID_USER(
																																																													28,
																																																													500,
																																																													"Bad Request",
																																																													"Invalid UserName or Password"), SMS_SENT_SUCCESS(
																																																															29,
																																																															200,
																																																															"Success",
																																																															"SMS Sended Successfully"), ALREADY_NAME(
																																																																	17,
																																																																	500,
																																																																	"Bad Request",
																																																																	"Login Id Already Exist."),ALREADY_EMAIL(
																																																																			18,
																																																																			500,
																																																																			"Bad Request",
																																																																			"Email Id Already Exist."),ALREADY_PHONE(
																																																																					19,
																																																																					500,
																																																																					"Bad Request",
																																																																					"Phone Number Already Exist."),USER_PASSWORD_RESET_SUCCESS(20, 200, "Success",
																																																																							"Password Reset Successfully"),ALREADY_NAME_EXIST(
																																																																									20,
																																																																									500,
																																																																									"Bad Request",
																																																																									"Name Already Exist."),
																																																																									PROJECT_ASSIGN_SUCCESS(21, 200,
																																																																											"Success",
																																																																											"Projects Assigned Successfully"),PROJECT_ASSIGN_FAILED(
																																																																													22, 500, "Bad Request",
																																																																													"Failed to Assign Project"),
																																																																									PROJECT_DEALLOCATE_SUCCESS(23, 200,
																																																																											"Success",
																																																																											"Project Dealocated Successfully");

	private long responseId;
	private long responseCode;
	private String responseMessage;
	private String responseDescription;

	Status(long responseId, long responseCode, String responseMessage, String responseDescription) {
		this.responseId = responseId;
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.responseDescription = responseDescription;
	}

	public long getResponseId() {
		return responseId;
	}

	public void setResponseId(long responseId) {
		this.responseId = responseId;
	}

	public long getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(long responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
}
