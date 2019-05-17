package org.sagarmala.exception;

import java.util.LinkedHashMap;

import org.sagarmala.dto.CustomReponseStatus;
import org.sagarmala.dto.ServiceResponse;
import org.sagarmala.enumtype.constants.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalException.class);

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ServiceResponse> exceptionHandler(Exception ex) {
		CustomReponseStatus customReponseStatus = null;
		LinkedHashMap<Object, Object> response = new LinkedHashMap<Object, Object>();
		ServiceResponse serviceResponse = new ServiceResponse();
		customReponseStatus = new CustomReponseStatus(Status.ERROR_OCCUR.getResponseId(),
				Status.ERROR_OCCUR.getResponseCode(), Status.ERROR_OCCUR.getResponseMessage(),
				Status.ERROR_OCCUR.getResponseDescription());
		response.put("customResponse", customReponseStatus);
		serviceResponse.setServiceResponse(response);
		LOGGER.info("forgetPassword method execution completed #####");
		return new ResponseEntity<ServiceResponse>(serviceResponse, HttpStatus.OK);

	}

}
