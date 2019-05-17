package org.sagarmala.dto;

import io.swagger.annotations.ApiModelProperty;

public class CustomReponseStatus {
	@ApiModelProperty(notes = "api response code")
	private Long responseCode;
	@ApiModelProperty(notes = "api response message")
	private String responseMessage;
	@ApiModelProperty(notes = "api response full description")
	private String responseDescription;
	@ApiModelProperty(notes = "generate id")
	private Long responseId;
	
	public CustomReponseStatus(Long responseId, Long responseCode, String responseMessage, String responseDescription) {
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

	public void setResponseCode(long responseCode) {
		this.responseCode = responseCode;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public long getResponseCode() {
		return responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public String toString() {
		return new StringBuilder().append("responseCode : ").append("" + responseCode).append(" , responseMessage : ")
				.append(responseMessage).append(" , responseDescription : ").append(responseDescription)
				.append(" , responseId : ").append(responseId).toString();
	}

}
